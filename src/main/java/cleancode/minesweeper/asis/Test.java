package cleancode.minesweeper.asis;

public class Test {
    /**
     *  주문에 대한 검증 로직
     *  1. 주문 안에 상품이 존재하는가? 없다면 잘못된 주문으로 판별
     *  2. 상품의 가격이 1원 이상이라면 정상 처리 / 아니라면 잘못된 주문으로 판별
     *  3. 주문에 대한 사용자 정보가 있어야 한다. 없다면 잘못된 로직이다.
     */

    /**
     * 적용할 수 있는 읽기 좋은 코드 방식
     * 1. 사고의 depth 줄이기
     * 2. 부정구 지양하기
     * 3. 최대한 추상적으로 접근하기 ( getter로 직접 접근하기 X)
     * 4. early return 사용하기
     */

    /**
     * 위 내용을 모두 적용하려고 했으니
     * 객체에 대한 내용이 부족하므로 일단 사고의 depth를 줄이기 위해 부정구를 사용하긴 했다.
     * 각 조건을 모두 통과해야 유효한 주문이라는 결과를 도출해야 한다.
     * if 조건 안에 && 처리를 하려 했지만 조건이 길어지면 오히려 기억해야 할 정보가 많을 거 같아 이와 같이
     * 리팩토링 했다.
     */
    /*public boolean validateOrder(Order order) {
        if(notExistItemsFrom(order)){ // 주문에 상품 목록이 존재하면
            log.info("주문 항목이 없습니다.");
            return false;
        }

        if (isNotVaildTotalPrice(order)) { // 총액이 유효한 값인지?
            log.info("올바르지 않은 총 가격입니다.");
            return false;
        }

        if (hasNotCustomerInfoFrom(order)) { //
            log.info("사용자 정보가 없습니다.");
            return false;
        }

        log.info("주문 항목이 없습니다.");
        return true;
    }

    private static boolean hasNotCustomerInfoFrom(Order order) {
        return !order.hasCustomerInfo();
    }

    private static boolean isNotVaildTotalPrice(Order order) {
        return order.getTotalPrice() <= 0;
    }

    private static boolean notExistItemsFrom(Order order) {
        return order.getItems().size() == 0;
    }*/
}
