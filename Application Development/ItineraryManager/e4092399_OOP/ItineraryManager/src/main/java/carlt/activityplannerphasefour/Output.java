/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlt.activityplannerphasefour;

// Imports
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * The output class handles the generation of output data related to
 * itineraries, activities and add-ons within the activity planner system. It
 * has a numberToStrings attribute which is used for displaying attendee and
 * activity counts in a more readable format. It features methods that generates
 * an itinerary reference number, date, attendee information, costs and methods
 * that handle activities and add-on management.
 *
 * @author Carlt
 */
public class Output {

    String[] numberToStrings = {"One", "Two", "Three", "Four", "Five",
        "Six", "Seven", "Eight", "Nine", "Ten"
    };

    /**
     * generateItineraryRef() method - Method that returns a reference number
     * that is used in main to write to file.
     *
     * @param newItinerary
     * @return newItinerary.getReferenceNumber()
     */
    public String generateItineraryRef(Itinerary newItinerary) {
        return newItinerary.getReferenceNumber();
    }

    /**
     * generateItineraryDate() method - Method that returns an itinerary date
     * that is used in main to write to file.
     *
     * @param newItinerary
     * @return newItinerary.getItineraryDate()
     */
    public LocalDate generateItineraryDate(Itinerary newItinerary) {
        return newItinerary.getItineraryDate();
    }

    /**
     * generateItineraryQuarter() method - Method that returns a quarter of the
     * year for the itinerary associated with the user-inputted reference
     * number.
     *
     *
     * @param newItinerary
     * @param finalRefNumber
     * @return newItinerary.setItineraryQuarter(finalRefNumber)
     */
    public String generateItineraryQuarter(Itinerary newItinerary, String finalRefNumber) {
        return newItinerary.setItineraryQuarter(finalRefNumber);
    }

    /**
     * generateAttendeeName() method - Method that returns an attendee name that
     * is used in main to write to file.
     *
     * @param newItinerary
     * @return newItinerary.getNameOfLeadAttendee()
     */
    public String generateAttendeeName(Itinerary newItinerary) {
        return newItinerary.getNameOfLeadAttendee();
    }

    /**
     * generateNumberOfAttendees() method - Method that returns the number of
     * attendees for a new itinerary object.
     *
     * @param newItinerary
     * @return newItinerary.getNumOfAttendees()
     */
    public int generateNumberOfAttendees(Itinerary newItinerary) {
        return newItinerary.getNumOfAttendees();
    }

    /**
     * generateNumOfAttendeesString() method - Method that returns the
     * numOfAttendees as a number string by finding the number string associated
     * with the totalAttendees from the numberToStrings array.
     *
     *
     * @param newItinerary
     * @return numOfAttendees or null
     */
    public String generateNumOfAttendeesString(Itinerary newItinerary) {
        String numOfAttendees;
        int totalAttendees = newItinerary.getNumOfAttendees();
        // If the total attendees is smaller than the length of the numberToStrings array.
        if (totalAttendees < numberToStrings.length) {
            // The number of attendees is equal to the position of the total attendees in the itinerary - 1 in the numberToStrings array.
            numOfAttendees = numberToStrings[totalAttendees - 1];
            return numOfAttendees;
        } else {
            return null;
        }
    }

    /**
     * generateTotalItineraryCostInPence() method - Method that returns a String
     * that is used in main to write the total itinerary cost in pence to file.
     *
     * @param newItinerary
     * @return the itinerary total cost * 100 to two decimal place.
     */
    public String generateTotalItineraryCostInPence(Itinerary newItinerary) {
        return String.format("%.2f", newItinerary.calculateItineraryTotalCost() * 100);
    }

    /**
     * generateTotalItineraryCost() method - Method that returns a String that
     * is used in main to access the itinerary total cost in pounds rounded to
     * two decimal places.
     *
     *
     * @param newItinerary
     * @return itinerary total cost rounded to two decimal place.
     */
    public String generateTotalItineraryCost(Itinerary newItinerary) {
        return String.format("£%.2f", newItinerary.calculateItineraryTotalCost());
    }

    /**
     * generateBaseItineraryAddOnCost() method
     *
     * @param itineraryAddOnsList
     * @return the add on cost in pounds of a new itinerary AddOn object.
     */
    
    public double generateBaseItineraryAddOnCost(List<AddOn> itineraryAddOnsList) {
        double baseItineraryAddOnCost = 0;
        // Loops through the list of itinerary addOn objects
        for (AddOn addOn : itineraryAddOnsList) {
            // adds to the base itinerary add on cost
            baseItineraryAddOnCost += addOn.getAddOnCostInPounds();
        }
        return baseItineraryAddOnCost;
    }
    
     /**
     * generateInputtedItineraryBaseAddOnCost() method - Method that returns the base cost of all add-ons selected
     * in the user input phase by looping through the passed-in processedItineraryAddOns list.
     *
     * @param processedItineraryAddOns
     * @return baseActivityAddOnCost
     */

    public double generateInputtedItineraryBaseAddOnCost(List<String> processedItineraryAddOns) {
        double baseActivityAddOnCost = 0;
        for (String itineraryAddOn : processedItineraryAddOns) {
            switch (itineraryAddOn) {
                case "ACC" -> {
                    baseActivityAddOnCost = 17500.0;
                }
                case "CTB" -> {
                    baseActivityAddOnCost = 1500.0;
                }
                case "LUN" -> {
                    baseActivityAddOnCost = 400.0;
                }
                default -> {
                    baseActivityAddOnCost = 0;
                }
            }
            baseActivityAddOnCost /= 100;
        }
        return baseActivityAddOnCost;
    }

    /**
     * generateTotalAddOnCost() method
     *
     * @param newAddOn
     * @param newItinerary
     * @param itineraryAddOnsList
     * @return the value of the base add-on cost of a new AddOn object
     * multiplied by the number of attendees for a new Itinerary object.
     */
    public double generateTotalAddOnCost(AddOn newAddOn, Itinerary newItinerary, List<AddOn> itineraryAddOnsList) {
        return generateBaseItineraryAddOnCost(itineraryAddOnsList) * generateNumberOfAttendees(newItinerary);
    }

    /**
     * generateAddOnSubtotal() method
     *
     * @param newItinerary
     * @param itineraryAddOnsList
     * @return the value of the add on cost of a new AddOn object multiplied by
     * the size of the addOnsList collection.
     */
    public double generateItineraryAddOnSubtotal(Itinerary newItinerary, List<AddOn> itineraryAddOnsList) {
        int numOfAttendees = newItinerary.getNumOfAttendees();
        double itinerarySubtotal = 0;
        for (AddOn addOn : itineraryAddOnsList) {
            double addOnCost = addOn.getAddOnCost();
            itinerarySubtotal += addOnCost * numOfAttendees;
        }
        return itinerarySubtotal;
    }
    
     /**
     * generateInputtedItineraryAddOnSubtotal() method - Method that returns the subtotal of the user-inputted itinerary.
     *
     * 
     * @param newItinerary
     * @param processedItineraryAddOns
     * @return itinerarySubtotal
     */

    public double generateInputtedItineraryAddOnSubtotal(Itinerary newItinerary, List<String> processedItineraryAddOns) {
        int numOfAttendees = newItinerary.getNumOfAttendees();
        double itinerarySubtotal = 0;
        double addOnCost = 0;
        double addOnCostInPounds;
        // loops through the list of user-selected itinerary add-ons
        for (String itineraryAddOn : processedItineraryAddOns) {
            switch (itineraryAddOn) {
                // adds to the cost based on the selected add-on codes.
                case "ACC" -> {
                    addOnCost = 17500.0;
                }
                case "CTB" -> {
                    addOnCost = 1500.0;
                }
                case "LUN" -> {
                    addOnCost = 400.0;
                }
                default -> {
                    addOnCost = 0;
                }
            }
            // Divides the total cost by 100 to get the addOnCostInPounds.
            addOnCostInPounds = addOnCost / 100;
            // multiplies the final cost by the number of attendees in the itinerary.
            itinerarySubtotal += (addOnCostInPounds * numOfAttendees);
        }
        return itinerarySubtotal;
    }

    /**
     * generateAddOnName() method
     *
     * @param itineraryAddOnsList
     * @return the add on title of a new AddOn object.
     */
    
    public String generateItineraryAddOnName(List<AddOn> itineraryAddOnsList) {
        String itineraryAddOnName = "";
        // loops through the list of itinerary add-ons objects
        for (AddOn addOn : itineraryAddOnsList) {
            // gets the title of the itinerary add-on objects.
            itineraryAddOnName = addOn.getAddOnTitle();
        }
        return itineraryAddOnName;
    }
    
     /**
     * generateInputtedItineraryAddOnName() method - Method that returns a string builder of itinerary add-on names.
     * These names are retrieved by looping through the processedItineraryAddOns list and getting the name of each addOn object stored there
     * by using the helper method setAddOnTitleFromCode from the AddOn class.
     *
     * 
     * @param processedItineraryAddOns
     * @param addOn
     * @return itineraryAddOnNames.toString()
     */

    public String generateInputtedItineraryAddOnName(List<String> processedItineraryAddOns, AddOn addOn) {
        StringBuilder itineraryAddOnNames = new StringBuilder();
        // loops through the list of user-selected itinerary add-ons
        for (String itineraryAddOn : processedItineraryAddOns) {
            // sets the name of each itinerary add-on
            String addOnName = addOn.setAddOnTitleFromCode(itineraryAddOn.trim());
            if (addOnName != null) {
                if (itineraryAddOnNames.length() > 0) {
                    // adds a new line with a pipe border 
                    itineraryAddOnNames.append("\n| - ");
                }
                // appends the addOnName to the string builder.
                itineraryAddOnNames.append(addOnName);
            }
        }
        return itineraryAddOnNames.toString();
    }

    /**
     * generateTotalActivities() method - Method that returns a String that is
     * used in main to write the total number of activities to file.
     *
     * @param newItinerary
     * @return newItinerary.getNumOfActivities()
     */
    public int generateTotalActivities(Itinerary newItinerary) {
        return newItinerary.getNumOfActivities();
    }

    /**
     * generateTotalActivitiesString() method
     *
     *
     * @param newItinerary
     * @return totalString if the totalActivities is smaller than the length of
     * the numberToStrings array. Returns String.valueOf the totalActivities
     * integer if the totalActivities is larger than five. Else it returns null.
     */
    public String generateTotalActivitiesString(Itinerary newItinerary) {
        String totalString;
        int totalActivities = newItinerary.getNumOfActivities();
        if (totalActivities < numberToStrings.length) {
            totalString = numberToStrings[totalActivities - 1];
            return totalString;
        }
        if (totalActivities > 5) {
            // returns the number string e.g. 5 = five.
            return String.valueOf(totalActivities);
        } else {
            return null;
        }
    }

    /**
     * generateActivitiesSubtotal() method
     *
     *
     * @param newActivity
     * @param activityCodesList
     * @param newItinerary
     * @return the activity base cost of the new activity object dependant on
     * its activity code.
     */
    public double generateActivitiesSubtotal(Activity newActivity, List<String> activityCodesList, Itinerary newItinerary) {
        return newActivity.setActivityBaseCostByCode(activityCodesList) * newItinerary.getNumOfAttendees();
    }
    
     /**
     * generateInputtedActivitiesSubtotal() method - The method returns the subtotal of all the user-inputted activities.
     * It does this by looping through the activityAddOnsMap to get access to the keys and values stored (the activity and add-on codes).
     * It checks to see if the instantiated sets of activity and add-on codes contain the keys and values stored in the map. The method then
     * calls the helper methods setBaseActivityCostByMapCode and setAddOnCostByMapCode to calculate the total base activity and add-on costs,
     * which are then used in the final calculation for the totalActivitesSubtotal.
     *
     * 
     * @param newActivity
     * @param newItinerary
     * @param selectedActivities
     * @param activityAddOnsMap
     * @return totalActivitiesSubtotal
     */

    public double generateInputtedActivitiesSubtotal(Activity newActivity, Itinerary newItinerary, String[] selectedActivities, Map<String, String> activityAddOnsMap) {
        int numOfAttendees = newItinerary.getNumOfAttendees();
        double baseActivityCost = 0;
        double addOnCost = 0;
        double totalActivitiesSubtotal = 0;
        
        // Sets are created to store all of the activity and add-on codes.
        Set<String> allActivityCodes = Set.of("ACT-01", "ACT-02", "ACT-03", "ACT-04", "ACT-05");
        Set<String> allAddOnCodes = Set.of("TRV", "INS", "PHO", "GRT", "GBG", "ACC", "CTB");
        
        // Loops through the activityAddOnsMap
        for (Map.Entry<String, String> entry : activityAddOnsMap.entrySet()) {
            // Checks if the activity codes set contains any of the keys stored in the map entries.
            if (allActivityCodes.contains(entry.getKey())) {
                // Calculates the base activity cost by calling a helper method and passing in the current entry key and the itinerary's number of attendees.
                baseActivityCost += setBaseActivityCostByMapCode(entry.getKey(), numOfAttendees);
            }
            // Checks if the add-on codes set contains any of the values stored in the map entries.
            if (allAddOnCodes.contains(entry.getValue())) {
                // Calculates the base add on cost by calling a helper method and passing in the current entry value and the itinerary's number of attendees.
                addOnCost += setAddOnCostByMapCode(entry.getValue(), numOfAttendees);
            }
        }
        totalActivitiesSubtotal = (baseActivityCost + addOnCost) / 100;
        return totalActivitiesSubtotal;
    }
    
    /**
     * setBaseActivityCostByMapCode() method - This method returns the totalBaseActivityCost of the user-inputted activities by passing in an activityCode
     * from the activityAddOns map and the number of attendees for the itinerary. The calculation used is the base activity cost (in pence) for the activity
     * multiplied by the number of attendees.
     * 
     * 
     * @param activityCode
     * @param numOfAttendees
     * @return totalBaseActivityCost
     */

    public double setBaseActivityCostByMapCode(String activityCode, int numOfAttendees) {
        double totalBaseActivityCost = 0.0;
        switch (activityCode) {
            case "ACT-01" -> {
                totalBaseActivityCost = 2500.0 * numOfAttendees;
            }
            case "ACT-02" -> {
                totalBaseActivityCost = 7500.0 * numOfAttendees;
            }
            case "ACT-03" -> {
                totalBaseActivityCost = 15000.0 * numOfAttendees;
            }
            case "ACT-04" -> {
                totalBaseActivityCost = 9000.0 * numOfAttendees;
            }
            case "ACT-05" -> {
                totalBaseActivityCost = 17500.0 * numOfAttendees;
            }
            default -> {
                totalBaseActivityCost += 0.0;
            }
        }
        return totalBaseActivityCost;
    }
    
     /**
     * setAddOnCostByMapCode() method - This method returns the total add-on cost of the user-inputted add-ons
     * by passing in an addOnCode from the activityAddOns map and the number of attendees for the itinerary. The calculation
     * used is the base add-on cost (in pence) multiplied by the number of attendees.
     *
     *
     * 
     * @param addOnCode
     * @param numOfAttendees
     * @return totalAddOnCost
     */

    public double setAddOnCostByMapCode(String addOnCode, int numOfAttendees) {
        double totalAddOnCost = 0.0;
        switch (addOnCode) {
            case "TRV" -> totalAddOnCost += 1200.0;
            case "INS" -> totalAddOnCost += 9000.0;
            case "PHO" -> totalAddOnCost += 15000.0;
            case "GRT" -> totalAddOnCost += 1000.0;
            case "GBG" -> totalAddOnCost += 500.0;
            case "ACC" -> totalAddOnCost += 17500.0;
            case "CTB" -> totalAddOnCost += 1500.0;
            case "LUN" -> totalAddOnCost += 400.0;
            default -> totalAddOnCost += 0;
        }
        return totalAddOnCost;
    }

    /**
     * generateActivitiesAddOnCodes() method - Method that returns a String that is
     * used in main to write the code (and add-on code if the activity has
     * add-ons selected) of each activity to file.
     *
     * @param activity
     * @param addOnsList
     * @param itineraryAddOnsList
     * @return output.toString() - string builder
     */
    
    public String generateActivitiesAddOnCodes(Activity activity, List<AddOn> addOnsList, List<AddOn> itineraryAddOnsList) {
        // Combines two add-on lists into a single list using the .stream method.
        List<AddOn> allAddOns = Stream.concat(addOnsList.stream(), itineraryAddOnsList.stream()).toList();
        StringBuilder output = new StringBuilder(activity.getCode() + ": ");
        boolean hasAddOns = false;
        // Loops through the addOn objects stored in the allAddOns list
        for (AddOn addOn : allAddOns) {
            // Checks if the addOnCode is not null, not empty and if the addon activity code is equal to the associated activity code.
            if (addOn.getAddOnCode() != null && !addOn.getAddOnCode().isEmpty() && addOn.getAddOnActivityCode().equals(activity.getCode())) {
                if (hasAddOns) {
                    output.append(", ");
                }
                // appends the add-on code to the string builder.
                output.append(addOn.getAddOnCode());
                hasAddOns = true;
            }
        }
        // else no add-ons applied.
        if (!hasAddOns) {
            output.append("No Add-Ons Applied!");
        }
        return output.toString();
    }

    /**
     * generateActivityAndAddOnRows() method - Method that returns a String is used in
     * main to generate the activity rows of the itinerary summary console
     * output.
     *
     *
     * @param lines
     * @param activities
     * @param newItinerary
     * @param newAddOn
     * @param activityAddOnsList
     * @return activitySubtotals.toString() - string builder.
     */
    
    public List<String> generateActivityAndAddOnRows(List<String> lines, List<Activity> activities, Itinerary newItinerary, AddOn newAddOn, List<AddOn> activityAddOnsList) {
        double activitySubtotal = 0;
        // Loops through the list of activity objects
        for (Activity activity : activities) {
            // adds the activity line to the string builder
            // activity number, activity title, activity base cost, number of attendees.
            lines.add(String.format(
                    "%d. %-28s @ £%.2f x %-2d",
                    generateActivityNumber(activity),
                    activity.getActivityTitle(),
                    activity.getBaseActivityCost(),
                    newItinerary.getNumOfAttendees()
            ));
            boolean hasAddOns = false;
            // Loops through the list of add-on objects
            for (AddOn addOn : activityAddOnsList) {
                // Checks if the activity code of the add-on objects are equal to the activity codes.
                if (addOn.getAddOnActivityCode().equals(activity.getCode())) {
                    // adds the add-on lines to the string builder.
                    // add-on title, add-on cost in pounds, number of attendees.
                    lines.add(String.format(
                            "%-10s @ £%.2f x %d",
                            "Add-On: " + addOn.getAddOnTitle(),
                            addOn.getAddOnCostInPounds(),
                            newItinerary.getNumOfAttendees()
                    ));
                    // Flag is set to true.
                    hasAddOns = true;
                }
                if (hasAddOns) {
                    // Calculates the activitySubtotal including the add-on costs.
                    activitySubtotal = (activity.getBaseActivityCost() * newItinerary.getNumOfAttendees()) + (addOn.getAddOnCostInPounds() * newItinerary.getNumOfAttendees());
                }
            }
            if (!hasAddOns) {
                // Calculates the activitySubtotal excluding add-on costs since there are no add-ons selected.
                activitySubtotal = activity.getBaseActivityCost() * newItinerary.getNumOfAttendees();
                // Checks if insurance is required for the activity object.
                if (activity.isInsuranceRequired()) {
                    lines.add("** Insurance Required - No add-on selected **");
                } else {
                    lines.add("Add-On: No Add-Ons Selected");
                }
            }
            // Adds the sub-total line.
            String subtotalString = "Sub-Total";
            lines.add(String.format("%41s: £%.2f", subtotalString, activitySubtotal));
        }
        return lines;
    }
    
     /**
     * generateInputtedActivityAndAddOnRows() method - This method generates formatted text rows that display information about
     * activities and their associated add-ons for an itinerary.
     * 
     * @param newActivity
     * @param lines
     * @param activityAddOnsMap
     * @param newItinerary
     * @param newAddOn
     * @return lines
     */

    public List<String> generateInputtedActivityAndAddOnRows(Activity newActivity, List<String> lines, Map<String, String> activityAddOnsMap, Itinerary newItinerary, AddOn newAddOn) {
        
        String activityTitle;
        char activityNumber;
        double baseActivityCost;
        String addOnTitle;
        double addOnCost;
        boolean hasAddOns;
        double addOnsSubtotal;
        
        // Creates an arraylist of all the activity codes.
        List<String> allActivityCodesList = new ArrayList<>();
        allActivityCodesList.add("ACT-01");
        allActivityCodesList.add("ACT-02");
        allActivityCodesList.add("ACT-03");
        allActivityCodesList.add("ACT-04");
        allActivityCodesList.add("ACT-05");
        
        // Creates an arraylist of all the activity add-on codes.
        List<String> allActivityAddOnCodes = new ArrayList<>();
        allActivityAddOnCodes.add("TRV");
        allActivityAddOnCodes.add("INS");
        allActivityAddOnCodes.add("PHO");
        allActivityAddOnCodes.add("GRT");
        allActivityAddOnCodes.add("GBG");
        
        // Loops through the arraylist of activity codes
        for (String activityCode : allActivityCodesList) {
            // Checks if the keys stored in the activityAddOnsMap is equal to the current activity code.
            if (activityAddOnsMap.containsKey(activityCode)) {
                // Gets the activity number from each activity code by extracting the last character.
                activityNumber = activityCode.charAt(activityCode.length() - 1);
                // Generates the activity title from the activity number using a helper method.
                activityTitle = generateActivityTitleFromActivityNumber(activityNumber);
                baseActivityCost = setBaseActivityCostByMapCode(activityCode, newItinerary.getNumOfAttendees());
                boolean insuranceRequired = isInsuranceRequiredForInputtedActivity(activityTitle);
                // Adds the activity lines to the lines arraylist.
                // Activity number, activity title, the base activity cost in pounds and the numher of attendees.
                lines.add(String.format("%c. %-28s @ £%.2f x %-2d",
                        activityNumber,
                        activityTitle,
                        baseActivityCost / 100,
                        newItinerary.getNumOfAttendees()
                ));
                addOnsSubtotal = 0;
                hasAddOns = false;
                // Loops through the activity add-ons map
                for (Map.Entry<String, String> entry : activityAddOnsMap.entrySet()) {
                    // Checks if the key at the current entry is equal to the current activity code.
                    if (entry.getKey().equals(activityCode)) {
                        // The add-on code is equal to the current value in the map.
                        String addOnCode = entry.getValue();
                        addOnCost = setAddOnCostByMapCode(addOnCode, newItinerary.getNumOfAttendees());
                        addOnTitle = generateAddOnTitleFromAddOnCode(addOnCode);
                        if (addOnTitle != null) {
                            // Adds the add-on lines to the lines arraylist.
                            // Add-on title, add-on cost in pounds and the number of attendees
                            lines.add(String.format(
                                    "Add-On: %-24s @ £%.2f x %d",
                                    addOnTitle,
                                    addOnCost / 100,
                                    newItinerary.getNumOfAttendees()
                            ));
                            hasAddOns = true;
                            // Adds to the add-on subtotal value.
                            addOnsSubtotal += (addOnCost / 100) * newItinerary.getNumOfAttendees();
                        }
                    }
                }
                // If there is no add-ons selected
                if (!hasAddOns) {
                    // Checks to see if insurance is required for the current activity.
                    if (insuranceRequired) {
                        lines.add("** Insurance Required - No add-on selected **");
                    } else {
                        lines.add("Add-On: No Add-Ons Selected");
                    }
                }
                // Calculates the activity subtotal.
                double activitySubtotal = (baseActivityCost / 100 * newItinerary.getNumOfAttendees()) + addOnsSubtotal;
                // Adds the activity subtotal line to the lines arraylist.
                lines.add(String.format("%41s: £%.2f", "Sub-Total", activitySubtotal));
            }
        }
        return lines;
    }
    
    /**
     * generateActivityTitleFromActivityNumber() method - Method that returns the activity title
     * from the activityNumber parameter.
     * 
     * 
     * @param activityNumber
     * @return activityTitle
     */
    public String generateActivityTitleFromActivityNumber(char activityNumber) {
        String activityTitle;
        activityTitle = switch (activityNumber) {
            case '1' -> "Building a bridge from paper";
            case '2' -> "Cookery Classes";
            case '3' -> "SAS-Style Assault Courses";
            case '4' -> "Obstacle Course";
            case '5' -> "Cavern Ziplining";
            default -> null;
        };
        return activityTitle;
    }
    
    /**
     * generateAddOnTitleFromAddOnCode() method - This method returns the add-on title from
     * the addOnCode parameter.
     * 
     * 
     * @param addOnCode
     * @return addOnTitle
     */

    public String generateAddOnTitleFromAddOnCode(String addOnCode) {
        String addOnTitle;
        addOnTitle = switch (addOnCode) {
            case "TRV" -> "Travel";
            case "INS" -> "Insurance";
            case "PHO" -> "Photography";
            case "GRT" -> "Gear Rentals";
            case "GBG" -> "Gift Bag";
            default -> null;
        };
        return addOnTitle;
    }
    
     /**
     * isInsuranceRequiredForInputtedActivity() method - This method returns a boolean value for insuranceRequired
     * to determine whether insurance is required for the inputted activity.
     * 
     * 
     * @param activityTitle
     * @return insuranceRequired
     */

    public boolean isInsuranceRequiredForInputtedActivity(String activityTitle) {
        boolean insuranceRequired;
        switch (activityTitle) {
            case "Building a bridge from paper" -> {
                insuranceRequired = false;
                break;
            }
            case "Cookery Classes" -> {
                insuranceRequired = false;
                break;
            }
            case "SAS-Style Assault Courses" -> {
                insuranceRequired = true;
                break;
            }
            case "Obstacle Course" -> {
                insuranceRequired = true;
                break;
            }
            case "Cavern Ziplining" -> {
                insuranceRequired = true;
                break;
            }
            default -> {
                insuranceRequired = false;
            }
        }
        return insuranceRequired;
    }

    /**
     * generateAddOnRows() method - Method that returns a String used in main to
     * generate the add on rows of the itinerary summary console output.
     *
     *
     * @param addOnsList
     * @param newAddOn
     * @param newItinerary
     * @return addOnSubtotals.toString() - string builder
     */
    public String generateAddOnRows(List<AddOn> addOnsList, AddOn newAddOn, Itinerary newItinerary) {
        StringBuilder addOnSubtotals = new StringBuilder();
        // Loops through the list of addOn objects
        for (AddOn addOn : addOnsList) {
            // Adds the add-on lines to the string builder.
            // Add-On title, add-on cost.
            addOnSubtotals.append(String.format("| Add-on: %s @ £%.2f|\n",
                    addOn.getAddOnTitle(),
                    addOn.getAddOnCost()));
        }
        return addOnSubtotals.toString();
    }

    /**
     * generateActivityNumber() method
     *
     *
     * @param activity
     * @return the activity number from its position in the number array.
     */
    public int generateActivityNumber(Activity activity) {
        Integer[] numberArray = {1, 2, 3, 4, 5};
        int activityNumber;
        switch (activity.getActivityTitle()) {
            case "Building a bridge from paper" -> {
                activityNumber = numberArray[0];
                return activityNumber;
            }
            case "Cookery Classes" -> {
                activityNumber = numberArray[1];
                return activityNumber;
            }
            case "SAS-Style Assault Courses" -> {
                activityNumber = numberArray[2];
                return activityNumber;
            }
            case "Obstacle Course" -> {
                activityNumber = numberArray[3];
                return activityNumber;
            }
            case "Cavern Ziplining" -> {
                activityNumber = numberArray[4];
                return activityNumber;
            }
            default ->
                activityNumber = 0;
        }
        return activityNumber;
    }

    /**
     * generateDiscountNumber() method
     *
     * @param newItinerary
     * @return the discount percentage of a new Itinerary object multiplied by
     * 100 and casted to an integer.
     */
    public int generateDiscountNumber(Itinerary newItinerary) {
        return (int) (newItinerary.setItineraryDiscountPercentage() * 100);
    }

    /**
     * generateDiscountSubtotal() method
     * @param newItinerary
     * @return the total discount applied to a new Itinerary object.
     */
    
    public double generateDiscountSubTotal(Itinerary newItinerary) {
        return newItinerary.setDiscountOutput();
    }
    
    /**
     * generateInputtedDiscountSubTotal() method - This method calculats the discount amount applied to the user-inputted itinerary.
     *
     * 
     * @param totalCost
     * @param processedItineraryAddOns
     * @param newActivity
     * @param newItinerary
     * @param selectedActivities
     * @param activityAddOnsMap
     * @return discountSubtotal
     */

    public double generateInputtedDiscountSubTotal(String totalCost, List<String> processedItineraryAddOns, Activity newActivity, Itinerary newItinerary,
                                                    String[] selectedActivities, Map<String, String> activityAddOnsMap) {
        String originalCostString = totalCost;
        // Overwrites the originalCostString to get rid of the £ sign so it can be parsed to a double.
        originalCostString = originalCostString.replace("£", "");
        double discountSubtotal = 0;
        // The itinerary base cost with add-ons is calculated by adding the results of two helper methods together.
        double itineraryBaseCostWithAddOns =
                generateInputtedActivitiesSubtotal(newActivity, newItinerary, selectedActivities, activityAddOnsMap)
                + generateInputtedItineraryAddOnSubtotal(newItinerary, processedItineraryAddOns);
        // The itinerary cost with discount is set to the originalCostString, parsed as double.
        double itineraryCostWithDiscount = Double.parseDouble(originalCostString);
        discountSubtotal = itineraryBaseCostWithAddOns - itineraryCostWithDiscount;
        return discountSubtotal;
    }
}
