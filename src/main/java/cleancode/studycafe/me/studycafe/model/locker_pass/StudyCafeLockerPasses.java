package cleancode.studycafe.me.studycafe.model.locker_pass;

import cleancode.studycafe.me.studycafe.model.pass.StudyCafePass;

import java.util.List;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> studyCafeLockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        this.studyCafeLockerPasses = studyCafeLockerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> studyCafeLockerPasses){
        return new StudyCafeLockerPasses(studyCafeLockerPasses);
    }

    public StudyCafeLockerPass findStudyCafeLockerPassBy(StudyCafePass studyCafePass){
        return studyCafeLockerPasses.stream()
                .filter(lockerPass -> isMatchedPass(studyCafePass, lockerPass))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당 스터티 카페 이용권에 일치하는 락커 이용권이 없습니다."));
    }

    private boolean isMatchedPass(StudyCafePass studyCafePass, StudyCafeLockerPass lockerPass) {
        return lockerPass.getPassType() == studyCafePass.getPassType()
                && lockerPass.getDuration() == studyCafePass.getDuration();
    }

}
