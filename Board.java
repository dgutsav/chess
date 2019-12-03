class Board{
    int size;
    Piece board[][];
    void setSize(){
        this.size = 5;
        board = new Piece[5][5];
    }
    void init(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                board[i][j] = null;
            }
        }
    }
    void show(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==null)
                    System.out.print("-");
                else
                    System.out.print(board[i][j].getName());
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    void setSize(int s){
        this.size = s;
        board = new Piece[s][s];
    }
    boolean isOnBoard(Point p){
        if(p.x<0 || p.y<0)
            return false;
        else if(p.x>=size || p.y>=size)
            return false;
        else
            return true;
    }
    void movePiece(Point oldPos, Point newPos){
        Piece piece = board[oldPos.y][oldPos.x];
        board[oldPos.y][oldPos.x] = null;
        board[newPos.y][newPos.x] = piece;
    }
}