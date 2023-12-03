package org.sakaevrs.les.les3;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main2 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        String s = (String) objectInputStream.readObject();
        System.out.println(s);
    }
}