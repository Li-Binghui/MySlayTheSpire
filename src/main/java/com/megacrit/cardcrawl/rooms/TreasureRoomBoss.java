package com.megacrit.cardcrawl.rooms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.mods.BlightChests;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import com.megacrit.cardcrawl.rewards.chests.BossChest;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.scene.SpookierChestEffect;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/TreasureRoomBoss.class */
public class TreasureRoomBoss extends AbstractRoom {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TreasureRoomBoss");
    public static final String[] TEXT = uiStrings.TEXT;
    public AbstractChest chest;
    private static final float SHINY_INTERVAL = 0.02f;
    private float shinyTimer = 0.0f;
    public boolean choseRelic = false;

    public TreasureRoomBoss() {
        CardCrawlGame.nextDungeon = getNextDungeonName();
        if (AbstractDungeon.actNum < 4 || !AbstractPlayer.customMods.contains(BlightChests.ID)) {
            this.phase = AbstractRoom.RoomPhase.COMPLETE;
        } else {
            this.phase = AbstractRoom.RoomPhase.INCOMPLETE;
        }
        this.mapImg = ImageMaster.MAP_NODE_TREASURE;
        this.mapImgOutline = ImageMaster.MAP_NODE_TREASURE_OUTLINE;
    }

    private String getNextDungeonName() {
        String str = AbstractDungeon.id;
        char c = 65535;
        switch (str.hashCode()) {
            case -1887678253:
                if (str.equals(Exordium.ID)) {
                    c = 0;
                    break;
                }
                break;
            case 313705820:
                if (str.equals(TheCity.ID)) {
                    c = 1;
                    break;
                }
                break;
            case 791401920:
                if (str.equals(TheBeyond.ID)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return TheCity.ID;
            case 1:
                return TheBeyond.ID;
            case 2:
                if (Settings.isEndless) {
                    return Exordium.ID;
                }
                return null;
            default:
                return null;
        }
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void onPlayerEntry() {
        CardCrawlGame.music.silenceBGM();
        if (AbstractDungeon.actNum < 4 || !AbstractPlayer.customMods.contains(BlightChests.ID)) {
            AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
        }
        playBGM("SHRINE");
        this.chest = new BossChest();
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void update() {
        super.update();
        this.chest.update();
        updateShiny();
    }

    private void updateShiny() {
        if (!this.chest.isOpen) {
            this.shinyTimer -= Gdx.graphics.getDeltaTime();
            if (this.shinyTimer < 0.0f && !Settings.DISABLE_EFFECTS) {
                this.shinyTimer = SHINY_INTERVAL;
                AbstractDungeon.effectList.add(new SpookierChestEffect());
                AbstractDungeon.effectList.add(new SpookierChestEffect());
            }
        }
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void renderAboveTopPanel(SpriteBatch sb) {
        super.renderAboveTopPanel(sb);
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void render(SpriteBatch sb) {
        this.chest.render(sb);
        super.render(sb);
    }
}