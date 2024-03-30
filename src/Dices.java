import java.util.Random;

public class Dices {
    private final Random generator;

    public Dices(){
        generator = new Random();
    }
    public int roll(){
        return generator.nextInt(7) + generator.nextInt(7);
    }
}
