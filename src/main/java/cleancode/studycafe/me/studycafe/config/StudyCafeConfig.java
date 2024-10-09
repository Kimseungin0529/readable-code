package cleancode.studycafe.me.studycafe.config;

import cleancode.studycafe.me.studycafe.io.InputHandler;
import cleancode.studycafe.me.studycafe.io.OutputHandler;
import cleancode.studycafe.me.studycafe.io.StudyCafeFileHandler;

public class StudyCafeConfig {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;

    private StudyCafeConfig(InputHandler inputHandler, OutputHandler outputHandler, StudyCafeFileHandler studyCafeFileHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafeFileHandler = studyCafeFileHandler;
    }

    public static StudyCafeConfig of(InputHandler inputHandler, OutputHandler outputHandler, StudyCafeFileHandler studyCafeFileHandler){
        return new StudyCafeConfig(inputHandler, outputHandler, studyCafeFileHandler);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public StudyCafeFileHandler getStudyCafeFileHandler() {
        return studyCafeFileHandler;
    }
}
