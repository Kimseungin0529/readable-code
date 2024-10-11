package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

public class StudyCafeSeatPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }


    public boolean isSamePassType(StudyCafePassType studyCafePassType) {
        return this.passType == studyCafePassType;
    }

    public boolean isDurationType(StudyCafeLockerPass lockerPass) {
        return this.passType == lockerPass.getPassType()
                && this.duration == lockerPass.getDuration();
    }


    public boolean cannotUseLockerPass() {
        return this.passType.isNotLockerType();
    }

    public double getDiscountPrice() {
        return this.price * this.discountRate;
    }
}
