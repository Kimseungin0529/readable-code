package cleancode.minesweeper.tobe;

import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {

    private static String[][] board = new String[8][10];
    private static Integer[][] landMineCounts = new Integer[8][10];
    private static boolean[][] landMines = new boolean[8][10];
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
                board[seletedRowIndex][seletedColIndex] = "⚑";
                checkIfAllGameIsOver();
            } else if (doesUserChooseToOpenCell(userActionInputCell)) {
                if (isLandMineCell(seletedRowIndex, seletedColIndex)) {
                    board[seletedRowIndex][seletedColIndex] = "☼";
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
        return landMines[seletedRowIndex][seletedColIndex];
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
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 10; col++) {
                if (board[row][col].equals("□")) {
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
        for (int row = 0; row < 8; row++) {
            System.out.printf("%d  ", row + 1);
            for (int col = 0; col < 10; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void initializeGame() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 10; col++) {
                board[row][col] = "□";
            }
        }
        for (int i = 0; i < 10; i++) {
            int col = new Random().nextInt(10);
            int row = new Random().nextInt(8);
            landMines[row][col] = true;
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 10; col++) {
                int count = 0;
                if (!isLandMineCell(row, col)) {
                    if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row-1, col-1)) {
                        count++;
                    }
                    if (row - 1 >= 0 && isLandMineCell(row-1, col)) {
                        count++;
                    }
                    if (row - 1 >= 0 && col + 1 < 10 && isLandMineCell(row-1, col+1)) {
                        count++;
                    }
                    if (col - 1 >= 0 && isLandMineCell(row, col-1)) {
                        count++;
                    }
                    if (col + 1 < 10 && isLandMineCell(row, col+1)) {
                        count++;
                    }
                    if (row + 1 < 8 && col - 1 >= 0 && isLandMineCell(row+1, col-1)) {
                        count++;
                    }
                    if (row + 1 < 8 && isLandMineCell(row+1, col)) {
                        count++;
                    }
                    if (row + 1 < 8 && col + 1 < 10 && isLandMineCell(row+1, col+1)) {
                        count++;
                    }
                    landMineCounts[row][col] = count;
                    continue;
                }
                landMineCounts[row][col] = 0;
            }
        }
    }

    private static void showGameStart() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void openAroundCell(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 10) {
            return;
        }
        if (!board[row][col].equals("□")) {
            return;
        }
        if (isLandMineCell(row, col)) {
            return;
        }
        if (landMineCounts[row][col] != 0) {
            board[row][col] = String.valueOf(landMineCounts[row][col]);
            return;
        } else {
            board[row][col] = "■";
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

}
