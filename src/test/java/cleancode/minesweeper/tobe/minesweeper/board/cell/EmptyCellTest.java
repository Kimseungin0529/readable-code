package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EmptyCellTest {

    @DisplayName("빈 셀은 지뢰를 가지고 있지 않습니다.")
    @Test
    void hasLandMineCount() {
        // given
        Cell emptyCell = new EmptyCell();
        // when & then
        assertThat(emptyCell.hasLandMineCount()).isFalse();
    }

    @DisplayName("빈 셀은 지뢰 셀이 아닙니다.")
    @Test
    void isLandMine() {
        // given
        Cell emptyCell = new EmptyCell();
        // when & then
        assertThat(emptyCell.isLandMine()).isFalse();
    }




}
