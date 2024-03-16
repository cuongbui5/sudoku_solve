package controllers;

import models.SodokuModel;
import utils.Constants;
import views.Game;
import views.SodokuView;
import views.TimeView;

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

    public SodokuController(SodokuView sodokuView, Game game, TimeView timeView) {
        this.sodokuView = sodokuView;
        this.game = game;
        this.timeView = timeView;
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


        if(value<1||value>9||colCurrent==0||rowCurrent==0||sodokuModel.getNodes()[rowCurrent][colCurrent].isProtect()){
            return;
        }

        if(!sodokuModel.validateNode(rowCurrent,colCurrent,value)){
            int checkFalse=sodokuModel.getCheckFalse()+1;
            sodokuModel.setCheckFalse(checkFalse);
            timeView.updateCheck(checkFalse);
            if(checkFalse==3){
                //nếu thua không được lưu dữ liệu player kiểm tra dựa vào statusEnd
                game.setStatusEnd(Constants.STATUS_LOSE);
                game.gameOver(Constants.MESSAGE_LOSE);
            }

        }else{
            sodokuModel.updateData(rowCurrent, colCurrent, value);
            sodokuView.updateView(rowCurrent,colCurrent,value);
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
