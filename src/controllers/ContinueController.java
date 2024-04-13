package controllers;

import exceptions.FileException;
import utils.Constants;
import utils.Utils;
import views.Game;
import views.TimeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ContinueController implements ActionListener {
    private final Game game;

    public ContinueController( Game game) {
        this.game = game;
    }


    @Override
    public void actionPerformed(ActionEvent e) throws FileException {
        game.changeView(Constants.MENU);

    }


}
