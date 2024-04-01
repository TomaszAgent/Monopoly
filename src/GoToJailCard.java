public class GoToJailCard extends Card {
    public GoToJailCard(){
        super("Go to jail.");
    }
    public void apply(Player player) {
        player.arrest();
    }
}
