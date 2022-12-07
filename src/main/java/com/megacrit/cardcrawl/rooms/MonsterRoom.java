package com.megacrit.cardcrawl.rooms;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.daily.mods.Vintage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.DiscardPileViewScreen;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/MonsterRoom.class */
public class MonsterRoom extends AbstractRoom {
    public DiscardPileViewScreen discardPileViewScreen = new DiscardPileViewScreen();
    public static final float COMBAT_WAIT_TIME = 0.1f;

    public MonsterRoom() {
        this.phase = AbstractRoom.RoomPhase.COMBAT;
        this.mapSymbol = "M";
        this.mapImg = ImageMaster.MAP_NODE_ENEMY;
        this.mapImgOutline = ImageMaster.MAP_NODE_ENEMY_OUTLINE;
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void dropReward() {
        if (ModHelper.isModEnabled(Vintage.ID) && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
            AbstractRelic.RelicTier tier = returnRandomRelicTier();
            addRelicToRewards(tier);
        }
    }

    private AbstractRelic.RelicTier returnRandomRelicTier() {
        int roll = AbstractDungeon.relicRng.random(0, 99);
        if (roll < 50) {
            return AbstractRelic.RelicTier.COMMON;
        }
        if (roll > 85) {
            return AbstractRelic.RelicTier.RARE;
        }
        return AbstractRelic.RelicTier.UNCOMMON;
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void onPlayerEntry() {
        playBGM(null);
        if (this.monsters == null) {
            this.monsters = CardCrawlGame.dungeon.getMonsterForRoomCreation();
            this.monsters.init();
        }
        waitTimer = 0.1f;
    }

    public void setMonster(MonsterGroup m) {
        this.monsters = m;
    }
}