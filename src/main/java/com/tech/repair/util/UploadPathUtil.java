package com.tech.repair.util;

import java.io.File;

public class UploadPathUtil {
    public static String getAbsolutePath(String path)
    {
        String absolutePath = File.separator.equals("\\") ? path.replaceAll("/", "\\\\") : path;
        return absolutePath;
    }
}
