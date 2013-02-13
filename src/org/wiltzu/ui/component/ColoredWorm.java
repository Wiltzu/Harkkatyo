package org.wiltzu.ui.component;

import java.awt.Color;
import java.awt.Graphics;

import org.wiltzu.util.Direction;

public class ColoredWorm extends Worm {
	
	private Color color;

	public ColoredWorm(Direction direction, int x, int y, Color color) {
		super(direction, x, y);
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		Color defaultColor = g.getColor();
		g.setColor(color);
		super.draw(g);
		g.setColor(defaultColor);
	}
	
	

}
