/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
/*    */ 
/*    */ public class ExplosivePotion extends AbstractPotion {
/*    */   public static final String POTION_ID = "Explosive Potion";
/* 19 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Explosive Potion");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ExplosivePotion() {
/* 25 */     super(potionStrings.NAME, "Explosive Potion", PotionRarity.COMMON, PotionSize.H, PotionColor.EXPLOSIVE);
/* 26 */     this.isThrown = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 31 */     this.potency = getPotency();
/* 32 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 33 */     this.tips.clear();
/* 34 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 39 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 40 */       if (!m.isDeadOrEscaped()) {
/* 41 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
/*    */       }
/*    */     } 
/*    */     
/* 45 */     addToBot((AbstractGameAction)new WaitAction(0.5F));
/* 46 */     addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, 
/*    */ 
/*    */           
/* 49 */           DamageInfo.createDamageMatrix(this.potency, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 56 */     return 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 61 */     return new ExplosivePotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\ExplosivePotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */