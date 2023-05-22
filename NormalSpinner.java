import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

/**
 * A numeric input field. This extends the JSpinner class with a callback-style interface
 */
public class NormalSpinner extends JSpinner
{
    private class ChangeCallbackCaller implements ChangeListener
    {
        private Consumer<ChangeEvent> cb;
        public ChangeCallbackCaller(Consumer<ChangeEvent> handler)
        {
            this.cb = handler;
        }
        public void stateChanged(ChangeEvent e)
        {
            cb.accept(e);
        }
    }

    /**
     * Sets the function that runs when the value is changed
     * @param handler callback handler
     */
    public void setChangeHandler(Consumer<ChangeEvent> handler)
    {
        this.addChangeListener(new ChangeCallbackCaller(handler));
    }
}
