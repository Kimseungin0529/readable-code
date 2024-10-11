package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.order.PassOrder;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;

import java.util.List;

public class StudyCafeIOHandler {
    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();


    public void showWelcomeMessage() {
        outputHandler.showWelcomeMessage();
    }

    public void showAnnouncement() {
        outputHandler.showAnnouncement();
    }

    public StudyCafePassType askPassTypeSelection() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    public StudyCafeSeatPass askPassSelection(List<StudyCafeSeatPass> passCandidates) {
        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }

    public boolean askLockerPassSelecting(StudyCafeLockerPass lockerPassCandidate) {
        outputHandler.askLockerPass(lockerPassCandidate);
        return inputHandler.getLockerSelection();
    }

    public void showPassOrderSummary(PassOrder passOrder) {
        outputHandler.showPassOrderSummary(passOrder);
    }

    public void showSimpleMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }
}
