package cleancode.studycafe.tobe.model.pass;

import java.util.List;

public class StudyCafeSeatPasses {
    private final List<StudyCafeSeatPass> passes;

    private StudyCafeSeatPasses(List<StudyCafeSeatPass> studyCafePasses) {
        this.passes = studyCafePasses;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> studyCafePasses){
        return new StudyCafeSeatPasses(studyCafePasses);
    }

    public List<StudyCafeSeatPass> findPassBy(StudyCafePassType studyCafePassType) {
        return passes.stream()
                .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
                .toList();
    }
}
