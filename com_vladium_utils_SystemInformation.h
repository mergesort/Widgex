/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_vladium_utils_SystemInformation */

#ifndef _Included_com_vladium_utils_SystemInformation
#define _Included_com_vladium_utils_SystemInformation
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_vladium_utils_SystemInformation
 * Method:    getProcessID
 * Signature: ()I
 */
JNIEXPORT jint
JNICALL Java_com_vladium_utils_SystemInformation_getProcessID (JNIEnv *, jclass);

/*
 * Class:     com_vladium_utils_SystemInformation
 * Method:    getProcessCPUTime
 * Signature: ()J
 */
JNIEXPORT jlong
JNICALL Java_com_vladium_utils_SystemInformation_getProcessCPUTime (JNIEnv *, jclass);

/*
 * Class:     com_vladium_utils_SystemInformation
 * Method:    getProcessCPUUsage
 * Signature: ()D
 */
JNIEXPORT jdouble
JNICALL Java_com_vladium_utils_SystemInformation_getProcessCPUUsage (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif
