import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class NormalTextField extends JTextField {
    
    private class KbCallbackCaller implements KeyListener {

        private Consumer<KeyEvent> cb;

        public KbCallbackCaller(Consumer<KeyEvent> handler)
        {
         this.cb = handler;   
        }

        @Override
        public void keyReleased(KeyEvent e) 
        {
            this.cb.accept(e);
        }

        public void keyTyped(KeyEvent e) 
        {
        }

        public void keyPressed(KeyEvent e) 
        {

        }
    }
    
    /**
     * 
     * @param handler
     */
    public void setKeyHandler(Consumer<KeyEvent> handler)
    {
        KbCallbackCaller kbcc = new KbCallbackCaller(handler);
        this.addKeyListener(kbcc);
    }

    /**
     * Returns the value that is contained within the text field as a double, or throws an exception
     * @return
     */
    public double getValue()
    {
        String input = this.getText();
        String[] splitString = input.split("/", 0);
        if (splitString.length == 1)
        {
            return Double.parseDouble(input);
        } else if (splitString.length == 2)
        {
            return Double.parseDouble(splitString[0]) / Double.parseDouble(splitString[1]);
        } else 
        {
            throw new RuntimeException("Invalid input into matrix");
        }
    }
}
