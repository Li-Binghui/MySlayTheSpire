/*    */ package com.gikk.twirk.types.hostTarget;
/*    */ 
/*    */ import com.gikk.twirk.enums.HOSTTARGET_MODE;
/*    */ import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*    */ 
/*    */ class DefaultHostTargetBuilder
/*    */   implements HostTargetBuilder
/*    */ {
/*    */   HOSTTARGET_MODE mode;
/*    */   String target;
/*    */   int viwerAmount;
/*    */   String rawLine;
/*    */   
/*    */   public HostTarget build(TwitchMessage message) {
/* 15 */     this.rawLine = message.getRaw();
/* 16 */     this.mode = message.getContent().startsWith("-") ? HOSTTARGET_MODE.STOP : HOSTTARGET_MODE.START;
/*    */     
/* 18 */     String[] segments = message.getContent().split(" ", 2);
/* 19 */     this.target = segments[0].equals("-") ? "" : segments[0];
/*    */     
/*    */     try {
/* 22 */       this.viwerAmount = Integer.parseInt(segments[1]);
/*    */     }
/* 24 */     catch (Exception e) {
/* 25 */       this.viwerAmount = 0;
/*    */     } 
/*    */     
/* 28 */     return new HostTargetImpl(this);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\hostTarget\DefaultHostTargetBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */