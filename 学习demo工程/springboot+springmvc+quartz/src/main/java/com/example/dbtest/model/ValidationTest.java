package com.example.dbtest.model;

import com.example.dbtest.validation.GroupTest;

import javax.validation.constraints.Min;

public class ValidationTest {

    @Min(value = 18, groups = {GroupTest.class})
    private int validation;

    public int getValidation() {
        return validation;
    }

    public void setValidation(int validation) {
        this.validation = validation;
    }
}
