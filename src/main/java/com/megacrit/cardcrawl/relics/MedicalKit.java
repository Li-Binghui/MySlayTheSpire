/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class MedicalKit extends AbstractRelic {
/*    */   public static final String ID = "Medical Kit";
/*    */   
/*    */   public MedicalKit() {
/* 11 */     super("Medical Kit", "medicalKit.png", RelicTier.SHOP, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 21 */     return new MedicalKit();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 26 */     if (card.type == AbstractCard.CardType.STATUS) {
/* 27 */       AbstractDungeon.player.getRelic("Medical Kit").flash();
/* 28 */       card.exhaust = true;
/* 29 */       action.exhaustCard = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\MedicalKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */