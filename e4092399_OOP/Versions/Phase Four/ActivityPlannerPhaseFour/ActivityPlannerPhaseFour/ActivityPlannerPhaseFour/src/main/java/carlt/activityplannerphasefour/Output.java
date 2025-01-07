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
        if (totalAttendees < numberToStrings.length) {
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
     * @return String
     */
    public String generateTotalItineraryCostInPence(Itinerary newItinerary) {

        return String.format("%.1f", newItinerary.calculateItineraryTotalCost() * 100);
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
     * generateBaseAddOnCost() method
     *
     * @param itineraryAddOnsList
     * @return the add on cost in pounds of a new AddOn object.
     */
    public double generateBaseItineraryAddOnCost(List<AddOn> itineraryAddOnsList) {

        double baseActivityAddOnCost = 0;

        for (AddOn addOn : itineraryAddOnsList) {
            baseActivityAddOnCost += addOn.getAddOnCostInPounds();
        }

        return baseActivityAddOnCost;
    }

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

        // Wanna access the selected itinerary add-ons from the itineraryAddOnsList and their prices.
        for (AddOn addOn : itineraryAddOnsList) {
            double addOnCost = addOn.getAddOnCost();

            itinerarySubtotal += addOnCost * numOfAttendees;
        }

        return itinerarySubtotal;

    }

    public double generateInputtedItineraryAddOnSubtotal(Itinerary newItinerary, List<String> processedItineraryAddOns) {

        int numOfAttendees = newItinerary.getNumOfAttendees();
        double itinerarySubtotal = 0;
        double addOnCost = 0;
        double addOnCostInPounds;

        for (String itineraryAddOn : processedItineraryAddOns) {
            switch (itineraryAddOn) {
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
            addOnCostInPounds = addOnCost / 100;
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

        for (AddOn addOn : itineraryAddOnsList) {
            itineraryAddOnName = addOn.getAddOnTitle();
        }

        return itineraryAddOnName;
    }

    public String generateInputtedItineraryAddOnName(List<String> processedItineraryAddOns, AddOn addOn) {

        StringBuilder itineraryAddOnNames = new StringBuilder();

        for (String itineraryAddOn : processedItineraryAddOns) {
            String addOnName = addOn.setAddOnTitleFromCode(itineraryAddOn.trim());
            if (addOnName != null) {
                if (itineraryAddOnNames.length() > 0) {
                    itineraryAddOnNames.append("\n| - ");
                }
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

    public double generateInputtedActivitiesSubtotal(Activity newActivity, Itinerary newItinerary, String[] selectedActivities, Map<String, String> activityAddOnsMap) {

        int numOfAttendees = newItinerary.getNumOfAttendees();

        double baseActivityCost = 0;
        double addOnCost = 0;
        double totalActivitiesSubtotal = 0;

        Set<String> allActivityCodes = Set.of("ACT-01", "ACT-02", "ACT-03", "ACT-04", "ACT-05");
        Set<String> allAddOnCodes = Set.of("TRV", "INS", "PHO", "GRT", "GBG", "ACC", "CTB");

        // Key = ACTIVITY CODE, VALUE = ADD-ON CODE
        // Calculate the subtotal from that
        for (Map.Entry<String, String> entry : activityAddOnsMap.entrySet()) {

            if (allActivityCodes.contains(entry.getKey())) {
                baseActivityCost += setBaseActivityCostByMapCode(entry.getKey(), numOfAttendees);
            }
            if (allAddOnCodes.contains(entry.getValue())) {
                addOnCost += setAddOnCostByMapCode(entry.getValue(), numOfAttendees);
            }
        }

        totalActivitiesSubtotal = (baseActivityCost + addOnCost) / 100;

        return totalActivitiesSubtotal;
    }

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

    public double setAddOnCostByMapCode(String addOnCode, int numOfAttendees) {
        double totalAddOnCost = 0.0;

        switch (addOnCode) {
            case "TRV":
                totalAddOnCost += 1200.0;
                break;
            case "INS":
                totalAddOnCost += 9000.0;
                break;
            case "PHO":
                totalAddOnCost += 15000.0;
                break;
            case "GRT":
                totalAddOnCost += 1000.0;
                break;
            case "GBG":
                totalAddOnCost += 500.0;
                break;
            case "ACC":
                totalAddOnCost += 17500.0;
                break;
            case "CTB":
                totalAddOnCost += 1500.0;
                break;
            case "LUN":
                totalAddOnCost += 400.0;
                break;
            default:
                totalAddOnCost += 0;
        }

        return totalAddOnCost;
    }

    /**
     * generateActivitiesCode() method - Method that returns a String that is
     * used in main to write the code (and add-on code if the activity has
     * add-ons selected) of each activity to file.
     *
     * @param activity
     * @param addOnsList
     * @param itineraryAddOnsList
     * @return output.toString() - string builder
     */
    public String generateActivitiesAddOnCodes(Activity activity, List<AddOn> addOnsList, List<AddOn> itineraryAddOnsList) {

        List<AddOn> allAddOns = Stream.concat(addOnsList.stream(), itineraryAddOnsList.stream()).toList();

        // New instance of StringBuilder class is instantiated to take the activityCode and ": " for formatting.
        StringBuilder output = new StringBuilder(activity.getCode() + ": ");

        // temporary boolean
        boolean hasAddOns = false;

        // Loops through the addOnsList to retrieve each addOn object.
        for (int index = 0; index < allAddOns.size(); index++) {
            AddOn addOn = allAddOns.get(index);

            // If the code returned from each addOn object is not null and not empty
            if (addOn.getAddOnCode() != null && !addOn.getAddOnCode().isEmpty() && addOn.getAddOnActivityCode().equals(activity.getCode())) {
                if (hasAddOns) {
                    // Adds comma to string builder for formatting.
                    output.append(", ");
                }
                // adds the addOn code from each addOn object to the string builder.
                output.append(addOn.getAddOnCode());
                hasAddOns = true;
            }

        }

        if (!hasAddOns) {
            output.append("No Add-Ons Applied!");
        }

        // return string builder.
        return output.toString();

    }

    /**
     * generateActivityRows() method - Method that returns a String is used in
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

        for (Activity activity : activities) {

            lines.add(String.format(
                    "%d. %-28s @ £%.2f x %-2d",
                    generateActivityNumber(activity),
                    activity.getActivityTitle(),
                    activity.getBaseActivityCost(),
                    newItinerary.getNumOfAttendees()
            ));

            boolean hasAddOns = false;

            for (AddOn addOn : activityAddOnsList) {
                if (addOn.getAddOnActivityCode().equals(activity.getCode())) {
                    lines.add(String.format(
                            "%-10s @ £%.2f x %d",
                            "Add-On: " + addOn.getAddOnTitle(),
                            addOn.getAddOnCostInPounds(),
                            newItinerary.getNumOfAttendees()
                    ));
                    hasAddOns = true;
                }
                if (hasAddOns) {
                    // Activity subtotal = (base activity cost x number of attendees) + (the add-on cost x number of attendees)
                    activitySubtotal = (activity.getBaseActivityCost() * newItinerary.getNumOfAttendees()) + (addOn.getAddOnCostInPounds() * newItinerary.getNumOfAttendees());
                }

            }

            if (!hasAddOns) {
                activitySubtotal = activity.getBaseActivityCost() * newItinerary.getNumOfAttendees();
                if (activity.isInsuranceRequired()) {
                    lines.add("** Insurance Required - No add-on selected **");
                } else {
                    lines.add("Add-On: No Add-Ons Selected");
                }

            }

            String subtotalString = "Sub-Total";

            // Append activity subtotals row to itinerary summary.
            lines.add(String.format("%41s: £%.2f", subtotalString, activitySubtotal));

        }

        return lines;
    }

    public List<String> generateInputtedActivityAndAddOnRows(Activity newActivity, List<String> lines, Map<String, String> activityAddOnsMap, Itinerary newItinerary, AddOn newAddOn) {

        String activityTitle;
        char activityNumber;
        double baseActivityCost;

        String addOnTitle;
        double addOnCost;
        boolean hasAddOns;
        double addOnsSubtotal;

        List<String> allActivityCodesList = new ArrayList<>();
        allActivityCodesList.add("ACT-01");
        allActivityCodesList.add("ACT-02");
        allActivityCodesList.add("ACT-03");
        allActivityCodesList.add("ACT-04");
        allActivityCodesList.add("ACT-05");

        List<String> allActivityAddOnCodes = new ArrayList<>();
        allActivityAddOnCodes.add("TRV");
        allActivityAddOnCodes.add("INS");
        allActivityAddOnCodes.add("PHO");
        allActivityAddOnCodes.add("GRT");
        allActivityAddOnCodes.add("GBG");

        for (String activityCode : allActivityCodesList) {

            if (activityAddOnsMap.containsKey(activityCode)) {
                activityNumber = activityCode.charAt(activityCode.length() - 1);

                activityTitle = generateActivityTitleFromActivityNumber(activityNumber);
                baseActivityCost = setBaseActivityCostByMapCode(activityCode, newItinerary.getNumOfAttendees());
                boolean insuranceRequired = isInsuranceRequiredForInputtedActivity(activityTitle);

                lines.add(String.format("%c. %-28s @ £%.2f x %-2d",
                        activityNumber,
                        activityTitle,
                        baseActivityCost / 100,
                        newItinerary.getNumOfAttendees())
                );

                addOnsSubtotal = 0;
                hasAddOns = false;

                for (Map.Entry<String, String> entry : activityAddOnsMap.entrySet()) {
                    if (entry.getKey().equals(activityCode)) {
                        String addOnCode = entry.getValue();
                        addOnCost = setAddOnCostByMapCode(addOnCode, newItinerary.getNumOfAttendees());
                        addOnTitle = generateAddOnTitleFromAddOnCode(addOnCode);

                        if (addOnTitle != null) {
                            // Add add-on line
                            lines.add(String.format(
                                    "Add-On: %-24s @ £%.2f x %d",
                                    addOnTitle,
                                    addOnCost / 100,
                                    newItinerary.getNumOfAttendees()
                            ));
                            hasAddOns = true;
                            addOnsSubtotal += (addOnCost / 100) * newItinerary.getNumOfAttendees();

                        }

                    }
                }

                if (!hasAddOns) {
                    if (insuranceRequired) {
                        lines.add("** Insurance Required - No add-on selected **");
                    } else {
                        lines.add("Add-On: No Add-Ons Selected");
                    }
                }

                double activitySubtotal = (baseActivityCost / 100 * newItinerary.getNumOfAttendees()) + addOnsSubtotal;
                lines.add(String.format("%41s: £%.2f", "Sub-Total", activitySubtotal));

            }
        }
        return lines;
    }

    public String generateActivityTitleFromActivityNumber(char activityNumber) {
        String activityTitle;
        activityTitle = switch (activityNumber) {
            case '1' ->
                "Building a bridge from paper";
            case '2' ->
                "Cookery Classes";
            case '3' ->
                "SAS-Style Assault Courses";
            case '4' ->
                "Obstacle Course";
            case '5' ->
                "Cavern Ziplining";
            default ->
                null;
        };

        return activityTitle;
    }

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

        for (AddOn addOn : addOnsList) {
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
     *
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
     *
     *
     * @param newItinerary
     * @return the total discount applied to a new Itinerary object.
     */
    public double generateDiscountSubTotal(Itinerary newItinerary) {

        return newItinerary.setDiscountOutput();
    }
    
    public double generateInputtedDiscountSubTotal(String totalCost, List<String> processedItineraryAddOns, Activity newActivity, Itinerary newItinerary,
                                                    String[] selectedActivities, Map<String, String> activityAddOnsMap){
        // Total cost is the itinerary cost with discount.
        String originalCostString = totalCost;
        originalCostString = originalCostString.replace("£", "");
        
        
        double discountSubtotal = 0;
        // Itinerary base cost with add-ons is equal to the total cost of the inputted activities
        // + the itinerary add-ons.
        double itineraryBaseCostWithAddOns = 
                generateInputtedActivitiesSubtotal(newActivity, newItinerary, selectedActivities, activityAddOnsMap)
                + generateInputtedItineraryAddOnSubtotal(newItinerary, processedItineraryAddOns);
                                            
        double itineraryCostWithDiscount = Double.parseDouble(originalCostString);
        
        discountSubtotal = itineraryBaseCostWithAddOns - itineraryCostWithDiscount;
        
        return discountSubtotal;
    }

}
