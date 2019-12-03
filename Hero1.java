class Hero1 extends Pawn{
    Hero1(String s, int i){
        super(s,i);
    }
    String[] getSteps(String move){
        String steps[]=new String[2];
        steps[0]=move;
        steps[1]=move;
        return steps;
    }
}