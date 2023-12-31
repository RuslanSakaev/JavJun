package org.sakaevrs.les.les5;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientExample1 {
    public static void main(String[] args) {

        try {
            InetAddress address = InetAddress.getLoopbackAddress();
            Socket client = new Socket(address, 1300);
        } catch (UnknownHostException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
