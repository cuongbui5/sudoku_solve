package views;

import controllers.ChooseLevelController;
import exceptions.FileException;
import interfaces.View;
import utils.Constants;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChooseLevelView extends JPanel implements View {
    private final Game game;

    public ChooseLevelView(Game game) {
        this.game = game;
    }

    @Override
    public void initView() {
        ChooseLevelController chooseLevelController=new ChooseLevelController(game);
        setPreferredSize(Constants.DIMENSION_DEFAULT);
        List<String> data=Utils.getLevel();
        if(data.size()==0)
            throw new FileException("Not found data level");
        JButton[] buttons = new JButton[data.size()];
        for (int i = 0; i< data.size(); i++) {
            buttons[i] = new JButton(i + "");
            buttons[i].setPreferredSize(new Dimension(50, 50));
            buttons[i].setFont(Constants.FONT_BUTTON);
            buttons[i].addActionListener(chooseLevelController);
            buttons[i].setActionCommand(i + "");
            this.add(buttons[i]);
        }
    }
}
