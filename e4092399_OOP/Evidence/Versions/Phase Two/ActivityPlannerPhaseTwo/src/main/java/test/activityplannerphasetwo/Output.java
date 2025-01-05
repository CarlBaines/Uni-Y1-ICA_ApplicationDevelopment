/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.activityplannerphasetwo;

import java.time.LocalDate;
import java.util.List;


/**
 *
 * @author E4092399
 */
public class Output {
    
     /**
     * generateItineraryRef() method
     * Method that returns a reference number that is used in main to write to file. 
     * 
     * @param newItinerary
     * @return newItinerary.getReferenceNumber()
     */
    
    public String generateItineraryRef(Itinerary newItinerary){
        
        return newItinerary.getReferenceNumber();
        
    }
    
     /**
     * generateItineraryDate() method
     * Method that returns an itinerary date that is used in main to write to file. 
     * 
     * @param newItinerary
     * @return newItinerary.getItineraryDate()
     */
    
    public LocalDate generateItineraryDate(Itinerary newItinerary){
        
        return newItinerary.getItineraryDate();
    }
    
    /**
     * generateAttendeeName() method
     * Method that returns an attendee name that is used in main to write to file. 
     * 
     * @param newItinerary
     * @return newItinerary.getNameOfLeadAttendee()
     */
    
    public String generateAttendeeName(Itinerary newItinerary){
        
        return newItinerary.getNameOfLeadAttendee();
    }
    
     /**
     * generateTotalItineraryCostInPence() method
     * Method that returns a String that is used in main to write the total itinerary cost in pence to file. 
     * 
     * @param newItinerary
     * @return String
     */
    
    
    public String generateTotalItineraryCostInPence(Itinerary newItinerary){
        
        return String.format("%.1f", newItinerary.setItineraryTotalCost() * 100);
    }
    
     /**
     * generateTotalActivities() method
     * Method that returns a String that is used in main to write the total number of activities to file.
     * 
     * @param newItinerary
     * @return newItinerary.getNumOfActivities()
     */
    
    public int generateTotalActivities(Itinerary newItinerary){
        
        return newItinerary.getNumOfActivities();
    }
    
     /**
     * generateActivitiesCode() method
     * Method that returns a String that is used in main to write the code (and add-on code if the activity has add-ons selected) of each activity to file.
     * 
     * @param activity
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
    
}
