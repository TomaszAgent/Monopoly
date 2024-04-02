import java.util.Random;

public class Dices {
    public static Pair<Integer, Integer> roll(){
        Random generator = new Random();
        return new Pair<>(generator.nextInt(6) + 1, generator.nextInt(6) + 1);
    }
}
