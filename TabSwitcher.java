import java.awt.*;

import javax.naming.ContextNotEmptyException;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.Runnable;


public class TabSwitcher
{
    private QuestionTab[] tabs;
    private NormalButton[] buttons;
    private JPanel buttonBar;
    private QuestionTab current;
    private JPanel component; 
    private Runnable refresh;

    public JPanel getComponent()
    {
        return component;
    }

    public TabSwitcher(QuestionTab[] tabs, String[] names, Runnable refresh)
    {
        this.tabs = tabs;
        this.buttons = new NormalButton[tabs.length];
        this.buttonBar = new JPanel();
        this.component = new JPanel();
        this.refresh = refresh;
        
        buttonBar.setLayout(new GridLayout(1, tabs.length));
        component.setLayout(new GridLayout(1+tabs.length, 1));

        component.add(buttonBar,0);
        for(int i = 0; i < tabs.length; i++)
        {
            final int k = i;
            buttons[i] = new NormalButton(names[i]);
            buttons[i].setKeyHandler((ActionEvent e) -> {
                setCurrent(k);
            });
            buttonBar.add(buttons[i]);
            component.add(tabs[i].component,i+1);
            tabs[i].component.setVisible(false);
            tabs[i].component.setSize(1, 1);
            tabs[i].component.repaint();
        }
        current = tabs[0];
        setCurrent(0);
    }

    public void setCurrent(int contentIx)
    {
        current.component.setVisible(false);
        current.component.setSize(1,1);
        current = tabs[contentIx];
        current.component.setVisible(true);
        current.component.setSize(480,320);
    }

}