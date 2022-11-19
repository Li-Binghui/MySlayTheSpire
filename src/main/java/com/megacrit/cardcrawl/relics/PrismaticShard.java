/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class PrismaticShard extends AbstractRelic {
/*    */   public static final String ID = "PrismaticShard";
/*    */   
/*    */   public PrismaticShard() {
/* 10 */     super("PrismaticShard", "prism.png", RelicTier.SHOP, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new PrismaticShard();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 25 */     if (AbstractDungeon.player.chosenClass != AbstractPlayer.PlayerClass.DEFECT && AbstractDungeon.player.masterMaxOrbs == 0)
/*    */     {
/* 27 */       AbstractDungeon.player.masterMaxOrbs = 1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\PrismaticShard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */