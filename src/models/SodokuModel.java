package models;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import utils.Constants;
import utils.Utils;
import java.io.Serializable;
import java.util.Arrays;

@NoArgsConstructor
public class SodokuModel implements Serializable {
    private Node[][] nodes=new Node[Constants.REAL_SIZE+1][Constants.REAL_SIZE+1];
    private Node[][] first=new Node[Constants.REAL_SIZE+1][Constants.REAL_SIZE+1];
    private int type=Constants.EASY;
    private int level=0;
    private int time;
    private int checkFalse=0;

    public Node[][] getFirst() {
        return first;
    }

    public void setFirst(Node[][] first) {
        this.first = first;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public void setNodes(Node[][] nodes) {
        this.nodes = nodes;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCheckFalse() {
        return checkFalse;
    }

    public void setCheckFalse(int checkFalse) {
        this.checkFalse = checkFalse;
    }

    public void initGame(){
        String[] a= Utils.getLevel().get(level).split("");
        for (int i=0;i<Math.pow(type,2);i++){
            double random=Utils.random(Constants.MIN_RANDOM,Constants.MAX_RANDOM);
            a[(int)random]="0";
        }
        int temp=0;
        for(int i=1;i<=Constants.REAL_SIZE;i++){
            for(int j=1;j<=Constants.REAL_SIZE;j++){
                int value=Integer.parseInt(a[temp]);
                nodes[i][j]=new Node(i,j,value, value != 0);
                temp++;
            }
        }
        first=nodes.clone();
        checkFalse=0;
        setTimeByType();

    }



    public void setTimeByType(){
        if(type==Constants.EASY){
            time=Constants.time[0];
        }else if(type==Constants.MEDIUM){
            time=Constants.time[1];
        }else {
            time=Constants.time[2];
        }
    }
    public boolean checkRow(int row,int value) {
        for(int i=1;i<=9;i++){
            if(value==nodes[row][i].getValue()){
                return false;
            }
        }
        return true;
    }


    public boolean checkCol(int col, int value){
        for(int i=1;i<=9;i++){
            if(value==nodes[i][col].getValue()){
                return false;
            }
        }
        return true;
    }

    private static int getStartPos(int x) {
        if(x<=3)
            return 1;
        else if (x<=6)
            return 4;
        else
            return 7;

    }

    public boolean check3x3(int row,int col,int value) {
        int startCol=getStartPos(col);
        int endCol=startCol+2;
        int startRow=getStartPos(row);
        int endRow=startRow+2;
        for(int i=startRow;i<=endRow;i++){
            for(int j=startCol;j<=endCol;j++){
                if(nodes[i][j].getValue()==value)
                    return false;
            }

        }
        return true;
    }


    public boolean validateNode(int row, int col, int value) {
        if (!check3x3(row, col, value) || !checkCol(col, value) || !checkRow(row, value)) {
            return false;
        }
        return true;

    }

    public boolean checkWin(){
        for(int i=1;i<=Constants.REAL_SIZE;i++){
            for(int j=1;j<=Constants.REAL_SIZE;j++){
                if(nodes[i][j].getValue()==0){
                    return false;
                }
            }
        }
        return true;
    }

    public void updateData(int i, int j, int value) {
        nodes[i][j].setValue(value);
        nodes[i][j].setProtect(true);
    }


}
