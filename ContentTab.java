import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ContentTab
{
    public JComponent component;

    public JPanel content;

    public ContentTab()
    {
        this.component = new JPanel();
        this.content = new JPanel();
    }

    public void show()
    {
        this.content.removeAll();
        this.content.add(this.component, BorderLayout.CENTER);
        this.content.repaint();
        //this.component.setSize(100, 100);
        this.component.repaint();
        System.out.println(this.component);
    }
}