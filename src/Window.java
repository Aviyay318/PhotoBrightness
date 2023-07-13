import javax.swing.*;

public class Window extends JFrame {
    public Window(){
        this.setSize(800,800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.add(new Panel(0,0,800,800));
        this.setVisible(true);
    }
}
