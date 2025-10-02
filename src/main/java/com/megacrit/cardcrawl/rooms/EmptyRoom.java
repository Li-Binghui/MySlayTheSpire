package com.megacrit.cardcrawl.rooms;

import com.megacrit.cardcrawl.rooms.AbstractRoom;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/rooms/EmptyRoom.class */
public class EmptyRoom extends AbstractRoom {
  public EmptyRoom() {
    this.phase = AbstractRoom.RoomPhase.COMPLETE;
  }

  @Override // com.megacrit.cardcrawl.rooms.AbstractRoom
  public void onPlayerEntry() {
  }
}
