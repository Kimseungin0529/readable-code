package cleancode.studycafe.me.studycafe.model;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권"),
    WEEKLY("주 단위 이용권"),
    FIXED("1인 고정석");

    private final String description;

    StudyCafePassType(String description) {
        this.description = description;
    }

    public static boolean isEqualFixedType(StudyCafePassType studyCafePassType){
        return studyCafePassType == FIXED;
    }

}
