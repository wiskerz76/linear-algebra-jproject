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
        }
        current = tabs[0];
        setCurrent(0);
    }

    public void setCurrent(int contentIx)
    {
        current.component.setVisible(false);
        tabs[contentIx].component.setVisible(true);
        current = tabs[contentIx];

        //tabs[contentIx].component.setVisible(false);
        /*
        System.out.println(contentIx);
        component.remove(contentIx+1);
        component.repaint();
        */


        //component.remove(current.component);
        /*current = tabs[contentIx];
        component.add(current.component,1);
        //component.add(tabs[contentIx].component);
        //component.remove(1);
        System.out.println("ADDED");

        refresh.run();
        */
    }

    /*
    public TabSwitcher(QuestionTab[] tabs, String[] names, Runnable refresh)
    {
        this.tabs = tabs;
        this.component = new JPanel();
        this.refresh = refresh;
        buttons = new NormalButton[tabs.length];
        for (int i = 0; i < tabs.length; i++)
        {
            final int k = i;
            buttons[i] = new NormalButton(names[i]);
            buttons[i].setKeyHandler((ActionEvent e) -> {
                setCurrent(k);
                System.out.println(k);
            });
            component.add(buttons[i]);
        }
        setCurrent(0);
    }
    
    public void setCurrent(int i)
    {
        this.current = this.tabs[i];
        this.current.show();
        this.refresh.run();
    }
    */
}