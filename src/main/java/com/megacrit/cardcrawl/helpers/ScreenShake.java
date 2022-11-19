package com.megacrit.cardcrawl.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.megacrit.cardcrawl.core.Settings;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/helpers/ScreenShake.class */
public class ScreenShake {
    private float x = 0.0f;
    private float duration = 0.0f;
    private float startDuration = 0.0f;
    private float intensityValue;
    private float intervalSpeed;
    private boolean vertical;

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/helpers/ScreenShake$ShakeDur.class */
    public enum ShakeDur {
        SHORT,
        MED,
        LONG,
        XLONG
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/helpers/ScreenShake$ShakeIntensity.class */
    public enum ShakeIntensity {
        LOW,
        MED,
        HIGH
    }

    public void shake(ShakeIntensity intensity, ShakeDur dur, boolean isVertical) {
        this.duration = getDuration(dur);
        this.startDuration = this.duration;
        this.intensityValue = getIntensity(intensity);
        this.vertical = isVertical;
        this.intervalSpeed = 0.3f;
    }

    public void rumble(float dur) {
        this.duration = dur;
        this.startDuration = dur;
        this.intensityValue = 10.0f;
        this.vertical = false;
        this.intervalSpeed = 0.7f;
    }

    public void mildRumble(float dur) {
        this.duration = dur;
        this.startDuration = dur;
        this.intensityValue = 3.0f;
        this.vertical = false;
        this.intervalSpeed = 0.7f;
    }

    public void update(FitViewport viewport) {
        if (Settings.HORIZ_LETTERBOX_AMT == 0 && Settings.VERT_LETTERBOX_AMT == 0 && this.duration != 0.0f) {
            this.duration -= Gdx.graphics.getDeltaTime();
            if (this.duration < 0.0f) {
                this.duration = 0.0f;
                viewport.update(Settings.M_W, Settings.M_H);
                return;
            }
            float tmp = Interpolation.fade.apply(0.1f, this.intensityValue, this.duration / this.startDuration);
            this.x = MathUtils.cosDeg(((float) (System.currentTimeMillis() % 360)) / this.intervalSpeed) * tmp;
            if (!Settings.SCREEN_SHAKE) {
                return;
            }
            if (this.vertical) {
                viewport.update(Settings.M_W, (int) (Settings.M_H + Math.abs(this.x)));
            } else {
                viewport.update((int) (Settings.M_W + this.x), Settings.M_H);
            }
        }
    }

    private float getIntensity(ShakeIntensity intensity) {
        switch (intensity) {
            case LOW:
                return 20.0f * Settings.scale;
            case MED:
                return 50.0f * Settings.scale;
            default:
                return 100.0f * Settings.scale;
        }
    }

    private float getDuration(ShakeDur dur) {
        switch (dur) {
            case SHORT:
                return 0.3f;
            case MED:
                return 0.5f;
            case LONG:
                return 1.0f;
            case XLONG:
                return 3.0f;
            default:
                return 1.0f;
        }
    }
}