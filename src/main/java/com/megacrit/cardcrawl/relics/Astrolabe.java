/*     */ package com.megacrit.cardcrawl.relics;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Astrolabe
/*     */   extends AbstractRelic
/*     */ {
/*     */   public static final String ID = "Astrolabe";
/*     */   private boolean cardsSelected = true;
/*     */   
/*     */   public Astrolabe() {
/*  19 */     super("Astrolabe", "astrolabe.png", RelicTier.BOSS, LandingSound.CLINK);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUpdatedDescription() {
/*  24 */     return this.DESCRIPTIONS[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEquip() {
/*  29 */     this.cardsSelected = false;
/*  30 */     CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*  31 */     for (AbstractCard card : (AbstractDungeon.player.masterDeck.getPurgeableCards()).group) {
/*  32 */       tmp.addToTop(card);
/*     */     }
/*     */     
/*  35 */     if (tmp.group.isEmpty()) {
/*  36 */       this.cardsSelected = true; return;
/*     */     } 
/*  38 */     if (tmp.group.size() <= 3) {
/*  39 */       giveCards(tmp.group);
/*     */     }
/*  41 */     else if (!AbstractDungeon.isScreenUp) {
/*  42 */       AbstractDungeon.gridSelectScreen.open(tmp, 3, this.DESCRIPTIONS[1] + this.name + LocalizedStrings.PERIOD, false, false, false, false);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/*  51 */       AbstractDungeon.dynamicBanner.hide();
/*  52 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*  53 */       AbstractDungeon.gridSelectScreen.open(tmp, 3, this.DESCRIPTIONS[1] + this.name + LocalizedStrings.PERIOD, false, false, false, false);
/*     */     } 
/*     */   }
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
/*     */   public void update() {
/*  67 */     super.update();
/*  68 */     if (!this.cardsSelected && 
/*  69 */       AbstractDungeon.gridSelectScreen.selectedCards.size() == 3) {
/*  70 */       giveCards(AbstractDungeon.gridSelectScreen.selectedCards);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void giveCards(ArrayList<AbstractCard> group) {
/*  76 */     this.cardsSelected = true;
/*  77 */     float displayCount = 0.0F;
/*  78 */     for (Iterator<AbstractCard> i = group.iterator(); i.hasNext(); ) {
/*  79 */       AbstractCard card = i.next();
/*  80 */       card.untip();
/*  81 */       card.unhover();
/*  82 */       AbstractDungeon.player.masterDeck.removeCard(card);
/*  83 */       AbstractDungeon.transformCard(card, true, AbstractDungeon.miscRng);
/*     */       
/*  85 */       if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.TRANSFORM && AbstractDungeon.transformedCard != null) {
/*     */         
/*  87 */         AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(
/*     */               
/*  89 */               AbstractDungeon.getTransformedCard(), Settings.WIDTH / 3.0F + displayCount, Settings.HEIGHT / 2.0F, false));
/*     */ 
/*     */ 
/*     */         
/*  93 */         displayCount += Settings.WIDTH / 6.0F;
/*     */       } 
/*     */     } 
/*  96 */     AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*  97 */     (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractRelic makeCopy() {
/* 102 */     return new Astrolabe();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Astrolabe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */