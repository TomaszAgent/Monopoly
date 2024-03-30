public class Player {
    private final String name;
    private int money;
    private int currentField;
    private boolean isPlaying;

    public Player(String name){
        this.name = name;
        money = 1500;
        currentField = 0;
        isPlaying = true;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getCurrentField() {
        return currentField;
    }

    public void lost(){
        isPlaying = false;
    }

    public void Move(int spaces){
        currentField += spaces;
    }

    public void addMoney(int quantity){
        money += quantity;
    }

    public void subtractMoney(int quantity){
        money -= quantity;
    }
}
