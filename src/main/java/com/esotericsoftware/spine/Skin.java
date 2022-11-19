/*     */ package com.esotericsoftware.spine;
/*     */
/*     */

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.spine.attachments.Attachment;

import java.util.Iterator;
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
/*     */
/*     */
/*     */ public class Skin
/*     */ {
/*  44 */   private static final Key lookup = new Key();
/*     */
/*     */   final String name;
/*  47 */   final ObjectMap<Key, Attachment> attachments = new ObjectMap();
/*  48 */   final Pool<Key> keyPool = new Pool(64) {
/*     */       protected Object newObject() {
/*  50 */         return new Key();
/*     */       }
/*     */     };
/*     */
/*     */   public Skin(String name) {
/*  55 */     if (name == null) throw new IllegalArgumentException("name cannot be null.");
/*  56 */     this.name = name;
/*     */   }
/*     */
/*     */   public void addAttachment(int slotIndex, String name, Attachment attachment) {
/*  60 */     if (attachment == null) throw new IllegalArgumentException("attachment cannot be null.");
/*  61 */     if (slotIndex < 0) throw new IllegalArgumentException("slotIndex must be >= 0.");
/*  62 */     Key key = (Key)this.keyPool.obtain();
/*  63 */     key.set(slotIndex, name);
/*  64 */     this.attachments.put(key, attachment);
/*     */   }
/*     */
/*     */
/*     */   public Attachment getAttachment(int slotIndex, String name) {
/*  69 */     if (slotIndex < 0) throw new IllegalArgumentException("slotIndex must be >= 0.");
/*  70 */     lookup.set(slotIndex, name);
/*  71 */     return (Attachment)this.attachments.get(lookup);
/*     */   }
/*     */
/*     */   public void findNamesForSlot(int slotIndex, Array<String> names) {
/*  75 */     if (names == null) throw new IllegalArgumentException("names cannot be null.");
/*  76 */     if (slotIndex < 0) throw new IllegalArgumentException("slotIndex must be >= 0.");
/*  77 */     for (ObjectMap.Keys<Key> keys = this.attachments.keys().iterator(); keys.hasNext(); ) { Key key = keys.next();
/*  78 */       if (key.slotIndex == slotIndex) names.add(key.name);  }
/*     */
/*     */   }
///*     */   public void findAttachmentsForSlot(int slotIndex, Array<Attachment> attachments) {
///*  82 */     if (attachments == null) throw new IllegalArgumentException("attachments cannot be null.");
///*  83 */     if (slotIndex < 0) throw new IllegalArgumentException("slotIndex must be >= 0.");
///*  84 */     for (ObjectMap.Entries<ObjectMap.Entry<Key, Attachment>> entries = this.attachments.entries().iterator(); entries.hasNext(); ) { ObjectMap.Entry<Key, Attachment> entry = entries.next();
///*  85 */       if (((Key)entry.key).slotIndex == slotIndex) attachments.add(entry.value);  }
///*     */
///*     */   }
/*     */   public void clear() {
/*  89 */     for (ObjectMap.Keys<Key> keys = this.attachments.keys().iterator(); keys.hasNext(); ) { Key key = keys.next();
/*  90 */       this.keyPool.free(key); }
/*  91 */      this.attachments.clear();
/*     */   }
/*     */
/*     */   public String getName() {
/*  95 */     return this.name;
/*     */   }
/*     */
/*     */   public Iterator<Attachment> attachments() {
/*  99 */     return (Iterator<Attachment>)this.attachments.values().iterator();
/*     */   }
/*     */
/*     */   public String toString() {
/* 103 */     return this.name;
/*     */   }
/*     */
/*     */
void attachAll (Skeleton skeleton, Skin oldSkin) {
    for (ObjectMap.Entry<Key, Attachment> entry : oldSkin.attachments.entries()) {
        int slotIndex = entry.key.slotIndex;
        Slot slot = skeleton.slots.get(slotIndex);
        if (slot.attachment == entry.value) {
            Attachment attachment = getAttachment(slotIndex, entry.key.name);
            if (attachment != null) slot.setAttachment(attachment);
        }
    }
}
/*     */
/*     */   static class Key {
/*     */     int slotIndex;
/*     */     String name;
/*     */     int hashCode;
/*     */
/*     */     public void set(int slotIndex, String name) {
/* 124 */       if (name == null) throw new IllegalArgumentException("name cannot be null.");
/* 125 */       this.slotIndex = slotIndex;
/* 126 */       this.name = name;
/* 127 */       this.hashCode = 31 * (31 + name.hashCode()) + slotIndex;
/*     */     }
/*     */
/*     */     public int hashCode() {
/* 131 */       return this.hashCode;
/*     */     }
/*     */
/*     */     public boolean equals(Object object) {
/* 135 */       if (object == null) return false;
/* 136 */       Key other = (Key)object;
/* 137 */       if (this.slotIndex != other.slotIndex) return false;
/* 138 */       if (!this.name.equals(other.name)) return false;
/* 139 */       return true;
/*     */     }
/*     */
/*     */     public String toString() {
/* 143 */       return this.slotIndex + ":" + this.name;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\Skin.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */