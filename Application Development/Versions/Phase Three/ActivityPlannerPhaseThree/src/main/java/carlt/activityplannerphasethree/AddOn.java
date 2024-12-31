/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlt.activityplannerphasethree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlt
 */
public class AddOn {
    
    private static final List<String> activityAddOnCodes = new ArrayList<>();
    private static final List<String> itineraryAddOnCodes = new ArrayList<>();
    
    static{
        activityAddOnCodes.add("TRV");
        activityAddOnCodes.add("INS");
        activityAddOnCodes.add("PHO");
        activityAddOnCodes.add("GRT");
        activityAddOnCodes.add("GBG");
        
        itineraryAddOnCodes.add("ACC");
        itineraryAddOnCodes.add("CTB");
        itineraryAddOnCodes.add("LUN");
    }
    
    //  Attributes
    private String addOnType;
    private String addOnTitle;
    private String addOnCode;
    private double addOnCost;
    private double addOnCostInPounds;

    //  Default Constructor
    public AddOn() {
        this.addOnType = "Activity";
        this.addOnTitle = "Travel";
        this.addOnCode = "INS";
        this.addOnCost = 0;
        this.addOnCostInPounds = 0;
    }

    //  Constructor
    public AddOn(String addOnType, String addOnTitle, String addOnCode, double addOnCost, double addOnCostInPounds) {
        this.addOnType = addOnType;
        this.addOnTitle = addOnTitle;
        this.addOnCode = addOnCode;
        this.addOnCost = addOnCost;
        this.addOnCostInPounds = addOnCostInPounds;
    }

    //  Methods
    //  Getters
    
    public static List<String> getActivityAddOnCodesList(){
        return new ArrayList<>(activityAddOnCodes);
    }
    
    public static List<String> getItineraryAddOnCodesList(){
        return new ArrayList<>(itineraryAddOnCodes);
    }
    
    /**
     * getAddOnType() method
     *
     * @return addOnType
     */
    public String getAddOnType() {
        return addOnType;
    }

    /**
     * getAddOnTitle() method
     *
     * @return addOnTitle
     */
    public String getAddOnTitle() {
        return addOnTitle;
    }

    /**
     * getAddOnCode() method
     *
     * @return addOnCode
     */
    public String getAddOnCode() {
        return addOnCode;
    }

    /**
     * getAddOnCost() method
     *
     * @return addOnCost(stored in pence) divided by 100 to make cost equivalent
     * to pounds.
     */
    public double getAddOnCost() {
        return addOnCost / 100;
    }

    /**
     * getAddOnCostInPounds() method
     *
     * @return addOnCostInPounds
     */
    public double getAddOnCostInPounds() {
        return addOnCostInPounds;
    }

    /**
     * setAddOnCostAndType() method The method sets the add-on cost and the
     * add-on type dependant on the add-on that is selected.
     *
     */
    public double setAddOnCostAndType() {

        String addOn = this.getAddOnTitle();

        switch (addOn) {
            case "Travel" -> {
                //  Costs are stored in pence
                addOnCost = 1200.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Activity";
                return addOnCostInPounds;
            }
            case "Insurance" -> {
                addOnCost = 9000.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Activity";
                return addOnCostInPounds;
            }
            case "Photography" -> {
                addOnCost = 15000.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Activity";
                return addOnCostInPounds;
            }
            case "Gear Rentals" -> {
                addOnCost = 1000.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Activity";
                return addOnCostInPounds;
            }
            case "Gift Bag" -> {
                addOnCost = 500.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Itinerary";
                return addOnCostInPounds;
            }
            case "Accommodation" -> {
                addOnCost = 17500.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Itinerary";
                return addOnCostInPounds;
            }
            case "Coffee/Tea Breaks" -> {
                addOnCost = 1500.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Itinerary";
                return addOnCostInPounds;
            }
            case "Lunch" -> {
                addOnCost = 400.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Itinerary";
                return addOnCostInPounds;
            }
            default -> {
                addOnCost = 0;
                addOnCostInPounds = 0;
                addOnType = "NULL_TYPE";
            }
        }
        return addOnCostInPounds;

    }
    
    public String addOnBaseCostOutput(String addOnTitle) {

        switch (addOnTitle) {
            case "Travel" -> {
                //  Costs are stored in pence
                addOnCost = 1200.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Activity";
                return String.format("£%.2f", addOnCostInPounds);
            }
            case "Insurance" -> {
                addOnCost = 9000.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Activity";
                return String.format("£%.2f", addOnCostInPounds);
            }
            case "Photography" -> {
                addOnCost = 15000.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Activity";
                return String.format("£%.2f", addOnCostInPounds);
            }
            case "Gear Rentals" -> {
                addOnCost = 1000.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Activity";
                return String.format("£%.2f", addOnCostInPounds);
            }
            case "Gift Bag" -> {
                addOnCost = 500.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Itinerary";
                return String.format("£%.2f", addOnCostInPounds);
            }
            case "Accommodation" -> {
                addOnCost = 17500.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Itinerary";
                return String.format("£%.2f", addOnCostInPounds);
            }
            case "Coffee/Tea Breaks" -> {
                addOnCost = 1500.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Itinerary";
                return String.format("£%.2f", addOnCostInPounds);
            }
            case "Lunch" -> {
                addOnCost = 400.0;
                addOnCostInPounds = (addOnCost / 100);
                addOnType = "Itinerary";
                return String.format("£%.2f", addOnCostInPounds);
            }
            default -> {
                addOnCost = 0;
                addOnCostInPounds = 0;
                addOnType = "NULL_TYPE";
                return "£ERROR";
            }
        }
     
    }


    /**
     * setAddOnCode() method The method sets an add-on code for an add-on
     * dependant on the add-on that is selected.
     */
    public void setAddOnCode() {
        String addOn = this.getAddOnTitle();

        switch (addOn) {
            case "Travel" -> {
                addOnCode = activityAddOnCodes.get(0);
                break;
            }
            case "Insurance" -> {
                addOnCode = activityAddOnCodes.get(1);
                break;
            }
            case "Photography" -> {
                addOnCode = activityAddOnCodes.get(2);
                break;
            }
            case "Gear Rentals" -> {
                addOnCode = activityAddOnCodes.get(3);
                break;
            }
            case "Gift Bag" -> {
                addOnCode = activityAddOnCodes.get(4);
                break;
            }
            case "Accommodation" -> {
                addOnCode = itineraryAddOnCodes.get(0);
                break;
            }
            case "Coffee/Tea Breaks" -> {
                addOnCode = itineraryAddOnCodes.get(1);
                break;
            }
            case "Lunch" -> {
                addOnCode = itineraryAddOnCodes.get(2);
                break;
            }
            default -> {
                addOnCode = null;
                break;
            }
        }
    }
    
    public String setAddOnTitleFromCode(String addOnCode){
        addOnTitle = switch (addOnCode) {
            case "TRV" -> "Travel";
            case "INS" -> "Insurance";
            case "PHO" -> "Photography";
            case "GRT" -> "Gear Rentals";
            case "GBG" -> "Gift Bag";
            case "ACC" -> "Accommodation";
            case "CTB" -> "Coffee/Tea Breaks";
            case "LUN" -> "Lunch";
            default -> null;
        };
        
        return addOnTitle;
    }
    
    

    /**
     * Override toString() method
     *
     * @return String of add-on object details.
     *
     */
    @Override
    public String toString() {
        return addOnTitle + "}" + ", Type: {" + addOnType + "}" + ", Code: {" + addOnCode + "}" + String.format(", Cost: {£%.2f", addOnCostInPounds) + "} ";
    }
}
