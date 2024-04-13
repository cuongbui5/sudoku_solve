package views;

import controllers.MenuController;
import interfaces.View;

import utils.Constants;
import javax.swing.*;
import java.awt.*;

import static utils.Constants.backgroundImage;

public class MenuView extends JPanel implements View {

    private final Game game;

    public MenuView(Game game) {
        this.game = game;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void initView() {
        setPreferredSize(Constants.DIMENSION_DEFAULT);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Thêm padding cho các thành phần

        JLabel titleLabel = new JLabel("Sudoku Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32)); // Thiết lập kiểu chữ cho tiêu đề
        titleLabel.setForeground(Color.WHITE); // Màu chữ xanh
        add(titleLabel, gbc);



        JButton[] buttons = new JButton[Constants.MENU_OPTIONS.length];
        MenuController menuController = new MenuController(game);
        for (int i = 0; i < Constants.MENU_OPTIONS.length; i++) {
            gbc.gridy++; // Di chuyển đến hàng tiếp theo
            buttons[i] = new JButton(Constants.MENU_OPTIONS[i]);
            buttons[i].setPreferredSize(new Dimension(200, 40));
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 18)); // Thiết lập kiểu chữ cho các nút
            buttons[i].setForeground(Color.BLUE); // Màu chữ trắng
            buttons[i].setBackground(new Color(30, 144, 255)); // Màu nền xanh dương
            buttons[i].addActionListener(menuController);
            buttons[i].setActionCommand(String.valueOf(i));
            buttons[i].setFocusPainted(false); // Ẩn viền khi focus
            gbc.insets = new Insets(5, 0, 5, 0); // Điều chỉnh khoảng cách giữa các nút
            add(buttons[i], gbc);
        }
    }
}
