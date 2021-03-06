import java.awt.*;

public class Missile {
    int x,y;
    Tank.Direction dir;
    private boolean isMissileLive = true;

    private static final int XSPEED = 10;
    private static final int YSPEED = 10;

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    TankClient tc;

    public Missile(int x, int y, Tank.Direction dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Missile(int x, int y, Tank.Direction dir, TankClient tc){
        this(x, y, dir);
        this.tc = tc;

    }

    public void draw(Graphics g){

        if(isMissileLive == false){
            tc.missileList.remove(this);
        }

        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        move();
    }

    private void move(){
//System.out.println(dir);
        switch(dir){
            case L:
                x -= XSPEED;
                break;
            case LU:
                x -= XSPEED;
                y -= YSPEED;
                break;
            case U:
                y -= YSPEED;
                break;
            case RU:
                x += XSPEED;
                y -= YSPEED;
                break;
            case R:
                x += XSPEED;
                break;
            case RD:
                x += XSPEED;
                y += YSPEED;
                break;
            case D:
                y += YSPEED;
                break;
            case LD:
                x -= XSPEED;
                y += YSPEED;
                break;
        }
        if( x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_WIDTH){
            setIsMissileLive ( false );
            tc.missileList.remove(this);
        }
    }

    public boolean getIsMissileLive(){
        return isMissileLive;
    }

    public void setIsMissileLive(boolean isMissileLive){
        this.isMissileLive=isMissileLive;
    }

    public Rectangle getRect(){ //碰撞检测类
        return new Rectangle( x, y, WIDTH, HEIGHT );
    }

    public boolean hitTank(Tank tank){
        if(this.getRect().intersects(tank.getRect()) && tank.getTankLive() ) { //这个子弹与 坦克相交
            tank.setTankLive(false);  //消除坦克
            this.isMissileLive=false;
            return true;
        }
        return false;
    }
}