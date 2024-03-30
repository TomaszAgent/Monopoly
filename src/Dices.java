import java.util.Random;

public class Dices {
    private final Random generator;

    public Dices(){
        generator = new Random();
    }
    public Pair<Integer, Integer> roll(){
        return new Pair<Integer, Integer>(generator.nextInt(7), generator.nextInt(7));
    }
}
