package com.liangchao.asuper.chaocwmpandroid;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class FileCheck {
    //check if the file or directory exits
    public static boolean checkFile (String filePath){
        File file = new File(filePath);
        if(file.exists()) return true;
        return false;
    }
    /**
     * check the cwmp.conf file content
     */
    public static void parseConf(String fileName) throws IOException {
        String str = null;
        File file = new File(fileName);
        FileReader in = new FileReader(file);
        BufferedReader br= new BufferedReader(in);
        CharArrayWriter tempStream = new CharArrayWriter();
        while ((str = br.readLine())!=null){
            Pattern pattern = Pattern.compile("=");
            //Matcher matcher = pattern.matcher(str);
            String [] strs = pattern.split(str);
            for(int i=0;i<strs.length;i++){
                if(i==1) {
                    if(strs[i].equals("NSB_STB")){
                        str=str.replaceAll("NSB_STB","NSB_STB123");
                    }
                }
            }
            tempStream.append(System.getProperty("line.separator"));
            tempStream.write(str);
            System.out.println(str);
        }
        br.close();

        FileWriter out = new FileWriter(file);
        tempStream.writeTo(out);
        out.close();
    }
}
