/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectai;


public class PuzzlePath {

    int distance;
    PuzzlePoints town;

    public PuzzlePath(int dist, PuzzlePoints t) {
        distance = dist;
        town = t;
    }

    public int getDistance() {
        return distance;
    }

    public PuzzlePoints getTown() {
        return town;
    }
}
