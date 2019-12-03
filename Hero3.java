class Hero3 extends Piece{
    Hero3(String s, int i){
        super(s,i);
    }
    boolean validateMove(String move){
        move = move.toLowerCase();
        if(move.equals("fl")||move.equals("bl")||move.equals("br")||move.equals("fr"))
            return true;
        else if(move.equals("lf")||move.equals("lb")||move.equals("rb")||move.equals("rf"))
            return true;
        else
            return false;    
    }
    String[] getSteps(String move){
        String steps[] = new String[3];
        steps[0] = move.substring(0,1);
        steps[1] = steps[0];
        steps[2] = move.substring(1);
        return steps;
    }
}