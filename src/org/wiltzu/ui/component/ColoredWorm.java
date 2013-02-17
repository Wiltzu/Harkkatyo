package org.wiltzu.ui.component;

import java.awt.Color;
import java.awt.Graphics;

import org.wiltzu.util.Direction;

/**
 * <p>ColoredWorm is a specialization of the worm that is colored.</p>
 * 
 * @author Ville Ahti
 *
 */
public class ColoredWorm extends Worm {
	
	private Color color;

	/**
	 * <p>Constructor for this class.</p>
	 * 
	 * @param direction initially
	 * @param x	coordinate
	 * @param y coordinate
	 * @param color of the worm
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
