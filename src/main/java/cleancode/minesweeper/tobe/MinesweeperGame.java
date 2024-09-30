package cleancode.minesweeper.tobe;

import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {
    private static final int BOARD_ROW_SIZE = 8;
    private static final int BOARD_COLUM_SIZE = 10;
    private static final String FLAG = "⚑";
    private static final String MINE = "☼";
    private static final String CLOSED_CELL = "□";
    private static final String OPEND_CELL = "■";
    private static final String[][] BOARD = new String[BOARD_ROW_SIZE][BOARD_COLUM_SIZE];
    private static final Integer[][] NEARBY_LAND_MINE_COUNTS = new Integer[BOARD_ROW_SIZE][BOARD_COLUM_SIZE];
    private static final boolean[][] LAND_MINES = new boolean[BOARD_ROW_SIZE][BOARD_COLUM_SIZE];


    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    
    

    public static void main(String[] args) {
        showGameStart();
        Scanner scanner = new Scanner(System.in);
        initializeGame();
        while (true) {
            showCurrentBoard();
            if (doesUserWinTheGame()) {
                System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
                break;
            }
            if (doesUserLoswTheGame()) {
                System.out.println("지뢰를 밟았습니다. GAME OVER!");
                break;
            }
            System.out.println();
            String inputCell = getInputCellFromUser(scanner);
            String userActionInputCell = getUserActionInputCellFromUser(scanner);
            int seletedColIndex = getSeletedColIndex(inputCell);
            int seletedRowIndex = getSeletedRowIndex(inputCell);

            if (doesUserChooseToPlantFlag(userActionInputCell)) {
                BOARD[seletedRowIndex][seletedColIndex] = FLAG;
                checkIfAllGameIsOver();
            } else if (doesUserChooseToOpenCell(userActionInputCell)) {
                if (isLandMineCell(seletedRowIndex, seletedColIndex)) {
                    BOARD[seletedRowIndex][seletedColIndex] = MINE;
                    changeGameStatusToLose();
                    continue;
                } else {
                    openAroundCell(seletedRowIndex, seletedColIndex);
                }
                checkIfAllGameIsOver();
            } else {
                System.out.println("잘못된 번호를 선택하셨습니다.");
            }
        }
    }

    private static void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private static boolean isLandMineCell(int seletedRowIndex, int seletedColIndex) {
        return LAND_MINES[seletedRowIndex][seletedColIndex];
    }

    private static boolean doesUserChooseToOpenCell(String userActionInputCell) {
        return userActionInputCell.equals("1");
    }

    private static boolean doesUserChooseToPlantFlag(String userActionInputCell) {
        return userActionInputCell.equals("2");
    }

    private static int getSeletedRowIndex(String inputCell) {
        char inputCellRow = inputCell.charAt(1);
        return convertRowFrom(inputCellRow);
    }

    private static int getSeletedColIndex(String inputCell) {
        char inputCellCol = inputCell.charAt(0);
        return convertColFrom(inputCellCol);
    }

    private static String getUserActionInputCellFromUser(Scanner scanner) {
        System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
        return scanner.nextLine();
    }

    private static String getInputCellFromUser(Scanner scanner) {
        System.out.println("선택할 좌표를 입력하세요. (예: a1)");
        return scanner.nextLine();
    }

    private static boolean doesUserLoswTheGame() {
        return gameStatus == -1;
    }

    private static boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    private static void checkIfAllGameIsOver() {
        boolean isOpenedAll = isOpenedAllCell();
        if (isOpenedAll) {
            changeGameStatusToWin();
        }
    }

    private static void changeGameStatusToWin() {
        gameStatus = 1;
    }

    private static boolean isOpenedAllCell() {
        boolean isOpenedAllCell = true;
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int col = 0; col < BOARD_COLUM_SIZE; col++) {
                if (BOARD[row][col].equals(CLOSED_CELL)) {
                    isOpenedAllCell = false;
                }
            }
        }
        return isOpenedAllCell;
    }

    private static int convertRowFrom(char inputCellRow) {
        return Character.getNumericValue(inputCellRow) - 1;
    }

    private static int convertColFrom(char inputCellRow) {
        switch (inputCellRow) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            case 'i':
                return 8;
            case 'j':
                return 9;
            default:
                return -1;
        }
    }

    private static void showCurrentBoard() {
        System.out.println("   a b c d e f g h i j");
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            System.out.printf("%d  ", row + 1);
            for (int col = 0; col < BOARD_COLUM_SIZE; col++) {
                System.out.print(BOARD[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void initializeGame() {
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int col = 0; col < BOARD_COLUM_SIZE; col++) {
                BOARD[row][col] = CLOSED_CELL;
            }
        }
        for (int i = 0; i < 10; i++) {
            int col = new Random().nextInt(BOARD_COLUM_SIZE);
            int row = new Random().nextInt(BOARD_ROW_SIZE);
            LAND_MINES[row][col] = true;
        }
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int col = 0; col < BOARD_COLUM_SIZE; col++) {
                int count = 0;
                if (!isLandMineCell(row, col)) {
                    if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row-1, col-1)) {
                        count++;
                    }
                    if (row - 1 >= 0 && isLandMineCell(row-1, col)) {
                        count++;
                    }
                    if (row - 1 >= 0 && col + 1 < BOARD_COLUM_SIZE && isLandMineCell(row-1, col+1)) {
                        count++;
                    }
                    if (col - 1 >= 0 && isLandMineCell(row, col-1)) {
                        count++;
                    }
                    if (col + 1 < BOARD_COLUM_SIZE && isLandMineCell(row, col+1)) {
                        count++;
                    }
                    if (row + 1 < BOARD_ROW_SIZE && col - 1 >= 0 && isLandMineCell(row+1, col-1)) {
                        count++;
                    }
                    if (row + 1 < BOARD_ROW_SIZE && isLandMineCell(row+1, col)) {
                        count++;
                    }
                    if (row + 1 < BOARD_ROW_SIZE && col + 1 < BOARD_COLUM_SIZE && isLandMineCell(row+1, col+1)) {
                        count++;
                    }
                    NEARBY_LAND_MINE_COUNTS[row][col] = count;
                    continue;
                }
                NEARBY_LAND_MINE_COUNTS[row][col] = 0;
            }
        }
    }

    private static void showGameStart() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void openAroundCell(int row, int col) {
        if (row < 0 || row >= BOARD_ROW_SIZE || col < 0 || col >= BOARD_COLUM_SIZE) {
            return;
        }
        if (!BOARD[row][col].equals(CLOSED_CELL)) {
            return;
        }
        if (isLandMineCell(row, col)) {
            return;
        }
        if (isThereMindCellAround(row, col)) {
            BOARD[row][col] = String.valueOf(NEARBY_LAND_MINE_COUNTS[row][col]);
            return;
        } else {
            BOARD[row][col] = OPEND_CELL;
        }
        openAroundCell(row - 1, col - 1);
        openAroundCell(row - 1, col);
        openAroundCell(row - 1, col + 1);
        openAroundCell(row, col - 1);
        openAroundCell(row, col + 1);
        openAroundCell(row + 1, col - 1);
        openAroundCell(row + 1, col);
        openAroundCell(row + 1, col + 1);
    }

    private static boolean isThereMindCellAround(int row, int col) {
        return NEARBY_LAND_MINE_COUNTS[row][col] != 0;
    }

}
