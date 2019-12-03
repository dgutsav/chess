import java.util.*;

class Board{
    Piece board[][];
    ArrayList <Piece> pieces = new ArrayList<Piece>();
    int max_size;
    void init_board(int m){
        max_size = m;
        board = new Piece[max_size][max_size];
        for(int i=0;i<max_size;i++){
            for(int j=0;j<max_size;j++){
                board[i][j] = null;
            }
        }    
    }
    void disp_board(){
        for(int i=0;i<max_size;i++){
            for(int j=0;j<max_size;j++){
                if(board[i][j]==null){
                    System.out.print("-");
                }
                else{
                    board[i][j].display();
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    void addPiece(String type, String name, int x, int y){
        if(type.equalsIgnoreCase("Pawn")){
            Pawn pawn = new Pawn(name,x,y);
            pieces.add(pawn);
        }
        else if(type.equalsIgnoreCase("Hero1")){
            Hero1 hero1 = new Hero1(name,x,y);
            pieces.add(hero1);
        }
        else if(type.equalsIgnoreCase("Hero2")){
            Hero2 hero2 = new Hero2(name,x,y);
            pieces.add(hero2);
        }
        else if(type.equalsIgnoreCase("Hero3")){
            Hero3 hero3 = new Hero3(name,x,y);
            pieces.add(hero3);
        }
    }
    
}
class Piece{
    int x,y;
    int nx, ny;
    boolean eats = false;
    String name;
    boolean move_impending = false;
    Piece(String n, int x, int y){
        this.name = n;
        this.x = x;
        this.y = y;
    }
    void display(){
        System.out.print(name);
    }
    void move(){
        
    }
}
class Pawn extends Piece{
    Pawn(String n, int x, int y){
        super(n,x,y);
        eats = true;
    }
    /*Pawn(String n, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }*/
    void moveF(int turn){
        nx = x; 
        ny = y;
        if(turn==1){
            ny--;
        }
        else if(turn == 2){
            ny++;
        }
        move_impending = true;
    }
    void moveB(int turn){
        nx = x; 
        ny = y;
        if(turn==1){
            ny++;
        }
        else if(turn == 2){
            ny--;
        }
        move_impending = true;
    }
    void moveR(int turn){
        nx = x; 
        ny = y;
        if(turn==1){
            nx++;
        }
        else if(turn == 2){
            nx--;
        }
        move_impending = true;
    }
    void moveL(int turn){
        nx = x; 
        ny = y;
        if(turn==1){
            nx--;
        }
        else if(turn == 2){
            nx++;
        }
        move_impending = true;
    }
    
}
/*class EmptyPiece extends Piece{
    EmptyPiece(String n, int x, int y){
        name = "-";
    }
}*/
class Hero1 extends Piece{
    int jumps_x = 2;
    int jumps_y = 2;
    Hero1(String n, int x, int y){
        super(n,x,y);
        eats = true;
    }
void moveF(int turn){
        nx = x; 
        ny = y;
        if(turn==1){
            ny-=2;
        }
        else if(turn == 2){
            ny+=2;
        }
        move_impending = true;
    }
    void moveB(int turn){
        nx = x; 
        ny = y;
        if(turn==1){
            ny+=2;
        }
        else if(turn == 2){
            ny-=2;
        }
        move_impending = true;
    }
    void moveR(int turn){
        nx = x; 
        ny = y;
        if(turn==1){
            nx+=2;
        }
        else if(turn == 2){
            nx-=2;
        }
        move_impending = true;
    }
    void moveL(int turn){
        nx = x; 
        ny = y;
        if(turn==1){
            nx-=2;
        }
        else if(turn == 2){
            nx+=2;
        }
        move_impending = true;
    }
}
class Hero3 extends Piece{
    int jumps_x = 2;
    int jumps_y = 2;
    Hero3(String n, int x, int y){
        super(n,x,y);
        eats = false;
    }
    void moveFL(int turn){
        nx = x;
        ny = y;
        if(turn==1){
            ny-=2;
            nx--;
        }
        else if(turn==2){
            ny+=2;
            nx++;
        }
        move_impending = true;
    }
    void moveFR(int turn){
        nx = x;
        ny = y;
        if(turn==1){
            ny-=2;
            nx++;
        }
        else if(turn==2){
            ny+=2;
            nx--;
        }
        move_impending = true;
    }
    void moveBL(int turn){
        nx = x;
        ny = y;
        if(turn==1){
            ny+=2;
            nx--;
        }
        else if(turn==2){
            ny-=2;
            nx++;
        }
        move_impending = true;
    }
    void moveBR(int turn){
        nx = x;
        ny = y;
        if(turn==1){
            ny+=2;
            nx++;
        }
        else if(turn==2){
            ny-=2;
            nx--;
        }
        move_impending = true;
    }
    void moveRB(int turn){
        nx = x;
        ny = y;
        if(turn == 1){
            ny++;
            nx+=2;
        }
        else if(turn == 2){
            ny--;
            nx-=2;
        }
    }
}
class Hero2 extends Piece{
    Hero2(String n, int x, int y){
        super(n,x,y);
        eats = true;
    }
}
class chessclass{
    static int turn;
    String players[]={"A","B"};
    static String getPieceType(String n){
        if(n.charAt(0)=='P'||n.charAt(0)=='p')
            return "Pawn";
        else if(n.charAt(0)=='H' || n.charAt(0)=='h'){
            if(n.equals("H1"))
                return "Hero1";
            else if(n.equals("H2"))
                return "Hero2";
            else if(n.equals("H3"))
                return "Hero3";
        }
    }
    static void startgame(Board b){
        Scanner sc = new Scanner(System.in);
        String input;
        String in[];
        for(int i=1;i<=2;i++){
            turn = i;
            System.out.println("Player "+i+" Start");
            input = sc.nextLine();
            in = input.split(",");
            addPiece
        }
    }
    public static void main(String args[]){
        Board b=new Board();
        b.init_board(5);
        b.disp_board();
        
    }
}