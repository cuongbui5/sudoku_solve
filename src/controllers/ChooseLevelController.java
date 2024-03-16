package controllers;

import views.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ChooseLevelController implements ActionListener {
    private final Game game;

    public ChooseLevelController(Game game) {
        this.game = game;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int level=Integer.parseInt(e.getActionCommand());
        game.getSodokuModel().setLevel(level);
        game.gameStart();
    }
}
