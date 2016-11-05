package main.java.de.honzont;

/**
 * Created by Gäbeler on 03.11.2016.
 */
public class Card {
    private String name;
    private Integer value;

    /**
     *
     * @param name card name
     * @param value value of card
     */
    public Card(String name, Integer value) {
        this.name = name;
        this.value = value;
    }


    /* Standard Getter & Setter */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
}