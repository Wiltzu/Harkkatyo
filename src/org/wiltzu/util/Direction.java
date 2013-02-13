/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiltzu.util;

/**
 *
 * @author Ville
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;
    
    public static Direction getOpposite(Direction direction){
        Direction oppDir = UP;
        switch(direction) {
            case UP:
                oppDir = DOWN;
                break;
            case DOWN:
                oppDir = UP;
                break;
            case LEFT:
                oppDir = RIGHT;
                break;
            case RIGHT:
                oppDir = LEFT;
                break;
        }
        return oppDir;
    } 
}
