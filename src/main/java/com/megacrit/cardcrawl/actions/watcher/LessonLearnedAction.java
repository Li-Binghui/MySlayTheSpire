/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class LessonLearnedAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private DamageInfo info;
/* 20 */   private AbstractCard theCard = null;
/*    */   
/*    */   public LessonLearnedAction(AbstractCreature target, DamageInfo info) {
/* 23 */     this.info = info;
/* 24 */     setValues(target, info);
/* 25 */     this.actionType = ActionType.DAMAGE;
/* 26 */     this.duration = Settings.ACTION_DUR_MED;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 31 */     if (this.duration == Settings.ACTION_DUR_MED && 
/* 32 */       this.target != null) {
/* 33 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE));
/* 34 */       this.target.damage(this.info);
/*    */       
/* 36 */       if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && 
/* 37 */         !this.target.hasPower("Minion")) {
/* 38 */         ArrayList<AbstractCard> possibleCards = new ArrayList<>();
/* 39 */         for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 40 */           if (c.canUpgrade()) {
/* 41 */             possibleCards.add(c);
/*    */           }
/*    */         } 
/*    */         
/* 45 */         if (!possibleCards.isEmpty()) {
/* 46 */           this.theCard = possibleCards.get(AbstractDungeon.miscRng.random(0, possibleCards.size() - 1));
/* 47 */           this.theCard.upgrade();
/* 48 */           AbstractDungeon.player.bottledCardUpgradeCheck(this.theCard);
/*    */         } 
/*    */       } 
/*    */       
/* 52 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 53 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */     } 
/*    */     
/* 57 */     tickDuration();
/*    */     
/* 59 */     if (this.isDone && this.theCard != null) {
/* 60 */       AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/* 61 */       AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(this.theCard.makeStatEquivalentCopy()));
/* 62 */       addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_MED));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\LessonLearnedAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */