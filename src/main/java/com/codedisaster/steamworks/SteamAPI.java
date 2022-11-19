package com.codedisaster.steamworks;

import java.io.PrintStream;

public class SteamAPI {
    private static boolean isRunning = false;
    private static boolean isNativeAPILoaded = false;

    public static void loadLibraries() throws SteamException {
        loadLibraries(null);
    }


    public static void loadLibraries(String libraryPath) throws SteamException {
        if (isNativeAPILoaded) {
            return;
        }

        if (libraryPath == null && SteamSharedLibraryLoader.DEBUG) {
            String sdkPath = SteamSharedLibraryLoader.getSdkRedistributableBinPath();
            SteamSharedLibraryLoader.loadLibrary("steam_api", sdkPath);
        } else {
            SteamSharedLibraryLoader.loadLibrary("steam_api", libraryPath);
        }

        SteamSharedLibraryLoader.loadLibrary("steamworks4j", libraryPath);

        isNativeAPILoaded = true;
    }

    public static void skipLoadLibraries() {
        isNativeAPILoaded = true;
    }


    public static boolean restartAppIfNecessary(int appId) throws SteamException {
        if (!isNativeAPILoaded) {
            throw new SteamException("Native libraries not loaded.\nEnsure to call SteamAPI.loadLibraries() first!");
        }

        return nativeRestartAppIfNecessary(appId);
    }


    public static boolean init() throws SteamException {
        if (!isNativeAPILoaded) {
            throw new SteamException("Native libraries not loaded.\nEnsure to call SteamAPI.loadLibraries() first!");
        }

        isRunning = nativeInit();

        return isRunning;
    }

    public static void shutdown() {
        isRunning = false;
        nativeShutdown();
    }


    public static boolean isSteamRunning() {
        return isSteamRunning(false);
    }

    public static boolean isSteamRunning(boolean checkNative) {
        return (isRunning && (!checkNative || isSteamRunningNative()));
    }

    public static void printDebugInfo(PrintStream stream) {
        stream.println("  Steam API initialized: " + isRunning);
        stream.println("  Steam client active: " + isSteamRunning());
    }

    static boolean isIsNativeAPILoaded() {
        return isNativeAPILoaded;
    }

    private static native boolean nativeRestartAppIfNecessary(int paramInt);

    public static native void releaseCurrentThreadMemory();

    private static native boolean nativeInit();

    private static native void nativeShutdown();

    public static native void runCallbacks();

    private static native boolean isSteamRunningNative();
}

