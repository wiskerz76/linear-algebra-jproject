import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MatrixInput
{
    JComponent component;

    JTextField[] fields;
    int m;
    int n;

    public MatrixInput(int m, int n)
    {
        this.m = m;
        this.n = n;
        component = new JPanel();
        component.setLayout(new GridLayout(m, n));

        fields = new JTextField[m * n];
        for (int i = 0; i < m * n; i++)
        {
            fields[i] = new JTextField();
            component.add(fields[i]);
        }
    }

    /*public Matrix getValue()
    {
        Matrix matrix = new Matrix(m, n);
        for (int i = 0; i < m * n; i++)
        {
            matrix.setValue(i, (double)fields[i].getValue());
        }

        return matrix;
    }*/
}