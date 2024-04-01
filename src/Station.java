public class Station extends Field implements Buyable{
    private final String name;
    private final int price;
    private Player owner;
    private final int mortgagePrice;
    private boolean mortgaged;
    private final int baseRent;

    public Station(String name, int price, int mortgagePrice, int baseRent){
        this.name = name;
        this.price = price;
        this.mortgagePrice = mortgagePrice;
        this.baseRent = baseRent;
        owner = null;
        mortgaged = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMortgagePrice() {
        return mortgagePrice;
    }

    @Override
    public boolean isMortgaged() {
        return mortgaged;
    }

    public int getBaseRent() {
        return baseRent;
    }

    @Override
    public boolean buy(Player player){
        if(owner == null || player.getMoney() < price){
            return false;
        }
        player.subtractMoney(price);
        owner = player;
        return true;
    }

    public boolean buy(Player player, int price){
        if(owner == null || player.getMoney() < price){
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
        if(player != owner){
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
        owner.subtractMoney((int) (mortgagePrice * 1.1));
        mortgaged = false;
        return true;
    }

    public boolean payRent(Player player, Station[] stations){
        int stationsCount = 0;
        for(Station station : stations){
            if(station.getOwner() == owner){
                stationsCount++;
            }
        }
        if(player.getMoney() < stationsCount * baseRent){
            return false;
        }
        player.subtractMoney(stationsCount * baseRent);
        owner.addMoney(stationsCount * baseRent);
        return true;
    }
}
