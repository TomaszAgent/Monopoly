public class Utility extends Field implements Buyable{
    private final String name;
    private final int price;
    private Player owner;
    private final int mortgagePrice;
    private boolean mortgaged;

    public Utility(String name, int price, int mortgagePrice) {
        this.name = name;
        this.price = price;
        this.mortgagePrice = mortgagePrice;
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

    public boolean payRent(Player player, Pair<Integer, Integer> dices, Pair<Utility, Utility> utilities){
        if(utilities.first().getOwner() == owner && utilities.second().getOwner() == owner){
            if(player.getMoney() < (dices.first() + dices.second()) * 10){
                return false;
            }
            player.subtractMoney((dices.first() + dices.second()) * 10);
            owner.addMoney((dices.first() + dices.second()) * 10);
        } else {
            if(player.getMoney() < (dices.first() + dices.second()) * 4){
                return false;
            }
            player.subtractMoney((dices.first() + dices.second()) * 4);
            owner.addMoney((dices.first() + dices.second()) * 4);
        }
        return true;
    }
}
