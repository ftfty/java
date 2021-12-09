package project3.service;

public class Status {
    private final String NAME;
    private Status(String NAME){
        this.NAME = NAME;
    }
    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VACATION = new Status("VACATION");

}
