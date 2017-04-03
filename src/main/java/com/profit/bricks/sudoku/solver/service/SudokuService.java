/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profit.bricks.sudoku.solver.service;

import com.profit.bricks.sudoku.solver.exception.ExceptionTypes;
import com.profit.bricks.sudoku.solver.exception.SudokuException;
import com.profit.bricks.sudoku.solver.model.Sudoku;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
/**
 * 
 * This class presents service class. It includes solveThePuzzle methods
 * and return <code>Response</code> class as a JSON format.  
 *
 */
@Path("solve")
public class SudokuService {
    
    private final static Logger logger = Logger.getLogger(SudokuService.class.getName());

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SudokuService
     */
    public SudokuService() {
    }

    /**
     * Retrieves representation of an instance of com.profit.bricks.sudoku.solver.SudokuService
     * @param input
     * @return an instance of java.lang.String
     * @throws com.profit.bricks.sudoku.solver.exception.SudokuException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response solveThePuzzle(@QueryParam("input") String input)throws SudokuException  {
        logger.log(Level.INFO, "solve uri called with input parameter :" + input);
        Sudoku sd = new Sudoku(input);
        if(sd.solve(0,0)){
            logger.log(Level.INFO, "successfully send response...");
            return Response.status(Response.Status.OK).entity(sd).build();
        }
        throw new SudokuException(ExceptionTypes.CANNOT_BE_SUDOKU);
    }

    
}
