/*    */ package com.esotericsoftware.spine.attachments;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.esotericsoftware.spine.Skin;
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
/*    */ 
/*    */ public class AtlasAttachmentLoader
/*    */   implements AttachmentLoader
/*    */ {
/*    */   private TextureAtlas atlas;
/*    */   
/*    */   public AtlasAttachmentLoader(TextureAtlas atlas) {
/* 43 */     if (atlas == null) throw new IllegalArgumentException("atlas cannot be null."); 
/* 44 */     this.atlas = atlas;
/*    */   }
/*    */   
/*    */   public RegionAttachment newRegionAttachment(Skin skin, String name, String path) {
/* 48 */     TextureAtlas.AtlasRegion region = this.atlas.findRegion(path);
/* 49 */     if (region == null) throw new RuntimeException("Region not found in atlas: " + path + " (region attachment: " + name + ")"); 
/* 50 */     RegionAttachment attachment = new RegionAttachment(name);
/* 51 */     attachment.setRegion((TextureRegion)region);
/* 52 */     return attachment;
/*    */   }
/*    */   
/*    */   public MeshAttachment newMeshAttachment(Skin skin, String name, String path) {
/* 56 */     TextureAtlas.AtlasRegion region = this.atlas.findRegion(path);
/* 57 */     if (region == null) throw new RuntimeException("Region not found in atlas: " + path + " (mesh attachment: " + name + ")"); 
/* 58 */     MeshAttachment attachment = new MeshAttachment(name);
/* 59 */     attachment.setRegion((TextureRegion)region);
/* 60 */     return attachment;
/*    */   }
/*    */   
/*    */   public BoundingBoxAttachment newBoundingBoxAttachment(Skin skin, String name) {
/* 64 */     return new BoundingBoxAttachment(name);
/*    */   }
/*    */   
/*    */   public PathAttachment newPathAttachment(Skin skin, String name) {
/* 68 */     return new PathAttachment(name);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\attachments\AtlasAttachmentLoader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */