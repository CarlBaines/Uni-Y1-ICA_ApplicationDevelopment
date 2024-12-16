/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlt.activityplannerphasethree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlt
 */
public class Itinerary {
    //  Attributes

    private String referenceNumber;
    private String nameOfLeadAttendee;
    private final List<Activity> activityList;
    private int numOfActivities;
    private int numOfAttendees;

    private LocalDate itineraryDate;

    private double itineraryBaseCost;
    private double discountPercentage;
    private double itineraryCostWithDiscount;

    //  Default Constructor
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

    //  Constructor - intialises the objects of the itinerary class.
    public Itinerary(String referenceNumber, String nameOfLeadAttendee, Activity activityList, double itineraryBaseCost, double discountPercentage, double itineraryCostWithDiscount, int numOfActivities, int numOfAttendees, LocalDate itineraryDate) {
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

    //  Methods
    //  Getters
    /**
     * getReferenceNumber() method
     *
     * @return referenceNumber
     */
    public String getReferenceNumber() {
        return referenceNumber;
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
        return activityList;
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
     * addActivity() method Method that adds new objects of type activity to the
     * activityList.
     *
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // Setters
    /**
     * setItineraryReferenceNumber() method Method that sets and returns the
     * itinerary reference number by performing validation.
     *
     * @param refNumInput
     * @return ERR_REFERENCE_NUMBER, referenceNumber or null.
     */
    public String setItineraryReferenceNumber(String refNumInput) {

        // Check to see if the refNumInput is already in the itineraries.txt file.
        File file = new File("itineraries.txt");

        //  Reference number needs to be validated
        //  A reference number should follow the format - one letter, four digits, and a letter.
        //  The final letter must be an A, B, C or D.
        //  referenceNumber is set to the return value from the getReferenceNumber method.
        referenceNumber = this.getReferenceNumber();

        //  This string is then converted to a character array.
        char[] reftoArray = (referenceNumber.toCharArray());

        try (Scanner fileReader = new Scanner(file)) {

            BufferedReader newBr = new BufferedReader(new FileReader(file));
            if (newBr.readLine() == null) {
                if (refNumInput.equals(referenceNumber)) {
                    newBr.close();
                    return "ERR_REFERENCE_NUMBER: The reference number is identical to that of another itinerary.";
                }
            }

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.toLowerCase().contains(refNumInput.toLowerCase())) {
                    newBr.close();
                    return "ERR_REFERENCE_NUMBER: The reference number is identical to that of another itinerary.";
                }
            }

            newBr.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Itinerary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Itinerary.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (refNumInput == null || refNumInput.length() != 6) {
            return "ERR_REFERENCE_NUMBER: Reference number is null or is not exactly 6 characters long.";
        }

        //  The character array is looped through to get access to every char stored in the indexes of the array - for validation. 
        for (int index = 0; index < reftoArray.length; index++) {

            //  If the char stored at [0] in the charArray is not a letter or a letter that is not uppercase.
            if (!Character.isLetter(reftoArray[0]) || !Character.isUpperCase(reftoArray[0])) {
                String ERR_REFERENCE_NUMBER = "ERR_REFERENCE_NUMBER\nError! Invalid reference number: The first character is not an uppercase letter.";
                return ERR_REFERENCE_NUMBER;
            } //  If the characters stored at [1], [2], [3], [4] in the charArray are not all digits
            else if (!Character.isDigit(reftoArray[1])
                    || !Character.isDigit(reftoArray[2])
                    || !Character.isDigit(reftoArray[3])
                    || !Character.isDigit(reftoArray[4])) {

                String ERR_REFERENCE_NUMBER = "ERR_REFERENCE_NUMBER: The second to fifth characters are not all digits.";
                return ERR_REFERENCE_NUMBER;
            } //  If the char stored at [5] in the charArray is not a letter or a letter that is not uppercase.
            else if (!Character.isLetter(reftoArray[5]) || !Character.isUpperCase(reftoArray[0])) {
                String ERR_REFERENCE_NUMBER = "ERR_REFERENCE_NUMBER: The last character is not an uppercase letter.";
                return ERR_REFERENCE_NUMBER;
            } //  If the char stored at [5] in the charArray is not a letter that is either A, B, C or D.
            else if (reftoArray[5] != 'A' && reftoArray[5] != 'B' && reftoArray[5] != 'C' && reftoArray[5] != 'D') {
                String ERR_REFERENCE_NUMBER = "ERR_REFERENCE_NUMBER: The last character is not a letter that is either A, B, C or D.";
                return ERR_REFERENCE_NUMBER;
            }
            // else valid itinerary reference number

            this.referenceNumber = refNumInput;

            return "Success! Valid reference number entered: " + referenceNumber;
        }

        return null;

    }
    

    /**
     * setLeadAttendee() method Method that sets and returns the lead attendee
     * name by performing validation.
     *
     * @param attendeeNameInput
     * @return true, false.
     */
    public boolean setLeadAttendeeName(String attendeeNameInput) {

        //  The lead attendee name must be in the form initial <SPACE> Surname
        //  The name also cannot be more than twenty characters.
        //  A string called leadAttendeeName is set to the return value from the getNameofLeadAttendee method, which is in the class.
        nameOfLeadAttendee = this.getNameOfLeadAttendee();

        //  This string is then converted into a character array.
        char[] nametoArray = (nameOfLeadAttendee.toCharArray());

        //  loops through the character array for access to the character stored at each specific index.
        for (int index = 0; index < nametoArray.length; index++) {

            int indexSpace = attendeeNameInput.indexOf(' ');

            if (attendeeNameInput == null) {
                System.out.println("Error! Invalid lead attendee name: Name is null!");
                return false;
            } //  If the length of the character array is larger than 20.
            else if (nametoArray.length > 20) {
                System.out.println("Error! Invalid lead attendee name: Name is more than 20 characters.");
                return false;
            } else if (indexSpace == -1) {
                System.out.println("Error! Invalid lead attendee name: There must be a space inbetween the initial and surname.");
                return false;
            }

            // else valid lead attendee name
            this.nameOfLeadAttendee = attendeeNameInput;
            System.out.println("Success! Valid lead attendee name entered.");
            return true;

        }

        return false;
    }

    /**
     * setNumberOfActivities() method Method that sets the number of activities
     * for an itinerary object.
     *
     * @param activities
     */
    public void setNumberOfActivities(List<Activity> activities) {

        activityList.addAll(activities);
        //  Sets number of activities to the size of the activityList arrayList.
        numOfActivities = activityList.size();

    }

    /**
     * setTotalActivityCost() method Method that sets and returns the total
     * activity cost
     *
     * @return totalActivityCost
     */
    public double setTotalActivityCost() {
        double totalActivityCost = 0;
        // loops through each activity object stored in the activityList
        for (Activity activity : activityList) {
            // adding to the totalActivityCost by using the base activity cost getter on each activity object.
            totalActivityCost += activity.getBaseActivityCost();
        }
        return totalActivityCost;
    }

    /**
     * setItineraryBaseCost() method Method that sets and returns the itinerary
     * base cost.
     *
     * @return itineraryBaseCost
     */
    // Method that sets and returns the itinerary base cost.
    public double setItineraryBaseCost() {

        //  Need to get the base cost of the activities from the activityList.
            //  The base cost has already been calculated for each individual activity from the setActivityBaseActivityCost method in the activity class.
        itineraryBaseCost = 0;

        for (Activity activity : activityList) {
            activity.setActivityBaseActivityCost();
            itineraryBaseCost += activity.getBaseActivityCost(); //  already divided by 100 to get the base cost in pounds.
        }

        return itineraryBaseCost;
    }

    /**
     * setItineraryBaseCostWithAddOns() method Method that sets and returns the
     * total itinerary cost with the price of add-ons included.
     *
     * @return totalCost
     */
    public double setItineraryBaseCostWithAddOns() {

        double totalCost = this.itineraryBaseCost;

        for (Activity activity : activityList) {
            for (AddOn addOn : activity.getAddOnsList()) {
                totalCost += addOn.getAddOnCost();
            }
        }

        return totalCost;
    }

    public void setItineraryDiscountPercentage() {

        //  Discount calculation is based on the number of attendees and the number of activities taken.
        discountPercentage = 0;

        numOfAttendees = getNumOfAttendees();

        //  Activities: 1-2, Attendees < 10
        if ((numOfActivities == 1 || numOfActivities == 2) && numOfAttendees < 10) {
            discountPercentage = 0;
        } //  Activities: 1-2, Attendees: 10 to 20.
        else if ((numOfActivities == 1 || numOfActivities == 2) && numOfAttendees >= 10 && numOfAttendees <= 20) {
            discountPercentage = 0.05;
        } //  Activities: 1-2, Attendees: 20+
        else if ((numOfActivities == 1 || numOfActivities == 2) && numOfAttendees >= 20) {
            discountPercentage = 0.08;
        } //  Activities: 3-5, Attendees: < 10
        else if (numOfActivities >= 3 && numOfActivities <= 5 && numOfAttendees < 10) {
            discountPercentage = 0.05;
        } //  Activities: 3-5, Attendees: 10 to 20
        else if (numOfActivities >= 3 && numOfActivities <= 5 && numOfAttendees >= 10 && numOfAttendees <= 20) {
            discountPercentage = 0.08;
        } //  Activities: 3-5, Attendees: 20+
        else if (numOfActivities >= 3 && numOfActivities <= 5 && numOfAttendees >= 20) {
            discountPercentage = 0.12;
        } //  Activities: 6+, Attendees: < 10
        else if (numOfActivities >= 6 && numOfAttendees < 10) {
            discountPercentage = 0.10;
        } //  Activities: 6+, Attendees: 10 to 20
        else if (numOfActivities >= 6 && numOfAttendees >= 10 && numOfAttendees <= 20) {
            discountPercentage = 0.12;
        } //  Activities 6+, Attendees: 20+
        else if (numOfActivities >= 6 && numOfAttendees >= 20) {
            discountPercentage = 0.14;
        } else {
            System.out.println("ERROR! Itinerary discount percentage could not be calculated");
        }

    }

    public double setItineraryTotalCost() {

        //  Base cost - discount.
        //  Result can then be passed into the activity calculateTotalCost method.
        //  itinerary base cost in pounds = base cost/100. The getter for itineraryBaseCost already divides it by 100 for display in pounds. 
        double baseCostInPounds = setItineraryBaseCostWithAddOns();

        double discountAmount = (baseCostInPounds * discountPercentage);
        double totalCost = (baseCostInPounds - discountAmount);

        return totalCost;
    }

    public double setDiscountOutput() {

        double discount;
        double itineraryBaseCostWithAddOns = this.setItineraryBaseCostWithAddOns();
        itineraryCostWithDiscount = this.setItineraryTotalCost();

        discount = itineraryBaseCostWithAddOns - itineraryCostWithDiscount;

        return discount;
    }

    @Override
    public String toString() {
        numOfActivities = getNumOfActivities();
        return "Itinerary Reference Number: {" + referenceNumber + "}" + ", Name of Lead Attendee: {" + nameOfLeadAttendee + "}" + ", Itinerary Date: {" + itineraryDate + "}" + ", Number of activities: {" + getNumOfActivities() + "}" + ", Number of attendees: {" + numOfAttendees + "}";
    }
}
