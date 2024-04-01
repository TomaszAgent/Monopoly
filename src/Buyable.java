public interface Buyable {
    public int getPrice();
    public boolean buy(Player player);
    public boolean buy(Player player, int price);
}
