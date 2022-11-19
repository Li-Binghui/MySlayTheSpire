package com.megacrit.cardcrawl.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.stats.AchievementGrid;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.Iterator;


/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/screens/SingleCardViewPopup.class */
public class SingleCardViewPopup {
    private CardGroup group;
    private AbstractCard card;
    private AbstractCard prevCard;
    private AbstractCard nextCard;
    private Hitbox cardHb;
    private static final float LINE_SPACING = 1.53f;
    private float current_x;
    private float current_y;
    private float drawScale;
    private float card_energy_w;
    private static final float DESC_OFFSET_Y2 = -12.0f;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final Color CARD_TYPE_COLOR = new Color(0.35f, 0.35f, 0.35f, 1.0f);
    private static final GlyphLayout gl = new GlyphLayout();
    public static boolean isViewingUpgrade = false;
    public static boolean enableUpgradeToggle = true;
    public boolean isOpen = false;
    private Texture portraitImg = null;
    private float fadeTimer = 0.0f;
    private Color fadeColor = Color.BLACK.cpy();
    private Hitbox upgradeHb = new Hitbox(250.0f * Settings.scale, 80.0f * Settings.scale);
    private Hitbox betaArtHb = null;
    private boolean viewBetaArt = false;
    private Hitbox prevHb = new Hitbox(200.0f * Settings.scale, 70.0f * Settings.scale);
    private Hitbox nextHb = new Hitbox(200.0f * Settings.scale, 70.0f * Settings.scale);

    public void open(AbstractCard card, CardGroup group) {
        CardCrawlGame.isPopupOpen = true;
        this.prevCard = null;
        this.nextCard = null;
        this.prevHb = null;
        this.nextHb = null;
        int i = 0;
        while (true) {
            if (i >= group.size()) {
                break;
            } else if (group.group.get(i) == card) {
                if (i != 0) {
                    this.prevCard = group.group.get(i - 1);
                }
                if (i != group.size() - 1) {
                    this.nextCard = group.group.get(i + 1);
                }
            } else {
                i++;
            }
        }
        this.prevHb = new Hitbox(160.0f * Settings.scale, 160.0f * Settings.scale);
        this.nextHb = new Hitbox(160.0f * Settings.scale, 160.0f * Settings.scale);
        this.prevHb.move((Settings.WIDTH / 2.0f) - (400.0f * Settings.scale), Settings.HEIGHT / 2.0f);
        this.nextHb.move((Settings.WIDTH / 2.0f) + (400.0f * Settings.scale), Settings.HEIGHT / 2.0f);
        this.card_energy_w = 24.0f * Settings.scale;
        this.drawScale = 2.0f;
        this.cardHb = new Hitbox(550.0f * Settings.scale, 770.0f * Settings.scale);
        this.cardHb.move(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f);
        this.card = card.makeStatEquivalentCopy();
        loadPortraitImg();
        this.group = group;
        this.isOpen = true;
        this.fadeTimer = 0.25f;
        this.fadeColor.a = 0.0f;
        this.current_x = (Settings.WIDTH / 2.0f) - (10.0f * Settings.scale);
        this.current_y = (Settings.HEIGHT / 2.0f) - (300.0f * Settings.scale);
        if (canToggleBetaArt()) {
            if (allowUpgradePreview()) {
                this.betaArtHb = new Hitbox(250.0f * Settings.scale, 80.0f * Settings.scale);
                this.betaArtHb.move((Settings.WIDTH / 2.0f) + (270.0f * Settings.scale), 70.0f * Settings.scale);
                this.upgradeHb.move((Settings.WIDTH / 2.0f) - (180.0f * Settings.scale), 70.0f * Settings.scale);
            } else {
                this.betaArtHb = new Hitbox(250.0f * Settings.scale, 80.0f * Settings.scale);
                this.betaArtHb.move(Settings.WIDTH / 2.0f, 70.0f * Settings.scale);
            }
            this.viewBetaArt = UnlockTracker.betaCardPref.getBoolean(card.cardID, false);
            return;
        }
        this.upgradeHb.move(Settings.WIDTH / 2.0f, 70.0f * Settings.scale);
        this.betaArtHb = null;
    }

    private boolean canToggleBetaArt() {
        if (UnlockTracker.isAchievementUnlocked(AchievementGrid.THE_ENDING_KEY)) {
            return true;
        }
        switch (this.card.color) {
            case RED:
                return UnlockTracker.isAchievementUnlocked(AchievementGrid.RUBY_PLUS_KEY);
            case GREEN:
                return UnlockTracker.isAchievementUnlocked(AchievementGrid.EMERALD_PLUS_KEY);
            case BLUE:
                return UnlockTracker.isAchievementUnlocked(AchievementGrid.SAPPHIRE_PLUS_KEY);
            case PURPLE:
                return UnlockTracker.isAchievementUnlocked(AchievementGrid.AMETHYST_PLUS_KEY);
            default:
                return false;
        }
    }

    private void loadPortraitImg() {
        if (Settings.PLAYTESTER_ART_MODE || UnlockTracker.betaCardPref.getBoolean(this.card.cardID, false)) {
            this.portraitImg = ImageMaster.loadImage("images/1024PortraitsBeta/" + this.card.assetUrl + ".png");
            return;
        }
        this.portraitImg = ImageMaster.loadImage("images/1024Portraits/" + this.card.assetUrl + ".png");
        if (this.portraitImg == null) {
            this.portraitImg = ImageMaster.loadImage("images/1024PortraitsBeta/" + this.card.assetUrl + ".png");
        }
    }

