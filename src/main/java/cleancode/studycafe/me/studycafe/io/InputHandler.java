package cleancode.studycafe.me.studycafe.io;

import cleancode.studycafe.me.studycafe.model.pass.StudyCafePass;
import cleancode.studycafe.me.studycafe.model.StudyCafePassType;
import cleancode.studycafe.me.studycafe.exception.AppException;
import cleancode.studycafe.me.studycafe.model.pass.StudyCafePasses;

import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    public StudyCafePass getSelectPass(StudyCafePasses passes) {
        String userInput = SCANNER.nextLine();
        List<StudyCafePass> passList = passes.getStudyCafePassList();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passList.get(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
