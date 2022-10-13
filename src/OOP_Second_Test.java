class Table extends Thread{
    @Override
    public void run() {
        super.run();
        int numa = 33;
        int numb = 59;
        int numc = 97;
        tableOf33(numa);
        tableOf59(numb);
        tableOf97(numc);
    }

    public void tableOf33(int numa){
        for (int i=1;i<=10000;i++){
            System.out.println(numa + " * " + i + " = " + numa*i);
        }
    }

    public void tableOf59(int numb){
        for (int i=1;i<=10000;i++){
            System.out.println(numb + " * " + i + " = " + numb*i);
        }
    }


    public void tableOf97(int numc){
        for (int i=1;i<=10000;i++){
            System.out.println(numc + " * " + i + " = " + numc*i);
        }
    }

}

public class OOP_Second_Test {
    public static void main(String[] args) {
        Table table = new Table();
        table.start();
    }
}
