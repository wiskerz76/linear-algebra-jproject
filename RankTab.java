import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RankTab extends QuestionTab
{
    Matrix matrix;

    JTextField input;
    JLabel result; 
    NormalButton next;

    MatrixDisplay display;
    static Random random;
    int difficulty = 3;
    int rank;

    public RankTab()
    {
        super();
        component.setLayout(new GridLayout(0, 2));

        component.add(new JLabel("Find the rank of the matrix", SwingConstants.CENTER));

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
            input.setText("");
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
        {
            component.remove(display.getComponent()); 
        }

        int n;
        int m;
        if (difficulty <= 10)
        {
            n = 2;
            m = 2;
        }  
        else if (difficulty <= 20)
        {
            n = random.nextInt(3) + 1;
            m = random.nextInt(3) + 1;
        }  
        else 
        {
            n = random.nextInt(4) + 1;
            m = random.nextInt(4) + 1;
        }

        //rank = 1;
        //matrix = new Matrix(n,m)
        
        rank = 1;
        matrix = Matrix.randomMatrixOfRank(n, m, rank); // BEN PLEASE FIX THIS THING


        display = new MatrixDisplay(n, m);
        /*System.out.println(n);
        System.out.println(m);
        System.out.println(matrix);*/
        display.setValue(matrix);

        component.add(display.getComponent(), 1);

        if (input != null)
            component.remove(input);

        input = new JTextField();

        component.add(input, 2);
    }

    public void testSuccess()
    {
        boolean right = false;
        try 
        {
            int guess = Integer.parseInt(input.getText());
            if (guess == rank)
            {
                result.setText("Correct!");
                difficulty += 2;
                next.setVisible(true);
                right = true;
            }
        } 
        finally
        {
            if (!right)
                result.setText("Incorrect, try again");
        }
    }
}