package cleancode.minesweeper.tobe.cell;

public class CellStatus {
    boolean isFlagged;
    boolean isOpened;

    public CellStatus(boolean isFlagged, boolean isOpened) {
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static CellStatus initialize(){
        return new CellStatus(false, false);
    }

    public boolean isFlagged(){
        return isFlagged;
    }
    public boolean isOpened(){
        return isOpened;
    }

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

}
