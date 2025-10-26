package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonToCsv {

    static class EdgeOut {
        String from;
        String to;
        double weight;

        EdgeOut(String f, String t, double w) {
            from = f;
            to = t;
            weight = w;
        }
    }

    static class AlgoOut {
        @SerializedName("mst_edges")
        List<EdgeOut> mstEdges = new ArrayList<>();
        @SerializedName("total_cost")
        double totalCost;
        @SerializedName("operations_count")
        long operationsCount;
        @SerializedName("execution_time_ms")
        double executionTimeMs;
    }

    static class GraphResult {
        @SerializedName("graph_id")
        int graphId;
        @SerializedName("input_stats")
        InputStats inputStats;
        AlgoOut prim;
        AlgoOut kruskal;
    }

    static class InputStats {
        int vertices;
        int edges;
    }

    static class OutputRoot {
        List<GraphResult> results = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        String inputPath = "output.json";
        String outputPath = "output.csv";

        try (Reader reader = new FileReader(inputPath)) {
            Gson gson = new Gson();
            OutputRoot root = gson.fromJson(reader, OutputRoot.class);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                writer.write("graph_id,algorithm,vertices,edges,total_cost,operations_count,execution_time_ms");
                writer.newLine();

                for (GraphResult graph : root.results) {
                    writer.write(graph.graphId + ",Prim," + graph.inputStats.vertices + ","
                            + graph.inputStats.edges + "," + graph.prim.totalCost + ","
                            + graph.prim.operationsCount + "," + graph.prim.executionTimeMs);
                    writer.newLine();

                    writer.write(graph.graphId + ",Kruskal," + graph.inputStats.vertices + ","
                            + graph.inputStats.edges + "," + graph.kruskal.totalCost + ","
                            + graph.kruskal.operationsCount + "," + graph.kruskal.executionTimeMs);
                    writer.newLine();
                }

                System.out.println("CSV file created successfully!");
            }
        }
    }
}
