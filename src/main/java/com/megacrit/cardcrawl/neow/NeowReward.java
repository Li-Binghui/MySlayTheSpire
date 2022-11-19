/*     */ package com.megacrit.cardcrawl.neow;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.PotionHelper;
/*     */ import com.megacrit.cardcrawl.helpers.SaveHelper;
/*     */ import com.megacrit.cardcrawl.localization.CharacterStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.relics.NeowsLament;
/*     */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*     */ import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
/*     */ import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NeowReward
/*     */ {
/*     */   public static class NeowRewardDef
/*     */   {
/*     */     public NeowRewardType type;
/*     */     public String desc;
/*     */     
/*     */     public NeowRewardDef(NeowRewardType type, String desc) {
/*  47 */       this.type = type;
/*  48 */       this.desc = desc;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class NeowRewardDrawbackDef
/*     */   {
/*     */     public NeowRewardDrawback type;
/*     */     public String desc;
/*     */     
/*     */     public NeowRewardDrawbackDef(NeowRewardDrawback type, String desc) {
/*  58 */       this.type = type;
/*  59 */       this.desc = desc;
/*     */     } }
/*     */   
/*     */   public NeowReward(boolean firstMini) {
/*     */     NeowRewardDef reward;
/*  64 */     this.optionLabel = "";
/*  65 */     this.drawback = NeowRewardDrawback.NONE;
/*  66 */     this.activated = false;
/*  67 */     this.hp_bonus = 0;
/*  68 */     this.cursed = false;
/*  69 */     this.hp_bonus = (int)(AbstractDungeon.player.maxHealth * 0.1F);
/*     */ 
/*     */     
/*  72 */     if (firstMini) {
/*  73 */       reward = new NeowRewardDef(NeowRewardType.THREE_ENEMY_KILL, TEXT[28]);
/*     */     } else {
/*  75 */       reward = new NeowRewardDef(NeowRewardType.TEN_PERCENT_HP_BONUS, TEXT[7] + this.hp_bonus + " ]");
/*     */     } 
/*     */     
/*  78 */     this.optionLabel += reward.desc;
/*  79 */     this.type = reward.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public NeowReward(int category) {
/*  84 */     this.optionLabel = "";
/*  85 */     this.drawback = NeowRewardDrawback.NONE;
/*  86 */     this.activated = false;
/*  87 */     this.hp_bonus = 0;
/*  88 */     this.cursed = false;
/*  89 */     this.hp_bonus = (int)(AbstractDungeon.player.maxHealth * 0.1F);
/*     */ 
/*     */     
/*  92 */     ArrayList<NeowRewardDef> possibleRewards = getRewardOptions(category);
/*     */ 
/*     */     
/*  95 */     NeowRewardDef reward = possibleRewards.get(NeowEvent.rng.random(0, possibleRewards.size() - 1));
/*  96 */     if (this.drawback != NeowRewardDrawback.NONE && this.drawbackDef != null) {
/*  97 */       this.optionLabel += this.drawbackDef.desc;
/*     */     }
/*  99 */     this.optionLabel += reward.desc;
/* 100 */     this.type = reward.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<NeowRewardDrawbackDef> getRewardDrawbackOptions() {
/* 106 */     ArrayList<NeowRewardDrawbackDef> drawbackOptions = new ArrayList<>();
/* 107 */     drawbackOptions.add(new NeowRewardDrawbackDef(NeowRewardDrawback.TEN_PERCENT_HP_LOSS, TEXT[17] + this.hp_bonus + TEXT[18]));
/*     */ 
/*     */ 
/*     */     
/* 111 */     drawbackOptions.add(new NeowRewardDrawbackDef(NeowRewardDrawback.NO_GOLD, TEXT[19]));
/* 112 */     drawbackOptions.add(new NeowRewardDrawbackDef(NeowRewardDrawback.CURSE, TEXT[20]));
/* 113 */     drawbackOptions.add(new NeowRewardDrawbackDef(NeowRewardDrawback.PERCENT_DAMAGE, TEXT[21] + (AbstractDungeon.player.currentHealth / 10 * 3) + TEXT[29] + " "));
/*     */ 
/*     */ 
/*     */     
/* 117 */     return drawbackOptions;
/*     */   }
/*     */ 
/*     */   
/*     */   private ArrayList<NeowRewardDef> getRewardOptions(int category) {
/*     */     ArrayList<NeowRewardDrawbackDef> drawbackOptions;
/* 123 */     ArrayList<NeowRewardDef> rewardOptions = new ArrayList<>();
/* 124 */     switch (category) {
/*     */       case 0:
/* 126 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.THREE_CARDS, TEXT[0]));
/* 127 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.ONE_RANDOM_RARE_CARD, TEXT[1]));
/* 128 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.REMOVE_CARD, TEXT[2]));
/* 129 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.UPGRADE_CARD, TEXT[3]));
/* 130 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.TRANSFORM_CARD, TEXT[4]));
/* 131 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.RANDOM_COLORLESS, TEXT[30]));
/*     */         break;
/*     */       case 1:
/* 134 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.THREE_SMALL_POTIONS, TEXT[5]));
/* 135 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.RANDOM_COMMON_RELIC, TEXT[6]));
/* 136 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.TEN_PERCENT_HP_BONUS, TEXT[7] + this.hp_bonus + " ]"));
/*     */         
/* 138 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.THREE_ENEMY_KILL, TEXT[28]));
/* 139 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.HUNDRED_GOLD, TEXT[8] + 'd' + TEXT[9]));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 146 */         drawbackOptions = getRewardDrawbackOptions();
/* 147 */         this.drawbackDef = drawbackOptions.get(NeowEvent.rng.random(0, drawbackOptions.size() - 1));
/* 148 */         this.drawback = this.drawbackDef.type;
/*     */         
/* 150 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.RANDOM_COLORLESS_2, TEXT[31]));
/* 151 */         if (this.drawback != NeowRewardDrawback.CURSE) {
/* 152 */           rewardOptions.add(new NeowRewardDef(NeowRewardType.REMOVE_TWO, TEXT[10]));
/*     */         }
/* 154 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.ONE_RARE_RELIC, TEXT[11]));
/* 155 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.THREE_RARE_CARDS, TEXT[12]));
/* 156 */         if (this.drawback != NeowRewardDrawback.NO_GOLD) {
/* 157 */           rewardOptions.add(new NeowRewardDef(NeowRewardType.TWO_FIFTY_GOLD, TEXT[13] + 'ú' + TEXT[14]));
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 162 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.TRANSFORM_TWO_CARDS, TEXT[15]));
/* 163 */         if (this.drawback != NeowRewardDrawback.TEN_PERCENT_HP_LOSS) {
/* 164 */           rewardOptions.add(new NeowRewardDef(NeowRewardType.TWENTY_PERCENT_HP_BONUS, TEXT[16] + (this.hp_bonus * 2) + " ]"));
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 171 */         rewardOptions.add(new NeowRewardDef(NeowRewardType.BOSS_RELIC, UNIQUE_REWARDS[0]));
/*     */         break;
/*     */     } 
/* 174 */     return rewardOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
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

/*     */   }
/*     */ 
/*     */   
/*     */   public void activate() {
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

/*     */   }
/*     */   
/*     */   public ArrayList<AbstractCard> getColorlessRewardCards(boolean rareOnly) {
/* 430 */     ArrayList<AbstractCard> retVal = new ArrayList<>();
/* 431 */     for (int numCards = 3, i = 0; i < numCards; i++) {
/* 432 */       AbstractCard.CardRarity rarity = rollRarity();
/* 433 */       if (rareOnly) {
/* 434 */         rarity = AbstractCard.CardRarity.RARE;
/* 435 */       } else if (rarity == AbstractCard.CardRarity.COMMON) {
/* 436 */         rarity = AbstractCard.CardRarity.UNCOMMON;
/*     */       } 
/* 438 */       AbstractCard card = AbstractDungeon.getColorlessCardFromPool(rarity);
/*     */       
/* 440 */       while (retVal.contains(card)) {
/* 441 */         card = AbstractDungeon.getColorlessCardFromPool(rarity);
/*     */       }
/* 443 */       retVal.add(card);
/*     */     } 
/* 445 */     ArrayList<AbstractCard> retVal2 = new ArrayList<>();
/* 446 */     for (AbstractCard c : retVal) {
/* 447 */       retVal2.add(c.makeCopy());
/*     */     }
/* 449 */     return retVal2;
/*     */   }
/*     */   
/*     */   public ArrayList<AbstractCard> getRewardCards(boolean rareOnly) {
/* 453 */     ArrayList<AbstractCard> retVal = new ArrayList<>();
/* 454 */     for (int numCards = 3, i = 0; i < numCards; i++) {
/* 455 */       AbstractCard.CardRarity rarity = rollRarity();
/* 456 */       if (rareOnly) {
/* 457 */         rarity = AbstractCard.CardRarity.RARE;
/*     */       }
/* 459 */       AbstractCard card = null;
/* 460 */       switch (rarity) {
/*     */         case RARE:
/* 462 */           card = getCard(rarity);
/*     */           break;
/*     */         case UNCOMMON:
/* 465 */           card = getCard(rarity);
/*     */           break;
/*     */         case COMMON:
/* 468 */           card = getCard(rarity);
/*     */           break;
/*     */         default:
/* 471 */           logger.info("WTF?");
/*     */           break;
/*     */       } 
/*     */       
/* 475 */       while (retVal.contains(card)) {
/* 476 */         card = getCard(rarity);
/*     */       }
/* 478 */       retVal.add(card);
/*     */     } 
/* 480 */     ArrayList<AbstractCard> retVal2 = new ArrayList<>();
/* 481 */     for (AbstractCard c : retVal) {
/* 482 */       retVal2.add(c.makeCopy());
/*     */     }
/* 484 */     return retVal2;
/*     */   }
/*     */   
/*     */   public AbstractCard.CardRarity rollRarity() {
/* 488 */     if (NeowEvent.rng.randomBoolean(0.33F)) {
/* 489 */       return AbstractCard.CardRarity.UNCOMMON;
/*     */     }
/* 491 */     return AbstractCard.CardRarity.COMMON;
/*     */   }
/*     */   
/*     */   public AbstractCard getCard(AbstractCard.CardRarity rarity) {
/* 495 */     switch (rarity) {
/*     */       case RARE:
/* 497 */         return AbstractDungeon.rareCardPool.getRandomCard(NeowEvent.rng);
/*     */       case UNCOMMON:
/* 499 */         return AbstractDungeon.uncommonCardPool.getRandomCard(NeowEvent.rng);
/*     */       case COMMON:
/* 501 */         return AbstractDungeon.commonCardPool.getRandomCard(NeowEvent.rng);
/*     */     } 
/* 503 */     logger.info("Error in getCard in Neow Reward");
/* 504 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 509 */   private static final Logger logger = LogManager.getLogger(NeowReward.class.getName());
/* 510 */   private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Neow Reward");
/* 511 */   public static final String[] NAMES = characterStrings.NAMES;
/* 512 */   public static final String[] TEXT = characterStrings.TEXT; public String optionLabel; public NeowRewardType type; public NeowRewardDrawback drawback; private boolean activated; private int hp_bonus;
/* 513 */   public static final String[] UNIQUE_REWARDS = characterStrings.UNIQUE_REWARDS; private boolean cursed; private static final int GOLD_BONUS = 100;
/*     */   private static final int LARGE_GOLD_BONUS = 250;
/*     */   private NeowRewardDrawbackDef drawbackDef;
/*     */   
/* 517 */   public enum NeowRewardType { RANDOM_COLORLESS_2, THREE_CARDS, ONE_RANDOM_RARE_CARD, REMOVE_CARD, UPGRADE_CARD, RANDOM_COLORLESS, TRANSFORM_CARD, THREE_SMALL_POTIONS, RANDOM_COMMON_RELIC, TEN_PERCENT_HP_BONUS, HUNDRED_GOLD, THREE_ENEMY_KILL, REMOVE_TWO, TRANSFORM_TWO_CARDS, ONE_RARE_RELIC, THREE_RARE_CARDS, TWO_FIFTY_GOLD, TWENTY_PERCENT_HP_BONUS, BOSS_RELIC; }
/*     */ 
/*     */   
/*     */   public enum NeowRewardDrawback {
/* 521 */     NONE, TEN_PERCENT_HP_LOSS, NO_GOLD, CURSE, PERCENT_DAMAGE;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\neow\NeowReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */