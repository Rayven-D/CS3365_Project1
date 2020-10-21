/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

/**
 *
 * @author Arthr
 */
public class PaymentInformation {

    private String date;
    private float amount;
    private String paymentType;
    private int referacneNumber;
    private int pin;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public PaymentInformation(String date, float amount, String paymentType, int referacneNumber, int pin) {
        this.date = date;
        this.amount = amount;
        this.paymentType = paymentType;
        this.referacneNumber = referacneNumber;
        this.pin = pin;
    }

    public PaymentInformation() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getReferacneNumber() {
        return referacneNumber;
    }

    public void setReferacneNumber(int referacneNumber) {
        this.referacneNumber = referacneNumber;
    }
}
