public class Board {
    private final Field[] board;
    private final ImmutableMap<String, Street[]> neighbourhoods;
    private final Station[] stations;
    private final Pair<Utility, Utility> utilities;
    public Board(){
        Start start = new Start();
        CommunityChest communityChest = new CommunityChest(new Card[]{
                new FoundMoneyCard(),
                new SoldClothesCard()
        });
        Chances chances = new Chances(new Card[]{
                new GoToJailCard(),
                new GetOutOfJailFreeCard()
        });
        Jail jail = new Jail("jail");
        FreeParking freeParking = new FreeParking("free parking");
        Police police = new Police("police");
        Utility electricity = new Utility("electricity", 150, 60);
        Utility waterWorks = new Utility("water works", 150, 60);
        utilities = new Pair<>(electricity, waterWorks);
        Station readingStation = new Station("reading railroad station", 200, 70, 50);
        Station pensylvaniaStation = new Station("reading railroad station", 200, 70, 50);
        Station BOStation = new Station("B. & O. station", 200, 70, 50);
        Station shortStation = new Station("short line station", 200, 70, 50);
        stations = new Station[]{
                readingStation,
                pensylvaniaStation,
                BOStation,
                shortStation
        };
        Tax incomeTax = new Tax("income tax", 200);
        Tax luxuryTax = new Tax("luxury tax", 100);
        Street mediterraneanAvenue = new Street("Mediterranean Avenue", 60, 50, 50, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 10),
                new Pair<>(1, 15),
                new Pair<>(2, 20),
                new Pair<>(3, 25),
                new Pair<>(4, 40)
        }), "brown", 25);

        Street balticAvenue = new Street("Baltic Avenue", 60, 50, 50, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 10),
                new Pair<>(1, 15),
                new Pair<>(2, 20),
                new Pair<>(3, 25),
                new Pair<>(4, 40)
        }), "brown", 25);

        Street orientalAvenue = new Street("Oriental Avenue", 100, 50, 50, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 10),
                new Pair<>(1, 20),
                new Pair<>(2, 30),
                new Pair<>(3, 40),
                new Pair<>(4, 50)
        }), "light blue", 50);

        Street vermontAvenue = new Street("Vermont Avenue", 100, 50, 50, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 10),
                new Pair<>(1, 20),
                new Pair<>(2, 30),
                new Pair<>(3, 40),
                new Pair<>(4, 50)
        }), "light blue", 50);

        Street connecticutAvenue = new Street("Connecticut Avenue", 100, 50, 50, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 10),
                new Pair<>(1, 20),
                new Pair<>(2, 30),
                new Pair<>(3, 40),
                new Pair<>(4, 50)
        }), "light blue", 50);

        Street stCharlesPlace = new Street("St. Charles Place", 120, 60, 60, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 20),
                new Pair<>(1, 30),
                new Pair<>(2, 40),
                new Pair<>(3, 50),
                new Pair<>(4, 60)
        }), "pink", 70);

        Street statesAvenue = new Street("States Avenue", 120, 60, 60, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 20),
                new Pair<>(1, 30),
                new Pair<>(2, 40),
                new Pair<>(3, 50),
                new Pair<>(4, 60)
        }), "pink", 70);

        Street virginiaAvenue = new Street("Virginia Avenue", 120, 60, 60, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 20),
                new Pair<>(1, 30),
                new Pair<>(2, 40),
                new Pair<>(3, 50),
                new Pair<>(4, 60)
        }), "pink", 70);

        Street stJamesPlace = new Street("St. James Place", 140, 70, 70, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 30),
                new Pair<>(1, 40),
                new Pair<>(2, 50),
                new Pair<>(3, 60),
                new Pair<>(4, 70)
        }), "orange", 90);

        Street tennesseeAvenue = new Street("Tennessee Avenue", 140, 70, 70, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 30),
                new Pair<>(1, 40),
                new Pair<>(2, 50),
                new Pair<>(3, 60),
                new Pair<>(4, 70)
        }), "orange", 90);

        Street newYorkAvenue = new Street("New York Avenue", 140, 70, 70, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 30),
                new Pair<>(1, 40),
                new Pair<>(2, 50),
                new Pair<>(3, 60),
                new Pair<>(4, 70)
        }), "orange", 90);

        Street kentuckyAvenue = new Street("Kentucky Avenue", 160, 80, 80, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 40),
                new Pair<>(1, 50),
                new Pair<>(2, 60),
                new Pair<>(3, 70),
                new Pair<>(4, 80)
        }), "red", 110);

        Street indianaAvenue = new Street("Indiana Avenue", 160, 80, 80, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 40),
                new Pair<>(1, 50),
                new Pair<>(2, 60),
                new Pair<>(3, 70),
                new Pair<>(4, 80)
        }), "red", 110);

        Street illinoisAvenue = new Street("Illinois Avenue", 160, 80, 80, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 40),
                new Pair<>(1, 50),
                new Pair<>(2, 60),
                new Pair<>(3, 70),
                new Pair<>(4, 80)
        }), "red", 110);

        Street atlanticAvenue = new Street("Atlantic Avenue", 180, 90, 90, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 50),
                new Pair<>(1, 60),
                new Pair<>(2, 70),
                new Pair<>(3, 80),
                new Pair<>(4, 90)
        }), "yellow", 130);

        Street ventnorAvenue = new Street("Ventnor Avenue", 180, 90, 90, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 50),
                new Pair<>(1, 60),
                new Pair<>(2, 70),
                new Pair<>(3, 80),
                new Pair<>(4, 90)
        }), "yellow", 130);

        Street marvinGardens = new Street("Marvin Gardens", 180, 90, 90, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 50),
                new Pair<>(1, 60),
                new Pair<>(2, 70),
                new Pair<>(3, 80),
                new Pair<>(4, 90)
        }), "yellow", 130);

        Street pacificAvenue = new Street("Pacific Avenue", 200, 100, 100, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 60),
                new Pair<>(1, 70),
                new Pair<>(2, 80),
                new Pair<>(3, 90),
                new Pair<>(4, 100)
        }), "green", 150);

        Street northCarolinaAvenue = new Street("North Carolina Avenue", 200, 100, 100, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 60),
                new Pair<>(1, 70),
                new Pair<>(2, 80),
                new Pair<>(3, 90),
                new Pair<>(4, 100)
        }), "green", 150);

        Street pennsylvaniaAvenue = new Street("Pennsylvania Avenue", 200, 100, 100, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 60),
                new Pair<>(1, 70),
                new Pair<>(2, 80),
                new Pair<>(3, 90),
                new Pair<>(4, 100)
        }), "green", 150);

        Street parkPlace = new Street("Park Place", 220, 110, 110, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 70),
                new Pair<>(1, 80),
                new Pair<>(2, 90),
                new Pair<>(3, 100),
                new Pair<>(4, 110)
        }), "blue", 170);

        Street boardwalk = new Street("Boardwalk", 220, 110, 110, new ImmutableMap<>(new Pair[]{
                new Pair<>(0, 70),
                new Pair<>(1, 80),
                new Pair<>(2, 90),
                new Pair<>(3, 100),
                new Pair<>(4, 110)
        }), "blue", 170);
        neighbourhoods = new ImmutableMap<String, Street[]>(new Pair[]{
            new Pair<>("brown", new Street[]{mediterraneanAvenue, balticAvenue}),
            new Pair<>("light blue", new Street[]{orientalAvenue, vermontAvenue, connecticutAvenue}),
            new Pair<>("pink", new Street[]{stCharlesPlace, statesAvenue, virginiaAvenue}),
            new Pair<>("orange", new Street[]{stJamesPlace, tennesseeAvenue, newYorkAvenue}),
            new Pair<>("red", new Street[]{kentuckyAvenue, indianaAvenue, illinoisAvenue}),
            new Pair<>("yellow", new Street[]{atlanticAvenue, ventnorAvenue, marvinGardens}),
            new Pair<>("green", new Street[]{pacificAvenue, northCarolinaAvenue, pennsylvaniaAvenue}),
            new Pair<>("blue", new Street[]{parkPlace, boardwalk})
        });
        board = new Field[]{
                start,
                mediterraneanAvenue,
                communityChest,
                balticAvenue,
                incomeTax,
                readingStation,
                orientalAvenue,
                chances,
                vermontAvenue,
                connecticutAvenue,
                jail,
                stCharlesPlace,
                electricity,
                statesAvenue,
                virginiaAvenue,
                pensylvaniaStation,
                stJamesPlace,
                communityChest,
                tennesseeAvenue,
                newYorkAvenue,
                freeParking,
                kentuckyAvenue,
                chances,
                indianaAvenue,
                illinoisAvenue,
                BOStation,
                atlanticAvenue,
                ventnorAvenue,
                waterWorks,
                marvinGardens,
                police,
                pacificAvenue,
                northCarolinaAvenue,
                communityChest,
                pennsylvaniaAvenue,
                shortStation,
                chances,
                parkPlace,
                luxuryTax,
                boardwalk
        };
    }
    public Field get(int index){
        return board[index];
    }

    public ImmutableMap<String, Street[]> getNeighbourhoods() {
        return neighbourhoods;
    }

    public Station[] getStations() {
        return stations;
    }

    public Pair<Utility, Utility> getUtilities() {
        return utilities;
    }
}
