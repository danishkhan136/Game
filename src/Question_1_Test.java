class oddNumbers extends Thread{
    int num;

    @Override
    public void run() {
        for (int i=0;i<=100;i++){
            num = (2*i)+10000;
            Squares s = new Squares(num);
            s.start();
            try {
                oddNumbers.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Squares extends Thread{
    int num;

    Squares(int num){
        this.num = num;
    }

    int square;
    @Override
    public void run() {
        square = num * num;
        System.out.println(num + " Square is: " + square);

    }
}

public class Question_1_Test {
    public static void main(String[] args) {
        oddNumbers n = new oddNumbers();
        n.start();
    }
}
