/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profit.bricks.sudoku.solver.exception;

import java.io.Serializable;

/**
 * 
 * It presents custom exception class.
 *
 */
public class SudokuException extends Throwable  implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private ExceptionTypes exceptionTypes;

    public ExceptionTypes getExceptionTypes() {
        return exceptionTypes;
    }

    public void setExceptionTypes(ExceptionTypes exceptionTypes) {
        this.exceptionTypes = exceptionTypes;
    }

    public SudokuException() {
    }
    
    public SudokuException(Exception exp) {
        super(exp);
    }

    public SudokuException(ExceptionTypes exceptionTypes) {
        this.exceptionTypes = exceptionTypes;
    }

    public SudokuException(ExceptionTypes exceptionTypes, Exception exp) {
        super(exp);
        this.exceptionTypes = exceptionTypes;
    }

    
}
