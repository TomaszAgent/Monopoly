import java.util.ArrayList;

public class Player {
    private final String name;
    private int money;
    private int currentField;
    private boolean playing;
    private boolean arrested;
    private int freeJails;
    private ArrayList<Field> ownedFields;
    private int waits;

    public Player(String name){
        this.name = name;
        money = 1500;
        currentField = 0;
        playing = true;
        arrested = false;
        freeJails = 0;
        ownedFields = new ArrayList<Field>();
        waits = 0;
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

    public int getFreeJails() {
        return freeJails;
    }

    public ArrayList<Field> getOwnedFields() {
        return ownedFields;
    }

    public void lost(){
        playing = false;
    }

    public void move(int spaces){
        currentField = (currentField + spaces) % 40;
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
        waits = 0;
    }
    public void addFreeJail(){
        freeJails++;
    }
    public void waitsInJail(){
        waits++;
    }
    public boolean useFreeJail(){
        if(!arrested || freeJails == 0){
            return false;
        }
        this.setFree();
        freeJails--;
        return true;
    }

    public void addField(Field field){
        ownedFields.add(field);
    }
}
