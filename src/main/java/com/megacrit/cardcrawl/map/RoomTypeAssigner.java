package com.megacrit.cardcrawl.map;

import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/map/RoomTypeAssigner.class */
public class RoomTypeAssigner {
    private static final Logger logger = LogManager.getLogger(RoomTypeAssigner.class.getName());

    public static void assignRowAsRoomType(ArrayList<MapRoomNode> row, Class<? extends AbstractRoom> c) {
        Iterator<MapRoomNode> it = row.iterator();
        while (it.hasNext()) {
            MapRoomNode n = it.next();
            if (n.getRoom() == null) {
                try {
                    n.setRoom(c.newInstance());
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int getConnectedNonAssignedNodeCount(List<ArrayList<MapRoomNode>> map) {
        int count = 0;
        for (ArrayList<MapRoomNode> row : map) {
            Iterator<MapRoomNode> it = row.iterator();
            while (it.hasNext()) {
                MapRoomNode node = it.next();
                if (node.hasEdges() && node.getRoom() == null) {
                    count++;
                }
            }
        }
        return count;
    }

    private static ArrayList<MapRoomNode> getSiblings(List<ArrayList<MapRoomNode>> map, ArrayList<MapRoomNode> parents, MapRoomNode n) {
        ArrayList<MapRoomNode> siblings = new ArrayList<>();
        Iterator<MapRoomNode> it = parents.iterator();
        while (it.hasNext()) {
            MapRoomNode parent = it.next();
            Iterator<MapEdge> it2 = parent.getEdges().iterator();
            while (it2.hasNext()) {
                MapEdge parentEdge = it2.next();
                MapRoomNode siblingNode = map.get(parentEdge.dstY).get(parentEdge.dstX);
                if (!siblingNode.equals(n)) {
                    siblings.add(siblingNode);
                }
            }
        }
        return siblings;
    }

    private static boolean ruleSiblingMatches(ArrayList<MapRoomNode> siblings, AbstractRoom roomToBeSet) {
        List<Class<? extends AbstractRoom>> applicableRooms = Arrays.asList(RestRoom.class, MonsterRoom.class, EventRoom.class, MonsterRoomElite.class, ShopRoom.class);
        Iterator<MapRoomNode> it = siblings.iterator();
        while (it.hasNext()) {
            MapRoomNode siblingNode = it.next();
            if (siblingNode.getRoom() != null && applicableRooms.contains(roomToBeSet.getClass()) && roomToBeSet.getClass().equals(siblingNode.getRoom().getClass())) {
                return true;
            }
        }
        return false;
    }

    private static boolean ruleParentMatches(ArrayList<MapRoomNode> parents, AbstractRoom roomToBeSet) {
        List<Class<? extends AbstractRoom>> applicableRooms = Arrays.asList(RestRoom.class, TreasureRoom.class, ShopRoom.class, MonsterRoomElite.class);
        Iterator<MapRoomNode> it = parents.iterator();
        while (it.hasNext()) {
            MapRoomNode parentNode = it.next();
            AbstractRoom parentRoom = parentNode.getRoom();
            if (parentRoom != null && applicableRooms.contains(roomToBeSet.getClass()) && roomToBeSet.getClass().equals(parentRoom.getClass())) {
                return true;
            }
        }
        return false;
    }

    private static boolean ruleAssignableToRow(MapRoomNode n, AbstractRoom roomToBeSet) {
        List<Class<? extends AbstractRoom>> applicableRooms = Arrays.asList(RestRoom.class, MonsterRoomElite.class);
        List<Class<RestRoom>> applicableRooms2 = Collections.singletonList(RestRoom.class);
        if (n.y <= 4 && applicableRooms.contains(roomToBeSet.getClass())) {
            return false;
        }
        if (n.y < 13 || !applicableRooms2.contains(roomToBeSet.getClass())) {
            return true;
        }
        return false;
    }

    private static AbstractRoom getNextRoomTypeAccordingToRules(ArrayList<ArrayList<MapRoomNode>> map, MapRoomNode n, ArrayList<AbstractRoom> roomList) {
        ArrayList<MapRoomNode> parents = n.getParents();
        ArrayList<MapRoomNode> siblings = getSiblings(map, parents, n);
        Iterator<AbstractRoom> it = roomList.iterator();
        while (it.hasNext()) {
            AbstractRoom roomToBeSet = it.next();
            if (ruleAssignableToRow(n, roomToBeSet)) {
                if (!ruleParentMatches(parents, roomToBeSet) && !ruleSiblingMatches(siblings, roomToBeSet)) {
                    return roomToBeSet;
                }
                if (n.y == 0) {
                    return roomToBeSet;
                }
            }
        }
        return null;
    }

    private static void lastMinuteNodeChecker(ArrayList<ArrayList<MapRoomNode>> map, MapRoomNode n) {
        Iterator<ArrayList<MapRoomNode>> it = map.iterator();
        while (it.hasNext()) {
            ArrayList<MapRoomNode> row = it.next();
            Iterator<MapRoomNode> it2 = row.iterator();
            while (it2.hasNext()) {
                MapRoomNode node = it2.next();
                if (node != null && node.hasEdges() && node.getRoom() == null) {
                    logger.info("INFO: Node=" + node.toString() + " was null. Changed to a MonsterRoom.");
                    node.setRoom(new MonsterRoom());
                }
            }
        }
    }

    private static void assignRoomsToNodes(ArrayList<ArrayList<MapRoomNode>> map, ArrayList<AbstractRoom> roomList) {
        AbstractRoom roomToBeSet;
        Iterator<ArrayList<MapRoomNode>> it = map.iterator();
        while (it.hasNext()) {
            ArrayList<MapRoomNode> row = it.next();
            Iterator<MapRoomNode> it2 = row.iterator();
            while (it2.hasNext()) {
                MapRoomNode node = it2.next();
                if (node != null && node.hasEdges() && node.getRoom() == null && (roomToBeSet = getNextRoomTypeAccordingToRules(map, node, roomList)) != null) {
                    node.setRoom(roomList.remove(roomList.indexOf(roomToBeSet)));
                }
            }
        }
    }

    public static ArrayList<ArrayList<MapRoomNode>> distributeRoomsAcrossMap(Random rng, ArrayList<ArrayList<MapRoomNode>> map, ArrayList<AbstractRoom> roomList) {
        int nodeCount = getConnectedNonAssignedNodeCount(map);
        while (roomList.size() < nodeCount) {
            roomList.add(new MonsterRoom());
        }
        if (roomList.size() > nodeCount) {
            logger.info("WARNING: the roomList is larger than the number of connected nodes. Not all desired roomTypes will be used.");
        }
        /*
            Collections.shuffle:通过使用指定的随机性对列表元素进行随机重新排序来工作
            将房间随机化
         */
        Collections.shuffle(roomList, rng.random);
        assignRoomsToNodes(map, roomList);
        logger.info("#### Unassigned Rooms:");
        Iterator<AbstractRoom> it = roomList.iterator();
        while (it.hasNext()) {
            AbstractRoom r = it.next();
            logger.info(r.getClass());
        }
        lastMinuteNodeChecker(map, null);
        return map;
    }
}