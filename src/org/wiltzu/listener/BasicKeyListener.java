/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiltzu.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.wiltzu.util.Direction;

/**
 *
 * @author Ville
 */
public class BasicKeyListener  implements KeyListener {
    
    private Direction dir = Direction.DOWN;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

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

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    public Direction getDirection() {
        return dir;
    }
    
    private void setDirection(Direction direction) {
        dir = direction;
    }
    
}
