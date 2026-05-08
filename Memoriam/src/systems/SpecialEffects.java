package systems;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

public class SpecialEffects {
    

    public ArrayList<Effects> effectList = new ArrayList<>();
    
    
    

    public abstract class Effects
    {
        protected int lifeTime;
        protected int currentLifetime;

        public abstract void drawEffect(Graphics g);
        public abstract void onTimeHit();

        public void timeEffect(int miliseccond)
        {
            this.currentLifetime -= miliseccond; 
            onTimeHit();
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
            this.lifeTime = 750;
            this.currentLifetime = this.lifeTime;
            
        }

        @Override
        public void onTimeHit()
        {
            // Alpha decreases per thing

            this.alpha = (int)(255 * ((double)this.currentLifetime/(double)this.lifeTime));
            if(this.alpha <= 0) this.alpha = 0;
        }


        @Override
        public void drawEffect(Graphics g)
        {
            drawPopUpNumber(position, numberText, g);
        }

        public void drawPopUpNumber(Vector2 position, int number, Graphics g)
        {
            Font monoFontLil = new Font("Monospaced", Font.BOLD, 20);



            g.setColor(new Color(255, 255, 255, this.alpha));

            int width = 50;
            int height = 30;

            FontMetrics metrics = g.getFontMetrics(monoFontLil);
    
    // Get the bounding box
            int fWidth = metrics.stringWidth(String.valueOf(number));
            int fHeight = metrics.getHeight();

            int cW = (int)position.x - width/2;
            int cH = (int)position.y - (height/2) - 100;
            g.fillRect(cW, cH, width, height);



            g.setFont(monoFontLil);
            g.setColor(new Color(0, 0, 0, this.alpha));
            g.drawString(String.valueOf(number), cW + (width/2) - (fWidth/2), cH + 22);
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
    public void update(int msForEachFrame)
    {
        // Purpose of the updtae function is to  call the timeEffect mmethof
        for(Effects fx: effectList)
        {
            fx.timeEffect(msForEachFrame);
        }
        
    }


}
