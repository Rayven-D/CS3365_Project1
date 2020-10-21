package healthcare;

/**
 *
 * @author Arthr
 */
public class PaymentInformation {

    private String date;
    private float amount;
    private String paymentType;
    private int referenceNumber;
    private int pin;

    /**
     *
     * @return
     */
    public int getPin() {
        return pin;
    }

    /**
     *
     * @param pin
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    /**
     * Param Constructor
     *
     * @param date The Date of the payment in a mm/dd/yyyy format
     * @param amount The amount due/owned
     * @param paymentType The type of payment used in transaction
     * @param referenceNumber The reference number of the transaction
     * @param pin The pin for the debit card
     */
    public PaymentInformation(String date, float amount, String paymentType, int referenceNumber, int pin) {
        this.date = date;
        this.amount = amount;
        this.paymentType = paymentType;
        this.referenceNumber = referenceNumber;
        this.pin = pin;
    }

    /**
     * Empty constructor
     */
    public PaymentInformation() {
    }

    /**
     * Get Date
     *
     * @return The date in a mm/dd/yyyy format
     */
    public String getDate() {
        return date;
    }

    /**
     * Set Date
     *
     * @param date The date in a mm/dd/yyyy format
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get Amount
     *
     * @return The amount due
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Set Amount
     *
     * @param amount Set an amount due for appointment
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Get Payment Type
     *
     * @return The payment type of the transaction
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Set Payment Type
     *
     * @param paymentType New Payment Type
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Get Reference Number
     *
     * @return The transaction reference number
     */
    public int getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Set Reference Number
     *
     * @param referenceNumber New Reference Number
     */
    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
