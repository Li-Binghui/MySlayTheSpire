/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PoisonLoseHpAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private static final float DURATION = 0.33F;
/*    */   
/*    */   public PoisonLoseHpAction(AbstractCreature target, AbstractCreature source, int amount, AttackEffect effect) {
/* 24 */     setValues(target, source, amount);
/* 25 */     this.actionType = ActionType.DAMAGE;
/* 26 */     this.attackEffect = effect;
/* 27 */     this.duration = 0.33F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 32 */     if ((AbstractDungeon.getCurrRoom()).phase != AbstractRoom.RoomPhase.COMBAT) {
/* 33 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 37 */     if (this.duration == 0.33F && this.target.currentHealth > 0) {
/* 38 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/*    */     }
/*    */ 
/*    */     
/* 42 */     tickDuration();
/*    */     
/* 44 */     if (this.isDone) {
/* 45 */       if (this.target.currentHealth > 0) {
/* 46 */         this.target.tint.color = Color.CHARTREUSE.cpy();
/* 47 */         this.target.tint.changeColor(Color.WHITE.cpy());
/* 48 */         this.target.damage(new DamageInfo(this.source, this.amount, DamageInfo.DamageType.HP_LOSS));
/*    */ 
/*    */         
/* 51 */         if (this.target.isDying) {
/* 52 */           AbstractPlayer.poisonKillCount++;
/* 53 */           if (AbstractPlayer.poisonKillCount == 3 && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT)
/*    */           {
/* 55 */             UnlockTracker.unlockAchievement("PLAGUE");
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 60 */       AbstractPower p = this.target.getPower("Poison");
/* 61 */       if (p != null) {
/* 62 */         p.amount--;
/* 63 */         if (p.amount == 0) {
/* 64 */           this.target.powers.remove(p);
/*    */         } else {
/* 66 */           p.updateDescription();
/*    */         } 
/*    */       } 
/*    */       
/* 70 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 71 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/* 73 */       addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\PoisonLoseHpAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */