package models;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

public class Node implements Serializable {
    private int row;
    private int col;
    private int zone;
    private int value;
    private boolean protect;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isProtect() {
        return protect;
    }

    public void setProtect(boolean protect) {
        this.protect = protect;
    }

    public Node(int row, int col, int value, boolean protect) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.protect = protect;
        this.zone = calculateZone();
    }
    private int calculateZone(){
        return  (row - 1) / 3 * 3 + (col - 1) / 3 + 1;
    }


}
