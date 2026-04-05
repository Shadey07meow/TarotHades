import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Thread;


public class InputManager implements  KeyListener{
    // Detect inputs
    // If input is detected, make a variable here change
    class Vector2
    {
        public int x;
        public int y;
    }

    Vector2 moveVector = new Vector2();
    
    boolean movingUp;
    boolean movingDown;
    boolean movingLeft;
    boolean movingRight;

    public InputManager()
    {
        System.out.println("Hello");

    }

    @Override
    public void keyTyped(KeyEvent k)
    {
        
    }
    
    @Override
    public void keyPressed(KeyEvent k)
    {
        // Detects when a key is pressed down
        char inp = k.getKeyChar();

        
        switch (inp) {
            case ('a'):
            case ('A'):
                System.out.println("a");
                moveVector.x = -1;
                movingLeft = true; break;            
            case ('d'):
            case ('D'):
                System.out.println("d");
                moveVector.x = 1;
                movingRight = true; break;
            case ('w'):
            case ('W'):
                moveVector.y = 1;
                System.out.println("w");
                movingUp = true; break;
            case ('s'):
            case ('S'):
                moveVector.y = -1;
                System.out.println("s");
                movingDown = true; break;
                                            
        }
    }

    @Override
    public void keyReleased(KeyEvent k)
    {
        char inp = k.getKeyChar();
        switch (inp) {
            case ('a'):
            case ('A'):
                moveVector.x = 0;
                movingLeft = false; break;            
            case ('d'):
            case ('D'):
                moveVector.x = 0;
                movingRight = false; break;
            case ('w'):
            case ('W'):
                moveVector.y = 0;
                movingUp = false; break;
            case ('s'):
            case ('S'):
                moveVector.y = 0;
                movingDown = false; break;
            }
    }
    
    
    

    


}
