/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectai;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameDisplay extends JPanel {

    Puzzle puzzle;
    BufferedImage image;
    int cellSize = 80;
    int start_x = 40;
    int start_y = 40;
    int offset_x = 15;
    int offset_y = 35;
    Font numberFont = new Font("Arial", 1, 30);
    Filename name;
    JTextArea instruction;
    JButton path, reset;
    String left, right, up, down;

    public GameDisplay(Puzzle puz) {
        puzzle = puz;
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel Textpane = new JPanel();
        Textpane.setLayout(new FlowLayout());

        instruction = new JTextArea();
        instruction.setEditable(false);
        instruction.setPreferredSize(new Dimension(250, 150));
        Font myFont = new Font("Courier New", 1, 25);
        instruction.setFont(myFont);
        try {
            Scanner iScan = new Scanner(new File("try.txt"));
            String[] insts = iScan.nextLine().split(",");
            left = insts[3];
            right = insts[2];
            up = String.valueOf(Math.abs(Integer.parseInt(right)));
            down = left;
        } catch (Exception e) {
        }
        instruction.setText("Goal Score = 20\nLeft = "+left+"\nRight ="+right+"\nUp = "+up+"\nDown = "+down);
        Textpane.add(instruction);

        JPanel Buttonpane = new JPanel();
        Buttonpane.setLayout(new FlowLayout());

        path = new JButton("Find Path");
        path.setPreferredSize(new Dimension(100, 30));
        Buttonpane.add(path);

        reset = new JButton("Randomize");
        reset.setPreferredSize(new Dimension(100, 30));
        Buttonpane.add(reset);

        panel.add(Textpane, gbc);
        panel.add(Buttonpane, gbc);

        this.add(panel, BorderLayout.SOUTH);

        path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = new Filename("upArrow.png", "downArrow.png", "rightArrow.png", "leftArrow.png");
                revalidate();
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g2d) {
        super.paintComponent(g2d);

        g2d.setFont(numberFont);
        for (int row = 0; row < puzzle.getSize(); row++) {
            for (int col = 0; col < puzzle.getSize(); col++) {
                g2d.drawRect(start_x + col * cellSize, start_y + row * cellSize, cellSize, cellSize);

                if (!puzzle.getGridPosition(row, col).matches("0")) {

                    try {
                        switch (puzzle.getGridPosition(row, col)) {
                            case "Rb":
                                image = ImageIO.read(new File("rabbit.png"));
                                g2d.drawImage(image, start_x + col * cellSize + offset_x, 20 + row * cellSize + offset_y, 50, 50, this);
                                break;
                            case "C":
                                image = ImageIO.read(new File("carrot.png"));
                                g2d.drawImage(image, start_x + col * cellSize + offset_x, 20 + row * cellSize + offset_y, 50, 50, this);
                                break;
                            case "R":
                                image = ImageIO.read(new File(name.getRightImage()));
                                g2d.drawImage(image, start_x + col * cellSize + offset_x, 20 + row * cellSize + offset_y, 50, 50, this);
                                break;
                            case "U":
                                image = ImageIO.read(new File(name.getUpImage()));
                                g2d.drawImage(image, start_x + col * cellSize + offset_x, 20 + row * cellSize + offset_y, 50, 50, this);
                                break;
                            case "L":
                                image = ImageIO.read(new File(name.getLeftImage()));
                                g2d.drawImage(image, start_x + col * cellSize + offset_x, 20 + row * cellSize + offset_y, 50, 50, this);
                                break;
                            case "D":
                                image = ImageIO.read(new File(name.getDownImage()));
                                g2d.drawImage(image, start_x + col * cellSize + offset_x, 20 + row * cellSize + offset_y, 50, 50, this);
                                break;
                        }
                    } catch (Exception e) {
                        System.out.print("Error");
                    }
                }
            }
        }
        Graphics2D g2 = (Graphics2D) g2d.create();
        g2.setStroke(new BasicStroke(6));
    }
}
