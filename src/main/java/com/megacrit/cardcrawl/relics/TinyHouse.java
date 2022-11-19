/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PotionHelper;
/*    */ import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class TinyHouse extends AbstractRelic {
/*    */   public static final String ID = "Tiny House";
/*    */   private static final int GOLD_AMT = 50;
/*    */   private static final int HP_AMT = 5;
/*    */   
/*    */   public TinyHouse() {
/* 19 */     super("Tiny House", "tinyHouse.png", RelicTier.BOSS, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0] + '2' + this.DESCRIPTIONS[1] + '\005' + this.DESCRIPTIONS[2];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 30 */     ArrayList<AbstractCard> upgradableCards = new ArrayList<>();
/* 31 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 32 */       if (c.canUpgrade()) {
/* 33 */         upgradableCards.add(c);
/*    */       }
/*    */     } 
/* 36 */     Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
/*    */     
/* 38 */     if (!upgradableCards.isEmpty())
/*    */     {
/* 40 */       if (upgradableCards.size() == 1) {
/* 41 */         ((AbstractCard)upgradableCards.get(0)).upgrade();
/* 42 */         AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
/* 43 */         AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(((AbstractCard)upgradableCards
/* 44 */               .get(0)).makeStatEquivalentCopy()));
/* 45 */         AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */       } else {
/* 47 */         ((AbstractCard)upgradableCards.get(0)).upgrade();
/* 48 */         AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
/* 49 */         AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
/* 50 */         AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(((AbstractCard)upgradableCards
/*    */               
/* 52 */               .get(0)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */ 
/*    */         
/* 55 */         AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */       } 
/*    */     }
/*    */     
/* 59 */     AbstractDungeon.player.increaseMaxHp(5, true);
/*    */ 
/*    */     
/* 62 */     AbstractDungeon.getCurrRoom().addGoldToRewards(50);
/* 63 */     AbstractDungeon.getCurrRoom().addPotionToRewards(PotionHelper.getRandomPotion(AbstractDungeon.miscRng));
/* 64 */     AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[3]);
/* 65 */     (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 70 */     return new TinyHouse();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\TinyHouse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */