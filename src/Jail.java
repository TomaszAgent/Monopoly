public class Jail extends FunctionalField{
    public Jail(String name) {
        super(name);
    }

    @Override
    public void apply(Player player) {
        System.out.printf("Welcome in jail %s%n", player.getName());
    }
}
