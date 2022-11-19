/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ 
/*    */ public class FlickerAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private DamageInfo info;
/*    */   private AbstractCard card;
/*    */   
/*    */   public FlickerAction(AbstractCreature target, DamageInfo info, AbstractCard card) {
/* 20 */     this.info = info;
/* 21 */     this.card = card;
/* 22 */     setValues(target, info);
/* 23 */     this.actionType = ActionType.DAMAGE;
/* 24 */     this.duration = Settings.ACTION_DUR_FASTER;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     if (this.duration == Settings.ACTION_DUR_FASTER && 
/* 30 */       this.target != null) {
/* 31 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY));
/*    */       
/* 33 */       this.target.damage(this.info);
/*    */ 
/*    */       
/* 36 */       if (((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) {
/* 37 */         addToBot(new FlickerReturnToHandAction(this.card));
/*    */       }
/*    */       
/* 40 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 41 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 46 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\FlickerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */