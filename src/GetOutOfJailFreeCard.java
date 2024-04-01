public class GetOutOfJailFreeCard extends Card {
    public GetOutOfJailFreeCard() {
        super("Get out of jail for free.");
    }

    @Override
    public void apply(Player player) {
        player.addFreeJail();
    }
}
