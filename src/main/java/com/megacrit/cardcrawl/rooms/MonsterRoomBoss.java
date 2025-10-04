package com.megacrit.cardcrawl.rooms;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/MonsterRoomBoss.class */
public class MonsterRoomBoss extends MonsterRoom {
    private static final Logger logger = LogManager.getLogger(MonsterRoomBoss.class.getName());

    public MonsterRoomBoss() {
        this.mapSymbol = "B";
    }

    @Override // com.megacrit.cardcrawl.rooms.MonsterRoom, com.megacrit.cardcrawl.rooms.AbstractRoom
    public void onPlayerEntry() {
        this.monsters = CardCrawlGame.dungeon.getBoss();
        logger.info("BOSSES: " + AbstractDungeon.bossList.size());
        CardCrawlGame.metricData.path_taken.add("BOSS");
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.bossList.remove(0);
        if (this.monsters != null) {
            this.monsters.init();
        }
        waitTimer = 0.1f;
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public AbstractCard.CardRarity getCardRarity(int roll) {
        return AbstractCard.CardRarity.RARE;
    }
}
