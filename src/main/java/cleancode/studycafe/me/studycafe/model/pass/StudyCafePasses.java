package cleancode.studycafe.me.studycafe.model.pass;

import java.util.ArrayList;
import java.util.List;

public class StudyCafePasses {
    private final List<StudyCafePass> studyCafePasses;

    private StudyCafePasses(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public static StudyCafePasses of(List<StudyCafePass> studyCafePasses){
        return new StudyCafePasses(studyCafePasses);
    }

    public List<StudyCafePass> getStudyCafePassList(){
        return new ArrayList<>(studyCafePasses);
    }
}
