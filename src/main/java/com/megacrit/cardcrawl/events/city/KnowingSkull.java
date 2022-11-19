/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KnowingSkull
/*     */   extends AbstractImageEvent
/*     */ {
/*  26 */   private static final Logger logger = LogManager.getLogger(KnowingSkull.class.getName());
/*     */   public static final String ID = "Knowing Skull";
/*  28 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Knowing Skull");
/*  29 */   public static final String NAME = eventStrings.NAME;
/*  30 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  31 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  33 */   private static final String INTRO_MSG = DESCRIPTIONS[0];
/*  34 */   private static final String INTRO_2_MSG = DESCRIPTIONS[1];
/*  35 */   private static final String ASK_AGAIN_MSG = DESCRIPTIONS[2];
/*  36 */   private static final String POTION_MSG = DESCRIPTIONS[4];
/*  37 */   private static final String CARD_MSG = DESCRIPTIONS[5];
/*  38 */   private static final String GOLD_MSG = DESCRIPTIONS[6];
/*  39 */   private static final String LEAVE_MSG = DESCRIPTIONS[7];
/*     */   
/*     */   private int potionCost;
/*     */   private int cardCost;
/*  43 */   private CurScreen screen = CurScreen.INTRO_1; private int goldCost; private int leaveCost; private static final int GOLD_REWARD = 90;
/*  44 */   private String optionsChosen = "";
/*     */   
/*     */   private int damageTaken;
/*     */   private int goldEarned;
/*     */   private List<String> potions;
/*     */   private List<String> cards;
/*  50 */   private ArrayList<Reward> options = new ArrayList<>();
/*     */   
/*     */   private enum CurScreen {
/*  53 */     INTRO_1, ASK, COMPLETE;
/*     */   }
/*     */   
/*     */   private enum Reward {
/*  57 */     POTION, LEAVE, GOLD, CARD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KnowingSkull() {
/*  64 */     super(NAME, INTRO_MSG, "images/events/knowingSkull.jpg");
/*  65 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*  66 */     this.options.add(Reward.CARD);
/*  67 */     this.options.add(Reward.GOLD);
/*  68 */     this.options.add(Reward.POTION);
/*  69 */     this.options.add(Reward.LEAVE);
/*     */     
/*  71 */     this.leaveCost = 6;
/*  72 */     this.cardCost = this.leaveCost;
/*  73 */     this.potionCost = this.leaveCost;
/*  74 */     this.goldCost = this.leaveCost;
/*     */     
/*  76 */     this.damageTaken = 0;
/*  77 */     this.goldEarned = 0;
/*  78 */     this.cards = new ArrayList<>();
/*  79 */     this.potions = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  84 */     if (Settings.AMBIANCE_ON) {
/*  85 */       CardCrawlGame.sound.play("EVENT_SKULL");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  91 */     switch (this.screen) {
/*     */       case INTRO_1:
/*  93 */         this.imageEventText.updateBodyText(INTRO_2_MSG);
/*  94 */         this.imageEventText.clearAllDialogs();
/*  95 */         this.imageEventText.setDialogOption(OPTIONS[4] + this.potionCost + OPTIONS[1]);
/*  96 */         this.imageEventText.setDialogOption(OPTIONS[5] + 'Z' + OPTIONS[6] + this.goldCost + OPTIONS[1]);
/*  97 */         this.imageEventText.setDialogOption(OPTIONS[3] + this.cardCost + OPTIONS[1]);
/*  98 */         this.imageEventText.setDialogOption(OPTIONS[7] + this.leaveCost + OPTIONS[1]);
/*  99 */         this.screen = CurScreen.ASK;
/*     */         break;
/*     */       case ASK:
/* 102 */         CardCrawlGame.sound.play("DEBUFF_2");
/* 103 */         switch (buttonPressed) {
/*     */           case 0:
/* 105 */             obtainReward(0);
/*     */             break;
/*     */           case 1:
/* 108 */             obtainReward(1);
/*     */             break;
/*     */           case 2:
/* 111 */             obtainReward(2);
/*     */             break;
/*     */         } 
/* 114 */         AbstractDungeon.player.damage(new DamageInfo(null, this.leaveCost, DamageInfo.DamageType.HP_LOSS));
/* 115 */         this.damageTaken += this.leaveCost;
/* 116 */         setLeave();
/*     */         break;
/*     */ 
/*     */       
/*     */       case COMPLETE:
/* 121 */         logMetric("Knowing Skull", this.optionsChosen, this.cards, null, null, null, null, this.potions, null, this.damageTaken, 0, 0, 0, this.goldEarned, 0);
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
/* 137 */         openMap();
/*     */         break;
/*     */     } 
/*     */   } private void obtainReward(int slot) {
/*     */     AbstractPotion p;
/*     */     AbstractCard c;
/* 143 */     switch (slot) {
/*     */       case 0:
/* 145 */         AbstractDungeon.player.damage(new DamageInfo(null, this.potionCost, DamageInfo.DamageType.HP_LOSS));
/* 146 */         this.damageTaken += this.potionCost;
/* 147 */         this.potionCost++;
/* 148 */         this.optionsChosen += "POTION ";
/* 149 */         this.imageEventText.updateBodyText(POTION_MSG + ASK_AGAIN_MSG);
/* 150 */         if (AbstractDungeon.player.hasRelic("Sozu")) {
/* 151 */           AbstractDungeon.player.getRelic("Sozu").flash(); break;
/*     */         } 
/* 153 */         p = PotionHelper.getRandomPotion();
/* 154 */         this.potions.add(p.ID);
/* 155 */         AbstractDungeon.player.obtainPotion(p);
/*     */         break;
/*     */       
/*     */       case 1:
/* 159 */         AbstractDungeon.player.damage(new DamageInfo(null, this.goldCost, DamageInfo.DamageType.HP_LOSS));
/* 160 */         this.damageTaken += this.goldCost;
/* 161 */         this.goldCost++;
/* 162 */         this.optionsChosen += "GOLD ";
/* 163 */         this.imageEventText.updateBodyText(GOLD_MSG + ASK_AGAIN_MSG);
/* 164 */         AbstractDungeon.effectList.add(new RainingGoldEffect(90));
/* 165 */         AbstractDungeon.player.gainGold(90);
/* 166 */         this.goldEarned += 90;
/*     */         break;
/*     */       case 2:
/* 169 */         AbstractDungeon.player.damage(new DamageInfo(null, this.cardCost, DamageInfo.DamageType.HP_LOSS));
/* 170 */         this.damageTaken += this.cardCost;
/* 171 */         this.cardCost++;
/* 172 */         this.optionsChosen += "CARD ";
/* 173 */         this.imageEventText.updateBodyText(CARD_MSG + ASK_AGAIN_MSG);
/* 174 */         c = AbstractDungeon.returnColorlessCard(AbstractCard.CardRarity.UNCOMMON).makeCopy();
/* 175 */         this.cards.add(c.cardID);
/* 176 */         AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */         break;
/*     */       
/*     */       default:
/* 180 */         logger.info("This should never happen.");
/*     */         break;
/*     */     } 
/* 183 */     this.imageEventText.clearAllDialogs();
/* 184 */     this.imageEventText.setDialogOption(OPTIONS[4] + this.potionCost + OPTIONS[1]);
/* 185 */     this.imageEventText.setDialogOption(OPTIONS[5] + 'Z' + OPTIONS[6] + this.goldCost + OPTIONS[1]);
/* 186 */     this.imageEventText.setDialogOption(OPTIONS[3] + this.cardCost + OPTIONS[1]);
/* 187 */     this.imageEventText.setDialogOption(OPTIONS[7] + this.leaveCost + OPTIONS[1]);
/*     */   }
/*     */   
/*     */   private void setLeave() {
/* 191 */     this.imageEventText.updateBodyText(LEAVE_MSG);
/* 192 */     this.imageEventText.clearAllDialogs();
/* 193 */     this.imageEventText.setDialogOption(OPTIONS[8]);
/* 194 */     this.screen = CurScreen.COMPLETE;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\KnowingSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */