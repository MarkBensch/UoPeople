package edu.uopeople.cs1102;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloWorld {
    public static void main(String[] args) {
        int N = 1;
        while (N <= 32) {
            N = 2 * N;
            System.out.print(N+", ");
        }
    }
}
class A extends JFrame implements ActionListener {
    JButton jbtCancel = new JButton("Cancel");
    JButton jbtok = new JButton("OK");

    public A() {
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(jbtCancel);
        jbtCancel.addActionListener(this);
        getContentPane().add(jbtok);
        jbtok.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtCancel) {
            System.out.println("Cancel button is clicked");
        }
        if (e.getSource() == jbtok) {
            System.out.println("OK button is clicked");
        }
    }
}
