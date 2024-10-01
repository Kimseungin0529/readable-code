package cleancode.minesweeper.tobe;

public class Test {
    public static void main(String[] args) {
        for(int i=0; i<20; i++){
            doSomethingWithI(i);
        }
    }

    private static void doSomethingWithI(int i) {
        for(int j=0; j<30; j++){
            doSomethingWithIJ(i, j);
        }
    }

    private static void doSomethingWithIJ(int i, int j) {
        if(i >15 && j >20){
            doSomething();
        }
    }
}
