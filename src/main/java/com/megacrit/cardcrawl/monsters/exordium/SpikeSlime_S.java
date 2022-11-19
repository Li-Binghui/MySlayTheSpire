/*    */ package com.megacrit.cardcrawl.monsters.exordium;
/*    */ 
/*    */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
/*    */ 
/*    */ public class SpikeSlime_S extends AbstractMonster {
/* 19 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SpikeSlime_S"); public static final String ID = "SpikeSlime_S";
/* 20 */   public static final String NAME = monsterStrings.NAME;
/* 21 */   public static final String[] MOVES = monsterStrings.MOVES;
/* 22 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*    */   
/*    */   public static final int HP_MIN = 10;
/*    */   public static final int HP_MAX = 14;
/*    */   public static final int A_2_HP_MIN = 11;
/*    */   public static final int A_2_HP_MAX = 15;
/*    */   public static final int TACKLE_DAMAGE = 5;
/*    */   public static final int A_2_TACKLE_DAMAGE = 6;
/*    */   private static final byte TACKLE = 1;
/*    */   
/*    */   public SpikeSlime_S(float x, float y, int poisonAmount) {
/* 33 */     super(NAME, "SpikeSlime_S", 14, 0.0F, -24.0F, 130.0F, 100.0F, null, x, y);
/*    */     
/* 35 */     if (AbstractDungeon.ascensionLevel >= 7) {
/* 36 */       setHp(11, 15);
/*    */     } else {
/* 38 */       setHp(10, 14);
/*    */     } 
/*    */     
/* 41 */     if (AbstractDungeon.ascensionLevel >= 2) {
/* 42 */       this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*    */     } else {
/* 44 */       this.damage.add(new DamageInfo((AbstractCreature)this, 5));
/*    */     } 
/*    */     
/* 47 */     if (poisonAmount >= 1) {
/* 48 */       this.powers.add(new PoisonPower((AbstractCreature)this, (AbstractCreature)this, poisonAmount));
/*    */     }
/*    */     
/* 51 */     loadAnimation("images/monsters/theBottom/slimeAltS/skeleton.atlas", "images/monsters/theBottom/slimeAltS/skeleton.json", 1.0F);
/*    */ 
/*    */ 
/*    */     
/* 55 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/* 56 */     e.setTime(e.getEndTime() * MathUtils.random());
/* 57 */     this.state.addListener((AnimationState.AnimationStateListener)new SlimeAnimListener());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void takeTurn() {
/* 63 */     switch (this.nextMove) {
/*    */       case 1:
/* 65 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/* 66 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 67 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 68 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void getMove(int num) {
/* 75 */     setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\SpikeSlime_S.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */