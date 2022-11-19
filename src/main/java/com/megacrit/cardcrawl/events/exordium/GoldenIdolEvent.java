/*     */ package com.megacrit.cardcrawl.events.exordium;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.cards.curses.Injury;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*     */ import com.megacrit.cardcrawl.helpers.RelicLibrary;
/*     */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.relics.GoldenIdol;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ 
/*     */ 
/*     */ public class GoldenIdolEvent
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "Golden Idol";
/*  24 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Golden Idol");
/*  25 */   public static final String NAME = eventStrings.NAME;
/*  26 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  27 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  29 */   private static final String DIALOG_START = DESCRIPTIONS[0];
/*  30 */   private static final String DIALOG_BOULDER = DESCRIPTIONS[1];
/*  31 */   private static final String DIALOG_CHOSE_RUN = DESCRIPTIONS[2];
/*  32 */   private static final String DIALOG_CHOSE_FIGHT = DESCRIPTIONS[3];
/*  33 */   private static final String DIALOG_CHOSE_FLAT = DESCRIPTIONS[4];
/*  34 */   private static final String DIALOG_IGNORE = DESCRIPTIONS[5];
/*     */   
/*  36 */   private int screenNum = 0;
/*     */   
/*     */   private static final float HP_LOSS_PERCENT = 0.25F;
/*     */   private static final float MAX_HP_LOSS_PERCENT = 0.08F;
/*     */   private static final float A_2_HP_LOSS_PERCENT = 0.35F;
/*     */   private static final float A_2_MAX_HP_LOSS_PERCENT = 0.1F;
/*     */   private int damage;
/*     */   private int maxHpLoss;
/*  44 */   private AbstractRelic relicMetric = null;
/*     */   
/*     */   public GoldenIdolEvent() {
/*  47 */     super(NAME, DIALOG_START, "images/events/goldenIdol.jpg");
/*     */     
/*  49 */     this.imageEventText.setDialogOption(OPTIONS[0], (AbstractRelic)new GoldenIdol());
/*  50 */     this.imageEventText.setDialogOption(OPTIONS[1]);
/*     */     
/*  52 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  53 */       this.damage = (int)(AbstractDungeon.player.maxHealth * 0.35F);
/*  54 */       this.maxHpLoss = (int)(AbstractDungeon.player.maxHealth * 0.1F);
/*     */     } else {
/*  56 */       this.damage = (int)(AbstractDungeon.player.maxHealth * 0.25F);
/*  57 */       this.maxHpLoss = (int)(AbstractDungeon.player.maxHealth * 0.08F);
/*     */     } 
/*     */     
/*  60 */     if (this.maxHpLoss < 1) {
/*  61 */       this.maxHpLoss = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  67 */     if (Settings.AMBIANCE_ON) {
/*  68 */       CardCrawlGame.sound.play("EVENT_GOLDEN");
/*     */     }
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     Injury injury;
/*  74 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/*  77 */         switch (buttonPressed) {
/*     */           case 0:
/*  79 */             this.imageEventText.updateBodyText(DIALOG_BOULDER);
/*     */             
/*  81 */             if (AbstractDungeon.player.hasRelic("Golden Idol")) {
/*  82 */               this.relicMetric = RelicLibrary.getRelic("Circlet").makeCopy();
/*     */             } else {
/*  84 */               this.relicMetric = RelicLibrary.getRelic("Golden Idol").makeCopy();
/*     */             } 
/*  86 */             AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), this.relicMetric);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  91 */             CardCrawlGame.screenShake.mildRumble(5.0F);
/*  92 */             CardCrawlGame.sound.play("BLUNT_HEAVY");
/*  93 */             this.screenNum = 1;
/*  94 */             this.imageEventText.updateDialogOption(0, OPTIONS[2], CardLibrary.getCopy("Injury"));
/*  95 */             this.imageEventText.updateDialogOption(1, OPTIONS[3] + this.damage + OPTIONS[4]);
/*  96 */             this.imageEventText.setDialogOption(OPTIONS[5] + this.maxHpLoss + OPTIONS[6]);
/*     */             return;
/*     */         } 
/*     */         
/* 100 */         this.imageEventText.updateBodyText(DIALOG_IGNORE);
/* 101 */         this.screenNum = 2;
/* 102 */         this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 103 */         this.imageEventText.clearRemainingOptions();
/* 104 */         AbstractEvent.logMetricIgnored("Golden Idol");
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 110 */         switch (buttonPressed) {
/*     */           
/*     */           case 0:
/* 113 */             CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
/* 114 */             this.imageEventText.updateBodyText(DIALOG_CHOSE_RUN);
/* 115 */             injury = new Injury();
/* 116 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)injury, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */             
/* 118 */             this.screenNum = 2;
/* 119 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/*     */             
/* 121 */             AbstractEvent.logMetricObtainCardAndRelic("Golden Idol", "Take Wound", (AbstractCard)injury, this.relicMetric);
/*     */             
/* 123 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */           
/*     */           case 1:
/* 127 */             this.imageEventText.updateBodyText(DIALOG_CHOSE_FIGHT);
/* 128 */             CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
/* 129 */             CardCrawlGame.sound.play("BLUNT_FAST");
/* 130 */             AbstractDungeon.player.damage(new DamageInfo(null, this.damage));
/* 131 */             this.screenNum = 2;
/* 132 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/*     */             
/* 134 */             AbstractEvent.logMetricObtainRelicAndDamage("Golden Idol", "Take Damage", this.relicMetric, this.damage);
/*     */             
/* 136 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */           
/*     */           case 2:
/* 140 */             this.imageEventText.updateBodyText(DIALOG_CHOSE_FLAT);
/* 141 */             AbstractDungeon.player.decreaseMaxHealth(this.maxHpLoss);
/* 142 */             CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
/* 143 */             CardCrawlGame.sound.play("BLUNT_FAST");
/* 144 */             this.screenNum = 2;
/* 145 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/*     */             
/* 147 */             AbstractEvent.logMetricObtainRelicAndLoseMaxHP("Golden Idol", "Lose Max HP", this.relicMetric, this.maxHpLoss);
/* 148 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */         } 
/* 151 */         openMap();
/*     */         return;
/*     */ 
/*     */       
/*     */       case 2:
/* 156 */         openMap();
/*     */         return;
/*     */     } 
/* 159 */     openMap();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\exordium\GoldenIdolEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */