import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyTab extends ContentTab
{

    public DummyTab()
    {
        super();
        component.setLayout(new GridLayout(5, 5));

        component.add(new JLabel("Welcome to the dummy tab"));
    }
}