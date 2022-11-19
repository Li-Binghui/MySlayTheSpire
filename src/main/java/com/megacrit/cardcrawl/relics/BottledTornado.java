/*     */ package com.megacrit.cardcrawl.relics;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.CardHelper;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*     */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ 
/*     */ public class BottledTornado extends AbstractRelic {
/*     */   public static final String ID = "Bottled Tornado";
/*     */   private boolean cardSelected = true;
/*  17 */   public AbstractCard card = null;
/*     */   
/*     */   public BottledTornado() {
/*  20 */     super("Bottled Tornado", "bottledTornado.png", RelicTier.UNCOMMON, LandingSound.CLINK);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUpdatedDescription() {
/*  25 */     return this.DESCRIPTIONS[0];
/*     */   }
/*     */   
/*     */   public AbstractCard getCard() {
/*  29 */     return this.card.makeCopy();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEquip() {
/*  34 */     if (AbstractDungeon.player.masterDeck.getPurgeableCards().getPowers().size() > 0) {
/*  35 */       this.cardSelected = false;
/*  36 */       if (AbstractDungeon.isScreenUp) {
/*  37 */         AbstractDungeon.dynamicBanner.hide();
/*  38 */         AbstractDungeon.overlayMenu.cancelButton.hide();
/*  39 */         AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*     */       } 
/*  41 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.INCOMPLETE;
/*  42 */       AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck
/*  43 */           .getPurgeableCards().getPowers(), 1, this.DESCRIPTIONS[1] + this.name + LocalizedStrings.PERIOD, false, false, false, false);
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
/*     */   public void onUnequip() {
/*  55 */     if (this.card != null) {
/*  56 */       AbstractCard cardInDeck = AbstractDungeon.player.masterDeck.getSpecificCard(this.card);
/*  57 */       if (cardInDeck != null) {
/*  58 */         cardInDeck.inBottleTornado = false;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  65 */     super.update();
/*  66 */     if (!this.cardSelected && 
/*  67 */       !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/*  68 */       this.cardSelected = true;
/*  69 */       this.card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/*  70 */       this.card.inBottleTornado = true;
/*  71 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*     */       
/*  73 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*  74 */       this.description = this.DESCRIPTIONS[2] + FontHelper.colorString(this.card.name, "y") + this.DESCRIPTIONS[3];
/*  75 */       this.tips.clear();
/*  76 */       this.tips.add(new PowerTip(this.name, this.description));
/*  77 */       initializeTips();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescriptionAfterLoading() {
/*  83 */     this.description = this.DESCRIPTIONS[2] + FontHelper.colorString(this.card.name, "y") + this.DESCRIPTIONS[3];
/*  84 */     this.tips.clear();
/*  85 */     this.tips.add(new PowerTip(this.name, this.description));
/*  86 */     initializeTips();
/*     */   }
/*     */ 
/*     */   
/*     */   public void atBattleStart() {
/*  91 */     flash();
/*  92 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawn() {
/*  97 */     return CardHelper.hasCardType(AbstractCard.CardType.POWER);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractRelic makeCopy() {
/* 102 */     return new BottledTornado();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\BottledTornado.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */