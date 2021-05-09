/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectai;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import java.io.File;
import java.lang.reflect.Array;
import javax.swing.*;

public class PathFinder {

    HashMap<String, PuzzlePoints> map;
    String name;
    String[] names;
    String[] points;
    String[][] positions;
    String[][] index = new String[5][5];

    public PathFinder(String nam, String cityFName, String edgeFName, String positions) {
        name = nam;
        map = new HashMap<String, PuzzlePoints>();
        loadTownsFromFile(cityFName);
        loadRoadsFromFile(edgeFName);
        loadPositionFromFile(positions); 
    }

    public String GetSolution(String initialName, String goalName) {
        PuzzlePoints initialNode = new PuzzlePoints(initialName);
        PuzzlePoints goal = new PuzzlePoints(goalName);
        String message = "";

        try {
            initialNode = map.get(initialName);
            goal = map.get(goalName);
            PathNode startNode = pathFinder(initialNode, goal);
            return startNode.toString();
        } catch (Exception e) {
            if (initialNode == null && goal == null) {
                message = " No Start-node and End-node defined";
            } else if (initialNode == null) {
                message = "Town " + initialName + " not found in file.";
            } else if (goal == null) {
                message = "Town " + goalName + " not found in file.";
            }
        }
        return message;
    }

    public PathNode pathFinder(PuzzlePoints startNode, PuzzlePoints goal) {
        PathNode rootNode = new PathNode(startNode, null, 0);
        if (rootNode.getTown().name.equals(goal.name)) {
            return rootNode;
        }

        LinkedList<PathNode> frontier = new LinkedList<>();
        frontier.add(rootNode);

        while (true) {
            if (frontier.isEmpty()) {
                return (null);
            }
            PathNode currentSN = frontier.get(0);
            frontier.remove(0);

            PuzzlePoints curTown = currentSN.getTown();
            if (curTown.name.equals(goal.name) && currentSN.getPathCost() == 20) {
                try {
                    BufferedWriter snode = new BufferedWriter(new FileWriter("node.txt"));
                    snode.write(currentSN.toString());
                    snode.close();
                    return currentSN;
                } catch (IOException e) {
                    System.out.print("An " + e + " error occured in the file");
                }
            }

            int childCount = curTown.countRoads();
            for (int chi = 0; chi < childCount; chi++) {
                PuzzlePoints child = curTown.getRoad(chi).town;

                PathNode newSN = new PathNode(child, currentSN,
                        currentSN.getPathCost() + curTown.getRoad(chi).distance);

                addFrontier(frontier, newSN);
            }
        }
    }
    private void addFrontier(LinkedList<PathNode> front, PathNode node) {

        if (checkDuplicate(node) == 1 || node.getPathCost() > 20) {
            return;
        } else {
            front.add(node);
        }
    }

    private int checkDuplicate(PathNode node) {

        names = node.toString().split("->");
        for (int i = 0; i < names.length - 1; i++) {
            for (int j = i + 1; j < names.length; j++) {
                if ((names[i].equals(names[j])) && (i != j)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public PuzzlePoints getTown(String nam) {
        return map.get(nam);
    }
    public void loadTownsFromFile(String fName) {
        try {
            Scanner inScan = new Scanner(new File(fName)).useDelimiter("[,\n]");
            while (inScan.hasNext()) {
                String name = inScan.next();
                map.put(name, new PuzzlePoints(name));
            }
        } catch (IOException ioe) {
            System.out.println("\nA IO EXception was thrown by: " + fName + "\n" + ioe);
            System.exit(0);
        }
    }

    private void loadRoadsFromFile(String fName) {
        try {
            Scanner inScan = new Scanner(new File(fName)).useDelimiter("[,\n]");
            while (inScan.hasNext()) {
                String twn1 = inScan.next();
                String twn2 = inScan.next();
                int dist1 = inScan.nextInt();
                int dist2 = inScan.nextInt();
                map.get(twn1).addRoad(dist1, map.get(twn2));
                map.get(twn2).addRoad(dist2, map.get(twn1));
            }
        } catch (IOException ioe) {
            System.out.println("\nA IO EXception was thrown by: " + fName + "\n" + ioe);
            System.exit(0);
        }
    }

    private void loadPositionFromFile(String position) {
        try {
            Scanner inScan = new Scanner(new File(position)).useDelimiter("[,\n]");
            positions = new String[5][5];
            points = new String[25];

            int row = 0;
            int counter = 0;
            while (inScan.hasNext()) {
                String[] estims = inScan.nextLine().trim().split(",");
                for (int col = 0; col < 5; col++) {
                    positions[row][col] = estims[col];
                    points[counter] = estims[col];
                    counter++;
                }
                row++;
            }
        } catch (IOException ioe) {
            System.out.println("\nA IO EXception was thrown by: " + position + "\n" + ioe);
            System.exit(0);
        }
    }
    public void getPositions(String[] arr) {
        int[] value = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            {
                for (int j = 0; j < 25; j++) {
                    if (arr[i].equals(points[j])) {
                        value[i] = j;
                    }
                }
            }
        }

        for (int i = 1; i < arr.length - 1; i++) {
            int row = 0;
            int col = 0;
            for (row = 0; row <= 4; row++) {
                for (col = 0; col <= 4; col++) {
                    if (positions[row][col].equals(arr[0])) {
                        index[row][col] = "Rb";
                    } else if (positions[row][col].equals(arr[arr.length - 1])) {
                        index[row][col] = "C";
                    } else if (col != 4 && positions[row][col].equals(arr[i]) && positions[row][col + 1].equals(arr[i + 1])) {
                        index[row][col] = "R";
                    } else if (col != 0 && positions[row][col].equals(arr[i]) && positions[row][col - 1].equals(arr[i + 1])) {
                        index[row][col] = "L";
                    } else if (row != 4 && positions[row][col].equals(arr[i]) && positions[row + 1][col].equals(arr[i + 1])) {
                        index[row][col] = "D";
                    } else if (row != 0 && positions[row][col].equals(arr[i]) && positions[row - 1][col].equals(arr[i + 1])) {
                        index[row][col] = "U";
                    }
                }
            }
        }

        try {
            FileWriter view = new FileWriter("view.txt");
            view.write("5\n");

            for (int row = 0; row <= 4; row++) {
                for (int col = 0; col <= 4; col++) {
                    view.write(index[row][col] + " ");
                }
                view.write("\n");
            }
            view.close();
        } catch (IOException e) {
            System.out.print(e);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("edge.txt"));
            FileWriter view = new FileWriter("try.txt");
            String[] factors = new String[]{"1", "2", "4", "5", "10"};

            Random rand = new Random();
            int move1 = rand.nextInt(5);
            int move2 = rand.nextInt(5);

            for (int row = 0; row < 40; row++) {
                String[] each = reader.readLine().split(",");
                for (int i = 0; i < 4; i++) {
                    if (Integer.parseInt(each[2]) < 0) {
                        each[2] = "-" + factors[move1];
                        each[3] = factors[move2];
                    } else {
                        each[2] = factors[move1];
                        each[3] = factors[move2];
                    }

                    if (i <= 2) {
                        view.write(each[i] + ",");
                    } else {
                        view.write(each[i]);
                    }
                }
                if (row <= 38) {
                    view.write("\n");
                } else {
                    view.write("");
                }
            }

            view.close();
        } catch (IOException e) {
            System.out.print(e);
        }
    }
}
