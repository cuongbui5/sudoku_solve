package views;

import controllers.OverGameController;
import interfaces.View;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
public class OverGameView extends JPanel implements View {
    private JButton[] buttons;
    private static int bottomMessage=100;
    private JLabel messageLabel;
    private final Game game;

    public OverGameView(Game game) {
        this.game = game;
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[] buttons) {
        this.buttons = buttons;
    }

    public static int getBottomMessage() {
        return bottomMessage;
    }

    public static void setBottomMessage(int bottomMessage) {
        OverGameView.bottomMessage = bottomMessage;
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(JLabel messageLabel) {
        this.messageLabel = messageLabel;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void initView() {
        OverGameController overGameController=new OverGameController(game);
        setPreferredSize(Constants.DIMENSION_DEFAULT);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 10, 0); // Thêm padding cho messageLabel

        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Thiết lập kiểu chữ cho messageLabel
        add(messageLabel, gbc);

        buttons = new JButton[Constants.OVER_OPTIONS.length];
        for (int i = 0; i < Constants.OVER_OPTIONS.length; i++) {
            gbc.gridy++; // Di chuyển đến hàng tiếp theo
            buttons[i] = new JButton(Constants.OVER_OPTIONS[i]);
            buttons[i].setPreferredSize(new Dimension(200, 50));
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 18)); // Thiết lập kiểu chữ cho các nút
            buttons[i].addActionListener(overGameController);
            buttons[i].setActionCommand(String.valueOf(i));
            gbc.insets = new Insets(10, 0, 10, 0); // Điều chỉnh khoảng cách giữa các nút
            add(buttons[i], gbc);
        }
    }


    public void setMessageLabel(String message) {
        this.messageLabel.setText(message);
    }
}
