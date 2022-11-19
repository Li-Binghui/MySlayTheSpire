/*     */ package com.megacrit.cardcrawl.relics;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*     */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ 
/*     */ public class BottledFlame extends AbstractRelic {
/*     */   public static final String ID = "Bottled Flame";
/*     */   private boolean cardSelected = true;
/*  16 */   public AbstractCard card = null;
/*     */   
/*     */   public BottledFlame() {
/*  19 */     super("Bottled Flame", "bottledFlame.png", RelicTier.UNCOMMON, LandingSound.CLINK);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUpdatedDescription() {
/*  24 */     return this.DESCRIPTIONS[0];
/*     */   }
/*     */   
/*     */   public AbstractCard getCard() {
/*  28 */     return this.card.makeCopy();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEquip() {
/*  33 */     if (AbstractDungeon.player.masterDeck.getPurgeableCards().getAttacks().size() > 0) {
/*  34 */       this.cardSelected = false;
/*  35 */       if (AbstractDungeon.isScreenUp) {
/*  36 */         AbstractDungeon.dynamicBanner.hide();
/*  37 */         AbstractDungeon.overlayMenu.cancelButton.hide();
/*  38 */         AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*     */       } 
/*  40 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.INCOMPLETE;
/*  41 */       AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck
/*  42 */           .getPurgeableCards().getAttacks(), 1, this.DESCRIPTIONS[1] + this.name + LocalizedStrings.PERIOD, false, false, false, false);
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
/*  54 */     if (this.card != null) {
/*  55 */       AbstractCard cardInDeck = AbstractDungeon.player.masterDeck.getSpecificCard(this.card);
/*  56 */       if (cardInDeck != null) {
/*  57 */         cardInDeck.inBottleFlame = false;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  64 */     super.update();
/*  65 */     if (!this.cardSelected && 
/*  66 */       !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/*  67 */       this.cardSelected = true;
/*  68 */       this.card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/*  69 */       this.card.inBottleFlame = true;
/*  70 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*  71 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*  72 */       this.description = this.DESCRIPTIONS[2] + FontHelper.colorString(this.card.name, "y") + this.DESCRIPTIONS[3];
/*  73 */       this.tips.clear();
/*  74 */       this.tips.add(new PowerTip(this.name, this.description));
/*  75 */       initializeTips();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescriptionAfterLoading() {
/*  81 */     this.description = this.DESCRIPTIONS[2] + FontHelper.colorString(this.card.name, "y") + this.DESCRIPTIONS[3];
/*  82 */     this.tips.clear();
/*  83 */     this.tips.add(new PowerTip(this.name, this.description));
/*  84 */     initializeTips();
/*     */   }
/*     */ 
/*     */   
/*     */   public void atBattleStart() {
/*  89 */     flash();
/*  90 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawn() {
/*  95 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/*  96 */       if (c.type == AbstractCard.CardType.ATTACK && c.rarity != AbstractCard.CardRarity.BASIC) {
/*  97 */         return true;
/*     */       }
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractRelic makeCopy() {
/* 105 */     return new BottledFlame();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\BottledFlame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */