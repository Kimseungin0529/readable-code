package cleancode.studycafe.me.studycafe.io;

import cleancode.studycafe.me.studycafe.model.StudyCafePassType;

import java.util.Arrays;

public enum StudyCafeFilePath {
    PASS_HOURLY_LIST_PATH("시간 단위 파일 경로", StudyCafePassType.HOURLY, "src/main/resources/cleancode/studycafe/pass-list-hourly.csv"),
    PASS_WEEKLY_LIST_PATH("주 단위 파일 경로", StudyCafePassType.WEEKLY, "src/main/resources/cleancode/studycafe/pass-list-weekly.csv"),
    PASS_FIXED_LIST_PATH("고정석 파일 경로", StudyCafePassType.FIXED, "src/main/resources/cleancode/studycafe/pass-list.fixed.csv"),
    ;

    private final String description;
    private final StudyCafePassType studyCafePassType;
    private final String path;

    StudyCafeFilePath(String description, StudyCafePassType studyCafePassType, String path) {
        this.description = description;
        this.studyCafePassType = studyCafePassType;
        this.path = path;
    }

    public static String findPathFrom(StudyCafePassType type){
        StudyCafeFilePath findFilePath = Arrays.stream(values())
                .filter(studyCafeFilePath -> studyCafeFilePath.isEqualStudyCafePassType(type))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("일치하는 type 경로가 없습니다."));
        return findFilePath.getPath();
    }

    private boolean isEqualStudyCafePassType(StudyCafePassType type){
        return this.getStudyCafePassType() == type;
    }

    private String getPath() {
        return path;
    }

    private StudyCafePassType getStudyCafePassType() {
        return studyCafePassType;
    }


}
