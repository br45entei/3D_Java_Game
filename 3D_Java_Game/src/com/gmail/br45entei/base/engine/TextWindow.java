/**
 * 
 */
package com.gmail.br45entei.base.engine;

//import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**@author Brian_Entei */
public class TextWindow extends JFrame {
	/**@see Serializable */private static final long serialVersionUID = -5151041547543472432L;
	private final JLabel textLabel;

	public TextWindow() {
		this.textLabel = createTextLabel();
		this.setSize(this.getWidth() / 12, this.getHeight() / 12);
	}

	public JLabel getTextLabel() {
		return this.textLabel;
	}

	public boolean hasTextLabel() {
		return this.textLabel != null;
	}

	public void setText(String str) {
		this.textLabel.setText("<html>" + str.replaceAll("\\n", "<br>") + "</html>");
	}

	private JLabel createTextLabel() {
		//this.getContentPane().setLayout(new FlowLayout());
		JLabel rtrn = new JLabel("Text-Only Label");
		rtrn.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.getContentPane().add(rtrn);
		
		rtrn.setText("<html>The quick brown fox<br>jumps over the lazy dog.</html>");
		return rtrn;
	}
	
}
