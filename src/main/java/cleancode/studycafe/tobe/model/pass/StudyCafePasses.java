package cleancode.studycafe.tobe.model.pass;

import java.util.List;

public class StudyCafePasses {
    private final List<StudyCafeSeatPass> passes;

    private StudyCafePasses(List<StudyCafeSeatPass> studyCafePasses) {
        this.passes = studyCafePasses;
    }

    public static StudyCafePasses of(List<StudyCafeSeatPass> studyCafePasses){
        return new StudyCafePasses(studyCafePasses);
    }

    public List<StudyCafeSeatPass> findPassBy(StudyCafePassType studyCafePassType) {
        return passes.stream()
                .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
                .toList();
    }
}
