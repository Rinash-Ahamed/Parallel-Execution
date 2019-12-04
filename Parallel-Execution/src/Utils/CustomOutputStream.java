package Utils;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
 
/**
 * This class extends from OutputStream to redirect output to a JTextArrea
 * @author Ron
 *
 */
public class CustomOutputStream extends OutputStream {
    private JTextArea textArea;
    
    @SuppressWarnings("unused")
	private JTextPane textPane;
     
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    public CustomOutputStream(JTextPane textPane) {
		this.textPane = textPane;
	}

	@Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
