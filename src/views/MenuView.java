package views;

import controllers.MenuController;
import interfaces.View;

import utils.Constants;
import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel implements View {
    private final Game game;

    public MenuView(Game game) {
        this.game = game;
    }

    @Override
    public void initView() {
        setPreferredSize(Constants.DIMENSION_DEFAULT);
        setLayout(new GridBagLayout());
        JButton[] buttons = new JButton[Constants.MENU_OPTIONS.length];
        GridBagConstraints gbc = new GridBagConstraints();
        MenuController menuController=new MenuController(game);
        for(int i=0;i<Constants.MENU_OPTIONS.length;i++){
            buttons[i]=new JButton(Constants.MENU_OPTIONS[i]);
            buttons[i].setPreferredSize(new Dimension(200,40));
            buttons[i].setFont(Constants.FONT_BUTTON);
            buttons[i].addActionListener(menuController);
            buttons[i].setActionCommand(i+"");
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(0, 0, Constants.BOTTOM_GAP, 0);
            add(buttons[i], gbc);
        }
    }
}
