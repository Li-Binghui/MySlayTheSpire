/*     */ package com.gikk.twirk;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.net.SocketTimeoutException;
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
/*     */ class InputThread
/*     */   extends Thread
/*     */ {
/*     */   private final Twirk connection;
/*     */   private final BufferedReader reader;
/*     */   private boolean isConnected = true;
/*     */   private boolean havePinged = false;
/*     */   
/*     */   public InputThread(Twirk connection, BufferedReader reader, BufferedWriter writer) {
/*  27 */     this.connection = connection;
/*  28 */     this.reader = reader;
/*     */     
/*  30 */     setName("Twirk-InputThread");
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/*  36 */       while (this.isConnected) {
/*     */         try {
/*  38 */           String line = null;
/*  39 */           while ((line = this.reader.readLine()) != null) {
/*  40 */             this.havePinged = false;
/*     */             
/*     */             try {
/*  43 */               this.connection.incommingMessage(line);
/*  44 */             } catch (Exception e) {
/*  45 */               System.err.println("Error in handling the incomming Irc Message");
/*  46 */               e.printStackTrace();
/*     */             } 
/*     */           } 
/*     */           
/*  50 */           this.isConnected = false;
/*     */         }
/*  52 */         catch (SocketTimeoutException e) {
/*     */ 
/*     */ 
/*     */           
/*  56 */           if (!this.havePinged) {
/*  57 */             this.connection.serverMessage("PING " + System.currentTimeMillis());
/*  58 */             this.havePinged = true;
/*     */             continue;
/*     */           } 
/*  61 */           this.isConnected = false;
/*     */         
/*     */         }
/*  64 */         catch (IOException e) {
/*     */           
/*  66 */           String message = e.getMessage();
/*  67 */           if (!message.contains("Socket Closed"))
/*     */           {
/*  69 */             if (message.contains("Connection reset") || message.contains("Stream closed")) {
/*  70 */               System.err.println(message);
/*     */             } else {
/*     */               
/*  73 */               e.printStackTrace();
/*     */             }  } 
/*  75 */           this.isConnected = false;
/*     */         } 
/*     */       } 
/*  78 */     } catch (Exception e) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  84 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  89 */     if (this.connection.isConnected()) {
/*  90 */       this.connection.disconnect();
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
/*     */   public void end() {
/* 103 */     this.isConnected = false;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\InputThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */