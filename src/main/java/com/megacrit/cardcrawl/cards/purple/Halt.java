/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.HaltAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Halt extends AbstractCard {
/* 12 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Halt"); public static final String ID = "Halt";
/*    */   private static final int BLOCK_AMOUNT = 3;
/*    */   private static final int UPGRADE_PLUS_BLOCK = 1;
/*    */   private static final int BLOCK_DIFFERENCE = 6;
/*    */   private static final int UPGRADE_PLUS_BLOCK_DIFFERENCE = 4;
/*    */   
/*    */   public Halt() {
/* 19 */     super("Halt", cardStrings.NAME, "purple/skill/halt", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.COMMON, CardTarget.SELF);
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
/* 30 */     this.block = this.baseBlock = 3;
/* 31 */     this.magicNumber = this.baseMagicNumber = 9;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     applyPowers();
/* 37 */     addToBot((AbstractGameAction)new HaltAction((AbstractCreature)p, this.block, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 42 */     this.baseBlock += 6 + this.timesUpgraded * 4;
/* 43 */     this.baseMagicNumber = this.baseBlock;
/* 44 */     super.applyPowers();
/* 45 */     this.magicNumber = this.block;
/* 46 */     this.isMagicNumberModified = this.isBlockModified;
/* 47 */     this.baseBlock -= 6 + this.timesUpgraded * 4;
/* 48 */     super.applyPowers();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 53 */     if (!this.upgraded) {
/* 54 */       upgradeName();
/* 55 */       upgradeBlock(1);
/* 56 */       this.baseMagicNumber = this.baseBlock + 6 + this.timesUpgraded * 4;
/*    */       
/* 58 */       this.upgradedMagicNumber = this.upgradedBlock;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 64 */     return new Halt();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\Halt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */