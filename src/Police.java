public class Police extends FunctionalField{
    public Police(String name) {
        super(name);
    }

    public void apply(Player player){
        player.arrest();
    }
}
