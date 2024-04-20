package views;

import controllers.ChooseLevelController;
import exceptions.FileException;
import interfaces.View;
import utils.Constants;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import static utils.Constants.backgroundImage;

public class ChooseLevelView extends JPanel implements View {
    private final Game game;


    public ChooseLevelView(Game game) {
        this.game = game;
    }

    @Override
    public void initView() {
        ChooseLevelController chooseLevelController=new ChooseLevelController(game);
        this.setLayout(new BorderLayout());
        setPreferredSize(Constants.DIMENSION_DEFAULT);

        // Panel chứa tiêu đề và nút back
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(64, 64, 64)); // Màu nền tối
        this.add(topPanel, BorderLayout.NORTH);

        // Tiêu đề "Choose level"
        JLabel chooseLevelLabel = new JLabel("Choose level");
        chooseLevelLabel.setFont(new Font("Arial", Font.BOLD, 24));
        chooseLevelLabel.setForeground(Color.WHITE); // Màu chữ trắng
        chooseLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chooseLevelLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        topPanel.add(chooseLevelLabel, BorderLayout.CENTER);

        // Nút Back
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(220, 220, 220)); // Màu nền xám
        backButton.setForeground(Color.BLACK); // Màu chữ đen
        backButton.setFocusPainted(false); // Loại bỏ viền khi focus
        backButton.addActionListener(e -> {
           game.changeView(Constants.MENU);
        });
        topPanel.add(backButton, BorderLayout.WEST); // Đặt nút Back ở bên trái

        // Panel chứa các nút chọn level
        JPanel container = new JPanel(new GridLayout(0, 6, 10, 10));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        this.add(container, BorderLayout.CENTER);



        List<String> data = Utils.getLevel();
        if (data.isEmpty()) {
            throw new FileException("Not found data level");
        }

        JButton[] buttons = new JButton[data.size()];
        for (int i = 0; i < data.size(); i++) {
            buttons[i] = new JButton(i+""); // Sử dụng tên level thay vì chỉ số i
            buttons[i].setFont(Constants.FONT_BUTTON);
            buttons[i].setBackground(new Color(220, 220, 220)); // Màu nền xám
            buttons[i].setForeground(Color.BLACK); // Màu chữ đen
            buttons[i].setFocusPainted(false); // Loại bỏ viền khi focus
            buttons[i].addActionListener(chooseLevelController);
            buttons[i].setActionCommand(String.valueOf(i));
            container.add(buttons[i]);
        }
    }
}
