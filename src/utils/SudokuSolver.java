package utils;

import lombok.Getter;
import models.Node;
import models.SodokuModel;
import views.SodokuView;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

public class SudokuSolver {
    private SodokuModel sodokuModel;
    private SodokuView sodokuView;



    public Node[][] temResults;


    public SudokuSolver(SodokuModel sodokuModel, SodokuView sodokuView) {
        this.sodokuModel = sodokuModel;
        this.sodokuView=sodokuView;
    }

    public boolean checkRow(int row,int value) {
        for(int i=1;i<=9;i++){
            if(value==temResults[row][i].getValue()){
                return false;
            }
        }
        return true;
    }


    public boolean checkCol(int col, int value){
        for(int i=1;i<=9;i++){
            if(value==temResults[i][col].getValue()){
                return false;
            }
        }
        return true;
    }

    private static int getStartPos(int x) {
        if(x<=3)
            return 1;
        else if (x<=6)
            return 4;
        else
            return 7;

    }

    public boolean check3x3(int row,int col,int value) {
        int startCol=getStartPos(col);
        int endCol=startCol+2;
        int startRow=getStartPos(row);
        int endRow=startRow+2;
        for(int i=startRow;i<=endRow;i++){
            for(int j=startCol;j<=endCol;j++){
                if(temResults[i][j].getValue()==value)
                    return false;
            }

        }
        return true;
    }


    public boolean validateNode(int row, int col, int value) {
        return check3x3(row, col, value) && checkCol(col, value) && checkRow(row, value);

    }

    public void setTemResults(Node[][] temResults) {
        this.temResults = temResults;
    }

    public void updateTempResult(int row,int col,int value){
        temResults[row][col].setValue(value);
    }

    public int solveNode(int row, int col){
        temResults=null;
        if(solveSudoku()){
            int value=temResults[row][col].getValue();
            if(value>0&&value<10){
                if(sodokuModel.validateNode(row,col,value)){
                   return value;
                }else {
                    System.out.println("sai");
                    JOptionPane.showMessageDialog(null, "Bạn đã điền sai ở ô nào đó nên trình gợi ý không thể đưa ra kết quả!.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

            }
            return 0;

        }

        JOptionPane.showMessageDialog(null, "Error!.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        return 0;






    }


    public boolean solveSudoku(){
        if(temResults==null){
            temResults=new Node[Constants.REAL_SIZE+1][Constants.REAL_SIZE+1];
            for(int i=1;i<=Constants.REAL_SIZE;i++){
                for(int j=1;j<=Constants.REAL_SIZE;j++){
                    Node node=sodokuModel.getNodes()[i][j];
                    temResults[i][j]=new Node(i,j,node.getValue(),node.isProtect());
                }
            }
        }








        for (int i=1;i<=Constants.REAL_SIZE;i++){
            for(int j=1;j<=Constants.REAL_SIZE;j++){
                if(temResults[i][j].getValue()==0){
                    for(int n=1;n<=Constants.REAL_SIZE;n++){
                        if(validateNode(i,j,n)){
                            temResults[i][j].setValue(n);
                            if (solveSudoku()){
                                return true;
                            }
                            temResults[i][j].setValue(0);
                        }

                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solve(){
        System.out.println("solve");
        Node[][] nodes=sodokuModel.getNodes();
        for (int i=1;i<=Constants.REAL_SIZE;i++){
            for(int j=1;j<=Constants.REAL_SIZE;j++){
                if(nodes[i][j].getValue()==0){
                    for(int n=1;n<=Constants.REAL_SIZE;n++){
                        if(sodokuModel.validateNode(i,j,n)){
                            nodes[i][j].setValue(n);
                            if (solve()){
                                sodokuView.updateView(i,j,n);
                                return true;
                            }
                            nodes[i][j].setValue(0);
                        }

                    }
                    return false;
                }
            }
        }
        return true;
    }


    public Node[][] getTemResults() {
        return temResults;
    }
}
