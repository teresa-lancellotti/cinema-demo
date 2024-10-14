package com.example.springboot.configuration;

import java.lang.reflect.Field;

import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotUndefinedValidator implements ConstraintValidator<NotUndefined, Object>{

        @Override
        public void initialize(NotUndefined constraintAnnotation) {
        }

        @Override
        public boolean isValid(Object addressInformation, ConstraintValidatorContext context) {
            Class<?> objClass = addressInformation.getClass();
            Field[] fields = objClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.getType().equals(JsonNullable.class)){
                    field.setAccessible(true);
                    try {
                        Object value = field.get(addressInformation);
                        if(value.equals(JsonNullable.undefined())){
                            context.disableDefaultConstraintViolation();
                            context.buildConstraintViolationWithTemplate(field.getName() + " cannot be undefined")
                                    .addConstraintViolation();
                            return false;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
}
