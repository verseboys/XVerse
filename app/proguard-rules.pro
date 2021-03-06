# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.

#-renamesourcefileattribute SourceFile



#不优化输入的类文件，若使用则日志不会被清除
#-dontoptimize

# 删除系统日志语句
-assumenosideeffects class android.util.Log {
     public static boolean isLoggable(java.lang.String, int);
     public static int v(...);
     public static int i(...);
     public static int w(...);
     public static int d(...);
     public static int e(...);
}
# 删除自定义的日志提示类提示语句 
-assumenosideeffects class com.onemt.sdk.base.framework.utils.LogUtil {
     public static *** v(...);
     public static *** i(...);
     public static *** w(...);
     public static *** d(...);
     public static *** e(...);
}


