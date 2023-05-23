import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * This is the main class for the project. It initializes the window, loads in all the tabs, etc.
 */
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
        setTitle("Linear Algebra JPractice");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel content = new JPanel(); // Panel to contain contents of each tab
        ContentTab[] tabs = new ContentTab[] {
            new InverseFinderTab(),
            new MultiplicationTab(), // TODO: Refactor the code to not take in this panel and create it internally in the Question Tab class
            new RankTab(),
            new PracticeQuizTab(),
            new FileTab("info.txt"),
            new FileTab("LICENSE")
        };

        String[] names = new String[]{
            "Inverse Practice",
            "Multiplication Practice",
            "Rank Practice",
            "Practice Quiz",
            "Info",
            "License"
        };
        Container pane = getContentPane();
        TabSwitcher tabSwitcher = new TabSwitcher(tabs, names); 

        pane.add(tabSwitcher.getComponent());
        setVisible(true);
    }
}