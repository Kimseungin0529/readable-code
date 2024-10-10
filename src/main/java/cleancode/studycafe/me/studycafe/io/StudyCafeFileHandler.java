package cleancode.studycafe.me.studycafe.io;

import cleancode.studycafe.me.studycafe.model.locker_pass.StudyCafeLockerPass;
import cleancode.studycafe.me.studycafe.model.locker_pass.StudyCafeLockerPasses;
import cleancode.studycafe.me.studycafe.model.pass.StudyCafePass;
import cleancode.studycafe.me.studycafe.model.StudyCafePassType;
import cleancode.studycafe.me.studycafe.model.pass.StudyCafePasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler {

    /*private final static String PASS_HOURLY_LIST_PATH = "src/main/resources/cleancode/studycafe/pass-list-hourly.csv";
    private final static String PASS_WEEKLY_LIST_PATH = "src/main/resources/cleancode/studycafe/pass-list-weekly.csv";
    private final static String PASS_FIXED_LIST_PATH = "src/main/resources/cleancode/studycafe/pass-list.fixed.csv";*/

    public StudyCafePasses readStudyCafePasses(StudyCafePassType studyCafePassType1) {
        try {
            String findPath = StudyCafeFilePath.findPathFrom(studyCafePassType1);
            List<String> lines = Files.readAllLines(Paths.get(findPath));
            List<StudyCafePass> studyCafePasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
                studyCafePasses.add(studyCafePass);
            }

            return StudyCafePasses.of(studyCafePasses);
            /**
             * 리스트 자체 반환보다는 일급컬렉션으로 넘기는게 좋아 보임. 나중에 요구사항에 따라 해당 컬렉션에 로직 처리가 되므로 캡슐화 가능해 보임
             */
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }


    public StudyCafeLockerPasses readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/locker.csv"));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return StudyCafeLockerPasses.of(lockerPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

}
