package com.springmvc.SpringMVC.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateConstraintValidator implements ConstraintValidator<ValidDate, Date> {

    @Override
    public void initialize(ValidDate arg0) {
    }

    @Override
    public boolean isValid(Date dateStr, ConstraintValidatorContext context) {

        return true;

//        System.out.println(dateStr.toString());
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        try {
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            df.setLenient(false);
//            df.parse(dateStr.toString());
////            LocalDate.parse(dateStr.toString(), dateFormatter);
//        } catch (ParseException e) {
//            String messages = e.getMessage();
//            String messageTemplate = String.join(",", messages);
//            messageTemplate = messageTemplate.replace(',', '\n');
//            context.buildConstraintViolationWithTemplate(messageTemplate)
//                    .addConstraintViolation()
//                    .disableDefaultConstraintViolation();
//            return false;
//        }
//        return true;
    }
}
