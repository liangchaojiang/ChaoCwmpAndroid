#include <com_liangchao_asuper_chaocwmpandroid_NativeCwmpMethod.h>
#include "cwmpd/include/cwmpd.h"

#include<android/log.h>
#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)

JNIEXPORT jstring JNICALL Java_com_liangchao_asuper_chaocwmpandroid_NativeCwmpMethod_getStringHello
  (JNIEnv *env, jclass jobj){

    LOGI("info now in native to luanch tr069\n");
    tr069launch();
  return (*env)->NewStringUTF(env,"welcome native method for chaocwmp.");
  }