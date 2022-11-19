/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
/*    */ 
/*    */ public class SignatureMove extends AbstractCard {
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("SignatureMove"); public static final String ID = "SignatureMove";
/*    */   
/*    */   public SignatureMove() {
/* 20 */     super("SignatureMove", cardStrings.NAME, "purple/attack/signature_move", 2, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 31 */     this.baseDamage = 30;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     if (m != null) {
/* 37 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClashEffect(m.hb.cX, m.hb.cY), 0.1F));
/*    */     }
/*    */     
/* 40 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 45 */     if (!this.upgraded) {
/* 46 */       upgradeName();
/* 47 */       upgradeDamage(10);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
/* 54 */     boolean canUse = super.canUse(p, m);
/* 55 */     if (!canUse) {
/* 56 */       return false;
/*    */     }
/*    */     
/* 59 */     for (AbstractCard c : p.hand.group) {
/* 60 */       if (c.type == CardType.ATTACK && c != this) {
/* 61 */         canUse = false;
/* 62 */         this.cantUseMessage = (CardCrawlGame.languagePack.getUIString("SignatureMoveMessage")).TEXT[0];
/*    */       } 
/*    */     } 
/*    */     
/* 66 */     return canUse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnGlowCheck() {
/* 71 */     boolean glow = true;
/*    */     
/* 73 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 74 */       if (c.type == CardType.ATTACK && c != this) {
/* 75 */         glow = false;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 80 */     if (glow) {
/* 81 */       this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
/*    */     } else {
/* 83 */       this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 89 */     return new SignatureMove();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\SignatureMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */