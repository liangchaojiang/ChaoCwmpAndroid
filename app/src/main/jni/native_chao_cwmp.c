#include <com_liangchao_asuper_chaocwmpandroid_NativeCwmpMethod.h>

JNIEXPORT jstring JNICALL Java_com_liangchao_asuper_chaocwmpandroid_NativeCwmpMethod_getStringHello
  (JNIEnv *env, jclass jobj){

  return (*env)->NewStringUTF(env,"welcome native method for chaocwmp.");
  }