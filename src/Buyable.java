public interface Buyable {
    public Player getOwner();
    public int getPrice();
    public boolean isMortgaged();
    public int getMortgagePrice();
    public boolean buy(Player player);
    public boolean buy(Player player, int price);
    public boolean sell(Player player, int price);
    public boolean mortgage(Player player);
    public boolean payMortgage(Player player);
}
