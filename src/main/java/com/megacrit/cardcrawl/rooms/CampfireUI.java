package com.megacrit.cardcrawl.rooms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
import com.megacrit.cardcrawl.ui.buttons.ConfirmButton;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.RecallOption;
import com.megacrit.cardcrawl.ui.campfire.RestOption;
import com.megacrit.cardcrawl.ui.campfire.SmithOption;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.campfire.CampfireBubbleEffect;
import com.megacrit.cardcrawl.vfx.campfire.CampfireBurningEffect;
import com.megacrit.cardcrawl.vfx.campfire.CampfireEndingBurningEffect;

import java.util.ArrayList;
import java.util.Iterator;


public class CampfireUI
        implements ScrollBarListener {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CampfireUI");
    public static final String[] TEXT = uiStrings.TEXT;

    public static boolean hidden = false;

    public boolean somethingSelected = false;
    private float hideStuffTimer = 0.5F;
    private float charAnimTimer = 2.0F;
    private ArrayList<AbstractCampfireOption> buttons = new ArrayList<>();
    private ArrayList<CampfireBubbleEffect> bubbles = new ArrayList<>();
    private float fireTimer = 0.0F;
    private static final float FIRE_INTERVAL = 0.05F;
    private ArrayList<AbstractGameEffect> flameEffect = new ArrayList<>();
    private int bubbleAmt;
    private String bubbleMsg;
    private BobEffect effect = new BobEffect(2.0F);

    private static final float BUTTON_START_X = Settings.WIDTH * 0.416F;
    private static final float BUTTON_SPACING_X = 300.0F * Settings.xScale;
    private static final float BUTTON_START_Y = Settings.HEIGHT / 2.0F + 180.0F * Settings.scale;
    private static final float BUTTON_SPACING_Y = -200.0F * Settings.scale;
    private static final float BUTTON_EXTRA_SPACING_Y = -70.0F * Settings.scale;

    private static final int MAX_BUTTONS_BEFORE_SCROLL = 6;

    private static final float START_Y = Settings.HEIGHT - 300.0F * Settings.scale;
    private boolean grabbedScreen = false;
    private float grabStartY = 0.0F;
    private float scrollY;
    private float targetY = this.scrollY = START_Y;
    private float scrollLowerBound = Settings.HEIGHT - 300.0F * Settings.scale;
    private float scrollUpperBound = 2400.0F * Settings.scale;

    private ScrollBar scrollBar;

    public ConfirmButton confirmButton = new ConfirmButton();
    public AbstractCampfireOption touchOption = null;

    public CampfireUI() {
        this.scrollBar = new ScrollBar(this);

        hidden = false;
        initializeButtons();

        if (this.buttons.size() > 2) {
            this.bubbleAmt = 60;
        } else {
            this.bubbleAmt = 40;
        }

        this.bubbleMsg = getCampMessage();
    }


    private void initializeButtons() {
        this.buttons.add(new RestOption(true));
        this.buttons.add(new SmithOption((AbstractDungeon.player.masterDeck

                .getUpgradableCards().size() > 0 && !ModHelper.isModEnabled("Midas"))));

        for (AbstractRelic r : AbstractDungeon.player.relics) {
            r.addCampfireOption(this.buttons);
        }


        for (AbstractCampfireOption co : this.buttons) {
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (!r.canUseCampfireOption(co)) {
                    co.usable = false;
                }
            }
        }


        if (Settings.isFinalActAvailable && !Settings.hasRubyKey) {
            this.buttons.add(new RecallOption());
        }


        boolean cannotProceed = true;
        for (AbstractCampfireOption opt : this.buttons) {
            if (opt.usable) {
                cannotProceed = false;

                break;
            }
        }
        if (cannotProceed) {
            AbstractRoom.waitTimer = 0.0F;
            (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
        }
    }

    public void update() {
        updateCharacterPosition();
        updateTouchscreen();
        updateControllerInput();
        if (!this.scrollBar.update()) {
            updateScrolling();
        }

        this.effect.update();
        if (!hidden) {
            updateBubbles();
            updateFire();
            for (AbstractCampfireOption o : this.buttons) {
                o.update();
            }
        }

        if (this.somethingSelected) {
            this.hideStuffTimer -= Gdx.graphics.getDeltaTime();
            if (this.hideStuffTimer < 0.0F) {
                hidden = true;
            }
        }
    }

    private void updateTouchscreen() {
        if (!Settings.isTouchScreen) {
            return;
        }

        this.confirmButton.update();


        if (this.confirmButton.hb.clicked && this.touchOption != null) {
            this.confirmButton.hb.clicked = false;
            this.confirmButton.hb.clickStarted = false;
            this.confirmButton.isDisabled = true;
            this.confirmButton.hide();

            this.touchOption.useOption();
            this.somethingSelected = true;
            this.touchOption = null;

        } else if (InputHelper.justReleasedClickLeft && this.touchOption != null) {
            this.touchOption = null;
            this.confirmButton.isDisabled = true;
            this.confirmButton.hide();
        }
    }

    private void updateControllerInput() {
        if (!Settings.isControllerMode || AbstractDungeon.player.viewingRelics || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || this.somethingSelected || this.buttons

                .isEmpty()) {
            return;
        }

        boolean anyHovered = false;
        int index = 0;
        for (AbstractCampfireOption o : this.buttons) {
            if (o.hb.hovered) {
                anyHovered = true;
                break;
            }
            index++;
        }

        if (!anyHovered) {
            CInputHelper.setCursor(((AbstractCampfireOption) this.buttons.get(0)).hb);
        } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
            index--;
            if (index < 0) {
                if (this.buttons.size() == 2) {
                    index = 1;
                } else {
                    index = 0;
                }
            } else if (index == 1) {
                if (this.buttons.size() == 4) {
                    index = 3;
                } else {
                    index = 2;
                }
            }
            CInputHelper.setCursor(((AbstractCampfireOption) this.buttons.get(index)).hb);
        } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
            index++;
            if (index > this.buttons.size() - 1) {
                if (this.buttons.size() == 2) {
                    index = 0;
                } else if (index == 3) {
                    index = 2;
                } else {
                    index = 0;
                }
            }
            CInputHelper.setCursor(((AbstractCampfireOption) this.buttons.get(index)).hb);
        } else if ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && this.buttons
                .size() > 2) {
            if (this.buttons.size() == 5) {
                index -= 2;
                if (index < 0) {
                    index = 4;
                }
            } else if (this.buttons.size() == 3) {
                if (index == 0) {
                    index = 2;
                } else if (index == 2) {
                    index = 0;
                }

            } else if (index == 0) {
                index = 2;
            } else if (index == 2) {
                index = 0;
            } else if (index == 3) {
                index = 1;
            } else {
                index = 3;
            }

            CInputHelper.setCursor(((AbstractCampfireOption) this.buttons.get(index)).hb);
        } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && this.buttons
                .size() > 2) {
            if (this.buttons.size() == 5) {
                if (index == 4) {
                    index = 0;
                } else if (index > 2) {
                    index = 4;
                } else {
                    index += 2;
                }
            } else if (this.buttons.size() == 4) {
                if (index >= 2) {
                    index -= 2;
                } else {
                    index += 2;
                }

            } else if (index == 0 || index == 1) {
                index = 2;
            } else {
                index = 0;
            }

            CInputHelper.setCursor(((AbstractCampfireOption) this.buttons.get(index)).hb);
        }
    }


    private void updateScrolling() {
        int y = InputHelper.mY;

        if (!this.grabbedScreen) {
            if (InputHelper.scrolledDown) {
                this.targetY += Settings.SCROLL_SPEED;
            } else if (InputHelper.scrolledUp) {
                this.targetY -= Settings.SCROLL_SPEED;
            }

            if (InputHelper.justClickedLeft) {
                this.grabbedScreen = true;
                this.grabStartY = y - this.targetY;
            }

        } else if (InputHelper.isMouseDown) {
            this.targetY = y - this.grabStartY;
        } else {
            this.grabbedScreen = false;
        }


        this.scrollY = MathHelper.scrollSnapLerpSpeed(this.scrollY, this.targetY);
        resetScrolling();
        updateBarPosition();
    }

    private void resetScrolling() {
        if (this.targetY < this.scrollLowerBound) {
            this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollLowerBound);
        } else if (this.targetY > this.scrollUpperBound) {
            this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollUpperBound);
        }
    }

    private void updateCharacterPosition() {
        this.charAnimTimer -= Gdx.graphics.getDeltaTime();
        if (this.charAnimTimer < 0.0F) {
            this.charAnimTimer = 0.0F;
        }
        AbstractDungeon.player.animX = Interpolation.exp10In.apply(0.0F, -300.0F * Settings.scale, this.charAnimTimer / 2.0F);
    }

    private void updateBubbles() {
        if (this.bubbles.size() < this.bubbleAmt) {
            int s = this.bubbleAmt - this.bubbles.size();
            for (int j = 0; j < s; j++) {
                this.bubbles.add(new CampfireBubbleEffect((this.bubbleAmt == 60)));
            }
        }

        for (Iterator<CampfireBubbleEffect> i = this.bubbles.iterator(); i.hasNext(); ) {
            CampfireBubbleEffect bubble = i.next();
            bubble.update();
            if (bubble.isDone) {
                i.remove();
            }
        }
    }

    private void updateFire() {
        this.fireTimer -= Gdx.graphics.getDeltaTime();
        if (this.fireTimer < 0.0F) {
            this.fireTimer = 0.05F;
            if (AbstractDungeon.id.equals("TheEnding")) {
                this.flameEffect.add(new CampfireEndingBurningEffect());
                this.flameEffect.add(new CampfireEndingBurningEffect());
                this.flameEffect.add(new CampfireEndingBurningEffect());
                this.flameEffect.add(new CampfireEndingBurningEffect());
            } else {
                this.flameEffect.add(new CampfireBurningEffect());
                this.flameEffect.add(new CampfireBurningEffect());
            }
        }

        for (Iterator<AbstractGameEffect> i = this.flameEffect.iterator(); i.hasNext(); ) {
            AbstractGameEffect fires = i.next();
            fires.update();
            if (fires.isDone) {
                i.remove();
            }
        }
    }


    public void reopen() {
        hidden = false;
        this.hideStuffTimer = 0.5F;
        this.somethingSelected = false;
    }

    public void render(SpriteBatch sb) {
        if (!hidden) {
            renderFire(sb);
            AbstractDungeon.player.render(sb);

            for (CampfireBubbleEffect e : this.bubbles) {
                e.render(sb, 950.0F * Settings.xScale, Settings.HEIGHT / 2.0F + 60.0F * Settings.yScale + this.effect.y / 4.0F);
            }

            FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.bubbleMsg, 950.0F * Settings.xScale, Settings.HEIGHT / 2.0F + 310.0F * Settings.scale + this.effect.y / 3.0F, Settings.CREAM_COLOR, 1.2F);


            renderCampfireButtons(sb);

            if (shouldShowScrollBar()) {
                this.scrollBar.render(sb);
            }

            if (Settings.isTouchScreen) {
                this.confirmButton.render(sb);
            }
        }
    }

    private String getCampMessage() {
        ArrayList<String> msgs = new ArrayList<>();
        msgs.add(TEXT[0]);
        msgs.add(TEXT[1]);
        msgs.add(TEXT[2]);
        msgs.add(TEXT[3]);

        if (this.buttons.size() > 2) {
            msgs.add(TEXT[4]);
        }

        if (AbstractDungeon.player.currentHealth < AbstractDungeon.player.maxHealth / 2) {
            msgs.add(TEXT[5]);
            msgs.add(TEXT[6]);
        }

        return msgs.get(MathUtils.random(msgs.size() - 1));
    }


    private void renderFire(SpriteBatch sb) {
        for (AbstractGameEffect e : this.flameEffect) {
            e.render(sb);
        }
    }

    private void renderCampfireButtons(SpriteBatch sb) {
        float buttonX = 0.0F;
        float buttonY = 0.0F;

        int maxPossibleStartingIndex = this.buttons.size() + 1 - 6;
        int indexToStartAt = Math.max(
                Math.min(
                        (int) ((maxPossibleStartingIndex + 1) * MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollY)), maxPossibleStartingIndex), 0);


        indexToStartAt = MathUtils.ceil((indexToStartAt / 2)) * 2;

        for (AbstractCampfireOption co : this.buttons) {
            if (this.buttons.indexOf(co) >= indexToStartAt && this.buttons.indexOf(co) < indexToStartAt + 6) {


                if (this.buttons.indexOf(co) == this.buttons.size() - 1 && (this.buttons.size() - indexToStartAt) % 2 == 1) {

                    buttonX = BUTTON_START_X + BUTTON_SPACING_X / 2.0F;
                } else if ((this.buttons.indexOf(co) - indexToStartAt) % 2 == 0) {

                    buttonX = BUTTON_START_X;
                } else {

                    buttonX = BUTTON_START_X + BUTTON_SPACING_X;
                }

                if ((this.buttons.indexOf(co) - indexToStartAt) / 2 == 0) {

                    buttonY = BUTTON_START_Y;
                } else {

                    buttonY = BUTTON_START_Y + BUTTON_SPACING_Y * MathUtils.floor(((this.buttons
                            .indexOf(co) - indexToStartAt) / 2)) + BUTTON_EXTRA_SPACING_Y;
                }
            } else {

                buttonX = Settings.WIDTH * 2.0F;
                buttonY = Settings.HEIGHT * 2.0F;
            }
            co.setPosition(buttonX, buttonY);
            co.render(sb);
        }
    }


    public void scrolledUsingBar(float newPercent) {
        this.scrollY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
        this.targetY = this.scrollY;
        updateBarPosition();
    }

    private void updateBarPosition() {
        float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollY);
        this.scrollBar.parentScrolledToPercent(percent);
    }

    private boolean shouldShowScrollBar() {
        return (this.buttons.size() > 6);
    }
}
