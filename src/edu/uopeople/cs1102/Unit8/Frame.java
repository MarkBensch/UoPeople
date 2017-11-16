package edu.uopeople.cs1102.Unit8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by MarkTurnTo on 3/17/17.
 */
public class Frame {
    int windowHeight = 300;
    int windowWidth = 300;
    double desktopWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    double desktopHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    int centerWidth = ((int)desktopWidth - windowWidth)/2;
    int centerHeight = ((int)desktopHeight - windowHeight)/2;

    public Frame(){
        createFrame();
    }

    private void createFrame(){
        JFrame window = new JFrame("Learning Journal Program");
        window.setContentPane(createContentPane());
        window.setSize(windowWidth, windowHeight);
        window.setLocation(centerWidth,centerHeight);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }

    private Container createContentPane() {
        JPanel shape = new ShapeDraw();
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(shape, BorderLayout.CENTER);
        content.add(new paintName("Mark J. Bensch"), BorderLayout.SOUTH);

        return content;
    }

}

class ShapeDraw extends JPanel{
    ArrayList<ShapeObject> shapes = new ArrayList<>();
    ShapeDraw(){
        for(int i =0; i<6;++i)
            shapes.add(new ShapeObject( randomShape(),randomNum(),randomNum(), (int)(Math.random()*225)));

        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                for(int i = 0; i < shapes.size(); ++i ) {
                    if(shapes.get(i).getTransparent() == 255) {
                        shapes.remove(shapes.get(i));
                        shapes.add(new ShapeObject( randomShape(),randomNum(),randomNum(), (int)(Math.random()*225)));
                    } else {
                        shapes.get(i).upTransparancy(1);
                    }
                }

                ShapeDraw.this.repaint();
            }
        });
        timer.start();
    }
    public int randomNum(){
        return (int)(Math.random()*300) - 100;
    }
    public int randomShape(){
        return (int)(Math.random()*4);
    }

    @Override
    public void paintComponent(Graphics g) {
        for(ShapeObject shape : shapes){
            shape.setGraphics(g);
            shape.setShape(shape.shape);
        }
    }

}

class ShapeObject {
    int red, blue, green,x,y;
    int transparent = 100;
    Graphics graphic;
    int shape;

    public ShapeObject( int shape, int x, int y, int trans){
        this.red = (int)(Math.random()*255);
        this.blue = (int)(Math.random()*255);
        this.green = (int)(Math.random()*255);
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.transparent = trans;
    }


    public void setGraphics(Graphics g){
        this.graphic = g;
    }
    public void upTransparancy(int increase){
        if (transparent + increase > 255)
            transparent = 255;
        else
            transparent += increase;
    }
    public int getTransparent(){
        return transparent;
    }
    public void setShape(int shape){
        graphic.setColor(this.randomColor());
        switch(shape){
            case 0:
                graphic.fillOval(x,y,100,100);
                break;
            case 1:
                graphic.fillRect(x,y,100,100);
                break;
            case 2:
                graphic.fill3DRect(x,y,100,100,true);
                break;
            case 3:
                graphic.fillArc(x,y,100,100, 60, 60);
                break;
            default:
                System.out.println(shape);
                break;
        }
    }
    public Color randomColor(){
        return new Color(red,blue,green,getTransparent());
    }

}

class paintName extends JLabel{
    String[] strs = new String[2];
    int outOfNames = 0;

    public paintName(String name){
        super("", JLabel.CENTER);
        strs[0] = "Hello, my name is " + name;
        strs[1] = "tchika tchika Slim Shady";  //peer pressure and money convinced me to have the label change because why not
        setText(strs[0]);
        setForeground(Color.BLACK);
        setFont(new Font("Serif", Font.BOLD, 18));
        setOpaque(true);

        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(outOfNames % 2 == 0) {
                    setText(strs[0]);
                    outOfNames++;
                } else {
                    setText(strs[1]);
                    outOfNames--;
                }
            }
         });
         timer.start();
    }
}
