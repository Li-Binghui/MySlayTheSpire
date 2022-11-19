/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ 
/*    */ public class DollysMirror extends AbstractRelic {
/*    */   public static final String ID = "DollysMirror";
/*    */   private boolean cardSelected = true;
/*    */   
/*    */   public DollysMirror() {
/* 14 */     super("DollysMirror", "mirror.png", RelicTier.SHOP, LandingSound.SOLID);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 24 */     this.cardSelected = false;
/* 25 */     if (AbstractDungeon.isScreenUp) {
/* 26 */       AbstractDungeon.dynamicBanner.hide();
/* 27 */       AbstractDungeon.overlayMenu.cancelButton.hide();
/* 28 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*    */     } 
/* 30 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.INCOMPLETE;
/*    */     
/* 32 */     AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, this.DESCRIPTIONS[1], false, false, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 44 */     super.update();
/* 45 */     if (!this.cardSelected && 
/* 46 */       AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
/* 47 */       this.cardSelected = true;
/*    */       
/* 49 */       AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeStatEquivalentCopy();
/* 50 */       c.inBottleFlame = false;
/* 51 */       c.inBottleLightning = false;
/* 52 */       c.inBottleTornado = false;
/* 53 */       AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */       
/* 55 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*    */       
/* 57 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 58 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 65 */     return new DollysMirror();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\DollysMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */