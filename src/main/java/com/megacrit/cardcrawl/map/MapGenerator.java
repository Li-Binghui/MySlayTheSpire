/*     */ package com.megacrit.cardcrawl.map;
/*     */ 
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapGenerator
/*     */ {
/*  14 */   private static final Logger logger = LogManager.getLogger(MapGenerator.class.getName());
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
/*     */   public static ArrayList<ArrayList<MapRoomNode>> generateDungeon(int height, int width, int pathDensity, Random rng) {
/*  38 */     ArrayList<ArrayList<MapRoomNode>> map = createNodes(height, width);
/*  39 */     if (ModHelper.isModEnabled("Uncertain Future")) {
/*  40 */       map = createPaths(map, 1, rng);
/*     */     } else {
/*  42 */       map = createPaths(map, pathDensity, rng);
/*     */     } 
/*  44 */     map = filterRedundantEdgesFromRow(map);
/*  45 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArrayList<ArrayList<MapRoomNode>> filterRedundantEdgesFromRow(ArrayList<ArrayList<MapRoomNode>> map) {
/*  56 */     ArrayList<MapEdge> existingEdges = new ArrayList<>();
/*  57 */     ArrayList<MapEdge> deleteList = new ArrayList<>();
/*     */     
/*  59 */     for (MapRoomNode node : map.get(0)) {
/*  60 */       if (node.hasEdges()) {
/*  61 */         for (MapEdge edge : node.getEdges()) {
/*     */           
/*  63 */           for (MapEdge prevEdge : existingEdges) {
/*  64 */             if (edge.dstX == prevEdge.dstX && edge.dstY == prevEdge.dstY)
/*     */             {
/*  66 */               deleteList.add(edge);
/*     */             }
/*     */           } 
/*  69 */           existingEdges.add(edge);
/*     */         } 
/*     */         
/*  72 */         for (MapEdge edge : deleteList) {
/*  73 */           node.delEdge(edge);
/*     */         }
/*  75 */         deleteList.clear();
/*     */       } 
/*     */     } 
/*  78 */     return map;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ArrayList<ArrayList<MapRoomNode>> createNodes(int height, int width) {
/*  83 */     ArrayList<ArrayList<MapRoomNode>> nodes = new ArrayList<>();
/*  84 */     for (int y = 0; y < height; y++) {
/*  85 */       ArrayList<MapRoomNode> row = new ArrayList<>();
/*  86 */       for (int x = 0; x < width; x++) {
/*  87 */         row.add(new MapRoomNode(x, y));
/*     */       }
/*  89 */       nodes.add(row);
/*     */     } 
/*  91 */     return nodes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArrayList<ArrayList<MapRoomNode>> createPaths(ArrayList<ArrayList<MapRoomNode>> nodes, int pathDensity, Random rng) {
/*  99 */     int first_row = 0;
/* 100 */     int row_size = ((ArrayList)nodes.get(first_row)).size() - 1;
/*     */     
/* 102 */     int firstStartingNode = -1;
/*     */     
/* 104 */     for (int i = 0; i < pathDensity; i++) {
/* 105 */       int startingNode = randRange(rng, 0, row_size);
/*     */       
/* 107 */       if (i == 0) {
/* 108 */         firstStartingNode = startingNode;
/*     */       }
/*     */       
/* 111 */       while (startingNode == firstStartingNode && i == 1) {
/* 112 */         startingNode = randRange(rng, 0, row_size);
/*     */       }
/*     */       
/* 115 */       _createPaths(nodes, new MapEdge(startingNode, -1, startingNode, 0), rng);
/*     */     } 
/* 117 */     return nodes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static MapEdge getMaxEdge(ArrayList<MapEdge> edges) {
/* 128 */     Collections.sort(edges, new EdgeComparator());
/* 129 */     assert !edges.isEmpty() : "Somehow the edges are empty. This shouldn't happen.";
/* 130 */     return edges.get(edges.size() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static MapEdge getMinEdge(ArrayList<MapEdge> edges) {
/* 141 */     Collections.sort(edges, new EdgeComparator());
/* 142 */     assert !edges.isEmpty() : "Somehow the edges are empty. This shouldn't happen.";
/* 143 */     return edges.get(0);
/*     */   }
/*     */   
/*     */   private static MapRoomNode getNodeWithMaxX(ArrayList<MapRoomNode> nodes) {
/* 147 */     assert !nodes.isEmpty() : "The nodes are empty, this shouldn't happen.";
/* 148 */     MapRoomNode max = nodes.get(0);
/* 149 */     for (MapRoomNode node : nodes) {
/* 150 */       if (node.x > max.x) {
/* 151 */         max = node;
/*     */       }
/*     */     } 
/* 154 */     return max;
/*     */   }
/*     */   
/*     */   private static MapRoomNode getNodeWithMinX(ArrayList<MapRoomNode> nodes) {
/* 158 */     assert !nodes.isEmpty() : "The nodes are empty, this shouldn't happen.";
/* 159 */     MapRoomNode min = nodes.get(0);
/* 160 */     for (MapRoomNode node : nodes) {
/* 161 */       if (node.x < min.x) {
/* 162 */         min = node;
/*     */       }
/*     */     } 
/* 165 */     return min;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static MapRoomNode getCommonAncestor(MapRoomNode node1, MapRoomNode node2, int max_depth) {
/*     */     MapRoomNode l_node, r_node;
/* 174 */     assert node1.y == node2.y;
/* 175 */     assert node1 != node2;
/*     */ 
/*     */     
/* 178 */     if (node1.x < node2.y) {
/* 179 */       l_node = node1;
/* 180 */       r_node = node2;
/*     */     } else {
/* 182 */       l_node = node2;
/* 183 */       r_node = node1;
/*     */     } 
/* 185 */     int current_y = node1.y;
/* 186 */     while (current_y >= 0 && current_y >= node1.y - max_depth) {
/* 187 */       if (l_node.getParents().isEmpty() || r_node.getParents().isEmpty()) {
/* 188 */         return null;
/*     */       }
/* 190 */       l_node = getNodeWithMaxX(l_node.getParents());
/* 191 */       r_node = getNodeWithMinX(r_node.getParents());
/* 192 */       if (l_node == r_node) {
/* 193 */         return l_node;
/*     */       }
/* 195 */       current_y--;
/*     */     } 
/* 197 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArrayList<ArrayList<MapRoomNode>> _createPaths(ArrayList<ArrayList<MapRoomNode>> nodes, MapEdge edge, Random rng) {
/*     */     int min, max;
/* 205 */     MapRoomNode currentNode = getNode(edge.dstX, edge.dstY, nodes);
/* 206 */     if (edge.dstY + 1 >= nodes.size()) {
/*     */       
/* 208 */       MapEdge mapEdge = new MapEdge(edge.dstX, edge.dstY, currentNode.offsetX, currentNode.offsetY, 3, edge.dstY + 2, 0.0F, 0.0F, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 218 */       currentNode.addEdge(mapEdge);
/* 219 */       Collections.sort(currentNode.getEdges(), new EdgeComparator());
/* 220 */       return nodes;
/*     */     } 
/* 222 */     int row_width = ((ArrayList)nodes.get(edge.dstY)).size();
/* 223 */     int row_end_node = row_width - 1;
/*     */ 
/*     */ 
/*     */     
/* 227 */     if (edge.dstX == 0) {
/* 228 */       min = 0;
/* 229 */       max = 1;
/* 230 */     } else if (edge.dstX == row_end_node) {
/* 231 */       min = -1;
/* 232 */       max = 0;
/*     */     } else {
/* 234 */       min = -1;
/* 235 */       max = 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 240 */     int newEdgeX = edge.dstX + randRange(rng, min, max);
/* 241 */     int newEdgeY = edge.dstY + 1;
/*     */     
/* 243 */     MapRoomNode targetNodeCandidate = getNode(newEdgeX, newEdgeY, nodes);
/*     */ 
/*     */     
/* 246 */     int min_ancestor_gap = 3;
/* 247 */     int max_ancestor_gap = 5;
/* 248 */     ArrayList<MapRoomNode> parents = targetNodeCandidate.getParents();
/* 249 */     if (!parents.isEmpty()) {
/* 250 */       for (MapRoomNode parent : parents) {
/* 251 */         if (parent != currentNode) {
/* 252 */           MapRoomNode ancestor = getCommonAncestor(parent, currentNode, max_ancestor_gap);
/* 253 */           if (ancestor != null) {
/* 254 */             int ancestor_gap = newEdgeY - ancestor.y;
/* 255 */             if (ancestor_gap < min_ancestor_gap) {
/* 256 */               if (targetNodeCandidate.x > currentNode.x) {
/*     */                 
/* 258 */                 newEdgeX = edge.dstX + randRange(rng, -1, 0);
/* 259 */                 if (newEdgeX < 0) {
/* 260 */                   newEdgeX = edge.dstX;
/*     */                 }
/* 262 */               } else if (targetNodeCandidate.x == currentNode.x) {
/*     */                 
/* 264 */                 newEdgeX = edge.dstX + randRange(rng, -1, 1);
/* 265 */                 if (newEdgeX > row_end_node) {
/* 266 */                   newEdgeX = edge.dstX - 1;
/* 267 */                 } else if (newEdgeX < 0) {
/* 268 */                   newEdgeX = edge.dstX + 1;
/*     */                 } 
/*     */               } else {
/*     */                 
/* 272 */                 newEdgeX = edge.dstX + randRange(rng, 0, 1);
/* 273 */                 if (newEdgeX > row_end_node) {
/* 274 */                   newEdgeX = edge.dstX;
/*     */                 }
/*     */               } 
/* 277 */               targetNodeCandidate = getNode(newEdgeX, newEdgeY, nodes); continue;
/* 278 */             }  if (ancestor_gap >= max_ancestor_gap);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 288 */     if (edge.dstX != 0) {
/*     */ 
/*     */ 
/*     */       
/* 292 */       MapRoomNode left_node = ((ArrayList<MapRoomNode>)nodes.get(edge.dstY)).get(edge.dstX - 1);
/*     */ 
/*     */       
/* 295 */       if (left_node.hasEdges()) {
/* 296 */         MapEdge right_edge_of_left_node = getMaxEdge(left_node.getEdges());
/* 297 */         if (right_edge_of_left_node.dstX > newEdgeX) {
/* 298 */           newEdgeX = right_edge_of_left_node.dstX;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 303 */     if (edge.dstX < row_end_node) {
/*     */ 
/*     */       
/* 306 */       MapRoomNode right_node = ((ArrayList<MapRoomNode>)nodes.get(edge.dstY)).get(edge.dstX + 1);
/*     */ 
/*     */       
/* 309 */       if (right_node.hasEdges()) {
/* 310 */         MapEdge left_edge_of_right_node = getMinEdge(right_node.getEdges());
/* 311 */         if (left_edge_of_right_node.dstX < newEdgeX) {
/* 312 */           newEdgeX = left_edge_of_right_node.dstX;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 318 */     targetNodeCandidate = getNode(newEdgeX, newEdgeY, nodes);
/*     */     
/* 320 */     MapEdge newEdge = new MapEdge(edge.dstX, edge.dstY, currentNode.offsetX, currentNode.offsetY, newEdgeX, newEdgeY, targetNodeCandidate.offsetX, targetNodeCandidate.offsetY, false);
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
/* 331 */     currentNode.addEdge(newEdge);
/* 332 */     Collections.sort(currentNode.getEdges(), new EdgeComparator());
/*     */     
/* 334 */     targetNodeCandidate.addParent(currentNode);
/* 335 */     return _createPaths(nodes, newEdge, rng);
/*     */   }
/*     */   
/*     */   private static MapRoomNode getNode(int x, int y, ArrayList<ArrayList<MapRoomNode>> nodes) {
/* 339 */     return ((ArrayList<MapRoomNode>)nodes.get(y)).get(x);
/*     */   }
/*     */   
/*     */   private static String paddingGenerator(int length) {
/* 343 */     StringBuilder str = new StringBuilder();
/* 344 */     for (int i = 0; i < length; i++) {
/* 345 */       str.append(" ");
/*     */     }
/* 347 */     return str.toString();
/*     */   }
/*     */   
/*     */   public static String toString(ArrayList<ArrayList<MapRoomNode>> nodes) {
/* 351 */     return toString(nodes, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public static String toString(ArrayList<ArrayList<MapRoomNode>> nodes, Boolean showRoomSymbols) {
/* 355 */     StringBuilder str = new StringBuilder();
/* 356 */     int row_num = nodes.size() - 1;
/* 357 */     int left_padding_size = 5;
/* 358 */     while (row_num >= 0) {
/* 359 */       str.append("\n ").append(paddingGenerator(left_padding_size));
/*     */       
/* 361 */       for (MapRoomNode node : nodes.get(row_num)) {
/*     */         
/* 363 */         String right = " ", mid = right, left = mid;
/* 364 */         for (MapEdge edge : node.getEdges()) {
/* 365 */           if (edge.dstX < node.x)
/* 366 */             left = "\\"; 
/* 367 */           if (edge.dstX == node.x)
/* 368 */             mid = "|"; 
/* 369 */           if (edge.dstX > node.x)
/* 370 */             right = "/"; 
/*     */         } 
/* 372 */         str.append(left).append(mid).append(right);
/*     */       } 
/* 374 */       str.append("\n").append(row_num).append(" ");
/*     */       
/* 376 */       str.append(paddingGenerator(left_padding_size - String.valueOf(row_num).length()));
/* 377 */       for (MapRoomNode node : nodes.get(row_num)) {
/* 378 */         String node_symbol = " ";
/* 379 */         if (row_num == nodes.size() - 1) {
/* 380 */           for (MapRoomNode lower_node : nodes.get(row_num - 1)) {
/* 381 */             for (MapEdge edge : lower_node.getEdges()) {
/* 382 */               if (edge.dstX == node.x)
/* 383 */                 node_symbol = node.getRoomSymbol(showRoomSymbols); 
/*     */             } 
/*     */           } 
/* 386 */         } else if (node.hasEdges()) {
/* 387 */           node_symbol = node.getRoomSymbol(showRoomSymbols);
/*     */         } 
/* 389 */         str.append(" ").append(node_symbol).append(" ");
/*     */       } 
/* 391 */       row_num--;
/*     */     } 
/* 393 */     return str.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int randRange(Random rng, int min, int max) {
/* 404 */     if (rng == null) {
/* 405 */       logger.info("RNG WAS NULL, REPORT IMMEDIATELY");
/* 406 */       rng = new Random(Long.valueOf(1L));
/*     */     } 
/* 408 */     return rng.random(max - min) + min;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\map\MapGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */