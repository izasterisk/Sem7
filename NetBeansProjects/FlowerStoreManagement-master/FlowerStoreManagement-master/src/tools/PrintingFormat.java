/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

/**
 *
 * @author Administrator
 */
public class PrintingFormat {
    public static String print4Args(String arg1, String arg2, String arg3, String arg4) {
        String ret = String.format("#%18s#%19s#%19s#%19s#", arg1, arg2, arg3, arg4);
        return ret;
    }

    public static void printLine80() {
        for (int i = 1; i <= 80; i++) {
            System.out.print("#");
        }
        System.out.println("");
    }

    public static String printTotal80(String str) {
        return String.format("#%78s#", str);
    }

    public static String print3Args(String arg1, String arg2, String arg3) {
        String ret = String.format("#%18s#%19s#%19s#", arg1, arg2, arg3);
        return ret;
    }

    public static void printLine60() {
        for (int i = 1; i <= 60; i++) {
            System.out.print("#");
        }
        System.out.println("");
    }

    public static String printTotal60(String str) {
        return String.format("#%58s#", str);
    }

    public static void printLine120() {
        for (int i = 1; i <= 120; i++) {
            System.out.print("#");
        }
        System.out.println("");
    }

    public static String print6Args(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) {
        String ret = String.format("#%18s#%19s#%19s#%19s#%19s#%19s#",
                arg1, arg2, arg3, arg4, arg5, arg6);
        return ret;
    }
    
    public static String printTotal120(String str) {
        return String.format("#%118s#", str);
    }
}
