public class Street extends Field implements Buyable, Expendable{
    private final String name;
    private final int price;
    private int houses;
    private final int housePrice;
    private final int hotelPrice;
    private Player owner;
    private final ImmutableMap<Integer, Integer> rent;

    public Street(String name, int price, int housePrice, int hotelPrice, ImmutableMap<Integer, Integer> rent){
        this.name = name;
        this.price = price;
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
        this.rent = rent;
        houses = 0;
        owner = null;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public int getHouses() {
        return houses;
    }

    @Override
    public int getHotelPrice() {
        return hotelPrice;
    }

    public ImmutableMap<Integer, Integer> getRent() {
        return rent;
    }

    public boolean buy(Player player){
        if(owner == null || player.getMoney() < price){
            return false;
        }
        player.subtractMoney(price);
        owner = player;
        return true;
    }

    @Override
    public boolean buildHouse(Player player, int amount){
        if(player != owner || houses + amount > 4 || housePrice * amount > player.getMoney()){
            return false;
        }
        player.subtractMoney(amount * housePrice);
        houses += amount;
        return true;
    }

    @Override
    public boolean buildHotel(Player player){
        if(player != owner || houses != 4 || hotelPrice > player.getMoney()){
            return false;
        }
        player.subtractMoney(hotelPrice);
        houses++;
        return true;
    }

    @Override
    public boolean sellHouse(Player player, int amount){
        if(player != owner || houses == 5 || amount > houses){
            return false;
        }
        player.addMoney(amount * housePrice);
        houses -= amount;
        return true;
    }

    @Override
    public boolean sellHotel(Player player){
        if(player != owner || houses != 5){
            return false;
        }
        player.addMoney(hotelPrice);
        houses--;
        return true;
    }

    public boolean payRent(Player player){
        if(owner == null || rent.get(houses) > player.getMoney()){
            return false;
        }
        player.subtractMoney(rent.get(houses));
        owner.addMoney(rent.get(houses));
        return true;
    }
}
