/*     */ package com.megacrit.cardcrawl.actions.unique;
/*     */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*     */ 
/*     */ public class ImmolateAction extends AbstractGameAction {
/*  17 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ImmolateAction");
/*  18 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public int[] damage;
/*     */   
/*     */   public ImmolateAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type) {
/*  23 */     setValues(null, source, amount[0]);
/*  24 */     this.damage = amount;
/*  25 */     this.actionType = ActionType.DAMAGE;
/*  26 */     this.damageType = type;
/*  27 */     this.attackEffect = AttackEffect.FIRE;
/*  28 */     this.duration = Settings.ACTION_DUR_FAST;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  34 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*     */ 
/*     */       
/*  37 */       if (AbstractDungeon.player.hand.size() == 0) {
/*  38 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/*  42 */       if (AbstractDungeon.player.hand.size() == 1) {
/*  43 */         AbstractCard card = AbstractDungeon.player.hand.getBottomCard();
/*  44 */         if (card.type == AbstractCard.CardType.CURSE || card.type == AbstractCard.CardType.STATUS) {
/*  45 */           dealDamage();
/*     */         }
/*  47 */         addToTop((AbstractGameAction)new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
/*  48 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/*  52 */       AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
/*  53 */       tickDuration();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/*  60 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/*  61 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/*  62 */         if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
/*  63 */           dealDamage();
/*     */         }
/*  65 */         addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.handCardSelectScreen.selectedCards));
/*     */       } 
/*  67 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*     */     } 
/*     */     
/*  70 */     tickDuration();
/*     */   }
/*     */ 
/*     */   
/*     */   public void dealDamage() {
/*  75 */     boolean playedMusic = false;
/*  76 */     int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
/*  77 */     for (int i = 0; i < temp; i++) {
/*  78 */       if (!((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isDying && 
/*  79 */         !((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isEscaping) {
/*  80 */         if (playedMusic) {
/*  81 */           AbstractDungeon.effectList.add(new FlashAtkImgEffect(
/*     */                 
/*  83 */                 ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cX, 
/*  84 */                 ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cY, this.attackEffect, true));
/*     */         }
/*     */         else {
/*     */           
/*  88 */           playedMusic = true;
/*  89 */           AbstractDungeon.effectList.add(new FlashAtkImgEffect(
/*     */                 
/*  91 */                 ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cX, 
/*  92 */                 ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cY, this.attackEffect));
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  98 */     for (AbstractPower p : AbstractDungeon.player.powers) {
/*  99 */       p.onDamageAllEnemies(this.damage);
/*     */     }
/*     */     
/* 102 */     int temp2 = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
/* 103 */     for (int j = 0; j < temp2; j++) {
/* 104 */       if (!((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(j)).isDying && 
/* 105 */         !((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(j)).isEscaping) {
/* 106 */         ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(j)).tint.color = Color.RED.cpy();
/* 107 */         ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(j)).tint.changeColor(Color.WHITE.cpy());
/* 108 */         ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(j)).damage(new DamageInfo(this.source, this.damage[j], this.damageType));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 113 */     if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
/* 114 */       AbstractDungeon.actionManager.clearPostCombatActions(); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\ImmolateAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */