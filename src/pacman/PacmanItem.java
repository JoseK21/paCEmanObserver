package pacman;

/**
 * @author josenunez
 */
import java.awt.Color;
import java.util.Random;

public abstract class PacmanItem {

    protected static final Direction[] theDirections = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
    protected static final Random theGenerator = new Random();

    protected static final byte leftTransferX = 0;
    protected static final byte rightTransferX = 22;
    protected static final byte transferY = 11;

    protected final Point thePoint = new Point();

    protected final byte startX;
    protected final byte startY;

    protected Direction desiredDirection;
    protected Direction facingDirection;

    protected byte x;
    protected byte y;

    protected Color theColor;
    protected String name = "";

    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param theColor
     */
    public PacmanItem(final byte x, final byte y, final Color theColor) {
        this.x = x;
        this.y = y;
        this.theColor = theColor;
        this.name = getName();

        this.startX = x;
        this.startY = y;

        facingDirection = Direction.UP;
    }

    /**
     * Updates the direction and either the X or Y coordinate of the object
     * depending on the direction it is moving in
     * @param theD
     */
    public void move(Direction theD) {

        if (theD == null) {
            return;
        }
        switch (theD) {
            case UP:
                this.y--;
                facingDirection = Direction.UP;
                break;

            case DOWN:
                this.y++;
                facingDirection = Direction.DOWN;
                break;

            case LEFT:
                this.x--;
                facingDirection = Direction.LEFT;
                break;

            case RIGHT:
                this.x++;
                facingDirection = Direction.RIGHT;
                break;

            default:
                break;
        }

        if (y == transferY) {
            if (x == leftTransferX) {
                x = rightTransferX;
            } else if (x == rightTransferX) {
                x = leftTransferX;
            }
        }
    }

    /**
     * @param theDirection
     *
     * @return ProspectivePoint if the item were to move in that direction
     */
    public Point getProspectivePoint(final Direction theDirection) {
        switch (theDirection) {
            case UP:
                return new Point(x, y - 1);
            case DOWN:
                return new Point(x, y + 1);
            case LEFT:
                return new Point(x - 1, y);
            case RIGHT:
                return new Point(x + 1, y);
            default:
                return null;
        }
    }

    /**
     * @param currentLoc
     *
     * @return ProspectivePoint if the item were to move in that direction a
     * certain distance
     * @param theDirection
     * @param units
     */
    public Point getProspectivePoint(final Point currentLoc, final Direction theDirection, final byte units) {
        final byte x = currentLoc.getX();
        final byte y = currentLoc.getY();
        switch (theDirection) {
            case UP:
                return new Point(x, y - units);
            case DOWN:
                return new Point(x, y + units);
            case LEFT:
                return new Point(x - units, y);
            case RIGHT:
                return new Point(x + units, y);
            default:
                return null;
        }
    }

    /**
     * Returns a new Point from the given point and the direction
     *
     * @param theOriginal
     * @param theDirection
     * @return
     */
    public static Point getNewPoint(final Point theOriginal, final Direction theDirection) {
        if (theOriginal == null || theDirection == null) {
            return null;
        }

        switch (theDirection) {
            case UP:
                return new Point(theOriginal.getX(), theOriginal.getY() - 1);
            case DOWN:
                return new Point(theOriginal.getX(), theOriginal.getY() + 1);
            case LEFT:
                return new Point(theOriginal.getX() - 1, theOriginal.getY());
            case RIGHT:
                return new Point(theOriginal.getX() + 1, theOriginal.getY());
            default:
                return null;
        }
    }

    /**
     * Return the opposite direction
     *
     * @param theDirection
     * @return
     */
    public static Direction getOppositeDirection(final Direction theDirection) {
        if (null == theDirection) {
            return null;
        } else {
            switch (theDirection) {
                case RIGHT:
                    return Direction.LEFT;
                case LEFT:
                    return Direction.RIGHT;
                case UP:
                    return Direction.DOWN;
                case DOWN:
                    return Direction.UP;
                default:
                    return null;
            }
        }
    }

    /**
     * Returns random direction
     *
     * @return
     */
    public static Direction getRandomDirection() {
        return theDirections[theGenerator.nextInt(theDirections.length)];
    }

    /**
     * @param thePoint * @param new point
     */
    public void setPoint(final Point thePoint) {
        this.x = (byte) thePoint.getX();
        this.y = (byte) thePoint.getY();
    }

    /**
     * @return colorOfItem
     */
    public Color getColor() {
        return this.theColor;
    }

    /**
     * @param tC
     */
    public void setColor(Color tC) {
        this.theColor = tC;
    }

    /**
     * @return startingXPosition
     */
    public int getStartX() {
        return this.startX;
    }

    /**
     * @return startingYPosition
     */
    public int getStartY() {
        return this.startY;
    }

    /**
     * Returns the item to initial position by setting X and Y coordinates to
     * the ones first given in the constructor
     */
    public void returnToStartPosition() {
        this.x = this.startX;
        this.y = this.startY;
        this.facingDirection = Direction.UP;
    }

    /**
     * @return direction the item is facing
     */
    public Direction getFacingDirection() {
        return facingDirection;
    }

    /**
     * @param facing * @param direction currently facing
     */
    public void setFacingDirection(Direction facing) {
        this.facingDirection = facing;
    }

    /**
     * @param desired * @param direction it is trying to face
     */
    public void setDesiredDirection(Direction desired) {
        this.desiredDirection = desired;
    }

    /**
     * @return direction the item wants to go
     */
    public Direction getDesiredDirection() {
        return this.desiredDirection;
    }

    /**
     * Four possible directions to move in
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    /**
     * @return item x coordinate
     */
    public byte getX() {
        return this.x;
    }

    /**
     * @return item y coordinate
     */
    public byte getY() {
        return this.y;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.x = (byte) x;
    }

    /**
     * @param y
     * @param item's y coordinate
     */
    public void setY(int y) {
        this.y = (byte) y;
    }

    /**
     * @return Pointform of object's location
     */
    public Point getPoint() {
        return new Point(x, y);
    }

    /**
     * Sets the name of the item based on the color
     */
    private String getName() {
        if (theColor == Color.YELLOW) {
            return "Yellow";
        } else if (theColor == Color.CYAN) {
            return "Blue (Cyan)";
        } else if (theColor == Color.PINK) {
            return "Pink";
        } else if (theColor == Color.ORANGE) {
            return "Orange";
        } else if (theColor == Color.RED) {
            return "Red";
        }
        return "Error";
    }
}
