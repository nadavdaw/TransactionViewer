package com.example.nadav.transactionviewer.Models;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nadav on 14/02/2017.
 */

public class GraphRates {
    private Map<String, LinkedHashSet<nodeRate>> map = new HashMap<>();

    public void addEdge(String node1, String node2, float rate) {
        LinkedHashSet<nodeRate> adjacent = map.get(node1);
        if(adjacent==null) {
            adjacent = new LinkedHashSet<>();
            map.put(node1, adjacent);
        }
        adjacent.add(new nodeRate(node2,rate));
    }

    public void addTwoWayVertex(String node1, String node2,float rate) {
        addEdge(node1, node2,rate);
        addEdge(node2, node1,1/rate);
    }

    public boolean isConnected(String node1, String node2) {
        Set adjacent = map.get(node1);
        if(adjacent==null) {
            return false;
        }
        return adjacent.contains(node2);
    }

    public LinkedList<nodeRate> adjacentNodes(String last) {
        LinkedHashSet<nodeRate> adjacent = map.get(last);
        if(adjacent==null) {
            return new LinkedList<>();
        }
        return new LinkedList<nodeRate>(adjacent);
    }
}

