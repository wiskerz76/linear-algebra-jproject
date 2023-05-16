import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Project extends JFrame
{
    static final int WIDTH = 750;
    static final int HEIGHT = 500;

    public static void main(String[] args) 
    {
        new Project();
    }

    /* 
     public Project()
    {
        // Setup window
        setTitle("Linear Algebra Project");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel content = new JPanel(); // Panel to contain contents of each tab
        QuestionTab[] tabs = new QuestionTab[] {new InverseFinderTab(content), new DummyTab(content)};

        Container pane = getContentPane();
        TabSwitcher tabSwitcher = new TabSwitcher(tabs, new String[] {"Inverse Finder", "Dummy"}, () -> {pane.repaint();});

        pane.setLayout(new GridLayout(2, 1));
        pane.add(tabSwitcher.getComponent());
        pane.add(content);
        setVisible(true);
    }
    */
    
    public Project()
    {
        // Setup window
        setTitle("Linear Algebra Project");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel content = new JPanel(); // Panel to contain contents of each tab
        QuestionTab[] tabs = new QuestionTab[] {new InverseFinderTab(new JPanel()),new DummyTab(new JPanel())};

        Container pane = getContentPane();
        TabSwitcher tabSwitcher = new TabSwitcher(tabs, new String[] {"Inverse Finder", "Dummy"}, () -> {pane.revalidate();});

        pane.add(tabSwitcher.getComponent());
        setVisible(true);
    }
}