public interface Buyable {
    public int getPrice();
    public boolean isMortgaged();
    public int getMortgagePrice();
    public boolean buy(Player player);
    public boolean buy(Player player, int price);
    public boolean mortgage();
    public boolean payMortgage();
}
