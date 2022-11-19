/*    */ package com.megacrit.cardcrawl.cards.blue;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.ChannelAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.orbs.Dark;
/*    */ import com.megacrit.cardcrawl.orbs.Frost;
/*    */ import com.megacrit.cardcrawl.orbs.Lightning;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
/*    */ 
/*    */ public class Rainbow extends AbstractCard {
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Rainbow"); public static final String ID = "Rainbow";
/*    */   
/*    */   public Rainbow() {
/* 20 */     super("Rainbow", cardStrings.NAME, "blue/skill/rainbow", 2, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.RARE, CardTarget.SELF);
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
/* 31 */     this.showEvokeValue = true;
/* 32 */     this.showEvokeOrbCount = 3;
/* 33 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new RainbowCardEffect()));
/* 39 */     addToBot((AbstractGameAction)new ChannelAction((AbstractOrb)new Lightning()));
/* 40 */     addToBot((AbstractGameAction)new ChannelAction((AbstractOrb)new Frost()));
/* 41 */     addToBot((AbstractGameAction)new ChannelAction((AbstractOrb)new Dark()));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 46 */     return new Rainbow();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeName();
/* 53 */       this.exhaust = false;
/* 54 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 55 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\blue\Rainbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */