package org.saber.study.thread.t02;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 14:36
 **/
public class FlightSecurityTest {

    static class Passengers extends Thread {
        //机场安检类
        private final FlightSecurity flightSecurity;

        //旅客身份证
        private final String idCard;

        //旅客登机牌
        private final String boardingPass;

        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while (true) {
                flightSecurity.pass(boardingPass, idCard);
            }
        }
    }

    public static void main(String[] arg) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity, "A123456", "AF12345").start();
        new Passengers(flightSecurity, "B123456", "BF12345").start();
        new Passengers(flightSecurity, "C123456", "CF12345").start();
    }
}
