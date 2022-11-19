/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AsyncSaver
/*    */ {
/* 13 */   private static final Logger logger = LogManager.getLogger(AsyncSaver.class.getName());
/*    */   
/*    */   private static Thread saveThread;
/*    */   
/* 17 */   private static final BlockingQueue<File> saveQueue = new LinkedBlockingQueue<>();
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
/*    */   
/*    */   public static void save(String filepath, String data) {
/* 30 */     boolean enableAsyncSave = true;
/* 31 */     if (enableAsyncSave) {
/* 32 */       logger.debug("Enqueue: qsize=" + saveQueue.size() + " file=" + filepath);
/* 33 */       saveQueue.add(new File(filepath, data));
/* 34 */       ensureSaveThread();
/*    */     } else {
/* 36 */       logger.info("Saving synchronously");
/* 37 */       File saveFile = new File(filepath, data);
/* 38 */       saveFile.save();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void ensureSaveThread() {
/* 46 */     if (saveThread == null) {
/* 47 */       startSaveThread();
/* 48 */     } else if (!saveThread.isAlive()) {
/* 49 */       logger.info("Save thread is dead. Starting save thread!");
/* 50 */       startSaveThread();
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void startSaveThread() {
/* 55 */     saveThread = new Thread(new FileSaver(saveQueue));
/* 56 */     saveThread.setName("FileSaver");
/* 57 */     saveThread.start();
/*    */   }
/*    */   
/*    */   public static void shutdownSaveThread() {
/* 61 */     if (saveThread != null)
/* 62 */       saveThread.interrupt(); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\AsyncSaver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */