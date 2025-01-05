/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlt.activityplannerphasefour;

// Imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Itinerary class represents an itinerary made up of multiple activities. It
 * features attributes and constructors to initialise itinerary objects. It
 * includes various getter methods, setters, activity management methods, cost
 * calculation methods and methods for calculating discounts.
 *
 * @author Carlt
 */
public class Itinerary {
    // Attributes

    private String referenceNumber;
    private String nameOfLeadAttendee;
    private final List<Activity> activityList;
    private int numOfActivities;
    private int numOfAttendees;

    private LocalDate itineraryDate;

    private double itineraryBaseCost;
    private double discountPercentage;
    private double itineraryCostWithDiscount;

    // Default Constructor
    public Itinerary(LocalDate itineraryDate) {
        this.referenceNumber = "J1234A";
        this.nameOfLeadAttendee = "C Baines";
        this.activityList = new ArrayList<>();
        this.itineraryBaseCost = 0;
        this.discountPercentage = 0;
        this.itineraryCostWithDiscount = 0;
        this.numOfActivities = 1;
        this.numOfAttendees = 2;
        this.itineraryDate = itineraryDate;
    }

    // Constructor - intialises the objects of the itinerary class.
    public Itinerary(String referenceNumber, String nameOfLeadAttendee, Activity activityList, double itineraryBaseCost,
            double discountPercentage, double itineraryCostWithDiscount, int numOfActivities, int numOfAttendees,
            LocalDate itineraryDate) {
        this.referenceNumber = referenceNumber;
        this.nameOfLeadAttendee = nameOfLeadAttendee;
        this.activityList = new ArrayList<>();
        this.itineraryBaseCost = itineraryBaseCost;
        this.discountPercentage = discountPercentage;
        this.itineraryCostWithDiscount = itineraryCostWithDiscount;
        this.numOfActivities = numOfActivities;
        this.numOfAttendees = numOfAttendees;
        this.itineraryDate = itineraryDate;
    }

    // Methods
    // Getters
    /**
     * getReferenceNumber() method
     *
     * @return referenceNumber
     */
    public String getReferenceNumber() {
        return this.referenceNumber;
    }

    /**
     * getNameOfLeadAttendee() method
     *
     * @return nameOfLeadAttendee
     */
    public String getNameOfLeadAttendee() {
        return nameOfLeadAttendee;
    }

    /**
     * getActivityList() method
     *
     * @return list of type activity
     */
    public List<Activity> getActivityList() {
        return Collections.unmodifiableList(activityList);
    }

    /**
     * getItineraryDate() method
     *
     * @return LocalDate object (itineraryDate).
     */
    public LocalDate getItineraryDate() {
        return itineraryDate;
    }

    /**
     * getItineraryBaseCost() method
     *
     * @return the base cost of an itinerary object.
     */
    public double getItineraryBaseCost() {
        return itineraryBaseCost;
    }

    /**
     * getDiscountPercentage() method
     *
     * @return the base cost of an itinerary object.
     */
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * getItineraryCostWithDiscount() method
     *
     * @return the cost of an itinerary object with discount applied.
     */
    public double getItineraryCostWithDiscount() {
        return itineraryCostWithDiscount;
    }

    /**
     * getNumOfActivities() method
     *
     * @return return the size of the activityList collection.
     */
    public int getNumOfActivities() {
        return activityList.size();
    }

    /**
     * getNumOfAttendees() method
     *
     * @return number of attendees
     */
    public int getNumOfAttendees() {
        return numOfAttendees;
    }

    /**
     * addActivity() method - Method that adds new objects of type activity to
     * the activityList.
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // Setters
    /**
     * setItineraryReferenceNumber() - Method that sets and returns the
     * itinerary reference number by performing validation.
     *
     * @param refNumInput
     * @return ERR_REFERENCE_NUMBER, referenceNumber or null.
     */
    public String setItineraryReferenceNumber(String refNumInput) {
        File file = new File("itineraries.txt");

        // Reference number needs to be validated
        // A reference number should follow the format - one letter, four digits, and a
        // letter.
        // The final letter must be an A, B, C or D.
        referenceNumber = this.getReferenceNumber();
        
        // try-with-resources
        try (Scanner fileReader = new Scanner(file); BufferedReader newBr = new BufferedReader(new FileReader(file))) {
            // Checks to see if the user-inputted reference number is equal to a reference number already stored in the file.
            if (newBr.readLine() == null) {
                if (refNumInput.equals(referenceNumber)) {
                    newBr.close();
                    return "ERR_REFERENCE_NUMBER: The reference number is identical to that of another itinerary.";
                }
            }
            // Reads the file line by line to check if the user-inputted reference number is identical to one already stored.
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.toLowerCase().contains(refNumInput.toLowerCase())) {
                    newBr.close();
                    return "ERR_REFERENCE_NUMBER: The reference number is identical to that of another itinerary.";
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Itinerary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Itinerary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Checks if the reference number input is null or not of the correct length.
        if (refNumInput == null || refNumInput.length() != 6) {
            return "ERR_REFERENCE_NUMBER: Reference number is null or is not exactly 6 characters long.";
        }
        
        // Converts the reference number input to a character array.
        char[] reftoArray = (refNumInput.toCharArray());
        
        // Checks if the first character is not an uppercase letter.
        if (!Character.isLetter(reftoArray[0]) || !Character.isUpperCase(reftoArray[0])) {
            String ERR_REFERENCE_NUMBER = "ERR_REFERENCE_NUMBER\nError! Invalid reference number: The first character is not an uppercase letter.";
            return ERR_REFERENCE_NUMBER;
        }
        
        // Checks if the second to fifth characters are not all digits.
        if (!Character.isDigit(reftoArray[1])
                || !Character.isDigit(reftoArray[2])
                || !Character.isDigit(reftoArray[3])
                || !Character.isDigit(reftoArray[4])) {

            String ERR_REFERENCE_NUMBER = "ERR_REFERENCE_NUMBER: The second to fifth characters are not all digits.";
            return ERR_REFERENCE_NUMBER;
        }
        
        // Checks to see if the last character is not an uppercase letter.
        if (!Character.isLetter(reftoArray[5]) || !Character.isUpperCase(reftoArray[5])) {
            String ERR_REFERENCE_NUMBER = "ERR_REFERENCE_NUMBER: The last character is not an uppercase letter.";
            return ERR_REFERENCE_NUMBER;
        }

        char lastChar = reftoArray[5];
        
        // Checks to see if the last character is not a letter that is either A, B, C or D.
        if ("ABCD".indexOf(lastChar) == -1) {
            String ERR_REFERENCE_NUMBER = "ERR_REFERENCE_NUMBER: The last character is not a letter that is either A, B, C or D.";
            return ERR_REFERENCE_NUMBER;
        }
        
        // Else valid reference number.

        this.referenceNumber = refNumInput;

        return "Success! Valid reference number entered: " + referenceNumber;

    }

    /**
     * setItineraryQuarter() - Method that checks the last letter of the
     * user-inputted itinerary reference number to determine the itinerary
     * quarter.
     *
     *
     * @param finalRefNumber
     * @return finalQuarter or null
     */
    public String setItineraryQuarter(String finalRefNumber) {
        String finalQuarter;
        char[] reftoArray = (finalRefNumber.toCharArray());

        char quarterLetter = reftoArray[5];
        finalQuarter = switch (quarterLetter) {
            case 'A' ->
                "Jan-Mar";
            case 'B' ->
                "Apr-Jun";
            case 'C' ->
                "Jul-Sep";
            case 'D' ->
                "Oct-Dec";
            default ->
                "ERR_QUARTER";
        };
        return finalQuarter;

    }

    /**
     * setLeadAttendee() method - Method that sets and returns the lead attendee
     * name by performing validation.
     *
     * @param attendeeNameInput
     * @return true, false.
     */
    public boolean setLeadAttendeeName(String attendeeNameInput) {

        nameOfLeadAttendee = this.getNameOfLeadAttendee();

        char[] nametoArray = (nameOfLeadAttendee.toCharArray());

        int indexSpace = attendeeNameInput.indexOf(' ');
        
        
        if (nametoArray.length > 20) {
            System.out.println("Error! Invalid lead attendee name: Name is more than 20 characters.");
            return false;
        } else if (indexSpace == -1) {
            System.out.println(
                    "Error! Invalid lead attendee name: There must be a space inbetween the initial and surname.");
            return false;
        }

        this.nameOfLeadAttendee = attendeeNameInput;
        System.out.println("Success! Valid lead attendee name entered: " + nameOfLeadAttendee);
        return true;

    }

    /**
     * setNumOfAttendees() method - Assigns the parameter to the instance
     * variable.
     *
     * @param numOfAttendees
     */
    public void setNumOfAttendees(int numOfAttendees) {
        this.numOfAttendees = numOfAttendees;
    }

    /**
     * setNumberOfActivities() method - Method that sets the number of
     * activities for an itinerary object.
     *
     * @param finalTotalActivities
     * @param activities
     */
    public void setNumberOfActivities(int finalTotalActivities, List<Activity> activities) {

        activityList.addAll(activities);
        numOfActivities = finalTotalActivities;

    }

    /**
     * setTotalActivityCost() method - Method that sets and returns the total
     * activity cost
     *
     * @return totalActivityCost
     */
    public double setTotalActivityCost() {
        double totalActivityCost = 0;
        for (Activity activity : activityList) {
            totalActivityCost += activity.getBaseActivityCost();
        }
        return totalActivityCost;
    }

    /**
     * setItineraryBaseCost() method - Method that sets and returns the
     * itinerary base cost.
     *
     * @return itineraryBaseCost
     */
    public double setItineraryBaseCost() {

        itineraryBaseCost = 0;

        for (Activity activity : activityList) {
            activity.setActivityBaseActivityCost();
            itineraryBaseCost += (activity.getBaseActivityCost() * numOfAttendees);
        }

        return itineraryBaseCost;
    }

    /**
     * setItineraryBaseCostWithAddOns() method - Method that sets and returns
     * the total itinerary cost with the price of add-ons included.
     *
     * @return totalCost
     */
    public double setItineraryBaseCostWithAddOns() {

        double totalCost = this.itineraryBaseCost;

        for (Activity activity : activityList) {
            for (AddOn activityAddOn : activity.getActivityAddOnsList()) {
                totalCost += (activityAddOn.getAddOnCost() * numOfAttendees);
            }
            for (AddOn itineraryAddOn : activity.getItineraryAddOnsList()) {
                totalCost += (itineraryAddOn.getAddOnCost() * numOfAttendees);
            }
        }

        return totalCost;
    }

    /**
     * setItineraryDiscountPercentage() method - Method that determines the
     * discount percentage based on the number of attendees and the number of
     * activities taken.
     *
     *
     * @return discountPercentage
     */
    public double setItineraryDiscountPercentage() {

        discountPercentage = 0;

        numOfAttendees = getNumOfAttendees();

        if ((numOfActivities == 1 || numOfActivities == 2) && numOfAttendees < 10) {
            discountPercentage = 0;
        } else if ((numOfActivities == 1 || numOfActivities == 2) && numOfAttendees >= 10 && numOfAttendees <= 20) {
            discountPercentage = 0.05;
        } else if ((numOfActivities == 1 || numOfActivities == 2) && numOfAttendees >= 20) {
            discountPercentage = 0.08;
        } else if (numOfActivities >= 3 && numOfActivities <= 5 && numOfAttendees < 10) {
            discountPercentage = 0.05;
        } else if (numOfActivities >= 3 && numOfActivities <= 5 && numOfAttendees >= 10 && numOfAttendees <= 20) {
            discountPercentage = 0.08;
        } else if (numOfActivities >= 3 && numOfActivities <= 5 && numOfAttendees >= 20) {
            discountPercentage = 0.12;
        } else if (numOfActivities >= 6 && numOfAttendees < 10) {
            discountPercentage = 0.10;
        } else if (numOfActivities >= 6 && numOfAttendees >= 10 && numOfAttendees <= 20) {
            discountPercentage = 0.12;
        } else if (numOfActivities >= 6 && numOfAttendees >= 20) {
            discountPercentage = 0.14;
        } else {
            System.out.println("ERROR! Itinerary discount percentage could not be calculated");
            discountPercentage = 0;
        }

        return discountPercentage;

    }

    /**
     * setItineraryTotalCost() method - Method that returns the total cost of an
     * itinerary with discount calculation.
     *
     * @return totalCost
     */
    public double calculateItineraryTotalCost() {

        double baseCostInPounds = setItineraryBaseCostWithAddOns();

        double discountAmount = (baseCostInPounds * discountPercentage);
        double totalCost = (baseCostInPounds - discountAmount);

        return totalCost;
    }

    /**
     * setDiscountOutput() method - calculates the total discount applied to the
     * total activity cost and returns it.
     *
     * @return discount
     */
    public double setDiscountOutput() {

        double discount;
        double itineraryBaseCostWithAddOns = this.setItineraryBaseCostWithAddOns();
        itineraryCostWithDiscount = this.calculateItineraryTotalCost();

        discount = itineraryBaseCostWithAddOns - itineraryCostWithDiscount;

        return discount;
    }

    /**
     * Override toString() method
     *
     * @return formatted string of itinerary attributes.
     *
     */
    @Override
    public String toString() {
        numOfActivities = getNumOfActivities();
        return "Itinerary Reference Number: {" + referenceNumber + "}" + ", Name of Lead Attendee: {"
                + nameOfLeadAttendee + "}" + ", Itinerary Date: {" + itineraryDate + "}" + ", Number of activities: {"
                + getNumOfActivities() + "}" + ", Number of attendees: {" + numOfAttendees + "}";
    }
}
