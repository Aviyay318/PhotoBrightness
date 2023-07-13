import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;

public class Panel extends JPanel {
    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage image;
    private JButton mirrorButton;
    private JButton incrementBrightness;
    private JButton decrementBrightness;
    private boolean isClicked;
    private BufferedImage temp;
    public Panel (int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.isClicked=false;
        this.setBounds(this.x, this.y, this.width,this.height);
        this.setLayout(null);
        addImage();
        setMirrorButton();
        setIncrementBrightness();
        setDecrementBrightness();
        this.temp = this.image;
    }

    private void addImage(){
        try{
            this.image= ImageIO.read(new File("src/IMG_4756.jpg") );
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void setMirrorButton(){
        this.mirrorButton=new JButton("Mirror");
        this.mirrorButton.setBounds(0,10,100,100);
        this.add(this.mirrorButton);
        this.mirrorButton.setVisible(true);
        this.mirrorButton.addActionListener(e -> {
            this.image=reverseImage();
            repaint();
        });
    }
    private void setIncrementBrightness(){
        this.incrementBrightness=new JButton("Increment");
        this.incrementBrightness.setBounds(0,110,100,100);
        this.add(this.incrementBrightness);
        this.incrementBrightness.setVisible(true);
        this.incrementBrightness.addActionListener(e -> {
        changeBrightness(this.image);
        this.isClicked=true;
        repaint();
        });
    }
    private void setDecrementBrightness(){
        this.decrementBrightness=new JButton("Decrement");
        this.decrementBrightness.setBounds(0,210,100,100);
        this.add(this.decrementBrightness);
        this.decrementBrightness.setVisible(true);
        this.decrementBrightness.addActionListener(e -> {
        changeBrightness(this.image);
        this.isClicked=false;
        repaint();
        });
    }

    private BufferedImage reverseImage(){
        BufferedImage reversed=new BufferedImage(this.image.getWidth(),this.image.getHeight(),this.image.getType());
        for (int i=0; i <this.image.getHeight()-1;i++){
            for (int j = this.image.getWidth()-1,w=0; j > 0;j--,w++){
                reversed.setRGB(w,i, this.image.getRGB(j,i));
            }
        }
        return reversed;
    }

    private BufferedImage changeBrightness(BufferedImage original){

        int red=0;
        int green=0;
        int blue=0;
        BufferedImage newImage=this.image;
        for (int i =0 ; i < this.image.getHeight()-1;i++){
            for(int j = 0 ; j <this.image.getWidth()-1;j++){
                int color= this.image.getRGB(j,i);
                Color color1= new Color(color);
                red=color1.getRed();
                green=color1.getGreen();
                blue=color1.getBlue();
                if (this.isClicked){
                red=Math.min(255,red+3);
                green=Math.min(255,green +3);
                blue=Math.min(255,blue+3);
                }
                else {
                    red=Math.max(0,red-3);
                    green=Math.max(0,green-3);
                    blue=Math.max(0,blue-3);

                }
                Color newColor = new Color(red,green,blue);
                newImage.setRGB(j,i,newColor.getRGB());
            }
        }
        return newImage;
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(this.image,0,0,800,800,null);
    }
}
