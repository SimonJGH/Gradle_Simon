package com.yds.customize.util;

import android.util.Log;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;

/**
 * @author YDS
 * @date 2020/6/3 0003
 * @discribe simon log
 * <p>
 * 用法： Application初始化 SimonLog.init("Simon",true);
 */
@SuppressWarnings("all")
public class SimonLog {
    private static String LOG_FLAG = "Simon";//Log标识
    private static boolean debug = true;//是否开启bug调试标识
    private final static SimpleDateFormat sdf_simple = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");


    private SimonLog() {
    }

    /**
     * @param logKey Log标识(全局共用，也可在具体调用时单独设置)
     * @param debug  是否开启bug调试
     */
    public static void init(String logFlag, boolean debug) {
        SimonLog.LOG_FLAG = logFlag;
        SimonLog.debug = debug;
    }

    /**
     * log.e
     *
     * @param context
     * @param targetMsg 目标信息
     */
    public static void e(String targetMsg) {
        boolean lambda = false;//判断是否含有 lambda 表达式

        //获取打印所在行数和方法
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        for (StackTraceElement value : s) {
            if (value.getMethodName().startsWith("lambda")) {
                lambda = true;
            }
        }
        String methodName;
        String className;
        if (!lambda) {
            methodName = s[3].getMethodName();
            className = s[3].toString();
        } else {
            methodName = s[5].getMethodName();
            className = s[5].toString();
        }

        if (debug) {
            Log.e("目标类名：" + className.substring(className.indexOf("(")), " \n"
                    + "目标标识：" + LOG_FLAG + "\n"
                    + "目标方法：" + methodName + "\n"
                    + "目标信息：" + targetMsg + "\n");
        }
    }

    /**
     * log.e
     *
     * @param logKey    目标标识
     * @param targetMsg 目标信息
     */
    public static void e(String logKey, String targetMsg) {
        boolean lambda = false;//判断是否含有 lambda 表达式

        //获取打印所在行数和方法
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        for (StackTraceElement value : s) {
            if (value.getMethodName().startsWith("lambda")) {
                lambda = true;
            }
        }
        String methodName;
        String className;
        if (!lambda) {
            methodName = s[3].getMethodName();
            className = s[3].toString();
        } else {
            methodName = s[5].getMethodName();
            className = s[5].toString();
        }

        if (debug) {
            Log.e("目标类名：" + className.substring(className.indexOf("(")), " \n"
                    + "目标标识：" + logKey + "\n"
                    + "目标方法：" + methodName + "\n"
                    + "目标信息：" + targetMsg + "\n");
        }
    }

    /**
     * log.e
     *
     * @param logKey         目标标识
     * @param requestAddress 请求地址
     * @param params         请求参数
     * @param targetMsg      目标信息
     */
    public static void e(String logKey, String requestAddress, Object params, String targetMsg) {
        boolean lambda = false;//判断是否含有 lambda 表达式

        //获取打印所在行数和方法
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        for (StackTraceElement value : s) {
            if (value.getMethodName().startsWith("lambda")) {
                lambda = true;
            }
        }
        String methodName;
        String className;
        if (!lambda) {
            methodName = s[3].getMethodName();
            className = s[3].toString();
        } else {
            methodName = s[5].getMethodName();
            className = s[5].toString();
        }

        if (debug) {
            String tag = "目标类名：" + className.substring(className.indexOf("("));
            String content = " \n"
                    + "目标标识：" + logKey + "\n"
                    + "目标函数：" + methodName + "\n"
                    + "请求时间：" + sdf_simple.format(System.currentTimeMillis()) + "\n"
                    + "请求地址：" + requestAddress + "\n"
                    + "请求参数：" + new Gson().toJson(params) + "\n"
                    + "请求结果：" + decode(formatString(targetMsg)) + "\n";
            Log.e(tag, content);
        }
    }


    /**
     * log.i
     *
     * @param targetMsg 目标信息
     */
    public static void i(String targetMsg) {
        boolean lambda = false;//判断是否含有 lambda 表达式

        //获取打印所在行数和方法
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        for (StackTraceElement value : s) {
            if (value.getMethodName().startsWith("lambda")) {
                lambda = true;
            }
        }
        String methodName;
        String className;
        if (!lambda) {
            methodName = s[3].getMethodName();
            className = s[3].toString();
        } else {
            methodName = s[5].getMethodName();
            className = s[5].toString();
        }

        if (debug) {
            Log.i("目标类名：" + className.substring(className.indexOf("(")), " \n"
                    + "目标标识：" + LOG_FLAG + "\n"
                    + "目标方法：" + methodName + "\n"
                    + "目标信息：" + targetMsg + "\n");
        }
    }

    /**
     * log.i
     *
     * @param logKey    目标标识
     * @param targetMsg 目标信息
     */
    public static void i(String logKey, String targetMsg) {
        boolean lambda = false;//判断是否含有 lambda 表达式

        //获取打印所在行数和方法
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        for (StackTraceElement value : s) {
            if (value.getMethodName().startsWith("lambda")) {
                lambda = true;
            }
        }
        String methodName;
        String className;
        if (!lambda) {
            methodName = s[3].getMethodName();
            className = s[3].toString();
        } else {
            methodName = s[5].getMethodName();
            className = s[5].toString();
        }

        if (debug) {
            Log.i("目标类名：" + className.substring(className.indexOf("(")), " \n"
                    + "目标标识：" + logKey + "\n"
                    + "目标方法：" + methodName + "\n"
                    + "目标信息：" + targetMsg + "\n");
        }
    }


    /**
     * log.i
     *
     * @param logKey         目标标识
     * @param requestAddress 请求地址
     * @param params         请求参数
     * @param targetMsg      目标信息
     */
    public static void i(String logKey, String requestAddress, Object params, String targetMsg) {
        boolean lambda = false;//判断是否含有 lambda 表达式

        //获取打印所在行数和方法
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        for (StackTraceElement value : s) {
            if (value.getMethodName().startsWith("lambda")) {
                lambda = true;
            }
        }
        String methodName;
        String className;
        if (!lambda) {
            methodName = s[3].getMethodName();
            className = s[3].toString();
        } else {
            methodName = s[5].getMethodName();
            className = s[5].toString();
        }

        if (debug) {
            String tag = "目标类名：" + className.substring(className.indexOf("("));
            String content = " \n"
                    + "目标标识：" + logKey + "\n"
                    + "目标函数：" + methodName + "\n"
                    + "请求时间：" + sdf_simple.format(System.currentTimeMillis()) + "\n"
                    + "请求地址：" + requestAddress + "\n"
                    + "请求参数：" + new Gson().toJson(params) + "\n"
                    + "请求结果：" + decode(formatString(targetMsg)) + "\n";
            Log.i(tag, content);
        }
    }

    /**
     * 格式化json串
     *
     * @param text
     * @return
     */
    private static String formatString(String text) {
        StringBuilder json = new StringBuilder();
        String indentString = "";

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            switch (letter) {
                case '{':
                case '[':
                    json.append("\n" + indentString + letter + "\n");
                    indentString = indentString + "\t";
                    json.append(indentString);
                    break;
                case '}':
                case ']':
                    indentString = indentString.replaceFirst("\t", "");
                    json.append("\n" + indentString + letter);
                    break;
                case ',':
                    json.append(letter + "\n" + indentString);
                    break;

                default:
                    json.append(letter);
                    break;
            }
        }

        return json.toString();
    }

    /**
     * unicode转中文
     *
     * @param unicodeStr
     * @return
     */
    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

}
