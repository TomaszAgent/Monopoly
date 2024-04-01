public abstract class FunctionalField extends Field implements Functional{
    private final String name;
    private final String type;

    public FunctionalField(String name) {
        this.name = name;
        type = "functional";
    }

    @Override
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
