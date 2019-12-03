class Point{
    int x,y;
    Point(){
        x=0;
        y=0;
    }
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    int getX(){
        return this.x;
    }
    int getY(){
        return this.y;
    }
    void setX(int a){
        this.x = a;
    }
    void setY(int b){
        this.y = b;
    }
    boolean isSame(Point a){
        if(a.x==this.x && a.y==this.y)
            return true;
        else
            return false;
    }
}