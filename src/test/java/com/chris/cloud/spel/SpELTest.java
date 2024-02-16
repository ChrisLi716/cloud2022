package com.chris.cloud.spel;


import com.chris.cloud.spel.bean.Demo;
import com.chris.cloud.spel.bean.Inventor;
import com.chris.cloud.spel.bean.Simple;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Slf4j
public class SpELTest {

    ExpressionParser parser;

    @Before
    public void init() {
        parser = new SpelExpressionParser();
    }

    /**
     * The following code introduces the SpEL API to evaluate the literal string expression, Hello World.
     */
    @Test
    public void test01() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        log.info("message:{}", message);
    }

    /**
     * 1. the following example shows how to retrieve the name property from an instance of the Inventor class
     * 2. create a boolean condition
     */
    @Test
    public void test02() {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, Calendar.AUGUST, 9);

        // The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        Expression exp = parser.parseExpression("name");
        String name = (String) exp.getValue(tesla);
        log.info("name:{}", name);

        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = Boolean.TRUE.equals(exp.getValue(tesla, Boolean.class));
        log.info("result:{}", result);


    }

    @Test
    public void test03() {
        Simple simple = new Simple();
        simple.booleanList.add(true);

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        // "false" is passed in here as a String. SpEL and the conversion service
        // will recognize that it needs to be a Boolean and convert it accordingly.
        parser.parseExpression("booleanList[0]").setValue(context, simple, "false");

        // b is false
        Boolean b = simple.booleanList.get(0);
        log.info("b:{}", b);

        log.info("simple:{}", simple);
    }

    @Test
    public void test04() {
        // Turn on:
        // - auto null reference initialization
        // - auto collection growing
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);

        ExpressionParser parser = new SpelExpressionParser(config);

        Expression expression = parser.parseExpression("list[3]");

        Demo demo = new Demo();

        Object o = expression.getValue(demo);
        log.info("o:{}", o);
    }


}
