/*     */ package com.megacrit.cardcrawl.events.beyond;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.cards.curses.Writhe;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.List;
/*     */ 
/*     */ public class WindingHalls
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "Winding Halls";
/*  23 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Winding Halls");
/*  24 */   public static final String NAME = eventStrings.NAME;
/*  25 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  26 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*     */   private static final float HP_LOSS_PERCENT = 0.125F;
/*     */   
/*     */   private static final float HP_MAX_LOSS_PERCENT = 0.05F;
/*     */   private static final float A_2_HP_LOSS_PERCENT = 0.18F;
/*     */   private static final float HEAL_AMT = 0.25F;
/*  33 */   private static final String INTRO_BODY1 = DESCRIPTIONS[0]; private static final float A_2_HEAL_AMT = 0.2F; private int hpAmt; private int healAmt; private int maxHPAmt;
/*  34 */   private static final String INTRO_BODY2 = DESCRIPTIONS[1];
/*  35 */   private static final String CHOICE_1_TEXT = DESCRIPTIONS[2];
/*  36 */   private static final String CHOICE_2_TEXT = DESCRIPTIONS[3];
/*     */   
/*  38 */   private int screenNum = 0;
/*     */   
/*     */   public WindingHalls() {
/*  41 */     super(NAME, INTRO_BODY1, "images/events/winding.jpg");
/*     */     
/*  43 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  44 */       this.hpAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.18F);
/*  45 */       this.healAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.2F);
/*     */     } else {
/*  47 */       this.hpAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.125F);
/*  48 */       this.healAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.25F);
/*     */     } 
/*  50 */     this.maxHPAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.05F);
/*     */     
/*  52 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  57 */     if (Settings.AMBIANCE_ON)
/*  58 */       CardCrawlGame.sound.play("EVENT_WINDING"); 
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     List<String> cards;
/*     */     Writhe writhe;
/*  64 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/*  67 */         this.imageEventText.updateBodyText(INTRO_BODY2);
/*  68 */         this.screenNum = 1;
/*  69 */         this.imageEventText.updateDialogOption(0, OPTIONS[1] + this.hpAmt + OPTIONS[2], CardLibrary.getCopy("Madness"));
/*  70 */         this.imageEventText.setDialogOption(OPTIONS[3] + this.healAmt + OPTIONS[5], CardLibrary.getCopy("Writhe"));
/*  71 */         this.imageEventText.setDialogOption(OPTIONS[6] + this.maxHPAmt + OPTIONS[7]);
/*     */         return;
/*     */       case 1:
/*  74 */         switch (buttonPressed) {
/*     */           case 0:
/*  76 */             cards = new ArrayList<>();
/*  77 */             cards.add("Madness");
/*  78 */             cards.add("Madness");
/*  79 */             logMetric("Winding Halls", "Embrace Madness", cards, null, null, null, null, null, null, this.hpAmt, 0, 0, 0, 0, 0);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  96 */             this.imageEventText.updateBodyText(CHOICE_1_TEXT);
/*  97 */             AbstractDungeon.player.damage(new DamageInfo(null, this.hpAmt));
/*  98 */             CardCrawlGame.sound.play("ATTACK_MAGIC_SLOW_1");
/*  99 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)new Madness(), Settings.WIDTH / 2.0F - 350.0F * Settings.xScale, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 104 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)new Madness(), Settings.WIDTH / 2.0F + 350.0F * Settings.xScale, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 109 */             this.screenNum = 2;
/* 110 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/* 111 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */           case 1:
/* 114 */             this.imageEventText.updateBodyText(CHOICE_2_TEXT);
/* 115 */             AbstractDungeon.player.heal(this.healAmt);
/* 116 */             writhe = new Writhe();
/* 117 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)writhe, Settings.WIDTH / 2.0F + 10.0F * Settings.xScale, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 122 */             logMetricObtainCardAndHeal("Winding Halls", "Writhe", (AbstractCard)writhe, this.healAmt);
/* 123 */             this.screenNum = 2;
/* 124 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/* 125 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */           case 2:
/* 128 */             this.screenNum = 2;
/* 129 */             this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
/* 130 */             logMetricMaxHPLoss("Winding Halls", "Max HP", this.maxHPAmt);
/* 131 */             AbstractDungeon.player.decreaseMaxHealth(this.maxHPAmt);
/* 132 */             CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
/*     */ 
/*     */ 
/*     */             
/* 136 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/* 137 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */         } 
/*     */         
/*     */         return;
/*     */     } 
/*     */     
/* 144 */     openMap();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\beyond\WindingHalls.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */