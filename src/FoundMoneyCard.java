public class FoundMoneyCard extends Card {
    public FoundMoneyCard() {
        super("You found 100 dollars on the ground.");
    }

    @Override
    public void apply(Player player) {
        player.addMoney(100);
    }
}
