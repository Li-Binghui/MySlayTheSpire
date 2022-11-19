/*    */ package com.megacrit.cardcrawl.cards.red;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
/*    */ 
/*    */ public class Warcry extends AbstractCard {
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Warcry"); public static final String ID = "Warcry";
/*    */   
/*    */   public Warcry() {
/* 20 */     super("Warcry", cardStrings.NAME, "red/skill/warcry", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.RED, CardRarity.COMMON, CardTarget.SELF);
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
/* 31 */     this.exhaust = true;
/* 32 */     this.baseMagicNumber = 1;
/* 33 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.RED_TEXT_COLOR, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.5F));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
/* 44 */     addToBot((AbstractGameAction)new PutOnDeckAction((AbstractCreature)p, (AbstractCreature)p, 1, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 49 */     if (!this.upgraded) {
/* 50 */       upgradeName();
/* 51 */       upgradeMagicNumber(1);
/* 52 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 53 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 59 */     return new Warcry();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\Warcry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */