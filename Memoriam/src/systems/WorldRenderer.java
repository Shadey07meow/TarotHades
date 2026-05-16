package systems;

import object.*;
import scenes.*;
import java.util.ArrayList;

import collision.RectangleCollider;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;

public class WorldRenderer {

    // Class containing all the information about the objects in it
    // Needs player class in order to determine position changes
    // Also needs a class in order to render the map first always, ensuring it'll always appear last
    private Player player;
    private MapObj map;
    private PlayableScreen gamePanel;

    // Object list MUST include player
    private ArrayList<GameObject> objectList = new ArrayList<GameObject>();
    private Vector2 centerPosition = new Vector2(950, 420);
    private int distanceFromCenter = 20 * 4 ;
    private final double xThresholdd = 20; 
    private final double yThresholdd = 20; 
    private final double xMargin = 30;
    private final double yMargin = 30;

    private boolean debugMode = false;

    // Constructors
    public WorldRenderer(Player player, PlayableScreen s)
    {
        this.player = player;
        this.map = new MapObj(null, player.getPosition(), 1, s);
        this.gamePanel = s;

        initWorld();

    }

    public WorldRenderer(Player player, MapObj  m,  PlayableScreen s)
    {
        this.player = player;
        this.map = m;
        this.gamePanel = s;

        initWorld();
    }

    public WorldRenderer(PlayableScreen s)
    {
        this.gamePanel = s;
        this.player = null;
        this.map = null;
    }

    private void initWorld()
    {
        this.setMap(this.map);
        this.setPlayer(this.player);
        initiateCamera();
    }

    // Add game object to the game world
    public void addObject(GameObject obj)
    {
        // Checks if there is a map
        if(this.objectList.get(0) == null)
        {
            //System.out.println("There is no map yet, cannot add objects");
            return;
        }

        if(obj == (GameObject)this.player || obj == (GameObject)this.map )
        {
            System.out.println("There can only be one instance of this object in this class");
            return;    
        }

        this.objectList.add(1, obj);
        //System.out.println("Added an object to the scene");
    }


    // Needs a center point, is already set in the beginning of the java file
    // If distance of the player from the center point is greater than some amount, then move the world instead of the player
    public boolean checkPlayerDistanceFromCenter()
    {
        return (Vector2.distance(this.centerPosition, this.player.getPosition()) > distanceFromCenter);
    }

    // Update world position
    public void updateWorld()
    {

        // Update player first
        if(this.player != null)
        {
            for (int x = 0; x < this.objectList.size(); x++)
            {  
                this.objectList.get(x).update();
            } 

            // This is the world clamping logic
            

            boolean willMoveRight = (this.player.getPosition().x > this.centerPosition.x + xThresholdd) &&  (this.player.getVelocity().x > 0);
            boolean willMoveLeft = (this.player.getPosition().x < this.centerPosition.x - xThresholdd) &&  (this.player.getVelocity().x < 0);
            
            boolean willMoveUp = (this.player.getPosition().y > this.centerPosition.y + yThresholdd) &&  (this.player.getVelocity().y < 0);
            boolean willMoveDown = (this.player.getPosition().y < this.centerPosition.y - yThresholdd) &&  (this.player.getVelocity().y > 0);
            if(willMoveRight|| willMoveLeft)
            {
                // Runs if the player is outside the bounding box of left and right 
                moveObjectsWithWorldX();
            }

            if( willMoveDown || willMoveUp)
            {
                // Runs if the player is outside the bounding box of left and right 
                moveObjectsWithWorldY();
            }


                
        }
    }

    public void moveObjectsWithWorldY()
    {
        for (GameObject obj : objectList)
        {   
        
            // Move objects
            // Move everything horizontally
            if(!atMapBorderY())
            {
                obj.move(0, -player.getVelocity().y);    
            } 
            obj.interpolate(1);
        }
    }


    public void moveObjectsWithWorldX()
    {
        for (GameObject obj : objectList)
        {   
            if(!atMapBorderX())
            {
                obj.move(-player.getVelocity().x, 0);    
            } 
            obj.interpolate(1);
        }
    }

    // Method to auto close the worldrenderer
    public void closeWorld()
    {
        for(GameObject obj : this.objectList)
        {
            this.objectList.remove(obj);
        }

        this.player = null;
        this.map = null;

    }

    // Setters getters
    public ArrayList<GameObject> getObjectList()
    {
        return  this.objectList;
    }

    // Sets the last object of the list to be the player
    public void setPlayer(Player p)
    {
        this.player = p;
        p.setWorldRenderer(this);

        // Checks if there is a map
        if(this.objectList.get(0) == null)
        {
            System.out.println("There is no map yet, cannot add objects");
            return;
        }


        if(objectList.size() == 1)
        {
            objectList.add(1, p);    
        }  else if (objectList.size() > 1)
        {
            objectList.add(objectList.size() - 1, p);
        }
    }

    // Sets the first object of the world into a map
    public void setMap(MapObj m)
    {
        this.map = m;
        this.objectList.add(0, m);
        System.out.println("Set map scene");
    }

    public boolean getDebug()
    {
        return this.debugMode;
    }
    
    public Vector2 getCenterPosition()
    {
        return this.centerPosition;
    }    

    public void setCenterPosition(Vector2 v)
    {
        this.centerPosition = v;
    }    

    public Player getPlayer()
    {
        return this.player;
    }

    public int getDistanceFromCenter()
    {
        return this.distanceFromCenter;
    }
    
    public boolean atMapBorderY()
    {
        boolean b = false;
        
        // Checks whether the top of the map is at the edge of the top of the screen
        if(player.getVelocity().y < 0)
        {
            if((int)map.getPosition().y + (int)(map.getScaledHeight() / 2) < gamePanel.getHeight() + yMargin) 
            {
                b = true;
            }
        } 
        else if(player.getVelocity().y > 0)
        {
            if((int)map.getPosition().y - (int)(map.getScaledHeight() / 2)  > -yMargin) 
            {
                b = true;
            }
        }
 


        return b;
    }

    public boolean atMapBorderX()
    {
        boolean b = false;
        
        if(player.getVelocity().x < 0)
        {
            if((int)map.getPosition().x - (int)(map.getScaledWidth() / 2) > -xMargin) 
            {
                b = true;
            }
        }
        else if(player.getVelocity().x > 0)
        {
            if((int)map.getPosition().x + (map.getScaledWidth() / 2) <  gamePanel.getWidth() + xMargin) 
            {
                b = true;

            }
        }
 

        return b;
    }

    
    public void resetWorld(MapObj map, Player player)
    {
        objectList.clear();

        this.map = map;


        this.player = player;


        this.setMap(map);
        this.setPlayer(player);

        player.setWorldRenderer(this);
    }

    public void removeObject(GameObject obj)
    {

        // Do not remove player or the map
        if(obj == this.player || obj == this.map) return;

        for(int x = 0; x < this.getObjectList().size(); x++)
        {
            if(obj == this.getObjectList().get(x))
            {
                this.getObjectList().remove((x));
            }
        }

    }

    public void initiateCamera()
    {

        for (GameObject obj : objectList)
        {   
            Vector2 desVel = new Vector2();
        
            // Move objects
            if((int)map.getPosition().x - (int)(map.getScaledWidth() / 2) > -xMargin) 
            {
                
            }

            obj.move(desVel);
            obj.interpolate(1);
        }

        // When entering a world, reset the world to be able to

    }

    public MapObj getMap()
    {
        return this.map;
    }

    
    public void drawWorld(Graphics g)
    {
        // Draw effects
        Graphics2D graphics2 = (Graphics2D) g;

            //System.out.println("I am rendering shit rn");
            ArrayList<GameObject> list = getObjectList();

        
            // In world renderer, the map must always be drawn first
            for (int x = 0; x < getObjectList().size(); x++) 
            {
                
                if (list.get(x).getImage() != null) {

                    graphics2.drawImage(
                        list.get(x).getImage(),
                        (int) list.get(x).getRenderX() - (int)(list.get(x).getScaledWidth() / 2),
                        (int) list.get(x).getRenderY() - (int)(list.get(x).getScaledHeight() / 2),
                        (int)list.get(x).getScaledWidth(),
                        (int)list.get(x).getScaledHeight(),
                        null
                    );

                } else {

                    graphics2.setColor(list.get(x).getColor());

                    graphics2.fillRect(
                        (int) list.get(x).getRenderX() - (int)(list.get(x).getScaledWidth() / 2),
                        (int) list.get(x).getRenderY() - (int)(list.get(x).getScaledHeight() / 2),
                        (int) list.get(x).getScaledWidth(),
                        (int) list.get(x).getScaledHeight()
                    );
                }
            }
        

    }

    public synchronized void drawDebugWorld(Graphics g)
    {
        Graphics2D graphics2 = (Graphics2D) g;
        
        // Draws World dddebug stuff first 
        if(this.debugMode == true)
        {
            graphics2.setStroke(new BasicStroke(2));
            for (GameObject obj : getObjectList()) {
                if (obj.getCollider() != null) {
                    if(obj.getCollider() instanceof RectangleCollider)
                    {
                        if(obj.getCollider().getIsColliding() == true)
                        {
                            g.setColor(obj.getCollider().activeColor);
                        } else
                        {
                            g.setColor(obj.getCollider().inactiveColor);
                        }

                        if(obj.getCollider().getIsMovable() == false) g.setColor(Color.RED);

                        
                        RectangleCollider tempCol = (RectangleCollider)obj.getCollider(); 
                        //System.out.println(tempCol.getLocalBounds().getWidth());
                        graphics2.drawRect(
                            (int)obj.getPosition().x - tempCol.getLocalBounds().LEFT,
                            (int)obj.getPosition().y - tempCol.getLocalBounds().TOP,
                            tempCol.getLocalBounds().getWidth(),
                            tempCol.getLocalBounds().getLength()                            
                        );
                    }
                }
            }         
        }
    }

    // for infinite level
    public int getEnemyCount() {

        int count = 0;

        for (GameObject obj : objectList) {
            if (obj instanceof Enemy) {
                count++;
            }
        }
        return count;
    }

    public void clearEnemies()
    {
        objectList.removeIf(obj -> obj instanceof Enemy);
    }



    public void clearChests()
    {
        objectList.removeIf(obj -> obj instanceof TreasureChest);
    }public void clearWaveObjects()
    {
        clearEnemies();
        clearChests();
    }
}

