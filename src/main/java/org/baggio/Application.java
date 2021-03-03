package org.baggio;

import javax.swing.*;
import java.awt.*;

public class Application extends Canvas{

    public static void start(){
        JFrame frame = new JFrame("dasdasdasda");
        Panel panel = new Panel();

        Color[] colors = new Color[]{new Color(217, 255, 205),
                new Color(231, 249, 255), new Color(236, 255, 179), new Color(152, 231, 211)};


        JButton encrypt = new JButton("Encrypt");
        encrypt.setBounds(75, 380, 90,50);
        encrypt.setFont(new Font("Courier", Font.ITALIC, 12));

        JButton swap = new JButton("Swap");
        swap.setBounds(325, 380, 90, 50);
        swap.setFont(new Font("Courier", Font.ITALIC, 12));
        swap.setBackground(new Color(255, 129, 161));

        JButton clear = new JButton("Clear");
        clear.setBounds(200, 380, 90, 50);
        clear.setFont(new Font("Courier", Font.ITALIC, 12));


        JTextArea input = new JTextArea();
        input.setBounds(25, 130, 200, 200);
        input.setFont(new Font("Courier", Font.ITALIC, 12));
        input.setBackground(colors[1]);
        input.setLineWrap(true);
        input.setText("\n\n");

        JTextArea output = new JTextArea();
        output.setBounds(275, 130, 200, 200);
        output.setFont(new Font("Courier", Font.ITALIC, 12));
        output.setBackground(colors[2]);
        output.setLineWrap(true);

        Label label = new Label();
        label.setBounds(100, 30, 300, 50);
        label.setFont(new Font("Courier", Font.ITALIC, 16));
//        label.setText("Reveal && Conceal your letter!");

        HuffmanTree tree = constructTree();

        encrypt.addActionListener(e -> {
            String text = input.getText();
            if (encrypt.getText().equals("Encrypt")){
                output.setText(tree.encode(text));
            }else {
                output.setText(tree.decode(text));
            }

        });

        swap.addActionListener(e -> {
            input.setText(output.getText());
            output.setText("");
            if (encrypt.getText().equals("Encrypt")){
                encrypt.setText("Decrypt");
                input.setBackground(colors[(int) (Math.random()*colors.length)]);
            }else {
                encrypt.setText("Encrypt");
                output.setBackground(colors[(int) (Math.random()*colors.length)]);
            }
        });

        clear.addActionListener(e -> {
            input.setText("");
            output.setText("");
        });

        JScrollPane pane = new JScrollPane(output);

        panel.add(encrypt);
        panel.add(swap);
        panel.add(clear);
        panel.add(input);
        panel.add(output);
        panel.add(label);
        panel.add(pane);

        panel.setBackground(new Color(255, 129, 161, 255));

        panel.setSize(500, 500);
        panel.setLayout(null);
        panel.setVisible(true);

        frame.add(panel);
        frame.add(pane);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    private static HuffmanTree constructTree(){
        HuffmanTree tree = new HuffmanTree();
        tree.constructTree();
        return tree;
    }

}

