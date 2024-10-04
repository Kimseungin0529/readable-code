package cleancode.minesweeper.tobe.cell;

public class EmptyCell implements Cell {
    private static final String EMPTY_SIGN = "■";
    private final CellStatus cellState = CellStatus.initialize();

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public boolean hasLandMineCount() {
        return false;
    }

    @Override
    public String getSign() {
        if (cellState.isOpened()) {
            return EMPTY_SIGN;
        }
        if (cellState.isFlagged()) {
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }

    @Override
    public void flag() {
        cellState.isFlagged();
    }

    @Override
    public void open() {
        cellState.open();
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public boolean isOpened() {
        return false;
    }
}
