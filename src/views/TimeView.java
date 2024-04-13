package views;

import controllers.ContinueController;
import controllers.TimeController;

import interfaces.View;
import models.SodokuModel;
import utils.Utils;
import javax.swing.*;
import java.awt.*;

public class TimeView extends JPanel implements View {
    private int time;
    private final Game game;
    private JLabel falseJLabel, timeJLabel;
    private Timer timer;
    private static final Font font=new Font(Font.SERIF, Font.PLAIN, 16);
    private static final Dimension dimension=new Dimension(500, 35);

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Game getGame() {
        return game;
    }

    public JLabel getFalseJLabel() {
        return falseJLabel;
    }

    public void setFalseJLabel(JLabel falseJLabel) {
        this.falseJLabel = falseJLabel;
    }

    public JLabel getTimeJLabel() {
        return timeJLabel;
    }

    public void setTimeJLabel(JLabel timeJLabel) {
        this.timeJLabel = timeJLabel;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public TimeView(Game game) {
        this.game = game;
    }

    @Override
    public void initView() {
        ContinueController continueController =new ContinueController(game);
        TimeController timeController=new TimeController(this,game);
        this.setPreferredSize(dimension);
        setLayout(new BorderLayout());
        timeJLabel = new JLabel("Time: " + Utils.convertSecondToMinute(time));
        falseJLabel = new JLabel("False: "+"0/3");
        style(timeJLabel);
        style(falseJLabel);
        JButton save=new JButton("Back");
        save.addActionListener(continueController);
        add(timeJLabel, BorderLayout.CENTER);
        add(falseJLabel, BorderLayout.WEST);
        add(save, BorderLayout.EAST);
        timer=new Timer(1000,timeController);
    }


    public void style(JLabel jLabel) {
        jLabel.setFont(font);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);
    }


    public void updateCheck(int num) {
        falseJLabel.setText("False: "+num+"/3");
    }

    public void updateTime(int time) {
        timeJLabel.setText("Time: " + Utils.convertSecondToMinute(time));
    }

    public void update(){
        SodokuModel sodokuModel=game.getSodokuModel();
        time=sodokuModel.getTime();
        updateCheck(sodokuModel.getCheckFalse());
        updateTime(sodokuModel.getTime());
        timeJLabel.setForeground(Color.BLACK);
        timer.start();
    }





}
