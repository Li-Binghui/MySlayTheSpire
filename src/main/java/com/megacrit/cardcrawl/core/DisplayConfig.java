package com.megacrit.cardcrawl.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/core/DisplayConfig.class */
public class DisplayConfig {
    private static final Logger logger = LogManager.getLogger(DisplayConfig.class.getName());
    private static final String DISPLAY_CONFIG_LOC = "info.displayconfig";
    private static final int DEFAULT_W = 1280;
    private static final int DEFAULT_H = 720;
    private static final int DEFAULT_FPS_LIMIT = 60;
    private static final boolean DEFAULT_FS = false;
    private static final boolean DEFAULT_WFS = false;
    private static final boolean DEFAULT_VSYNC = true;
    private int width;
    private int height;
    private int fps_limit;
    private boolean isFullscreen;
    private boolean wfs;
    private boolean vsync;

    private DisplayConfig(int width, int height, int fps_limit, boolean isFullscreen, boolean wfs, boolean vsync) {
        this.width = width;
        this.height = height;
        this.fps_limit = fps_limit;
        this.isFullscreen = isFullscreen;
        this.wfs = wfs;
        this.vsync = vsync;
    }

    public String toString() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("width", Integer.valueOf(this.width));
        hm.put("height", Integer.valueOf(this.height));
        hm.put("fps_limit", Integer.valueOf(this.fps_limit));
        hm.put("isFullscreen", Boolean.valueOf(this.isFullscreen));
        hm.put("wfs", Boolean.valueOf(this.wfs));
        hm.put("vsync", Boolean.valueOf(this.vsync));
        return hm.toString();
    }

    public static DisplayConfig readConfig() {
        logger.info("Reading info.displayconfig");
        ArrayList<String> configLines = readDisplayConfFile();
        if (configLines.size() < 4) {
            createNewConfig();
            return readConfig();
        } else if (configLines.size() == 5) {
            appendFpsLimit(configLines);
            return readConfig();
        } else {
            try {
                DisplayConfig dc = new DisplayConfig(Integer.parseInt(configLines.get(0).trim()), Integer.parseInt(configLines.get(1).trim()), Integer.parseInt(configLines.get(2).trim()), Boolean.parseBoolean(configLines.get(3).trim()), Boolean.parseBoolean(configLines.get(4).trim()), Boolean.parseBoolean(configLines.get(5).trim()));
                logger.info("DisplayConfig successfully read.");
                return dc;
            } catch (Exception e) {
                logger.info("Failed to parse the info.displayconfig going to recreate it with defaults.");
                createNewConfig();
                return readConfig();
            }
        }
    }

    private static ArrayList<String> readDisplayConfFile() {
        ArrayList<String> configLines = new ArrayList<>();
        Scanner s = null;
        try {
            try {
                s = new Scanner(new File(DISPLAY_CONFIG_LOC));
                while (s.hasNextLine()) {
                    configLines.add(s.nextLine());
                }
                if (s != null) {
                    s.close();
                }
                return configLines;
            } catch (FileNotFoundException e) {
                logger.info("File info.displayconfig not found, creating with defaults.");
                createNewConfig();
                ArrayList<String> readDisplayConfFile = readDisplayConfFile();
                if (s != null) {
                    s.close();
                }
                return readDisplayConfFile;
            }
        } catch (Throwable th) {
            if (s != null) {
                s.close();
            }
            throw th;
        }
    }

    public static void writeDisplayConfigFile(int w, int h, int fps, boolean fs, boolean wfs, boolean vs) {
        PrintWriter writer = null;
        try {
            try {
                writer = new PrintWriter(DISPLAY_CONFIG_LOC, "UTF-8");
                writer.println(Integer.toString(w));
                writer.println(Integer.toString(h));
                writer.println(Integer.toString(fps));
                writer.println(Boolean.toString(fs));
                writer.println(Boolean.toString(wfs));
                writer.println(Boolean.toString(vs));
                if (writer != null) {
                    writer.close();
                }
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                ExceptionHandler.handleException(e, logger);
                if (writer != null) {
                    writer.close();
                }
            }
        } catch (Throwable th) {
            if (writer != null) {
                writer.close();
            }
            throw th;
        }
    }

    private static void createNewConfig() {
        logger.info("Creating new config with default values...");
        writeDisplayConfigFile(1280, DEFAULT_H, 60, false, false, true);
    }

    private static void appendFpsLimit(ArrayList<String> configLines) {
        logger.info("Updating config...");
        try {
            writeDisplayConfigFile(Integer.parseInt(configLines.get(0).trim()), Integer.parseInt(configLines.get(1).trim()), 60, Boolean.parseBoolean(configLines.get(2).trim()), Boolean.parseBoolean(configLines.get(3).trim()), true);
        } catch (Exception e) {
            logger.info("Failed to parse the info.displayconfig going to recreate it with defaults.");
            createNewConfig();
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getMaxFPS() {
        return this.fps_limit;
    }

    public boolean getIsFullscreen() {
        return this.isFullscreen;
    }

    public boolean getWFS() {
        return this.wfs;
    }

    public boolean getIsVsync() {
        return this.vsync;
    }
}