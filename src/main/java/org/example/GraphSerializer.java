package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GraphSerializer {

    public static void writeGraphToFile(Graph graph, String filename) throws IOException {
        Gson gson = new Gson();
        JsonObject graphJson = new JsonObject();

        for (Integer vertex : graph.getVertices()) {
            JsonObject vertexJson = new JsonObject();

            List<Edge> edges = graph.getNeighbours(vertex);

            for (Edge edge : edges) {
                vertexJson.addProperty(String.valueOf(edge.getV()), edge.getWeight());
            }

            graphJson.add(String.valueOf(vertex), vertexJson);
        }


        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(graphJson, writer);
        }
    }

    public static Graph readGraphFromFile(String filename) throws IOException {
        Gson gson = new Gson();
        Graph graph = new Graph();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            JsonObject graphJson = gson.fromJson(reader, JsonObject.class);


            for (String vertex : graphJson.keySet()) {
                JsonObject vertexData = graphJson.getAsJsonObject(vertex);

                for (String neighbour : vertexData.keySet()) {
                    double weight = vertexData.getAsJsonPrimitive(neighbour).getAsDouble();
                    graph.addEdge(Integer.parseInt(vertex), Integer.parseInt(neighbour), weight);
                }
            }
        }

        return graph;
    }
}
