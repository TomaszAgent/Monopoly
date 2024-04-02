import java.util.*;
import java.util.stream.Stream;

public class Game {
    private static void sellHouse(Player player, Scanner input){
        List<Field> availableStreets = player.getOwnedFields().stream().filter(field -> field instanceof Street).filter(street -> ((Street) street).getHouses() > 0 && ((Street) street).getHouses() < 4 ).toList();
        for(int i = 0; i < availableStreets.size(); i++){
            System.out.printf("%d - Sell up to %d houses at %s\n", i, ((Street) availableStreets.get(i)).getHouses(), availableStreets.get(i).getName());
        }
        while(true) {
            System.out.println("Select street:");
            String answer = input.nextLine();
            int option = -1;
            try {
                option = Integer.parseInt(answer);
            } catch (NumberFormatException ignored) {}
            if(option > 0 && option < availableStreets.size()){
                while(true){
                    System.out.println("Amount of houses:");
                    String housesStr = input.nextLine();
                    int houses = -1;
                    try {
                        houses = Integer.parseInt(housesStr);
                    } catch (NumberFormatException ignored) {}
                    if(houses > 0 && houses <= ((Street) availableStreets.get(option)).getHouses()){
                        ((Street) availableStreets.get(option)).sellHouse(player, houses);
                        return;
                    }
                }
            }
        }
    }

    private static void sellHotel(Player player, Scanner input){
        List<Field> availableStreets = player.getOwnedFields().stream().filter(field -> field instanceof Street).filter(street -> ((Street) street).getHouses() == 5).toList();
        for(int i = 0; i < availableStreets.size(); i++){
            System.out.printf("%d - Sell hotel at %s\n", i, availableStreets.get(i).getName());
        }
        while(true) {
            System.out.println("Select street:");
            String answer = input.nextLine();
            int option = -1;
            try {
                option = Integer.parseInt(answer);
            } catch (NumberFormatException ignored) {}
            if(option > 0 && option < availableStreets.size()){
                ((Street) availableStreets.get(option)).sellHotel(player);
                return;
            }
        }
    }

