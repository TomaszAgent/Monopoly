public class SoldClothesCard extends Card{
    public SoldClothesCard() {
        super("You've sold some old clothes. Get 50 dollars.");
    }

    @Override
    public void apply(Player player) {
        player.addMoney(50);
    }
}
