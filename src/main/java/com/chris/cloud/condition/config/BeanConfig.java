package com.chris.cloud.condition.config;

import com.chris.cloud.condition.PetCondition;
import com.chris.cloud.condition.bean.Cat;
import com.chris.cloud.condition.bean.Dog;
import com.chris.cloud.condition.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

public class BeanConfig {

    @Bean
    public Person person() {
        return new Person();
    }

    @Bean
    public Cat cat() {
        return new Cat("cat");
    }

    /**
     * 如果把这个方法放在 `public Cat cat()`之前会报这个错，因为有两个Animal的实例Bean
     * Error creating bean with name 'person': Unsatisfied dependency expressed through field 'animal'; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.chris.cloud.conditional.bean.Animal' available: expected single matching bean but found 2: dog,cat
     *
     * @return Dog
     */
    @Bean
    @Conditional({PetCondition.class})
    public Dog dog() {
        return new Dog("dog");
    }

}
