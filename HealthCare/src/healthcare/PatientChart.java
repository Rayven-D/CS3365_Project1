package healthcare;

/**
 *
 * @author Arthr
 */
public class PatientChart {

    private float weight;
    private float height;
    private float bloodPressure;
    private String reason;
    private String treatment;
    private String prescription;

    /**
     * Param Constructor
     *
     * @param weight The weight of the patient
     * @param height The height of the patient
     * @param bloodPressure The blood pressure of the patient
     * @param reason The reason behind the visit
     * @param treatment The treatment provided by the doctor
     * @param prescription The prescription provided by the doctor
     */
    public PatientChart(int weight, int height, int bloodPressure, String reason, String treatment, String prescription) {
        this.weight = weight;
        this.height = height;
        this.bloodPressure = bloodPressure;
        this.reason = reason;
        this.treatment = treatment;
        this.prescription = prescription;
    }

    /**
     * Empty Constructor
     */
    public PatientChart() {
    }

    /**
     * Get Prescription
     *
     * @return The prescription provided by the doctor
     */
    public String getPrescription() {
        return prescription;
    }

    /**
     * Set Prescription
     *
     * @param prescription New Prescription
     */
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    /**
     * Get Weight
     *
     * @return The User's weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Set Weight
     *
     * @param weight New patient weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Get Treatment
     *
     * @return The Treatment provided by the doctor
     */
    public String getTreatment() {
        return treatment;
    }

    /**
     * Set Treatment
     *
     * @param treatment New treatment by doctor
     */
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    /**
     * Get Height
     *
     * @return The User's Height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Set Height
     *
     * @param height New patient height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Get Blood Pressure
     *
     * @return The User's blood pressure
     */
    public float getBloodPressure() {
        return bloodPressure;
    }

    /**
     * Set Blood Pressure
     *
     * @param bloodPressure New patient blood pressure
     */
    public void setBloodPressure(float bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    /**
     * Get Reason
     *
     * @return The User's reason for visit
     */
    public String getReason() {
        return reason;
    }

    /**
     * Set Reason
     *
     * @param reason New patient's reason for visit
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

}
