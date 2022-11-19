/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.HealAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class ToyOrnithopter extends AbstractRelic {
/*    */   public static final String ID = "Toy Ornithopter";
/*    */   
/*    */   public ToyOrnithopter() {
/* 13 */     super("Toy Ornithopter", "ornithopter.png", RelicTier.COMMON, LandingSound.FLAT);
/*    */   }
/*    */   public static final int HEAL_AMT = 5;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\005' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUsePotion() {
/* 23 */     flash();
/* 24 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 25 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 26 */       addToBot((AbstractGameAction)new HealAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 5));
/*    */     } else {
/* 28 */       AbstractDungeon.player.heal(5);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 34 */     return new ToyOrnithopter();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\ToyOrnithopter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */