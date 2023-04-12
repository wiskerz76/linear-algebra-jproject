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

    public Matrix getValue()
    {
        Matrix matrix = new Matrix(m, n);
        for (int i = 0; i < m * n; i++)
        {
            matrix.setValue(i, (double)fields[i].getValue());
        }

        return matrix;
    }
}

class Matrix
{
    Double[] values;
    int m;
    int n;

    public Matrix(int m, int n)
    {
        this.m = m;
        this.n = n;
        values = new Double[m * n];
    }

    public Double getValue(int i, int j)
    {
        return values[i + j * n];
    }

    public Double getValue(int idx)
    {
        return values[idx];
    }

    public void setValue(int i, int j, Double value)
    {
        values[i + j * n] = value;
    }

    public void setValue(int idx, Double value)
    {
        values[idx] = value;
    }

    public Matrix getMinor(int i, int j)
    {
        if (m < 2 || n < 2)
            throw new IllegalStateException("This matrix does not have a minor");
        Matrix minor = new Matrix(m - 1, n - 1);
        int idx = 0;
        for (int k = 0; k < m; k++)
        {
            if (k == i)
                continue;
            for (int l = 0; l < n; l++)
            {
                if (l == j)
                    continue;
                minor.setValue(idx, this.getValue(k, l));
                idx++;
            }
        }
        
        return minor;
    }

    public Double determinant()
    {
        if (n != m)
            throw new IllegalStateException("Cannot get the determinant of non-square matrices");

        if (n == 2)
            return getValue(0) * getValue(3) - getValue(1) * getValue(2);

        int sign = 1;
        Double sum = 0d;
        for (int i = 0; i < n; i++)
        {
            sum += getValue(i) * sign * getMinor(i, 0).determinant();
            sign *= -1;
        }
        return sum;
    }

    @Override
    public String toString()
    {
        String s = "";
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                s += getValue(i, j).toString() + " ";
            }
            s += "\n";
        }
        return s;
    }
}