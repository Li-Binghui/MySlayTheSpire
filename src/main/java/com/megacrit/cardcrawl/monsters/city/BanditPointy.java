/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ 
/*     */ public class BanditPointy extends AbstractMonster {
/*     */   public static final String ID = "BanditChild";
/*  21 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("BanditChild");
/*  22 */   public static final String NAME = monsterStrings.NAME;
/*  23 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  24 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   public static final int HP = 30;
/*     */   
/*     */   public static final int A_2_HP_MIN = 34;
/*     */   
/*     */   private static final int ATTACK_DMG = 5;
/*     */   private static final int A_2_ATTACK_DMG = 6;
/*     */   private int attackDmg;
/*     */   private static final byte POINTY_SPECIAL = 1;
/*     */   
/*     */   public BanditPointy(float x, float y) {
/*  36 */     super(NAME, "BanditChild", 30, -5.0F, -4.0F, 190.0F, 180.0F, null, x, y);
/*  37 */     this.dialogX = 0.0F * Settings.scale;
/*  38 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  40 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  41 */       setHp(34);
/*     */     } else {
/*  43 */       setHp(30);
/*     */     } 
/*     */     
/*  46 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  47 */       this.attackDmg = 6;
/*     */     } else {
/*  49 */       this.attackDmg = 5;
/*     */     } 
/*     */     
/*  52 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.attackDmg, DamageInfo.DamageType.NORMAL));
/*     */     
/*  54 */     loadAnimation("images/monsters/theCity/pointy/skeleton.atlas", "images/monsters/theCity/pointy/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  58 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  59 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  60 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/*  61 */     this.state.setTimeScale(1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  66 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "SLASH"));
/*  67 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/*  68 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  69 */           .get(0), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
/*  70 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  71 */           .get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*  72 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)1, Intent.ATTACK, ((DamageInfo)this.damage
/*  73 */           .get(0)).base, 2, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void deathReact() {
/*  78 */     if (!isDeadOrEscaped()) {
/*  79 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0]));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/*  85 */     switch (key) {
/*     */       case "SLASH":
/*  87 */         this.state.setAnimation(0, "Attack", false);
/*  88 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/*  97 */     super.damage(info);
/*  98 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/*  99 */       this.state.setAnimation(0, "Hit", false);
/* 100 */       this.state.setTimeScale(1.0F);
/* 101 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 107 */     setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\BanditPointy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */