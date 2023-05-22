import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * A question tab that allows users to practice finding inverses
 */
public class InverseFinderTab extends QuestionTab
{
    Matrix originalMatrix;
    MatrixInput input;
    JLabel result; 
    NormalButton next;
    MatrixDisplay display;
    static Random random;
    int difficulty = 3;
    public InverseFinderTab()
    {
        super();
        component.setLayout(new GridLayout(3, 5));

        component.add(new JLabel("Find the inverse of this matrix: "));

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

    /**
     * Create a new invertible matrix and set it up to be displayed to the user
     */
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

        //We get a non-singular matrix so that M^-1 exists
        originalMatrix = Matrix.randomNonSingular(size, difficulty);
        display = new MatrixDisplay(size, size);
        display.setValue(originalMatrix);

        component.add(display.getComponent(), 1);

        if (input != null)
            component.remove(input.getComponent());
        input = new MatrixInput(size, size);
        component.add(input.getComponent(), 2);
    }

    /**
     * Determines if the user has computed the inverse correctly.
     */
    public void testSuccess()
    {

        Matrix product = input.getValue().multiply(originalMatrix);
        System.out.println(product);

        /*
         * We use roughlyEqual to prevent floating point silliness. 
         * This gives the user the benefit of the doubt,
         * but it's pretty unlikely they'll make a mistake and still get within +- 0.1%
         */
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