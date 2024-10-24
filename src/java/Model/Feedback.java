package Model;

public class Feedback {
    private int feedbackID;
    private String feedbackNote;
    private int customerID;
    private Customer customer;
    private Responde responde; // New field

    // Constructors, Getters, and Setters
    public Feedback() {
    }

    public Feedback(int feedbackID, String feedbackNote, int customerID, Customer customer, Responde responde) {
        this.feedbackID = feedbackID;
        this.feedbackNote = feedbackNote;
        this.customerID = customerID;
        this.customer = customer;
        this.responde = responde;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getFeedbackNote() {
        return feedbackNote;
    }

    public void setFeedbackNote(String feedbackNote) {
        this.feedbackNote = feedbackNote;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Responde getResponde() {
        return responde;
    }

    public void setResponde(Responde responde) {
        this.responde = responde;
    }
}
