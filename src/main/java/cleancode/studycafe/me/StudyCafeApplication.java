package cleancode.studycafe.me;

import cleancode.studycafe.me.studycafe.StudyCafePassMachine;
import cleancode.studycafe.me.studycafe.io.InputHandler;
import cleancode.studycafe.me.studycafe.io.OutputHandler;
import cleancode.studycafe.me.studycafe.config.StudyCafeConfig;

public class StudyCafeApplication {

    public static void main(String[] args) {

        StudyCafeConfig config = StudyCafeConfig.of(new InputHandler(), new OutputHandler());
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(config);

        studyCafePassMachine.run();
    }

}
