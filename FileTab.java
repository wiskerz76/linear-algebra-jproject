import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileTab extends ContentTab
{
    public FileTab(String path)
    {
        super();
         
        String data = "";
        try 
        {
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter("\n");
            
            while(scanner.hasNextLine())
            {
                data += scanner.next() + '\n';
            }
        } catch (FileNotFoundException e)
        {
            data = "File not found.";
            JOptionPane.showMessageDialog(
                null,
                "Missing or corrupted files. You may be using a pirated version of this software, which may provide a sub-par experience",
                "Missing Files",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (java.util.NoSuchElementException e)
        {
        }

        BoxLayout boxLayout = new BoxLayout(content, BoxLayout.Y_AXIS);
        content.setLayout(boxLayout);

        JTextArea text = new JTextArea(data, 100, 100);
        text.setLineWrap(true);
        JScrollPane pane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.add(pane);
    }
}