/*    */ package com.megacrit.cardcrawl.cards.green;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class Eviscerate extends AbstractCard {
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Eviscerate"); public static final String ID = "Eviscerate";
/*    */   
/*    */   public Eviscerate() {
/* 20 */     super("Eviscerate", cardStrings.NAME, "green/attack/eviscerate", 3, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.GREEN, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 31 */     this.baseDamage = 7;
/*    */   }
/*    */ 
/*    */   
/*    */   public void didDiscard() {
/* 36 */     setCostForTurn(this.costForTurn - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerWhenDrawn() {
/* 41 */     super.triggerWhenDrawn();
/* 42 */     setCostForTurn(this.cost - GameActionManager.totalDiscardedThisTurn);
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 47 */     resetAttributes();
/* 48 */     applyPowers();
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 53 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/* 54 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/* 55 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 60 */     if (!this.upgraded) {
/* 61 */       upgradeName();
/* 62 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 68 */     AbstractCard tmp = new Eviscerate();
/* 69 */     if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null && 
/* 70 */       (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 71 */       setCostForTurn(this.cost - GameActionManager.totalDiscardedThisTurn);
/*    */     }
/* 73 */     return tmp;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\green\Eviscerate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */