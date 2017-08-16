package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class DigitJTextField extends JTextField{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7909825121011269835L;

	public DigitJTextField(){
	        addKeyListener(new KeyAdapter() {
	            public void keyTyped(KeyEvent e) {
	                char ch = e.getKeyChar();

	                if (!isNumber(ch) && ch != '\b') {
	                    e.consume();
	                }
	            }
	        });

	    }

	    private boolean isNumber(char ch){
	        return ch >= '0' && ch <= '9';
	    }

}
