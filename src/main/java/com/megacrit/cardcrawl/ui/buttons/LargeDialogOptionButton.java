/*     */ package com.megacrit.cardcrawl.ui.buttons;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.RoomEventDialog;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ 
/*     */ 
/*     */ public class LargeDialogOptionButton
/*     */ {
/*  22 */   private static final float OPTION_SPACING_Y = -82.0F * Settings.scale;
/*  23 */   private static final float TEXT_ADJUST_X = -400.0F * Settings.xScale; private static final float TEXT_ADJUST_Y = 10.0F * Settings.scale;
/*     */   public String msg;
/*  25 */   private Color textColor = new Color(0.0F, 0.0F, 0.0F, 0.0F); private Color boxColor = new Color(0.0F, 0.0F, 0.0F, 0.0F); private float x;
/*  26 */   private float y = -9999.0F * Settings.scale;
/*     */   public Hitbox hb;
/*     */   private static final float ANIM_TIME = 0.5F;
/*  29 */   private float animTimer = 0.5F; private float alpha = 0.0F;
/*  30 */   private static final Color TEXT_ACTIVE_COLOR = Color.WHITE.cpy();
/*  31 */   private static final Color TEXT_INACTIVE_COLOR = new Color(0.8F, 0.8F, 0.8F, 1.0F);
/*  32 */   private static final Color TEXT_DISABLED_COLOR = Color.FIREBRICK.cpy();
/*  33 */   private Color boxInactiveColor = new Color(0.2F, 0.25F, 0.25F, 0.0F);
/*     */   public boolean pressed = false;
/*     */   public boolean isDisabled;
/*  36 */   public int slot = 0;
/*     */   
/*     */   private AbstractCard cardToPreview;
/*     */   
/*     */   private AbstractRelic relicToPreview;
/*     */   
/*     */   private static final int W = 890;
/*     */   
/*     */   private static final int H = 77;
/*     */ 
/*     */   
/*     */   public LargeDialogOptionButton(int slot, String msg, boolean isDisabled, AbstractCard previewCard, AbstractRelic previewRelic) {
/*  48 */     switch (AbstractEvent.type) {
/*     */       case TEXT:
/*  50 */         this.x = 895.0F * Settings.xScale;
/*     */         break;
/*     */       case IMAGE:
/*  53 */         this.x = 1260.0F * Settings.xScale;
/*     */         break;
/*     */       case ROOM:
/*  56 */         this.x = 620.0F * Settings.xScale;
/*     */         break;
/*     */     } 
/*  59 */     this.slot = slot;
/*  60 */     this.isDisabled = isDisabled;
/*  61 */     this.cardToPreview = previewCard;
/*  62 */     this.relicToPreview = previewRelic;
/*     */     
/*  64 */     if (isDisabled) {
/*  65 */       this.msg = stripColor(msg);
/*     */     } else {
/*  67 */       this.msg = msg;
/*     */     } 
/*     */     
/*  70 */     this.hb = new Hitbox(892.0F * Settings.xScale, 80.0F * Settings.yScale);
/*     */   }
/*     */   
/*     */   public LargeDialogOptionButton(int slot, String msg, AbstractCard previewCard, AbstractRelic previewRelic) {
/*  74 */     this(slot, msg, false, previewCard, previewRelic);
/*     */   }
/*     */   
/*     */   public LargeDialogOptionButton(int slot, String msg, boolean isDisabled, AbstractCard previewCard) {
/*  78 */     this(slot, msg, isDisabled, previewCard, null);
/*     */   }
/*     */   
/*     */   public LargeDialogOptionButton(int slot, String msg, boolean isDisabled, AbstractRelic previewRelic) {
/*  82 */     this(slot, msg, isDisabled, null, previewRelic);
/*     */   }
/*     */   
/*     */   public LargeDialogOptionButton(int slot, String msg) {
/*  86 */     this(slot, msg, false, null, null);
/*     */   }
/*     */   
/*     */   public LargeDialogOptionButton(int slot, String msg, boolean isDisabled) {
/*  90 */     this(slot, msg, isDisabled, null, null);
/*     */   }
/*     */   
/*     */   public LargeDialogOptionButton(int slot, String msg, AbstractCard previewCard) {
/*  94 */     this(slot, msg, false, previewCard);
/*     */   }
/*     */   
/*     */   public LargeDialogOptionButton(int slot, String msg, AbstractRelic previewRelic) {
/*  98 */     this(slot, msg, false, previewRelic);
/*     */   }
/*     */   
/*     */   private String stripColor(String input) {
/* 102 */     input = input.replace("#r", "");
/* 103 */     input = input.replace("#g", "");
/* 104 */     input = input.replace("#b", "");
/* 105 */     input = input.replace("#y", "");
/* 106 */     return input;
/*     */   }
/*     */   
/*     */   public void calculateY(int size) {
/* 110 */     if (AbstractEvent.type != AbstractEvent.EventType.ROOM) {
/* 111 */       this.y = Settings.OPTION_Y - 424.0F * Settings.scale;
/* 112 */       this.y += this.slot * OPTION_SPACING_Y;
/* 113 */       this.y -= size * OPTION_SPACING_Y;
/*     */     } else {
/* 115 */       this.y = Settings.OPTION_Y - 500.0F * Settings.scale;
/* 116 */       this.y += this.slot * OPTION_SPACING_Y;
/* 117 */       this.y -= RoomEventDialog.optionList.size() * OPTION_SPACING_Y;
/*     */     } 
/* 119 */     this.hb.move(this.x, this.y);
/*     */   }
/*     */   
/*     */   public void update(int size) {
/* 123 */     calculateY(size);
/* 124 */     hoverAndClickLogic();
/* 125 */     updateAnimation();
/*     */   }
/*     */   
/*     */   private void updateAnimation() {
/* 129 */     if (this.animTimer != 0.0F) {
/* 130 */       this.animTimer -= Gdx.graphics.getDeltaTime();
/* 131 */       if (this.animTimer < 0.0F) {
/* 132 */         this.animTimer = 0.0F;
/*     */       }
/* 134 */       this.alpha = Interpolation.exp5In.apply(0.0F, 1.0F, 1.0F - this.animTimer / 0.5F);
/*     */     } 
/* 136 */     this.textColor.a = this.alpha;
/* 137 */     this.boxColor.a = this.alpha;
/*     */   }
/*     */ 
/*     */   
/*     */   private void hoverAndClickLogic() {
/* 142 */     this.hb.update();
/* 143 */     if (this.hb.hovered && InputHelper.justClickedLeft && !this.isDisabled && this.animTimer < 0.1F) {
/* 144 */       InputHelper.justClickedLeft = false;
/* 145 */       this.hb.clickStarted = true;
/*     */     } 
/*     */     
/* 148 */     if (this.hb.hovered && CInputActionSet.select.isJustPressed() && !this.isDisabled) {
/* 149 */       this.hb.clicked = true;
/*     */     }
/*     */     
/* 152 */     if (this.hb.clicked) {
/* 153 */       this.hb.clicked = false;
/* 154 */       this.pressed = true;
/*     */     } 
/*     */     
/* 157 */     if (!this.isDisabled) {
/* 158 */       if (this.hb.hovered) {
/* 159 */         this.textColor = TEXT_ACTIVE_COLOR;
/* 160 */         this.boxColor = Color.WHITE.cpy();
/*     */       } else {
/* 162 */         this.textColor = TEXT_INACTIVE_COLOR;
/* 163 */         this.boxColor = new Color(0.4F, 0.4F, 0.4F, 1.0F);
/*     */       } 
/*     */     } else {
/* 166 */       this.textColor = TEXT_DISABLED_COLOR;
/* 167 */       this.boxColor = this.boxInactiveColor;
/*     */     } 
/*     */ 
/*     */     
/* 171 */     if (this.hb.hovered) {
/* 172 */       if (!this.isDisabled) {
/* 173 */         this.textColor = TEXT_ACTIVE_COLOR;
/*     */       } else {
/* 175 */         this.textColor = Color.GRAY.cpy();
/*     */       }
/*     */     
/* 178 */     } else if (!this.isDisabled) {
/* 179 */       this.textColor = TEXT_ACTIVE_COLOR;
/*     */     } else {
/* 181 */       this.textColor = Color.GRAY.cpy();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 187 */     float scale = Settings.scale;
/* 188 */     float xScale = Settings.xScale;
/*     */     
/* 190 */     if (this.hb.clickStarted) {
/* 191 */       scale *= 0.99F;
/* 192 */       xScale *= 0.99F;
/*     */     } 
/*     */     
/* 195 */     if (this.isDisabled) {
/* 196 */       sb.setColor(Color.WHITE);
/* 197 */       sb.draw(ImageMaster.EVENT_BUTTON_DISABLED, this.x - 445.0F, this.y - 38.5F, 445.0F, 38.5F, 890.0F, 77.0F, xScale, scale, 0.0F, 0, 0, 890, 77, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 215 */       sb.setColor(this.boxColor);
/* 216 */       sb.draw(ImageMaster.EVENT_BUTTON_ENABLED, this.x - 445.0F, this.y - 38.5F, 445.0F, 38.5F, 890.0F, 77.0F, xScale, scale, 0.0F, 0, 0, 890, 77, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 234 */       sb.setBlendFunction(770, 1);
/* 235 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.15F));
/* 236 */       sb.draw(ImageMaster.EVENT_BUTTON_ENABLED, this.x - 445.0F, this.y - 38.5F, 445.0F, 38.5F, 890.0F, 77.0F, xScale, scale, 0.0F, 0, 0, 890, 77, false, false);
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
/*     */ 
/*     */ 
/*     */       
/* 253 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 256 */     if (FontHelper.getSmartWidth(FontHelper.largeDialogOptionFont, this.msg, Settings.WIDTH, 0.0F) > 800.0F * Settings.xScale) {
/*     */       
/* 258 */       FontHelper.renderSmartText(sb, FontHelper.smallDialogOptionFont, this.msg, this.x + TEXT_ADJUST_X, this.y + TEXT_ADJUST_Y, Settings.WIDTH, 0.0F, this.textColor);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 268 */       FontHelper.renderSmartText(sb, FontHelper.largeDialogOptionFont, this.msg, this.x + TEXT_ADJUST_X, this.y + TEXT_ADJUST_Y, Settings.WIDTH, 0.0F, this.textColor);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     this.hb.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderCardPreview(SpriteBatch sb) {
/* 284 */     if (this.cardToPreview != null && this.hb.hovered) {
/* 285 */       this.cardToPreview.current_x = this.x + this.hb.width / 1.75F;
/* 286 */       if (this.y < this.cardToPreview.hb.height / 2.0F + 5.0F) {
/* 287 */         this.y = this.cardToPreview.hb.height / 2.0F + 5.0F;
/*     */       }
/* 289 */       this.cardToPreview.current_y = this.y;
/* 290 */       this.cardToPreview.render(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderRelicPreview(SpriteBatch sb) {
/* 295 */     if (!Settings.isControllerMode && this.relicToPreview != null && this.hb.hovered)
/* 296 */       TipHelper.queuePowerTips(470.0F * Settings.scale, InputHelper.mY + 
/*     */           
/* 298 */           TipHelper.calculateToAvoidOffscreen(this.relicToPreview.tips, InputHelper.mY), this.relicToPreview.tips); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\LargeDialogOptionButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */