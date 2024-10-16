package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CellPositionTest {


    @DisplayName("셀의 위치를 나타내는 인덱스는 0보다 크거나 같아야 합니다.")
    @ParameterizedTest
    @CsvSource({
        "0, -1", "-1, 0", "-1, -1"
    })
    void throwExceptionForInvalidCellPosition(int rowIndex, int colIndex) {
        // given

        // when & then
        assertThatThrownBy(() -> CellPosition.of(rowIndex, colIndex))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("올바르지 않은 좌표입니다.");

    }

    @DisplayName("셀의 상대적인 위치를 계산한 결과는 예상된 좌표값이어야 한다")
    @Test
    void calculateRelativePositionCorrectly() {
        // given
        int rowIndex = 1;
        int colIndex = 2;
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);

        int deltaRow = 0;
        int deltaCol = -1;
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        // when
        CellPosition calculatedCellPosition = cellPosition.calculatePositionBy(relativePosition);

        // then
        assertThat(calculatedCellPosition.getRowIndex()).isEqualTo(rowIndex + deltaRow);
        assertThat(calculatedCellPosition.getColIndex()).isEqualTo(colIndex + deltaCol);
    }

    @DisplayName("잘못된 상대적인 위치에 대한 셀을 계산할 수 없습니다.")
    @Test
    void throwExceptionCalculatePositionBy() {
        // given
        int rowIndex = 0;
        int colIndex = 0;
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);

        int deltaRow = 0;
        int deltaCol = -1;
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        // when & then
        assertThatThrownBy(() -> cellPosition.calculatePositionBy(relativePosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("움직일 수 있는 좌표가 아닙니다.");
    }

}
