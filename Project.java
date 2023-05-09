import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Project extends JFrame
{
    static final int WIDTH = 750;
    static final int HEIGHT = 500;

    public Matrix originalMatrix;
    public MatrixInput input;
    public JLabel result; 
    public NormalButton next;
    public MatrixDisplay display;

    public static void main(String[] args) 
    {
        new Project();
    }

    public Project()
    {
        // Setup window
        setTitle("Linear Algebra Project");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(5, 5));

        pane.add(new JLabel("Find the inverse of this matrix: "));

        // Setup matrix to invert
        display = new MatrixDisplay(3, 3);
        pane.add(display.getComponent());
        generateProblem();

        input = new MatrixInput(3, 3);
        pane.add(input.getComponent());

        NormalButton submit = new NormalButton("Submit");
        submit.setKeyHandler((ActionEvent e) -> {
            testSuccess();
        });
        pane.add(submit);

        next = new NormalButton("Next");
        next.setKeyHandler((ActionEvent e) -> {
            generateProblem();
            input.clear();
            next.setVisible(false);
        });
        next.setVisible(false);

        result = new JLabel();
        pane.add(result);
        pane.add(next);

        setVisible(true);
    }

    public void generateProblem()
    {
        originalMatrix = Matrix.randomNonSingular(3);
        display.setValue(originalMatrix);
    }

    public void testSuccess()
    {
        Matrix product = input.getValue().multiply(originalMatrix);
        System.out.println(product);
        if (Matrix.roughlyEqual(product, Matrix.identity(3)))
        {
            result.setText("Correct!");
            next.setVisible(true);
        }
        else 
        {
            result.setText("Incorrect, try again");
        }
    }
}