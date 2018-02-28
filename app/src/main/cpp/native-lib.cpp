#include "native-lib.h"
#include <string>


using namespace std;


JNIEXPORT jstring JNICALL
Java_ir_whiteapp_maremat_Inpainting_test(JNIEnv *env, jclass type, jint a) {

    // TODO


    return env->NewStringUTF("hiiii");
}