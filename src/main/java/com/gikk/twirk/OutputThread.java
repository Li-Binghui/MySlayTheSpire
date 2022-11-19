/*     */ package com.gikk.twirk;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.net.SocketException;
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
/*     */ class OutputThread
/*     */   extends Thread
/*     */ {
/*     */   private final Twirk connection;
/*     */   private final BufferedWriter writer;
/*     */   private final OutputQueue queue;
/*     */   private boolean isConnected = true;
/*  26 */   private int messageGapMillis = 1500;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputThread(Twirk connection, OutputQueue queue, BufferedReader reader, BufferedWriter writer) {
/*  32 */     this.connection = connection;
/*  33 */     this.queue = queue;
/*  34 */     this.writer = writer;
/*     */     
/*  36 */     setName("Twirk-OutputThread");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  42 */     while (this.isConnected) {
/*     */       try {
/*  44 */         String line = this.queue.next();
/*  45 */         if (line != null) {
/*  46 */           sendLine(line);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  51 */           this.isConnected = this.connection.isConnected();
/*     */         } 
/*  53 */         Thread.sleep(this.messageGapMillis);
/*  54 */       } catch (Exception exception) {}
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void quickSend(String message) {
/*     */     try {
/*  75 */       sendLine(message);
/*  76 */     } catch (SocketException e) {
/*  77 */       System.err.println("Could not QuickSend message. Socket was closed (OutputThread @ Twirk)");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void end() {
/*  85 */     this.isConnected = false;
/*  86 */     this.queue.releaseWaitingThreads();
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
/*     */   
/*     */   void setMessageDelay(int millis) {
/*  99 */     this.messageGapMillis = millis;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendLine(String message) throws SocketException {
/* 115 */     if (!this.isConnected) {
/* 116 */       System.err.println("Twirk is not connected! Sending messages will not succeed!");
/*     */     }
/*     */     
/* 119 */     if (this.connection.verboseMode) {
/* 120 */       System.out.println("OUT " + message);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     if (message.length() > 510) {
/* 131 */       message = message.substring(0, 511);
/*     */     }
/*     */     
/*     */     try {
/* 135 */       synchronized (this.writer) {
/* 136 */         this.writer.write(message + "\r\n");
/* 137 */         this.writer.flush();
/*     */       } 
/* 139 */     } catch (IOException e) {
/* 140 */       if (e.getMessage().matches("Stream closed")) {
/* 141 */         System.err.println("Cannot send message: " + message + " Stream closed");
/*     */         return;
/*     */       } 
/* 144 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\OutputThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */