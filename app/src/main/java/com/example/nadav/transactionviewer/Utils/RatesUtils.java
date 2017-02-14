package com.example.nadav.transactionviewer.Utils;

import android.content.Context;

import com.example.nadav.transactionviewer.Models.GraphRates;
import com.example.nadav.transactionviewer.Models.Rate;
import com.example.nadav.transactionviewer.Models.nodeRate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by Nadav on 13/02/2017.
 */

public class RatesUtils {
    private static final String RATES = "rates.json";
    private static final String GBP = "GBP";
    private static final String END = "USD";

    public static void getRatesFromFile(Context context) {
        ArrayList<Rate> rates = new ArrayList<>();
        GraphRates graph = new GraphRates();

        String loadFromFile = GeneralUtils.loadJSONFromAsset(context, RATES);
        if (loadFromFile != null) {
            rates = getJsonArrayFromString(loadFromFile);
        }
        for (Rate item:rates){
            graph.addEdge(item.from,item.to,item.rate);
        }
//        LinkedList<nodeRate> visited = new LinkedList<>();
//        visited.add(new nodeRate(GBP,1));
//        getDirections(GBP,END,graph);
    }



    public static List getDirections(String start, String finish, GraphRates graph){
        LinkedList<nodeRate> nodes = graph.adjacentNodes(start);
        Map<nodeRate, Boolean> vis = new HashMap<>();
        Map<nodeRate, nodeRate> prev = new HashMap<>();
        List directions = new LinkedList();
        Queue q = nodes;
        nodeRate current = nodes.getLast();
        q.add(current);
        vis.put(current, true);
        while(!q.isEmpty()){
            current = (nodeRate) q.remove();
            if (current.to.contentEquals(finish)){
                break;
            }else{
                for(nodeRate node : nodes){
                    if(!vis.containsKey(node)){
                        q.add(node);
                        vis.put(node, true);
                        prev.put(node, current);
                    }
                }
            }
        }
        if (!current.equals(finish)){
            System.out.println("can't reach destination");
        }
        for(String node = finish; node != null; node.contentEquals(prev.get(node).to)) {
            directions.add(node);
        }
        return directions;
    }


    public static void depthFirst(GraphRates graph, LinkedList<nodeRate> visited) {
        LinkedList<nodeRate> nodes = graph.adjacentNodes(visited.getLast().to);
        // examine adjacent nodes
        for (nodeRate node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.to.contentEquals(END)) {
                visited.add(node);
                printPath(visited);
                visited.removeLast();
                break;
            }
        }
        for (nodeRate node : nodes) {
            if (visited.contains(node) || node.to.contentEquals(END)) {
                continue;
            }
            visited.addLast(node);
            depthFirst(graph, visited);
            visited.removeLast();
        }
    }
    public static void printPath(LinkedList<nodeRate> visited) {
        for (nodeRate node : visited) {
            System.out.print(node.to);
            System.out.print(" ");
        }
        System.out.println();
    }
    private static ArrayList<Rate> getJsonArrayFromString(String list) {
        ArrayList<Rate> rateArrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(list);
            if (jsonArray.length() != 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String from = jsonObject.getString("from");
                    String rate = jsonObject.getString("rate");
                    String to = jsonObject.getString("to");
                    if (from != null && rate != null && to != null) {
                        Rate rateTmp = new Rate(from, Float.valueOf(rate), to);
                        rateArrayList.add(rateTmp);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rateArrayList;
    }
}
