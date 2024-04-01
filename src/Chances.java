import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chances extends Field implements ProvidesCard{
    private final Card[] cards;
    private int currentCard;
    public Chances(Card[] cards){
        List<Card> temp = Arrays.asList(cards);
        Collections.shuffle(temp);
        temp.toArray(cards);
        this.cards = cards;
        currentCard = 0;
    }

    @Override
    public String getName() {
        return "chances";
    }

    public String getType() {
        return "cards";
    }

    @Override
    public Card DrawCard() {
        Card card = cards[currentCard];
        currentCard = (currentCard + 1) % cards.length;
        return card;
    }
}
