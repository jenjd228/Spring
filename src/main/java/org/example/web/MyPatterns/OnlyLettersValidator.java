package org.example.web.MyPatterns;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlyLettersValidator implements ConstraintValidator<OnlyLetters, String> {

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        String passwordPravilo = "^[a-zA-Zа-яА-Я]+$";
        Pattern pattern = Pattern.compile(passwordPravilo);
        Matcher matcher = pattern.matcher(userName);
        boolean flag = matcher.find();
        return flag;
    }

}
