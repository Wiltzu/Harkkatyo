package org.wiltzu.ui.component;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.wiltzu.ui.component.exception.CollisionException;
import org.wiltzu.ui.component.food.Food;
import org.wiltzu.util.Direction;

/**
 * <p>Class acts as a worm and consists of WormParts.</p>
 * 
 * @author Ville Ahti
 */
public class Worm implements Controlable, Eater {

	private List<Controlable> parts;
	private static final int PARTSIZE = 10;
	private Direction direction;
	private final int HEAD = 0;

	/**
	 * <p>Constructor for this class.</p>
	 * 
	 * @param direction initially
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Worm(Direction direction, int x, int y) {
		parts = new ArrayList<Controlable>();
		Controlable head = new WormPart(x, y, PARTSIZE, direction);
		parts.add(head);
	}

	/**
	 * <p>Resets worms status with given parameters.</p>
	 * 
	 * @param dir to initialize
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void reset(Direction dir, int x, int y) {
		parts.clear();
		Controlable head = new WormPart(x, y, PARTSIZE, direction);
		parts.add(head);
	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Controlable#getDirection()
	 */
	@Override
	public Direction getDirection() {
		return parts.get(HEAD).getDirection();
	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Controlable#setDirection(org.wiltzu.util.Direction)
	 */
	@Override
	public void setDirection(Direction dir) {
		Controlable head = parts.get(HEAD);
		int partListSize = parts.size();

		for (int i = 0; i < partListSize; i++) {
			Controlable part = parts.get(i);
			if (part == head) {
				part.setDirection(dir);
			} else {
				Direction partAhead = parts.get(i - 1).getLastDirection();
				part.setDirection(partAhead);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Controlable#update(javax.swing.JPanel)
	 */
	@Override
	public void update(JPanel container) throws CollisionException {
		int partListSize = parts.size();
		Controlable head = parts.get(HEAD);

		for (int i = 0; i < partListSize; i++) {
			Controlable part = parts.get(i);
			part.update(container);

			if (part != head) {
				if (part.getBounds().contains(head.getBounds())) {
					throw new CollisionException();
				}
			}

		}
	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Eater#feed(org.wiltzu.ui.component.food.Food)
	 */
	@Override
	public void feed(Food food) {
		if (food != null) {
			food.eat();
			createNewPart();
		}

	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Controlable#setX(int)
	 */
	@Override
	public void setX(int x) {
		parts.get(HEAD).setX(x);
	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Controlable#setY(int)
	 */
	@Override
	public void setY(int y) {
		parts.get(HEAD).setY(y);
	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		for (Drawable part : parts) {
			part.draw(g);
		}
	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Drawable#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		return parts.get(HEAD).getBounds();
	}

	/**
	 * <p>Creates new WormPart to the tail of the worm.</p>
	 */
	private void createNewPart() {
		int newX, newY;
		Direction newDir;
		if (!parts.isEmpty()) {
			int lastPart = parts.size() - 1;
			Rectangle lastPartsBounds = parts.get(lastPart).getLastBounds();
			newX = (int) lastPartsBounds.getX();
			newY = (int) lastPartsBounds.getY();
			newDir = parts.get(lastPart).getLastDirection();
			Controlable newPart = new WormPart(newX, newY, PARTSIZE, newDir);
			parts.add(newPart);
		}
	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Controlable#getLastBounds()
	 */
	@Override
	public Rectangle getLastBounds() {
		return parts.get(HEAD).getLastBounds();
	}

	/* (non-Javadoc)
	 * @see org.wiltzu.ui.component.Controlable#getLastDirection()
	 */
	@Override
	public Direction getLastDirection() {
		return parts.get(HEAD).getLastDirection();
	}
}
