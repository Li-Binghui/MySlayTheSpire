package com.megacrit.cardcrawl.rooms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/RestRoom.class */
public class RestRoom extends AbstractRoom {
    public long fireSoundId;
    public static long lastFireSoundId = 0;
    public CampfireUI campfireUI;

    public RestRoom() {
        this.phase = AbstractRoom.RoomPhase.INCOMPLETE;
        this.mapSymbol = "R";
        this.mapImg = ImageMaster.MAP_NODE_REST;
        this.mapImgOutline = ImageMaster.MAP_NODE_REST_OUTLINE;
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void onPlayerEntry() {
        if (!AbstractDungeon.id.equals(TheEnding.ID)) {
            CardCrawlGame.music.silenceBGM();
        }
        this.fireSoundId = CardCrawlGame.sound.playAndLoop("REST_FIRE_WET");
        lastFireSoundId = this.fireSoundId;
        this.campfireUI = new CampfireUI();
        Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            r.onEnterRestRoom();
        }
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public AbstractCard.CardRarity getCardRarity(int roll) {
        return getCardRarity(roll, false);
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void update() {
        super.update();
        if (this.campfireUI != null) {
            this.campfireUI.update();
        }
    }

    public void fadeIn() {
        if (!AbstractDungeon.id.equals(TheEnding.ID)) {
            CardCrawlGame.music.unsilenceBGM();
        }
    }

    public void cutFireSound() {
        CardCrawlGame.sound.fadeOut("REST_FIRE_WET", ((RestRoom) AbstractDungeon.getCurrRoom()).fireSoundId);
    }

    public void updateAmbience() {
        CardCrawlGame.sound.adjustVolume("REST_FIRE_WET", this.fireSoundId);
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void render(SpriteBatch sb) {
        if (this.campfireUI != null) {
            this.campfireUI.render(sb);
        }
        super.render(sb);
    }
}