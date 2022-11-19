/*     */ package com.megacrit.cardcrawl.rooms;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.MetallicizePower;
/*     */ import com.megacrit.cardcrawl.powers.RegenerateMonsterPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MonsterRoomElite
/*     */   extends MonsterRoom
/*     */ {
/*     */   public void applyEmeraldEliteBuff() {
/*  36 */     if (Settings.isFinalActAvailable && (AbstractDungeon.getCurrMapNode()).hasEmeraldKey) {
/*  37 */       switch (AbstractDungeon.mapRng.random(0, 3)) {
/*     */         case 0:
/*  39 */           for (AbstractMonster m : this.monsters.monsters) {
/*  40 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new StrengthPower((AbstractCreature)m, AbstractDungeon.actNum + 1), AbstractDungeon.actNum + 1));
/*     */           }
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/*  50 */           for (AbstractMonster m : this.monsters.monsters) {
/*  51 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new IncreaseMaxHpAction(m, 0.25F, true));
/*     */           }
/*     */           break;
/*     */         case 2:
/*  55 */           for (AbstractMonster m : this.monsters.monsters) {
/*  56 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new MetallicizePower((AbstractCreature)m, AbstractDungeon.actNum * 2 + 2), AbstractDungeon.actNum * 2 + 2));
/*     */           }
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/*  65 */           for (AbstractMonster m : this.monsters.monsters) {
/*  66 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new RegenerateMonsterPower(m, 1 + AbstractDungeon.actNum * 2), 1 + AbstractDungeon.actNum * 2));
/*     */           }
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlayerEntry() {
/*  82 */     playBGM(null);
/*  83 */     if (this.monsters == null) {
/*  84 */       this.monsters = CardCrawlGame.dungeon.getEliteMonsterForRoomCreation();
/*  85 */       this.monsters.init();
/*     */     } 
/*     */     
/*  88 */     waitTimer = 0.1F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropReward() {
/*  93 */     AbstractRelic.RelicTier tier = returnRandomRelicTier();
/*  94 */     if (Settings.isEndless && AbstractDungeon.player.hasBlight("MimicInfestation")) {
/*     */       
/*  96 */       AbstractDungeon.player.getBlight("MimicInfestation").flash();
/*     */     } else {
/*  98 */       addRelicToRewards(tier);
/*  99 */       if (AbstractDungeon.player.hasRelic("Black Star")) {
/* 100 */         addNoncampRelicToRewards(returnRandomRelicTier());
/*     */       }
/*     */       
/* 103 */       addEmeraldKey();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addEmeraldKey() {
/* 108 */     if (Settings.isFinalActAvailable && !Settings.hasEmeraldKey && !this.rewards.isEmpty() && 
/* 109 */       (AbstractDungeon.getCurrMapNode()).hasEmeraldKey) {
/* 110 */       this.rewards.add(new RewardItem(this.rewards.get(this.rewards.size() - 1), RewardItem.RewardType.EMERALD_KEY));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AbstractRelic.RelicTier returnRandomRelicTier() {
/* 120 */     int roll = AbstractDungeon.relicRng.random(0, 99);
/*     */     
/* 122 */     if (ModHelper.isModEnabled("Elite Swarm")) {
/* 123 */       roll += 10;
/*     */     }
/*     */ 
/*     */     
/* 127 */     if (roll < 50) {
/* 128 */       return AbstractRelic.RelicTier.COMMON;
/*     */     }
/* 130 */     if (roll > 82) {
/* 131 */       return AbstractRelic.RelicTier.RARE;
/*     */     }
/*     */ 
/*     */     
/* 135 */     return AbstractRelic.RelicTier.UNCOMMON;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard.CardRarity getCardRarity(int roll) {
/* 140 */     if (ModHelper.isModEnabled("Elite Swarm")) {
/* 141 */       return AbstractCard.CardRarity.RARE;
/*     */     }
/*     */     
/* 144 */     return super.getCardRarity(roll);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\MonsterRoomElite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */