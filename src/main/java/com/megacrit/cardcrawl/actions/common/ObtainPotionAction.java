/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.potions.AbstractPotion;
/*    */ 
/*    */ public class ObtainPotionAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractPotion potion;
/* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractPotion");
/* 14 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   public ObtainPotionAction(AbstractPotion potion) {
/* 17 */     this.actionType = ActionType.SPECIAL;
/* 18 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 19 */     this.potion = potion;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == Settings.ACTION_DUR_XFAST) {
/* 25 */       if (AbstractDungeon.player.hasRelic("Sozu")) {
/* 26 */         AbstractDungeon.player.getRelic("Sozu").flash();
/*    */       } else {
/* 28 */         AbstractDungeon.player.obtainPotion(this.potion);
/*    */       } 
/*    */     }
/* 31 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ObtainPotionAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */