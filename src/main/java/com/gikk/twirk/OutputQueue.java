/*    */ package com.gikk.twirk;
/*    */ 
/*    */ import java.util.LinkedList;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class OutputQueue
/*    */ {
/* 21 */   private final LinkedList<String> queue = new LinkedList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(String s) {
/* 31 */     synchronized (this.queue) {
/* 32 */       this.queue.add(s);
/* 33 */       this.queue.notify();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addFirst(String s) {
/* 43 */     synchronized (this.queue) {
/* 44 */       this.queue.addFirst(s);
/* 45 */       this.queue.notify();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String next() {
/* 55 */     synchronized (this.queue) {
/* 56 */       if (!hasNext()) {
/* 57 */         try { this.queue.wait(); }
/* 58 */         catch (InterruptedException interruptedException) {}
/*    */       }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 65 */       if (!hasNext()) {
/* 66 */         return null;
/*    */       }
/*    */       
/* 69 */       String message = this.queue.getFirst();
/* 70 */       this.queue.removeFirst();
/* 71 */       return message;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasNext() {
/* 80 */     synchronized (this.queue) {
/* 81 */       return (this.queue.size() > 0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void releaseWaitingThreads() {
/* 89 */     synchronized (this.queue) {
/* 90 */       this.queue.notifyAll();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\OutputQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */