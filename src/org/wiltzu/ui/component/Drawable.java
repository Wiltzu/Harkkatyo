/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiltzu.ui.component;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Ville
 */
public interface Drawable {

    void draw(Graphics g);

    Rectangle getBounds();
    
}
