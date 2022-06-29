package com.dale.login.buttons;

import java.awt.*;

import javax.swing.JButton;

public class RoundButton extends JButton {
	Color color;

	   public RoundButton(String string) {
	      super(string);
	      decorate();
	      color =new Color(61,205,91);
	   }
	   public RoundButton(String string,Color color) {
	      super(string);
	      decorate();
	      this.color = color;
	   }
	   
	   public RoundButton(String string,Color color, boolean hasBorderLine) {
		      super(string);
		      
		      this.color = color;
		   }
	   
	   public void setColor(String string,Color color) {
		     this.setText(string);
		      decorate();
		      this.color = color;
	   }
	   
	   protected void decorate() {
	        setOpaque(false);
	        
	    }
	   
	   protected void paintComponent(Graphics g) {
	       int width = getWidth();
	       int height = getHeight();
	       this.setBackground(color);

	       Graphics2D graphics = (Graphics2D) g;

	       graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	       if (getModel().isArmed()) {
	           graphics.setColor(getBackground().darker());
	       } else {
	           graphics.setColor(getBackground());
	       }

	       graphics.fillRoundRect(0, 0, width, height, 10, 10);

	       FontMetrics fontMetrics = graphics.getFontMetrics();
	       Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

	       int textX = (width - stringBounds.width) / 2;
	       int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();

	       graphics.setColor(getForeground());
	       graphics.setFont(getFont());
	       graphics.drawString(getText(), textX, textY);
	       graphics.dispose();

	       super.paintComponent(g);
	   }

}
