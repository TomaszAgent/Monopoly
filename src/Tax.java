public class Tax extends Field{
    private final String name;
    private final int taxAmount;

    public Tax(String name, int taxAmount) {
        this.name = name;
        this.taxAmount = taxAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getTaxAmount() {
        return taxAmount;
    }
}
