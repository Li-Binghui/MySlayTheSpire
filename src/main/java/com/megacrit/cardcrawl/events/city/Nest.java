/*    */ package com.megacrit.cardcrawl.events.city;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*    */ import com.megacrit.cardcrawl.localization.EventStrings;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ 
/*    */ public class Nest
/*    */   extends AbstractImageEvent {
/*    */   public static final String ID = "Nest";
/* 18 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Nest");
/* 19 */   public static final String NAME = eventStrings.NAME;
/* 20 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 21 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/* 22 */   private static final String INTRO_BODY_M = DESCRIPTIONS[0];
/* 23 */   private static final String INTRO_BODY_M_2 = DESCRIPTIONS[1];
/* 24 */   private static final String ACCEPT_BODY = DESCRIPTIONS[2];
/* 25 */   private static final String EXIT_BODY = DESCRIPTIONS[3];
/*    */   
/*    */   private static final int HP_LOSS = 6;
/*    */   private int goldGain;
/* 29 */   private int screenNum = 0;
/*    */   
/*    */   public Nest() {
/* 32 */     super(NAME, INTRO_BODY_M, "images/events/theNest.jpg");
/* 33 */     this.imageEventText.setDialogOption(OPTIONS[5]);
/*    */     
/* 35 */     if (AbstractDungeon.ascensionLevel >= 15) {
/* 36 */       this.goldGain = 50;
/*    */     } else {
/* 38 */       this.goldGain = 99;
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/*    */     RitualDagger ritualDagger;
/* 44 */     switch (this.screenNum) {
/*    */       case 0:
/* 46 */         this.imageEventText.updateBodyText(INTRO_BODY_M_2);
/* 47 */         this.imageEventText.setDialogOption(OPTIONS[0] + '\006' + OPTIONS[1], (AbstractCard)new RitualDagger());
/* 48 */         UnlockTracker.markCardAsSeen("RitualDagger");
/* 49 */         this.imageEventText.updateDialogOption(0, OPTIONS[2] + this.goldGain + OPTIONS[3]);
/* 50 */         this.screenNum = 1;
/*    */         return;
/*    */       
/*    */       case 1:
/* 54 */         switch (buttonPressed) {
/*    */           
/*    */           case 0:
/* 57 */             logMetricGainGold("Nest", "Stole From Cult", this.goldGain);
/* 58 */             this.imageEventText.updateBodyText(EXIT_BODY);
/* 59 */             this.screenNum = 2;
/* 60 */             AbstractDungeon.effectList.add(new RainingGoldEffect(this.goldGain));
/* 61 */             AbstractDungeon.player.gainGold(this.goldGain);
/* 62 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/* 63 */             this.imageEventText.clearRemainingOptions();
/*    */             break;
/*    */           case 1:
/* 66 */             ritualDagger = new RitualDagger();
/* 67 */             logMetricObtainCardAndDamage("Nest", "Joined the Cult", (AbstractCard)ritualDagger, 6);
/* 68 */             this.imageEventText.updateBodyText(ACCEPT_BODY);
/* 69 */             AbstractDungeon.player.damage(new DamageInfo(null, 6));
/* 70 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)ritualDagger, Settings.WIDTH * 0.3F, Settings.HEIGHT / 2.0F));
/*    */             
/* 72 */             this.screenNum = 2;
/* 73 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/* 74 */             this.imageEventText.clearRemainingOptions();
/*    */             break;
/*    */         } 
/*    */         
/*    */         return;
/*    */       
/*    */       case 2:
/* 81 */         openMap();
/*    */         return;
/*    */     } 
/* 84 */     openMap();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\Nest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */