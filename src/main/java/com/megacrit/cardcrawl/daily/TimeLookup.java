/*    */ package com.megacrit.cardcrawl.daily;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;
/*    */ 
/*    */ 
/*    */ public class TimeLookup
/*    */ {
/* 13 */   private static final Logger logger = LogManager.getLogger(TimeLookup.class.getName());
/*    */   
/*    */   public static volatile boolean isDone = false;
/*    */   
/*    */   private static final String timeServer = "https://hyi3lwrhf5.execute-api.us-east-1.amazonaws.com/prod/time";
/* 18 */   private static volatile AtomicInteger retryCount = new AtomicInteger(1);
/*    */   
/*    */   private static final int MAX_RETRY = 2;
/*    */   
/*    */   private static final int WAIT_TIME_CAP = 2;
/*    */ 
/*    */   
/*    */   private static void makeHTTPReq(String url) {
/* 26 */     HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     Net.HttpRequest httpRequest = requestBuilder.newRequest().method("GET").url(url).header("Content-Type", "application/json").header("Accept", "application/json").header("User-Agent", "curl/7.43.0").timeout(5000).build();
/* 33 */     Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener()
/*    */         {
/*    */           public void handleHttpResponse(Net.HttpResponse httpResponse) {
/* 36 */             String status = String.valueOf(httpResponse.getStatus().getStatusCode());
/* 37 */             String result = httpResponse.getResultAsString();
/* 38 */             if (!status.startsWith("2")) {
/* 39 */               TimeLookup.logger.info("Query to sts-time-server failed: status_code=" + status + " result=" + result);
/*    */             }
/* 41 */             TimeLookup.logger.info("Time server response: " + result);
/* 42 */             long serverTime = Long.parseLong(result);
/* 43 */             TimeLookup.isDone = true;
/* 44 */             TimeHelper.setTime(serverTime, false);
/*    */           }
/*    */ 
/*    */           
/*    */           public void failed(Throwable t) {
/* 49 */             TimeLookup.logger.info("http request failed: " + t.toString());
/* 50 */             TimeLookup.logger.info("retry count: " + TimeLookup.retryCount);
/*    */             
/* 52 */             if (TimeLookup.retryCount.get() > 2) {
/* 53 */               TimeLookup.logger.info("Failed to lookup time. Switching to OFFLINE MODE!");
/* 54 */               long localTime = System.currentTimeMillis() / 1000L;
/* 55 */               TimeHelper.setTime(localTime, true);
/*    */               return;
/*    */             } 
/* 58 */             long waitTime = (long)Math.pow(2.0D, TimeLookup.retryCount.get());
/* 59 */             TimeLookup.logger.info("wait time: " + waitTime);
/* 60 */             if (waitTime > 2L) {
/* 61 */               waitTime = 2L;
/*    */             }
/* 63 */             TimeLookup.logger.info("Retry " + TimeLookup.retryCount.get() + ": waiting " + waitTime + " seconds for time lookup");
/* 64 */             TimeLookup.retryCount.getAndIncrement();
/*    */             try {
/* 66 */               Thread.sleep(waitTime * 1000L);
/* 67 */             } catch (InterruptedException ex) {
/* 68 */               Thread.currentThread().interrupt();
/* 69 */               TimeLookup.logger.info("Thread interrupted!");
/*    */             } 
/* 71 */             TimeLookup.makeHTTPReq("https://hyi3lwrhf5.execute-api.us-east-1.amazonaws.com/prod/time");
/*    */           }
/*    */ 
/*    */           
/*    */           public void cancelled() {
/* 76 */             TimeLookup.logger.info("http request cancelled.");
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void fetchDailyTimeAsync() {
/* 87 */     makeHTTPReq("https://hyi3lwrhf5.execute-api.us-east-1.amazonaws.com/prod/time");
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\TimeLookup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */