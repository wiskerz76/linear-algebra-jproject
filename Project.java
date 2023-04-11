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
    JComponent component;

    JSpinner[] fields;
    int m;
    int n;

    public MatrixInput(int m, int n)
    {
        this.m = m;
        this.n = n;
        component = new JPanel();
        component.setLayout(new GridLayout(m, n));

        fields = new JSpinner[m * n];
        for (int i = 0; i < m * n; i++)
        {
            fields[i] = new JSpinner();
            component.add(fields[i]);
        }
    }

    public Matrix<Double> getValue()
    {
        Matrix<Double> matrix = new Matrix<Double>(m, n);
        for (int i = 0; i < m * n; i++)
        {
            matrix.setValue(i, (double)fields[i].getValue());
        }

        return matrix;
    }
}

class Matrix<T extends Number>
{
    List<T> values;
    int m;
    int n;

    public Matrix(int m, int n)
    {
        values = new ArrayList<T>(m * n);
    }

    public T getValue(int i, int j)
    {
        return values.get(i * n + j);
    }

    public T getValue(int idx)
    {
        return values.get(idx);
    }

    public void setValue(int i, int j, T value)
    {
        values.set(i * n + j, value);
    }

    public void setValue(int idx, T value)
    {
        values.set(idx, value);
    }

    @Override
    public String toString()
    {
        String s = "";
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                s += getValue(i, j) + " ";
            }
            s += "\n";
        }
        return s;
    }
}