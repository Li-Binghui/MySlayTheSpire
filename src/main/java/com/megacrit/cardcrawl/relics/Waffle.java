/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class Waffle
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Lee's Waffle";
/*    */   private static final int HP_AMT = 7;
/*    */   
/*    */   public Waffle() {
/* 11 */     super("Lee's Waffle", "waffle.png", RelicTier.SHOP, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0] + '\007' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 21 */     AbstractDungeon.player.increaseMaxHp(7, false);
/* 22 */     AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 27 */     return new Waffle();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Waffle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */