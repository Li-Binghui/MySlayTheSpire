/*    */ package com.megacrit.cardcrawl.blights;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.BlightStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.RegenerateMonsterPower;
/*    */ 
/*    */ public class AncientAugmentation extends AbstractBlight {
/* 15 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("MetallicRebirth"); public static final String ID = "MetallicRebirth";
/* 16 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   
/*    */   public AncientAugmentation() {
/* 19 */     super("MetallicRebirth", NAME, DESC[0] + '\001' + DESC[1] + '\n' + DESC[2] + '\n' + DESC[3], "ancient.png", false);
/* 20 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stack() {
/* 25 */     this.counter++;
/* 26 */     updateDescription();
/* 27 */     flash();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 32 */     this.description = DESC[0] + this.counter + DESC[1] + (this.counter * 10) + DESC[2] + (this.counter * 10) + DESC[3];
/* 33 */     this.tips.clear();
/* 34 */     this.tips.add(new PowerTip(this.name, this.description));
/* 35 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCreateEnemy(AbstractMonster m) {
/* 40 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new ArtifactPower((AbstractCreature)m, this.counter), this.counter));
/* 41 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new PlatedArmorPower((AbstractCreature)m, this.counter * 10), this.counter * 10));
/*    */     
/* 43 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new RegenerateMonsterPower(m, this.counter * 10), this.counter * 10));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\AncientAugmentation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */