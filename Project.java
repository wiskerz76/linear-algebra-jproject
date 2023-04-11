import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Project extends JFrame
{
    static final int WIDTH = 750;
    static final int HEIGHT = 500;

    public static void main(String[] args) 
    {
        new Project();
    }

    public Project()
    {
        setTitle("Linear Algebra Project");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container pane = getContentPane();
        MatrixInput matrix = new MatrixInput(3, 5);
        pane.add(matrix.component);
        setVisible(true);
    }
}

class MatrixInput
{
    public JComponent component;

    JTextField[][] fields;

    public MatrixInput(int m, int n)
    {
        component = new JPanel();
        component.setLayout(new GridLayout(m, n));
        fields = new JTextField[m][];
        for (int i = 0; i < m; i++)
        {
            fields[i] = new JTextField[n];
            for (int j = 0; j < n; j++)
            {
                fields[i][j] = new JTextField("test");
                component.add(fields[i][j]);
            }
        }
    }
}