package controllers;

import utils.Constants;
import views.Game;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class OverGameController implements ActionListener {
    private final Game game;

    public OverGameController(Game game) {
        this.game = game;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int value = Integer.parseInt(e.getActionCommand());
        if (value == Constants.CONTINUE) {
            game.gameStart();
        } else {
            game.changeView(Constants.MENU);
        }
    }
}
