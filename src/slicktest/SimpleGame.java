package slicktest;

import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import sun.org.mozilla.javascript.internal.regexp.SubString;

public class SimpleGame extends BasicGame {

    Image brick, land, stone, water, tank0, tank1, tank2, tank3, tank4, coins, health, uniLogo, bullet;
    private String response, resType;
    SpriteSheet tileSheet;
    SpriteSheet brickTilesht;
    SpriteSheet stoneTilesht;
    SpriteSheet tanks;
    TiledMap map;
    String bricksArr[], stonesArr[], waterArr[];
    Vector<Tank> tankDetails;
    Vector<Brick> brickDetails;
    Vector<Stone> stoneDetails;
    Vector<Water> waterDetails;
    Vector<CoinPile> coinPileDetails;
    Vector<Health> healthDetails;
    
    String MyTank="";
    
    //bulet arrays for the tanks
    Vector<Bullet> p0Bullet;
    Vector<Bullet> p1Bullet;
    Vector<Bullet> p2Bullet;
    Vector<Bullet> p3Bullet;
    Vector<Bullet> p4Bullet;
    int frameCounter = 0;
    float count = 0;
    int x;
    int y;
    MyServer srvr;
    Timer timer;
    MyTimer mt;
    
    //dead tanks x,y, collected owned money
    int[] deadX = new int[5];
    int[] deadY = new int[5];
    boolean[] collected = {false, false, false, false, false};

    public SimpleGame() {
        super("Slick2DPath2Glory - SimpleGame");
        brickDetails = new Vector<Brick>();
        stoneDetails = new Vector<Stone>();
        waterDetails = new Vector<Water>();
        tankDetails = new Vector<Tank>();
        coinPileDetails = new Vector<CoinPile>();
        healthDetails = new Vector<Health>();
        
        p0Bullet=new Vector<>();
        p1Bullet=new Vector<>();
        p2Bullet=new Vector<>();
        p3Bullet=new Vector<>();
        p4Bullet=new Vector<>();

        mt = new MyTimer();

        //New Timer
        timer = new Timer();
        timer.schedule(mt, 0);


        for (int a = 0; a < 5; a++) {
            deadX[a] = -1;
            deadY[a] = -1;
        }


    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        map = new TiledMap("Images/mapFin.tmx");
        tileSheet = new SpriteSheet("Images/mapBasic.png", 32, 32);
        tanks = new SpriteSheet("Images/MulticolorTanks.png", 32, 32);
        brickTilesht = new SpriteSheet("Images/brk.png", 32, 32);
        stoneTilesht = new SpriteSheet("Images/stone.jpg", 32, 32);

//        brick = tileSheet.getSprite(15, 5);
        brick = brickTilesht.getSprite(29, 13);
        land = tileSheet.getSprite(1, 11);
        stone = stoneTilesht.getSprite(3, 4);
        water = tileSheet.getSprite(0, 4);
        tank0 = tanks.getSprite(0, 1);
        tank1 = tanks.getSprite(0, 2);
        tank2 = tanks.getSprite(0, 0);
        tank3 = tanks.getSprite(0, 3);
        tank4 = tanks.getSprite(0, 4);
        coins = new Image("Images/Money_bag.png");
        health = new Image("Images/health2.png");
        uniLogo = new Image("Images/uniLogo.png");
        bullet = new Image("Images/bullet2.png");
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {


        count += delta / 1000f;
        if (count >= 1.0) {
            x += 32;
            y += 32;
            count = 0;
        }





        //2013-08-02
        //render bullets

        
        try{
        if (!p0Bullet.isEmpty()) {
            for (int bl = 0; bl < p0Bullet.size(); bl++) {

                //check for bricks
                for (int br = 0; br < brickDetails.size(); br++) {
                    if (p0Bullet.get(bl).getxCord() == brickDetails.get(br).getxCord() && p0Bullet.get(bl).getyCord() == brickDetails.get(br).getyCord()) {
                        p0Bullet.remove(bl);
                    } else {
                    }
                }

                //check for stones

                for (int st = 0; st < stoneDetails.size(); st++) {
                    if (p0Bullet.get(bl).getxCord() == stoneDetails.get(st).getxCord() && p0Bullet.get(bl).getyCord() == stoneDetails.get(st).getyCord()) {
                        p0Bullet.remove(bl);
                    } else {
                    }
                }

                //check for tanks
                        for (int tk = 0; tk < tankDetails.size(); tk++) {
                            if (p0Bullet.get(bl).getxCord()==tankDetails.get(tk).getxCord() && p0Bullet.get(bl).getyCord()==tankDetails.get(tk).getyCord()) {
                                p0Bullet.remove(bl);
                            }
                        }
            }


        } else if (!p1Bullet.isEmpty()) {
            for (int bl = 0; bl < p1Bullet.size(); bl++) {

                //check for bricks
                for (int br = 0; br < brickDetails.size(); br++) {
                    if (p1Bullet.get(bl).getxCord() == brickDetails.get(br).getxCord() && p1Bullet.get(bl).getyCord() == brickDetails.get(br).getyCord()) {
                        p1Bullet.remove(bl);
                    } else {
                    }
                }

                //check for stones

                for (int st = 0; st < stoneDetails.size(); st++) {
                    if (p1Bullet.get(bl).getxCord() == stoneDetails.get(st).getxCord() && p1Bullet.get(bl).getyCord() == stoneDetails.get(st).getyCord()) {
                        p1Bullet.remove(bl);
                    } else {
                    }
                }

                //check for tanks
                        for (int tk = 0; tk < tankDetails.size(); tk++) {
                            if (p1Bullet.get(bl).getxCord()==tankDetails.get(tk).getxCord() && p1Bullet.get(bl).getyCord()==tankDetails.get(tk).getyCord()) {
                                p1Bullet.remove(bl);
                            }
                        }
            }
        } else if (!p2Bullet.isEmpty()) {
            for (int bl = 0; bl < p2Bullet.size(); bl++) {

                //check for bricks
                for (int br = 0; br < brickDetails.size(); br++) {
                    if (p2Bullet.get(bl).getxCord() == brickDetails.get(br).getxCord() && p2Bullet.get(bl).getyCord() == brickDetails.get(br).getyCord()) {
                        p2Bullet.remove(bl);
                    } else {
                    }
                }

                //check for stones

                for (int st = 0; st < stoneDetails.size(); st++) {
                    if (p2Bullet.get(bl).getxCord() == stoneDetails.get(st).getxCord() && p2Bullet.get(bl).getyCord() == stoneDetails.get(st).getyCord()) {
                        p2Bullet.remove(bl);
                    } else {
                    }
                }

                //check for tanks
                        for (int tk = 0; tk < tankDetails.size(); tk++) {
                            if (p2Bullet.get(bl).getxCord()==tankDetails.get(tk).getxCord() && p2Bullet.get(bl).getyCord()==tankDetails.get(tk).getyCord()) {
                                p2Bullet.remove(bl);
                            }
                        }
            }
        } else if (!p3Bullet.isEmpty()) {
            for (int bl = 0; bl < p3Bullet.size(); bl++) {

                //check for bricks
                for (int br = 0; br < brickDetails.size(); br++) {
                    if (p3Bullet.get(bl).getxCord() == brickDetails.get(br).getxCord() && p3Bullet.get(bl).getyCord() == brickDetails.get(br).getyCord()) {
                        p3Bullet.remove(bl);
                    } else {
                    }
                }

                //check for stones

                for (int st = 0; st < stoneDetails.size(); st++) {
                    if (p3Bullet.get(bl).getxCord() == stoneDetails.get(st).getxCord() && p3Bullet.get(bl).getyCord() == stoneDetails.get(st).getyCord()) {
                        p3Bullet.remove(bl);
                    } else {
                    }
                }

                //check for tanks
                        for (int tk = 0; tk < tankDetails.size(); tk++) {
                            if (p3Bullet.get(bl).getxCord()==tankDetails.get(tk).getxCord() && p3Bullet.get(bl).getyCord()==tankDetails.get(tk).getyCord()) {
                                p3Bullet.remove(bl);
                            }
                        }
            }
        } else if (!p4Bullet.isEmpty()) {
            for (int bl = 0; bl < p4Bullet.size(); bl++) {

                //check for bricks
                for (int br = 0; br < brickDetails.size(); br++) {
                    if (p4Bullet.get(bl).getxCord() == brickDetails.get(br).getxCord() && p4Bullet.get(bl).getyCord() == brickDetails.get(br).getyCord()) {
                        p4Bullet.remove(bl);
                    } else {
                    }
                }

                //check for stones

                for (int st = 0; st < stoneDetails.size(); st++) {
                    if (p4Bullet.get(bl).getxCord() == stoneDetails.get(st).getxCord() && p4Bullet.get(bl).getyCord() == stoneDetails.get(st).getyCord()) {
                        p4Bullet.remove(bl);
                    } else {
                    }
                }

                //check for tanks
                        for (int tk = 0; tk < tankDetails.size(); tk++) {
                            if (p4Bullet.get(bl).getxCord()==tankDetails.get(tk).getxCord() && p4Bullet.get(bl).getyCord()==tankDetails.get(tk).getyCord()) {
                                p4Bullet.remove(bl);
                            }
                        }
            }
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }

    public void render(GameContainer gc, Graphics g) throws SlickException {

        map.render(0, 0);
        Brick brk;
        Stone stne;
        Water wtr;
        Tank tnk;
        CoinPile cp;
        Health hlth;

        //render bricks
        if (!this.brickDetails.isEmpty()) {
            for (int a = 0; a < this.brickDetails.size(); a++) {
                brk = this.brickDetails.get(a);
                //Check for brick damages
                if (brk.getDamage() != 4) {
                    brick.draw(brk.getxCord(), brk.getyCord());
                }
            }
        }

        //render Stones
        if (!this.stoneDetails.isEmpty()) {
            for (int a = 0; a < this.stoneDetails.size(); a++) {
                stne = this.stoneDetails.get(a);
                stone.draw(stne.getxCord(), stne.getyCord());
            }
        }

        //render water
        if (!this.waterDetails.isEmpty()) {
            for (int a = 0; a < this.waterDetails.size(); a++) {
                wtr = this.waterDetails.get(a);
                water.draw(wtr.getxCord(), wtr.getyCord());
            }
        }

        //render tanks
        if (!this.tankDetails.isEmpty()) {
            for (int a = 0; a < this.tankDetails.size(); a++) {
                tnk = this.tankDetails.get(a);
                if (tankDetails.get(a).rotate) {
                    tankDetails.get(a).getTankImg().rotate(tankDetails.get(a).getAngel());
                    //System.out.println(tankDetails.get(a).getAngel());
                    tankDetails.get(a).rotate = false;
                }
                
                if(a==0){
                    g.setColor(Color.red);
                }
                else if(a==1){
                    g.setColor(Color.green);
                }
                else if(a==2){
                    g.setColor(Color.orange);
                }
                else if(a==3){
                    g.setColor(Color.blue);
                }
                else if(a==4){
                    g.setColor(Color.magenta);
                }
                
                
                
                //draw player details name,coins,health,points
                g.drawString(tankDetails.get(a).name, 724, 134 + a * 32);
                g.setColor(Color.black);
                g.drawString(tankDetails.get(a).getCoins() + "", 782, 134 + a * 32);
                g.drawString(tankDetails.get(a).getCoins() + "", 858, 134 + a * 32);

                if (tankDetails.get(a).getHealth() < 50 && tankDetails.get(a).getHealth() > 25) {
                    g.setColor(Color.orange);

                } else if (tankDetails.get(a).getHealth() < 25) {
                    g.setColor(Color.red);

                } else {
                    g.setColor(Color.decode("0x33cc00"));
                }
                g.drawString(tankDetails.get(a).getHealth() + "", 938, 134 + a * 32);

                //change appplied here


                //render dead tanks' coinpiles
                if (tankDetails.get(a).getHealth() > 0) {
                    for (int p = 0; p < tankDetails.size(); p++) {
                        if (p != a) {
                            if (deadX[p] == tankDetails.get(a).getxCord() && deadY[p] == tankDetails.get(a).getyCord()) {
                                collected[p] = true;
                            }
                        }
                    }

                    tankDetails.get(a).getTankImg().draw(tnk.getxCord(), tnk.getyCord());
                } else {
                    if (!collected[a]) {
                        coins.draw(tnk.getxCord(), tnk.getyCord(), 32, 32);
                    }
                }



            }
        }


        //render coin piles
        if (!this.coinPileDetails.isEmpty()) {
            for (int a = 0; a < this.coinPileDetails.size(); a++) {
                cp = this.coinPileDetails.get(a);
                if (!(mt.getTimerVal() >= cp.getAppearTime() + cp.getLifeTime() / 1000)) {
                    cp.getPileImage().draw(cp.getxCord(), cp.getyCord(), 32, 32);
                } else {
                    coinPileDetails.remove(a);
                }
            }
        }

        //render health packs
        if (!this.healthDetails.isEmpty()) {
            for (int a = 0; a < this.healthDetails.size(); a++) {
                hlth = this.healthDetails.get(a);
                if (!(mt.getTimerVal() >= hlth.getAppearTime() + hlth.getLifeTime() / 1000)) {
                    hlth.getPileImage().draw(hlth.getxCord(), hlth.getyCord(), 32, 32);
                } else {
                    healthDetails.remove(a);
                }
            }
        }

        //render bullets
        if (!p0Bullet.isEmpty()) {
            for (int blt = 0; blt < p0Bullet.size(); blt++) {
                p0Bullet.get(blt).getBulletImg().draw(p0Bullet.get(blt).getxCord(), p0Bullet.get(blt).getyCord(), 32, 32);

                if (frameCounter == 10 || frameCounter == 19 || frameCounter == 27) {
                    p0Bullet.get(blt).setxCord(this.setBulletX(this.p0Bullet.get(blt).getxCord(), p0Bullet.get(blt).getDirection()));
                    p0Bullet.get(blt).setyCord(this.setBulletY(p0Bullet.get(blt).getyCord(), p0Bullet.get(blt).getDirection()));
                }
            }
        }
        
        if (!p1Bullet.isEmpty()) {
            for (int blt = 0; blt < p1Bullet.size(); blt++) {
                p1Bullet.get(blt).getBulletImg().draw(p1Bullet.get(blt).getxCord(), p1Bullet.get(blt).getyCord(), 32, 32);
                //System.out.println("%%%%%%%%"+p0Bullet.get(blt).getxCord()+"--"+ p0Bullet.get(blt).getyCord());

                if (frameCounter == 10 || frameCounter == 19 || frameCounter == 27) {
                    p1Bullet.get(blt).setxCord(this.setBulletX(this.p1Bullet.get(blt).getxCord(), p1Bullet.get(blt).getDirection()));
                    p1Bullet.get(blt).setyCord(this.setBulletY(p1Bullet.get(blt).getyCord(), p1Bullet.get(blt).getDirection()));
                }
            }
        }
        
        if (!p2Bullet.isEmpty()) {
            for (int blt = 0; blt < p2Bullet.size(); blt++) {
                p2Bullet.get(blt).getBulletImg().draw(p2Bullet.get(blt).getxCord(), p2Bullet.get(blt).getyCord(), 32, 32);
                //System.out.println("%%%%%%%%"+p0Bullet.get(blt).getxCord()+"--"+ p0Bullet.get(blt).getyCord());

                if (frameCounter == 10 || frameCounter == 19 || frameCounter == 27) {
                    p2Bullet.get(blt).setxCord(this.setBulletX(this.p2Bullet.get(blt).getxCord(), p2Bullet.get(blt).getDirection()));
                    p2Bullet.get(blt).setyCord(this.setBulletY(p2Bullet.get(blt).getyCord(), p2Bullet.get(blt).getDirection()));
                }
            }
        }
        
        if (!p3Bullet.isEmpty()) {
            for (int blt = 0; blt < p3Bullet.size(); blt++) {
                p3Bullet.get(blt).getBulletImg().draw(p3Bullet.get(blt).getxCord(), p3Bullet.get(blt).getyCord(), 32, 32);
                //System.out.println("%%%%%%%%"+p0Bullet.get(blt).getxCord()+"--"+ p0Bullet.get(blt).getyCord());

                if (frameCounter == 10 || frameCounter == 19 || frameCounter == 27) {
                    p3Bullet.get(blt).setxCord(this.setBulletX(this.p3Bullet.get(blt).getxCord(), p3Bullet.get(blt).getDirection()));
                    p3Bullet.get(blt).setyCord(this.setBulletY(p3Bullet.get(blt).getyCord(), p3Bullet.get(blt).getDirection()));
                }
            }
        }
        
        if (!p4Bullet.isEmpty()) {
            for (int blt = 0; blt < p4Bullet.size(); blt++) {
                p4Bullet.get(blt).getBulletImg().draw(p4Bullet.get(blt).getxCord(), p4Bullet.get(blt).getyCord(), 32, 32);
                //System.out.println("%%%%%%%%"+p0Bullet.get(blt).getxCord()+"--"+ p0Bullet.get(blt).getyCord());

                if (frameCounter == 10 || frameCounter == 19 || frameCounter == 27) {
                    p4Bullet.get(blt).setxCord(this.setBulletX(this.p4Bullet.get(blt).getxCord(), p4Bullet.get(blt).getDirection()));
                    p4Bullet.get(blt).setyCord(this.setBulletY(p4Bullet.get(blt).getyCord(), p4Bullet.get(blt).getDirection()));
                }
            }
        }




        g.setColor(Color.white);
        for (int a = 0; a <= 640; a += 32) {
            g.drawLine(0, a, 640, a);
            g.drawLine(a, 0, a, 640);
        }
        g.setColor(Color.black);
        g.drawRect(704, 96, 288, 192);

        for (int a = 1; a < 6; a++) {
            g.drawLine(704, 96 + 32 * a + 1, 992, 96 + 32 * a + 1);
        }
        g.drawLine(768, 96, 768, 288);
        g.drawLine(848, 96, 848, 288);
        g.drawLine(928, 96, 928, 288);

        g.setColor(Color.red);
        g.drawString("Score Board", 800, 70);

        g.setColor(Color.blue);
        g.drawString("Player", 710, 102);
        g.drawString("Coins", 788, 102);
        g.drawString("Points", 858, 102);
        g.drawString("Health", 933, 102);

        g.setColor(Color.magenta);
        g.drawString("Phoenix Gamers", 790, 328);
        g.setColor(Color.black);
        g.drawString("My Player: "+MyTank, 710, 296);
        g.setColor(Color.black);
        g.drawString("Members:", 700, 359);
        g.drawString("Maduranga Siriwardena", 758, 391);
        g.drawString("Nadeeshaan Gunasinghe", 758, 423);
        g.setColor(Color.red);
        g.drawString("University of Moratuwa", 754, 455);
//         uniLogo.draw(uniLogo,800,487,288,288);
        uniLogo.draw(810, 487, 96, 96);
        
        

        frameCounter++;

    }

    public void setResponse(String res) {
        this.response = res;
    }

    public String getResponse() {
        return this.response;
    }

    //process server response
    public void stringProcessor(String str) {

        StringTokenizer str1, str2;
        String temp;
        String[] tempArr;
        Image tempImg = null;
        int tankX = 0;
        int tankY = 0;


        this.resType = str.charAt(0) + "";

        if (resType.equals("I")) {
            this.MyTank= str.substring(2,4);
            str1 = new StringTokenizer(str.substring(5, str.length() - 1), ":");

            temp = str1.nextToken();
            this.bricksArr = temp.split(";");

            for (int a = 0; a < this.bricksArr.length; a++) {
                Brick brk = new Brick(brick);
                str2 = new StringTokenizer(bricksArr[a], ",");
                brk.setxyDamage(Integer.parseInt(str2.nextToken()) * 32, Integer.parseInt(str2.nextToken()) * 32, 0);
                brickDetails.add(brk);
            }

            temp = str1.nextToken();
            this.stonesArr = temp.split(";");
            for (int a = 0; a < this.stonesArr.length; a++) {
                Stone stne = new Stone(stone);
                str2 = new StringTokenizer(stonesArr[a], ",");
                stne.setxy(Integer.parseInt(str2.nextToken()) * 32, Integer.parseInt(str2.nextToken()) * 32);
                stoneDetails.add(stne);
            }

            temp = str1.nextToken();
            this.waterArr = temp.split(";");
            for (int a = 0; a < this.waterArr.length; a++) {
                Water wtr = new Water(water);
                str2 = new StringTokenizer(waterArr[a], ",");
                wtr.setxy(Integer.parseInt(str2.nextToken()) * 32, Integer.parseInt(str2.nextToken()) * 32);
                waterDetails.add(wtr);
            }
        } else if (resType.equals("S")) {
            str1 = new StringTokenizer(str.substring(2, str.length() - 1), ":");
            int tokens = str1.countTokens();
            for (int a = 0; a < tokens; a++) {
                temp = str1.nextToken();
                tempArr = temp.split(";");
                
                //change applied Here
                
                

                if (Integer.parseInt(tempArr[0].charAt(1) + "") == 0) {
                    tempImg = tank0;
                } else if (Integer.parseInt(tempArr[0].charAt(1) + "") == 1) {
                    tempImg = tank1;
                } else if (Integer.parseInt(tempArr[0].charAt(1) + "") == 2) {
                    tempImg = tank2;
                } else if (Integer.parseInt(tempArr[0].charAt(1) + "") == 3) {
                    tempImg = tank3;
                } else if (Integer.parseInt(tempArr[0].charAt(1) + "") == 4) {
                    tempImg = tank4;
                }

                Tank tnk = new Tank(tempImg, 1);
                str2 = new StringTokenizer(tempArr[1], ",");
                tnk.name = tempArr[0];
                tnk.setxyCord(Integer.parseInt(str2.nextToken()) * 32, Integer.parseInt(str2.nextToken()) * 32);
                tankDetails.add(tnk);
            }
        } else if (resType.equals("G")) {

            this.frameCounter = 0;

            str1 = new StringTokenizer(str.substring(2, str.length() - 1), ":");
            int tokens = str1.countTokens();

            
            //set tank details
            for (int a = 0; a < tokens; a++) {
                temp = str1.nextToken();
                tempArr = temp.split(";");

                str2 = new StringTokenizer(tempArr[1], ",");
                if ((temp.charAt(0) + "").equals("P")) {
                    for (int b = 0; b < tankDetails.size(); b++) {
                        if (tankDetails.get(b).name.equals(tempArr[0])) {
                            tankX = Integer.parseInt(str2.nextToken()) * 32;
                            tankY = Integer.parseInt(str2.nextToken()) * 32;
                            tankDetails.get(b).setxyCord(tankX, tankY);
                            tankDetails.get(b).setAngel((Integer.parseInt(tempArr[2]) - tankDetails.get(b).getDirection()) * 90);
                            tankDetails.get(b).setDirection(Integer.parseInt(tempArr[2]));
                            tankDetails.get(b).rotate = true;

                            tankDetails.get(b).setCoinHealthPoints(Integer.parseInt(tempArr[5]), Integer.parseInt(tempArr[4]), Integer.parseInt(tempArr[6]));
                            
                            //dead Tanks
                            if (tankDetails.get(b).getHealth() == 0) {
                                deadX[b] = tankDetails.get(b).getxCord();
                                deadY[b] = tankDetails.get(b).getyCord();
                            }

                            //set bullet movements and initialize new bullets
                            if (Integer.parseInt(tempArr[3]) == 1) {
                                //add bullets to the lists
                                Bullet bult = new Bullet(bullet, this.setBulletX(tankX, Integer.parseInt(tempArr[2])), this.setBulletY(tankY, Integer.parseInt(tempArr[2])), tankDetails.get(b).getDirection()); //add bullets to the bullet array
                                
                                if (b == 0) {
                                    p0Bullet.add(bult);
                                    System.out.println("@@@@@"+tankX+"--"+tankY);
                                    System.out.println("****"+this.setBulletX(tankX, Integer.parseInt(tempArr[2]))+"--"+this.setBulletY(tankY, Integer.parseInt(tempArr[2])));
                                } else if (b == 1) {
                                    p1Bullet.add(bult);
                                } else if (b == 2) {
                                    p2Bullet.add(bult);
                                } else if (b == 3) {
                                    p3Bullet.add(bult);
                                } else if (b == 4) {
                                    p4Bullet.add(bult);
                                }

                            } else { //if the tank dont shoot correponding array empties
                                if (b == 0 && !p0Bullet.isEmpty()) {
                                    p0Bullet.clear();
                                } else if (b == 1 && !p1Bullet.isEmpty()) {
                                    p1Bullet.clear();
                                } else if (b == 2 && !p2Bullet.isEmpty()) {
                                    p2Bullet.clear();
                                } else if (b == 3 && !p3Bullet.isEmpty()) {
                                    p3Bullet.clear();
                                } else if (b == 4 && !p4Bullet.isEmpty()) {
                                    p4Bullet.clear();
                                }
                            }



                            //removing acquired coin piles
                            for (int d = 0; d < coinPileDetails.size(); d++) {
                                if ((tankDetails.get(b).getxCord() == coinPileDetails.get(d).getxCord()) && (tankDetails.get(b).getyCord() == coinPileDetails.get(d).getyCord())) {
                                    coinPileDetails.remove(d);
                                }
                            }

                            //removing acquired health packs
                            for (int d = 0; d < healthDetails.size(); d++) {
                                if ((tankDetails.get(b).getxCord() == healthDetails.get(d).getxCord()) && (tankDetails.get(b).getyCord() == healthDetails.get(d).getyCord())) {
                                    healthDetails.remove(d);
                                }
                            }
                        }
                    }
                }
            }
        } else if (resType.equals("C") && (str.charAt(1) + "").equals(":")) {
            str1 = new StringTokenizer(str.substring(2, str.length() - 1), ":");
            int tokens = str1.countTokens();
            CoinPile cp = new CoinPile(mt.getTimerVal(), coins);

            temp = str1.nextToken();
            str2 = new StringTokenizer(temp, ",");
            cp.setXY(Integer.parseInt(str2.nextToken()) * 32, Integer.parseInt(str2.nextToken()) * 32);
            cp.setLifeTime(Integer.parseInt(str1.nextToken()));
            cp.setAppearTime(mt.getTimerVal());
            coinPileDetails.add(cp);
        } else if (resType.equals("L")) {
            str1 = new StringTokenizer(str.substring(2, str.length() - 1), ":");
            int tokens = str1.countTokens();
            Health hlth = new Health(mt.getTimerVal(), health);

            temp = str1.nextToken();
            str2 = new StringTokenizer(temp, ",");
            hlth.setXYCord(Integer.parseInt(str2.nextToken()) * 32, Integer.parseInt(str2.nextToken()) * 32);
            hlth.setLifeTime(Integer.parseInt(str1.nextToken()));
            hlth.setAppearTime(mt.getTimerVal());
            healthDetails.add(hlth);
        }

    }
//    public static void main(String[] args) throws SlickException {
//        AppGameContainer app = new AppGameContainer(new SimpleGame());
//        app.setDisplayMode(640, 640, false);
//        app.start();
//    }

    public int setBulletX(int x, int direction) {
        if (direction == 1) {
            return (x + 32);
        } else if (direction == 3) {
            return (x - 32);
        } else {
            return x;
        }
    }

    public int setBulletY(int y, int direction) {
        if (direction == 0) {
            return (y - 32);
        } else if (direction == 2) {
            return (y + 32);
        } else {
            return y;
        }
    }
}
