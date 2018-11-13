package pacman;

/**
 * @author josenunez
 */
import java.awt.Color;

/** 
 * Represents the Pacman 
 */
public class ThePacman extends PacmanItem {
  public ThePacman(byte x, byte y, Color theColor) {
    super(x, y, theColor);
  }
  
  @Override
  public String toString() { 
    return "Pacman. X: " + this.x + "\tY: " + this.y;
  }
}