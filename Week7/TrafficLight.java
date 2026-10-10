package Week7;

/**
 * Problem 4: The Traffic Light
 * Enforces controlled state machine transitions (RED -> GREEN -> YELLOW -> RED).
 */
public class TrafficLight {
    private final String id;
    private String color;

    public TrafficLight(String id) {
        this.id = id;
        this.color = "RED";
    }

    public String getId() {
        return this.id;
    }

    public String getColor() {
        return this.color;
    }

    public String next() {
        switch (this.color) {
            case "RED":
                this.color = "GREEN";
                break;
            case "GREEN":
                this.color = "YELLOW";
                break;
            case "YELLOW":
            default:
                this.color = "RED";
                break;
        }
        return this.color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight("TL-9");
        System.out.println("t.getColor() -> \"" + t.getColor() + "\"");
        System.out.println("t.next() -> \"" + t.next() + "\"");
        System.out.println("t.next() -> \"" + t.next() + "\"");
        System.out.println("t.next() -> \"" + t.next() + "\"");
    }
}
