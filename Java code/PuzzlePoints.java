/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectai;

import java.util.*;

public class PuzzlePoints {

    String name;
    LinkedList roads;

    public PuzzlePoints(String nam) {
        name = nam;
        roads = new LinkedList();
    }

    public void addRoad(int dis, PuzzlePoints tow) {
        roads.add(new PuzzlePath(dis, tow));
    }

    public String getName()
    {
        return name;
    }

    public int countRoads() {
        return roads.size();
    }

    public PuzzlePath getRoad(int dex) {
        return (PuzzlePath) roads.get(dex);
    }

}

