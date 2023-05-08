import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
/*import Matrix.*;
import MatrixDisplay.*;
import MatrixInput.*;*/

public class Project extends JFrame
{
    static final int WIDTH = 750;
    static final int HEIGHT = 500;

    public static void main(String[] args) 
    {
        new Project();
        Matrix m = new Matrix(3, 3);
        for (int i = 0; i < 3 * 3; i++)
        {
            m.setValue(i, (double)i + 10.0);
        }
        System.out.println(m.toString());
        System.out.printf("Determinant: %f", m.determinant());
    }

    public Project()
    {
        setTitle("Linear Algebra Project");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(5, 5));

        MatrixInput matrix = new MatrixInput(3, 5);
        pane.add(matrix.component);

        MatrixDisplay display = new MatrixDisplay(3, 3);
        display.setValue(Matrix.identity(3).transpose());
        pane.add(display.component);

        pane.add(new JLabel("test"));
        pane.add(new JLabel("test"));
        pane.add(new JLabel("test"));
        pane.add(new JLabel("test"));
        pane.add(new JLabel("test"));
        pane.add(new JLabel("test"));
        pane.add(new JLabel("test"));
        setVisible(true);
    }
}