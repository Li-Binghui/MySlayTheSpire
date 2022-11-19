/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.ObtainPotionEffect;
/*    */ 
/*    */ public class EntropicBrew
/*    */   extends AbstractPotion
/*    */ {
/*    */   public static final String POTION_ID = "EntropicBrew";
/* 18 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("EntropicBrew");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntropicBrew() {
/* 24 */     super(potionStrings.NAME, "EntropicBrew", PotionRarity.RARE, PotionSize.M, PotionEffect.RAINBOW, Color.WHITE, null, null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 33 */     this.description = potionStrings.DESCRIPTIONS[0];
/* 34 */     this.potency = getPotency();
/* 35 */     this.isThrown = false;
/* 36 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 41 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 42 */       for (int i = 0; i < AbstractDungeon.player.potionSlots; i++) {
/* 43 */         addToBot((AbstractGameAction)new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
/*    */       }
/*    */     }
/* 46 */     else if (AbstractDungeon.player.hasRelic("Sozu")) {
/* 47 */       AbstractDungeon.player.getRelic("Sozu").flash();
/*    */     } else {
/* 49 */       for (int i = 0; i < AbstractDungeon.player.potionSlots; i++) {
/* 50 */         AbstractDungeon.effectsQueue.add(new ObtainPotionEffect(AbstractDungeon.returnRandomPotion()));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 58 */     if (AbstractDungeon.actionManager.turnHasEnded && 
/* 59 */       (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 60 */       return false;
/*    */     }
/* 62 */     if ((AbstractDungeon.getCurrRoom()).event != null && 
/* 63 */       (AbstractDungeon.getCurrRoom()).event instanceof com.megacrit.cardcrawl.events.shrines.WeMeetAgain) {
/* 64 */       return false;
/*    */     }
/*    */     
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 72 */     if (AbstractDungeon.player != null) {
/* 73 */       return AbstractDungeon.player.potionSlots;
/*    */     }
/* 75 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 80 */     return new EntropicBrew();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\EntropicBrew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */