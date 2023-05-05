public class Device {
    private int id;
    private String name;
    private String origin;
    private int price;
    private Type type;

    public Device(String name, String origin, int price, boolean isPeripheral, int energy, boolean hasCooler, String componentGroup, String port, int id) {
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.type = new Type(isPeripheral, energy, hasCooler, componentGroup, port);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public int getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }
}

class Type{
    private boolean isPeripheral;
    private int energy;
    private boolean hasCooler;
    private String componentGroup;
    private String port;

    public Type(boolean isPeripheral, int energy, boolean hasCooler, String componentGroup, String port) {
        this.isPeripheral = isPeripheral;
        this.energy = energy;
        this.hasCooler = hasCooler;
        this.componentGroup = componentGroup;
        this.port = port;
    }

    public boolean isPeripheral() {
        return isPeripheral;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isHasCooler() {
        return hasCooler;
    }

    public String getComponentGroup() {
        return componentGroup;
    }

    public String getPort() {
        return port;
    }
}