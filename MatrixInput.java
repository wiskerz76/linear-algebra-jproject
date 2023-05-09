import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MatrixInput
{
    JComponent component;

    NormalTextField[] fields;
    int m;
    int n;

    public MatrixInput(int m, int n)
    {
        this.m = m;
        this.n = n;
        component = new JPanel();
        component.setLayout(new GridLayout(m, n));

        fields = new NormalTextField[m * n];
        for (int i = 0; i < m * n; i++)
        {
            NormalTextField x = new NormalTextField();
            x.setText("0");
            fields[i] = x;
            x.setKeyHandler((KeyEvent e) -> {
                x.setText(x.getText().replaceAll("[a-zA-Z]", ""));
            });
            component.add(fields[i]);
        }
    }

    public Matrix getValue()
    {
        Matrix matrix = new Matrix(m, n);
        for (int i = 0; i < m * n; i++)
        {
            matrix.setValue(i, ((NormalTextField)fields[i]).getValue());
        }

        return matrix;
    }

    public JComponent getComponent()
    {
        return component;
    }

    public void clear()
    {
        for (int i = 0; i < fields.length; i++)
        {
            fields[i].setText("");
        }
    }
}