package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MSTTest {
    private Graph connectedGraph;
    private Graph disconnectedGraph;

    @BeforeEach
    public void setUp() {
        connectedGraph = new Graph();
        connectedGraph.addEdge(0, 1, 10);
        connectedGraph.addEdge(0, 2, 5);
        connectedGraph.addEdge(1, 2, 2);
        connectedGraph.addEdge(2, 3, 1);


        disconnectedGraph = new Graph();
        disconnectedGraph.addEdge(0, 1, 3);
        disconnectedGraph.addEdge(2, 3, 7);
    }
    @Test
    public void testMSTCostEquality() {
        PrimMST prim = new PrimMST(connectedGraph);
        prim.run(0);

        KruskalMST kruskal = new KruskalMST(connectedGraph);
        kruskal.run();

        Assertions.assertEquals(prim.getTotalCost(), kruskal.getTotalCost(), 0.0001,
                "Prim and Kruskal must have the same total MST cost");
    }

    @Test
    public void testEdgeCountEqualsVMinusOne() {
        PrimMST prim = new PrimMST(connectedGraph);
        prim.run(0);
        int expectedEdges = connectedGraph.getVertices().size() - 1;
        Assertions.assertEquals(expectedEdges, prim.getEdgeCount(),
                "Number of edges in MST must be V - 1");
    }

    @Test
    public void testNoCyclesInMST() {
        PrimMST prim = new PrimMST(connectedGraph);
        prim.run(0);

        Assertions.assertTrue(prim.getEdgeCount() <= connectedGraph.getVertices().size() - 1,
                "MST should not contain cycles");
    }

    @Test
    public void testGraphIsConnectedInMST() {
        PrimMST prim = new PrimMST(connectedGraph);
        prim.run(0);

        Assertions.assertEquals(connectedGraph.getVertices().size() - 1, prim.getEdgeCount(),
                "MST should connect all vertices");
    }

    @Test
    public void testDisconnectedGraphHandledProperly() {
        PrimMST prim = new PrimMST(disconnectedGraph);
        prim.run(0);

        Assertions.assertTrue(prim.getEdgeCount() < disconnectedGraph.getVertices().size() - 1,
                "Disconnected graph must not have a full MST");
    }

    @Test
    public void testPerformanceIsMeasured() {
        PrimMST prim = new PrimMST(connectedGraph);
        prim.run(0);
        Assertions.assertTrue(prim.getExecutionTime() >= 0,
                "Execution time must be non-negative");
    }

    @Test
    public void testOperationCountIsNonNegative() {
        KruskalMST kruskal = new KruskalMST(connectedGraph);
        kruskal.run();
        Assertions.assertTrue(kruskal.getOperationCount() >= 0,
                "Operation count must be non-negative");
    }
}