/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiltzu.ui.component;

import org.wiltzu.ui.component.exception.CollisionException;
import org.wiltzu.ui.component.food.Food;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.wiltzu.util.Direction;

/**
 *
 * @author Ville
 */
public class Worm implements Controlable, Eater {

    private List<Controlable> parts;
    private static final int PARTSIZE = 10;
    private Direction direction;
    private final int HEAD = 0;

    public Worm(Direction direction, int x, int y) {
        parts = new ArrayList<Controlable>();
        Controlable head = new WormPart(x, y, PARTSIZE, direction);
        parts.add(head);
    }
    
    public void reset(Direction dir, int x, int y) {
    	parts.clear();
    	Controlable head = new WormPart(x, y, PARTSIZE, direction);
        parts.add(head);
    }

    @Override
    public Direction getDirection() {
        return parts.get(HEAD).getDirection();
    }

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

    @Override
    public void feed(Food food) {
        if (food != null) {
            food.eat();
            createNewPart();
        }

    }

    @Override
    public void setX(int x) {
        parts.get(HEAD).setX(x);
    }

    @Override
    public void setY(int y) {
        parts.get(HEAD).setY(y);
    }

    @Override
    public void draw(Graphics g) {
        for (Drawable part : parts) {
            part.draw(g);
        }
    }

    @Override
    public Rectangle getBounds() {
        return parts.get(HEAD).getBounds();
    }

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

    @Override
    public Rectangle getLastBounds() {
        return parts.get(HEAD).getLastBounds();
    }

    @Override
    public Direction getLastDirection() {
        return parts.get(HEAD).getLastDirection();
    }
}
