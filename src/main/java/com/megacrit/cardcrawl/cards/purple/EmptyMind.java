/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
/*    */ 
/*    */ public class EmptyMind extends AbstractCard {
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("EmptyMind"); public static final String ID = "EmptyMind";
/*    */   
/*    */   public EmptyMind() {
/* 20 */     super("EmptyMind", cardStrings.NAME, "purple/skill/empty_mind", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
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
/* 31 */     this.magicNumber = 2;
/* 32 */     this.baseMagicNumber = 2;
/* 33 */     this.tags.add(CardTags.EMPTY);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
/* 39 */     addToBot((AbstractGameAction)new NotStanceCheckAction("Neutral", (AbstractGameAction)new VFXAction((AbstractGameEffect)new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
/*    */ 
/*    */ 
/*    */     
/* 43 */     addToBot((AbstractGameAction)new ChangeStanceAction("Neutral"));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 48 */     if (!this.upgraded) {
/* 49 */       upgradeName();
/* 50 */       upgradeMagicNumber(1);
/* 51 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 52 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 58 */     return new EmptyMind();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\EmptyMind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */