/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.curses.Decay;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BloodyIdol;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.relics.GoldenIdol;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ 
/*     */ public class ForgottenAltar
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "Forgotten Altar";
/*  25 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Forgotten Altar");
/*  26 */   public static final String NAME = eventStrings.NAME;
/*  27 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  28 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  30 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  31 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/*  32 */   private static final String DIALOG_3 = DESCRIPTIONS[2];
/*  33 */   private static final String DIALOG_4 = DESCRIPTIONS[3];
/*     */   private static final float HP_LOSS_PERCENT = 0.25F;
/*     */   private static final float A_2_HP_LOSS_PERCENT = 0.35F;
/*     */   private int hpLoss;
/*     */   private static final int MAX_HP_GAIN = 5;
/*     */   
/*     */   public ForgottenAltar() {
/*  40 */     super(NAME, DIALOG_1, "images/events/forgottenAltar.jpg");
/*     */     
/*  42 */     if (AbstractDungeon.player.hasRelic("Golden Idol")) {
/*  43 */       this.imageEventText.setDialogOption(OPTIONS[0], 
/*     */           
/*  45 */           !AbstractDungeon.player.hasRelic("Golden Idol"), (AbstractRelic)new BloodyIdol());
/*     */     } else {
/*     */       
/*  48 */       this.imageEventText.setDialogOption(OPTIONS[1], 
/*     */           
/*  50 */           !AbstractDungeon.player.hasRelic("Golden Idol"), (AbstractRelic)new BloodyIdol());
/*     */     } 
/*     */ 
/*     */     
/*  54 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  55 */       this.hpLoss = MathUtils.round(AbstractDungeon.player.maxHealth * 0.35F);
/*     */     } else {
/*  57 */       this.hpLoss = MathUtils.round(AbstractDungeon.player.maxHealth * 0.25F);
/*     */     } 
/*     */     
/*  60 */     this.imageEventText.setDialogOption(OPTIONS[2] + '\005' + OPTIONS[3] + this.hpLoss + OPTIONS[4]);
/*  61 */     this.imageEventText.setDialogOption(OPTIONS[6], CardLibrary.getCopy("Decay"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  67 */     if (Settings.AMBIANCE_ON) {
/*  68 */       CardCrawlGame.sound.play("EVENT_FORGOTTEN");
/*     */     }
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     Decay decay;
/*  74 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/*  77 */         switch (buttonPressed) {
/*     */           case 0:
/*  79 */             gainChalice();
/*  80 */             showProceedScreen(DIALOG_2);
/*  81 */             CardCrawlGame.sound.play("HEAL_1");
/*     */             break;
/*     */           case 1:
/*  84 */             AbstractDungeon.player.increaseMaxHp(5, false);
/*  85 */             AbstractDungeon.player.damage(new DamageInfo(null, this.hpLoss));
/*  86 */             CardCrawlGame.sound.play("HEAL_3");
/*  87 */             showProceedScreen(DIALOG_3);
/*  88 */             logMetricDamageAndMaxHPGain("Forgotten Altar", "Shed Blood", this.hpLoss, 5);
/*     */             break;
/*     */           case 2:
/*  91 */             CardCrawlGame.sound.play("BLUNT_HEAVY");
/*  92 */             CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true);
/*  93 */             decay = new Decay();
/*  94 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)decay, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*     */             
/*  96 */             showProceedScreen(DIALOG_4);
/*  97 */             logMetricObtainCard("Forgotten Altar", "Smashed Altar", (AbstractCard)decay);
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/*     */         return;
/*     */     } 
/*     */     
/* 105 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void gainChalice() {
/* 111 */     int relicAtIndex = 0;
/* 112 */     for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
/* 113 */       if (((AbstractRelic)AbstractDungeon.player.relics.get(i)).relicId.equals("Golden Idol")) {
/* 114 */         relicAtIndex = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 118 */     if (AbstractDungeon.player.hasRelic("Bloody Idol")) {
/* 119 */       AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), 
/*     */ 
/*     */           
/* 122 */           RelicLibrary.getRelic("Circlet").makeCopy());
/* 123 */       logMetricRelicSwap("Forgotten Altar", "Gave Idol", (AbstractRelic)new Circlet(), (AbstractRelic)new GoldenIdol());
/*     */     } else {
/* 125 */       ((AbstractRelic)AbstractDungeon.player.relics.get(relicAtIndex)).onUnequip();
/* 126 */       AbstractRelic bloodyIdol = RelicLibrary.getRelic("Bloody Idol").makeCopy();
/* 127 */       bloodyIdol.instantObtain(AbstractDungeon.player, relicAtIndex, false);
/* 128 */       logMetricRelicSwap("Forgotten Altar", "Gave Idol", (AbstractRelic)new BloodyIdol(), (AbstractRelic)new GoldenIdol());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\ForgottenAltar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */