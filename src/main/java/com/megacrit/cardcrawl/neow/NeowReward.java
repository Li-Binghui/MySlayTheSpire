package com.megacrit.cardcrawl.neow;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.NeowsLament;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/neow/NeowReward.class */
public class NeowReward {
    private static final Logger logger = LogManager.getLogger(NeowReward.class.getName());
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Neow Reward");
    public static final String[] NAMES = characterStrings.NAMES;
    public static final String[] TEXT = characterStrings.TEXT;
    public static final String[] UNIQUE_REWARDS = characterStrings.UNIQUE_REWARDS;
    public NeowRewardType type;
    private int hp_bonus;
    private static final int GOLD_BONUS = 100;
    private static final int LARGE_GOLD_BONUS = 250;
    private NeowRewardDrawbackDef drawbackDef;
    public String optionLabel = "";
    public NeowRewardDrawback drawback = NeowRewardDrawback.NONE;
    private boolean activated = false;
    private boolean cursed = false;

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/neow/NeowReward$NeowRewardDrawback.class */
    public enum NeowRewardDrawback {
        NONE,
        TEN_PERCENT_HP_LOSS,
        NO_GOLD,
        CURSE,
        PERCENT_DAMAGE
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/neow/NeowReward$NeowRewardType.class */
    public enum NeowRewardType {
        RANDOM_COLORLESS_2,
        THREE_CARDS,
        ONE_RANDOM_RARE_CARD,
        REMOVE_CARD,
        UPGRADE_CARD,
        RANDOM_COLORLESS,
        TRANSFORM_CARD,
        THREE_SMALL_POTIONS,
        RANDOM_COMMON_RELIC,
        TEN_PERCENT_HP_BONUS,
        HUNDRED_GOLD,
        THREE_ENEMY_KILL,
        REMOVE_TWO,
        TRANSFORM_TWO_CARDS,
        ONE_RARE_RELIC,
        THREE_RARE_CARDS,
        TWO_FIFTY_GOLD,
        TWENTY_PERCENT_HP_BONUS,
        BOSS_RELIC
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/neow/NeowReward$NeowRewardDef.class */
    public static class NeowRewardDef {
        public NeowRewardType type;
        public String desc;

        public NeowRewardDef(NeowRewardType type, String desc) {
            this.type = type;
            this.desc = desc;
        }
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/neow/NeowReward$NeowRewardDrawbackDef.class */
    public static class NeowRewardDrawbackDef {
        public NeowRewardDrawback type;
        public String desc;

        public NeowRewardDrawbackDef(NeowRewardDrawback type, String desc) {
            this.type = type;
            this.desc = desc;
        }
    }

    public NeowReward(boolean firstMini) {
        NeowRewardDef reward;
        this.hp_bonus = 0;
        this.hp_bonus = (int) (AbstractDungeon.player.maxHealth * 0.1f);
        if (firstMini) {
            reward = new NeowRewardDef(NeowRewardType.THREE_ENEMY_KILL, TEXT[28]);
        } else {
            reward = new NeowRewardDef(NeowRewardType.TEN_PERCENT_HP_BONUS, TEXT[7] + this.hp_bonus + " ]");
        }
        this.optionLabel += reward.desc;
        this.type = reward.type;
    }

    public NeowReward(int category) {
        this.hp_bonus = 0;
        this.hp_bonus = (int) (AbstractDungeon.player.maxHealth * 0.1f);
        ArrayList<NeowRewardDef> possibleRewards = getRewardOptions(category);
        NeowRewardDef reward = possibleRewards.get(NeowEvent.rng.random(0, possibleRewards.size() - 1));
        if (!(this.drawback == NeowRewardDrawback.NONE || this.drawbackDef == null)) {
            this.optionLabel += this.drawbackDef.desc;
        }
        this.optionLabel += reward.desc;
        this.type = reward.type;
    }

    private ArrayList<NeowRewardDrawbackDef> getRewardDrawbackOptions() {
        ArrayList<NeowRewardDrawbackDef> drawbackOptions = new ArrayList<>();
        drawbackOptions.add(new NeowRewardDrawbackDef(NeowRewardDrawback.TEN_PERCENT_HP_LOSS, TEXT[17] + this.hp_bonus + TEXT[18]));
        drawbackOptions.add(new NeowRewardDrawbackDef(NeowRewardDrawback.NO_GOLD, TEXT[19]));
        drawbackOptions.add(new NeowRewardDrawbackDef(NeowRewardDrawback.CURSE, TEXT[20]));
        drawbackOptions.add(new NeowRewardDrawbackDef(NeowRewardDrawback.PERCENT_DAMAGE, TEXT[21] + ((AbstractDungeon.player.currentHealth / 10) * 3) + TEXT[29] + " "));
        return drawbackOptions;
    }

    private ArrayList<NeowRewardDef> getRewardOptions(int category) {
        ArrayList<NeowRewardDef> rewardOptions = new ArrayList<>();
        switch (category) {
            case 0:
                rewardOptions.add(new NeowRewardDef(NeowRewardType.THREE_CARDS, TEXT[0]));
                rewardOptions.add(new NeowRewardDef(NeowRewardType.ONE_RANDOM_RARE_CARD, TEXT[1]));
                rewardOptions.add(new NeowRewardDef(NeowRewardType.REMOVE_CARD, TEXT[2]));
                rewardOptions.add(new NeowRewardDef(NeowRewardType.UPGRADE_CARD, TEXT[3]));
                rewardOptions.add(new NeowRewardDef(NeowRewardType.TRANSFORM_CARD, TEXT[4]));
                rewardOptions.add(new NeowRewardDef(NeowRewardType.RANDOM_COLORLESS, TEXT[30]));
                break;
            case 1:
                rewardOptions.add(new NeowRewardDef(NeowRewardType.THREE_SMALL_POTIONS, TEXT[5]));
                rewardOptions.add(new NeowRewardDef(NeowRewardType.RANDOM_COMMON_RELIC, TEXT[6]));
                rewardOptions.add(new NeowRewardDef(NeowRewardType.TEN_PERCENT_HP_BONUS, TEXT[7] + this.hp_bonus + " ]"));
                rewardOptions.add(new NeowRewardDef(NeowRewardType.THREE_ENEMY_KILL, TEXT[28]));
                rewardOptions.add(new NeowRewardDef(NeowRewardType.HUNDRED_GOLD, TEXT[8] + 100 + TEXT[9]));
                break;
            case 2:
                ArrayList<NeowRewardDrawbackDef> drawbackOptions = getRewardDrawbackOptions();
                this.drawbackDef = drawbackOptions.get(NeowEvent.rng.random(0, drawbackOptions.size() - 1));
                this.drawback = this.drawbackDef.type;
                rewardOptions.add(new NeowRewardDef(NeowRewardType.RANDOM_COLORLESS_2, TEXT[31]));
                if (this.drawback != NeowRewardDrawback.CURSE) {
                    rewardOptions.add(new NeowRewardDef(NeowRewardType.REMOVE_TWO, TEXT[10]));
                }
                rewardOptions.add(new NeowRewardDef(NeowRewardType.ONE_RARE_RELIC, TEXT[11]));
                rewardOptions.add(new NeowRewardDef(NeowRewardType.THREE_RARE_CARDS, TEXT[12]));
                if (this.drawback != NeowRewardDrawback.NO_GOLD) {
                    rewardOptions.add(new NeowRewardDef(NeowRewardType.TWO_FIFTY_GOLD, TEXT[13] + 250 + TEXT[14]));
                }
                rewardOptions.add(new NeowRewardDef(NeowRewardType.TRANSFORM_TWO_CARDS, TEXT[15]));
                if (this.drawback != NeowRewardDrawback.TEN_PERCENT_HP_LOSS) {
                    rewardOptions.add(new NeowRewardDef(NeowRewardType.TWENTY_PERCENT_HP_BONUS, TEXT[16] + (this.hp_bonus * 2) + " ]"));
                    break;
                }
                break;
            case 3:
                rewardOptions.add(new NeowRewardDef(NeowRewardType.BOSS_RELIC, UNIQUE_REWARDS[0]));
                break;
        }
        return rewardOptions;
    }

    public void update() {
        if (this.activated) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                switch (this.type) {
                    case UPGRADE_CARD:
                        AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        c.upgrade();
                        AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));
                        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                        break;
                    case REMOVE_CARD:
                        CardCrawlGame.sound.play("CARD_EXHAUST");
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(AbstractDungeon.gridSelectScreen.selectedCards.get(0), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        AbstractDungeon.player.masterDeck.removeCard(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
                        break;
                    case REMOVE_TWO:
                        CardCrawlGame.sound.play("CARD_EXHAUST");
                        AbstractCard c2 = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        AbstractCard c3 = AbstractDungeon.gridSelectScreen.selectedCards.get(1);
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c2, ((Settings.WIDTH / 2.0f) - (AbstractCard.IMG_WIDTH / 2.0f)) - (30.0f * Settings.scale), Settings.HEIGHT / 2));
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c3, (Settings.WIDTH / 2.0f) + (AbstractCard.IMG_WIDTH / 2.0f) + (30.0f * Settings.scale), Settings.HEIGHT / 2.0f));
                        AbstractDungeon.player.masterDeck.removeCard(c2);
                        AbstractDungeon.player.masterDeck.removeCard(c3);
                        break;
                    case TRANSFORM_CARD:
                        AbstractDungeon.transformCard(AbstractDungeon.gridSelectScreen.selectedCards.get(0), false, NeowEvent.rng);
                        AbstractDungeon.player.masterDeck.removeCard(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
                        AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(AbstractDungeon.getTransformedCard(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                        break;
                    case TRANSFORM_TWO_CARDS:
                        AbstractCard t1 = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        AbstractCard t2 = AbstractDungeon.gridSelectScreen.selectedCards.get(1);
                        AbstractDungeon.player.masterDeck.removeCard(t1);
                        AbstractDungeon.player.masterDeck.removeCard(t2);
                        AbstractDungeon.transformCard(t1, false, NeowEvent.rng);
                        AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(AbstractDungeon.getTransformedCard(), ((Settings.WIDTH / 2.0f) - (AbstractCard.IMG_WIDTH / 2.0f)) - (30.0f * Settings.scale), Settings.HEIGHT / 2.0f));
                        AbstractDungeon.transformCard(t2, false, NeowEvent.rng);
                        AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(AbstractDungeon.getTransformedCard(), (Settings.WIDTH / 2.0f) + (AbstractCard.IMG_WIDTH / 2.0f) + (30.0f * Settings.scale), Settings.HEIGHT / 2.0f));
                        break;
                    default:
                        logger.info("[ERROR] Missing Neow Reward Type: " + this.type.name());
                        break;
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.overlayMenu.cancelButton.hide();
                SaveHelper.saveIfAppropriate(SaveFile.SaveType.POST_NEOW);
                this.activated = false;
            }
            if (this.cursed) {
                this.cursed = !this.cursed;
                AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(AbstractDungeon.getCardWithoutRng(AbstractCard.CardRarity.CURSE), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
            }
        }
    }

    public void activate() {
        this.activated = true;
        switch (this.drawback) {
            case CURSE:
                this.cursed = true;
                break;
            case NO_GOLD:
                AbstractDungeon.player.loseGold(AbstractDungeon.player.gold);
                break;
            case TEN_PERCENT_HP_LOSS:
                AbstractDungeon.player.decreaseMaxHealth(this.hp_bonus);
                break;
            case PERCENT_DAMAGE:
                AbstractDungeon.player.damage(new DamageInfo(null, (AbstractDungeon.player.currentHealth / 10) * 3, DamageInfo.DamageType.HP_LOSS));
                break;
            default:
                logger.info("[ERROR] Missing Neow Reward Drawback: " + this.drawback.name());
                break;
        }
        switch (this.type) {
            case UPGRADE_CARD:
                AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getUpgradableCards(), 1, TEXT[27], true, false, false, false);
                break;
            case REMOVE_CARD:
                AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), 1, TEXT[23], false, false, false, true);
                break;
            case REMOVE_TWO:
                AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), 2, TEXT[24], false, false, false, false);
                break;
            case TRANSFORM_CARD:
                AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), 1, TEXT[25], false, true, false, false);
                break;
            case TRANSFORM_TWO_CARDS:
                AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), 2, TEXT[26], false, false, false, false);
                break;
            case RANDOM_COLORLESS_2:
                AbstractDungeon.cardRewardScreen.open(getColorlessRewardCards(true), null, CardCrawlGame.languagePack.getUIString("CardRewardScreen").TEXT[1]);
                break;
            case RANDOM_COLORLESS:
                AbstractDungeon.cardRewardScreen.open(getColorlessRewardCards(false), null, CardCrawlGame.languagePack.getUIString("CardRewardScreen").TEXT[1]);
                break;
            case THREE_RARE_CARDS:
                AbstractDungeon.cardRewardScreen.open(getRewardCards(true), null, TEXT[22]);
                break;
            case HUNDRED_GOLD:
                CardCrawlGame.sound.play("GOLD_JINGLE");
                AbstractDungeon.player.gainGold(100);
                break;
            case ONE_RANDOM_RARE_CARD:
                AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(AbstractDungeon.getCard(AbstractCard.CardRarity.RARE, NeowEvent.rng).makeCopy(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                break;
            case RANDOM_COMMON_RELIC:
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON));
                break;
            case ONE_RARE_RELIC:
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.RARE));
                break;
            case BOSS_RELIC:
                AbstractDungeon.player.loseRelic(AbstractDungeon.player.relics.get(0).relicId);
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.BOSS));
                break;
            case THREE_ENEMY_KILL:
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, new NeowsLament());
                break;
            case TEN_PERCENT_HP_BONUS:
                AbstractDungeon.player.increaseMaxHp(this.hp_bonus, true);
                break;
            case THREE_CARDS:
                AbstractDungeon.cardRewardScreen.open(getRewardCards(false), null, CardCrawlGame.languagePack.getUIString("CardRewardScreen").TEXT[1]);
                break;
            case THREE_SMALL_POTIONS:
                CardCrawlGame.sound.play("POTION_1");
                for (int i = 0; i < 3; i++) {
                    AbstractDungeon.getCurrRoom().addPotionToRewards(PotionHelper.getRandomPotion());
                }
                AbstractDungeon.combatRewardScreen.open();
                AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0f;
                int remove = -1;
                int j = 0;
                while (true) {
                    if (j < AbstractDungeon.combatRewardScreen.rewards.size()) {
                        if (AbstractDungeon.combatRewardScreen.rewards.get(j).type == RewardItem.RewardType.CARD) {
                            remove = j;
                        } else {
                            j++;
                        }
                    }
                    if (remove != -1) {
                        AbstractDungeon.combatRewardScreen.rewards.remove(remove);
                        break;
                    }
                }

                break;
            case TWENTY_PERCENT_HP_BONUS:
                AbstractDungeon.player.increaseMaxHp(this.hp_bonus * 2, true);
                break;
            case TWO_FIFTY_GOLD:
                CardCrawlGame.sound.play("GOLD_JINGLE");
                AbstractDungeon.player.gainGold(250);
                break;
        }
        CardCrawlGame.metricData.addNeowData(this.type.name(), this.drawback.name());
    }

    public ArrayList<AbstractCard> getColorlessRewardCards(boolean rareOnly) {
//        AbstractCard card;
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AbstractCard.CardRarity rarity = rollRarity();
            if (rareOnly) {
                rarity = AbstractCard.CardRarity.RARE;
            } else if (rarity == AbstractCard.CardRarity.COMMON) {
                rarity = AbstractCard.CardRarity.UNCOMMON;
            }
            AbstractCard card = AbstractDungeon.getColorlessCardFromPool(rarity);
            while (retVal.contains(card)) {
                card = AbstractDungeon.getColorlessCardFromPool(rarity);
            }
            retVal.add(card);

        }
        ArrayList<AbstractCard> retVal2 = new ArrayList<>();
        Iterator<AbstractCard> it = retVal.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            retVal2.add(c.makeCopy());
        }
        return retVal2;
    }

    public ArrayList<AbstractCard> getRewardCards(boolean rareOnly) {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AbstractCard.CardRarity rarity = rollRarity();
            if (rareOnly) {
                rarity = AbstractCard.CardRarity.RARE;
            }
            AbstractCard card = null;
            switch (rarity) {
                case RARE:
                    card = getCard(rarity);
                    break;
                case UNCOMMON:
                    card = getCard(rarity);
                    break;
                case COMMON:
                    card = getCard(rarity);
                    break;
                default:
                    logger.info("WTF?");
                    break;
            }
            while (retVal.contains(card)) {
                card = getCard(rarity);
            }
            retVal.add(card);
        }
        ArrayList<AbstractCard> retVal2 = new ArrayList<>();
        Iterator<AbstractCard> it = retVal.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            retVal2.add(c.makeCopy());
        }
        return retVal2;
    }

    public AbstractCard.CardRarity rollRarity() {
        if (NeowEvent.rng.randomBoolean(0.33f)) {
            return AbstractCard.CardRarity.UNCOMMON;
        }
        return AbstractCard.CardRarity.COMMON;
    }

    public AbstractCard getCard(AbstractCard.CardRarity rarity) {
        switch (rarity) {
            case RARE:
                return AbstractDungeon.rareCardPool.getRandomCard(NeowEvent.rng);
            case UNCOMMON:
                return AbstractDungeon.uncommonCardPool.getRandomCard(NeowEvent.rng);
            case COMMON:
                return AbstractDungeon.commonCardPool.getRandomCard(NeowEvent.rng);
            default:
                logger.info("Error in getCard in Neow Reward");
                return null;
        }
    }
}