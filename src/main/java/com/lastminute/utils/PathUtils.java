package com.lastminute.utils;

public final class PathUtils {

    private PathUtils(){
    }

    public static String normalizePathToWindows(String path) {
        if (OsUtils.isWindows()) {
                return path.substring(1);
        }
        return path;
    }

    public static String fullPathTo(String fileName)
    {
        String pathFile =  PathUtils.class.getClassLoader().getResource(fileName).getPath();
        return PathUtils.normalizePathToWindows(pathFile);
    }

}