    public void open(AbstractCard card) {
        CardCrawlGame.isPopupOpen = true;
        this.prevCard = null;
        this.nextCard = null;
        this.prevHb = null;
        this.nextHb = null;
        this.card_energy_w = 24.0f * Settings.scale;
        this.drawScale = 2.0f;
        this.cardHb = new Hitbox(550.0f * Settings.scale, 770.0f * Settings.scale);
        this.cardHb.move(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f);
        this.card = card.makeStatEquivalentCopy();
        loadPortraitImg();
        this.group = null;
        this.isOpen = true;
        this.fadeTimer = 0.25f;
        this.fadeColor.a = 0.0f;
        this.current_x = (Settings.WIDTH / 2.0f) - (10.0f * Settings.scale);
        this.current_y = (Settings.HEIGHT / 2.0f) - (300.0f * Settings.scale);
        this.betaArtHb = null;
        if (canToggleBetaArt()) {
            this.betaArtHb = new Hitbox(250.0f * Settings.scale, 80.0f * Settings.scale);
            this.betaArtHb.move((Settings.WIDTH / 2.0f) + (270.0f * Settings.scale), 70.0f * Settings.scale);
            this.upgradeHb.move((Settings.WIDTH / 2.0f) - (180.0f * Settings.scale), 70.0f * Settings.scale);
            this.viewBetaArt = UnlockTracker.betaCardPref.getBoolean(card.cardID, false);
            return;
        }
        this.upgradeHb.move(Settings.WIDTH / 2.0f, 70.0f * Settings.scale);
    }

    public void close() {
        isViewingUpgrade = false;
        InputHelper.justReleasedClickLeft = false;
        CardCrawlGame.isPopupOpen = false;
        this.isOpen = false;
        if (this.portraitImg != null) {
            this.portraitImg.dispose();
            this.portraitImg = null;
        }
    }

    public void update() {
        this.cardHb.update();
        updateArrows();
        updateInput();
        updateFade();
        if (allowUpgradePreview()) {
            updateUpgradePreview();
        }
        if (this.betaArtHb != null && canToggleBetaArt()) {
            updateBetaArtToggler();
        }
    }

    private void updateBetaArtToggler() {
        this.betaArtHb.update();
        if (this.betaArtHb.hovered && InputHelper.justClickedLeft) {
            this.betaArtHb.clickStarted = true;
        }
        if (this.betaArtHb.clicked || CInputActionSet.topPanel.isJustPressed()) {
            CInputActionSet.topPanel.unpress();
            this.betaArtHb.clicked = false;
            this.viewBetaArt = !this.viewBetaArt;
            UnlockTracker.betaCardPref.putBoolean(this.card.cardID, this.viewBetaArt);
            UnlockTracker.betaCardPref.flush();
            if (this.portraitImg != null) {
                this.portraitImg.dispose();
            }
            loadPortraitImg();
        }
    }

    private void updateUpgradePreview() {
        this.upgradeHb.update();
        if (this.upgradeHb.hovered && InputHelper.justClickedLeft) {
            this.upgradeHb.clickStarted = true;
        }
        if (this.upgradeHb.clicked || CInputActionSet.proceed.isJustPressed()) {
            CInputActionSet.proceed.unpress();
            this.upgradeHb.clicked = false;
            isViewingUpgrade = !isViewingUpgrade;
        }
    }

    private boolean allowUpgradePreview() {
        return (!enableUpgradeToggle || this.card.color == AbstractCard.CardColor.CURSE || this.card.type == AbstractCard.CardType.STATUS) ? false : true;
    }

    private void updateArrows() {
        if (this.prevCard != null) {
            this.prevHb.update();
            if (this.prevHb.justHovered) {
                CardCrawlGame.sound.play("UI_HOVER");
            }
            if (this.prevHb.clicked || (this.prevCard != null && CInputActionSet.pageLeftViewDeck.isJustPressed())) {
                CInputActionSet.pageLeftViewDeck.unpress();
                openPrev();
            }
        }
        if (this.nextCard != null) {
            this.nextHb.update();
            if (this.nextHb.justHovered) {
                CardCrawlGame.sound.play("UI_HOVER");
            }
            if (this.nextHb.clicked || (this.nextCard != null && CInputActionSet.pageRightViewExhaust.isJustPressed())) {
                CInputActionSet.pageRightViewExhaust.unpress();
                openNext();
            }
        }
    }

    private void updateInput() {
        if (InputHelper.justClickedLeft) {
            if (this.prevCard != null && this.prevHb.hovered) {
                this.prevHb.clickStarted = true;
                CardCrawlGame.sound.play("UI_CLICK_1");
                return;
            } else if (this.nextCard != null && this.nextHb.hovered) {
                this.nextHb.clickStarted = true;
                CardCrawlGame.sound.play("UI_CLICK_1");
                return;
            }
        }
        if (InputHelper.justClickedLeft) {
            if (!this.cardHb.hovered && !this.upgradeHb.hovered && (this.betaArtHb == null || (this.betaArtHb != null && !this.betaArtHb.hovered))) {
                close();
                InputHelper.justClickedLeft = false;
                FontHelper.ClearSCPFontTextures();
            }
        } else if (InputHelper.pressedEscape || CInputActionSet.cancel.isJustPressed()) {
            CInputActionSet.cancel.unpress();
            InputHelper.pressedEscape = false;
            close();
            FontHelper.ClearSCPFontTextures();
        }
        if (this.prevCard != null && InputActionSet.left.isJustPressed()) {
            openPrev();
        } else if (this.nextCard != null && InputActionSet.right.isJustPressed()) {
            openNext();
        }
    }

    private void openPrev() {
        boolean tmp = isViewingUpgrade;
        close();
        open(this.prevCard, this.group);
        isViewingUpgrade = tmp;
        this.fadeTimer = 0.0f;
        this.fadeColor.a = 0.9f;
    }

    private void openNext() {
        boolean tmp = isViewingUpgrade;
        close();
        open(this.nextCard, this.group);
        isViewingUpgrade = tmp;
        this.fadeTimer = 0.0f;
        this.fadeColor.a = 0.9f;
    }

    private void updateFade() {
        this.fadeTimer -= Gdx.graphics.getDeltaTime();
        if (this.fadeTimer < 0.0f) {
            this.fadeTimer = 0.0f;
        }
        this.fadeColor.a = Interpolation.pow2In.apply(0.9f, 0.0f, this.fadeTimer * 4.0f);
    }

