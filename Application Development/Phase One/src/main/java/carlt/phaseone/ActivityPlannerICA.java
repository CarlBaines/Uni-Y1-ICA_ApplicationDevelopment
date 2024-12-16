/*
 * Click nbfs:// nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package carlt.phaseone;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Carlt
 */



public class ActivityPlannerICA {

    public static void main(String[] args) {
        
        //  Initialises a new ArrayList that takes AddOn objects 
        List<AddOn> addOnsList = new ArrayList<>();
        
        //  Initialises two new instances of the AddOn class and gives them preset values.
        AddOn travelActivityAddOn = new AddOn("Activity", "Travel", 0.0, 0.0);
        AddOn accommodationItineraryAddOn = new AddOn("Itinerary", "Accommodation", 0.0, 0.0);
        
        //  Method calls on the addOn instances - to set the cost and type of each add-on.
        travelActivityAddOn.setAddOnCostAndType();
        accommodationItineraryAddOn.setAddOnCostAndType();
        
        //  Adds the attributes of each AddOn instance to the addOnsList.
        addOnsList.add(travelActivityAddOn);
        addOnsList.add(accommodationItineraryAddOn);
        
        //  Custom activity date and time is set here to be passed into the new instance of the activity class.
        LocalDate customActivityDate = LocalDate.of(2024, 12, 15);
        LocalTime customActivityTime = LocalTime.of(14, 30);
        
        //  Creates new instance of the activity class with default constructor values.
        Activity activityOne = new Activity();
               
        
        //  Creates new instance of the activity class with preset values.
        Activity activityTwo = new Activity(
                0, 
                "ACT-02", 
                "Cookery Classes", 
                "Discover the joy of cooking with cookery classes", 
                "Middlesbrough", 
                customActivityDate, 
                customActivityTime, 
                60, 
                false,
                addOnsList
        );
        //  Third activity instantiated with the same default constructor values as activityOne for testing purposes.
        Activity activityThree = new Activity();
        
        //  Creates new instance of the itinerary class. Uses default constructor values.
        Itinerary newItinerary = new Itinerary();
        
        //  Outputs toString methods of each instance of the activity class. Outputs the attributes of each activity.
        System.out.println(activityOne.toString() + "\n\n" + activityTwo.toString() + "\n" + activityThree.toString());
        //  A list that takes activity objects is initialised to take each activity object.
        List<Activity> activities = Arrays.asList(activityOne, activityTwo, activityThree);
        //  Method call that returns the number of activities.
        newItinerary.setNumberOfActivities(activities);
        
        
        System.out.println("\n" + newItinerary.toString() + "\n");
        System.out.println("Activity Costs:");
        //  Output the return values of the setActivityBaseActivityCost method for each activity.
        //  The method determines the base cost for each activity based on the activity title.
        System.out.println(activityOne.setActivityBaseActivityCost());
        System.out.println(activityTwo.setActivityBaseActivityCost());
        System.out.println(activityThree.setActivityBaseActivityCost());
        //  Outputs the return value of the setTotalActivityCost method.
        //  The method sets the total activity cost by looping through the list of activities and getting the base cost of each activity.
        System.out.printf("The total cost of all activities is: £%.2f\n" , newItinerary.setTotalActivityCost());
        
        System.out.println("\nItinerary Costs:");
        //  Outputs the return values of two methods: one method sets the itinerary base cost without add-ons, the other sets the itinerary base cost, with add-ons.
        System.out.printf("The itinerary base cost (add-ons not included) is: £%.2f\n" , newItinerary.setItineraryBaseCost());
        System.out.printf("The total itinerary base cost (add-ons included) is: £%.2f\n" , newItinerary.setItineraryBaseCostWithAddOns());
        
        //  Method call for use in calculation.
        //  The method works out the discount percentage for the itinerary based on the number of activities taken and the number of attendees.
        newItinerary.setItineraryDiscountPercentage();
        
        //  Returns value from method call.
        //  The setDiscountOutput method works out the discount applied by
        //  subtracting the itinerary base cost with add-ons away from the total itinerary cost with discount.
        System.out.printf("Discount = £%.2f" , newItinerary.setDiscountOutput());
        System.out.printf("\nThe total itinerary cost (with discount applied is): £%.2f" , newItinerary.setItineraryTotalCost());
        
        
    }
}
