/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profit.bricks.sudoku.solver.model;

import com.profit.bricks.sudoku.solver.exception.ExceptionTypes;
import com.profit.bricks.sudoku.solver.exception.SudokuException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * This class presents Sudoku which aims to fill missing numbers which is
 * between 1-9 and each row, column and each 3 x 3 box must includes distinct
 * numbers in [1-9] range.
 *
 */
public class Sudoku {

    private final static Logger logger = Logger.getLogger(Sudoku.class.getName());

    private int[][] puzzle = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public Sudoku() {
    }

    public Sudoku(String input) throws SudokuException {
        String[] inputPattern = null;

        inputPattern = input.split(",");
        if (inputPattern.length != 81) {
            logger.log(Level.WARNING, "{0}:{1}", new Object[]{ExceptionTypes.LENGTH_MUST_BE_VALID.getDescription(), input});
            throw new SudokuException(ExceptionTypes.LENGTH_MUST_BE_VALID);
        }
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                puzzle[row][column] = parseString(inputPattern[row * 9 + column]);
            }
        }

    }

    /**
     * parsing method
     *
     * @param s
     * @return
     */
    private int parseString(String s) throws SudokuException {
        char cell = s.charAt(0);
        switch (cell) {
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'x':
                return 0;
        }
        logger.log(Level.WARNING, "{0}:{1}", new Object[]{ExceptionTypes.ILLEGAL_VALUE.getDescription(), s});

        throw new SudokuException(ExceptionTypes.ILLEGAL_VALUE);
    }

    /**
     * check row
     *
     * @param value
     * @param row
     * @return
     */
    private boolean checkRow(int value, int row) {
        for (int column = 0; column < 9; column++) {
            if (puzzle[row][column] == value) {
                return false;
            }
        }
        return true;
    }

    /**
     * check column
     *
     * @param value
     * @param column
     * @return
     */
    private boolean checkColumn(int value, int column) {
        for (int row = 0; row < 9; row++) {
            if (puzzle[row][column] == value) {
                return false;
            }
        }
        return true;
    }

    /**
     * check 3 x 3 boxes if it fits the rule
     *
     * @param row
     * @param column
     * @param value
     * @return
     */
    private boolean checkBox(int row, int column, int value) {
        int boxRow = ((row / 3) * 3);
        int boxColumn = ((column / 3) * 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[boxRow + i][boxColumn + j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * recursive method to check rows and columns and boxes
     *
     * @param row
     * @param column
     * @return
     */
    public boolean solve(int row, int column) {
        if (row > 8) {
            row = 0;
            if (++column == 9) {
                return true;
            }
        }

        if (puzzle[row][column] != 0) {
            return solve(row + 1, column);
        } else {

            for (int value = 1; value < 10; value++) {
                if (checkRow(value, row) && checkColumn(value, column)
                        && checkBox(row, column, value)) {
                    puzzle[row][column] = value;
                    if (solve(row + 1, column)) {
                        return true;
                    }
                }
            }
            puzzle[row][column] = 0;
            return false;
        }
    }

    /**
     * user-friendly print method
     *
     * @return
     */
    private String print() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0) {
                sb.append("----------------");
                sb.append("\n");
            }
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0) {
                    sb.append("| ");
                }
                sb.append(puzzle[row][col] == 0 ? " " : puzzle[row][col]);
            }
            sb.append("| ");
            sb.append("\n");
        }
        sb.append("----------------");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Sudoku : " + "\n" + print();
    }

    public static void main(String[] args) throws Exception, SudokuException {
        Sudoku sd = new Sudoku(
                "5,3,x,x,7,x,x,x,x,6,x,x,1,9,5,x,x,x,x,9,8,x,x,x,x,6,x,8,x,x,x,6,x,x,x,3,4,x,x,8,x,3,x,x,1,7,x,x,x,2,x,x,x,6,x,6,x,x,x,x,2,8,x,x,x,x,4,1,9,x,x,5,x,x,x,x,8,x,x,7,9");
        sd.solve(0, 0);
        System.out.println(sd);
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

}
