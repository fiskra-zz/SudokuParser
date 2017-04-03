/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profit.bricks.sudoku.solver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.profit.bricks.sudoku.solver.exception.ErrorMessage;
import com.profit.bricks.sudoku.solver.exception.ExceptionTypes;
import com.profit.bricks.sudoku.solver.exception.SudokuException;
import java.io.IOException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static junit.framework.Assert.assertEquals;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

/**
 *It represents unit test classf or <code>SudokuService</code> class
 *
 */
public class SudokuServiceTest extends JerseyTest {
    

    @Override
    protected Application configure() {
        return new ApplicationConfig();
    }
    
    

    @Test
    public void shouldReturnOkWithCorrectInputsOne() throws SudokuException {
        Response response = target("solve").queryParam("input", "x,6,3,4,9,x,x,x,1,x,x,x,x,x,x,7,x,9,x,1,9,x,x,x,x,x,x,x,x,1,x,x,2,9,3,x,9,x,x,1,x,7,x,x,2,x,7,8,9,x,x,4,x,x,x,x,x,x,x,x,8,2,x,3,x,6,x,x,x,x,x,x,4,x,x,x,2,9,1,7,x").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    @Test
    public void shouldReturnOkWithCorrectInputsTwo() throws SudokuException {
        Response response = target("solve").queryParam("input", "5,3,x,x,7,x,x,x,x,6,x,x,1,9,5,x,x,x,x,9,8,x,x,x,x,6,x,8,x,x,x,6,x,x,x,3,4,x,x,8,x,3,x,x,1,7,x,x,x,2,x,x,x,6,x,6,x,x,x,x,2,8,x,x,x,x,4,1,9,x,x,5,x,x,x,x,8,x,x,7,9").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void shouldReturnBadRequest() throws SudokuException {
        Response response = target("solve").queryParam("input", "6,3,4,9,x,x,x,1,x,x,x,x,x,x,7,x,9,x,1,9,x,x,x,x,x,x,x,x,1,x,x,2,9,3,x,9,x,x,1,x,7,x,x,2,x,7,8,9,x,x,4,x,x,x,x,x,x,x,x,8,2,x,3,x,6,x,x,x,x,x,x,4,x,x,x,2,9,1,7,x").request().get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void shouldReturnLengthMustBeValid() throws SudokuException, IOException {
        Response response = target("solve").queryParam("input", "6,3,4,9,x,x,x,1,x,x,x,x,x,x,7,x,9,x,x,x,x,x,x,x,x,x,1,x,x,2,9,3,x,9,x,x,1,x,7,x,x,2,x,7,8,9,x,x,4,x,x,x,x,x,x,x,x,8,2,x,3,x,6,x,x,x,x,x,x,4,x,x,x,2,9,1,7,x").request().accept(MediaType.APPLICATION_JSON).get();
        ErrorMessage obj = convertJsonToObject((String)response.readEntity(String.class));
        assertEquals(ExceptionTypes.LENGTH_MUST_BE_VALID.getDescription(), obj.getErrorMessage());
    }
    
    @Test
    public void shouldReturnIllegalArgument() throws SudokuException, IOException {
        Response response = target("solve").queryParam("input", "x,6,3,4,9,x,x,x,1,x,x,x,x,x,x,7,x,9,x,1,9,x,c,x,x,x,x,x,x,1,x,x,2,9,3,x,9,x,x,1,x,7,x,x,2,x,7,8,9,x,x,4,x,x,x,x,x,x,x,x,8,2,x,3,x,6,x,x,x,x,x,x,4,x,x,x,2,9,1,7,x").request().accept(MediaType.APPLICATION_JSON).get();
        ErrorMessage obj = convertJsonToObject((String)response.readEntity(String.class));
        assertEquals(ExceptionTypes.ILLEGAL_VALUE.getDescription(), obj.getErrorMessage());
    }
    @Test
    public void shouldReturnCannotBeSudoku() throws SudokuException, IOException {
        Response response = target("solve").queryParam("input", "x,x,x,2,6,x,7,x,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x").request().accept(MediaType.APPLICATION_JSON).get();
        ErrorMessage obj = convertJsonToObject((String)response.readEntity(String.class));
        assertEquals(ExceptionTypes.CANNOT_BE_SUDOKU.getDescription(), obj.getErrorMessage());
    }
    
    @Test
    public void shouldReturnBadRequestWithNullInput() throws SudokuException {
        Response response = target("solve").queryParam("input", "").request().get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    /**
     * This method converts json message format to ErrorMessage object
     * @param input
     * @return
     * @throws IOException 
     */
    private ErrorMessage convertJsonToObject(String input) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(input, ErrorMessage.class);
    }
}
