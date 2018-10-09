package com.liangchao.asuper.chaocwmpandroid;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ConfFileControl {
    //check if the file or directory exits
    public static boolean checkFile (String filePath){
        File file = new File(filePath);
        if(file.exists()) return true;
        return false;
    }
    /**
     * check the cwmp.conf file content
     */
    public static Map<String,String> getConf(String fileName) throws IOException {
        Map<String,String> mapConf = new HashMap<String, String>();

        String str = null;
        File file = new File(fileName);
        FileReader in = new FileReader(file);
        BufferedReader br= new BufferedReader(in);
        CharArrayWriter tempStream = new CharArrayWriter();
        while ((str = br.readLine())!=null){
            if(str.startsWith("#")) continue;
            if(str.startsWith("[")) continue;
            Pattern pattern = Pattern.compile("=");
            String [] strs = pattern.split(str);
            if(!strs[0].isEmpty())
                mapConf.put(strs[0],strs[1]);
            System.out.println(str);
        }
        br.close();
        return mapConf;
    }

    /**
     * check the cwmp.conf file content
     */
    public static void setConf(String fileName,String key,String valuse) throws IOException {

        String str = null;
        File file = new File(fileName);
        FileReader in = new FileReader(file);
        BufferedReader br= new BufferedReader(in);
        CharArrayWriter tempStream = new CharArrayWriter();
        while ((str = br.readLine())!=null){
            if(str.startsWith("#")) continue;
            if(str.startsWith("[")) continue;
            Pattern pattern = Pattern.compile("=");
            String [] strs = pattern.split(str);

            if(strs[0].equals(key)){
                str=str.replaceAll(strs[1],valuse);
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
