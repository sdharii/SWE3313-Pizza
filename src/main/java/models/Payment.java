package models;

public class Payment {
    private int paymentID;
    private int customerID;
    private String cardholderName;
    private String cardNumber;
    private String expiration;
    private String cvv;

    public Payment( int customerID, String cardholderName, String cardNumber, String expiration, String cvv) {
        this.customerID = customerID;
        this.cardholderName = cardholderName;
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.cvv = cvv;
    }

    public Payment(int customerID, String paymentMethod) {
        this.customerID = customerID;
        this.cardholderName = paymentMethod;
        this.cardholderName = "";
        this.cardNumber = "";
        this.cvv = "";
    }
    // Getters
    public int getPaymentID() {return paymentID;}
    public int getCustomerID() {return customerID;}
    public String getCardholderName() {return cardholderName;}
    public String getExpiration() {return expiration;}
    public String getCvv() {return cvv;}
    public String getCardNumber() {return cardNumber;}
}
