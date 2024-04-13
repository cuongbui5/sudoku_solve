package views;

import controllers.SodokuController;
import interfaces.View;
import models.Node;
import models.SodokuModel;
import utils.Constants;
import utils.SudokuSolver;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static utils.Constants.FONT_BUTTON;


public class SodokuView extends JPanel implements View {
    private final TimeView timeView;
    private final Game game;
    private JButton[][] buttonNumber = new JButton[Constants.REAL_SIZE+1][Constants.REAL_SIZE+1];
    private boolean init=false;

    public SodokuView(TimeView timeView, Game game) {
        this.timeView = timeView;
        this.game = game;
    }

    public TimeView getTimeView() {
        return timeView;
    }

    public Game getGame() {
        return game;
    }

    public JButton[][] getButtonNumber() {
        return buttonNumber;
    }

    public void setButtonNumber(JButton[][] buttonNumber) {
        this.buttonNumber = buttonNumber;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }
    private SudokuSolver sudokuSolver;

    @Override
    public void initView() {
        SudokuSolver sudokuSolver=new SudokuSolver(game.getSodokuModel(),SodokuView.this);
        SodokuController sodokuController=new SodokuController(this,game, timeView,sudokuSolver);
        setPreferredSize(Constants.DIMENSION_DEFAULT);
        JPanel sodokuContainer=new JPanel(new GridLayout(3, 3,2,2));
        sodokuContainer.setBorder(BorderFactory.createEmptyBorder(3,3, 3, 3));
        JPanel[] block= new JPanel[Constants.REAL_SIZE+1];
        GridLayout gridLayout=new GridLayout(3,3);
        LineBorder lineBorder=new LineBorder(Color.BLACK, 1);
        for (int i = 1; i <= Constants.REAL_SIZE; i++) {
            block[i] = new JPanel(gridLayout);
            block[i].setBorder(lineBorder);
            sodokuContainer.add(block[i]);
        }
        SodokuModel sodokuModel=game.getSodokuModel();
        for(int i = 1; i<= Constants.REAL_SIZE; i++){
            for(int j=1;j<=Constants.REAL_SIZE;j++){
                Node node=sodokuModel.getNodes()[i][j];
                buttonNumber[i][j]=new JButton(node.isProtect()? node.getValue()+"" : "");
                buttonNumber[i][j].setActionCommand(i+""+j);
                buttonNumber[i][j].setFont(Constants.FONT_BOARD_GAME);
                buttonNumber[i][j].addActionListener(sodokuController);
                buttonNumber[i][j].addKeyListener(sodokuController);
                block[node.getZone()].add(buttonNumber[i][j]);
            }
        }

        this.setLayout(new BorderLayout());
        this.add(timeView,BorderLayout.NORTH);
        this.add(sodokuContainer,BorderLayout.CENTER);
        JButton jButton=new JButton("Đầu hàng");
        jButton.setFont(FONT_BUTTON);
        jButton.setPreferredSize(new Dimension(100, 50));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    sudokuSolver.solve();
                    timeView.getTimer().stop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        });
        this.add(jButton,BorderLayout.SOUTH);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                timeView.update();
            }
        });
        init=true;
    }



    public void resetData(){
       SodokuModel sodokuModel= game.getSodokuModel();
        for(int i = 1; i<= Constants.REAL_SIZE; i++){
            for(int j=1;j<=Constants.REAL_SIZE;j++){
                Node node=sodokuModel.getNodes()[i][j];
                buttonNumber[i][j].setText(node.isProtect()? node.getValue()+"" : "");
                buttonNumber[i][j].setForeground(Color.BLACK);
            }
        }
    }


    public void updateView(int row, int col, int value) {
        buttonNumber[row][col].setText("" + value);
        buttonNumber[row][col].setActionCommand(row + "" + col);
        buttonNumber[row][col].setForeground(Constants.COLOR_DEFAULT);

    }




}
