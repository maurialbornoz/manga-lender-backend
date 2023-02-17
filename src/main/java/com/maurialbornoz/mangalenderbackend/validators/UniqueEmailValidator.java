package com.maurialbornoz.mangalenderbackend.validators;

import com.maurialbornoz.mangalenderbackend.annotations.UniqueEmail;
import com.maurialbornoz.mangalenderbackend.dao.UserRepository;
import com.maurialbornoz.mangalenderbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByEmail(value);
        if(user == null){
            return true;
        }
        return false;
    }
}
