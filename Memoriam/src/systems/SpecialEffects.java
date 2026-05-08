package systems;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Color;
import java.util.ArrayList;

public class SpecialEffects {
    

    public ArrayList<Effects> effectList = new ArrayList<>();
    
    
    

    public abstract class Effects
    {
        protected int lifeTime;

        public abstract void drawEffect(Graphics g);
        public abstract void onTimeHit();

        public void timeEffect(int miliseccond)
        {
            this.lifeTime -= miliseccond; 
        }
    }

    public void spawnNumberPopup(Vector2 position, int number)
    {
        effectList.add(new PopUpNumber(position, number));
    }


    public class PopUpNumber extends Effects
    {
        private Vector2 position = new Vector2(0, 0); 
        private int numberText = 0; 
        private int alpha = 255;


        public PopUpNumber(Vector2 position, int number)
        {
            this.position = position;
            this.numberText = number;
        }

        @Override
        public void onTimeHit()
        {

        }


        @Override
        public void drawEffect(Graphics g)
        {
            drawPopUpNumber(position, numberText, g);
        }

        public void drawPopUpNumber(Vector2 position, int number, Graphics g)
        {
            g.setColor(new Color(0, 0, 0, this.alpha));
            g.drawString(String.valueOf(number), (int)position.x, (int)position.y + 100);
        }
    }


    // Draw loop, call in playableScreen paintComponent
    public void drawEffects(Graphics g)
    {
        for(Effects fx : effectList)
        {
            fx.drawEffect(g);
        }
    }

    // Update function
    public void update()
    {
        
    }


}
