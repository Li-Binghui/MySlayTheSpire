/*     */ package com.megacrit.cardcrawl.cards.blue;
/*     */ 
/*     */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
/*     */ 
/*     */ public class Blizzard extends AbstractCard {
/*  19 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Blizzard"); public static final String ID = "Blizzard";
/*     */   
/*     */   public Blizzard() {
/*  22 */     super("Blizzard", cardStrings.NAME, "blue/attack/blizzard", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
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
/*  33 */     this.baseDamage = 0;
/*  34 */     this.baseMagicNumber = 2;
/*  35 */     this.magicNumber = this.baseMagicNumber;
/*  36 */     this.isMultiDamage = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void use(AbstractPlayer p, AbstractMonster m) {
/*  41 */     int frostCount = 0;
/*  42 */     for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
/*  43 */       if (o instanceof com.megacrit.cardcrawl.orbs.Frost) {
/*  44 */         frostCount++;
/*     */       }
/*     */     } 
/*     */     
/*  48 */     this.baseDamage = frostCount * this.magicNumber;
/*  49 */     calculateCardDamage((AbstractMonster)null);
/*     */     
/*  51 */     if (Settings.FAST_MODE) {
/*  52 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BlizzardEffect(frostCount, 
/*  53 */               AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
/*     */     } else {
/*  55 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BlizzardEffect(frostCount, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
/*     */     } 
/*     */     
/*  58 */     addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyPowers() {
/*  63 */     int frostCount = 0;
/*  64 */     for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
/*  65 */       if (o instanceof com.megacrit.cardcrawl.orbs.Frost) {
/*  66 */         frostCount++;
/*     */       }
/*     */     } 
/*     */     
/*  70 */     if (frostCount > 0) {
/*  71 */       this.baseDamage = frostCount * this.magicNumber;
/*  72 */       super.applyPowers();
/*  73 */       this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
/*  74 */       initializeDescription();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onMoveToDiscard() {
/*  80 */     this.rawDescription = cardStrings.DESCRIPTION;
/*  81 */     initializeDescription();
/*     */   }
/*     */ 
/*     */   
/*     */   public void calculateCardDamage(AbstractMonster mo) {
/*  86 */     super.calculateCardDamage(mo);
/*  87 */     this.rawDescription = cardStrings.DESCRIPTION;
/*  88 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
/*  89 */     initializeDescription();
/*     */   }
/*     */ 
/*     */   
/*     */   public void upgrade() {
/*  94 */     if (!this.upgraded) {
/*  95 */       upgradeName();
/*  96 */       upgradeMagicNumber(1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard makeCopy() {
/* 102 */     return new Blizzard();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\blue\Blizzard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */