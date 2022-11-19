/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ 
/*     */ public class SnakeDagger extends AbstractMonster {
/*  21 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Dagger"); public static final String ID = "Dagger";
/*  22 */   public static final String NAME = monsterStrings.NAME;
/*  23 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  24 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final int HP_MIN = 20; private static final int HP_MAX = 25;
/*     */   private static final int STAB_DMG = 9;
/*     */   private static final int SACRIFICE_DMG = 25;
/*     */   private static final byte WOUND = 1;
/*     */   private static final byte EXPLODE = 2;
/*     */   public boolean firstMove = true;
/*     */   
/*     */   public SnakeDagger(float x, float y) {
/*  32 */     super(NAME, "Dagger", AbstractDungeon.monsterHpRng.random(20, 25), 0.0F, -50.0F, 140.0F, 130.0F, null, x, y);
/*  33 */     initializeAnimation();
/*     */ 
/*     */     
/*  36 */     this.damage.add(new DamageInfo((AbstractCreature)this, 9));
/*     */ 
/*     */     
/*  39 */     this.damage.add(new DamageInfo((AbstractCreature)this, 25));
/*  40 */     this.damage.add(new DamageInfo((AbstractCreature)this, 25, DamageInfo.DamageType.HP_LOSS));
/*     */   }
/*     */   
/*     */   public void initializeAnimation() {
/*  44 */     loadAnimation("images/monsters/theForest/mage_dagger/skeleton.atlas", "images/monsters/theForest/mage_dagger/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  50 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  55 */     switch (this.nextMove) {
/*     */       case 1:
/*  57 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  58 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/*  59 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  60 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*  61 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Wound(), 1));
/*     */         break;
/*     */       case 2:
/*  64 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "SUICIDE"));
/*  65 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/*  66 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  67 */               .get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*  68 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new LoseHPAction((AbstractCreature)this, (AbstractCreature)this, this.currentHealth));
/*     */         break;
/*     */     } 
/*  71 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/*  76 */     super.damage(info);
/*  77 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/*  78 */       this.state.setAnimation(0, "Hurt", false);
/*  79 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*  80 */       this.stateData.setMix("Hurt", "Idle", 0.1F);
/*  81 */       this.stateData.setMix("Idle", "Hurt", 0.1F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/*  87 */     if (this.firstMove) {
/*  88 */       this.firstMove = false;
/*  89 */       setMove((byte)1, Intent.ATTACK_DEBUFF, 9);
/*     */       
/*     */       return;
/*     */     } 
/*  93 */     setMove((byte)2, Intent.ATTACK, 25);
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/*  98 */     switch (key) {
/*     */       case "ATTACK":
/* 100 */         this.state.setAnimation(0, "Attack", false);
/* 101 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "SUICIDE":
/* 104 */         this.state.setAnimation(0, "Attack2", false);
/* 105 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\SnakeDagger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */