import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InverseFinderTab extends QuestionTab
{
    Matrix originalMatrix;
    MatrixInput input;
    JLabel result; 
    NormalButton next;
    MatrixDisplay display;
    static Random random;
    int difficulty = 3;

    public InverseFinderTab(JPanel content)
    {
        super(content);
        component.setLayout(new GridLayout(3, 5));

        component.add(new JLabel("Find the inverse of this matrix: "));

        // Setup matrix to invert
        random = new Random();
        generateProblem();

        NormalButton submit = new NormalButton("Submit");
        submit.setKeyHandler((ActionEvent e) -> {
            testSuccess();
        });
        component.add(submit);

        next = new NormalButton("Next");
        next.setKeyHandler((ActionEvent e) -> {
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
        if (display != null)
            component.remove(display.getComponent()); 

        int size;
        if (difficulty <= 10)
        {
            size = 2;
        }  
        else if (difficulty <= 20)
        {
            size = random.nextInt(3) + 1;
        }  
        else 
        {
            size = random.nextInt(3) + 1;
        }
        originalMatrix = Matrix.randomNonSingular(size, difficulty);
        display = new MatrixDisplay(size, size);
        display.setValue(originalMatrix);

        component.add(display.getComponent(), 1);

        if (input != null)
            component.remove(input.getComponent());
        input = new MatrixInput(size, size);
        component.add(input.getComponent(), 2);
    }

    public void testSuccess()
    {

        Matrix product = input.getValue().multiply(originalMatrix);
        System.out.println(product);
        if (Matrix.roughlyEqual(product, Matrix.identity(product.n)))
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