import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiplicationTab extends QuestionTab
{
    Matrix matrix1;
    Matrix matrix2;

    MatrixInput input;
    JLabel result; 
    NormalButton next;

    MatrixDisplay display1;
    MatrixDisplay display2;
    static Random random;
    int difficulty = 3;

    public  MultiplicationTab()
    {
        super(new JPanel());
        component.setLayout(new GridLayout(4, 5));

        component.add(new JLabel("Multiply Matrices: "));

        // Setup matrix to invert
        random = new Random();
        generateProblem();

        NormalButton submit = new NormalButton("Submit");
        submit.setClickHandler((ActionEvent e) -> {
            testSuccess();
        });
        component.add(submit);

        next = new NormalButton("Next");
        next.setClickHandler((ActionEvent e) -> {
            generateProblem();
            input.clear();
            next.setVisible(false);
        });
        next.setVisible(false);

        result = new JLabel();
        component.add(result);
        component.add(next);
    }

    public void generateProblem()
    {
        if (display1 != null)
        {
            component.remove(display1.getComponent()); 
            component.remove(display2.getComponent()); 
        }

        int outer1;
        int outer2;
        int inner;
        if (difficulty <= 10)
        {
            outer1 = 2;
            outer2 = 2;
            inner = 2;
        }  
        else if (difficulty <= 20)
        {
            outer1 = random.nextInt(3) + 1;
            outer2 = random.nextInt(3) + 1;
            inner = random.nextInt(3) + 1;
        }  
        else 
        {
            outer1 = random.nextInt(4) + 1;
            outer2 = random.nextInt(4) + 1;
            inner = random.nextInt(4) + 1;
        }
        matrix1 = Matrix.random(outer1, inner);
        matrix2 = Matrix.random(inner, outer2);

        display1 = new MatrixDisplay(outer1, inner);
        display1.setValue(matrix1);

        display2 = new MatrixDisplay(inner, outer2);
        display2.setValue(matrix2);

        component.add(display1.getComponent(), 1);
        component.add(new JLabel("X"), 2);
        component.add(display2.getComponent(), 3);

        if (input != null)
            component.remove(input.getComponent());

        input = new MatrixInput(outer1, outer2);

        component.add(input.getComponent(), 4);
    }

    public void testSuccess()
    {
        Matrix product = matrix1.multiply(matrix2);
        if (Matrix.roughlyEqual(product, input.getValue()))
        {
            result.setText("Correct!");
            difficulty += 2;
            next.setVisible(true);
        }
        else 
        {
            result.setText("Incorrect, try again");
        }
    }
}