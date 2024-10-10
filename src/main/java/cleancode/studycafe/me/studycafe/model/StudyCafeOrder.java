package cleancode.studycafe.me.studycafe.model;

import cleancode.studycafe.me.studycafe.model.locker_pass.StudyCafeLockerPass;
import cleancode.studycafe.me.studycafe.model.pass.StudyCafePass;

public class StudyCafeOrder {
    private StudyCafePass studyCafePass;
    private StudyCafeLockerPass studyCafeLockerPass;

    private StudyCafeOrder(StudyCafePass studyCafePass, StudyCafeLockerPass studyCafeLockerPass) {
        this.studyCafePass = studyCafePass;
        this.studyCafeLockerPass = studyCafeLockerPass;
    }
    private StudyCafeOrder(StudyCafePass studyCafePass) {
        this.studyCafePass = studyCafePass;
    }

    public static StudyCafeOrder of(StudyCafePass studyCafePass){
        return new StudyCafeOrder(studyCafePass);
    }
    public static StudyCafeOrder of(StudyCafePass studyCafePass, StudyCafeLockerPass studyCafeLockerPass){
        return new StudyCafeOrder(studyCafePass, studyCafeLockerPass);
    }

    public void addLockerPass(StudyCafeLockerPass lockerPass){
        this.studyCafeLockerPass = lockerPass;
    }

    public StudyCafePass getStudyCafePass() {
        return studyCafePass;
    }

    public StudyCafeLockerPass getStudyCafeLockerPass() {
        return studyCafeLockerPass;
    }
}
