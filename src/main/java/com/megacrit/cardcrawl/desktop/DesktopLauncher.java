package com.megacrit.cardcrawl.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.DisplayConfig;
import com.megacrit.cardcrawl.core.ExceptionHandler;
import com.megacrit.cardcrawl.core.SystemStats;

import java.nio.charset.Charset;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DesktopLauncher {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }

    private static final Logger logger = LogManager.getLogger(DesktopLauncher.class.getName());

    public static void main(String[] arg) {
        logger.info("time: " + System.currentTimeMillis());
        logger.info("version: " + CardCrawlGame.TRUE_VERSION_NUM);
        logger.info("libgdx:  1.9.5");
        logger.info("default_locale: " + Locale.getDefault());
        logger.info("default_charset: " + Charset.defaultCharset());
        logger.info("default_encoding: " + System.getProperty("file.encoding"));
        logger.info("java_version: " + System.getProperty("java.version"));
        logger.info("os_arch: " + System.getProperty("os.arch"));
        logger.info("os_name: " + System.getProperty("os.name"));
        logger.info("os_version: " + System.getProperty("os.version"));
        SystemStats.logMemoryStats();
        SystemStats.logDiskStats();

        try {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.setDisplayModeCallback = new STSDisplayModeCallback();
            config.addIcon("images/ui/icon.png", Files.FileType.Internal);
            config.resizable = false;
            config.title = "My Slay the Spire";
            loadSettings(config);
            logger.info("Launching application...");
            new LwjglApplication((ApplicationListener) new CardCrawlGame(config.preferencesDirectory), config);
        } catch (Exception e) {
            logger.info("Exception occurred while initializing application!");
            ExceptionHandler.handleException(e, logger);
            Gdx.app.exit();
        }
    }

    private static void loadSettings(LwjglApplicationConfiguration config) {
        DisplayConfig displayConf = DisplayConfig.readConfig();

        if (displayConf.getWidth() < 800 || displayConf.getHeight() < 450) {
            logger.info("[ERROR] Display Config set lower than minimum allowed, resetting config.");
            config.width = 1280;
            config.height = 720;
            DisplayConfig.writeDisplayConfigFile(1280, 720, displayConf.getMaxFPS(), displayConf.getIsFullscreen(), displayConf.getWFS(), displayConf.getIsVsync());
        } else {
            config.height = displayConf.getHeight();
            config.width = displayConf.getWidth();
        }

        config.foregroundFPS = displayConf.getMaxFPS();
        config.backgroundFPS = config.foregroundFPS;

        if (displayConf.getIsFullscreen()) {
            logger.info("[FULLSCREEN_MODE]");
            System.setProperty("org.lwjgl.opengl.Display.enableOSXFullscreenModeAPI", "true");
            System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
            config.fullscreen = true;
            config.height = displayConf.getHeight();
            config.width = displayConf.getWidth();
            logger.info("Running the game in: " + config.width + " x " + config.height);
        } else {
            config.fullscreen = false;
            if (displayConf.getWFS() && config.width == (LwjglApplicationConfiguration.getDesktopDisplayMode()).width && config.height == (LwjglApplicationConfiguration.getDesktopDisplayMode()).height) {
                logger.info("[BORDERLESS_FULLSCREEN_MODE]");
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
                config.width = (LwjglApplicationConfiguration.getDesktopDisplayMode()).width;
                config.height = (LwjglApplicationConfiguration.getDesktopDisplayMode()).height;
                logger.info("Running the game in: " + config.width + " x " + config.height);
            } else {
                logger.info("[WINDOWED_MODE]");
            }
        }

        if (config.fullscreen && (displayConf.getWidth() > (LwjglApplicationConfiguration.getDesktopDisplayMode()).width || displayConf.getHeight() > (LwjglApplicationConfiguration.getDesktopDisplayMode()).height)) {
            logger.info("[ERROR] Monitor resolution is lower than config, resetting config.");
            config.width = (LwjglApplicationConfiguration.getDesktopDisplayMode()).width;
            config.height = (LwjglApplicationConfiguration.getDesktopDisplayMode()).height;
            DisplayConfig.writeDisplayConfigFile(config.width, config.height, displayConf.getMaxFPS(), false, false, displayConf.getIsVsync());
        }

        config.vSyncEnabled = displayConf.getIsVsync();
        logger.info("Settings successfully loaded");
    }
}
