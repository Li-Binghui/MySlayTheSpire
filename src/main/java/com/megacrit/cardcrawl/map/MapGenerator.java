package com.megacrit.cardcrawl.map;

import com.megacrit.cardcrawl.daily.mods.CertainFuture;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.random.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/map/MapGenerator.class */
public class MapGenerator {
    private static final Logger logger;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !MapGenerator.class.desiredAssertionStatus();
        logger = LogManager.getLogger(MapGenerator.class.getName());
    }

    public static ArrayList<ArrayList<MapRoomNode>> generateDungeon(int height, int width, int pathDensity, Random rng) {
        ArrayList<ArrayList<MapRoomNode>> map;
        ArrayList<ArrayList<MapRoomNode>> map2 = createNodes(height, width);
        if (ModHelper.isModEnabled(CertainFuture.ID)) {
            map = createPaths(map2, 1, rng);
        } else {
            map = createPaths(map2, pathDensity, rng);
        }
        return filterRedundantEdgesFromRow(map);
    }

    private static ArrayList<ArrayList<MapRoomNode>> filterRedundantEdgesFromRow(ArrayList<ArrayList<MapRoomNode>> map) {
        ArrayList<MapEdge> existingEdges = new ArrayList<>();
        ArrayList<MapEdge> deleteList = new ArrayList<>();
        Iterator<MapRoomNode> it = map.get(0).iterator();
        while (it.hasNext()) {
            MapRoomNode node = it.next();
            if (node.hasEdges()) {
                Iterator<MapEdge> it2 = node.getEdges().iterator();
                while (it2.hasNext()) {
                    MapEdge edge = it2.next();
                    Iterator<MapEdge> it3 = existingEdges.iterator();
                    while (it3.hasNext()) {
                        MapEdge prevEdge = it3.next();
                        if (edge.dstX == prevEdge.dstX && edge.dstY == prevEdge.dstY) {
                            deleteList.add(edge);
                        }
                    }
                    existingEdges.add(edge);
                }
                Iterator<MapEdge> it4 = deleteList.iterator();
                while (it4.hasNext()) {
                    node.delEdge(it4.next());
                }
                deleteList.clear();
            }
        }
        return map;
    }

    private static ArrayList<ArrayList<MapRoomNode>> createNodes(int height, int width) {
        ArrayList<ArrayList<MapRoomNode>> nodes = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            ArrayList<MapRoomNode> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                row.add(new MapRoomNode(x, y));
            }
            nodes.add(row);
        }
        return nodes;
    }

    private static ArrayList<ArrayList<MapRoomNode>> createPaths(ArrayList<ArrayList<MapRoomNode>> nodes, int pathDensity, Random rng) {
        int row_size = nodes.get(0).size() - 1;
        int firstStartingNode = -1;
        for (int i = 0; i < pathDensity; i++) {
            int startingNode = randRange(rng, 0, row_size);
            if (i == 0) {
                firstStartingNode = startingNode;
            }
            while (startingNode == firstStartingNode && i == 1) {
                startingNode = randRange(rng, 0, row_size);
            }
            _createPaths(nodes, new MapEdge(startingNode, -1, startingNode, 0), rng);
        }
        return nodes;
    }

    private static MapEdge getMaxEdge(ArrayList<MapEdge> edges) {
        Collections.sort(edges, new EdgeComparator());
        if ($assertionsDisabled || !edges.isEmpty()) {
            return edges.get(edges.size() - 1);
        }
        throw new AssertionError("Somehow the edges are empty. This shouldn't happen.");
    }

    private static MapEdge getMinEdge(ArrayList<MapEdge> edges) {
        Collections.sort(edges, new EdgeComparator());
        if ($assertionsDisabled || !edges.isEmpty()) {
            return edges.get(0);
        }
        throw new AssertionError("Somehow the edges are empty. This shouldn't happen.");
    }

    private static MapRoomNode getNodeWithMaxX(ArrayList<MapRoomNode> nodes) {
        if ($assertionsDisabled || !nodes.isEmpty()) {
            MapRoomNode max = nodes.get(0);
            Iterator<MapRoomNode> it = nodes.iterator();
            while (it.hasNext()) {
                MapRoomNode node = it.next();
                if (node.x > max.x) {
                    max = node;
                }
            }
            return max;
        }
        throw new AssertionError("The nodes are empty, this shouldn't happen.");
    }

    private static MapRoomNode getNodeWithMinX(ArrayList<MapRoomNode> nodes) {
        if ($assertionsDisabled || !nodes.isEmpty()) {
            MapRoomNode min = nodes.get(0);
            Iterator<MapRoomNode> it = nodes.iterator();
            while (it.hasNext()) {
                MapRoomNode node = it.next();
                if (node.x < min.x) {
                    min = node;
                }
            }
            return min;
        }
        throw new AssertionError("The nodes are empty, this shouldn't happen.");
    }

    private static MapRoomNode getCommonAncestor(MapRoomNode node1, MapRoomNode node2, int max_depth) {
        MapRoomNode r_node;
        MapRoomNode l_node;
        if (!$assertionsDisabled && node1.y != node2.y) {
            throw new AssertionError();
        } else if ($assertionsDisabled || node1 != node2) {
            if (node1.x < node2.y) {
                l_node = node1;
                r_node = node2;
            } else {
                l_node = node2;
                r_node = node1;
            }
            for (int current_y = node1.y; current_y >= 0 && current_y >= node1.y - max_depth && !l_node.getParents().isEmpty() && !r_node.getParents().isEmpty(); current_y--) {
                l_node = getNodeWithMaxX(l_node.getParents());
                r_node = getNodeWithMinX(r_node.getParents());
                if (l_node == r_node) {
                    return l_node;
                }
            }
            return null;
        } else {
            throw new AssertionError();
        }
    }

    private static ArrayList<ArrayList<MapRoomNode>> _createPaths(ArrayList<ArrayList<MapRoomNode>> nodes, MapEdge edge, Random rng) {
        int max;
        int min;
        MapRoomNode ancestor;
        MapRoomNode currentNode = getNode(edge.dstX, edge.dstY, nodes);
        if (edge.dstY + 1 >= nodes.size()) {
            currentNode.addEdge(new MapEdge(edge.dstX, edge.dstY, currentNode.offsetX, currentNode.offsetY, 3, edge.dstY + 2, 0.0f, 0.0f, true));
            Collections.sort(currentNode.getEdges(), new EdgeComparator());
            return nodes;
        }
        int row_width = nodes.get(edge.dstY).size();
        int row_end_node = row_width - 1;
        if (edge.dstX == 0) {
            min = 0;
            max = 1;
        } else if (edge.dstX == row_end_node) {
            min = -1;
            max = 0;
        } else {
            min = -1;
            max = 1;
        }
        int newEdgeX = edge.dstX + randRange(rng, min, max);
        int newEdgeY = edge.dstY + 1;
        MapRoomNode targetNodeCandidate = getNode(newEdgeX, newEdgeY, nodes);
        ArrayList<MapRoomNode> parents = targetNodeCandidate.getParents();
        if (!parents.isEmpty()) {
            Iterator<MapRoomNode> it = parents.iterator();
            while (it.hasNext()) {
                MapRoomNode parent = it.next();
                if (!(parent == currentNode || (ancestor = getCommonAncestor(parent, currentNode, 5)) == null)) {
                    int ancestor_gap = newEdgeY - ancestor.y;
                    if (ancestor_gap < 3) {
                        if (targetNodeCandidate.x > currentNode.x) {
                            newEdgeX = edge.dstX + randRange(rng, -1, 0);
                            if (newEdgeX < 0) {
                                newEdgeX = edge.dstX;
                            }
                        } else if (targetNodeCandidate.x == currentNode.x) {
                            newEdgeX = edge.dstX + randRange(rng, -1, 1);
                            if (newEdgeX > row_end_node) {
                                newEdgeX = edge.dstX - 1;
                            } else if (newEdgeX < 0) {
                                newEdgeX = edge.dstX + 1;
                            }
                        } else {
                            newEdgeX = edge.dstX + randRange(rng, 0, 1);
                            if (newEdgeX > row_end_node) {
                                newEdgeX = edge.dstX;
                            }
                        }
                        targetNodeCandidate = getNode(newEdgeX, newEdgeY, nodes);
                    } else if (ancestor_gap >= 5) {
                    }
                }
            }
        }
        if (edge.dstX != 0) {
            MapRoomNode left_node = nodes.get(edge.dstY).get(edge.dstX - 1);
            if (left_node.hasEdges()) {
                MapEdge right_edge_of_left_node = getMaxEdge(left_node.getEdges());
                if (right_edge_of_left_node.dstX > newEdgeX) {
                    newEdgeX = right_edge_of_left_node.dstX;
                }
            }
        }
        if (edge.dstX < row_end_node) {
            MapRoomNode right_node = nodes.get(edge.dstY).get(edge.dstX + 1);
            if (right_node.hasEdges()) {
                MapEdge left_edge_of_right_node = getMinEdge(right_node.getEdges());
                if (left_edge_of_right_node.dstX < newEdgeX) {
                    newEdgeX = left_edge_of_right_node.dstX;
                }
            }
        }
        MapRoomNode targetNodeCandidate2 = getNode(newEdgeX, newEdgeY, nodes);
        MapEdge newEdge = new MapEdge(edge.dstX, edge.dstY, currentNode.offsetX, currentNode.offsetY, newEdgeX, newEdgeY, targetNodeCandidate2.offsetX, targetNodeCandidate2.offsetY, false);
        currentNode.addEdge(newEdge);
        Collections.sort(currentNode.getEdges(), new EdgeComparator());
        targetNodeCandidate2.addParent(currentNode);
        return _createPaths(nodes, newEdge, rng);
    }

    private static MapRoomNode getNode(int x, int y, ArrayList<ArrayList<MapRoomNode>> nodes) {
        return nodes.get(y).get(x);
    }

    private static String paddingGenerator(int length) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(" ");
        }
        return str.toString();
    }

    public static String toString(ArrayList<ArrayList<MapRoomNode>> nodes) {
        return toString(nodes, false);
    }

    public static String toString(ArrayList<ArrayList<MapRoomNode>> nodes, Boolean showRoomSymbols) {
        StringBuilder str = new StringBuilder();
        for (int row_num = nodes.size() - 1; row_num >= 0; row_num--) {
            str.append("\n ").append(paddingGenerator(5));
            Iterator<MapRoomNode> it = nodes.get(row_num).iterator();
            while (it.hasNext()) {
                MapRoomNode node = it.next();
                String right = " ";
                String mid = " ";
                String left = " ";
                Iterator<MapEdge> it2 = node.getEdges().iterator();
                while (it2.hasNext()) {
                    MapEdge edge = it2.next();
                    if (edge.dstX < node.x) {
                        left = "\\";
                    }
                    if (edge.dstX == node.x) {
                        mid = "|";
                    }
                    if (edge.dstX > node.x) {
                        right = "/";
                    }
                }
                str.append(left).append(mid).append(right);
            }
            str.append("\n").append(row_num).append(" ");
            str.append(paddingGenerator(5 - String.valueOf(row_num).length()));
            Iterator<MapRoomNode> it3 = nodes.get(row_num).iterator();
            while (it3.hasNext()) {
                MapRoomNode node2 = it3.next();
                String node_symbol = " ";
                if (row_num == nodes.size() - 1) {
                    Iterator<MapRoomNode> it4 = nodes.get(row_num - 1).iterator();
                    while (it4.hasNext()) {
                        MapRoomNode lower_node = it4.next();
                        Iterator<MapEdge> it5 = lower_node.getEdges().iterator();
                        while (it5.hasNext()) {
                            if (it5.next().dstX == node2.x) {
                                node_symbol = node2.getRoomSymbol(showRoomSymbols);
                            }
                        }
                    }
                } else if (node2.hasEdges()) {
                    node_symbol = node2.getRoomSymbol(showRoomSymbols);
                }
                str.append(" ").append(node_symbol).append(" ");
            }
        }
        return str.toString();
    }

    private static int randRange(Random rng, int min, int max) {
        if (rng == null) {
            logger.info("RNG WAS NULL, REPORT IMMEDIATELY");
            rng = new Random(1L);
        }
        return rng.random(max - min) + min;
    }
}