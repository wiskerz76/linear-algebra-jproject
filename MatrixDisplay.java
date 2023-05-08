import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MatrixDisplay
{
    JComponent component;

    JLabel[] elements;
    int m;
    int n;

    public MatrixDisplay(int m, int n)
    {
        this.m = m;
        this.n = n;
        component = new JPanel();
        component.setLayout(new GridLayout(m, n));

        elements = new JLabel[m * n];
        for (int i = 0; i < m * n; i++)
        {
            elements[i] = new JLabel("", SwingConstants.CENTER);
            component.add(elements[i]);
        }
    }

    public void setValue(Matrix m)
    {
        for (int i = 0; i < elements.length; i++)
        {
            elements[i].setText(m.getValue(i).toString());
        }
    }
}