/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectai;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow  extends JFrame {

    int win_wid = 570;
    int win_hei = 770;

    GameDisplay display;
    Puzzle puzzle;
    
    public GameWindow(String fileName)
    {
//        this.setTitle("Sudoku puzzle from file: " + fileName);
        this.setSize(win_wid, win_hei);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(new BorderLayout());
        
        puzzle = new Puzzle(fileName);
        display = new GameDisplay(puzzle);
        
        display.reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                String start = String.valueOf((char) (r.nextInt(26) + 'A'));
                String goal = String.valueOf((char) (r.nextInt(26) + 'A'));

//                PathFinder myGraph = new PathFinder("small town", "citie_1.txt", "try.txt", "position1.txt");

//                String[] pathArray = myGraph.GetSolution(start, goal).split("->");
//                myGraph.getPositions(pathArray);                
//                
                dispose();
                new GameWindow("view.txt");
            }
        });
        
        content.add(display);        
        this.add(content);
        this.setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        GameWindow game = new GameWindow("view.txt");
        
        PathFinder myGraph = new PathFinder("small town", "cities_1.txt", "edges_1.txt", "position.txt");
        
        String start = "A";
        String goal = "H";

        String[] pathArray = myGraph.GetSolution(start, goal).split("->");
        myGraph.getPositions(pathArray);
    }
    
}
