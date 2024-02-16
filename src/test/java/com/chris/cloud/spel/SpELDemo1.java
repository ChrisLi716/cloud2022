package com.chris.cloud.spel;


import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@Slf4j
public class SpELDemo1 {

    ExpressionParser parser;

    @Before
    public void init() {
        parser = new SpelExpressionParser();
    }

    /**
     * 字面表达式：
     * 字符串中的内容是最终转换后的结果，字符串定义了什么，最终就可以得到什么。
     * <p>
     * 示例：加#时SpEL表达式才可以解析，不加时是解析不了的，SpEL表达式解析时单引号和双引号在SpEL中的效果是一样的
     * content:'hello'+", Chris", result:'hello'+", Chris", 对象类型:class java.lang.String
     * content:#{'hello'+", Chris"}, result:hello, Chris, 对象类型:class java.lang.String
     * <p>
     * 如果传递的是一些基础的数据内容，也会自动的进行类型转换
     * content:#{1}, result:1, 对象类型:class java.lang.Integer
     * content:#{1.1}, result:1.1, 对象类型:class java.lang.Double
     * content:#{true}, result:true, 对象类型:class java.lang.Boolean
     */
    @Test
    public void test01() {
        String str1 = "'hello'+\", Chris\"";
        String str2 = "#{'hello'+\", Chris\"}";
        spel(str1);
        spel(str2);

        String str3 = "#{1}";
        String str4 = "#{1.1}";
        String str5 = "#{true}";
        spel(str3);
        spel(str4);
        spel(str5);

    }

    private void spel(String content) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(content, ParserContext.TEMPLATE_EXPRESSION);
        Object result = exp.getValue();
        log.info("content:{}, result:{}, 对象类型:{}", content, result, result.getClass());
    }


    /**
     * 数学表达式
     * 示例：
     * content:#{1+2-3*4/5}, result:1, 对象类型:class java.lang.Integer
     * content:#{1+2-3*4/5.0}, result:0.6000000000000001, 对象类型:class java.lang.Double
     * content:#{10%3}, result:1, 对象类型:class java.lang.Integer
     * content:#{10 mod 3}, result:1, 对象类型:class java.lang.Integer
     * content:#{10 div 3}, result:3, 对象类型:class java.lang.Integer
     * content:#{10 ^ 3}, result:1000, 对象类型:class java.lang.Integer
     */
    @Test
    public void test02() {
        spel("#{1+2-3*4/5}");
        spel("#{1+2-3*4/5.0}");
        spel("#{10%3}");
        spel("#{10 mod 3}");
        spel("#{10 div 3}");
        spel("#{10 ^ 3}");
    }


    /**
     * 关系表达式
     * 示例：
     * content:#{30!=20}, result:true, 对象类型:class java.lang.Boolean
     * content:#{30 NE 20}, result:true, 对象类型:class java.lang.Boolean
     * content:#{'chris' > 'Chris'}, result:true, 对象类型:class java.lang.Boolean
     * content:#{10+20 eq 30}, result:true, 对象类型:class java.lang.Boolean
     */
    @Test
    public void test03() {
        spel("#{30!=20}");
        spel("#{30 NE 20}");
        spel("#{'chris' > 'Chris'}");
        spel("#{10+20 eq 30}");
    }

    /**
     * 逻辑表达式
     * 示例：
     * content:#{30!=20 || 10==10}, result:true, 对象类型:class java.lang.Boolean
     * content:#{'chris' > 'Chris' && 'Ethan' lt 'ethan'}, result:true, 对象类型:class java.lang.Boolean
     * content:#{10 between {1,100} && 1==1}, result:true, 对象类型:class java.lang.Boolean
     * content:#{'y' between {'a','c'} }, result:false, 对象类型:class java.lang.Boolean
     */
    @Test
    public void test04() {
        spel("#{30!=20 || 10==10}");
        spel("#{'chris' > 'Chris' && 'Ethan' lt 'ethan'}");
        spel("#{10 between {1,100} && 1==1}");
        spel("#{'y' between {'a','c'} }");
    }

    /**
     * 三目运算符
     * SpEL中支持三目运算符
     * 示例：
     * content:#{'chris'!=null? 'hello,chris':'who are you?'}, result:hello,chris, 对象类型:class java.lang.String
     * content:#{null==null? 'hello,chris':'who are you?'}, result:hello,chris, 对象类型:class java.lang.String
     * content:#{false ? 'hello,chris':'who are you?'}, result:who are you?, 对象类型:class java.lang.String
     * <p>
     * 同进也支持Groovy中扩展的三目结构,如果判断内容为null,则返回fase的结果，如果不为null,则返回判断内容本身
     * 示例：
     * content:#{null ?:'it is null'}, result:it is null, 对象类型:class java.lang.String
     * content:#{'chris' ?:'it is null'}, result:chris, 对象类型:class java.lang.String
     */
    @Test
    public void test05() {
        spel("#{'chris'!=null? 'hello,chris':'who are you?'}");
        spel("#{null==null? 'hello,chris':'who are you?'}");
        spel("#{false ? 'hello,chris':'who are you?'}");

        spel("#{null ?:'it is null'}");
        spel("#{'chris' ?:'it is null'}");
    }


    /**
     * 字符串处理
     * 示例：
     * content:#{'spring express language'[4]}, result:n, 对象类型:class java.lang.String
     * content:#{'spring express language'.substring(4, 'spring express language'.length())}, result:ng express language, 对象类型:class java.lang.String
     * content:#{'www.bing.com'.matches('\w+\.\w+\.\w+')}, result:true, 对象类型:class java.lang.Boolean
     * content:#{'www.bing.com'matches '\w+\.\w+\.\w+'}, result:true, 对象类型:class java.lang.Boolean
     */
    @Test
    public void test06() {
        spel("#{'spring express language'[4]}");
        spel("#{'spring express language'.substring(4, 'spring express language'.length())}");
        spel("#{'www.bing.com'.matches('\\w+\\.\\w+\\.\\w+')}");
        spel("#{'www.bing.com' matches '\\w+\\.\\w+\\.\\w+'}");
    }
}
