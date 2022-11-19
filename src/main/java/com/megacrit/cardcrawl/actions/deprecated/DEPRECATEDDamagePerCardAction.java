/*    */ package com.megacrit.cardcrawl.actions.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class DEPRECATEDDamagePerCardAction
/*    */   extends AbstractGameAction {
/* 14 */   private static final Logger logger = LogManager.getLogger(DEPRECATEDDamagePerCardAction.class.getName());
/*    */ 
/*    */   
/*    */   private DamageInfo info;
/*    */   
/*    */   private String cardName;
/*    */ 
/*    */   
/*    */   public DEPRECATEDDamagePerCardAction(AbstractCreature target, DamageInfo info, String cardName, AttackEffect effect) {
/* 23 */     this.info = info;
/* 24 */     this.cardName = cardName;
/* 25 */     this.attackEffect = effect;
/*    */     
/* 27 */     setValues(target, info);
/* 28 */     this.actionType = ActionType.DAMAGE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 33 */     if (!this.isDone) {
/* 34 */       this.isDone = true;
/* 35 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 36 */         if (c.originalName.equals(this.cardName)) {
/* 37 */           logger.info("QUEUED DAMAGE...");
/* 38 */           AbstractDungeon.actionManager.addToTop((AbstractGameAction)new DamageAction(this.target, this.info, this.attackEffect));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\deprecated\DEPRECATEDDamagePerCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */