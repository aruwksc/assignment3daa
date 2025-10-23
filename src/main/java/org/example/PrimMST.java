package org.example;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimMST {
    private final Graph graph;
    private double totalCost;
    private int edgeCount;
    private int operationCount;
    private long startTime;

    public PrimMST(Graph graph) {
        this.graph = graph;
        this.totalCost = 0;
        this.edgeCount = 0;
        this.operationCount = 0;
    }
    public void run(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));

        visited.add(startVertex);
        minHeap.addAll(graph.getNeighbours(startVertex));

        startTime = System.nanoTime();
        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            operationCount++;

            int u = edge.getU();
            int v = edge.getV();
            double weight = edge.getWeight();

            if(!visited.contains(v)) {
                visited.add(v);
                totalCost += weight;
                edgeCount++;

                minHeap.addAll(graph.getNeighbours(v));
                operationCount++;
            }

            if(visited.size() == graph.getVertices().size()) {
                break;
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
