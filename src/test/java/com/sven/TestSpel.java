package com.sven;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class TestSpel
{
    
    class Author {
        
        private int age;
        private String name;
        public Author(final int age, final String name)
        {
            super();
            this.age = age;
            this.name = name;
        }
        public int getAge()
        {
            return age;
        }
        public void setAge(final int age)
        {
            this.age = age;
        }
        public String getName()
        {
            return name;
        }
        public void setName(final String name)
        {
            this.name = name;
        }
        
    }

    @Test
    public void test()
    {
        Author author = new Author(100, "kwan"); 
        
        Map<String, Object> map = new HashMap<>();
        map.put("author", author);
        
        StandardEvaluationContext context =
                new StandardEvaluationContext(map);
        
        context.addPropertyAccessor(new MapAccessor());
        
        ExpressionParser parser = new SpelExpressionParser();
        
        String obj = parser.parseExpression("'topicId: '.concat(author.name)").getValue(context, String.class);
        System.out.println(obj);
    }
}
