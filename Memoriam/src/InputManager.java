import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;



public class InputManager implements  KeyListener{
    // Detect inputs
    // If input is detected, make a variable here change
    class Vector2
    {
        public int x;
        public int y;
    }

    Vector2 moveVector = new Vector2();
    
    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;

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

        if(inp == 'a' || inp == 'A')
        {
            this.movingLeft = true; 
        }
        if(inp == 'd' || inp == 'D')
        {
            this.movingRight = true;
        }
        if(inp == 'w' || inp == 'W')
        {
            this.movingUp = true;
        }
        if(inp == 's' || inp == 'S')
        {
            this.movingDown = true;            
        }
        updMovement();
    }

    @Override
    public void keyReleased(KeyEvent k)
    {
        char inp = k.getKeyChar();

        if(inp == 'a' || inp == 'A')
        {
            this.movingLeft = false; 
        }
        if(inp == 'd' || inp == 'D')
        {
            this.movingRight = false;
        }
        if(inp == 'w' || inp == 'W')
        {
            this.movingUp = false;
        }
        if(inp == 's' || inp == 'S')
        {
            this.movingDown = false;            
        }
        updMovement();
    }
    
    
    
    public void updMovement()
    {

            
        // Handles Y
        if(movingDown && movingUp)
        {
            System.out.println("Hellothere");
            moveVector.y = 0;
        } else if(movingUp)
        {
            moveVector.y = 1;
        }else if (movingDown)
        {
            moveVector.y = -1;
        } else
        {
            moveVector.y = 0;
        }

        // Handles X
        if(movingLeft && movingRight)
        {
            moveVector.x = 0;
        } else if(movingRight)
        {
            moveVector.x = 1;
        }else if (movingLeft)
        {
            moveVector.x = -1;
        } else
        {
            moveVector.x = 0;
        }
        
        
    }

    


}
