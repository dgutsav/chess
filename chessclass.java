class Board{
    Piece board[][];
    int max_size;
    void init_board(int m){
        max_size = m;
        board = new Piece[max_size][max_size];
        for(int i=0;i<max_size;i++){
            for(int j=0;j<max_size;j++){
                board[i][j] = new EmptyPiece();
            }
        }    
    }
    void disp_board(){
        for(int i=0;i<max_size;i++){
            for(int j=0;j<max_size;j++){
                board[i][j].display();
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
class Piece{
    String name;
    void display(){
        System.out.print(name);
    }
}
class Pawn extends Piece{
    Pawn(String n){
        name = n;
    }
}
class EmptyPiece extends Piece{
    EmptyPiece(){
        name = "-";
    }
}
class chessclass{
    public static void main(String args[]){
        Board b=new Board();
        b.init_board(5);
        b.disp_board();
    }
}