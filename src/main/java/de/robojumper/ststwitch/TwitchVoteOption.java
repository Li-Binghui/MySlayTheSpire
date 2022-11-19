/*    */ package de.robojumper.ststwitch;
/*    */ 
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class TwitchVoteOption {
/*    */   public final String displayName;
/*    */   final String commandHint;
/*    */   final Pattern matchPattern;
/*    */   public int voteCount;
/*    */   
/*    */   TwitchVoteOption(String name, String hint, String pattern) {
/* 12 */     this.displayName = name;
/* 13 */     this.commandHint = hint;
/* 14 */     this.matchPattern = Pattern.compile(pattern);
/*    */   }
/*    */   
/*    */   private TwitchVoteOption(TwitchVoteOption other) {
/* 18 */     this.displayName = other.displayName;
/* 19 */     this.commandHint = other.commandHint;
/* 20 */     this.matchPattern = other.matchPattern;
/* 21 */     this.voteCount = other.voteCount;
/*    */   }
/*    */   
/*    */   static TwitchVoteOption[] cloneArrayOfOptions(TwitchVoteOption[] options) {
/* 25 */     TwitchVoteOption[] newArr = new TwitchVoteOption[options.length];
/* 26 */     for (int i = 0; i < newArr.length; i++) {
/* 27 */       newArr[i] = new TwitchVoteOption(options[i]);
/*    */     }
/* 29 */     return newArr;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\de\robojumper\ststwitch\TwitchVoteOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */