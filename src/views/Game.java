package views;

import exceptions.FileException;
import interfaces.GamePlay;
import interfaces.View;
import lombok.Getter;
import lombok.Setter;
import models.SodokuModel;

import utils.Constants;
import utils.Utils;
import javax.swing.*;
import java.awt.*;
public class Game extends JFrame implements View, GamePlay {
    private SodokuModel sodokuModel;
    private SodokuView sodokuView;
    private OverGameView overGame;
    private MenuView menuView;
    private ChooseLevelView chooseLevel;

    private TimeView timeView;

    private CardLayout cardLayout;
    private JPanel container;
    private boolean end;
    private int statusEnd=0;
    public Game(){
        initView();
        changeView(Constants.MENU);
    }

    public SodokuModel getSodokuModel() {
        return sodokuModel;
    }

    public void setSodokuModel(SodokuModel sodokuModel) {
        this.sodokuModel = sodokuModel;
    }

    public SodokuView getSodokuView() {
        return sodokuView;
    }

    public void setSodokuView(SodokuView sodokuView) {
        this.sodokuView = sodokuView;
    }

    public OverGameView getOverGame() {
        return overGame;
    }

    public void setOverGame(OverGameView overGame) {
        this.overGame = overGame;
    }

    public MenuView getMenuView() {
        return menuView;
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }

    public ChooseLevelView getChooseLevel() {
        return chooseLevel;
    }

    public void setChooseLevel(ChooseLevelView chooseLevel) {
        this.chooseLevel = chooseLevel;
    }



    public TimeView getTimeView() {
        return timeView;
    }

    public void setTimeView(TimeView timeView) {
        this.timeView = timeView;
    }





    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public int getStatusEnd() {
        return statusEnd;
    }

    public void setStatusEnd(int statusEnd) {
        this.statusEnd = statusEnd;
    }

    @Override
    public void initView() {
        setTitle("SODOKU");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        this.add(container);
        initComponent();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private void initComponent(){
        this.sodokuModel=new SodokuModel();
        this.chooseLevel=new ChooseLevelView(this);
        chooseLevel.initView();
        this.menuView=new MenuView(this);
        menuView.initView();
        this.overGame=new OverGameView(this);
        overGame.initView();
        this.timeView =new TimeView(this);
        timeView.initView();
        this.sodokuView=new SodokuView(timeView, this);

        container.add(menuView, Constants.MENU);
        container.add(chooseLevel, Constants.CHOOSE_LEVEL);
        container.add(sodokuView, Constants.SODOKU);
        container.add(overGame, Constants.OVER_GAME);


    }
    @Override
    public void gameStart(){
        sodokuModel.initGame();
        initViewPlay();
    }



    private void initViewPlay() {
        if(sodokuView.isInit()){
            sodokuView.resetData();
        }else
            sodokuView.initView();
        changeView(Constants.SODOKU);
        end=false;
    }



    @Override
    public void gameOver(String message){
        overGame.setMessageLabel(message);
        changeView(Constants.OVER_GAME);
        end=true;
    }


    public void changeView(String view){
        cardLayout.show(container,view);
    }


}
