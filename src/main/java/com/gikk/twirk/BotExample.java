/*    */ package com.gikk.twirk;
/*    */ 
/*    */ import com.gikk.twirk.commands.PatternCommandExample;
/*    */ import com.gikk.twirk.commands.PrefixCommandExample;
/*    */ import com.gikk.twirk.events.TwirkListener;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.Scanner;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BotExample
/*    */ {
/*    */   public static void main(String[] args) throws IOException, InterruptedException {
/* 22 */     System.out.println("Welcome to this Bot example. In this example you will be able \nto send and receive messages from a Twitch chat channel. You will \nmake all input directly here in the command prompt. \n\nEnter channel to join (leave out the #):");
/*    */ 
/*    */ 
/*    */     
/* 26 */     Scanner scanner = new Scanner(new InputStreamReader(System.in, "UTF-8"));
/* 27 */     String channel = "#" + scanner.nextLine();
/*    */ 
/*    */ 
/*    */     
/* 31 */     Twirk twirk = (new TwirkBuilder(channel, "USER_NAME", "oauth:XXXXXXX")).setVerboseMode(true).build();
/*    */     
/* 33 */     twirk.addIrcListener(getOnDisconnectListener(twirk));
/* 34 */     twirk.addIrcListener((TwirkListener)new PatternCommandExample(twirk));
/* 35 */     twirk.addIrcListener((TwirkListener)new PrefixCommandExample(twirk));
/*    */     
/* 37 */     twirk.connect();
/*    */     
/*    */     String line;
/*    */     
/* 41 */     while (!(line = scanner.nextLine()).matches(".quit")) {
/* 42 */       twirk.channelMessage(line);
/*    */     }
/* 44 */     scanner.close();
/* 45 */     twirk.close();
/*    */   }
/*    */ 
/*    */   
/*    */   private static TwirkListener getOnDisconnectListener(final Twirk twirk) {
/* 50 */     return new TwirkListener()
/*    */       {
/*    */         public void onDisconnect()
/*    */         {
/*    */           try {
/* 55 */             if (!twirk.connect())
/*    */             {
/* 57 */               twirk.close();
/*    */             }
/* 59 */           } catch (IOException e) {
/*    */             
/* 61 */             twirk.close();
/*    */           }
/* 63 */           catch (InterruptedException interruptedException) {}
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\BotExample.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */