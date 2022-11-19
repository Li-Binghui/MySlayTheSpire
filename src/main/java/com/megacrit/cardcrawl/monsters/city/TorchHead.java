/*    */ package com.megacrit.cardcrawl.monsters.city;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.TorchHeadFireEffect;
/*    */ 
/*    */ public class TorchHead extends AbstractMonster {
/* 20 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TorchHead"); public static final String ID = "TorchHead";
/* 21 */   public static final String NAME = monsterStrings.NAME;
/* 22 */   public static final String[] MOVES = monsterStrings.MOVES;
/* 23 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*    */   
/*    */   public static final int HP_MIN = 38;
/*    */   
/*    */   public static final int HP_MAX = 40;
/*    */   public static final int A_2_HP_MIN = 40;
/*    */   public static final int A_2_HP_MAX = 45;
/*    */   public static final int ATTACK_DMG = 7;
/*    */   private static final byte TACKLE = 1;
/* 32 */   private float fireTimer = 0.0F;
/*    */   private static final float FIRE_TIME = 0.04F;
/*    */   
/*    */   public TorchHead(float x, float y) {
/* 36 */     super(NAME, "TorchHead", AbstractDungeon.monsterHpRng.random(38, 40), -5.0F, -20.0F, 145.0F, 240.0F, null, x, y);
/* 37 */     setMove((byte)1, Intent.ATTACK, 7);
/* 38 */     this.damage.add(new DamageInfo((AbstractCreature)this, 7));
/*    */ 
/*    */     
/* 41 */     loadAnimation("images/monsters/theCity/torchHead/skeleton.atlas", "images/monsters/theCity/torchHead/skeleton.json", 1.0F);
/*    */ 
/*    */ 
/*    */     
/* 45 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/* 46 */     e.setTime(e.getEndTime() * MathUtils.random());
/*    */     
/* 48 */     if (AbstractDungeon.ascensionLevel >= 9) {
/* 49 */       setHp(40, 45);
/*    */     } else {
/* 51 */       setHp(38, 40);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void takeTurn() {
/* 57 */     switch (this.nextMove) {
/*    */       case 1:
/* 59 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 60 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 61 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/* 62 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)1, Intent.ATTACK, 7));
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 70 */     super.update();
/* 71 */     if (!this.isDying) {
/* 72 */       this.fireTimer -= Gdx.graphics.getDeltaTime();
/* 73 */       if (this.fireTimer < 0.0F) {
/* 74 */         this.fireTimer = 0.04F;
/* 75 */         AbstractDungeon.effectList.add(new TorchHeadFireEffect(this.skeleton
/*    */               
/* 77 */               .getX() + this.skeleton.findBone("fireslot").getX() + 10.0F * Settings.scale, this.skeleton
/* 78 */               .getY() + this.skeleton.findBone("fireslot").getY() + 110.0F * Settings.scale));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void getMove(int num) {
/* 85 */     setMove((byte)1, Intent.ATTACK, 7);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\TorchHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */