/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlt.activityplannerphasefour;

// Imports
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The activity class is designed to represent and manage activities. It
 * contains static and instance fields for attributes and constructors for
 * initialising activities. It contains methods such as getters, setters and
 * methods for add-ons management, validation, cost calculation and string
 * formatting.
 *
 * @author Carlt
 *
 */
public class Activity {

    private static final List<String> activityCodesList = new ArrayList<>();

    static {
        // Adds pre-defined strings as activity codes to the activityCodesList.
        activityCodesList.add("ACT-01");
        activityCodesList.add("ACT-02");
        activityCodesList.add("ACT-03");
        activityCodesList.add("ACT-04");
        activityCodesList.add("ACT-05");
    }

    //  A static activityCounter is initialised to one - as there is one activity by default.
    public static int activityCounter = 1;

    //   Attributes of activity class
    private double baseActivityCost;
    private String code;
    private String activityTitle;
    private final String activityDescription;
    private final String location;
    //   The LocalDate and LocalTime objects will be used for the date and time handling.
    private final LocalDate activityDate;
    private final LocalTime activityTime;
    private final int expectedDuration;
    private boolean insuranceRequired;

    //  A private list that takes objects from the AddOn class is set up.
    private List<AddOn> activityAddOnsList;
    private List<AddOn> itineraryAddOnsList;

    //  Default Constructor
    public Activity() {
        this.baseActivityCost = 0;
        this.code = "ACT-01";
        this.activityTitle = "Building a bridge from paper";
        this.activityDescription = "Build a bridge using only paper and tape that spans a 6-inch gap";
        this.location = "Middlesbrough";
        //  Creates LocalDate and LocalTime objects.
        //  The activity date object represents November 6, 2024.
        //  The local time object represents 19:00 (7PM).
        this.activityDate = LocalDate.of(2025, 1, 7);
        this.activityTime = LocalTime.of(19, 0);
        this.expectedDuration = 60;
        this.insuranceRequired = false;

        this.activityAddOnsList = new ArrayList<>();
        this.itineraryAddOnsList = new ArrayList<>();
    }

    //   Constructor - initalises the objects of the activity class.
    public Activity(double baseActivityCost, String code, String activityTitle, String activityDescription, String location, LocalDate activityDate, LocalTime activityTime, int expectedDuration, boolean insuranceRequired, List<AddOn> activityAddOnsList, List<AddOn> itineraryAddOnsList) {
        this.baseActivityCost = baseActivityCost;
        this.code = code;
        this.activityTitle = activityTitle;
        this.activityDescription = activityDescription;
        this.location = location;
        this.activityDate = activityDate;
        this.activityTime = activityTime;
        this.expectedDuration = expectedDuration;
        this.insuranceRequired = insuranceRequired;

        this.activityAddOnsList = activityAddOnsList;
        this.itineraryAddOnsList = itineraryAddOnsList;
        //  Increments the activityCounter. 
        Activity.activityCounter++;

    }

    // Getters
    /**
     * getActivityCodesList() method
     *
     * @return the activity codes list.
     *
     */
    public List<String> getActivityCodesList() {
        return Collections.unmodifiableList(activityCodesList);
    }

    /**
     * getBaseActivityCost() method
     *
     * @return base cost of the activity in pounds.
     *
     */
    public double getBaseActivityCost() {
        // divides this by 100 to convert the internal value of baseActivityCost, which is stored in pence.
        return baseActivityCost / 100;
    }

    /**
     * getCode() method
     *
     * @return activity code.
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * getActivityTitle() method
     *
     * @return activity title.
     *
     */
    public String getActivityTitle() {
        return activityTitle;
    }

    /**
     * getActivityDescription() method
     *
     * @return activity description.
     *
     */
    public String getActivityDescription() {
        return activityDescription;
    }

    /**
     * getLocation() method
     *
     * @return activity location.
     *
     */
    public String getLocation() {
        return location;
    }

    /**
     * getActivityDate() method
     *
     * @return LocalDate
     *
     */
    public LocalDate getActivityDate() {
        return activityDate;
    }

    /**
     * getActivityTime() method
     *
     * @return LocalTime
     *
     */
    public LocalTime getActivityTime() {
        return activityTime;
    }

    /**
     * getExpectedDuration() method
     *
     * @return expected duration of activity.
     *
     */
    public int getExpectedDuration() {
        return expectedDuration;
    }

    /**
     * getInsuranceRequired() method
     *
     * @return whether insurance is required for activity.
     *
     */
    public boolean getInsuranceRequired() {
        return insuranceRequired;
    }

    /**
     * getAddOnsList() method
     *
     * @return list of addOns for an activity.
     *
     */
    public List<AddOn> getActivityAddOnsList() {
        return Collections.unmodifiableList(activityAddOnsList);
    }

    public List<AddOn> getItineraryAddOnsList() {
        return Collections.unmodifiableList(itineraryAddOnsList);
    }

    /**
     * addAddOn() method - adds an AddOn object to the addOnsList.
     *
     * @param addOn
     */
    
    public void addActivityAddOn(AddOn addOn) {
        activityAddOnsList.add(addOn);
    }

    public void addItineraryAddOn(AddOn addOn) {
        itineraryAddOnsList.add(addOn);
    }

    //  Methods
    //  Setters
    /**
     * setAddOnsList() method
     *
     * @param activityAddOnsList
     */
    public void setActivityAddOnsList(List<AddOn> activityAddOnsList) {
        this.activityAddOnsList = activityAddOnsList;
    }

    public void setItineraryAddOnsList(List<AddOn> itineraryAddOnsList) {
        this.itineraryAddOnsList = itineraryAddOnsList;
    }

    /**
     * isInsuranceRequired() method
     *
     * @return if insurance is required for an activity (true/false value).
     *
     */
    public boolean isInsuranceRequired() {
        String activity = this.getActivityTitle();

        switch (activity) {
            case "Building a bridge from paper" -> {

                insuranceRequired = false;
                return insuranceRequired;

            }
            case "Cookery Classes" -> {

                insuranceRequired = false;
                return insuranceRequired;

            }
            case "SAS-Style Assault Courses" -> {

                insuranceRequired = true;
                return insuranceRequired;

            }
            case "Obstacle Course" -> {
                insuranceRequired = true;
                return insuranceRequired;
            }
            case "Cavern Ziplining" -> {
                insuranceRequired = true;
                return insuranceRequired;
            }
            default -> {
                insuranceRequired = false;
                return insuranceRequired;
            }
        }
        //  default case - to prevent fallthrough.
    }

    /**
     * setActivityTitleFromCode() method
     *
     * @param activityCodesList
     * @param index
     * @return the activity title from the associated activity code.
     *
     */
    public String setActivityTitleFromCode(List<String> activityCodesList, int index) {

        String activityCode = activityCodesList.get(index);

        activityTitle = switch (activityCode) {
            case "ACT-01" ->
                "Building a bridge from paper";
            case "ACT-02" ->
                "Cookery Classes";
            case "ACT-03" ->
                "SAS-Style Assault Courses";
            case "ACT-04" ->
                "Obstacle Course";
            case "ACT-05" ->
                "Cavern Ziplining";
            default ->
                null;
        };
        return activityTitle;

    }

    /**
     * setActivityBaseActivityCost() method
     *
     * @return activity base cost in pounds.
     *
     */
    public double setActivityBaseActivityCost() {
        // The base cost is determined by the specific activity.
        String activity = this.getActivityTitle();

        switch (activity) {
            case "Building a bridge from paper" -> {
                //  Costs are stored in pence.
                //  2500.0p is £25.00.
                baseActivityCost = 2500.00;
                double baseActivityCostInPounds = (baseActivityCost / 100);
                return baseActivityCostInPounds;

            }
            case "Cookery Classes" -> {
                //  7500.0p is £75.00.
                baseActivityCost = 7500.00;
                double baseActivityCostInPounds = (baseActivityCost / 100);
                return baseActivityCostInPounds;

            }
            case "SAS-Style Assault Courses" -> {
                //  15000.0p is £150.00.
                baseActivityCost = 15000.00;
                double baseActivityCostInPounds = (baseActivityCost / 100);
                return baseActivityCostInPounds;

            }
            case "Obstacle Course" -> {
                //  9000.0p is £90.00.
                baseActivityCost = 9000.00;
                double baseActivityCostInPounds = (baseActivityCost / 100);
                return baseActivityCostInPounds;
            }
            case "Cavern Ziplining" -> {
                //  17500.0p is £175.00
                baseActivityCost = 17500.00;
                double baseActivityCostInPounds = (baseActivityCost / 100);
                return baseActivityCostInPounds;
            }
            default -> {
                baseActivityCost = 0.00;
                return 0;
            }
        }
        //  default case - to prevent fallthrough.

    }

    /**
     * activityBaseCostOutput() method
     *
     * @param activityTitle
     * @return the base cost of an activity, in pounds, dependant on the
     * activity title.
     *
     */
    public String activityBaseCostOutput(String activityTitle) {

        switch (activityTitle) {
            case "Building a bridge from paper" -> {
                //  Costs are stored in pence.
                //  2500.0p is £25.00.
                baseActivityCost = 2500.0;
                double baseActivityCostInPounds = (baseActivityCost / 100);
                return String.format("Building a bridge from paper: £%.2f ", baseActivityCostInPounds);

            }
            case "Cookery Classes" -> {
                //  7500.0p is £75.00.
                baseActivityCost = 7500.0;
                double baseActivityCostInPounds = (baseActivityCost / 100);
                return String.format("Cookery Classes: £%.2f ", baseActivityCostInPounds);

            }
            case "SAS-Style Assault Courses" -> {
                //  15000.0p is £150.00.
                baseActivityCost = 15000.0;
                double baseActivityCostInPounds = (baseActivityCost / 100);
                return String.format("SAS-Style Assault Courses: £%.2f ", baseActivityCostInPounds);

            }
            case "Obstacle Course" -> {
                //  9000.0p is £90.00.
                baseActivityCost = 9000.0;
                double baseActivityCostInPounds = (baseActivityCost / 100);
                return String.format("Obstacle Course: £%.2f ", baseActivityCostInPounds);
            }
            case "Cavern Ziplining" -> {
                //  17500.0p is £175.00
                baseActivityCost = 17500.0;
                double baseActivityCostInPounds = (baseActivityCost / 100);
                return String.format("Cavern Ziplining: £%.2f ", baseActivityCostInPounds);
            }
            default -> {
                baseActivityCost = 0;
                return "ACTIVITY_ERROR£";
            }
        }
        //  default case - to prevent fallthrough.

    }

    /**
     * setActivityBaseCostByCode() method
     *
     * @param activityCodesList
     * @return the totalBaseActivityCost / 100.
     *
     */
    public double setActivityBaseCostByCode(List<String> activityCodesList) {
        double totalBaseActivityCost = 0.0;

        for (String activityCode : activityCodesList) {
            switch (activityCode) {
                case "ACT-01" -> {
                    totalBaseActivityCost += 2500.0;

                }
                case "ACT-02" -> {
                    totalBaseActivityCost += 7500.0;

                }
                case "ACT-03" -> {
                    totalBaseActivityCost += 15000.0;

                }
                case "ACT-04" -> {
                    totalBaseActivityCost += 9000.0;

                }
                case "ACT-05" -> {
                    totalBaseActivityCost += 17500.0;

                }
                default -> {
                    totalBaseActivityCost += 0.0;
                }

            }

        }

        return totalBaseActivityCost / 100;
    }

    /**
     * setActivityCode() method
     *
     * @return activity code based on the associated activity title.
     *
     */
    public String setActivityCode() {
        String title = this.getActivityTitle();

        switch (title) {
            case "Building a bridge from paper" -> {
                code = "ACT-01";
            }
            case "Cookery Classes" -> {
                code = "ACT-02";
            }
            case "SAS-Style Assault Courses" -> {
                code = "ACT-03";
            }
            case "Obstacle Course" -> {
                code = "ACT-04";
            }
            case "Cavern Ziplining" -> {
                code = "ACT-05";
            }
            default -> {
                code = "ERR-ACT";
            }
        }
        return code;
    }

    /**
     * validateActivityCode() method
     *
     * @return validated activity code.
     *
     */
    // Method that sets and returns the activity code by performing validation.
    public String validateActivityCode() {
        //  Code needs to be validated
        //  Needs to be checked if it contains three capital letters seperated by a hyphen and then two digits.
        String activityCode = this.getCode();

        //  Char array.
        char[] codetoArray = activityCode.toCharArray();

        //  check to see if code is of the right length.
        //  loops through the first three indexes of the character array to check if the first three letters are capital letters.
        for (int index = 0; index < 3; index++) {
            if (!Character.isUpperCase(codetoArray[index])) {
                return "ERR_ACTIVITY_CODE\nError! Invalid activity code: The first three characters are not capital letters.";
            }
        }

        if (codetoArray[3] != '-') {
            return "ERR_ACTIVITY_CODE\nError! Invalid activity code: Hyphen missing.";
        }

        if (!Character.isDigit(codetoArray[4]) || !Character.isDigit(codetoArray[5])) {
            return "ERR_ACTIVITY_CODE\nError! Invalid activity code: Last two characters must be digits.";
        }

        //  else valid activity code
        return "Success! Valid Activity Code Entered: " + activityCode;

    }

    /**
     * setAddOnDetails() method
     *
     * @return addOnDetails.
     *
     */
    // Method that sets and returns addOn details by looping through the addOns list
    // calling the toString method, in the AddOn class, to add to a string variable which is then returned.
    public String setAddOnDetails() {
        String addOnDetails = "";
        for (AddOn activityAddOn : activityAddOnsList) {
            addOnDetails += activityAddOn.toString();
        }

        for (AddOn itineraryAddOn : itineraryAddOnsList) {
            addOnDetails += itineraryAddOn.toString();
        }

        // if the addOnDetails string remains empty
        // the string is set to an appropriate message and is then returned.
        if ("".equals(addOnDetails)) {
            addOnDetails = "No Add-Ons Applied}";
        }

        return addOnDetails;
    }

    /**
     * Override toString() method
     *
     * @return String of activity object details.
     *
     */
    @Override
    public String toString() {

        // isInsuranceRequired method is called to determine whether insurance is required for the specific activity instance. The true/false return value is then outputted in the toString return value.
        isInsuranceRequired();

        return "Activity Code: " + code + ", Title: {" + activityTitle + "}, Description: {" + activityDescription + "}, Location: {" + location + "}, Date: {" + activityDate + "}, Time: {" + activityTime + "}, Duration: {" + expectedDuration + "}, Insurance?: {" + insuranceRequired + "}" + ",\nAdd-Ons:\n{" + setAddOnDetails();
    }

}
