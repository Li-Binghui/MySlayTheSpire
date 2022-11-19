package com.esotericsoftware.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.spine.Skin;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.MeshAttachment;
import com.esotericsoftware.spine.attachments.PathAttachment;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import java.util.Iterator;

/* loaded from: desktop-1.0.jar:com/esotericsoftware/spine/Skeleton.class */
public class Skeleton {
    final SkeletonData data;
    final Array<Bone> bones;
    final Array<Slot> slots;
    Array<Slot> drawOrder;
    final Array<IkConstraint> ikConstraints;
    final Array<IkConstraint> ikConstraintsSorted;
    final Array<TransformConstraint> transformConstraints;
    final Array<PathConstraint> pathConstraints;
    final Array<Updatable> updateCache = new Array<>();
    Skin skin;
    final Color color;
    float time;
    boolean flipX;
    boolean flipY;
    float x;
    float y;

    public Skeleton(SkeletonData data) {
        Bone bone;
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        this.data = data;
        this.bones = new Array<>(data.bones.size);
        Iterator<BoneData> it = data.bones.iterator();
        while (it.hasNext()) {
            BoneData boneData = it.next();
            if (boneData.parent == null) {
                bone = new Bone(boneData, this, (Bone) null);
            } else {
                Bone parent = this.bones.get(boneData.parent.index);
                bone = new Bone(boneData, this, parent);
                parent.children.add(bone);
            }
            this.bones.add(bone);
        }
        this.slots = new Array<>(data.slots.size);
        this.drawOrder = new Array<>(data.slots.size);
        Iterator<SlotData> it2 = data.slots.iterator();
        while (it2.hasNext()) {
            SlotData slotData = it2.next();
            Bone bone2 = this.bones.get(slotData.boneData.index);
            Slot slot = new Slot(slotData, bone2);
            this.slots.add(slot);
            this.drawOrder.add(slot);
        }
        this.ikConstraints = new Array<>(data.ikConstraints.size);
        this.ikConstraintsSorted = new Array<>(this.ikConstraints.size);
        Iterator<IkConstraintData> it3 = data.ikConstraints.iterator();
        while (it3.hasNext()) {
            IkConstraintData ikConstraintData = it3.next();
            this.ikConstraints.add(new IkConstraint(ikConstraintData, this));
        }
        this.transformConstraints = new Array<>(data.transformConstraints.size);
        Iterator<TransformConstraintData> it4 = data.transformConstraints.iterator();
        while (it4.hasNext()) {
            TransformConstraintData transformConstraintData = it4.next();
            this.transformConstraints.add(new TransformConstraint(transformConstraintData, this));
        }
        this.pathConstraints = new Array<>(data.pathConstraints.size);
        Iterator<PathConstraintData> it5 = data.pathConstraints.iterator();
        while (it5.hasNext()) {
            PathConstraintData pathConstraintData = it5.next();
            this.pathConstraints.add(new PathConstraint(pathConstraintData, this));
        }
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        updateCache();
    }

    public Skeleton(Skeleton skeleton) {
        Bone copy;
        if (skeleton == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = skeleton.data;
        this.bones = new Array<>(skeleton.bones.size);
        Iterator<Bone> it = skeleton.bones.iterator();
        while (it.hasNext()) {
            Bone bone = it.next();
            if (bone.parent == null) {
                copy = new Bone(bone, this, (Bone) null);
            } else {
                Bone parent = this.bones.get(bone.parent.data.index);
                copy = new Bone(bone, this, parent);
                parent.children.add(copy);
            }
            this.bones.add(copy);
        }
        this.slots = new Array<>(skeleton.slots.size);
        Iterator<Slot> it2 = skeleton.slots.iterator();
        while (it2.hasNext()) {
            Slot slot = it2.next();
            this.slots.add(new Slot(slot, this.bones.get(slot.bone.data.index)));
        }
        this.drawOrder = new Array<>(this.slots.size);
        Iterator<Slot> it3 = skeleton.drawOrder.iterator();
        while (it3.hasNext()) {
            this.drawOrder.add(this.slots.get(it3.next().data.index));
        }
        this.ikConstraints = new Array<>(skeleton.ikConstraints.size);
        this.ikConstraintsSorted = new Array<>(this.ikConstraints.size);
        Iterator<IkConstraint> it4 = skeleton.ikConstraints.iterator();
        while (it4.hasNext()) {
            IkConstraint ikConstraint = it4.next();
            this.ikConstraints.add(new IkConstraint(ikConstraint, this));
        }
        this.transformConstraints = new Array<>(skeleton.transformConstraints.size);
        Iterator<TransformConstraint> it5 = skeleton.transformConstraints.iterator();
        while (it5.hasNext()) {
            TransformConstraint transformConstraint = it5.next();
            this.transformConstraints.add(new TransformConstraint(transformConstraint, this));
        }
        this.pathConstraints = new Array<>(skeleton.pathConstraints.size);
        Iterator<PathConstraint> it6 = skeleton.pathConstraints.iterator();
        while (it6.hasNext()) {
            PathConstraint pathConstraint = it6.next();
            this.pathConstraints.add(new PathConstraint(pathConstraint, this));
        }
        this.skin = skeleton.skin;
        this.color = new Color(skeleton.color);
        this.time = skeleton.time;
        this.flipX = skeleton.flipX;
        this.flipY = skeleton.flipY;
        updateCache();
    }

    public void updateCache() {
        Array<Updatable> updateCache = this.updateCache;
        updateCache.clear();
        Array<Bone> bones = this.bones;
        int n = bones.size;
        for (int i = 0; i < n; i++) {
            bones.get(i).sorted = false;
        }
        Array<IkConstraint> ikConstraints = this.ikConstraintsSorted;
        ikConstraints.clear();
        ikConstraints.addAll(this.ikConstraints);
        int ikCount = ikConstraints.size;
        for (int i2 = 0; i2 < ikCount; i2++) {
            IkConstraint ik = ikConstraints.get(i2);
            Bone bone = ik.bones.first().parent;
            int level = 0;
            while (bone != null) {
                bone = bone.parent;
                level++;
            }
            ik.level = level;
        }
        for (int i3 = 1; i3 < ikCount; i3++) {
            IkConstraint ik2 = ikConstraints.get(i3);
            int level2 = ik2.level;
            int ii = i3 - 1;
            while (ii >= 0) {
                IkConstraint other = ikConstraints.get(ii);
                if (other.level < level2) {
                    break;
                }
                ikConstraints.set(ii + 1, other);
                ii--;
            }
            ikConstraints.set(ii + 1, ik2);
        }
        int n2 = ikConstraints.size;
        for (int i4 = 0; i4 < n2; i4++) {
            IkConstraint constraint = ikConstraints.get(i4);
            Bone target = constraint.target;
            sortBone(target);
            Array<Bone> constrained = constraint.bones;
            Bone parent = constrained.first();
            sortBone(parent);
            updateCache.add(constraint);
            sortReset(parent.children);
            constrained.peek().sorted = true;
        }
        Array<PathConstraint> pathConstraints = this.pathConstraints;
        int n3 = pathConstraints.size;
        for (int i5 = 0; i5 < n3; i5++) {
            PathConstraint constraint2 = pathConstraints.get(i5);
            Slot slot = constraint2.target;
            int slotIndex = slot.getData().index;
            Bone slotBone = slot.bone;
            if (this.skin != null) {
                sortPathConstraintAttachment(this.skin, slotIndex, slotBone);
            }
            if (!(this.data.defaultSkin == null || this.data.defaultSkin == this.skin)) {
                sortPathConstraintAttachment(this.data.defaultSkin, slotIndex, slotBone);
            }
            int nn = this.data.skins.size;
            for (int ii2 = 0; ii2 < nn; ii2++) {
                sortPathConstraintAttachment(this.data.skins.get(ii2), slotIndex, slotBone);
            }
            Attachment attachment = slot.attachment;
            if (attachment instanceof PathAttachment) {
                sortPathConstraintAttachment(attachment, slotBone);
            }
            Array<Bone> constrained2 = constraint2.bones;
            int boneCount = constrained2.size;
            for (int ii3 = 0; ii3 < boneCount; ii3++) {
                sortBone(constrained2.get(ii3));
            }
            updateCache.add(constraint2);
            for (int ii4 = 0; ii4 < boneCount; ii4++) {
                sortReset(constrained2.get(ii4).children);
            }
            for (int ii5 = 0; ii5 < boneCount; ii5++) {
                constrained2.get(ii5).sorted = true;
            }
        }
        Array<TransformConstraint> transformConstraints = this.transformConstraints;
        int n4 = transformConstraints.size;
        for (int i6 = 0; i6 < n4; i6++) {
            TransformConstraint constraint3 = transformConstraints.get(i6);
            sortBone(constraint3.target);
            Array<Bone> constrained3 = constraint3.bones;
            int boneCount2 = constrained3.size;
            for (int ii6 = 0; ii6 < boneCount2; ii6++) {
                sortBone(constrained3.get(ii6));
            }
            updateCache.add(constraint3);
            for (int ii7 = 0; ii7 < boneCount2; ii7++) {
                sortReset(constrained3.get(ii7).children);
            }
            for (int ii8 = 0; ii8 < boneCount2; ii8++) {
                constrained3.get(ii8).sorted = true;
            }
        }
        int n5 = bones.size;
        for (int i7 = 0; i7 < n5; i7++) {
            sortBone(bones.get(i7));
        }
    }

    private void sortPathConstraintAttachment(Skin skin, int slotIndex, Bone slotBone) {
        ObjectMap.Entries<Skin.Key, Attachment> it = skin.attachments.entries().iterator();
        while (it.hasNext()) {
            ObjectMap.Entry entry = it.next();
            if (((Skin.Key) entry.key).slotIndex == slotIndex) {
                sortPathConstraintAttachment((Attachment) entry.value, slotBone);
            }
        }
    }

    private void sortPathConstraintAttachment(Attachment attachment, Bone slotBone) {
        if (attachment instanceof PathAttachment) {
            int[] pathBones = ((PathAttachment) attachment).getBones();
            if (pathBones == null) {
                sortBone(slotBone);
                return;
            }
            Array<Bone> bones = this.bones;
            for (int boneIndex : pathBones) {
                sortBone(bones.get(boneIndex));
            }
        }
    }

    private void sortBone(Bone bone) {
        if (!bone.sorted) {
            Bone parent = bone.parent;
            if (parent != null) {
                sortBone(parent);
            }
            bone.sorted = true;
            this.updateCache.add(bone);
        }
    }

    private void sortReset(Array<Bone> bones) {
        int n = bones.size;
        for (int i = 0; i < n; i++) {
            Bone bone = bones.get(i);
            if (bone.sorted) {
                sortReset(bone.children);
            }
            bone.sorted = false;
        }
    }

    public void updateWorldTransform() {
        Array<Updatable> updateCache = this.updateCache;
        int n = updateCache.size;
        for (int i = 0; i < n; i++) {
            updateCache.get(i).update();
        }
    }

    public void setToSetupPose() {
        setBonesToSetupPose();
        setSlotsToSetupPose();
    }

    public void setBonesToSetupPose() {
        Array<Bone> bones = this.bones;
        int n = bones.size;
        for (int i = 0; i < n; i++) {
            bones.get(i).setToSetupPose();
        }
        Array<IkConstraint> ikConstraints = this.ikConstraints;
        int n2 = ikConstraints.size;
        for (int i2 = 0; i2 < n2; i2++) {
            IkConstraint constraint = ikConstraints.get(i2);
            constraint.bendDirection = constraint.data.bendDirection;
            constraint.mix = constraint.data.mix;
        }
        Array<TransformConstraint> transformConstraints = this.transformConstraints;
        int n3 = transformConstraints.size;
        for (int i3 = 0; i3 < n3; i3++) {
            TransformConstraint constraint2 = transformConstraints.get(i3);
            TransformConstraintData data = constraint2.data;
            constraint2.rotateMix = data.rotateMix;
            constraint2.translateMix = data.translateMix;
            constraint2.scaleMix = data.scaleMix;
            constraint2.shearMix = data.shearMix;
        }
        Array<PathConstraint> pathConstraints = this.pathConstraints;
        int n4 = pathConstraints.size;
        for (int i4 = 0; i4 < n4; i4++) {
            PathConstraint constraint3 = pathConstraints.get(i4);
            PathConstraintData data2 = constraint3.data;
            constraint3.position = data2.position;
            constraint3.spacing = data2.spacing;
            constraint3.rotateMix = data2.rotateMix;
            constraint3.translateMix = data2.translateMix;
        }
    }

    public void setSlotsToSetupPose() {
        Array<Slot> slots = this.slots;
        System.arraycopy(slots.items, 0, this.drawOrder.items, 0, slots.size);
        int n = slots.size;
        for (int i = 0; i < n; i++) {
            slots.get(i).setToSetupPose();
        }
    }

    public SkeletonData getData() {
        return this.data;
    }

    public Array<Bone> getBones() {
        return this.bones;
    }

    public Array<Updatable> getUpdateCache() {
        return this.updateCache;
    }

    public Bone getRootBone() {
        if (this.bones.size == 0) {
            return null;
        }
        return this.bones.first();
    }

    public Bone findBone(String boneName) {
        if (boneName == null) {
            throw new IllegalArgumentException("boneName cannot be null.");
        }
        Array<Bone> bones = this.bones;
        int n = bones.size;
        for (int i = 0; i < n; i++) {
            Bone bone = bones.get(i);
            if (bone.data.name.equals(boneName)) {
                return bone;
            }
        }
        return null;
    }

    public int findBoneIndex(String boneName) {
        if (boneName == null) {
            throw new IllegalArgumentException("boneName cannot be null.");
        }
        Array<Bone> bones = this.bones;
        int n = bones.size;
        for (int i = 0; i < n; i++) {
            if (bones.get(i).data.name.equals(boneName)) {
                return i;
            }
        }
        return -1;
    }

    public Array<Slot> getSlots() {
        return this.slots;
    }

    public Slot findSlot(String slotName) {
        if (slotName == null) {
            throw new IllegalArgumentException("slotName cannot be null.");
        }
        Array<Slot> slots = this.slots;
        int n = slots.size;
        for (int i = 0; i < n; i++) {
            Slot slot = slots.get(i);
            if (slot.data.name.equals(slotName)) {
                return slot;
            }
        }
        return null;
    }

    public int findSlotIndex(String slotName) {
        if (slotName == null) {
            throw new IllegalArgumentException("slotName cannot be null.");
        }
        Array<Slot> slots = this.slots;
        int n = slots.size;
        for (int i = 0; i < n; i++) {
            if (slots.get(i).data.name.equals(slotName)) {
                return i;
            }
        }
        return -1;
    }

    public Array<Slot> getDrawOrder() {
        return this.drawOrder;
    }

    public void setDrawOrder(Array<Slot> drawOrder) {
        if (drawOrder == null) {
            throw new IllegalArgumentException("drawOrder cannot be null.");
        }
        this.drawOrder = drawOrder;
    }

    public Skin getSkin() {
        return this.skin;
    }

    public void setSkin(String skinName) {
        Skin skin = this.data.findSkin(skinName);
        if (skin == null) {
            throw new IllegalArgumentException("Skin not found: " + skinName);
        }
        setSkin(skin);
    }

    public void setSkin(Skin newSkin) {
        Attachment attachment;
        if (newSkin != null) {
            if (this.skin != null) {
                newSkin.attachAll(this, this.skin);
            } else {
                Array<Slot> slots = this.slots;
                int n = slots.size;
                for (int i = 0; i < n; i++) {
                    Slot slot = slots.get(i);
                    String name = slot.data.attachmentName;
                    if (!(name == null || (attachment = newSkin.getAttachment(i, name)) == null)) {
                        slot.setAttachment(attachment);
                    }
                }
            }
        }
        this.skin = newSkin;
    }

    public Attachment getAttachment(String slotName, String attachmentName) {
        return getAttachment(this.data.findSlotIndex(slotName), attachmentName);
    }

    public Attachment getAttachment(int slotIndex, String attachmentName) {
        Attachment attachment;
        if (attachmentName == null) {
            throw new IllegalArgumentException("attachmentName cannot be null.");
        } else if (this.skin != null && (attachment = this.skin.getAttachment(slotIndex, attachmentName)) != null) {
            return attachment;
        } else {
            if (this.data.defaultSkin != null) {
                return this.data.defaultSkin.getAttachment(slotIndex, attachmentName);
            }
            return null;
        }
    }

    public void setAttachment(String slotName, String attachmentName) {
        if (slotName == null) {
            throw new IllegalArgumentException("slotName cannot be null.");
        }
        Array<Slot> slots = this.slots;
        int n = slots.size;
        for (int i = 0; i < n; i++) {
            Slot slot = slots.get(i);
            if (slot.data.name.equals(slotName)) {
                Attachment attachment = null;
                if (attachmentName != null) {
                    attachment = getAttachment(i, attachmentName);
                    if (attachment == null) {
                        throw new IllegalArgumentException("Attachment not found: " + attachmentName + ", for slot: " + slotName);
                    }
                }
                slot.setAttachment(attachment);
                return;
            }
        }
        throw new IllegalArgumentException("Slot not found: " + slotName);
    }

    public Array<IkConstraint> getIkConstraints() {
        return this.ikConstraints;
    }

    public IkConstraint findIkConstraint(String constraintName) {
        if (constraintName == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Array<IkConstraint> ikConstraints = this.ikConstraints;
        int n = ikConstraints.size;
        for (int i = 0; i < n; i++) {
            IkConstraint ikConstraint = ikConstraints.get(i);
            if (ikConstraint.data.name.equals(constraintName)) {
                return ikConstraint;
            }
        }
        return null;
    }

    public Array<TransformConstraint> getTransformConstraints() {
        return this.transformConstraints;
    }

    public TransformConstraint findTransformConstraint(String constraintName) {
        if (constraintName == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Array<TransformConstraint> transformConstraints = this.transformConstraints;
        int n = transformConstraints.size;
        for (int i = 0; i < n; i++) {
            TransformConstraint constraint = transformConstraints.get(i);
            if (constraint.data.name.equals(constraintName)) {
                return constraint;
            }
        }
        return null;
    }

    public Array<PathConstraint> getPathConstraints() {
        return this.pathConstraints;
    }

    public PathConstraint findPathConstraint(String constraintName) {
        if (constraintName == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Array<PathConstraint> pathConstraints = this.pathConstraints;
        int n = pathConstraints.size;
        for (int i = 0; i < n; i++) {
            PathConstraint constraint = pathConstraints.get(i);
            if (constraint.data.name.equals(constraintName)) {
                return constraint;
            }
        }
        return null;
    }

    public void getBounds(Vector2 offset, Vector2 size) {
        if (offset == null) {
            throw new IllegalArgumentException("offset cannot be null.");
        } else if (size == null) {
            throw new IllegalArgumentException("size cannot be null.");
        } else {
            Array<Slot> drawOrder = this.drawOrder;
            float minX = 2.14748365E9f;
            float minY = 2.14748365E9f;
            float maxX = -2.14748365E9f;
            float maxY = -2.14748365E9f;
            int n = drawOrder.size;
            for (int i = 0; i < n; i++) {
                Slot slot = drawOrder.get(i);
                float[] vertices = null;
                Attachment attachment = slot.attachment;
                if (attachment instanceof RegionAttachment) {
                    vertices = ((RegionAttachment) attachment).updateWorldVertices(slot, false);
                } else if (attachment instanceof MeshAttachment) {
                    vertices = ((MeshAttachment) attachment).updateWorldVertices(slot, true);
                }
                if (vertices != null) {
                    int nn = vertices.length;
                    for (int ii = 0; ii < nn; ii += 5) {
                        float x = vertices[ii];
                        float y = vertices[ii + 1];
                        minX = Math.min(minX, x);
                        minY = Math.min(minY, y);
                        maxX = Math.max(maxX, x);
                        maxY = Math.max(maxY, y);
                    }
                }
            }
            offset.set(minX, minY);
            size.set(maxX - minX, maxY - minY);
        }
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("color cannot be null.");
        }
        this.color.set(color);
    }

    public boolean getFlipX() {
        return this.flipX;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public boolean getFlipY() {
        return this.flipY;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    public void setFlip(boolean flipX, boolean flipY) {
        this.flipX = flipX;
        this.flipY = flipY;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getTime() {
        return this.time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void update(float delta) {
        this.time += delta;
    }

    public String toString() {
        return this.data.name != null ? this.data.name : super.toString();
    }
}