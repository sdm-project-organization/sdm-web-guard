package com.mo.guard.util;

import org.springframework.util.ResourceUtils;

import java.io.*;

public class FileUtil {

    public static File loadResource(String filename) throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:" + filename);
    }

    /**
     * readFile
     *
     */
    public static String readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}