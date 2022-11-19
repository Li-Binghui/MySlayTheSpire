/*    */ package com.megacrit.cardcrawl.blights;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.BlightStrings;
/*    */ import com.megacrit.cardcrawl.random.Random;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ 
/*    */ public class Accursed extends AbstractBlight {
/*    */   public static final String ID = "Accursed";
/* 14 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("Accursed");
/* 15 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   
/*    */   public Accursed() {
/* 18 */     super("Accursed", NAME, DESC[0] + '\002' + DESC[1], "accursed.png", false);
/* 19 */     this.counter = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stack() {
/* 24 */     this.counter += 2;
/* 25 */     updateDescription();
/* 26 */     flash();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 31 */     this.description = DESC[0] + this.counter + DESC[1];
/* 32 */     this.tips.clear();
/* 33 */     this.tips.add(new PowerTip(this.name, this.description));
/* 34 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBossDefeat() {
/* 39 */     flash();
/*    */     
/* 41 */     Random posRandom = new Random();
/* 42 */     for (int i = 0; i < this.counter; i++)
/* 43 */       AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(
/*    */             
/* 45 */             AbstractDungeon.getCardWithoutRng(AbstractCard.CardRarity.CURSE), Settings.WIDTH / 2.0F + posRandom
/* 46 */             .random(-(Settings.WIDTH / 3.0F), Settings.WIDTH / 3.0F), Settings.HEIGHT / 2.0F + posRandom
/* 47 */             .random(-(Settings.HEIGHT / 3.0F), Settings.HEIGHT / 3.0F))); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\Accursed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */