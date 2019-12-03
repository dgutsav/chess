class Pawn extends Piece{
    Pawn(String s, int x){
        super(s,x);
    }
    boolean validateMove(String move){
        move = move.toLowerCase();
        if(move.equals("f")||move.equals("b")||move.equals("l")||move.equals("r"))
            return true;
        else
            return false;    
    }
    String[] getSteps(String move){
        String steps[]=new steps[1];
        steps[0]=move;
        return steps;
    }
}