package systems;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener{
    // Detect inputs
    // If input is detected, make a variable here change


    // Variables
    private Vector2 moveVector = new Vector2();
    private Vector2 clickPosition = new Vector2();
    private Vector2 mousePosition = new Vector2();
    
    
    private boolean mousePressed = false;
    private boolean mouseClicked = false;
    private boolean pausePressed = false;
    
    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;

    private boolean isInteracting;


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
        if(inp == 'e' || inp == 'E')
        {
            this.isInteracting = true;
        }

// for esc button
        if(k.getKeyCode() == KeyEvent.VK_ESCAPE){
            pausePressed = true;
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


        if(inp == 'e' || inp == 'E')
        {
            this.isInteracting = false;
        }

        updMovement();
    }

    @Override
    public void keyTyped(KeyEvent k){}

    /// Mouse listener methods
    @Override
    public void mousePressed(MouseEvent m)
    {
        if(m.getButton() == MouseEvent.BUTTON1)
        {
           
            mousePressed = true;
            mouseClicked = true;

            this.clickPosition.x = m.getX();
            this.clickPosition.y = m.getY();

            System.out.println("CLICK DETECTED: " + m.getX() + "," + m.getY());
            
        }
    }

    @Override
    public void mouseReleased(MouseEvent m)
    {
        if(m.getButton() == MouseEvent.BUTTON1)
        {
              mousePressed = false;
            
        }
    }


    @Override
    public void mouseClicked(MouseEvent m) {}
    
    @Override
    public void mouseEntered(MouseEvent m)
    {}
    
    @Override
    public void mouseExited(MouseEvent m)
    {}
    
    /// MOUSE MOTION
    @Override
    public void mouseMoved(MouseEvent m) {
        mousePosition.x = m.getX();
        mousePosition.y = m.getY();
    }

    @Override
    public void mouseDragged(MouseEvent m) {
        mousePosition.x = m.getX();
        mousePosition.y = m.getY();
    }
    
    
    // Input manager methods
    private void updMovement()
    {
        // Handles Y
        if(movingDown && movingUp){
            this.moveVector.y = 0;
        } else if(movingUp){
            this.moveVector.y = 1;
        } else if (movingDown){
            this.moveVector.y = -1;
        } else {
            this.moveVector.y = 0;
        }

        // Handles X
        if(movingLeft && movingRight){
            this.moveVector.x = 0;
        } else if(movingRight){
            this.moveVector.x = 1;
        } else if (movingLeft){
            this.moveVector.x = -1;
        } else{
            this.moveVector.x = 0;
        }
    }

    

    public double getInputX() {return this.moveVector.x;}
    public double getInputY() {return this.moveVector.y;}
   
   public Vector2 getInputVector() {return  this.moveVector;}
    
    public Vector2 getClickPosition() {return clickPosition;}
    public Vector2 getMousePosition() {return mousePosition;}
    public boolean getIsInteracting(){return this.isInteracting;}

    public boolean isPausePressed() {
        boolean temp = pausePressed;
        pausePressed = false;
        return temp;
    }

    public boolean consumeClick() {
        boolean temp = mouseClicked;
        mouseClicked = false;
        return temp;
    }

    


}


