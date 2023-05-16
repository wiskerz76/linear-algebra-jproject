import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class NormalButton extends JButton {
    
    private class KbCallbackCaller implements ActionListener {

        private Consumer<ActionEvent> cb;

        public KbCallbackCaller(Consumer<ActionEvent> handler)
        {
         this.cb = handler;   
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            this.cb.accept(e);
        }
    }

    public NormalButton(String s)
    {
        super(s);
    }
    

    public void setKeyHandler(Consumer<ActionEvent> handler)
    {
        KbCallbackCaller kbcc = new KbCallbackCaller(handler);
        this.addActionListener(kbcc);
    }

    public int getValue()
    {
        return Integer.parseInt(this.getText());
    }
}
