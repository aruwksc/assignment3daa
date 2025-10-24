package org.example;

import java.util.*;

public class PrimMST {
    private final Graph graph;
    private double totalCost;
    private int edgeCount;
    private int operationCount;
    private long startTime;
    private long endTime;

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

            if (!visited.contains(v)) {
                visited.add(v);
                totalCost += weight;
                edgeCount++;

                minHeap.addAll(graph.getNeighbours(v));
                operationCount++;
            }

            if (visited.size() == graph.getVertices().size()) {
                break;
            }
        }

        endTime = System.nanoTime();
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
        return (endTime - startTime) / 1_000_000;
    }

    public Map<String, Object> getExecutionData() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalCost", totalCost);
        data.put("edgeCount", edgeCount);
        data.put("operationCount", operationCount);
        data.put("executionTime", getExecutionTime());
        return data;
    }
}
