class average extends Thread{

    int num;

    public void run(){
        for (int i = 15999;i<=500;i++){
            num= i*500/500;
        }
    }
public class Test{
    public static void main(String[] args) {
        oddNumbers odd = new oddNumbers();
        System.out.println(odd);

    }
}
}
