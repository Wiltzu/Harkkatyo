package org.wiltzu.ui.component;

import java.awt.Color;
import java.awt.Graphics;

import org.wiltzu.util.Direction;

/**
 * <p></p>
 * 
 * @author Ville Ahti
 *
 */
public class ColoredWorm extends Worm {
	
	private Color color;

	/**
	 * <p></p>
	 * 
	 * @param direction
	 * @param x
	 * @param y
	 * @param color
	 */
	public ColoredWorm(Direction direction, int x, int y, Color color) {
		super(direction, x, y);
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Worm#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		Color defaultColor = g.getColor();
		g.setColor(color);
		super.draw(g);
		g.setColor(defaultColor);
	}
	
	

}
