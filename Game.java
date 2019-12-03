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
            if(p.isKilled)
                continue;
            if(str.equalsIgnoreCase(p.name))
                return p;
        }
        return null;
    }
    Piece fetchPiece(String str, int n){
        for(Piece p:pieces){
            if(p.isKilled)
                continue;
            if(str.equalsIgnoreCase(p.name) && p.player==n)
                return p;
        }
        return null;
    }
    Piece fetchPiece(Point point){
        for(Piece p:pieces){
            if(p.isKilled)
                continue;
            if(p.piecePoint.isSame(point)){
                return p;
            }
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
            if(p.isKilled)
                continue;
            if(p.piecePoint.isSame(point)){
                return true;
            }
        }
        return false;
    }
    boolean isFriendlyPiece(Point point,int player){
        for(Piece p:pieces){
            if(p.isKilled)
                continue;
            if(p.piecePoint.isSame(point)){
                if(p.player==player)
                    return true;
            }
        }
        return false;
    }
    boolean isEnemyPiece(Point point,int player){
        for(Piece p:pieces){
            if(p.isKilled)
                continue;
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
    void checkVictory(){
        int pieces1 = 0;
        int pieces2 = 0;
        for(Piece p:pieces){
            if(p.player==1 && p.isKilled==false)
                pieces1++;
            else if(p.player==2 && p.isKilled==false)
                pieces2++;
        }
        if(pieces1==0 && pieces2!=0){
            System.out.println("Player 2 Won");
            game_over = true;
        }
        else if(pieces2==0 && pieces1!=0){
            System.out.println("Player 1 Won");
            game_over = true;
        }
    }
    String[] player2moves(String str[]){
        for(int i=0;i<str.length;i++){
            if(str[i].equalsIgnoreCase("f"))
                str[i] = "b";
            else if(str[i].equalsIgnoreCase("b"))
                str[i] = "f";
            else if(str[i].equalsIgnoreCase("l"))
                str[i] = "r";
            else if(str[i].equalsIgnoreCase("r"))
                str[i] = "l";
        }
        return str;
    }
    void play(){
        String input,move;
        Piece piece;
        //point old,new;
        Scanner sc = new Scanner(System.in);
        String s[],stp[];
        int count=1;
        int player_turn;
        int steps;
        while(!game_over){
            player_turn = (count%2==0)?2:1;
            System.out.println("Player "+player_turn+" Move:");
            input = sc.next();
            s = input.split(":");
            piece = fetchPiece(s[0],player_turn);
            move = s[1];
            if(piece==null){
                System.out.println("Incorrect piece selected");
                continue;
            }
            if(piece.player!=player_turn){
                System.out.println(piece.player+"  "+player_turn);
                System.out.println("Choose your piece.");
                continue;
            }
            if(piece.validateMove(move)==false){
                System.out.println("Illegal Move");
                continue;
            }
            stp=piece.getSteps(move);

            if(piece.player==2)
                stp = player2moves(stp);
            
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
                if(board.isOnBoard(piece.nextPoint)==false){
                    System.out.println("Wrong Move");
                    piece.moving = false;
                    break;
                }
                if(isFriendlyPiece(piece.nextPoint, piece.player)){
                    System.out.println("Piece cannot be moved on a friendly piece");
                    piece.moving = false;
                    break;
                }
                if(isEnemyPiece(piece.nextPoint,piece.player)){
                    System.out.println(fetchPiece(piece.nextPoint).getName()+" was killed");
                    fetchPiece(piece.nextPoint).kill();
                }
                board.movePiece(piece.initialPoint,piece.nextPoint);
            }
            if(piece.moving == false)
                continue;
                
            piece.hasMoved();
            board.show();
            checkVictory();
            count++;
        }
    }
    public static void main(String args[]){
        Game g=new Game();
        g.init();
        g.play();
    }
}