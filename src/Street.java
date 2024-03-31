public class Street extends Field implements Buyable, Expendable{
    private final String name;
    private final int price;
    private int houses;
    private boolean hotel;

    public Street(String name, int price){
        this.name = name;
        this.price = price;
        houses = 0;
        hotel = false;
    }
}
