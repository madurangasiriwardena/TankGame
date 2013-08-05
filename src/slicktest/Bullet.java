/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

import org.newdawn.slick.Image;

public class Bullet {
    private Image bulletImg;
    private int xCord;
    private int yCord;
    private int direction;
    
    
    public Bullet(Image img,int x,int y,int dir){
        this.bulletImg=img;
        this.direction=dir;
        xCord=x;
        yCord=y;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public Image getBulletImg() {
        return bulletImg;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
//        if(direction==1){
//            this.xCord=(xCord+1)*32;
//        }
//        else if(direction==3){
//            this.xCord=(xCord-1)*32;
//        }
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
//        if(direction==0){
//            this.yCord=(yCord+1)*32;
//        }
//        else if(direction==2){
//            this.yCord=(yCord-1)*32;
//        }
        
    }

    public int getDirection() {
        return direction;
    }
    
    
}