    private static void sellField(Player player, Scanner input, Player[] players){
        List<Field> notMortgagedFields = player.getOwnedFields().stream().filter(field -> !((Buyable) field).isMortgaged()).toList();
        List<Field> availableNonStreets = notMortgagedFields.stream().filter(field -> !(field instanceof Street)).toList();
        List<Field> availableStreets = notMortgagedFields.stream().filter(field -> field instanceof Street).filter(field -> ((Street) field).getHouses() == 0).toList();
        List<Field> availableFields = Stream.concat(availableStreets.stream(), availableNonStreets.stream()).toList();
        for(int i = 0; i < availableFields.size(); i++){
            System.out.printf("%d - Sell %s\n", i, availableStreets.get(i).getName());
        }
        while(true) {
            System.out.println("Select field:");
            String answer = input.nextLine();
            int option = -1;
            try {
                option = Integer.parseInt(answer);
            } catch (NumberFormatException ignored) {
            }
            if (option > 0 && option < availableFields.size()) {
                while(true){
                    System.out.println("Sell to:");
                    String player2 = input.nextLine();
                    if(Arrays.stream(players).map(Player::getName).toList().contains(player2)){
                        Player player2Obj = Arrays.stream(players).filter(e -> Objects.equals(e.getName(), player2)).findFirst().get();
                        while(true){
                            System.out.println("For how much:");
                            String priceStr = input.nextLine();
                            int price = -1;
                            try {
                                price = Integer.parseInt(priceStr);
                                ((Buyable) availableFields.get(option)).sell(player2Obj, price);
                                return;
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    }
                }
            }
        }
    }

    private static void mortgageField(Player player, Scanner input){
        List<Field> notMortgagedFields = player.getOwnedFields().stream().filter(field -> !((Buyable) field).isMortgaged()).toList();
        for(int i = 0; i < notMortgagedFields.size(); i++){
            System.out.printf("%d - Mortgage %s for %d\n", i, notMortgagedFields.get(i).getName(), ((Buyable)notMortgagedFields.get(i)).getMortgagePrice());
        }
        while(true) {
            System.out.println("Select field:");
            String answer = input.nextLine();
            int option = -1;
            try {
                option = Integer.parseInt(answer);
            } catch (NumberFormatException ignored) {
            }
            if (option > 0 && option < notMortgagedFields.size()) {
                ((Buyable) notMortgagedFields.get(option)).mortgage(player);
            }
        }
    }


    public static boolean getMoney(Player player, PriorityQueue<Player> result, Scanner input, Player[] players){
        if(player.getOwnedFields().isEmpty()){
            player.lost();
            System.out.printf("%s lost\n", player.getName());
            result.add(player);
            return false;
        }
        boolean[] options = new boolean[3];
        if(!player.getOwnedFields().stream().filter(field -> field instanceof Street).filter(street -> ((Street) street).getHouses() > 0).toList().isEmpty()){
            options[0] = true;
            // can sell house
        }
        if(
            !player.getOwnedFields().stream().filter(field -> !(((Buyable) field).isMortgaged())).filter(field -> field instanceof Street).filter(street -> ((Street) street).getHouses() == 0).toList().isEmpty() ||
            !player.getOwnedFields().stream().filter(field -> !(((Buyable) field).isMortgaged())).filter(field -> !(field instanceof Street)).toList().isEmpty()
        ){
            options[1] = true;
            // can sell or mortgage field
        }
        if(!player.getOwnedFields().stream().filter(field -> field instanceof Street).filter(street -> ((Street) street).getHouses() == 5).toList().isEmpty()){
            options[2] = true;
            // can sell hotel
        }
        System.out.println("Choose one:");
        if(options[0]){
            System.out.println("0 - sell a house");
        }
        if(options[1]){
            System.out.println("1 - sell a field to other player");
            System.out.println("2 - mortgage a field");
        }
        if(options[2]){
            System.out.println("3 - sell a hotel");
        }
        while(true){
            String answer = input.nextLine();
            int option = -1;
            try{
                option = Integer.parseInt(answer);
            }catch(NumberFormatException ignored){
                System.out.println("invalid");
            }
            switch (option){
                case 0:
                    if(options[0]) {
                        sellHouse(player, input);
                        return true;
                    }
                case 1:
                    if(options[1]){
                        sellField(player, input, players);
                        return true;
                    }
                case 2:
                    if(options[1]) {
                        mortgageField(player, input);
                        return true;
                    }
                case 3:
                    if(options[2]){
                        sellHotel(player, input);
                        return true;
                    }
            }
        }
    }

    private static void buildHouses(Player player, Scanner input){
        List<Street> availableStreets = player.getOwnedFields().stream().filter(e -> e instanceof Street).filter(e -> ((Street) e).getHouses() < 5).map(e -> (Street) e).toList();
        if(availableStreets.isEmpty()){
            System.out.println("You can't");
            return;
        }
        for(int i = 0; i < availableStreets.size(); i++){
            System.out.printf("%d - Buy up to %d houses at %s\n", i, 4 - availableStreets.get(i).getHouses(), availableStreets.get(i).getName());
        }
        while(true) {
            System.out.println("Select street:");
            String answer = input.nextLine();
            int option = -1;
            try {
                option = Integer.parseInt(answer);
            } catch (NumberFormatException ignored) {
            }
            if (option > 0 && option < availableStreets.size()) {
                Street street = availableStreets.get(option);
                while(true) {
                    System.out.println("How many?");
                    String amountStr = input.nextLine();
                    int amount = -1;
                    try {
                        amount = Integer.parseInt(answer);
                    } catch (NumberFormatException ignored) {
                    }
                    if(amount > 0 && amount + street.getHouses() < 5){
                        if(street.buildHouse(player, amount)){
                            System.out.println("Built!");
                        }else{
                            System.out.println("Too poor :c");
                        }
                        return;
                    }
                }
            }
        }
    }

    private static void buildHotel(Player player, Scanner input){
        List<Street> availableStreets = player.getOwnedFields().stream().filter(e -> e instanceof Street).filter(e -> ((Street) e).getHouses() == 5).map(e -> (Street) e).toList();
        if(availableStreets.isEmpty()){
            System.out.println("You can't");
            return;
        }
        for(int i = 0; i < availableStreets.size(); i++){
            System.out.printf("%d - Build a hotel at %s\n", i, availableStreets.get(i).getName());
        }
        while(true) {
            System.out.println("Select street:");
            String answer = input.nextLine();
            int option = -1;
            try {
                option = Integer.parseInt(answer);
            } catch (NumberFormatException ignored) {
            }
            if (option > 0 && option < availableStreets.size()) {
                Street street = availableStreets.get(option);
                if(street.buildHotel(player)){
                    System.out.println("Built!");
                }else{
                    System.out.println("Too poor :c");
                }
            }
        }
    }

    public static void play(){
        final Player[] players = new Player[5];
        final PriorityQueue<Player> result = new PriorityQueue<Player>();
        final Board board = new Board();
        final Scanner input = new Scanner(System.in);
        int currentPlayer = 1;
        int playersAmount = 0;
        while(playersAmount < 2 || playersAmount > 4){
            System.out.println("Enter players amount (It must be a number between 2 and 4):");
            String answer = input.nextLine();
            try{
                playersAmount = Integer.parseInt(answer);
            } catch (NumberFormatException ignored) {}
        }
        for(int i = 1; i <= playersAmount; i++){
            System.out.printf("Enter player %d name:\n", i);
            players[i] = new Player(input.nextLine());
        }
        while(true){
            if(result.size() == playersAmount - 1){
                break;
            }
            Player player = players[currentPlayer];
            if(!player.isPlaying()){
                currentPlayer = (currentPlayer % playersAmount) + 1;
            }
            if(player.isArrested()){
                System.out.printf("%s is in jail", player.getName());
                System.out.println("anything - try to roll double (or wait 3 turns)");
                if(player.getMoney() >= 50) {
                    System.out.println("1 - pay 50$");
                }
                if(player.getFreeJails() > 0){
                    System.out.println("2 - use get out of jail free card");
                }
                System.out.println("Select option:");
                String selected = input.nextLine();
                switch (selected) {
                    case "1":
                        if(player.getMoney() >= 50) {
                            player.subtractMoney(50);
                            player.setFree();
                            currentPlayer--;
                        }
                        break;
                    case "2":
                        if(player.getFreeJails() > 0){
                            player.useFreeJail();
                            currentPlayer--;
                        }
                        break;
                }
                if(player.isArrested()){
                    Pair<Integer, Integer> dices = Dices.roll();
                    if(dices.first() == dices.second()){
                        player.setFree();
                        currentPlayer--;
                    } else {
                        player.waitsInJail();
                    }
                }
                currentPlayer = (currentPlayer % playersAmount) + 1;
            }
            System.out.printf("%s`s turn\n", player.getName());
            Pair<Integer, Integer> rolled = Dices.roll();
            if(player.getCurrentField() + rolled.first() + rolled.second() > 40){
                player.addMoney(200);
            }
            if(rolled.first().equals(rolled.second())){
                currentPlayer--;
            }
            player.move(rolled.first() + rolled.second());
            System.out.printf("%s rolled %d and %d\n", player.getName(), rolled.first(), rolled.second());
            Field currentField = board.get(player.getCurrentField());
            System.out.printf("%s stopped at %s\n", player.getName(), currentField.getName());
            switch(currentField.getType()){
                case "cards":
                    Card card = ((ProvidesCard) currentField).drawCard();
                    System.out.printf("You drew - %s\n", card.getDescription());
                    card.apply(player);
                    break;
                case "tax":
                    System.out.printf("You have to pay %d\n", ((Tax) currentField).getTaxAmount());
                    while(!((Tax) currentField).payTax(player)){
                        System.out.println("You don't have enough money");
                        if(!getMoney(player, result, input, players)){
                            break;
                        }
                    }
                    break;
                case "station":
                    Station field = (Station) currentField;
                    if(field.getOwner() != null){
                        System.out.printf("You have to pay %d\n", field.getRent(board.getStations()));
                        while(!field.payRent(player, board.getStations())){
                            System.out.println("You don't have enough money");
                            if(!getMoney(player, result, input, players)){
                                break;
                            }
                        }
                    } else{
                        while(true){
                            System.out.println("Buy? y - yes; n - no");
                            String answer = input.nextLine();
                            if(Objects.equals(answer, "y")){
                                if(!field.buy(player)){
                                    System.out.println("To poor");
                                } else {
                                    System.out.println("Bought!");
                                }
                                break;
                            } else if(Objects.equals(answer, "n")) {
                                System.out.println("Ok");
                                break;
                            }
                        }
                    }
                    break;
                case "utility":
                    Utility utilityField = (Utility) currentField;
                    if(utilityField.getOwner() != null){
                        System.out.printf("You have to pay %d\n", utilityField.getRent(board.getUtilities(), rolled));
                        while(!utilityField.payRent(player, rolled, board.getUtilities())){
                            System.out.println("You don't have enough money");
                            if(!getMoney(player, result, input, players)){
                                break;
                            }
                        }
                    } else {
                        while(true){
                            System.out.println("Buy? y - yes; n - no");
                            String answer = input.nextLine();
                            if(Objects.equals(answer, "y")){
                                if(!utilityField.buy(player)){
                                    System.out.println("To poor");
                                } else {
                                    System.out.println("Bought!");
                                }
                                break;
                            } else if(Objects.equals(answer, "n")) {
                                System.out.println("Ok");
                                break;
                            }
                        }
                    }
                    break;
                case "functional":
                    ((FunctionalField) currentField).apply(player);
                    break;
                case "street":
                    Street street = (Street) currentField;
                    if(street.getOwner() == null){
                        while(true) {
                            System.out.println("Buy? y - yes; n - no");
                            String answer = input.nextLine();
                            if (Objects.equals(answer, "y")) {
                                if (!street.buy(player)) {
                                    System.out.println("To poor");
                                } else {
                                    System.out.println("Bought!");
                                }
                                break;
                            } else if (Objects.equals(answer, "n")) {
                                System.out.println("Ok");
                                break;
                            }
                        }
                    } else {
                        if(street.getOwner() != player){
                            System.out.printf("You have to pay %d\n", street.getRent(board.getNeighbourhoods()));
                            while(!street.payRent(player, board.getNeighbourhoods())){
                                System.out.println("You don't have enough money!");
                                if(!getMoney(player, result, input, players)){
                                    break;
                                }
                            }
                        }
                    }
                    break;
            }
            if(!player.isArrested()) {
                System.out.println("anything - continue");
                System.out.println("1 - get some money");
                System.out.println("2 - build some houses");
                System.out.println("3 - build a hotel");
                System.out.println("Select option:");
                String answer = input.nextLine();
                switch (answer) {
                    case "1":
                        getMoney(player, result, input, players);
                        break;
                    case "2":
                        buildHouses(player, input);
                        break;
                    case "3":
                        buildHotel(player, input);
                }
            }
            currentPlayer = (currentPlayer % playersAmount) + 1;
            System.out.println();
        }
    }
}
