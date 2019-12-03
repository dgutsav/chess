abstract class Piece{
    String name;
    int player;
    Point initialPoint;
    Point piecePoint;
    Point nextPoint;
    boolean isKilled;
    boolean eats;
    boolean moving;
    Piece(String name, int p){
        this.name = name;
        this.player = p;
        isKilled  = false;
        eats = true;
        moving = false;
    }
    String getName(){
        return name+"("+player+")";
    }
    void setPoint(Point pt){
        piecePoint = new Point();
        piecePoint.setX(pt.x);
        piecePoint.setY(pt.y);
    }
    Point getpoint(){
        return this.piecePoint;
    }
    void moveF(int left_steps){
        nextPoint.setX(piecePoint.x);
        nextPoint.setY(piecePoint.y-1);
        moving = true;
    }
    void moveB(int left_steps){
        nextPoint.setX(piecePoint.x);
        nextPoint.setY(piecePoint.y+1);
        moving = true;
    }
    void moveL(int left_steps){
        nextPoint.setX(piecePoint.x-1);
        nextPoint.setY(piecePoint.y);
        moving = true;
    }
    void moveR(int left_steps){
        nextPoint.setX(piecePoint.x+1);
        nextPoint.setY(piecePoint.y);
        moving = true;
    }
    void moveFL(int left_steps){
        nextPoint.setX(piecePoint.x-1);
        nextPoint.setY(piecePoint.y-1);
        moving = true;
    }
    void moveFR(int left_steps){
        nextPoint.setX(piecePoint.x+1);
        nextPoint.setY(piecePoint.y-1);
        moving = true;
    }
    void moveBL(int left_steps){
        nextPoint.setX(piecePoint.x-1);
        nextPoint.setY(piecePoint.y+1);
        moving = true;
    }
    void moveBR(int left_steps){
        nextPoint.setX(piecePoint.x+1);
        nextPoint.setY(piecePoint.y+1);
        moving = true;
    }
    void beingMoved(){
        initialPoint = new Point();
        initialPoint.x = piecePoint.getX();
        initialPoint.y = piecePoint.getY();
        nextPoint = new Point();
    }
    abstract boolean validateMove(String str);
    abstract String[] getSteps(String str);
    void kill(){
        this.isKilled = true;
        this.piecePoint = null;
        this.nextPoint = null;
        this.initialPoint = null;
        this.moving = false;
    }
    void hasMoved(){
        if(moving){
            moving = false;
            piecePoint.x = nextPoint.x;
            piecePoint.y = nextPoint.y;
        }
    }
}