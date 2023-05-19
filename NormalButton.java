import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

/**
 * Provides an improved interface to the JButton class that implements a more modern callback based interface
 */
public class NormalButton extends JButton {
    
    /**
     * This class is used to implement the callback
     */
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
    
    /**
     * Sets a given function to run when the button is clicked
     * @param handler A function ActionEvent -> (); Consumer<ActionEvent>
     */
    public void setClickHandler(Consumer<ActionEvent> handler)
    {
        KbCallbackCaller kbcc = new KbCallbackCaller(handler);
        this.addActionListener(kbcc);
    }
}
