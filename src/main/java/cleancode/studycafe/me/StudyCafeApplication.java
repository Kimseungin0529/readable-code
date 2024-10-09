package cleancode.studycafe.me;

import cleancode.studycafe.me.io.InputHandler;
import cleancode.studycafe.me.io.OutputHandler;
import cleancode.studycafe.me.config.StudyCafeConfig;

public class StudyCafeApplication {

    public static void main(String[] args) {

        StudyCafeConfig config = StudyCafeConfig.of(new InputHandler(), new OutputHandler());
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(config);

        studyCafePassMachine.run();
    }

}
