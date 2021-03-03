package org.baggio;

import javax.swing.*;
import java.awt.*;

public class Demo extends Canvas {

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.repaint();
        demo.repaint(100, 100, 100,100);
        JFrame frame = new JFrame();
        frame.add(demo);
        frame.setSize(400,400);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.getImage("love.png");
        g.drawImage(image, 250, 200, this);
    }
}
