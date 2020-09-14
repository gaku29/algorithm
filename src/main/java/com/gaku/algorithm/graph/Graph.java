package com.gaku.algorithm.graph;

import java.util.LinkedList;
import java.util.List;

public class Graph {

    private final int V; // 顶点个数
    private int E;       // 边个数
    private List<Integer>[] adj; // 邻接表

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (List<Integer>[])new List[V];
        for(int v = 0; v < V; v++){
            adj[v] = new LinkedList<Integer>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }


}
