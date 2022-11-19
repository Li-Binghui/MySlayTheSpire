/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.screens.CardRewardScreen;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class CodexAction
/*    */   extends AbstractGameAction {
/*    */   public static int numPlaced;
/*    */   private boolean retrieveCard = false;
/*    */   
/*    */   public CodexAction() {
/* 17 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 18 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 24 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 28 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 29 */       AbstractDungeon.cardRewardScreen.customCombatOpen(generateCardChoices(), CardRewardScreen.TEXT[1], true);
/* 30 */       tickDuration();
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     if (!this.retrieveCard) {
/* 35 */       if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
/* 36 */         AbstractCard codexCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
/* 37 */         codexCard.current_x = -1000.0F * Settings.xScale;
/* 38 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(codexCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, true));
/*    */         
/* 40 */         AbstractDungeon.cardRewardScreen.discoveryCard = null;
/*    */       } 
/* 42 */       this.retrieveCard = true;
/*    */     } 
/*    */     
/* 45 */     tickDuration();
/*    */   }
/*    */   
/*    */   private ArrayList<AbstractCard> generateCardChoices() {
/* 49 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*    */     
/* 51 */     while (derp.size() != 3) {
/* 52 */       boolean dupe = false;
/* 53 */       AbstractCard tmp = AbstractDungeon.returnTrulyRandomCardInCombat();
/* 54 */       for (AbstractCard c : derp) {
/* 55 */         if (c.cardID.equals(tmp.cardID)) {
/* 56 */           dupe = true;
/*    */           break;
/*    */         } 
/*    */       } 
/* 60 */       if (!dupe) {
/* 61 */         derp.add(tmp.makeCopy());
/*    */       }
/*    */     } 
/*    */     
/* 65 */     return derp;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\CodexAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */