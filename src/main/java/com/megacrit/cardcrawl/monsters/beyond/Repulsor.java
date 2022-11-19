/*    */ package com.megacrit.cardcrawl.monsters.beyond;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.esotericsoftware.spine.AnimationState;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.cards.status.Dazed;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Repulsor extends AbstractMonster {
/* 19 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Repulsor"); public static final String ID = "Repulsor";
/* 20 */   public static final String NAME = monsterStrings.NAME;
/* 21 */   public static final String[] MOVES = monsterStrings.MOVES;
/* 22 */   public static final String[] DIALOG = monsterStrings.DIALOG; public static final String ENCOUNTER_NAME_W = "Ancient Shapes Weak";
/*    */   public static final String ENCOUNTER_NAME = "Ancient Shapes";
/*    */   private static final float HB_X = -8.0F;
/*    */   private static final float HB_Y = -10.0F;
/*    */   private static final float HB_W = 150.0F;
/*    */   private static final float HB_H = 150.0F;
/*    */   private static final byte DAZE = 1;
/*    */   private static final byte ATTACK = 2;
/*    */   private int attackDmg;
/*    */   private int dazeAmt;
/*    */   
/*    */   public Repulsor(float x, float y) {
/* 34 */     super(NAME, "Repulsor", 35, -8.0F, -10.0F, 150.0F, 150.0F, null, x, y + 10.0F);
/*    */     
/* 36 */     loadAnimation("images/monsters/theForest/repulser/skeleton.atlas", "images/monsters/theForest/repulser/skeleton.json", 1.0F);
/*    */ 
/*    */ 
/*    */     
/* 40 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/* 41 */     e.setTime(e.getEndTime() * MathUtils.random());
/*    */     
/* 43 */     this.dazeAmt = 2;
/*    */     
/* 45 */     if (AbstractDungeon.ascensionLevel >= 7) {
/* 46 */       setHp(31, 38);
/*    */     } else {
/* 48 */       setHp(29, 35);
/*    */     } 
/*    */     
/* 51 */     if (AbstractDungeon.ascensionLevel >= 2) {
/* 52 */       this.attackDmg = 13;
/*    */     } else {
/* 54 */       this.attackDmg = 11;
/*    */     } 
/*    */     
/* 57 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.attackDmg));
/*    */   }
/*    */ 
/*    */   
/*    */   public void takeTurn() {
/* 62 */     switch (this.nextMove) {
/*    */       case 2:
/* 64 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 65 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 66 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*    */         break;
/*    */       case 1:
/* 69 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Dazed(), this.dazeAmt, true, true));
/*    */         break;
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 76 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void getMove(int num) {
/* 81 */     if (num < 20 && !lastMove((byte)2)) {
/* 82 */       setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*    */       
/*    */       return;
/*    */     } 
/* 86 */     setMove((byte)1, Intent.DEBUFF);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\Repulsor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */