package controllers;

import models.SodokuModel;
import utils.Constants;
import views.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MenuController implements ActionListener {
    private final Game game;

    public MenuController(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int value = Integer.parseInt(e.getActionCommand());
        SodokuModel sodokuModel = game.getSodokuModel();
        if (value == 0) {
            sodokuModel.setType(Constants.EASY);
            game.changeView(Constants.CHOOSE_LEVEL);
        } else if (value == 1) {
            sodokuModel.setType(Constants.MEDIUM);
            game.changeView(Constants.CHOOSE_LEVEL);
        } else if (value == 2) {
            sodokuModel.setType(Constants.DIFFICULT);
            game.changeView(Constants.CHOOSE_LEVEL);
        } else {
            System.exit(0);
        }
    }



}
