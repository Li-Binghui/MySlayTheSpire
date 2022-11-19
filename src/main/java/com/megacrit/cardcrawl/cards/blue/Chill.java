/*    */ package com.megacrit.cardcrawl.cards.blue;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
/*    */ 
/*    */ public class Chill extends AbstractCard {
/* 14 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Chill"); public static final String ID = "Chill";
/*    */   
/*    */   public Chill() {
/* 17 */     super("Chill", cardStrings.NAME, "blue/skill/chill", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
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
/* 28 */     this.exhaust = true;
/* 29 */     this.showEvokeValue = true;
/* 30 */     this.showEvokeOrbCount = 3;
/* 31 */     this.baseMagicNumber = 1;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     int count = 0;
/* 38 */     for (AbstractMonster mon : (AbstractDungeon.getMonsters()).monsters) {
/* 39 */       if (!mon.isDeadOrEscaped()) {
/* 40 */         count++;
/*    */       }
/*    */     } 
/* 43 */     for (int i = 0; i < count * this.magicNumber; i++) {
/* 44 */       addToBot((AbstractGameAction)new ChannelAction((AbstractOrb)new Frost()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 50 */     if (!this.upgraded) {
/* 51 */       upgradeName();
/* 52 */       this.isInnate = true;
/* 53 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 54 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 60 */     return new Chill();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\blue\Chill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */