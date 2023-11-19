package org.sakaevrs.les.les1;

public class Main {
    public static void main(String[] args) {

//        PlainInterface plainInterface = new PlainInterface() {
//            @Override
//            public String action(int x, int y) {
//                return String.valueOf(x + y);
//            }
//        };
        PlainInterface plainInterface = (x, y) -> String.valueOf(x+y);
        System.out.println(plainInterface.action(5, 3));
    }
}