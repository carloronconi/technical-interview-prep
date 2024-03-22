package questions.scratch;

public class Scratch implements Runnable {
    @Override
    public void run() {
        System.out.println("Ciao");
    }


    public static void main(String[] args) {
        Scratch scratch = new Scratch();
        scratch.run();
    }
}
