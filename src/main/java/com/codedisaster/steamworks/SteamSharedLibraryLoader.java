package com.codedisaster.steamworks;

import java.io.*;
import java.util.UUID;

/* loaded from: desktop-1.0.jar:com/codedisaster/steamworks/SteamSharedLibraryLoader.class */
class SteamSharedLibraryLoader {
    private static final PLATFORM OS;
    private static final boolean IS_64_BIT;
    private static final String SHARED_LIBRARY_EXTRACT_DIRECTORY = System.getProperty("com.codedisaster.steamworks.SharedLibraryExtractDirectory", "steamworks4j");
    private static final String SHARED_LIBRARY_EXTRACT_PATH = System.getProperty("com.codedisaster.steamworks.SharedLibraryExtractPath", null);
    private static final String SDK_REDISTRIBUTABLE_BIN_PATH = System.getProperty("com.codedisaster.steamworks.SDKRedistributableBinPath", "sdk/redistributable_bin");
    private static final String SDK_LIBRARY_PATH = System.getProperty("com.codedisaster.steamworks.SDKLibraryPath", "sdk/public/steam/lib");
    static final boolean DEBUG = Boolean.parseBoolean(System.getProperty("com.codedisaster.steamworks.Debug", "false"));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: desktop-1.0.jar:com/codedisaster/steamworks/SteamSharedLibraryLoader$PLATFORM.class */
    public enum PLATFORM {
        Windows,
        Linux,
        MacOS
    }

    SteamSharedLibraryLoader() {
    }

    static {
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        if (osName.contains("Windows")) {
            OS = PLATFORM.Windows;
        } else if (osName.contains("Linux")) {
            OS = PLATFORM.Linux;
        } else if (osName.contains("Mac")) {
            OS = PLATFORM.MacOS;
        } else {
            throw new RuntimeException("Unknown host architecture: " + osName + ", " + osArch);
        }
        IS_64_BIT = osArch.equals("amd64") || osArch.equals("x86_64");
    }

    private static String getPlatformLibName(String libName) {
        switch (OS) {
            case Windows:
                return libName + (IS_64_BIT ? "64" : "") + ".dll";
            case Linux:
                return "lib" + libName + ".so";
            case MacOS:
                return "lib" + libName + ".dylib";
            default:
                throw new RuntimeException("Unknown host architecture");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getSdkRedistributableBinPath() {
        File path;
        switch (OS) {
            case Windows:
                path = new File(SDK_REDISTRIBUTABLE_BIN_PATH, IS_64_BIT ? "win64" : "");
                break;
            case Linux:
                path = new File(SDK_REDISTRIBUTABLE_BIN_PATH, "linux64");
                break;
            case MacOS:
                path = new File(SDK_REDISTRIBUTABLE_BIN_PATH, "osx");
                break;
            default:
                return null;
        }
        if (path.exists()) {
            return path.getPath();
        }
        return null;
    }

    static String getSdkLibraryPath() {
        File path;
        switch (OS) {
            case Windows:
                path = new File(SDK_LIBRARY_PATH, IS_64_BIT ? "win64" : "win32");
                break;
            case Linux:
                path = new File(SDK_LIBRARY_PATH, "linux64");
                break;
            case MacOS:
                path = new File(SDK_LIBRARY_PATH, "osx");
                break;
            default:
                return null;
        }
        if (path.exists()) {
            return path.getPath();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadLibrary(String libraryName, String libraryPath) throws SteamException {
        try {
            String librarySystemName = getPlatformLibName(libraryName);
            File librarySystemPath = discoverExtractLocation(SHARED_LIBRARY_EXTRACT_DIRECTORY + "/" + Version.getVersion(), librarySystemName);
            if (libraryPath == null) {
                extractLibrary(librarySystemPath, librarySystemName);
            } else {
                File librarySourcePath = new File(libraryPath, librarySystemName);
                if (OS != PLATFORM.Windows) {
                    extractLibrary(librarySystemPath, librarySourcePath);
                } else {
                    librarySystemPath = librarySourcePath;
                }
            }
            String absolutePath = librarySystemPath.getCanonicalPath();
            System.load(absolutePath);
        } catch (IOException e) {
            throw new SteamException(e);
        }
    }

    private static void extractLibrary(File librarySystemPath, String librarySystemName) throws IOException {
        extractLibrary(librarySystemPath, SteamSharedLibraryLoader.class.getResourceAsStream("/" + librarySystemName));
    }

    private static void extractLibrary(File librarySystemPath, File librarySourcePath) throws IOException {
        extractLibrary(librarySystemPath, new FileInputStream(librarySourcePath));
    }

    private static void extractLibrary(File librarySystemPath, InputStream input) throws IOException {
        try {
            if (input != null) {
                try {
                    FileOutputStream output = new FileOutputStream(librarySystemPath);
                    Throwable th = null;
                    try {
                        byte[] buffer = new byte[4096];
                        while (true) {
                            int length = input.read(buffer);
                            if (length == -1) {
                                break;
                            }
                            output.write(buffer, 0, length);
                        }
                        output.close();
                        if (output != null) {
                            if (0 != 0) {
                                try {
                                    output.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                output.close();
                            }
                        }
                        input.close();
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            if (output != null) {
                                if (th3 != null) {
                                    try {
                                        output.close();
                                    } catch (Throwable th5) {
                                        th3.addSuppressed(th5);
                                    }
                                } else {
                                    output.close();
                                }
                            }
                            throw th4;
                        }
                    }
                } catch (IOException e) {
                    if (!librarySystemPath.exists()) {
                        throw e;
                    }
                    input.close();
                }
            } else {
                throw new IOException("Failed to read input stream for " + librarySystemPath.getCanonicalPath());
            }
        } catch (Throwable th6) {
            input.close();
            throw th6;
        }
    }

    private static File discoverExtractLocation(String folderName, String fileName) throws IOException {
        if (SHARED_LIBRARY_EXTRACT_PATH != null) {
            File path = new File(SHARED_LIBRARY_EXTRACT_PATH, fileName);
            if (canWrite(path)) {
                return path;
            }
        }
        File path2 = new File(System.getProperty("java.io.tmpdir") + "/" + folderName, fileName);
        if (canWrite(path2)) {
            return path2;
        }
        try {
            File file = File.createTempFile(folderName, null);
            if (file.delete()) {
                File path3 = new File(file, fileName);
                if (canWrite(path3)) {
                    return path3;
                }
            }
        } catch (IOException e) {
        }
        File path4 = new File(System.getProperty("user.home") + "/." + folderName, fileName);
        if (canWrite(path4)) {
            return path4;
        }
        File path5 = new File(".tmp/" + folderName, fileName);
        if (canWrite(path5)) {
            return path5;
        }
        throw new IOException("No suitable extraction path found");
    }

    private static boolean canWrite(File file) {
        File folder = file.getParentFile();
        if (file.exists()) {
            if (!file.canWrite() || !canExecute(file)) {
                return false;
            }
        } else if ((!folder.exists() && !folder.mkdirs()) || !folder.isDirectory()) {
            return false;
        }
        File testFile = new File(folder, UUID.randomUUID().toString());
        try {
            new FileOutputStream(testFile).close();
            boolean canExecute = canExecute(testFile);
            testFile.delete();
            return canExecute;
        } catch (IOException e) {
            testFile.delete();
            return false;
        } catch (Throwable th) {
            testFile.delete();
            throw th;
        }
    }

    private static boolean canExecute(File file) {
        try {
            if (file.canExecute()) {
                return true;
            }
            if (file.setExecutable(true)) {
                return file.canExecute();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}