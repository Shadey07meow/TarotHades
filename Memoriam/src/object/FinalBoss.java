package object;

import collision.*;
import scenes.*;
import systems.*;


public class FinalBoss extends Enemy{
    private final int maxHealth = 500;
    //private final int maxHealth = 10;
    private double currentSpeed = 0;    
    private int shotCount;
    private int shotsToSpawn = 5;

    private int phase = 1;

    public FinalBoss(Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        this.fireCooldown = 0.1 * 1000;
        this.health = maxHealth;
        this.speed = currentSpeed;
        this.setImage(img.finalBoss);
        this.moveRightImg = img.finalBoss;
        this.moveLeftImg = img.finalBoss;
        this.setDetectionDistance(999999);
        this.hurtImg = img.finalBossHurt;
        this.damage = 1;
        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));
    }

    @Override 
    public void shootAtPlayer()
    {
        super.shootAtPlayer();
        shotCount += 1;

        if(shotCount >= shotsToSpawn)
        {
            if(phase == 1)
            {
                spawnBaddies();
            }else if (phase == 2)
            {
                spawnBaddies2();
            }
            shotCount = 0;
        }
    }

    public void spawnBaddies()
    {
        BlueWisp bossBlu = new BlueWisp(new Vector2(this.position.x + 100, this.position.y), 2, this.playScrn);

        BlueWisp bossBlu2 = new BlueWisp(new Vector2(this.position.x - 100, this.position.y), 2, this.playScrn);
        bossBlu.setDetectionDistance(10000);
        bossBlu2.setDetectionDistance(10000);
        world.addObject(bossBlu);
        world.addObject(bossBlu2);
    }

    public void spawnBaddies2()
    {
        BlueWisp bossBlu = new BlueWisp(new Vector2(this.position.x + 100, this.position.y), 2, this.playScrn);
        PurpleWisp bossPurpur2 = new PurpleWisp(new Vector2(this.position.x - 100, this.position.y), 2, this.playScrn);
        bossBlu.setDetectionDistance(10000);
        bossPurpur2.setDetectionDistance(10000);
        bossPurpur2.setAttackingRange(400);
        world.addObject(bossBlu);
        world.addObject(bossPurpur2);
    }


    @Override
    public void onDeath()
    {
        super.onDeath();
        SaveSystem.saveProgress(this.playScrn.getID(), this.playScrn.getWorldRenderer().getPlayer().getHP(), this.playScrn.getWorldRenderer().getPlayer().getAbilityMap(), GameStats.get().getEnemiesKilled());

        passLevel();
    }

    // Passing level logic
    private void passLevel()
    {
        CutsceneScreen cutscene =
            (CutsceneScreen) playScrn.getGameFrame().getPanel("cutscene");
        
        cutscene.loadEndingCutscene();

        playScrn.getGameFrame().showPanel("cutscene");
    }

    
    @Override
    public void damage(int a)
    {
        if(this.health < (maxHealth / 2))
        {
            System.out.println("Entered phase 2");
            this.speed = 20;
            this.attackingRange = 750;
            this.fireCooldown = 0.7 * 1000;
            this.shotsToSpawn = 1;
            this.phase = 2;

        }
        super.damage(a);
    }

}
