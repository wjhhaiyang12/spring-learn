package com.example.dbtest.controllers;

import com.example.dbtest.model.ValidationTest;
import com.example.dbtest.validation.GroupTest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @RequestMapping("/validationTest")
    public String validationTest(@Validated ValidationTest validationTest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(validationTest.getValidation());
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "参数校验通过";
    }


}
