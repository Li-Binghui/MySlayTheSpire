package com.megacrit.cardcrawl.rooms;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.blights.MimicInfestation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.mods.BigGameHunter;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.RegenerateMonsterPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BlackStar;
import com.megacrit.cardcrawl.rewards.RewardItem;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/MonsterRoomElite.class */
public class MonsterRoomElite extends MonsterRoom {
    public MonsterRoomElite() {
        this.mapSymbol = "E";
        this.mapImg = ImageMaster.MAP_NODE_ELITE;
        this.mapImgOutline = ImageMaster.MAP_NODE_ELITE_OUTLINE;
        this.eliteTrigger = true;
        this.baseRareCardChance = 10;
        this.baseUncommonCardChance = 40;
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void applyEmeraldEliteBuff() {
        if (Settings.isFinalActAvailable && AbstractDungeon.getCurrMapNode().hasEmeraldKey) {
            switch (AbstractDungeon.mapRng.random(0, 3)) {
                case 0:
                    Iterator<AbstractMonster> it = this.monsters.monsters.iterator();
                    while (it.hasNext()) {
                        AbstractMonster m = it.next();
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new StrengthPower(m, AbstractDungeon.actNum + 1), AbstractDungeon.actNum + 1));
                    }
                    break;
                case 1:
                    Iterator<AbstractMonster> it2 = this.monsters.monsters.iterator();
                    while (it2.hasNext()) {
                        AbstractDungeon.actionManager.addToBottom(new IncreaseMaxHpAction(it2.next(), 0.25f, true));
                    }
                    break;
                case 2:
                    Iterator<AbstractMonster> it3 = this.monsters.monsters.iterator();
                    while (it3.hasNext()) {
                        AbstractMonster m2 = it3.next();
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m2, m2, new MetallicizePower(m2, (AbstractDungeon.actNum * 2) + 2), (AbstractDungeon.actNum * 2) + 2));
                    }
                    break;
                case 3:
                    Iterator<AbstractMonster> it4 = this.monsters.monsters.iterator();
                    while (it4.hasNext()) {
                        AbstractMonster m3 = it4.next();
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m3, m3, new RegenerateMonsterPower(m3, 1 + (AbstractDungeon.actNum * 2)), 1 + (AbstractDungeon.actNum * 2)));
                    }
                    break;
            }
        }
    }

    @Override // com.megacrit.cardcrawl.rooms.MonsterRoom, com.megacrit.cardcrawl.rooms.AbstractRoom
    public void onPlayerEntry() {
        playBGM(null);
        if (this.monsters == null) {
            this.monsters = CardCrawlGame.dungeon.getEliteMonsterForRoomCreation();
            this.monsters.init();
        }
        waitTimer = 0.1f;
    }

    @Override // com.megacrit.cardcrawl.rooms.MonsterRoom, com.megacrit.cardcrawl.rooms.AbstractRoom
    public void dropReward() {
        AbstractRelic.RelicTier tier = returnRandomRelicTier();
        if (Settings.isEndless && AbstractDungeon.player.hasBlight(MimicInfestation.ID)) {
            AbstractDungeon.player.getBlight(MimicInfestation.ID).flash();
            return;
        }
        addRelicToRewards(tier);
        if (AbstractDungeon.player.hasRelic(BlackStar.ID)) {
            addNoncampRelicToRewards(returnRandomRelicTier());
        }
        addEmeraldKey();
    }

    private void addEmeraldKey() {
        if (Settings.isFinalActAvailable && !Settings.hasEmeraldKey && !this.rewards.isEmpty() && AbstractDungeon.getCurrMapNode().hasEmeraldKey) {
            this.rewards.add(new RewardItem(this.rewards.get(this.rewards.size() - 1), RewardItem.RewardType.EMERALD_KEY));
        }
    }

    private AbstractRelic.RelicTier returnRandomRelicTier() {
        int roll = AbstractDungeon.relicRng.random(0, 99);
        if (ModHelper.isModEnabled(BigGameHunter.ID)) {
            roll += 10;
        }
        if (roll < 50) {
            return AbstractRelic.RelicTier.COMMON;
        }
        if (roll > 82) {
            return AbstractRelic.RelicTier.RARE;
        }
        return AbstractRelic.RelicTier.UNCOMMON;
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public AbstractCard.CardRarity getCardRarity(int roll) {
        if (ModHelper.isModEnabled(BigGameHunter.ID)) {
            return AbstractCard.CardRarity.RARE;
        }
        return super.getCardRarity(roll);
    }
}
