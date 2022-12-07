package com.megacrit.cardcrawl.rooms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/EventRoom.class */
public class EventRoom extends AbstractRoom {
    public EventRoom() {
        this.phase = AbstractRoom.RoomPhase.EVENT;
        this.mapSymbol = "?";
        this.mapImg = ImageMaster.MAP_NODE_EVENT;
        this.mapImgOutline = ImageMaster.MAP_NODE_EVENT_OUTLINE;
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void onPlayerEntry() {
        AbstractDungeon.overlayMenu.proceedButton.hide();
        Random eventRngDuplicate = new Random(Settings.seed, AbstractDungeon.eventRng.counter);
        this.event = AbstractDungeon.generateEvent(eventRngDuplicate);
        this.event.onEnterRoom();
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void update() {
        super.update();
        if (!AbstractDungeon.isScreenUp) {
            this.event.update();
        }
        if (this.event.waitTimer == 0.0f && !this.event.hasFocus && this.phase != AbstractRoom.RoomPhase.COMBAT) {
            this.phase = AbstractRoom.RoomPhase.COMPLETE;
            this.event.reopen();
        }
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void render(SpriteBatch sb) {
        if (this.event != null) {
            this.event.render(sb);
        }
        super.render(sb);
    }

    @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
    public void renderAboveTopPanel(SpriteBatch sb) {
        super.renderAboveTopPanel(sb);
        if (this.event != null) {
            this.event.renderAboveTopPanel(sb);
        }
    }
}