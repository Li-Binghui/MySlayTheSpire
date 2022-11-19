/*    */ package com.megacrit.cardcrawl.vfx.cardManip;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.ExhaustBlurEffect;
/*    */ import com.megacrit.cardcrawl.vfx.ExhaustEmberEffect;
/*    */ 
/*    */ public class ExhaustCardEffect extends AbstractGameEffect {
/*    */   private AbstractCard c;
/*    */   private static final float DUR = 1.0F;
/*    */   
/*    */   public ExhaustCardEffect(AbstractCard c) {
/* 17 */     this.duration = 1.0F;
/* 18 */     this.c = c;
/*    */   }
/*    */   
/*    */   public void update() {
/* 22 */     if (this.duration == 1.0F) {
/* 23 */       CardCrawlGame.sound.play("CARD_EXHAUST", 0.2F); int i;
/* 24 */       for (i = 0; i < 90; i++) {
/* 25 */         AbstractDungeon.effectsQueue.add(new ExhaustBlurEffect(this.c.current_x, this.c.current_y));
/*    */       }
/* 27 */       for (i = 0; i < 50; i++) {
/* 28 */         AbstractDungeon.effectsQueue.add(new ExhaustEmberEffect(this.c.current_x, this.c.current_y));
/*    */       }
/*    */     } 
/*    */     
/* 32 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 33 */     if (!this.c.fadingOut && this.duration < 0.7F && 
/* 34 */       !AbstractDungeon.player.hand.contains(this.c)) {
/* 35 */       this.c.fadingOut = true;
/*    */     }
/*    */     
/* 38 */     if (this.duration < 0.0F) {
/* 39 */       this.isDone = true;
/* 40 */       this.c.resetAttributes();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 46 */     this.c.render(sb);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\cardManip\ExhaustCardEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */