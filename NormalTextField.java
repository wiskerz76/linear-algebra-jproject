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
    
    
    public void setKeyHandler(Consumer<KeyEvent> handler)
    {
        KbCallbackCaller kbcc = new KbCallbackCaller(handler);
        this.addKeyListener(kbcc);
    }
}
