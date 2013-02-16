package org.wiltzu.util;

/**
 *<p>Enum class that represents directions: left, right, up and down.</p>
 *
 * @author Ville Ahti
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;
    
    /**
     * <p>Getter for an opposite of some direction</p>
     * 
     * @param direction which opposite we want
     * @return opposite of given parameter
     */
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
