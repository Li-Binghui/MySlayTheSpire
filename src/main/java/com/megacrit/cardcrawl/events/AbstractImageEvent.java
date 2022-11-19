/*    */ package com.megacrit.cardcrawl.events;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.EventStrings;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.vfx.scene.EventBgParticle;
/*    */ 
/*    */ 
/*    */ public abstract class AbstractImageEvent
/*    */   extends AbstractEvent
/*    */ {
/*    */   protected String title;
/* 17 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Proceed Screen");
/* 18 */   private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*    */   
/*    */   public AbstractImageEvent(String title, String body, String imgUrl) {
/* 21 */     this.imageEventText.clear();
/* 22 */     this.roomEventText.clear();
/* 23 */     this.title = title;
/* 24 */     this.body = body;
/* 25 */     this.imageEventText.loadImage(imgUrl);
/* 26 */     type = EventType.IMAGE;
/* 27 */     this.noCardsInRewards = false;
/*    */   }
/*    */   
/*    */   public void update() {
/* 31 */     if (!this.combatTime) {
/* 32 */       this.hasFocus = true;
/* 33 */       if (MathUtils.randomBoolean(0.1F)) {
/* 34 */         AbstractDungeon.effectList.add(new EventBgParticle());
/*    */       }
/*    */       
/* 37 */       if (this.waitTimer > 0.0F) {
/* 38 */         this.waitTimer -= Gdx.graphics.getDeltaTime();
/* 39 */         if (this.waitTimer < 0.0F) {
/* 40 */           this.imageEventText.show(this.title, this.body);
/* 41 */           this.waitTimer = 0.0F;
/*    */         } 
/*    */       } 
/*    */       
/* 45 */       if (!GenericEventDialog.waitForInput) {
/* 46 */         buttonEffect(GenericEventDialog.getSelectedOption());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void showProceedScreen(String bodyText) {
/* 53 */     this.imageEventText.updateBodyText(bodyText);
/* 54 */     this.imageEventText.updateDialogOption(0, DESCRIPTIONS[0]);
/* 55 */     this.imageEventText.clearRemainingOptions();
/* 56 */     this.screenNum = 99;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   protected void openMap() {
/* 63 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 64 */     AbstractDungeon.dungeonMapScreen.open(false);
/*    */   }
/*    */   
/*    */   public void enterCombatFromImage() {
/* 68 */     (AbstractDungeon.getCurrRoom()).smoked = false;
/* 69 */     AbstractDungeon.player.isEscaping = false;
/* 70 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMBAT;
/* 71 */     (AbstractDungeon.getCurrRoom()).monsters.init();
/* 72 */     AbstractRoom.waitTimer = 0.1F;
/* 73 */     AbstractDungeon.player.preBattlePrep();
/* 74 */     this.hasFocus = false;
/* 75 */     GenericEventDialog.hide();
/* 76 */     CardCrawlGame.fadeIn(1.5F);
/* 77 */     AbstractDungeon.rs = AbstractDungeon.RenderScene.NORMAL;
/* 78 */     this.combatTime = true;
/*    */   }
/*    */   
/*    */   public void enterImageFromCombat() {
/* 82 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.EVENT;
/* 83 */     (AbstractDungeon.getCurrRoom()).isBattleOver = false;
/* 84 */     (AbstractDungeon.getCurrRoom()).monsters.monsters.clear();
/* 85 */     (AbstractDungeon.getCurrRoom()).rewards.clear();
/* 86 */     this.hasDialog = true;
/* 87 */     this.hasFocus = true;
/* 88 */     this.combatTime = false;
/* 89 */     GenericEventDialog.show();
/* 90 */     CardCrawlGame.fadeIn(1.5F);
/* 91 */     AbstractDungeon.rs = AbstractDungeon.RenderScene.EVENT;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\AbstractImageEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */