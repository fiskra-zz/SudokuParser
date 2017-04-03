/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profit.bricks.sudoku.solver.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * It presents generic exception handler class to handle custom exceptions
 *
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<SudokuException> {

    @Override
    public Response toResponse(SudokuException ex) {
        Response.StatusType type = Response.Status.BAD_REQUEST;
        ErrorMessage error = new ErrorMessage(
                type.getStatusCode(),
                type.getReasonPhrase(),
                ex.getExceptionTypes().getDescription());

        return Response.status(error.getStatusCode())
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
