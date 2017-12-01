package com.github.yannismarkou.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author yannis
 */
public class TextOverlay extends JPanel {

    private BufferedImage image;

    public TextOverlay(String imageURL, String text) throws IOException {
        try {
            image = ImageIO.read(new File(imageURL));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = process(image, text);
        File outputfile = new File(changeFilename(imageURL));

        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        bi.getGraphics().drawImage(image, 0, 0, null);
        ImageIO.write(image, "PNG", outputfile);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    private BufferedImage process(BufferedImage old, String text) {
        int w = old.getWidth() / 3;
        int h = old.getHeight() / 3;
        BufferedImage img = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, w, h, this);
        g2d.setPaint(Color.red);
        g2d.setFont(new Font("Serif", Font.BOLD, 50));
        FontMetrics fm = g2d.getFontMetrics();
        int x = img.getWidth() / 2 - fm.stringWidth(text);
//        int x = img.getWidth() - fm.stringWidth(s) - 5;
        int y = fm.getHeight();
        g2d.drawString(text, 50, y);
        g2d.dispose();
        return img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public static void create(String imageURL, String text) throws IOException {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new TextOverlay(imageURL, text));
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            String imageURL = "sdgf";
            String text = "";

            @Override
            public void run() {
                try {
                    create(imageURL, text);
                } catch (IOException ex) {
                    Logger.getLogger(TextOverlay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public String changeFilename(String oldURL) {
        return oldURL.split("\\.")[0] + "TRANLSATED." + oldURL.split("\\.")[1];
    }
}
