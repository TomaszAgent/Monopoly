public class FreeParking extends FunctionalField{
    public FreeParking(String name) {
        super(name);
    }

    @Override
    public void apply(Player player) {
        player.addMoney(50);
        System.out.printf("Enjoy your free parking and 50 dollars %s", player.getName());
    }
}
