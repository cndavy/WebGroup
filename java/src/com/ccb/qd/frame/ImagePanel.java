package com.ccb.qd.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by han on 2015/7/10.
 */
public  class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel() {

    }
    public void setImage(BufferedImage image){
        this.image=image;
    }
    public ImagePanel(BufferedImage b) {

        image = b;

    }
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

}