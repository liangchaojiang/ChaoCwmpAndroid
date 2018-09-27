package com.liangchao.asuper.chaocwmpandroid;

import java.io.File;

public class FileCheck {
    public static boolean checkFile (String filePath){
        File file = new File(filePath);
        if(file.exists()) return true;
        return false;
    }
}
