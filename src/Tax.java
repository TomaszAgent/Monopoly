public class Tax extends Field{
    private final String name;
    private final int taxAmount;
    private final String type;

    public Tax(String name, int taxAmount) {
        this.name = name;
        this.taxAmount = taxAmount;
        type = "tax";
    }

    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getTaxAmount() {
        return taxAmount;
    }

    public boolean payTax(Player player){
        if(player.getMoney() < taxAmount){
            return false;
        }
        player.subtractMoney(taxAmount);
        return true;
    }
}
