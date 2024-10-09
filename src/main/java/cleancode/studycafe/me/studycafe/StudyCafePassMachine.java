package cleancode.studycafe.me.studycafe;

import cleancode.studycafe.me.studycafe.exception.AppException;
import cleancode.studycafe.me.studycafe.io.InputHandler;
import cleancode.studycafe.me.studycafe.io.OutputHandler;
import cleancode.studycafe.me.studycafe.io.StudyCafeFileHandler;
import cleancode.studycafe.me.studycafe.model.StudyCafeLockerPass;
import cleancode.studycafe.me.studycafe.model.StudyCafePass;
import cleancode.studycafe.me.studycafe.model.StudyCafePassType;
import cleancode.studycafe.me.studycafe.config.StudyCafeConfig;

import java.util.Arrays;
import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;

    public StudyCafePassMachine(StudyCafeConfig config) {
        this.inputHandler = config.getInputHandler();
        this.outputHandler = config.getOutputHandler();
        this.studyCafeFileHandler = config.getStudyCafeFileHandler();
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
            // TODO: 2024-10-09
            /**
             * TYPE에 따라 fileHandler에 접근해 파일에 있는 모든 값을 가져온다 -> List<StudyCafePass> studyCafePasses
             * 굳이 모든 정보를 가져와야 할까? TPYE에 대한 값만 가져오면 되는데 모든 값을 가져오고 필터링하는 것은 비효율적인 방법 아닌가?
             */
            StudyCafePassType studyCafePassType1 = Arrays.stream(StudyCafePassType.values())
                    .filter(type -> type == studyCafePassType)
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다."));
            List<StudyCafePass> passes = studyCafeFileHandler.readStudyCafePasses(studyCafePassType1);
            // TODO: 2024-10-09  
            /**
             *  List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
             *                 List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
             *                     .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
             *                     .toList();
             *  위 과정은 이러한 코드를 대체 가능하게 변경함. 해당하는 StudyCafePassType에 대해서 유형에 맞는 file 정보를 가져온다.
             *  따라서 위 코드까지는 기존 여러 조건문에 중복되는 부분은 제거하여 하나로 처리할 수 있게 함.해당 머신에서는 어떤 Type인지 알 필요가 없다.
             */

            if (studyCafePassType == StudyCafePassType.HOURLY) {
                List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
                List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
                    .toList();

                outputHandler.showPassListForSelection(hourlyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
                List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
                List<StudyCafePass> weeklyPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.WEEKLY)
                    .toList();
                outputHandler.showPassListForSelection(weeklyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.FIXED) {
                List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
                List<StudyCafePass> fixedPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.FIXED)
                    .toList();
                outputHandler.showPassListForSelection(fixedPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

                List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
                StudyCafeLockerPass lockerPass = lockerPasses.stream()
                    .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                            && option.getDuration() == selectedPass.getDuration()
                    )
                    .findFirst()
                    .orElse(null);

                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
