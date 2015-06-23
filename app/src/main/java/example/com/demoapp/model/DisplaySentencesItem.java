package example.com.demoapp.model;

/**
 * Created by Long on 6/22/2015.
 */
public class DisplaySentencesItem {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DisplaySentencesItem(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public DisplaySentencesItem() {
        this.id = id;
        this.name = "";
    }

}
