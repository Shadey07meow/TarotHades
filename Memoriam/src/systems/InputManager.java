package systems;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;



public class InputManager implements KeyListener, MouseListener{
    // Detect inputs
    // If input is detected, make a variable here change


    // Variables
    private Vector2 moveVector = new Vector2();
    private Vector2 clickPosition = new Vector2();
    private boolean mouse1down;
    
    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;



    public InputManager()
    {
        System.out.println("Hello");

    }
    
    /// Keylistener methods
    
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

    @Override
    public void keyTyped(KeyEvent k){}

    /// Mouse listener methods
    @Override
    public void mouseClicked(MouseEvent m)
    {
        if(m.getButton() == MouseEvent.BUTTON1)
        {
            setClicking(true);
            System.out.println("You clicked");
            this.clickPosition.x = m.getLocationOnScreen().x;
            this.clickPosition.y = m.getLocationOnScreen().y;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent m)
    {}

    @Override
    public void mouseReleased(MouseEvent m)
    {
        if(m.getButton() == MouseEvent.BUTTON1)
        {
            setClicking(false);
            this.clickPosition.x = 0;
            this.clickPosition.y = 0;
        }
    }

    @Override
    public void mouseEntered(MouseEvent m)
    {}
    
    @Override
    public void mouseExited(MouseEvent m)
    {}
    

    // Input manager methods
    private void updMovement()
    {
        // Handles Y
        if(movingDown && movingUp)
        {
            this.moveVector.y = 0;
        } else if(movingUp)
        {
            this.moveVector.y = 1;
        }else if (movingDown)
        {
            this.moveVector.y = -1;
        } else
        {
            this.moveVector.y = 0;
        }

        // Handles X
        if(movingLeft && movingRight)
        {
            this.moveVector.x = 0;
        } else if(movingRight)
        {
            this.moveVector.x = 1;
        }else if (movingLeft)
        {
            this.moveVector.x = -1;
        } else
        {
            this.moveVector.x = 0;
        }
    }

    private void setClicking(boolean v){this.mouse1down = v;}

    public int getInputX() {return this.moveVector.x;}
    public int getInputY() {return this.moveVector.y;}
    public Vector2 getInputVector() {return  this.moveVector;}
    public boolean getClickingStatus() {return this.mouse1down;}


}
