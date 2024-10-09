package cleancode.studycafe.me.config;

import cleancode.studycafe.me.io.InputHandler;
import cleancode.studycafe.me.io.OutputHandler;

public class StudyCafeConfig {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    private StudyCafeConfig(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public static StudyCafeConfig of(InputHandler inputHandler, OutputHandler outputHandler){
        return new StudyCafeConfig(inputHandler, outputHandler);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }
}
