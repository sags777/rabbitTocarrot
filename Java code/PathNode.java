/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectai;

import java.text.*;

public class PathNode {

    private PuzzlePoints town;
    private int pathCost;
    private PathNode parent;

    public PathNode(PuzzlePoints tow, PathNode par, int cos) {
        town = tow;
        parent = par;
        pathCost = cos;
    }

    public String toString() {
        String solution = "" + town.name;
        PathNode sNode = parent;
        while (sNode != null) {
            solution = sNode.getTown().name+"->"+solution;
            sNode = sNode.getParent();
        }
        return solution;
    }

    public PuzzlePoints getTown() {
        return town;
    }

    public int getPathCost() {
        return pathCost;
    }

    public PathNode getParent() {
        return parent;
    }

}
