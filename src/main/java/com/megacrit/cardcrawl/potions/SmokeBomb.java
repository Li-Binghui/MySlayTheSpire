/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
/*    */ 
/*    */ public class SmokeBomb extends AbstractPotion {
/* 17 */   public static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("SmokeBomb");
/*    */   
/*    */   public static final String POTION_ID = "SmokeBomb";
/*    */ 
/*    */   
/*    */   public SmokeBomb() {
/* 23 */     super(potionStrings.NAME, "SmokeBomb", PotionRarity.RARE, PotionSize.SPHERE, PotionColor.SMOKE);
/* 24 */     this.description = potionStrings.DESCRIPTIONS[0];
/* 25 */     this.isThrown = true;
/* 26 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 31 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 32 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 33 */       (AbstractDungeon.getCurrRoom()).smoked = true;
/* 34 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmokeBombEffect(((AbstractCreature)abstractPlayer).hb.cX, ((AbstractCreature)abstractPlayer).hb.cY)));
/* 35 */       AbstractDungeon.player.hideHealthBar();
/* 36 */       AbstractDungeon.player.isEscaping = true;
/* 37 */       AbstractDungeon.player.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
/* 38 */       AbstractDungeon.overlayMenu.endTurnButton.disable();
/* 39 */       AbstractDungeon.player.escapeTimer = 2.5F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 45 */     if (super.canUse()) {
/* 46 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 47 */         if (m.hasPower("BackAttack")) {
/* 48 */           return false;
/*    */         }
/* 50 */         if (m.type == AbstractMonster.EnemyType.BOSS) {
/* 51 */           return false;
/*    */         }
/*    */       } 
/* 54 */       return true;
/*    */     } 
/* 56 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 61 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 66 */     return new SmokeBomb();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\SmokeBomb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */