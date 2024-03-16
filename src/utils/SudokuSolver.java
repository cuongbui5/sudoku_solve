package utils;

import models.Node;
import models.SodokuModel;
import views.SodokuView;

public class SudokuSolver {
    private SodokuModel sodokuModel;
    private SodokuView sodokuView;

    public SudokuSolver(SodokuModel sodokuModel, SodokuView sodokuView) {
        this.sodokuModel = sodokuModel;
        this.sodokuView=sodokuView;
    }

    public boolean solveSudoku() throws InterruptedException {
        return solve(1, 1);
    }

    private boolean solve(int row, int col) throws InterruptedException {

        if(col>Constants.REAL_SIZE){
            col=1;
            row=row+1;
        }

        if(row>Constants.REAL_SIZE){
            return true;
        }

        Node node = sodokuModel.getFirst()[row][col];

        if (node.getValue() != 0) {
            // If the cell is already filled, move to the next cell
            int nextCol =col+1;
            return solve(row, nextCol);
        }

        for (int value : Constants.VALUES) {
            if (sodokuModel.validateNode(row, col, value)) {
                sodokuModel.updateData(row, col, value);
                if (solve(row, col + 1)) {
                    sodokuView.updateView(row,col,value);
                    return true;
                }
                sodokuModel.updateData(row, col, 0); // Backtrack
            }
        }

        return false; // No valid value found for this cell, need to backtrack
    }
}
