class Hero2 extends Piece{
    Hero2(String s, int i){
        super(s,i);
    }
    boolean validateMove(String move){
        move = move.toLowerCase();
        if(move.equals("fl")||move.equals("bl")||move.equals("br")||move.equals("fr"))
            return true;
        else
            return false;    
    }
    String[] getSteps(String move){
        String steps[]=new String[2];
        steps[0]=move;
        steps[1]=move;
        return steps;
    }
}