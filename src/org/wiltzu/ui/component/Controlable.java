/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiltzu.ui.component;

import javax.swing.JPanel;
import org.wiltzu.util.Direction;

/**
 *
 * @author Ville
 */
public interface Controlable extends Moveable{

    Direction getDirection();
    
    Direction getLastDirection();

    void setDirection(Direction dir);
    
    void update(JPanel container) throws CollisionException;
    
}
