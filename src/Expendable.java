public interface Expendable {
    public boolean buildHouse(Player player, int amount);
    public boolean buildHotel(Player player);
    public int getHousePrice();
    public int getHotelPrice();
    public boolean sellHouse(Player player, int amount);
    public boolean sellHotel(Player player);
}
