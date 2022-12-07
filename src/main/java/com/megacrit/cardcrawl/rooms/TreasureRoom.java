package com.megacrit.cardcrawl.rooms;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import com.megacrit.cardcrawl.vfx.ChestShineEffect;
import com.megacrit.cardcrawl.vfx.scene.SpookyChestEffect;

public class TreasureRoom extends AbstractRoom {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TreasureRoom");
    public static final String[] TEXT = uiStrings.TEXT;

    public AbstractChest chest;

    private float shinyTimer = 0.0F;

    private static final float SHINY_INTERVAL = 0.2F;

    public TreasureRoom() {
        this.phase = RoomPhase.COMPLETE;
        this.mapSymbol = "T";
        this.mapImg = ImageMaster.MAP_NODE_TREASURE;
        this.mapImgOutline = ImageMaster.MAP_NODE_TREASURE_OUTLINE;
    }


    public void onPlayerEntry() {
        playBGM(null);
        this.chest = AbstractDungeon.getRandomChest();
        AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
    }


    public void update() {
        super.update();
        if (this.chest != null) {
            this.chest.update();
        }
        updateShiny();
    }

    private void updateShiny() {
        if (!this.chest.isOpen) {
            this.shinyTimer -= Gdx.graphics.getDeltaTime();
            if (this.shinyTimer < 0.0F && !Settings.DISABLE_EFFECTS) {
                this.shinyTimer = 0.2F;
                AbstractDungeon.topLevelEffects.add(new ChestShineEffect());
                AbstractDungeon.effectList.add(new SpookyChestEffect());
                AbstractDungeon.effectList.add(new SpookyChestEffect());
            }
        }
    }


    public void renderAboveTopPanel(SpriteBatch sb) {
        super.renderAboveTopPanel(sb);
    }


    public void render(SpriteBatch sb) {
        if (this.chest != null) {
            this.chest.render(sb);
        }
        super.render(sb);
    }
}
