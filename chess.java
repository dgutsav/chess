import java.io.*;
import java.util.*;
class chess{
    static String board[][] = new String[5][5];
    static int turn;
    static void init_board(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                board[i][j] = "-    ";
            }
        }
    }
    static void disp_board(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    static boolean validatePiece(String s){
        if(s.charAt(0)!='P' && s.charAt(0)!='H')
            return false;
        else if(((int)(s.charAt(1)))-49 >=5)
            return false;
        return true;
    }
    static boolean validateMove(String s){
        if(s.charAt(2)!=':')
            return false;
        if(validatePiece(s.substring(0,2))==false)
            return false;
        if(s.charAt(0)=='H'){
            if(s.charAt(3)!='F' && s.charAt(3)!='R' && s.charAt(3)!='L' && s.charAt(3)!='B'){
                return false;
            }
        }
        return true;
    }
    int findPiece(String p){
        int flag = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(board[i][j].indexOf(p)!=-1){
                    flag = 1;
                    return (i*5)+j;
                }
            }
        }
        if(flag==0)
            return -1;
    }
    public static void main(String args[]){
        init_board();
        Scanner sc=new Scanner(System.in);
        String input;
        turn = 1;
        System.out.println("Player1 Start:");
        input = sc.nextLine();
        
        String i1[] = input.split(",");
        for(int i=0;i<i1.length;i++){
            //System.out.println(i1[i]);
            i1[i].trim();
        }
        for(int i=0;i<i1.length;i++){
            if(validatePiece(i1[i]))
                board[4][i]="A-"+i1[i]+" ";
        }
        disp_board();
        turn = 2;
        System.out.println("Player2 Start:");
        input = sc.nextLine();
       
        String i2[] = input.split(",");
        for(int i=0;i<i2.length;i++){
            i2[i].trim();
        }
        for(int i=0;i<i2.length;i++){
            if(validatePiece(i2[i]))
                board[0][i]="B-"+i2[i]+" ";
        }
        disp_board();

        
    }
    public void move_piece(String s){
        if(validatePiece(s.substring(0,2))==false){
            System.out.println("Illegal Piece");
            return;
        }
        String piece = s.substring(0,2);
        int fp = findPiece(piece);
        int p_x = fp%5;
        int p_y = fp/5;
        
    }
}