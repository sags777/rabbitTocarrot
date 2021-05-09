/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectai;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Puzzle {

    private int size;
    String[][] grid;

    public Puzzle(String fName) {
        loadPuzzleFromFile(fName);
    }

    public void loadPuzzleFromFile(String fName) {
        File inFile = new File(fName);

        try {
            Scanner fileScan = new Scanner(inFile);
            size = fileScan.nextInt();
            grid = new String[size][size];

            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    grid[row][col] = fileScan.next();
                    System.out.print(" " + grid[row][col]);
                }
                System.out.print("\n");
            }
            fileScan.close();
        } catch (Exception e) {
            String mess = "An " + e + " was thrown while \nworking with the file: " + fName;
            JOptionPane.showMessageDialog(null, mess, "Error message", 0);
            System.exit(0);
        }
    }

    public String getGridPosition(int row, int col) {
        return grid[row][col];
    }

    public int getSize() {
        return size;
    }
}
