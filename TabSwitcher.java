import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.lang.Runnable;


public class TabSwitcher
{
    QuestionTab[] tabs;
    NormalButton[] buttons;
    QuestionTab current;
    JPanel component;
    Runnable refresh;

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
}