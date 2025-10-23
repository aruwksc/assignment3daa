package org.example;

public class Edge {
    private final int u;
    private final int v;
    private final double weight;

    public Edge(int u, int v, double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
    public int getU() {
        return u;
    }
    public int getV() {
        return v;
    }
    public double getWeight() {
        return weight;
    }
    @Override
    public String toString() {
        return "(" + u + ", " + v + ") with weight " + weight ;
    }
}
