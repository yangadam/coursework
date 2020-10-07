package com.dedup4.storage.core;

import com.dedup4.storage.core.detection.Dedup;
import com.dedup4.storage.core.util.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) {
        Dedup dedup = new Dedup("C:\\temp\\");

        String fileMetaDataPath = "C:\\temp\\container.info";

        try (BufferedReader br = new BufferedReader(new FileReader(fileMetaDataPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Constants.CONTAINER_ID = new AtomicInteger(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File[] files = new File[4];
        files[0] = new File("C:\\temp\\file\\test.pdf");

        try {
            dedup.operate(files[0]);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        dedup.save();

        try {
            Dedup dedup2 = Dedup.load("C:\\temp\\test\\dedup.ser");
            System.out.println(dedup2 != null ? dedup2.getCapacity() : 0);
            System.out.println(dedup2 != null ? dedup2.getStorePath() : null);
            if (dedup2 != null) {
                dedup2.operate(new File("C:\\temp\\file\\test.pdf"));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
