/*    */ package com.megacrit.cardcrawl.events.beyond;
/*    */ 
/*    */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.GoldenIdol;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
/*    */ 
/*    */ public class MoaiHead
/*    */   extends AbstractImageEvent {
/*    */   public static final String ID = "The Moai Head";
/* 16 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("The Moai Head");
/* 17 */   public static final String NAME = eventStrings.NAME;
/* 18 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 19 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/*    */   private static final float HP_LOSS_PERCENT = 0.125F;
/*    */   private static final float A_2_HP_LOSS_PERCENT = 0.18F;
/* 23 */   private int hpAmt = 0;
/*    */   
/*    */   private static final int goldAmount = 333;
/*    */   
/* 27 */   private static final String INTRO_BODY = DESCRIPTIONS[0];
/* 28 */   private int screenNum = 0;
/*    */   
/*    */   public MoaiHead() {
/* 31 */     super(NAME, INTRO_BODY, "images/events/moaiHead.jpg");
/*    */     
/* 33 */     if (AbstractDungeon.ascensionLevel >= 15) {
/* 34 */       this.hpAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.18F);
/*    */     } else {
/* 36 */       this.hpAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.125F);
/*    */     } 
/*    */     
/* 39 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.hpAmt + OPTIONS[1]);
/* 40 */     if (AbstractDungeon.player.hasRelic("Golden Idol")) {
/* 41 */       this.imageEventText.setDialogOption(OPTIONS[2], !AbstractDungeon.player.hasRelic("Golden Idol"));
/*    */     } else {
/* 43 */       this.imageEventText.setDialogOption(OPTIONS[3], !AbstractDungeon.player.hasRelic("Golden Idol"));
/*    */     } 
/* 45 */     this.imageEventText.setDialogOption(OPTIONS[4]);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/* 50 */     switch (this.screenNum) {
/*    */       
/*    */       case 0:
/* 53 */         switch (buttonPressed) {
/*    */ 
/*    */           
/*    */           case 0:
/* 57 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/*    */ 
/*    */             
/* 60 */             CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true);
/* 61 */             CardCrawlGame.sound.play("BLUNT_HEAVY");
/* 62 */             AbstractDungeon.player.maxHealth -= this.hpAmt;
/* 63 */             if (AbstractDungeon.player.currentHealth > AbstractDungeon.player.maxHealth) {
/* 64 */               AbstractDungeon.player.currentHealth = AbstractDungeon.player.maxHealth;
/*    */             }
/* 66 */             if (AbstractDungeon.player.maxHealth < 1) {
/* 67 */               AbstractDungeon.player.maxHealth = 1;
/*    */             }
/* 69 */             AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
/* 70 */             logMetricHealAndLoseMaxHP("The Moai Head", "Heal", AbstractDungeon.player.maxHealth, this.hpAmt);
/*    */             
/* 72 */             this.screenNum = 1;
/* 73 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/* 74 */             this.imageEventText.clearRemainingOptions();
/*    */             return;
/*    */           
/*    */           case 1:
/* 78 */             logMetricGainGoldAndLoseRelic("The Moai Head", "Gave Idol", (AbstractRelic)new GoldenIdol(), 333);
/* 79 */             this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
/* 80 */             this.screenNum = 1;
/* 81 */             AbstractDungeon.player.loseRelic("Golden Idol");
/* 82 */             AbstractDungeon.effectList.add(new RainingGoldEffect(333));
/* 83 */             AbstractDungeon.player.gainGold(333);
/* 84 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/* 85 */             this.imageEventText.clearRemainingOptions();
/*    */             return;
/*    */         } 
/*    */         
/* 89 */         logMetricIgnored("The Moai Head");
/* 90 */         this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
/* 91 */         this.screenNum = 1;
/* 92 */         this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/* 93 */         this.imageEventText.clearRemainingOptions();
/*    */         return;
/*    */     } 
/*    */ 
/*    */     
/* 98 */     openMap();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\beyond\MoaiHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */