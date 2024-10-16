package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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


}
