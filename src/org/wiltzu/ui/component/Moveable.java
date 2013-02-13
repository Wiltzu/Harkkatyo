/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiltzu.ui.component;

import java.awt.Rectangle;
import javax.swing.JPanel;
import org.wiltzu.util.Direction;

/**
 *
 * @author Ville
 */
public interface Moveable extends Drawable {

    void setX(int x);

    void setY(int y);
    
    Rectangle getLastBounds();
    
}
