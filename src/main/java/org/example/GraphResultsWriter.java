package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class GraphResultsWriter {
    public static void writeResultsToFile(Map<String, Object> primData, Map<String, Object> kruskalData, String filename) throws IOException {
        Gson gson = new Gson();
        JsonObject results = new JsonObject();

        JsonObject primResults = new JsonObject();
        primResults.addProperty("totalCost", (Double) primData.get("totalCost"));
        primResults.addProperty("edgeCount", (Integer) primData.get("edgeCount"));
        primResults.addProperty("operationCount", (Integer) primData.get("operationCount"));
        primResults.addProperty("executionTime", (Long) primData.get("executionTime"));

        JsonObject kruskalResults = new JsonObject();
        kruskalResults.addProperty("totalCost", (Double) kruskalData.get("totalCost"));
        kruskalResults.addProperty("edgeCount", (Integer) kruskalData.get("edgeCount"));
        kruskalResults.addProperty("operationCount", (Integer) kruskalData.get("operationCount"));
        kruskalResults.addProperty("executionTime", (Long) kruskalData.get("executionTime"));

        results.add("Prim", primResults);
        results.add("Kruskal", kruskalResults);

        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(results, writer);
        }
    }
}
