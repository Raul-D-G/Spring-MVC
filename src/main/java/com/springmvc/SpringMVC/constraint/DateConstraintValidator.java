package com.springmvc.SpringMVC.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConstraintValidator implements ConstraintValidator<ValidDate, String> {

    @Override
    public void initialize(ValidDate arg0) {
    }

    @Override
    public boolean isValid(String dateStr, ConstraintValidatorContext context) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        try {
            LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            String messages = e.getMessage();
            String messageTemplate = String.join(",", messages);
            messageTemplate = messageTemplate.replace(',', '\n');
            context.buildConstraintViolationWithTemplate(messageTemplate)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }
        return true;
    }
}
