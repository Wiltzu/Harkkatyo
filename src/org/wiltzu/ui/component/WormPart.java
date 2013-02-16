package org.wiltzu.ui.component;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import org.wiltzu.ui.component.exception.CollisionException;
import org.wiltzu.util.Direction;

/**
 * <p>Class that acts as part of the worm.</p>
 *
 * @author Ville Ahti
 */
public class WormPart implements Controlable {

    private int x, y, size;
    private Direction direction, lastDirection;
    private Rectangle bounds, lastBounds;

    /**
     * <p>Constructor for this class</p>
     * 
     * @param x_pos x coordinate
     * @param y_pos y coordinate
     * @param size of part
     * @param direction initially
     */
    public WormPart(int x_pos, int y_pos, int size, Direction direction) {
        this.x = x_pos;
        this.y = y_pos;
        this.size = size;
        this.bounds = new Rectangle(x_pos, y_pos, size, size);
        this.lastBounds = new Rectangle(size, size);
        this.direction = direction;
        //this.lastDirection = this.getDirection();
    }

    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Controlable#setX(int)
     */
    @Override
    public void setX(int x) {
        this.x = x;
        bounds.setLocation(x, y);
    }

    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Controlable#setY(int)
     */
    @Override
    public void setY(int y) {
        this.y = y;
        bounds.setLocation(x,y);
    }

    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Drawable#getBounds()
     */
    @Override
    public Rectangle getBounds() {
        return bounds;
    }
    
   
    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Controlable#getLastBounds()
     */
    @Override
    public Rectangle getLastBounds() {
        return lastBounds;
    }
    
    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Controlable#getDirection()
     */
    @Override
    public Direction getDirection() {
        return direction;
    }
    
    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Controlable#update(javax.swing.JPanel)
     */
    @Override
    public void update(JPanel container) throws CollisionException {
        Rectangle containerBounds = container.getBounds();
        
        lastBounds.setLocation(x, y); //old position
        if(getDirection() == Direction.DOWN)
            y+=size;
        else if(getDirection() == Direction.UP) 
            y-=size;
        else if(getDirection() == Direction.LEFT) 
            x-=size;
        else if(getDirection() == Direction.RIGHT)
            x+=size;
        bounds.setLocation(x, y); //new position
        
        boolean contains = containerBounds.contains(this.getBounds());
        if(!contains) {
            throw new CollisionException();  
        }
        
    }
    
    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Drawable#draw(java.awt.Graphics)
     */
    @Override
    public void draw(Graphics g) {
       g.drawOval(x, y, size, size);
       g.fillOval(x, y, size, size);
    }
    
    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Controlable#setDirection(org.wiltzu.util.Direction)
     */
    @Override
    public void setDirection(Direction dir) {
        lastDirection = getDirection(); //setting last Direction in memory
        if(Direction.getOpposite(dir) != lastDirection) {
            switch(dir) {
                case UP:
                    direction = Direction.UP;
                    break;
                case DOWN:
                    direction = Direction.DOWN;
                    break;
                case LEFT:
                    direction = Direction.LEFT;
                    break;
                case RIGHT:
                    direction = Direction.RIGHT;
                    break;
            }                
        }
    }

    /* (non-Javadoc)
     * @see org.wiltzu.ui.component.Controlable#getLastDirection()
     */
    @Override
    public Direction getLastDirection() {
        return lastDirection;
    }
    
    
    
    
    
}
