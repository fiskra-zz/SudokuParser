/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profit.bricks.sudoku.solver.exception;

/**
 * 
 * This class provides types of custom exception and it's definitions.
 *
 */
public enum ExceptionTypes {
    
    LENGTH_MUST_BE_VALID("Length of given sudoku pattern must be valid."),   
    ILLEGAL_VALUE("Given sudoku pattern includes illegal argument."),
    CANNOT_BE_SUDOKU("Given sudoku pattern can not be completed.");
    private final String description;
    
    private ExceptionTypes(String description){
    this.description = description;
    
    }
    public String getDescription() {
        return description;
    }
}
