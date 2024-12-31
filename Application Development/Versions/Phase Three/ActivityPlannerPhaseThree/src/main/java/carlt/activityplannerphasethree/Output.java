/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlt.activityplannerphasethree;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Carlt
 */
public class Output {
    
    String[] numberToStrings = {"One", "Two", "Three", "Four", "Five",
        "Six", "Seven", "Eight", "Nine", "Ten"
    };
            

    /**
     * generateItineraryRef() method Method that returns a reference number that
     * is used in main to write to file.
     *
     * @param newItinerary
     * @return newItinerary.getReferenceNumber()
     */

    public String generateItineraryRef(Itinerary newItinerary) {

        return newItinerary.getReferenceNumber();

    }

    /**
     * generateItineraryDate() method Method that returns an itinerary date that
     * is used in main to write to file.
     *
     * @param newItinerary
     * @return newItinerary.getItineraryDate()
     */
    public LocalDate generateItineraryDate(Itinerary newItinerary) {

        return newItinerary.getItineraryDate();
    }

    /**
     * generateAttendeeName() method Method that returns an attendee name that
     * is used in main to write to file.
     *
     * @param newItinerary
     * @return newItinerary.getNameOfLeadAttendee()
     */
    public String generateAttendeeName(Itinerary newItinerary) {

        return newItinerary.getNameOfLeadAttendee();
    }
    
    public int generateNumberOfAttendees(Itinerary newItinerary){
        return newItinerary.getNumOfAttendees();
    }
    
    public String generateNumOfAttendeesString(Itinerary newItinerary){
        String numOfAttendees;
        int totalAttendees = newItinerary.getNumOfAttendees();
        if (totalAttendees < numberToStrings.length){
            numOfAttendees = numberToStrings[totalAttendees - 1];
            return numOfAttendees;
        }
        else{
            return null;
        }
    }

    /**
     * generateTotalItineraryCostInPence() method Method that returns a String
     * that is used in main to write the total itinerary cost in pence to file.
     *
     * @param newItinerary
     * @return String
     */
    public String generateTotalItineraryCostInPence(Itinerary newItinerary) {

        return String.format("%.1f", newItinerary.setItineraryTotalCost() * 100);
    }
    
    public String generateTotalItineraryCost(Itinerary newItinerary){
        return String.format("%.2f", newItinerary.setItineraryTotalCost());
    }
    
    
    public double generateBaseAddOnCost(AddOn newAddOn){
        return newAddOn.getAddOnCostInPounds();
    }
    
    public double generateTotalAddOnCost(AddOn newAddOn, Itinerary newItinerary){
        return generateBaseAddOnCost(newAddOn) * generateNumberOfAttendees(newItinerary);
    }
    
    public double generateAddOnSubtotal(AddOn newAddOn, List<AddOn> addOnsList){
        return newAddOn.setAddOnCostAndType() * addOnsList.size();
    }
    
    public String generateAddOnName(AddOn newAddOn){
        return newAddOn.getAddOnTitle();
    }

    /**
     * generateTotalActivities() method Method that returns a String that is
     * used in main to write the total number of activities to file.
     *
     * @param newItinerary
     * @return newItinerary.getNumOfActivities()
     */
    public int generateTotalActivities(Itinerary newItinerary) {

        return newItinerary.getNumOfActivities();
    }
    
    public String generateTotalActivitiesString(Itinerary newItinerary){
        String totalString;
        int totalActivities = newItinerary.getNumOfActivities();
        if (totalActivities < numberToStrings.length){
            totalString = numberToStrings[totalActivities - 1];
            return totalString;
        }
        if (totalActivities > 5){
            return String.valueOf(totalActivities);
        }
        else{
            return null;
        }
        
    }
    
    public double generateActivitiesSubtotal(Activity newActivity, List<String> activityCodesList){
        
        return newActivity.setActivityBaseCostByCode(activityCodesList);
        
    }

    /**
     * generateActivitiesCode() method Method that returns a String that is used
     * in main to write the code (and add-on code if the activity has add-ons
     * selected) of each activity to file.
     *
     * @param activity
     * @param addOnsList
     * @return output.toString() - string builder
     */
    public String generateActivitiesCode(Activity activity, List<AddOn> addOnsList) {

        // New instance of StringBuilder class is instantiated to take the activityCode and ": " for formatting.
        StringBuilder output = new StringBuilder("");

        // temporary boolean
        boolean hasAddOns = false;

        // Loops through the addOnsList to retrieve each addOn object.
        for (int index = 0; index < addOnsList.size(); index++) {
            AddOn addOn = addOnsList.get(index);

            // If the code returned from each addOn object is not null and not empty
            if (addOn.getAddOnCode() != null && !addOn.getAddOnCode().isEmpty()) {
                if (hasAddOns) {
                    // Adds comma to string builder for formatting.
                    output.append(" ");
                }
                // adds the addOn code from each addOn object to the string builder.
                output.append(addOn.getAddOnCode());
                hasAddOns = true;
            }
        }

        // return string builder.
        return output.toString();

    }
    
    public String generateActivityRows(List<Activity> activities, Activity newActivity){
        
        StringBuilder activitySubtotals = new StringBuilder();
        
        for (Activity activity : activities){
            activitySubtotals.append(String.format("\n|                                                     |\n| %d. %-28s @ £%-16.2f|\n",
                    generateActivityNumber(activity),
                    activity.getActivityTitle(),
                    activity.getBaseActivityCost()));
        }
        
        
        return activitySubtotals.toString();
    }
    
    public String generateAddOnRows(List<AddOn> addOnsList, AddOn newAddOn, Itinerary newItinerary){
        
        StringBuilder addOnSubtotals = new StringBuilder();
        
        for (AddOn addOn : addOnsList){
            addOnSubtotals.append(String.format("| Add-on: %s @ £%.2f|\n",
                    addOn.getAddOnTitle(),
                    addOn.getAddOnCost()));
        }
        
        
        return addOnSubtotals.toString();
    }
    
    public int generateActivityNumber(Activity activity){
        Integer[] numberArray = {1, 2, 3, 4, 5};
        
        int activityNumber;
        
        switch(activity.getActivityTitle()){
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
            default -> activityNumber = 0;
        }
        
        return activityNumber;
    }
    
    public int generateDiscountNumber(Itinerary newItinerary){
        
        
        return (int) (newItinerary.getDiscountPercentage() * 100);
    }
    
    public double generateDiscountSubTotal(Itinerary newItinerary){
        
        return newItinerary.setDiscountOutput();
    }
    
}