    public void render(SpriteBatch sb) {
        AbstractCard copy = null;
        if (isViewingUpgrade) {
            copy = this.card.makeStatEquivalentCopy();
            this.card.upgrade();
            this.card.displayUpgrades();
        }
        sb.setColor(this.fadeColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, 0.0f, Settings.WIDTH, Settings.HEIGHT);
        sb.setColor(Color.WHITE);
        renderCardBack(sb);
        renderPortrait(sb);
        renderFrame(sb);
        renderCardBanner(sb);
        renderCardTypeText(sb);
        if (Settings.lineBreakViaCharacter) {
            renderDescriptionCN(sb);
        } else {
            renderDescription(sb);
        }
        renderTitle(sb);
        renderCost(sb);
        renderArrows(sb);
        renderTips(sb);
        this.cardHb.render(sb);
        if (this.nextHb != null) {
            this.nextHb.render(sb);
        }
        if (this.prevHb != null) {
            this.prevHb.render(sb);
        }
        FontHelper.cardTitleFont.getData().setScale(1.0f);
        if (canToggleBetaArt()) {
            renderBetaArtToggle(sb);
        }
        if (allowUpgradePreview()) {
            renderUpgradeViewToggle(sb);
            if (Settings.isControllerMode) {
                sb.draw(CInputActionSet.proceed.getKeyImg(), (this.upgradeHb.cX - (132.0f * Settings.scale)) - 32.0f, (-32.0f) + (67.0f * Settings.scale), 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
            }
        }
        if (this.betaArtHb != null && Settings.isControllerMode) {
            sb.draw(CInputActionSet.topPanel.getKeyImg(), (this.betaArtHb.cX - (132.0f * Settings.scale)) - 32.0f, (-32.0f) + (67.0f * Settings.scale), 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
        }
        if (copy != null) {
            this.card = copy;
        }
    }

    public void renderCardBack(SpriteBatch sb) {
        TextureAtlas.AtlasRegion tmpImg = getCardBackAtlasRegion();
        if (tmpImg != null) {
            renderHelper(sb, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, tmpImg);
            return;
        }
        Texture img = getCardBackImg();
        if (img != null) {
            sb.draw(img, (Settings.WIDTH / 2.0f) - 512.0f, (Settings.HEIGHT / 2.0f) - 512.0f, 512.0f, 512.0f, 1024.0f, 1024.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 1024, 1024, false, false);
        }
    }

    private Texture getCardBackImg() {
        switch (this.card.type) {
            case ATTACK:
                switch (this.card.color) {
                }
            case POWER:
                switch (this.card.color) {
                }
        }
        switch (this.card.color) {
            default:
                return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fc A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion getCardBackAtlasRegion() {
        /*
            r3 = this;
            int[] r0 = com.megacrit.cardcrawl.screens.SingleCardViewPopup.AnonymousClass1.$SwitchMap$com$megacrit$cardcrawl$cards$AbstractCard$CardType
            r1 = r3
            com.megacrit.cardcrawl.cards.AbstractCard r1 = r1.card
            com.megacrit.cardcrawl.cards.AbstractCard$CardType r1 = r1.type
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L28;
                case 2: goto L6c;
                default: goto Lb0;
            }
        L28:
            int[] r0 = com.megacrit.cardcrawl.screens.SingleCardViewPopup.AnonymousClass1.$SwitchMap$com$megacrit$cardcrawl$cards$AbstractCard$CardColor
            r1 = r3
            com.megacrit.cardcrawl.cards.AbstractCard r1 = r1.card
            com.megacrit.cardcrawl.cards.AbstractCard$CardColor r1 = r1.color
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L58;
                case 2: goto L5c;
                case 3: goto L60;
                case 4: goto L64;
                case 5: goto L68;
                default: goto L6c;
            }
        L58:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_ATTACK_BG_RED_L
            return r0
        L5c:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_ATTACK_BG_GREEN_L
            return r0
        L60:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_ATTACK_BG_BLUE_L
            return r0
        L64:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_ATTACK_BG_PURPLE_L
            return r0
        L68:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_ATTACK_BG_GRAY_L
            return r0
        L6c:
            int[] r0 = com.megacrit.cardcrawl.screens.SingleCardViewPopup.AnonymousClass1.$SwitchMap$com$megacrit$cardcrawl$cards$AbstractCard$CardColor
            r1 = r3
            com.megacrit.cardcrawl.cards.AbstractCard r1 = r1.card
            com.megacrit.cardcrawl.cards.AbstractCard$CardColor r1 = r1.color
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L9c;
                case 2: goto La0;
                case 3: goto La4;
                case 4: goto La8;
                case 5: goto Lac;
                default: goto Lb0;
            }
        L9c:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_POWER_BG_RED_L
            return r0
        La0:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_POWER_BG_GREEN_L
            return r0
        La4:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_POWER_BG_BLUE_L
            return r0
        La8:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_POWER_BG_PURPLE_L
            return r0
        Lac:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_POWER_BG_GRAY_L
            return r0
        Lb0:
            int[] r0 = com.megacrit.cardcrawl.screens.SingleCardViewPopup.AnonymousClass1.$SwitchMap$com$megacrit$cardcrawl$cards$AbstractCard$CardColor
            r1 = r3
            com.megacrit.cardcrawl.cards.AbstractCard r1 = r1.card
            com.megacrit.cardcrawl.cards.AbstractCard$CardColor r1 = r1.color
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto Le4;
                case 2: goto Le8;
                case 3: goto Lec;
                case 4: goto Lf0;
                case 5: goto Lf4;
                case 6: goto Lf8;
                default: goto Lfc;
            }
        Le4:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_SKILL_BG_RED_L
            return r0
        Le8:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_SKILL_BG_GREEN_L
            return r0
        Lec:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_SKILL_BG_BLUE_L
            return r0
        Lf0:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_SKILL_BG_PURPLE_L
            return r0
        Lf4:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_SKILL_BG_GRAY_L
            return r0
        Lf8:
            com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion r0 = com.megacrit.cardcrawl.helpers.ImageMaster.CARD_SKILL_BG_BLACK_L
            return r0
        Lfc:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megacrit.cardcrawl.screens.SingleCardViewPopup.getCardBackAtlasRegion():com.badlogic.gdx.graphics.g2d.TextureAtlas$AtlasRegion");
    }

    private void renderPortrait(SpriteBatch sb) {
        TextureAtlas.AtlasRegion img;
        if (this.card.isLocked) {
            switch (this.card.type) {
                case ATTACK:
                    img = ImageMaster.CARD_LOCKED_ATTACK_L;
                    break;
                case POWER:
                    img = ImageMaster.CARD_LOCKED_POWER_L;
                    break;
                case SKILL:
                default:
                    img = ImageMaster.CARD_LOCKED_SKILL_L;
                    break;
            }
            renderHelper(sb, Settings.WIDTH / 2.0f, (Settings.HEIGHT / 2.0f) + (136.0f * Settings.scale), img);
        } else if (this.portraitImg != null) {
            sb.draw(this.portraitImg, Settings.WIDTH / 2.0F - 250.0F, Settings.HEIGHT / 2.0F - 190.0F + 136.0F * Settings.scale, 250.0F, 190.0F, 500.0F, 380.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 500, 380, false, false);
        } else if (this.card.jokePortrait != null) {
            sb.draw(this.card.jokePortrait, (Settings.WIDTH / 2.0f) - (this.card.portrait.packedWidth / 2.0f), ((Settings.HEIGHT / 2.0f) - (this.card.portrait.packedHeight / 2.0f)) + (140.0f * Settings.scale), this.card.portrait.packedWidth / 2.0f, this.card.portrait.packedHeight / 2.0f, this.card.portrait.packedWidth, this.card.portrait.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, 0.0f);
        }
    }

    private void renderFrame(SpriteBatch sb) {
        TextureAtlas.AtlasRegion tmpImg = null;
        float tOffset = 0.0f;
        float tWidth = 0.0f;
        switch (this.card.type) {
            case ATTACK:
                tWidth = AbstractCard.typeWidthAttack;
                tOffset = AbstractCard.typeOffsetAttack;
                switch (this.card.rarity) {
                    case UNCOMMON:
                        tmpImg = ImageMaster.CARD_FRAME_ATTACK_UNCOMMON_L;
                        break;
                    case RARE:
                        tmpImg = ImageMaster.CARD_FRAME_ATTACK_RARE_L;
                        break;
                    case COMMON:
                    default:
                        tmpImg = ImageMaster.CARD_FRAME_ATTACK_COMMON_L;
                        break;
                }
            case POWER:
                tWidth = AbstractCard.typeWidthPower;
                tOffset = AbstractCard.typeOffsetPower;
                switch (this.card.rarity) {
                    case UNCOMMON:
                        tmpImg = ImageMaster.CARD_FRAME_POWER_UNCOMMON_L;
                        break;
                    case RARE:
                        tmpImg = ImageMaster.CARD_FRAME_POWER_RARE_L;
                        break;
                    case COMMON:
                    default:
                        tmpImg = ImageMaster.CARD_FRAME_POWER_COMMON_L;
                        break;
                }
            case SKILL:
                tWidth = AbstractCard.typeWidthSkill;
                tOffset = AbstractCard.typeOffsetSkill;
                switch (this.card.rarity) {
                    case UNCOMMON:
                        tmpImg = ImageMaster.CARD_FRAME_SKILL_UNCOMMON_L;
                        break;
                    case RARE:
                        tmpImg = ImageMaster.CARD_FRAME_SKILL_RARE_L;
                        break;
                    case COMMON:
                    default:
                        tmpImg = ImageMaster.CARD_FRAME_SKILL_COMMON_L;
                        break;
                }
            case CURSE:
                tWidth = AbstractCard.typeWidthCurse;
                tOffset = AbstractCard.typeOffsetCurse;
                switch (this.card.rarity) {
                    case UNCOMMON:
                        tmpImg = ImageMaster.CARD_FRAME_SKILL_UNCOMMON_L;
                        break;
                    case RARE:
                        tmpImg = ImageMaster.CARD_FRAME_SKILL_RARE_L;
                        break;
                    case COMMON:
                    default:
                        tmpImg = ImageMaster.CARD_FRAME_SKILL_COMMON_L;
                        break;
                }
            case STATUS:
                tWidth = AbstractCard.typeWidthStatus;
                tOffset = AbstractCard.typeOffsetStatus;
                switch (this.card.rarity) {
                    case UNCOMMON:
                        tmpImg = ImageMaster.CARD_FRAME_SKILL_UNCOMMON_L;
                        break;
                    case RARE:
                        tmpImg = ImageMaster.CARD_FRAME_SKILL_RARE_L;
                        break;
                    case COMMON:
                    default:
                        tmpImg = ImageMaster.CARD_FRAME_SKILL_COMMON_L;
                        break;
                }
        }
        if (tmpImg != null) {
            renderHelper(sb, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, tmpImg);
        } else {
            Texture img = getFrameImg();
            tWidth = AbstractCard.typeWidthSkill;
            tOffset = AbstractCard.typeOffsetSkill;
            if (img != null) {
                sb.draw(img, (Settings.WIDTH / 2.0f) - 512.0f, (Settings.HEIGHT / 2.0f) - 512.0f, 512.0f, 512.0f, 1024.0f, 1024.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 1024, 1024, false, false);
            } else {
                renderHelper(sb, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, ImageMaster.CARD_FRAME_SKILL_COMMON_L);
            }
        }
        renderDynamicFrame(sb, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, tOffset, tWidth);
    }

    private Texture getFrameImg() {
        switch (this.card.rarity) {
            default:
                return null;
        }
    }

    private void renderDynamicFrame(SpriteBatch sb, float x, float y, float typeOffset, float typeWidth) {
        if (typeWidth > 1.1f) {
            switch (this.card.rarity) {
                case UNCOMMON:
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_MID_L, 0.0f, typeWidth);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_LEFT_L, -typeOffset, 1.0f);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_RIGHT_L, typeOffset, 1.0f);
                    return;
                case RARE:
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_MID_L, 0.0f, typeWidth);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_LEFT_L, -typeOffset, 1.0f);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_RIGHT_L, typeOffset, 1.0f);
                    return;
                case COMMON:
                case BASIC:
                case CURSE:
                case SPECIAL:
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_MID_L, 0.0f, typeWidth);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_LEFT_L, -typeOffset, 1.0f);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_RIGHT_L, typeOffset, 1.0f);
                    return;
                default:
                    return;
            }
        }
    }

    private void dynamicFrameRenderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float xOffset, float xScale) {
        sb.draw(img, (((Settings.WIDTH / 2.0f) + img.offsetX) - (img.originalWidth / 2.0f)) + (xOffset * this.drawScale), ((Settings.HEIGHT / 2.0f) + img.offsetY) - (img.originalHeight / 2.0f), (img.originalWidth / 2.0f) - img.offsetX, (img.originalHeight / 2.0f) - img.offsetY, img.packedWidth, img.packedHeight, Settings.scale * xScale, Settings.scale, 0.0f);
    }

    private void renderCardBanner(SpriteBatch sb) {
        TextureAtlas.AtlasRegion tmpImg = null;
        switch (this.card.rarity) {
            case UNCOMMON:
                tmpImg = ImageMaster.CARD_BANNER_UNCOMMON_L;
                break;
            case RARE:
                tmpImg = ImageMaster.CARD_BANNER_RARE_L;
                break;
            case COMMON:
                tmpImg = ImageMaster.CARD_BANNER_COMMON_L;
                break;
        }
        if (tmpImg != null) {
            renderHelper(sb, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, tmpImg);
            return;
        }
        Texture img = getBannerImg();
        if (img != null) {
            sb.draw(img, (Settings.WIDTH / 2.0f) - 512.0f, (Settings.HEIGHT / 2.0f) - 512.0f, 512.0f, 512.0f, 1024.0f, 1024.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 1024, 1024, false, false);
            return;
        }
        TextureAtlas.AtlasRegion tmpImg2 = ImageMaster.CARD_BANNER_COMMON_L;
        renderHelper(sb, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, tmpImg2);
    }

    private Texture getBannerImg() {
        switch (this.card.rarity) {
            default:
                return null;
        }
    }

    private String getDynamicValue(char key) {
        switch (key) {
            case 'B':
                if (!this.card.isBlockModified) {
                    return Integer.toString(this.card.baseBlock);
                }
                if (this.card.block >= this.card.baseBlock) {
                    return "[#7fff00]" + Integer.toString(this.card.block) + "[]";
                }
                return "[#ff6563]" + Integer.toString(this.card.block) + "[]";
            case 'D':
                if (!this.card.isDamageModified) {
                    return Integer.toString(this.card.baseDamage);
                }
                if (this.card.damage >= this.card.baseDamage) {
                    return "[#7fff00]" + Integer.toString(this.card.damage) + "[]";
                }
                return "[#ff6563]" + Integer.toString(this.card.damage) + "[]";
            case 'M':
                if (!this.card.isMagicNumberModified) {
                    return Integer.toString(this.card.baseMagicNumber);
                }
                if (this.card.magicNumber >= this.card.baseMagicNumber) {
                    return "[#7fff00]" + Integer.toString(this.card.magicNumber) + "[]";
                }
                return "[#ff6563]" + Integer.toString(this.card.magicNumber) + "[]";
            default:
                return Integer.toString(-99);
        }
    }

    private void renderDescriptionCN(SpriteBatch sb) {
        float start_x;
        String[] cachedTokenizedTextCN;
        float f;
        float f2;
        if (this.card.isLocked || !this.card.isSeen) {
            FontHelper.renderFontCentered(sb, FontHelper.largeCardFont, "? ? ?", Settings.WIDTH / 2.0f, (Settings.HEIGHT / 2.0f) - (195.0f * Settings.scale), Settings.CREAM_COLOR);
            return;
        }
        BitmapFont font = FontHelper.SCP_cardDescFont;
        float draw_y = this.current_y + (100.0f * Settings.scale) + (((this.card.description.size() * font.getCapHeight()) * 0.775f) - (font.getCapHeight() * 0.375f));
        float spacing = ((LINE_SPACING * (-font.getCapHeight())) / Settings.scale) / this.drawScale;
        GlyphLayout gl2 = new GlyphLayout();
        for (int i = 0; i < this.card.description.size(); i++) {
            if (Settings.leftAlignCards) {
                start_x = this.current_x - (214.0f * Settings.scale);
            } else {
                start_x = (this.current_x - ((this.card.description.get(i).width * this.drawScale) / 2.0f)) - (20.0f * Settings.scale);
            }
            for (String tmp : this.card.description.get(i).getCachedTokenizedTextCN()) {
                String updateTmp = null;
                for (int j = 0; j < tmp.length(); j++) {
                    if (tmp.charAt(j) == 'D' || ((tmp.charAt(j) == 'B' && !tmp.contains("[B]")) || tmp.charAt(j) == 'M')) {
                        String updateTmp2 = tmp.substring(0, j);
                        updateTmp = (updateTmp2 + getDynamicValue(tmp.charAt(j))) + tmp.substring(j + 1);
                        break;
                    }
                }
                if (updateTmp != null) {
                    tmp = updateTmp;
                }
                for (int j2 = 0; j2 < tmp.length(); j2++) {
                    if (tmp.charAt(j2) == 'D' || ((tmp.charAt(j2) == 'B' && !tmp.contains("[B]")) || tmp.charAt(j2) == 'M')) {
                        String updateTmp3 = tmp.substring(0, j2);
                        updateTmp = (updateTmp3 + getDynamicValue(tmp.charAt(j2))) + tmp.substring(j2 + 1);
                        break;
                    }
                }
                if (updateTmp != null) {
                    tmp = updateTmp;
                }
                if (!tmp.isEmpty() && tmp.charAt(0) == '*') {
                    String tmp2 = tmp.substring(1);
                    String punctuation = "";
                    if (tmp2.length() > 1 && tmp2.charAt(tmp2.length() - 2) != '+' && !Character.isLetter(tmp2.charAt(tmp2.length() - 2))) {
                        String punctuation2 = punctuation + tmp2.charAt(tmp2.length() - 2);
                        tmp2 = tmp2.substring(0, tmp2.length() - 2);
                        punctuation = punctuation2 + ' ';
                    }
                    gl2.setText(font, tmp2);
                    FontHelper.renderRotatedText(sb, font, tmp2, this.current_x, this.current_y, (start_x - this.current_x) + (gl2.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.GOLD_COLOR);
                    float start_x2 = Math.round(start_x + gl2.width);
                    gl2.setText(font, punctuation);
                    FontHelper.renderRotatedText(sb, font, punctuation, this.current_x, this.current_y, (start_x2 - this.current_x) + (gl2.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.CREAM_COLOR);
                    gl2.setText(font, punctuation);
                    f2 = start_x2;
                    f = gl2.width;
                } else if (tmp.equals("[R]")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_red, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    f2 = start_x;
                    f = gl2.width;
                } else if (tmp.equals("[G]")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_green, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    f2 = start_x;
                    f = gl2.width;
                } else if (tmp.equals("[B]")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_blue, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    f2 = start_x;
                    f = gl2.width;
                } else if (tmp.equals("[W]")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_purple, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    f2 = start_x;
                    f = gl2.width;
                } else {
                    gl2.setText(font, tmp);
                    FontHelper.renderRotatedText(sb, font, tmp, this.current_x, this.current_y, (start_x - this.current_x) + (gl2.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.CREAM_COLOR);
                    f2 = start_x;
                    f = gl2.width;
                }
                start_x = f2 + f;
            }
        }
        font.getData().setScale(1.0f);
    }

    private void renderDescription(SpriteBatch sb) {
        String[] cachedTokenizedText;
        if (this.card.isLocked || !this.card.isSeen) {
            FontHelper.renderFontCentered(sb, FontHelper.largeCardFont, "? ? ?", Settings.WIDTH / 2.0f, (Settings.HEIGHT / 2.0f) - (195.0f * Settings.scale), Settings.CREAM_COLOR);
            return;
        }
        BitmapFont font = FontHelper.SCP_cardDescFont;
        float draw_y = this.current_y + (100.0f * Settings.scale) + (((this.card.description.size() * font.getCapHeight()) * 0.775f) - (font.getCapHeight() * 0.375f));
        float spacing = ((LINE_SPACING * (-font.getCapHeight())) / Settings.scale) / this.drawScale;
        GlyphLayout gl2 = new GlyphLayout();
        for (int i = 0; i < this.card.description.size(); i++) {
            float start_x = this.current_x - ((this.card.description.get(i).width * this.drawScale) / 2.0f);
            for (String tmp : this.card.description.get(i).getCachedTokenizedText()) {
                if (tmp.charAt(0) == '*') {
                    String tmp2 = tmp.substring(1);
                    String punctuation = "";
                    if (tmp2.length() > 1 && tmp2.charAt(tmp2.length() - 2) != '+' && !Character.isLetter(tmp2.charAt(tmp2.length() - 2))) {
                        String punctuation2 = punctuation + tmp2.charAt(tmp2.length() - 2);
                        tmp2 = tmp2.substring(0, tmp2.length() - 2);
                        punctuation = punctuation2 + ' ';
                    }
                    gl2.setText(font, tmp2);
                    FontHelper.renderRotatedText(sb, font, tmp2, this.current_x, this.current_y, (start_x - this.current_x) + (gl2.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.GOLD_COLOR);
                    float start_x2 = Math.round(start_x + gl2.width);
                    gl2.setText(font, punctuation);
                    FontHelper.renderRotatedText(sb, font, punctuation, this.current_x, this.current_y, (start_x2 - this.current_x) + (gl2.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.CREAM_COLOR);
                    gl2.setText(font, punctuation);
                    start_x = start_x2 + gl2.width;
                } else if (tmp.charAt(0) == '!') {
                    if (tmp.length() == 4) {
                        start_x += renderDynamicVariable(tmp.charAt(1), start_x, draw_y, i, font, sb, null);
                    } else if (tmp.length() == 5) {
                        start_x += renderDynamicVariable(tmp.charAt(1), start_x, draw_y, i, font, sb, Character.valueOf(tmp.charAt(3)));
                    }
                } else if (tmp.equals("[R] ")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_red, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    start_x += gl2.width;
                } else if (tmp.equals("[R]. ")) {
                    gl2.width = (this.card_energy_w * this.drawScale) / Settings.scale;
                    renderSmallEnergy(sb, AbstractCard.orb_red, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, (start_x - this.current_x) + (this.card_energy_w * this.drawScale), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.CREAM_COLOR);
                    float start_x3 = start_x + gl2.width;
                    gl2.setText(font, LocalizedStrings.PERIOD);
                    start_x = start_x3 + gl2.width;
                } else if (tmp.equals("[G] ")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_green, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    start_x += gl2.width;
                } else if (tmp.equals("[G]. ")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_green, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, (start_x - this.current_x) + (this.card_energy_w * this.drawScale), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.CREAM_COLOR);
                    start_x += gl2.width;
                } else if (tmp.equals("[B] ")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_blue, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    start_x += gl2.width;
                } else if (tmp.equals("[B]. ")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_blue, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, (start_x - this.current_x) + (this.card_energy_w * this.drawScale), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.CREAM_COLOR);
                    start_x += gl2.width;
                } else if (tmp.equals("[W] ")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_purple, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    start_x += gl2.width;
                } else if (tmp.equals("[W]. ")) {
                    gl2.width = this.card_energy_w * this.drawScale;
                    renderSmallEnergy(sb, AbstractCard.orb_purple, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-87.0f) - (((((this.card.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, (start_x - this.current_x) + (this.card_energy_w * this.drawScale), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.CREAM_COLOR);
                    start_x += gl2.width;
                } else {
                    gl2.setText(font, tmp);
                    FontHelper.renderRotatedText(sb, font, tmp, this.current_x, this.current_y, (start_x - this.current_x) + (gl2.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.CREAM_COLOR);
                    start_x += gl2.width;
                }
            }
        }
        font.getData().setScale(1.0f);
    }

    private void renderSmallEnergy(SpriteBatch sb, TextureAtlas.AtlasRegion region, float x, float y) {
        sb.setColor(Color.WHITE);
        sb.draw(region.getTexture(), ((this.current_x + ((x * Settings.scale) * this.drawScale)) + (region.offsetX * Settings.scale)) - (4.0f * Settings.scale), this.current_y + (y * Settings.scale * this.drawScale) + (280.0f * Settings.scale), 0.0f, 0.0f, region.packedWidth, region.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, 0.0f, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight(), false, false);
    }

    private void renderCardTypeText(SpriteBatch sb) {
        String label = "";
        switch (this.card.type) {
            case ATTACK:
                label = TEXT[0];
                break;
            case POWER:
                label = TEXT[2];
                break;
            case SKILL:
                label = TEXT[1];
                break;
            case CURSE:
                label = TEXT[3];
                break;
            case STATUS:
                label = TEXT[7];
                break;
        }
        FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, label, (Settings.WIDTH / 2.0f) + (3.0f * Settings.scale), (Settings.HEIGHT / 2.0f) - (40.0f * Settings.scale), CARD_TYPE_COLOR);
    }

    private float renderDynamicVariable(char key, float start_x, float draw_y, int i, BitmapFont font, SpriteBatch sb, Character end) {
        StringBuilder stringBuilder = new StringBuilder();
        Color c = null;
        int num = 0;
        switch (key) {
            case 'B':
                num = this.card.baseBlock;
                if (!this.card.upgradedBlock) {
                    c = Settings.CREAM_COLOR;
                    break;
                } else {
                    c = Settings.GREEN_TEXT_COLOR;
                    break;
                }
            case 'D':
                num = this.card.baseDamage;
                if (!this.card.upgradedDamage) {
                    c = Settings.CREAM_COLOR;
                    break;
                } else {
                    c = Settings.GREEN_TEXT_COLOR;
                    break;
                }
            case 'M':
                num = this.card.baseMagicNumber;
                if (!this.card.upgradedMagicNumber) {
                    c = Settings.CREAM_COLOR;
                    break;
                } else {
                    c = Settings.GREEN_TEXT_COLOR;
                    break;
                }
        }
        stringBuilder.append(Integer.toString(num));
        gl.setText(font, stringBuilder.toString());
        FontHelper.renderRotatedText(sb, font, stringBuilder.toString(), this.current_x, this.current_y, (start_x - this.current_x) + (gl.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, c);
        if (end != null) {
            FontHelper.renderRotatedText(sb, font, Character.toString(end.charValue()), this.current_x, this.current_y, (start_x - this.current_x) + gl.width + (10.0f * Settings.scale), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.CREAM_COLOR);
        }
        stringBuilder.append(' ');
        gl.setText(font, stringBuilder.toString());
        return gl.width;
    }

    private void renderTitle(SpriteBatch sb) {
        if (this.card.isLocked) {
            FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, TEXT[4], Settings.WIDTH / 2.0f, (Settings.HEIGHT / 2.0f) + (338.0f * Settings.scale), Settings.CREAM_COLOR);
        } else if (!this.card.isSeen) {
            FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, TEXT[5], Settings.WIDTH / 2.0f, (Settings.HEIGHT / 2.0f) + (338.0f * Settings.scale), Settings.CREAM_COLOR);
        } else if (!isViewingUpgrade || allowUpgradePreview()) {
            FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, this.card.name, Settings.WIDTH / 2.0f, (Settings.HEIGHT / 2.0f) + (338.0f * Settings.scale), Settings.CREAM_COLOR);
        } else {
            FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, this.card.name, Settings.WIDTH / 2.0f, (Settings.HEIGHT / 2.0f) + (338.0f * Settings.scale), Settings.GREEN_TEXT_COLOR);
        }
    }

    private void renderCost(SpriteBatch sb) {
        Color c;
        TextureAtlas.AtlasRegion tmpImg;
        if (!this.card.isLocked && this.card.isSeen) {
            if (this.card.cost > -2) {
                switch (this.card.color) {
                    case RED:
                        tmpImg = ImageMaster.CARD_RED_ORB_L;
                        break;
                    case GREEN:
                        tmpImg = ImageMaster.CARD_GREEN_ORB_L;
                        break;
                    case BLUE:
                        tmpImg = ImageMaster.CARD_BLUE_ORB_L;
                        break;
                    case PURPLE:
                        tmpImg = ImageMaster.CARD_PURPLE_ORB_L;
                        break;
                    case COLORLESS:
                    default:
                        tmpImg = ImageMaster.CARD_GRAY_ORB_L;
                        break;
                }
                if (tmpImg != null) {
                    renderHelper(sb, (Settings.WIDTH / 2.0f) - (270.0f * Settings.scale), (Settings.HEIGHT / 2.0f) + (380.0f * Settings.scale), tmpImg);
                }
            }
            if (this.card.isCostModified) {
                c = Settings.GREEN_TEXT_COLOR;
            } else {
                c = Settings.CREAM_COLOR;
            }
            switch (this.card.cost) {
                case -2:
                    return;
                case -1:
                    FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, "X", (Settings.WIDTH / 2.0f) - (292.0f * Settings.scale), (Settings.HEIGHT / 2.0f) + (404.0f * Settings.scale), c);
                    return;
                case 0:
                default:
                    FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, Integer.toString(this.card.cost), (Settings.WIDTH / 2.0f) - (292.0f * Settings.scale), (Settings.HEIGHT / 2.0f) + (404.0f * Settings.scale), c);
                    return;
                case 1:
                    FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, Integer.toString(this.card.cost), (Settings.WIDTH / 2.0f) - (284.0f * Settings.scale), (Settings.HEIGHT / 2.0f) + (404.0f * Settings.scale), c);
                    return;
            }
        }
    }

    private void renderHelper(SpriteBatch sb, float x, float y, TextureAtlas.AtlasRegion img) {
        if (img != null) {
            sb.draw(img, (x + img.offsetX) - (img.originalWidth / 2.0f), (y + img.offsetY) - (img.originalHeight / 2.0f), (img.originalWidth / 2.0f) - img.offsetX, (img.originalHeight / 2.0f) - img.offsetY, img.packedWidth, img.packedHeight, Settings.scale, Settings.scale, 0.0f);
        }
    }

    private void renderArrows(SpriteBatch sb) {
        if (this.prevCard != null) {
            sb.draw(ImageMaster.POPUP_ARROW, this.prevHb.cX - 128.0f, this.prevHb.cY - 128.0f, 128.0f, 128.0f, 256.0f, 256.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 256, 256, false, false);
            if (Settings.isControllerMode) {
                sb.draw(CInputActionSet.pageLeftViewDeck.getKeyImg(), (this.prevHb.cX - 32.0f) + (0.0f * Settings.scale), (this.prevHb.cY - 32.0f) + (100.0f * Settings.scale), 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
            }
            if (this.prevHb.hovered) {
                sb.setBlendFunction(770, 1);
                sb.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
                sb.draw(ImageMaster.POPUP_ARROW, this.prevHb.cX - 128.0f, this.prevHb.cY - 128.0f, 128.0f, 128.0f, 256.0f, 256.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 256, 256, false, false);
                sb.setColor(Color.WHITE);
                sb.setBlendFunction(770, 771);
            }
        }
        if (this.nextCard != null) {
            sb.draw(ImageMaster.POPUP_ARROW, this.nextHb.cX - 128.0f, this.nextHb.cY - 128.0f, 128.0f, 128.0f, 256.0f, 256.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 256, 256, true, false);
            if (Settings.isControllerMode) {
                sb.draw(CInputActionSet.pageRightViewExhaust.getKeyImg(), (this.nextHb.cX - 32.0f) + (0.0f * Settings.scale), (this.nextHb.cY - 32.0f) + (100.0f * Settings.scale), 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
            }
            if (this.nextHb.hovered) {
                sb.setBlendFunction(770, 1);
                sb.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
                sb.draw(ImageMaster.POPUP_ARROW, this.nextHb.cX - 128.0f, this.nextHb.cY - 128.0f, 128.0f, 128.0f, 256.0f, 256.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 256, 256, true, false);
                sb.setColor(Color.WHITE);
                sb.setBlendFunction(770, 771);
            }
        }
    }

    private void renderBetaArtToggle(SpriteBatch sb) {
        if (this.betaArtHb != null) {
            sb.setColor(Color.WHITE);
            sb.draw(ImageMaster.CHECKBOX, (this.betaArtHb.cX - (80.0f * Settings.scale)) - 32.0f, this.betaArtHb.cY - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
            if (this.betaArtHb.hovered) {
                FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[14], this.betaArtHb.cX - (45.0f * Settings.scale), this.betaArtHb.cY + (10.0f * Settings.scale), Settings.BLUE_TEXT_COLOR);
            } else {
                FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[14], this.betaArtHb.cX - (45.0f * Settings.scale), this.betaArtHb.cY + (10.0f * Settings.scale), Settings.GOLD_COLOR);
            }
            if (this.viewBetaArt) {
                sb.setColor(Color.WHITE);
                sb.draw(ImageMaster.TICK, (this.betaArtHb.cX - (80.0f * Settings.scale)) - 32.0f, this.betaArtHb.cY - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
            }
            this.betaArtHb.render(sb);
        }
    }

    private void renderUpgradeViewToggle(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(ImageMaster.CHECKBOX, (this.upgradeHb.cX - (80.0f * Settings.scale)) - 32.0f, this.upgradeHb.cY - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
        if (this.upgradeHb.hovered) {
            FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[6], this.upgradeHb.cX - (45.0f * Settings.scale), this.upgradeHb.cY + (10.0f * Settings.scale), Settings.BLUE_TEXT_COLOR);
        } else {
            FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[6], this.upgradeHb.cX - (45.0f * Settings.scale), this.upgradeHb.cY + (10.0f * Settings.scale), Settings.GOLD_COLOR);
        }
        if (isViewingUpgrade) {
            sb.setColor(Color.WHITE);
            sb.draw(ImageMaster.TICK, (this.upgradeHb.cX - (80.0f * Settings.scale)) - 32.0f, this.upgradeHb.cY - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
        }
        this.upgradeHb.render(sb);
    }

    private void renderTips(SpriteBatch sb) {
        ArrayList<PowerTip> t = new ArrayList<>();
        if (this.card.isLocked) {
            t.add(new PowerTip(TEXT[4], GameDictionary.keywords.get(TEXT[4].toLowerCase())));
        } else if (!this.card.isSeen) {
            t.add(new PowerTip(TEXT[5], GameDictionary.keywords.get(TEXT[5].toLowerCase())));
        } else {
            Iterator<String> it = this.card.keywords.iterator();
            while (it.hasNext()) {
                String s = it.next();
                if (!s.equals("[R]") && !s.equals("[G]") && !s.equals("[B]") && !s.equals("[W]")) {
                    t.add(new PowerTip(TipHelper.capitalize(s), GameDictionary.keywords.get(s)));
                }
            }
        }
        if (!t.isEmpty()) {
            TipHelper.queuePowerTips((Settings.WIDTH / 2.0f) + (340.0f * Settings.scale), 420.0f * Settings.scale, t);
        }
        if (this.card.cardsToPreview != null) {
            this.card.renderCardPreviewInSingleView(sb);
        }
    }
}