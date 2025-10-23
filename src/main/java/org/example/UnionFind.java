package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UnionFind {
    private final Map<Integer, Integer> parent;
    private final Map<Integer, Integer> rank;

    public UnionFind(Set<Integer> vertices) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        for (Integer vertex : vertices) {
            parent.put(vertex, vertex);
            rank.put(vertex, 0);
        }
    }
    public int find(int vertex) {
        if (parent.get(vertex) !=vertex) {
            parent.put(vertex, find(parent.get(vertex)));
        }
        return parent.get(vertex);
    }
    public void union(int vertex1, int vertex2) {
        int root1 = find(vertex1);
        int root2 = find(vertex2);
        if (root1 != root2) {
            if (rank.get(root1)>rank.get(root2)){
                parent.put(root2, root1);
            } else if (rank.get(root1)<rank.get(root2)){
                parent.put(root1, root2);
            }else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
        }
    }
    public boolean connected(int vertex1, int vertex2) {
        return find(vertex1) == find(vertex2);
    }
}
