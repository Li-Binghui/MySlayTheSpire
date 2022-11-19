/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ 
/*    */ public class MoltenEgg2 extends AbstractRelic {
/*    */   public static final String ID = "Molten Egg 2";
/*    */   
/*    */   public MoltenEgg2() {
/* 12 */     super("Molten Egg 2", "stoneEgg.png", RelicTier.UNCOMMON, LandingSound.SOLID);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 22 */     for (RewardItem reward : AbstractDungeon.combatRewardScreen.rewards) {
/* 23 */       if (reward.cards != null) {
/* 24 */         for (AbstractCard c : reward.cards) {
/* 25 */           onPreviewObtainCard(c);
/*    */         }
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPreviewObtainCard(AbstractCard c) {
/* 33 */     onObtainCard(c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onObtainCard(AbstractCard c) {
/* 38 */     if (c.type == AbstractCard.CardType.ATTACK && c.canUpgrade() && !c.upgraded) {
/* 39 */       c.upgrade();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 45 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 50 */     return new MoltenEgg2();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\MoltenEgg2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */