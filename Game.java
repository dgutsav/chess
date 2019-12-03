import java.util.*;


class Game{
    static Board board;
    ArrayList <Piece> pieces = new ArrayList<Piece>();
    static int turn;
    boolean game_over = false;
    String getPieceType(String n){
        if(n.charAt(0)=='P'||n.charAt(0)=='p')
            return "Pawn";
        else if(n.charAt(0)=='H' || n.charAt(0)=='h'){
            if(n.equalsIgnoreCase("H1"))
                return "Hero1";
            else if(n.equalsIgnoreCase("H2"))
                return "Hero2";
            else if(n.equalsIgnoreCase("H3"))
                return "Hero3";
        }
        return null;
    }
    Piece fetchPiece(String str){
        for(Piece p:pieces){
            if(str.equalsIgnoreCase(p.name))
                return p;
        }
        return null;
    }
    boolean addPieceToBoard(int player, String name, int x, int y){
        System.out.println(name+" "+x+" "+y);
        Point point = new Point(x,y);
        if(board.isOnBoard(point)==false)
            return false;
        if(isPieceThere(point))
            return false;
        String type = getPieceType(name);
        if(type.equalsIgnoreCase("Pawn")){
            Pawn pawn = new Pawn(name, player);
            pawn.piecePoint = point;
            pieces.add(pawn);
            board.board[y][x] = pawn;
            System.out.println(name+" added");
            return true;
        }
        else if(type.equalsIgnoreCase("Hero1")){
            Hero1 h1 = new Hero1(name, player);
            h1.piecePoint = point;
            pieces.add(h1);
            board.board[y][x] = h1;
            return true;
        }
        else if(type.equalsIgnoreCase("Hero2")){
            Hero2 h2 = new Hero2(name, player);
            h2.piecePoint = point;
            pieces.add(h2);
            board.board[y][x] = h2;
            return true;
        }
        else if(type.equalsIgnoreCase("Hero3")){
            Hero1 h3 = new Hero1(name, player);
            h3.piecePoint = point;
            pieces.add(h3);
            board.board[y][x] = h3;
            return true;
        }
        return false;
    }
    boolean isPieceThere(Point point){
        for(Piece p:pieces){
            if(p.piecePoint.isSame(point)){
                return true;
            }
        }
        return false;
    }
    boolean isFriendlyPiece(Point point,int player){
        for(Piece p:pieces){
            if(p.piecePoint.isSame(point)){
                if(p.player==player)
                    return true;
            }
        }
        return false;
    }
    boolean isEnemyPiece(Point point,int player){
        for(Piece p:pieces){
            if(p.piecePoint.isSame(point)){
                if(p.player!=player)
                    return true;
            }
        }
        return false;
    }
    void startForPlayer(int t){
        int y = 0;
        if(t==1)
            y = board.size-1;
        else
            y = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Start Game\nPlayer "+t);
        String input = sc.nextLine();
        String p[] = input.split(",");
        for(int i=0;i<p.length;i++){
            if(addPieceToBoard(t,p[i].trim(),i,y)==false){
                System.out.println("Piece "+p[i]+" could not be placed on board");
                return;
            }
        }
    }
    void init(){
        board = new Board();
        board.setSize(5);
        board.init();
        startForPlayer(1);
        board.show();
        startForPlayer(2);
        board.show();   
    }
    void play(){
        String input,piece,move;
        Scanner sc = new Scanner(System.in);
        String s[],stp[];
        turn = 1;
        int steps;
        while(!game_over){
            System.out.println("Player "+turn%2+" Move:");
            input = sc.next();
            s = input.split(":");
            piece = s[0];
            move = s[1];
            if(piece==null){
                System.out.println("Incorrect piece selected");
                continue;
            }
            if(piece.player!=(turn%2)){
                System.out.println("Choose your piece.");
                continue;
            }
            if(piece.validateMove(move)==false){
                System.out.println("Illegal Move");
                continue;
            }
            stp=piece.getSteps(move);
            piece.beingMoved();
            for(int i=0;i<stp.length;i++){
                if(stp[i].equalsIgnoreCase("f")){
                    piece.moveF(stp.length-i);
                }
                else if(stp[i].equalsIgnoreCase("b")){
                    piece.moveB(stp.length-i);
                }
                else if(stp[i].equalsIgnoreCase("l")){
                    piece.moveL(stp.length-i);
                }
                else if(stp[i].equalsIgnoreCase("r")){
                    piece.moveR(stp.length-i);
                }
                else if(stp[i].equalsIgnoreCase("fl")){
                    piece.moveFL(stp.length-i);
                }
                else if(stp[i].equalsIgnoreCase("fr")){
                    piece.moveFR(stp.length-i);
                }
                else if(stp[i].equalsIgnoreCase("bl")){
                    piece.moveBL(stp.length-i);
                }
                else if(stp[i].equalsIgnoreCase("br")){
                    piece.moveBR(stp.length-i);
                }                   
            }
            turn++;
        }
    }
    public static void main(String args[]){
        Game g=new Game();
        g.init();
        g.play();
    }
}