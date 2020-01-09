package org.saber.study.thread.t02;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 14:32
 **/
public class FlightSecurity {

    private int count = 0;

    private String boardingPass = "null";

    private String idCard = "null";

    public synchronized void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        count++;
        check();
    }

    private void check() {
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException("===Exception===" + toString());
        }
    }

    @Override
    public String toString() {
        return "count=" + count + ",boardingPass=" + boardingPass + ",idCard=" + idCard;
    }
}
