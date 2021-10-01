package com.sytoss.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class MenuUtils {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean lastScanInteger = false;

    public static int scanInt(String text) {
        System.out.println(colors.ANSI_PURPLE + "----- INPUT INT -----");
        System.out.print(text + colors.ANSI_RESET);
        lastScanInteger = true;
        return scanner.nextInt();
    }

    public static int scanInt() {
        System.out.println(colors.ANSI_PURPLE + "----- INPUT INT -----");
        lastScanInteger = true;
        return scanner.nextInt();
    }

    public static Date scanDate() throws ParseException {
        System.out.println(colors.ANSI_PURPLE + "----- INPUT DATE IN FORMAT DD/MM/YYYY-----");
        if (lastScanInteger)
            scanner.nextLine();// java just ignore first Scanner.nextLine() after Scanner.nextInt();
        lastScanInteger = false;
        String date = scanner.nextLine();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.parse(date);
    }

    public static Date scanDate(String text) throws ParseException {
        System.out.println(colors.ANSI_PURPLE + "----- INPUT LINE -----");
        System.out.print(text + colors.ANSI_RESET);
        if (lastScanInteger)
            scanner.nextLine();// java just ignore first Scanner.nextLine() after Scanner.nextInt();
        lastScanInteger = false;
        String date = scanner.nextLine();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        if (date.equals("")) {
            return format.parse("2000/01/01 00:00");
        }
        return format.parse(date);
    }

    public static String scanLine(String text) {
        System.out.println(colors.ANSI_PURPLE + "----- INPUT LINE -----");
        System.out.print(text + colors.ANSI_RESET);
        if (lastScanInteger)
            scanner.nextLine();// java just ignore first Scanner.nextLine() after Scanner.nextInt();
        lastScanInteger = false;
        return scanner.nextLine();
    }

    public static void printMenu(String... menu) {
        System.out.println(colors.ANSI_BLUE + "----- MENU -----");
        for (String menuPoint : menu) {
            System.out.println(menuPoint);
        }
        System.out.print(colors.ANSI_RESET);
    }

    public static void printClassName(String className) {
        System.out.println(colors.ANSI_RED + "----- " + className + " -----" + colors.ANSI_RESET);
    }

    public static void printField(String field, String value) {
        System.out.println(colors.ANSI_GREEN + field + " : " + colors.ANSI_CYAN + value + colors.ANSI_RESET);

    }

    public static void printField(String field, Object value) {
        System.out.println(colors.ANSI_GREEN + field + " : " + colors.ANSI_CYAN + value + colors.ANSI_RESET);
    }

    public static class colors {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";
    }
}