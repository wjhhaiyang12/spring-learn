package com.example.dbtest.javatest;

import java.io.*;

public class FileTest {

    public static void main(String[] args) {
        FileOutputStream fop = null;
        File file;
        String content = "This is the text content";
        try {
            FileWriter writer = new FileWriter("C:\\Users\\lenovo\\Desktop\\实习\\影院关联数据\\test.txt", true);
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
