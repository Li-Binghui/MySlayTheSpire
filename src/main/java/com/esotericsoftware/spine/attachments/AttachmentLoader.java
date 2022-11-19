package com.esotericsoftware.spine.attachments;

import com.esotericsoftware.spine.Skin;

public interface AttachmentLoader {
  RegionAttachment newRegionAttachment(Skin paramSkin, String paramString1, String paramString2);
  
  MeshAttachment newMeshAttachment(Skin paramSkin, String paramString1, String paramString2);
  
  BoundingBoxAttachment newBoundingBoxAttachment(Skin paramSkin, String paramString);
  
  PathAttachment newPathAttachment(Skin paramSkin, String paramString);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\attachments\AttachmentLoader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */