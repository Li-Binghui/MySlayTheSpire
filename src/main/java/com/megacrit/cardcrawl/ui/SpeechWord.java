package com.megacrit.cardcrawl.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/ui/SpeechWord.class */
public class SpeechWord {
    private BitmapFont font;
    private DialogWord.WordEffect effect;
    private DialogWord.WordColor wColor;
    public String word;
    public int line;
    private float x;
    private float y;
    private float target_x;
    private float target_y;
    private float offset_x;
    private float offset_y;
    private float timer;
    private Color color;
    private Color targetColor;
    private float scale;
    private float targetScale = 1.0f;
    private static GlyphLayout gl;
    private static final float COLOR_LERP_SPEED = 8.0f;
    private static final float SHAKE_INTERVAL = 0.02f;
    private static final float BUMP_OFFSET = 20.0f * Settings.scale;
    private static final float SHAKE_AMT = 2.0f * Settings.scale;
    private static final float DIALOG_FADE_Y = 50.0f * Settings.scale;
    private static final float WAVY_DIST = 3.0f * Settings.scale;

    public SpeechWord(BitmapFont font, String word, DialogWord.AppearEffect a_effect, DialogWord.WordEffect effect, DialogWord.WordColor wColor, float x, float y, int line) {
        this.line = 0;
        this.timer = 0.0f;
        this.scale = 1.0f;
        if (gl == null) {
            gl = new GlyphLayout();
        }
        this.font = font;
        this.effect = effect;
        this.wColor = wColor;
        this.word = word;
        this.x = x;
        this.y = y;
        this.target_x = x;
        this.target_y = y;
        this.targetColor = getColor();
        this.line = line;
        this.color = new Color(this.targetColor.r, this.targetColor.g, this.targetColor.b, 0.0f);
        if (effect == DialogWord.WordEffect.WAVY) {
            this.timer = MathUtils.random(1.5707964f);
        }
        switch (a_effect) {
            case FADE_IN:
            default:
                return;
            case GROW_IN:
                this.y -= BUMP_OFFSET;
                this.scale = 0.0f;
                return;
            case BUMP_IN:
                this.y -= BUMP_OFFSET;
                return;
        }
    }

    private Color getColor() {
        switch (this.wColor) {
            case RED:
                return new Color(1.0f, 0.2f, 0.3f, 1.0f);
            case GREEN:
                return new Color(0.3f, 1.0f, 0.1f, 1.0f);
            case BLUE:
                return Settings.BLUE_TEXT_COLOR.cpy();
            case GOLD:
                return Settings.GOLD_COLOR.cpy();
            case WHITE:
                return Settings.CREAM_COLOR.cpy();
            default:
                return Color.DARK_GRAY.cpy();
        }
    }

    public void update() {
        if (this.x != this.target_x) {
            this.x = MathUtils.lerp(this.x, this.target_x, Gdx.graphics.getDeltaTime() * 12.0f);
        }
        if (this.y != this.target_y) {
            this.y = MathUtils.lerp(this.y, this.target_y, Gdx.graphics.getDeltaTime() * 12.0f);
        }
        this.color = this.color.lerp(this.targetColor, Gdx.graphics.getDeltaTime() * 8.0f);
        if (this.scale != this.targetScale) {
            this.scale = MathHelper.scaleLerpSnap(this.scale, this.targetScale);
        }
        applyEffects();
    }

    private void applyEffects() {
        switch (this.effect) {
            case SHAKY:
                this.timer -= Gdx.graphics.getDeltaTime();
                if (this.timer < 0.0f) {
                    this.offset_x = MathUtils.random(-SHAKE_AMT, SHAKE_AMT);
                    this.offset_y = MathUtils.random(-SHAKE_AMT, SHAKE_AMT);
                    this.timer = SHAKE_INTERVAL;
                    return;
                }
                return;
            case WAVY:
                this.timer += Gdx.graphics.getDeltaTime() * 5.0f;
                return;
            case SLOW_WAVY:
                this.timer += Gdx.graphics.getDeltaTime() * 2.5f;
                return;
            default:
                return;
        }
    }

    public void fadeOut() {
        this.targetColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void dialogFadeOut() {
        this.targetColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
        this.target_y -= DIALOG_FADE_Y;
    }

    public void shiftY(float shiftAmount) {
        this.target_y += shiftAmount;
    }

    public void shiftX(float shiftAmount) {
        this.target_x += shiftAmount;
    }

    public void setX(float newX) {
        this.target_x = newX;
    }

    public static DialogWord.WordEffect identifyWordEffect(String word) {
        if (word.length() > 2) {
            if (word.charAt(0) == '@' && word.charAt(word.length() - 1) == '@') {
                return DialogWord.WordEffect.SHAKY;
            }
            if (word.charAt(0) == '~' && word.charAt(word.length() - 1) == '~') {
                return DialogWord.WordEffect.WAVY;
            }
        }
        return DialogWord.WordEffect.NONE;
    }

    public static DialogWord.WordColor identifyWordColor(String word) {
        if (word.charAt(0) == '#') {
            switch (word.charAt(1)) {
                case 'b':
                    return DialogWord.WordColor.BLUE;
                case 'g':
                    return DialogWord.WordColor.GREEN;
                case 'r':
                    return DialogWord.WordColor.RED;
                case 'y':
                    return DialogWord.WordColor.GOLD;
            }
        }
        return DialogWord.WordColor.DEFAULT;
    }

    public void render(SpriteBatch sb) {
        char[] charArray;
        char[] charArray2;
        char[] charArray3;
        this.font.setColor(this.color);
        this.font.getData().setScale(this.scale);
        switch (this.effect) {
            case SHAKY:
                float charOffset2 = 0.0f;
                for (char c : this.word.toCharArray()) {
                    String i = Character.toString(c);
                    gl.setText(this.font, i);
                    this.font.draw(sb, i, this.x + (MathUtils.random(-2.0f, 2.0f) * Settings.scale) + charOffset2, this.y + (MathUtils.random(-2.0f, 2.0f) * Settings.scale));
                    charOffset2 += gl.width;
                }
                break;
            case WAVY:
                float charOffset = 0.0f;
                int j = 0;
                for (char c2 : this.word.toCharArray()) {
                    String i2 = Character.toString(c2);
                    gl.setText(this.font, i2);
                    this.font.draw(sb, i2, this.x + this.offset_x + charOffset, this.y + (MathUtils.cosDeg((float) (((System.currentTimeMillis() + (j * 70)) / 4) % 360)) * WAVY_DIST));
                    charOffset += gl.width;
                    j++;
                }
                break;
            case SLOW_WAVY:
                float charOffset3 = 0.0f;
                int j3 = 0;
                for (char c3 : this.word.toCharArray()) {
                    String i3 = Character.toString(c3);
                    gl.setText(this.font, i3);
                    this.font.draw(sb, i3, this.x + this.offset_x + charOffset3, this.y + (MathUtils.cosDeg((float) (((System.currentTimeMillis() + (j3 * 70)) / 4) % 360)) * (WAVY_DIST / 2.0f)));
                    charOffset3 += gl.width;
                    j3++;
                }
                break;
            default:
                this.font.draw(sb, this.word, this.x + this.offset_x, this.y + this.offset_y);
                break;
        }
        this.font.getData().setScale(1.0f);
    }

    public void render(SpriteBatch sb, float y2) {
        this.font.setColor(this.color);
        this.font.getData().setScale(this.scale);
        this.font.draw(sb, this.word, this.x + this.offset_x, this.y + this.offset_y + y2);
        this.font.getData().setScale(1.0f);
    }
}