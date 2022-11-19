/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ public class ChannelAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractOrb orbType;
/*    */   private boolean autoEvoke = false;
/*    */   
/*    */   public ChannelAction(AbstractOrb newOrbType) {
/* 14 */     this(newOrbType, true);
/*    */   }
/*    */   
/*    */   public ChannelAction(AbstractOrb newOrbType, boolean autoEvoke) {
/* 18 */     this.duration = Settings.ACTION_DUR_FAST;
/* 19 */     this.orbType = newOrbType;
/* 20 */     this.autoEvoke = autoEvoke;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 26 */       if (this.autoEvoke) {
/* 27 */         AbstractDungeon.player.channelOrb(this.orbType);
/*    */       } else {
/*    */         
/* 30 */         for (AbstractOrb o : AbstractDungeon.player.orbs) {
/* 31 */           if (o instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot) {
/* 32 */             AbstractDungeon.player.channelOrb(this.orbType);
/*    */             
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/* 38 */       if (Settings.FAST_MODE) {
/* 39 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/* 44 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\ChannelAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */