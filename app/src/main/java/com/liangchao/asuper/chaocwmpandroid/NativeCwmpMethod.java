package com.liangchao.asuper.chaocwmpandroid;

public class NativeCwmpMethod {

    static {
        System.loadLibrary("chaocwmp");
    }

    public static native String getStringHello();


}
