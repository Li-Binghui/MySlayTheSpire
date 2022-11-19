/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileSaver
/*    */   implements Runnable
/*    */ {
/* 14 */   private static final Logger logger = LogManager.getLogger(FileSaver.class.getName());
/*    */   private final BlockingQueue<File> queue;
/*    */   
/*    */   public FileSaver(BlockingQueue<File> q) {
/* 18 */     this.queue = q;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 23 */     while (!Thread.currentThread().isInterrupted()) {
/*    */       try {
/* 25 */         consume(this.queue.take());
/* 26 */       } catch (InterruptedException e) {
/* 27 */         logger.info("Save thread interrupted!");
/* 28 */         Thread.currentThread().interrupt();
/*    */       } 
/*    */     } 
/* 31 */     logger.info("Save thread will die now.");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void consume(File file) {
/* 41 */     logger.debug("Dequeue: qsize=" + this.queue.size() + " file=" + file.getFilepath());
/* 42 */     file.save();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\FileSaver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */