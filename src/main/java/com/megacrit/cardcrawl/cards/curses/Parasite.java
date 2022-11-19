/*    */ package com.megacrit.cardcrawl.cards.curses;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Parasite extends AbstractCard {
/*    */   public static final String ID = "Parasite";
/* 12 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Parasite");
/*    */   
/*    */   public Parasite() {
/* 15 */     super("Parasite", cardStrings.NAME, "curse/parasite", -2, cardStrings.DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.CURSE, CardTarget.NONE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onRemoveFromMasterDeck() {
/* 33 */     AbstractDungeon.player.decreaseMaxHealth(3);
/* 34 */     CardCrawlGame.sound.play("BLOOD_SWISH");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 43 */     return new Parasite();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\curses\Parasite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */