/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class TheLibrary
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "The Library";
/*  21 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("The Library");
/*  22 */   public static final String NAME = eventStrings.NAME;
/*  23 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  24 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  26 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  27 */   private static final String SLEEP_RESULT = DESCRIPTIONS[1];
/*  28 */   private int screenNum = 0;
/*     */   
/*     */   private boolean pickCard = false;
/*     */   private static final float HP_HEAL_PERCENT = 0.33F;
/*     */   private static final float A_2_HP_HEAL_PERCENT = 0.2F;
/*     */   private int healAmt;
/*     */   
/*     */   public TheLibrary() {
/*  36 */     super(NAME, DIALOG_1, "images/events/library.jpg");
/*     */     
/*  38 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  39 */       this.healAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.2F);
/*     */     } else {
/*  41 */       this.healAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.33F);
/*     */     } 
/*     */     
/*  44 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*  45 */     this.imageEventText.setDialogOption(OPTIONS[1] + this.healAmt + OPTIONS[2]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  50 */     super.update();
/*  51 */     if (this.pickCard && 
/*  52 */       !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/*  53 */       AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeCopy();
/*  54 */       logMetricObtainCard("The Library", "Read", c);
/*  55 */       AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */       
/*  57 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     CardGroup group;
/*     */     int i;
/*  64 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/*  67 */         switch (buttonPressed) {
/*     */           case 0:
/*  69 */             this.imageEventText.updateBodyText(getBook());
/*  70 */             this.screenNum = 1;
/*  71 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  72 */             this.imageEventText.clearRemainingOptions();
/*  73 */             this.pickCard = true;
/*  74 */             group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*     */ 
/*     */             
/*  77 */             for (i = 0; i < 20; i++) {
/*  78 */               AbstractCard card = AbstractDungeon.getCard(AbstractDungeon.rollRarity()).makeCopy();
/*     */               
/*  80 */               boolean containsDupe = true;
/*  81 */               while (containsDupe) {
/*  82 */                 containsDupe = false;
/*     */                 
/*  84 */                 for (AbstractCard c : group.group) {
/*  85 */                   if (c.cardID.equals(card.cardID)) {
/*  86 */                     containsDupe = true;
/*  87 */                     card = AbstractDungeon.getCard(AbstractDungeon.rollRarity()).makeCopy();
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */ 
/*     */               
/*  93 */               if (!group.contains(card)) {
/*  94 */                 for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  95 */                   r.onPreviewObtainCard(card);
/*     */                 }
/*  97 */                 group.addToBottom(card);
/*     */               } else {
/*  99 */                 i--;
/*     */               } 
/*     */             } 
/*     */             
/* 103 */             for (AbstractCard c : group.group) {
/* 104 */               UnlockTracker.markCardAsSeen(c.cardID);
/*     */             }
/* 106 */             AbstractDungeon.gridSelectScreen.open(group, 1, OPTIONS[4], false);
/*     */             return;
/*     */         } 
/* 109 */         this.imageEventText.updateBodyText(SLEEP_RESULT);
/* 110 */         AbstractDungeon.player.heal(this.healAmt, true);
/* 111 */         logMetricHeal("The Library", "Heal", this.healAmt);
/* 112 */         this.screenNum = 1;
/* 113 */         this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/* 114 */         this.imageEventText.clearRemainingOptions();
/*     */         return;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 120 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getBook() {
/* 126 */     ArrayList<String> list = new ArrayList<>();
/* 127 */     list.add(DESCRIPTIONS[2]);
/* 128 */     list.add(DESCRIPTIONS[3]);
/* 129 */     list.add(DESCRIPTIONS[4]);
/* 130 */     return list.get(MathUtils.random(2));
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\TheLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */