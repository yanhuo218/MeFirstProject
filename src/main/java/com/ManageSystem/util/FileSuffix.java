package com.ManageSystem.util;

public class FileSuffix {
    public static String FileSuffix(String filename) {
        StringBuilder Suffix = new StringBuilder();
        for (int i = filename.lastIndexOf("."); i < filename.length(); i++) {
            Suffix.append(filename.charAt(i));
        }
        return Suffix.toString();
    }
}
