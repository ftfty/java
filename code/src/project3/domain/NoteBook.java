package project3.domain;

public class NoteBook implements Equipment{
    private String model;
    private String price;

    public NoteBook(String model, String price) {
        this.model = model;
        this.price = price;
    }

    public NoteBook() {
        super();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return model + "(" + price + ")";
    }
}
