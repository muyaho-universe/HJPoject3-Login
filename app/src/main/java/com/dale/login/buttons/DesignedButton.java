package com.dale.login.buttons;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class DesignedButton extends JButton {
	
	public DesignedButton(String name){
		super(name);
		decorate();
		this.setSize(100, 100);
	}
	
	protected void decorate() { 
    	setBorderPainted(true);
    	setOpaque(false);
    }
	
	@Override 
    protected void paintComponent(Graphics g) {
		Color c=new Color(0,255,255); //배경색 결정
		Color o=new Color(0,0,0); //글자색 결정
//			setMargin(new Insets(50, 50, 50, 50));
		int width = getWidth(); 
		int height = getHeight(); 
		
		Graphics2D graphics = (Graphics2D) g; 
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		
		if (getModel().isArmed()) { 
			graphics.setColor(c.darker()); 
		} 
		else if (getModel().isRollover()) { 
			graphics.setColor(c.brighter()); 
		} 
		else { 
			graphics.setColor(c); 
		} 
		
		graphics.fillRoundRect(0, 0, width, height, 100, 100);
		
		FontMetrics fontMetrics = graphics.getFontMetrics(); 
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
		
		int textX = (width - stringBounds.width) / 2; 
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
		
		graphics.setBackground(o);
		graphics.setColor(o); 
		graphics.setFont(getFont()); 
		graphics.drawString(getText(), textX, textY); 
		graphics.dispose(); 
	 	super.paintComponent(g); 
    }
}
