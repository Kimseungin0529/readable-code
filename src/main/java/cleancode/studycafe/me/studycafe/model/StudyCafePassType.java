package cleancode.studycafe.me.studycafe.model;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권", "시간권"),
    WEEKLY("주 단위 이용권", "주권"),
    FIXED("1인 고정석", "주권");

    private final String description;
    private final String timeType;

    StudyCafePassType(String description, String timeType) {
        this.description = description;
        this.timeType = timeType;
    }

    public static boolean isEqualFixedTypeBy(StudyCafePassType studyCafePassType){
        return studyCafePassType == FIXED;
    }

    public String getTimeType() {
        return timeType;
    }
}
