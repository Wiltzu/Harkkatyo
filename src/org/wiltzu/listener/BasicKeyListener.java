package org.wiltzu.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.wiltzu.util.Direction;

/**
 *<p>Class that listens arrow button clicks.</p>	
 *
 * @author Ville Ahti
 */
public class BasicKeyListener  implements KeyListener {
    
    private Direction dir = Direction.DOWN;

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_UP == e.getKeyCode()) 
            setDirection(Direction.UP);
        if(KeyEvent.VK_DOWN == e.getKeyCode()) 
            setDirection(Direction.DOWN);
        if(KeyEvent.VK_LEFT == e.getKeyCode()) 
            setDirection(Direction.LEFT);
        if(KeyEvent.VK_RIGHT == e.getKeyCode()) 
            setDirection(Direction.RIGHT);
    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    /**
     * <p>Getter for Last pressed direction.</p>
     * 
     * @return Last pressed direction.
     */
    public Direction getDirection() {
        return dir;
    }
    
    /**
     * <p>Setter for last pressed direction</p>
     * 
     * @param direction pressed last
     */
    private void setDirection(Direction direction) {
        dir = direction;
    }
    
}
