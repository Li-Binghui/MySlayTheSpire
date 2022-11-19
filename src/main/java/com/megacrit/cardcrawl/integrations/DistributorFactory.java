/*    */ package com.megacrit.cardcrawl.integrations;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.integrations.discord.DiscordIntegration;
/*    */ import com.megacrit.cardcrawl.integrations.ea.EaIntegration;
/*    */ import com.megacrit.cardcrawl.integrations.gog.GogIntegration;
/*    */ import com.megacrit.cardcrawl.integrations.microsoft.MicrosoftIntegration;
/*    */ import com.megacrit.cardcrawl.integrations.steam.SteamIntegration;
/*    */ import com.megacrit.cardcrawl.integrations.wegame.WeGameIntegration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DistributorFactory
/*    */ {
/*    */   public enum Distributor
/*    */   {
/* 22 */     STEAM, DISCORD, WEGAME, GOG, EA, MICROSOFT;
/*    */   }
/*    */   
/*    */   public static PublisherIntegration getEnabledDistributor(String distributor) throws DistributorFactoryException {
/* 26 */     switch (distributor) {
/*    */       case "steam":
/* 28 */         return (PublisherIntegration)new SteamIntegration();
/*    */       case "discord":
/* 30 */         return (PublisherIntegration)new DiscordIntegration();
/*    */       case "wegame":
/* 32 */         return (PublisherIntegration)new WeGameIntegration();
/*    */       case "gog":
/* 34 */         return (PublisherIntegration)new GogIntegration();
/*    */       case "ea":
/* 36 */         return (PublisherIntegration)new EaIntegration();
/*    */       case "microsoft":
/* 38 */         return (PublisherIntegration)new MicrosoftIntegration();
/*    */     } 
/* 40 */     throw new DistributorFactoryException("Unrecognized distributor=" + distributor);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isLeaderboardEnabled() {
/* 45 */     return (CardCrawlGame.publisherIntegration.getType() == Distributor.STEAM);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\DistributorFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */