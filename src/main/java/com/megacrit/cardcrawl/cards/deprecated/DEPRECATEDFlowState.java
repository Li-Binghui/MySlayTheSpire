/*    */ package com.megacrit.cardcrawl.cards.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.EmotionalTurmoilAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class DEPRECATEDFlowState extends AbstractCard {
/* 12 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("FlowState"); public static final String ID = "FlowState";
/*    */   
/*    */   public DEPRECATEDFlowState() {
/* 15 */     super("FlowState", cardStrings.NAME, "purple/skill/flow_state", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 26 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 31 */     addToBot((AbstractGameAction)new EmotionalTurmoilAction());
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 36 */     if (!this.upgraded) {
/* 37 */       upgradeName();
/* 38 */       this.exhaust = false;
/* 39 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 40 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 46 */     return new DEPRECATEDFlowState();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\deprecated\DEPRECATEDFlowState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */