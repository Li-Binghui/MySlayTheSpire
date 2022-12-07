package com.megacrit.cardcrawl.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/audio/MainMusic.class */
public class MainMusic {
    private static final Logger logger = LogManager.getLogger(MainMusic.class.getName());
    private Music music;
    public String key;
    private static final String DIR = "audio/music/";
    private static final String TITLE_BGM = "STS_MenuTheme_NewMix_v1.ogg";
    private static final String LEVEL_1_1_BGM = "STS_Level1_NewMix_v1.ogg";
    private static final String LEVEL_1_2_BGM = "STS_Level1-2_v2.ogg";
    private static final String LEVEL_2_1_BGM = "STS_Level2_NewMix_v1.ogg";
    private static final String LEVEL_2_2_BGM = "STS_Level2-2_v2.ogg";
    private static final String LEVEL_3_1_BGM = "STS_Level3_v2.ogg";
    private static final String LEVEL_3_2_BGM = "STS_Level3-2_v2.ogg";
    private static final String LEVEL_4_1_BGM = "STS_Act4_BGM_v2.ogg";
    private static final float SILENCE_TIME = 4.0f;
    private static final float FAST_SILENCE_TIME = 0.25f;
    private float silenceStartVolume;
    private static final float FADE_IN_TIME = 4.0f;
    private static final float FADE_OUT_TIME = 4.0f;
    private float fadeTimer;
    private float fadeOutStartVolume;
    public boolean isSilenced = false;
    private float silenceTimer = 0.0f;
    private float silenceTime = 0.0f;
    public boolean isFadingOut = false;
    public boolean isDone = false;

    public MainMusic(String key) {
        this.fadeTimer = 0.0f;
        this.key = key;
        this.music = getSong(key);
        this.fadeTimer = 4.0f;
        this.music.setLooping(true);
        this.music.play();
        this.music.setVolume(0.0f);
    }

    private Music getSong(String key) {
        char c = 65535;
        switch (key.hashCode()) {
            case -1887678253:
                if (key.equals(Exordium.ID)) {
                    c = 0;
                    break;
                }
                break;
            case 2362719:
                if (key.equals("MENU")) {
                    c = 4;
                    break;
                }
                break;
            case 313705820:
                if (key.equals(TheCity.ID)) {
                    c = 1;
                    break;
                }
                break;
            case 791401920:
                if (key.equals(TheBeyond.ID)) {
                    c = 2;
                    break;
                }
                break;
            case 884969688:
                if (key.equals(TheEnding.ID)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                switch (AbstractDungeon.miscRng.random(1)) {
                    case 0:
                        return newMusic("audio/music/STS_Level1_NewMix_v1.ogg");
                    default:
                        return newMusic("audio/music/STS_Level1-2_v2.ogg");
                }
            case 1:
                switch (AbstractDungeon.miscRng.random(1)) {
                    case 0:
                        return newMusic("audio/music/STS_Level2_NewMix_v1.ogg");
                    default:
                        return newMusic("audio/music/STS_Level2-2_v2.ogg");
                }
            case 2:
                switch (AbstractDungeon.miscRng.random(1)) {
                    case 0:
                        return newMusic("audio/music/STS_Level3_v2.ogg");
                    default:
                        return newMusic("audio/music/STS_Level3-2_v2.ogg");
                }
            case 3:
                return newMusic("audio/music/STS_Act4_BGM_v2.ogg");
            case 4:
                return newMusic("audio/music/STS_MenuTheme_NewMix_v1.ogg");
            default:
                logger.info("NO SUCH MAIN BGM (playing level_1 instead): " + key);
                return newMusic("audio/music/STS_Level1_NewMix_v1.ogg");
        }
    }

    public static Music newMusic(String path) {
        if (Gdx.audio != null) {
            return Gdx.audio.newMusic(Gdx.files.internal(path));
        }
        logger.info("WARNING: Gdx.audio is null so no Music instance can be initialized.");
        return new MockMusic();
    }

    public void updateVolume() {
        if (!this.isFadingOut && !this.isSilenced) {
            this.music.setVolume(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
        }
    }

    public void fadeOut() {
        this.isFadingOut = true;
        this.fadeOutStartVolume = this.music.getVolume();
        this.fadeTimer = 4.0f;
    }

    public void silence() {
        this.isSilenced = true;
        this.silenceTimer = 4.0f;
        this.silenceTime = 4.0f;
        this.silenceStartVolume = this.music.getVolume();
    }

    public void silenceInstantly() {
        this.isSilenced = true;
        this.silenceTimer = 0.25f;
        this.silenceTime = 0.25f;
        this.silenceStartVolume = this.music.getVolume();
    }

    public void unsilence() {
        if (this.isSilenced) {
            logger.info("Unsilencing " + this.key);
            this.isSilenced = false;
            this.fadeTimer = 4.0f;
        }
    }

    public void kill() {
        this.music.dispose();
        this.isDone = true;
    }

    public void update() {
        if (!this.isFadingOut) {
            updateFadeIn();
        } else {
            updateFadeOut();
        }
    }

    private void updateFadeIn() {
        if (!this.isSilenced) {
            this.fadeTimer -= Gdx.graphics.getDeltaTime();
            if (this.fadeTimer < 0.0f) {
                this.fadeTimer = 0.0f;
            }
            if (!Settings.isBackgrounded) {
                this.music.setVolume(Interpolation.fade.apply(0.0f, 1.0f, 1.0f - (this.fadeTimer / 4.0f)) * Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
            } else {
                this.music.setVolume(MathHelper.slowColorLerpSnap(this.music.getVolume(), 0.0f));
            }
        } else {
            this.silenceTimer -= Gdx.graphics.getDeltaTime();
            if (this.silenceTimer < 0.0f) {
                this.silenceTimer = 0.0f;
            }
            if (!Settings.isBackgrounded) {
                this.music.setVolume(Interpolation.fade.apply(this.silenceStartVolume, 0.0f, 1.0f - (this.silenceTimer / this.silenceTime)));
            } else {
                this.music.setVolume(MathHelper.slowColorLerpSnap(this.music.getVolume(), 0.0f));
            }
        }
    }

    private void updateFadeOut() {
        if (!this.isSilenced) {
            this.fadeTimer -= Gdx.graphics.getDeltaTime();
            if (this.fadeTimer < 0.0f) {
                this.fadeTimer = 0.0f;
                this.isDone = true;
                logger.info("Disposing MainMusic: " + this.key);
                this.music.dispose();
                return;
            }
            this.music.setVolume(Interpolation.fade.apply(this.fadeOutStartVolume, 0.0f, 1.0f - (this.fadeTimer / 4.0f)));
            return;
        }
        this.silenceTimer -= Gdx.graphics.getDeltaTime();
        if (this.silenceTimer < 0.0f) {
            this.silenceTimer = 0.0f;
        }
        this.music.setVolume(Interpolation.fade.apply(this.silenceStartVolume, 0.0f, 1.0f - (this.silenceTimer / this.silenceTime)));
    }
}