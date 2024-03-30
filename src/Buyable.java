public interface Buyable {
    public int getPrice();
    public boolean buy(Player player);
    public int getRent();
    public boolean payRent(Player player);
}
