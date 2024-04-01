public class Street extends Field implements Buyable, Expendable{
    private final String name;
    private final int price;
    private int houses;
    private final int housePrice;
    private final int hotelPrice;
    private Player owner;
    private final ImmutableMap<Integer, Integer> rent;
    private final String colour;
    private boolean mortgaged;
    private final int mortgagePrice;

    public Street(String name, int price, int housePrice, int hotelPrice, ImmutableMap<Integer, Integer> rent, String colour, int mortgagePrice){
        this.name = name;
        this.price = price;
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
        this.rent = rent;
        this.colour = colour;
        this.mortgagePrice = mortgagePrice;
        houses = 0;
        owner = null;
        mortgaged = false;
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

    public String getColour() {
        return colour;
    }

    @Override
    public boolean isMortgaged() {
        return mortgaged;
    }

    @Override
    public int getMortgagePrice() {
        return mortgagePrice;
    }

    public boolean buy(Player player){
        if(owner != null || player.getMoney() < price){
            return false;
        }
        player.subtractMoney(price);
        owner = player;
        return true;
    }

    public boolean buy(Player player, int price){
        if(owner != null || player.getMoney() < price){
            return false;
        }
        player.subtractMoney(price);
        owner = player;
        return true;
    }

    public boolean sell(Player player, int price){
        if(player.getMoney() < price){
            return false;
        }
        player.subtractMoney(price);
        owner.addMoney(price);
        owner = player;
        return true;
    }

    public boolean mortgage(Player player){
        if(player != owner || houses != 0){
            return false;
        }
        owner.addMoney(mortgagePrice);
        mortgaged = true;
        return true;
    }

    public boolean payMortgage(Player player){
        if(player != owner || player.getMoney() < mortgagePrice * 1.1){
            return false;
        }
        player.subtractMoney((int) (mortgagePrice * 1.1));
        mortgaged = false;
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

    public boolean payRent(Player player, ImmutableMap<String, Neighbourhood> neighbourhoods){
        int toPay = rent.get(houses);
        Neighbourhood neighbourhood = neighbourhoods.get(colour);
        if(
                neighbourhood.first().getOwner() == owner &&
                neighbourhood.second().getOwner() == owner &&
                neighbourhood.third().getOwner() == owner
        ){
            toPay *= 2;
        }
        if(owner == null || toPay > player.getMoney()){
            return false;
        }
        player.subtractMoney(toPay);
        owner.addMoney(toPay);
        return true;
    }
}
