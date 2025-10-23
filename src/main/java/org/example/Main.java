package org.example;

public class Main {
    public static void main(String[] args) {
        // Создание графа
        Graph graph = new Graph();
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 1);

        // Создание и запуск алгоритма Прима с начальной вершины 0
        PrimMST primMST = new PrimMST(graph);
        primMST.run(0);

        // Вывод результатов
        System.out.println("Total Cost of MST: " + primMST.getTotalCost());
        System.out.println("Number of Edges in MST: " + primMST.getEdgeCount());
        System.out.println("Total Operations Count: " + primMST.getOperationCount());
        System.out.println("Execution Time (ms): " + primMST.getExecutionTime());
    }
}
