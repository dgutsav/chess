import java.util.*;
class Test{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("pp");
        String in = sc.nextLine();
        String p[] = in.split(",");
        for(String s:p){
            System.out.println(s);
        }
    }
}