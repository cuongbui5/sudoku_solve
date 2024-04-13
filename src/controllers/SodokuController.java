package controllers;

import models.Node;
import models.SodokuModel;
import utils.Constants;
import utils.SudokuSolver;
import views.Game;
import views.SodokuView;
import views.TimeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class SodokuController implements ActionListener, KeyListener {
    private final SodokuView sodokuView;
    private final Game game;
    private final TimeView timeView;
    private int rowCurrent=0;
    private int colCurrent=0;
    private final SudokuSolver sudokuSolver;

    public SodokuController(SodokuView sodokuView, Game game, TimeView timeView,SudokuSolver sudokuSolver) {
        this.sodokuView = sodokuView;
        this.game = game;
        this.timeView = timeView;
        this.sudokuSolver=sudokuSolver;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String[] data=e.getActionCommand().split("");
        rowCurrent=Integer.parseInt(data[0]);
        colCurrent=Integer.parseInt(data[1]);
    }


    @Override
    public void keyPressed(KeyEvent e) {

        int value=e.getKeyChar()-48;

        SodokuModel sodokuModel= game.getSodokuModel();


        if(value==55&&!sodokuModel.getNodes()[rowCurrent][colCurrent].isProtect()){
            int v=sudokuSolver.solveNode(rowCurrent,colCurrent);
            sodokuView.updateView(rowCurrent,colCurrent,v);
            sodokuModel.updateData(rowCurrent,colCurrent,v);

            if(sodokuModel.checkWin()){
                game.setStatusEnd(Constants.STATUS_WIN);
                game.gameOver(Constants.MESSAGE_WIN);
            }



        }


        if(value<1||value>9||colCurrent==0||rowCurrent==0||sodokuModel.getNodes()[rowCurrent][colCurrent].isProtect()){
            return;
        }

        if(!sodokuModel.validateNode(rowCurrent,colCurrent,value)){
            int checkFalse=sodokuModel.getCheckFalse()+1;
            int i=3-checkFalse;
            sodokuModel.setCheckFalse(checkFalse);
            timeView.updateCheck(checkFalse);
            if(i>0){
                JOptionPane.showMessageDialog(null, "Nếu sai "+i+" lượt nữa bạn sẽ thua!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

            if(checkFalse==3){
                //nếu thua không được lưu dữ liệu player kiểm tra dựa vào statusEnd
                game.setStatusEnd(Constants.STATUS_LOSE);
                game.gameOver(Constants.MESSAGE_LOSE);

            }

        }else{
            //check sodokuSolve

            int v= sudokuSolver.solveNode(rowCurrent,colCurrent);
            if(value==v){
                sodokuModel.updateData(rowCurrent, colCurrent, value);
                sodokuView.updateView(rowCurrent,colCurrent,value);
            }else {
                JOptionPane.showMessageDialog(null, "Sai rồi thử giá trị khác đi!.Lưu ý: giá trị bạn điền có thể đúng ở hiện tại nhưng sẽ tạo ra trường hợp không thể hoàn thành sodoku.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }





        }


        if(sodokuModel.checkWin()){
            game.setStatusEnd(Constants.STATUS_WIN);
            game.gameOver(Constants.MESSAGE_WIN);
        }



    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
