package com.chris.cloud.spel;


import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Date;

@Slf4j
public class SpELDemo2 {

    ExpressionParser parser;

    @Before
    public void init() {
        // 定义SpEL解析器
        parser = new SpelExpressionParser();
    }


    @Test
    public void test01() {
        String template = "#{#varA+#varB}";
        Expression exp = parser.parseExpression(template, ParserContext.TEMPLATE_EXPRESSION); // 表达式解析

        StandardEvaluationContext context = new StandardEvaluationContext();// 表达式上下文配置

        // 定义变更内容
        context.setVariable("varA", "hello");
        context.setVariable("varB", ", magic SpEL");

        // 字符串类型的计算处理
        String value = exp.getValue(context, String.class);
        log.info("value:{}", value);

        // 数字类型的计算处理
        context.setVariable("varA", 1212);
        context.setVariable("varB", 3.33);
        Double result = exp.getValue(context, Double.class);
        log.info("result:{}", result);
    }

    @Test
    public void test02() {
        // 根变量与普通变量的区别在于其可以直接在实例化表达式解析上下文时进行根变量的配置（构造方法上的特权使用，仅此而已）
        String template = "#{#root.contains('hello')}";

        Expression exp = parser.parseExpression(template, ParserContext.TEMPLATE_EXPRESSION); // 表达式解析
        StandardEvaluationContext context = new StandardEvaluationContext("hello, happy new year");// 表达式上下文配置根对象
        Boolean result = exp.getValue(context, Boolean.class);
        log.info("result:{}", result);
    }

    /**
     * 对919进行转型操作，将其转换为Integer的对象实例
     */
    @Test
    public void test03() throws NoSuchMethodException {
        String template = "#{#convert('919')}";
        Expression exp = parser.parseExpression(template, ParserContext.TEMPLATE_EXPRESSION); // 表达式解析

        Method method = Integer.class.getMethod("parseInt", String.class);

        StandardEvaluationContext context = new StandardEvaluationContext();// 表达式上下文配置
        context.setVariable("convert", method); // 方法引用的实现

        Integer result = exp.getValue(context, Integer.class);
        log.info("result:{}", result);
    }


    @Test
    public void test04() {
        // java.util.Date类中提供有getTime()方法，所以此处可以直接采用time的标记进行属性表示
        String template = "#{#var.time}";
        Expression exp = parser.parseExpression(template, ParserContext.TEMPLATE_EXPRESSION); // 表达式解析

        StandardEvaluationContext context = new StandardEvaluationContext();// 表达式上下文配置
        context.setVariable("var", new Date());

        Long time = exp.getValue(context, Long.class);
        log.info("result:{}", time);
    }


    /**
     * 对于SpEL，需要确保对象存在，如果对象不存或为null在则会出下异常
     * org.springframework.expression.spel.SpelEvaluationException: EL1007E: Property or field 'time' cannot be found on null
     * <p>
     * 解决方案：在SpEL中使用Groovy表达式进行判断处理
     */
    @Test
    public void test05() {
        // 首先会判断var对象是否存在，如果不存在则直接返回null,如果存在则继续判断属性是否存在，如果存在则返回属性值，如果不存在则返回null
        String template = "#{#var?.time}";
        Expression exp = parser.parseExpression(template, ParserContext.TEMPLATE_EXPRESSION); // 表达式解析

        StandardEvaluationContext context = new StandardEvaluationContext();// 表达式上下文配置
        context.setVariable("var", null);

        Long time = exp.getValue(context, Long.class);
        log.info("result:{}", time);
    }

    /**
     * Java中最大的处理机制在于反射机制，包括JDK之中的内存结构的不断优化实际上也是与反射机制有直接的关联，比如：永久代被替换为元空间
     * <p>
     * Spring中最重要的特点就是通过字符串描述出更多的内容，那么对于反射机制也可以通过字符串的形式进行描述
     * <p>
     * 这种代码操作的核心意义在于：直接在配置文件里面编写的字符串可以定义任意数据类型
     * <p>
     * #{T(java.lang.String)} class表达式：class java.lang.String
     * #{T(java.util.Date)} class表达式：class java.util.Date
     * #{T(java.lang.Integer).MAX_VALUE} class表达式：2147483647
     * #{T(java.lang.Integer).parseInt('123')} class表达式：123
     */
    @Test
    public void test06() {
        log.info("#{T(java.lang.String)} class表达式：{}", spel("#{T(java.lang.String)}"));
        log.info("#{T(java.util.Date)} class表达式：{}", spel("#{T(java.util.Date)}"));

        // 通过字符串反射定义的静态常量和方法
        log.info("#{T(java.lang.Integer).MAX_VALUE} class表达式：{}", spel("#{T(java.lang.Integer).MAX_VALUE}"));
        log.info("#{T(java.lang.Integer).parseInt('123')} class表达式：{}", spel("#{T(java.lang.Integer).parseInt('123')}"));
    }

    private Object spel(String content) {
        Expression exp = parser.parseExpression(content, ParserContext.TEMPLATE_EXPRESSION);
        StandardEvaluationContext context = new StandardEvaluationContext();
        return exp.getValue(context);
    }

    /**
     * 字符串表达式还可以直接定义对象，比如：#{new com.chris.cloud.spel.bean.Book('Spring开发实战5',64.5)}
     * <p>
     * 直接使用字符串的'new'，根据类中提供的双参构造器方法实现对象的实例化，但这里本质上是通过SpEL解析得来的
     */
    @Test
    public void test07() {
        Object book = spel("#{new com.chris.cloud.spel.bean.Book('Spring开发实战5',64.5)}");
        log.info("book:{}", book);
    }

}
