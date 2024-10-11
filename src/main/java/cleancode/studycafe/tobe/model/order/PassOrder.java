package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

import java.util.Optional;

public class PassOrder {
    private StudyCafeSeatPass studyCafeSeatPass;
    private StudyCafeLockerPass studyCafeLockerPass;

    private PassOrder(StudyCafeSeatPass studyCafeSeatPass, StudyCafeLockerPass studyCafeLockerPass) {
        this.studyCafeSeatPass = studyCafeSeatPass;
        this.studyCafeLockerPass = studyCafeLockerPass;
    }

    public static PassOrder of(StudyCafeSeatPass studyCafeSeatPass, StudyCafeLockerPass studyCafeLockerPass) {
        return new PassOrder(studyCafeSeatPass, studyCafeLockerPass);
    }


    public double getDiscountPrice() {
        return studyCafeSeatPass.getDiscountPrice();
    }

    public int getTotalPrice() {
        int lockerPassPrice = studyCafeLockerPass != null ? studyCafeLockerPass.getPrice() : 0;

        return (int) (studyCafeSeatPass.getPrice() - getDiscountPrice() + lockerPassPrice);
    }

    public StudyCafeSeatPass getSeatPass() {
        return this.studyCafeSeatPass;
    }
    public Optional<StudyCafeLockerPass> getLockerPass() {
        return Optional.ofNullable(this.studyCafeLockerPass);
    }
}
