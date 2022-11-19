/*    */ package com.gikk.twirk.types.mode;
/*    */ 
/*    */ import com.gikk.twirk.types.AbstractType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Mode
/*    */   extends AbstractType
/*    */ {
/*    */   MODE_EVENT getEvent();
/*    */   
/*    */   String getUser();
/*    */   
/*    */   public enum MODE_EVENT
/*    */   {
/* 21 */     GAINED_MOD, LOST_MOD;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\mode\Mode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */