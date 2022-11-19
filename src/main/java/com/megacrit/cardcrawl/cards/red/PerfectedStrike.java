/*     */ package com.megacrit.cardcrawl.cards.red;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.CardStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ 
/*     */ public class PerfectedStrike extends AbstractCard {
/*  15 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Perfected Strike"); public static final String ID = "Perfected Strike";
/*     */   
/*     */   public PerfectedStrike() {
/*  18 */     super("Perfected Strike", cardStrings.NAME, "red/attack/perfected_strike", 2, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.RED, CardRarity.COMMON, CardTarget.ENEMY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     this.baseDamage = 6;
/*  30 */     this.baseMagicNumber = 2;
/*  31 */     this.magicNumber = this.baseMagicNumber;
/*  32 */     this.tags.add(CardTags.STRIKE);
/*     */   }
/*     */   
/*     */   public static int countCards() {
/*  36 */     int count = 0;
/*  37 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/*  38 */       if (isStrike(c)) {
/*  39 */         count++;
/*     */       }
/*     */     } 
/*  42 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/*  43 */       if (isStrike(c)) {
/*  44 */         count++;
/*     */       }
/*     */     } 
/*  47 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/*  48 */       if (isStrike(c)) {
/*  49 */         count++;
/*     */       }
/*     */     } 
/*  52 */     return count;
/*     */   }
/*     */   
/*     */   public static boolean isStrike(AbstractCard c) {
/*  56 */     return c.hasTag(CardTags.STRIKE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void use(AbstractPlayer p, AbstractMonster m) {
/*  61 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void calculateCardDamage(AbstractMonster mo) {
/*  70 */     int realBaseDamage = this.baseDamage;
/*  71 */     this.baseDamage += this.magicNumber * countCards();
/*     */     
/*  73 */     super.calculateCardDamage(mo);
/*     */     
/*  75 */     this.baseDamage = realBaseDamage;
/*     */ 
/*     */     
/*  78 */     this.isDamageModified = (this.damage != this.baseDamage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyPowers() {
/*  85 */     int realBaseDamage = this.baseDamage;
/*  86 */     this.baseDamage += this.magicNumber * countCards();
/*     */     
/*  88 */     super.applyPowers();
/*     */     
/*  90 */     this.baseDamage = realBaseDamage;
/*     */ 
/*     */     
/*  93 */     this.isDamageModified = (this.damage != this.baseDamage);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard makeCopy() {
/*  98 */     return new PerfectedStrike();
/*     */   }
/*     */ 
/*     */   
/*     */   public void upgrade() {
/* 103 */     if (!this.upgraded) {
/* 104 */       upgradeName();
/* 105 */       upgradeMagicNumber(1);
/* 106 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 107 */       initializeDescription();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\PerfectedStrike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */