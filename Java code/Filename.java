/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectai;

public class Filename {
    
    private String upImage;
    private String downImage;
    private String rightImage;
    private String leftImage;
    
    public Filename(String up, String down, String right, String left)
    {
        upImage = up;
        downImage = down;
        rightImage = right;
        leftImage = left;
    }

    /**
     * @return the upImage
     */
    public String getUpImage() {
        return upImage;
    }

    /**
     * @param upImage the upImage to set
     */
    public void setUpImage(String upImage) {
        this.upImage = upImage;
    }

    /**
     * @return the downImage
     */
    public String getDownImage() {
        return downImage;
    }

    /**
     * @param downImage the downImage to set
     */
    public void setDownImage(String downImage) {
        this.downImage = downImage;
    }

    /**
     * @return the rightImage
     */
    public String getRightImage() {
        return rightImage;
    }

    /**
     * @param rightImage the rightImage to set
     */
    public void setRightImage(String rightImage) {
        this.rightImage = rightImage;
    }

    /**
     * @return the leftImage
     */
    public String getLeftImage() {
        return leftImage;
    }

    /**
     * @param leftImage the leftImage to set
     */
    public void setLeftImage(String leftImage) {
        this.leftImage = leftImage;
    }
    
}
