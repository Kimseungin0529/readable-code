package cleancode.minesweeper.tobe.cell;

import cleancode.minesweeper.tobe.position.CellPosition;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cells {
    private final List<Cell> cells;

    private Cells(List<Cell> cells) {
        this.cells = cells;
    }
    public static Cells of(List<Cell> cells){
        return new Cells(cells);
    }
    public static Cells from(Cell[][] cells){
        List<Cell> cellList = Arrays.stream(cells)
                .flatMap(cell -> Arrays.stream(cell))
                .toList();

        return of(cellList);

    }

    public boolean isAllChecked() {
        return this.cells.stream()
                .allMatch(Cell::isChecked);
    }
}
