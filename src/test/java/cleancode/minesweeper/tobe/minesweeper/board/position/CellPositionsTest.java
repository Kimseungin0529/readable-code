package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CellPositionsTest {

    @DisplayName("지정된 개수만큼의 셀 위치를 임의로 추출한다")
    @Test
    void extractRandomPositions() {
        // given
        List<CellPosition> cellPositionList = List.of(
            CellPosition.of(0, 0),
            CellPosition.of(0, 1),
            CellPosition.of(2, 3),
            CellPosition.of(4, 2),
            CellPosition.of(1, 7)
        );

        CellPositions cellPositions = CellPositions.of(cellPositionList);
        int count = 3;

        // when
        List<CellPosition> randomPositions = cellPositions.extractRandomPositions(count);

        // then
        assertThat(randomPositions).hasSize(count);
        assertThat(cellPositionList).containsAll(randomPositions);
    }

    @DisplayName("지정된 셀 위치들을 제외한 셀 위치 목록을 반환한다.")
    @Test
    void subtract() {
        // given
        List<CellPosition> cellPositionList = List.of(
            CellPosition.of(0, 0),
            CellPosition.of(0, 1),
            CellPosition.of(2, 3),
            CellPosition.of(4, 2),
            CellPosition.of(1, 7)
        );

        List<CellPosition> subCellPositionList = cellPositionList.subList(0, 3);
        CellPositions cellPositions = CellPositions.of(cellPositionList);
        int size = cellPositionList.size() - subCellPositionList.size();

        // when
        List<CellPosition> subtractedCellPositions = cellPositions.subtract(subCellPositionList);

        // then
        assertThat(subtractedCellPositions)
            .hasSize(size)
            .doesNotContainAnyElementsOf(subCellPositionList);

    }


}
