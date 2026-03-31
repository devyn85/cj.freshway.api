package cjfw.wms.tm.dto;

import lombok.Data;

@Data
public class TmOrderListSummay {
    private String dccode;
    private String deliveryDate;
    private int dlvCnt;
    private int truthcustCnt;
    private int truthcustExceptSelfCnt;
    private int selfCnt;

    public String toString() {
        return "\n---------------------------------------------------\n" +
               "*** 자동배차 대상 배송건과 주문목록의 배송건 불일치 확인용 \n" +
               "---------------------------------------------------\n" +
               " [센터] \t\t: " + dccode + "\n" +
               " [배송일자]]\t: " + deliveryDate + "\n" +
               "---------------------------------------------------\n" +
               " - 배송 주문 수 \t\t\t\t\t: " + dlvCnt + "\n" +
               " - 실착지 기준 주문 수 \t\t\t: " + truthcustCnt + "\n" +
               " - 실착지 기준 주문 수(자차 제외) \t: " + truthcustExceptSelfCnt + "\n" +
               " - 실착지 기준 자차 주문 수 \t\t: " + selfCnt + "\n" +
               "---------------------------------------------------";
    }
}
