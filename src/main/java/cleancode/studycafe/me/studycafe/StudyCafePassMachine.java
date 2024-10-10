package cleancode.studycafe.me.studycafe;

import cleancode.studycafe.me.studycafe.exception.AppException;
import cleancode.studycafe.me.studycafe.io.InputHandler;
import cleancode.studycafe.me.studycafe.io.OutputHandler;
import cleancode.studycafe.me.studycafe.io.StudyCafeFileHandler;
import cleancode.studycafe.me.studycafe.model.*;
import cleancode.studycafe.me.studycafe.config.StudyCafeConfig;
import cleancode.studycafe.me.studycafe.model.locker_pass.StudyCafeLockerPass;
import cleancode.studycafe.me.studycafe.model.locker_pass.StudyCafeLockerPasses;
import cleancode.studycafe.me.studycafe.model.pass.StudyCafePass;
import cleancode.studycafe.me.studycafe.model.StudyCafePassType;
import cleancode.studycafe.me.studycafe.model.pass.StudyCafePasses;

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
            StudyCafePassType studyCafePassType1 = inputHandler.getPassTypeSelectingUserAction();

            StudyCafePasses passes = studyCafeFileHandler.readStudyCafePasses(studyCafePassType1);
            outputHandler.showPassListForSelection(passes);

            StudyCafePass selectedPass = inputHandler.getSelectPass(passes);
            StudyCafeOrder studyCafeOrder = StudyCafeOrder.of(selectedPass);

            if(StudyCafePassType.isEqualFixedTypeBy(selectedPass.getPassType())){
                StudyCafeLockerPasses lockerPasses = studyCafeFileHandler.readLockerPasses();
                StudyCafeLockerPass lockerPass = lockerPasses.findStudyCafeLockerPassBy(selectedPass);

                outputHandler.askLockerPass(lockerPass);
                boolean lockerSelection = inputHandler.getLockerSelection();
                if(lockerSelection){
                    studyCafeOrder.addLockerPass(lockerPass);
                }
            }
            outputHandler.showPassOrderSummary(studyCafeOrder);


        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
