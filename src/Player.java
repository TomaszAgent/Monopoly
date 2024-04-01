public class Player {
    private final String name;
    private int money;
    private int currentField;
    private boolean playing;
    private boolean arrested;

    public Player(String name){
        this.name = name;
        money = 1500;
        currentField = 0;
        playing = true;
        arrested = false;
    }

    public boolean isArrested() {
        return arrested;
    }

    public boolean isPlaying() {
        return playing;
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
        playing = false;
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

    public void arrest(){
        arrested = true;
        currentField = 10;
    }

    public void setFree(){
        arrested = false;
    }
}
