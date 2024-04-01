public abstract class Card implements Functional {
    private final String description;

    public Card(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
