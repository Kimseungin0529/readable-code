package cleancode.studycafe.me.studycafe.io;

import cleancode.studycafe.me.studycafe.model.locker_pass.StudyCafeLockerPass;
import cleancode.studycafe.me.studycafe.model.StudyCafeOrder;
import cleancode.studycafe.me.studycafe.model.pass.StudyCafePass;
import cleancode.studycafe.me.studycafe.model.pass.StudyCafePasses;

import java.util.List;

public class OutputHandler {

    public void showWelcomeMessage() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }

    public void askPassTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        System.out.println("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    public void showPassListForSelection(StudyCafePasses passes) {
        System.out.println();
        System.out.println("이용권 목록");
        List<StudyCafePass> passList = passes.getStudyCafePassList();

        for (int index = 0; index < passList.size(); index++) {
            StudyCafePass pass = passList.get(index);
            System.out.println(String.format("%s. ", index + 1) + pass.display());
        }
    }

    public void askLockerPass(StudyCafeLockerPass lockerPass) {
        System.out.println();
        String askMessage = String.format(
            "사물함을 이용하시겠습니까? (%s)",
            lockerPass.display()
        );

        System.out.println(askMessage);
        System.out.println("1. 예 | 2. 아니오");
    }

    /*public void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " + selectedPass.display());

        if (isExist(lockerPass)) {
            System.out.println("사물함: " + lockerPass.display());
        }

        double discountRate = selectedPass.getDiscountRate();
        int discountPrice = calculatePrice(selectedPass, discountRate);
        if (isPositivePrice(discountPrice)) {
            System.out.println("이벤트 할인 금액: " + discountPrice + "원");
        }

        int totalPrice = checkFinalPrice(selectedPass, lockerPass, discountPrice);
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }*/

    private int checkFinalPrice(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass, int discountPrice) {
        return selectedPass.getPrice() - discountPrice + (isExist(lockerPass) ? lockerPass.getPrice() : 0);
    }

    private static boolean isPositivePrice(int discountPrice) {
        return discountPrice > 0;
    }

    private int calculatePrice(StudyCafePass selectedPass, double discountRate) {
        return (int) (selectedPass.getPrice() * discountRate);
    }

    public void showPassOrderSummary(StudyCafeOrder studyCafeOrder) {
        StudyCafePass selectedPass = studyCafeOrder.getStudyCafePass();
        StudyCafeLockerPass lockerPass = studyCafeOrder.getStudyCafeLockerPass();

        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " + selectedPass.display());
        if (isExist(lockerPass)) {
            System.out.println("사물함: " + lockerPass.display());
        }

        double discountRate = selectedPass.getDiscountRate();
        int discountPrice = calculatePrice(selectedPass, discountRate);
        if (isPositivePrice(discountPrice)) {
            System.out.println("이벤트 할인 금액: " + discountPrice + "원");
        }
        int totalPrice = checkFinalPrice(selectedPass, lockerPass, discountPrice);
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    private boolean isExist(StudyCafeLockerPass lockerPass) {
        return lockerPass != null;
    }

    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

}
