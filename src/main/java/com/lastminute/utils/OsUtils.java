package com.lastminute.utils;

public final class OsUtils
{
    private OsUtils(){

    }

    private static String OS = null;
    private static final String WINDOWS_OS = "Windows";
    public static String getOsName()
    {
        if(OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }
    public static boolean isWindows()
    {
        return getOsName().startsWith(WINDOWS_OS);
    }

}
