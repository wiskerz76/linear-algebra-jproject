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

    Matrix originalMatrix;
    MatrixInput input;
    JLabel result; 
    NormalButton next;
    MatrixDisplay display;
    Container pane;
    static Random random;

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

        pane = getContentPane();
        pane.setLayout(new GridLayout(5, 5));

        pane.add(new JLabel("Find the inverse of this matrix: "));

        // Setup matrix to invert
        random = new Random();
        generateProblem();

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
        if (display != null)
            pane.remove(display.getComponent()); 

        int size = random.nextInt(3) + 2;
        originalMatrix = Matrix.randomNonSingular(size);
        display = new MatrixDisplay(size, size);
        display.setValue(originalMatrix);

        pane.add(display.getComponent(), 1);

        if (input != null)
            pane.remove(input.getComponent());
        input = new MatrixInput(size, size);
        pane.add(input.getComponent(), 2);
    }

    public void testSuccess()
    {

        Matrix product = input.getValue().multiply(originalMatrix);
        System.out.println(product);
        if (Matrix.roughlyEqual(product, Matrix.identity(product.n)))
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