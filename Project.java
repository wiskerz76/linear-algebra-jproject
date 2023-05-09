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

        pane.add(new JLabel("Find the inverse of this matrix: "));

        MatrixDisplay display = new MatrixDisplay(3, 3);
        originalMatrix = Matrix.randomNonSingular(3);
        display.setValue(originalMatrix);
        pane.add(display.getComponent());
        System.out.println(originalMatrix);

        input = new MatrixInput(3, 3);
        pane.add(input.getComponent());

        NormalButton btn = new NormalButton("Submit");
        btn.setKeyHandler((ActionEvent e) -> {
            System.out.println("click btn");
            testSuccess();
        });
        pane.add(btn);

        result = new JLabel();
        pane.add(result);

        setVisible(true);
    }

    public void testSuccess()
    {
        Matrix product = input.getValue().multiply(originalMatrix);
        System.out.println(product);
        if (Matrix.roughlyEqual(product, Matrix.identity(3)))
        {
            result.setText("Correct!");
        }
        else 
        {
            result.setText("Incorrect, try again");
        }
    }
}