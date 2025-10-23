package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class KruskalMST {
    private final Graph graph;
    private double totalCost;
    private int edgeCount;
    private int operationCount;
    private long startTime;

    public KruskalMST(Graph graph) {
        this.graph = graph;
        this.totalCost = 0;
        this.edgeCount = 0;
        this.operationCount = 0;
    }

    public void run() {
        Set<Integer> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();
        edges.sort(Comparator.comparingDouble(Edge::getWeight));

        UnionFind unionFind = new UnionFind(vertices);

        startTime = System.nanoTime();

        for (Edge edge : edges) {
            operationCount++;
            int u = edge.getU();
            int v = edge.getV();
            double weight = edge.getWeight();


            if(!unionFind.connected(u, v)){
                unionFind.union(u, v);
                totalCost += weight;
                edgeCount++;

                if(edgeCount == vertices.size()-1){
                    break;
                }
            }
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Total cost: " + totalCost);
        System.out.println("Edges count: " + edgeCount);
        System.out.println("Execution Time (ms): " + (duration / 1000000));
    }
    public double getTotalCost() {
        return totalCost;
    }
    public int getEdgeCount() {
        return edgeCount;
    }
    public int getOperationCount() {
        return operationCount;
    }
    public long getExecutionTime() {
        return (System.nanoTime() - startTime) / 1000000;
    }
}
