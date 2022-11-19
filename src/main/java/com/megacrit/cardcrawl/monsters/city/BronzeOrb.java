/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.unique.ApplyStasisAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
/*     */ 
/*     */ public class BronzeOrb extends AbstractMonster {
/*     */   public static final String ID = "BronzeOrb";
/*  23 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("BronzeOrb");
/*  24 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  25 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int HP_MIN = 52;
/*     */   
/*     */   private static final int HP_MAX = 58;
/*     */   
/*     */   private static final int A_2_HP_MIN = 54;
/*     */   private static final int A_2_HP_MAX = 60;
/*     */   private static final int BEAM_DMG = 8;
/*     */   
/*     */   public BronzeOrb(float x, float y, int count) {
/*  36 */     super(monsterStrings.NAME, "BronzeOrb", AbstractDungeon.monsterHpRng
/*     */ 
/*     */         
/*  39 */         .random(52, 58), 0.0F, 0.0F, 160.0F, 160.0F, "images/monsters/theCity/automaton/orb.png", x, y);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  49 */       setHp(54, 60);
/*     */     } else {
/*  51 */       setHp(52, 58);
/*     */     } 
/*     */     
/*  54 */     this.count = count;
/*  55 */     this.damage.add(new DamageInfo((AbstractCreature)this, 8));
/*     */   }
/*     */   private static final int BLOCK_AMT = 12; private static final byte BEAM = 1; private static final byte SUPPORT_BEAM = 2; private static final byte STASIS = 3; private boolean usedStasis = false; private int count;
/*     */   
/*     */   public void takeTurn() {
/*  60 */     switch (this.nextMove) {
/*     */       case 1:
/*  62 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
/*  63 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.SKY)));
/*  64 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX, this.hb.cY), 0.3F));
/*     */ 
/*     */ 
/*     */         
/*  68 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  69 */               .get(0), AbstractGameAction.AttackEffect.NONE));
/*     */         break;
/*     */       case 2:
/*  72 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction(
/*  73 */               (AbstractCreature)AbstractDungeon.getMonsters().getMonster("BronzeAutomaton"), (AbstractCreature)this, 12));
/*     */         break;
/*     */       case 3:
/*  76 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyStasisAction((AbstractCreature)this));
/*     */         break;
/*     */     } 
/*  79 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  84 */     super.update();
/*  85 */     if (this.count % 2 == 0) {
/*  86 */       this.animY = MathUtils.cosDeg((float)(System.currentTimeMillis() / 6L % 360L)) * 6.0F * Settings.scale;
/*     */     } else {
/*  88 */       this.animY = -MathUtils.cosDeg((float)(System.currentTimeMillis() / 6L % 360L)) * 6.0F * Settings.scale;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/*  94 */     if (!this.usedStasis && num >= 25) {
/*  95 */       setMove((byte)3, Intent.STRONG_DEBUFF);
/*  96 */       this.usedStasis = true;
/*     */       
/*     */       return;
/*     */     } 
/* 100 */     if (num >= 70 && !lastTwoMoves((byte)2)) {
/* 101 */       setMove((byte)2, Intent.DEFEND); return;
/*     */     } 
/* 103 */     if (!lastTwoMoves((byte)1)) {
/* 104 */       setMove((byte)1, Intent.ATTACK, 8);
/*     */       
/*     */       return;
/*     */     } 
/* 108 */     setMove((byte)2, Intent.DEFEND);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\BronzeOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */