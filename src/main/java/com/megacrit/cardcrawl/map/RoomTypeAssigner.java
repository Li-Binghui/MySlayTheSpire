/*     */ package com.megacrit.cardcrawl.map;
/*     */ 
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.rooms.EventRoom;
/*     */ import com.megacrit.cardcrawl.rooms.MonsterRoom;
/*     */ import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
/*     */ import com.megacrit.cardcrawl.rooms.RestRoom;
/*     */ import com.megacrit.cardcrawl.rooms.ShopRoom;
/*     */ import com.megacrit.cardcrawl.rooms.TreasureRoom;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RoomTypeAssigner
/*     */ {
/*  24 */   private static final Logger logger = LogManager.getLogger(RoomTypeAssigner.class.getName());
/*     */ 
/*     */   
/*     */   public static void assignRowAsRoomType(ArrayList<MapRoomNode> row, Class<? extends AbstractRoom> c) {
/*  28 */     for (MapRoomNode n : row) {
/*  29 */       if (n.getRoom() == null) {
/*     */         try {
/*  31 */           n.setRoom(c.newInstance());
/*  32 */         } catch (InstantiationException|IllegalAccessException e) {
/*  33 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getConnectedNonAssignedNodeCount(List<ArrayList<MapRoomNode>> map) {
/*  46 */     int count = 0;
/*  47 */     for (ArrayList<MapRoomNode> row : map) {
/*  48 */       for (MapRoomNode node : row) {
/*  49 */         if (node.hasEdges() && node.getRoom() == null)
/*     */         {
/*  51 */           count++;
/*     */         }
/*     */       } 
/*     */     } 
/*  55 */     return count;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArrayList<MapRoomNode> getSiblings(List<ArrayList<MapRoomNode>> map, ArrayList<MapRoomNode> parents, MapRoomNode n) {
/*  71 */     ArrayList<MapRoomNode> siblings = new ArrayList<>();
/*  72 */     for (MapRoomNode parent : parents) {
/*  73 */       for (MapEdge parentEdge : parent.getEdges()) {
/*  74 */         MapRoomNode siblingNode = ((ArrayList<MapRoomNode>)map.get(parentEdge.dstY)).get(parentEdge.dstX);
/*  75 */         if (!siblingNode.equals(n)) {
/*  76 */           siblings.add(siblingNode);
/*     */         }
/*     */       } 
/*     */     } 
/*  80 */     return siblings;
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
/*     */ 
/*     */   
/*     */   private static boolean ruleSiblingMatches(ArrayList<MapRoomNode> siblings, AbstractRoom roomToBeSet) {
/*  94 */     List<Class<? extends AbstractRoom>> applicableRooms = Arrays.asList((Class<? extends AbstractRoom>[])new Class[] { RestRoom.class, MonsterRoom.class, EventRoom.class, MonsterRoomElite.class, ShopRoom.class });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     for (MapRoomNode siblingNode : siblings) {
/* 101 */       if (siblingNode.getRoom() != null && 
/* 102 */         applicableRooms.contains(roomToBeSet.getClass()) && roomToBeSet.getClass().equals(siblingNode
/* 103 */           .getRoom().getClass())) {
/* 104 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 108 */     return false;
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
/*     */   private static boolean ruleParentMatches(ArrayList<MapRoomNode> parents, AbstractRoom roomToBeSet) {
/* 120 */     List<Class<? extends AbstractRoom>> applicableRooms = Arrays.asList((Class<? extends AbstractRoom>[])new Class[] { RestRoom.class, TreasureRoom.class, ShopRoom.class, MonsterRoomElite.class });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     for (MapRoomNode parentNode : parents) {
/* 126 */       AbstractRoom parentRoom = parentNode.getRoom();
/* 127 */       if (parentRoom != null && 
/* 128 */         applicableRooms.contains(roomToBeSet.getClass()) && roomToBeSet.getClass().equals(parentRoom
/* 129 */           .getClass())) {
/* 130 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 134 */     return false;
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
/*     */ 
/*     */   
/*     */   private static boolean ruleAssignableToRow(MapRoomNode n, AbstractRoom roomToBeSet) {
/* 148 */     List<Class<? extends AbstractRoom>> applicableRooms = Arrays.asList((Class<? extends AbstractRoom>[])new Class[] { RestRoom.class, MonsterRoomElite.class });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     List<Class<RestRoom>> applicableRooms2 = Collections.singletonList(RestRoom.class);
/*     */ 
/*     */     
/* 156 */     if (n.y <= 4 && applicableRooms.contains(roomToBeSet.getClass())) {
/* 157 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 161 */     if (n.y >= 13 && applicableRooms2.contains(roomToBeSet.getClass())) {
/* 162 */       return false;
/*     */     }
/*     */     
/* 165 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static AbstractRoom getNextRoomTypeAccordingToRules(ArrayList<ArrayList<MapRoomNode>> map, MapRoomNode n, ArrayList<AbstractRoom> roomList) {
/* 183 */     ArrayList<MapRoomNode> parents = n.getParents();
/* 184 */     ArrayList<MapRoomNode> siblings = getSiblings(map, parents, n);
/*     */     
/* 186 */     for (AbstractRoom roomToBeSet : roomList) {
/* 187 */       if (ruleAssignableToRow(n, roomToBeSet)) {
/*     */ 
/*     */         
/* 190 */         if (!ruleParentMatches(parents, roomToBeSet) && !ruleSiblingMatches(siblings, roomToBeSet))
/* 191 */           return roomToBeSet; 
/* 192 */         if (n.y == 0) {
/* 193 */           return roomToBeSet;
/*     */         }
/*     */       } 
/*     */     } 
/* 197 */     return null;
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
/*     */   
/*     */   private static void lastMinuteNodeChecker(ArrayList<ArrayList<MapRoomNode>> map, MapRoomNode n) {
/* 210 */     for (ArrayList<MapRoomNode> row : map) {
/* 211 */       for (MapRoomNode node : row) {
/* 212 */         if (node != null && node.hasEdges() && node.getRoom() == null) {
/* 213 */           logger.info("INFO: Node=" + node.toString() + " was null. Changed to a MonsterRoom.");
/* 214 */           node.setRoom((AbstractRoom)new MonsterRoom());
/*     */         } 
/*     */       } 
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
/*     */   private static void assignRoomsToNodes(ArrayList<ArrayList<MapRoomNode>> map, ArrayList<AbstractRoom> roomList) {
/* 229 */     for (ArrayList<MapRoomNode> row : map) {
/* 230 */       for (MapRoomNode node : row) {
/*     */         
/* 232 */         if (node != null && node.hasEdges() && node.getRoom() == null) {
/* 233 */           AbstractRoom roomToBeSet = getNextRoomTypeAccordingToRules(map, node, roomList);
/* 234 */           if (roomToBeSet != null)
/*     */           {
/* 236 */             node.setRoom(roomList.remove(roomList.indexOf(roomToBeSet)));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<ArrayList<MapRoomNode>> distributeRoomsAcrossMap(Random rng, ArrayList<ArrayList<MapRoomNode>> map, ArrayList<AbstractRoom> roomList) {
/* 248 */     int nodeCount = getConnectedNonAssignedNodeCount(map);
/* 249 */     while (roomList.size() < nodeCount) {
/* 250 */       roomList.add(new MonsterRoom());
/*     */     }
/* 252 */     if (roomList.size() > nodeCount) {
/* 253 */       logger.info("WARNING: the roomList is larger than the number of connected nodes. Not all desired roomTypes will be used.");
/*     */     }
/*     */     
/* 256 */     Collections.shuffle(roomList, rng.random);
/*     */ 
/*     */     
/* 259 */     assignRoomsToNodes(map, roomList);
/*     */     
/* 261 */     logger.info("#### Unassigned Rooms:");
/* 262 */     for (AbstractRoom r : roomList) {
/* 263 */       logger.info(r.getClass());
/*     */     }
/*     */     
/* 266 */     lastMinuteNodeChecker(map, null);
/* 267 */     return map;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\map\RoomTypeAssigner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */