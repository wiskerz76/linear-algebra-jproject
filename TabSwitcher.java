import java.awt.*;

import javax.naming.ContextNotEmptyException;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.Runnable;


public class TabSwitcher
{
    private ContentTab[] tabs;
    private JTabbedPane component;

    public JTabbedPane getComponent()
    {
        return component;
    }

    public TabSwitcher(ContentTab[] tabs, String[] names)
    {
        this.tabs = tabs;
        //this.component = new JPanel();
        this.component = new JTabbedPane();        
        
        for(int i = 0; i < tabs.length; i++)
        {
            component.addTab(names[i], tabs[i].component);
        }
    }
}