/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlt.activityplannerphasethree;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Carlt
 */
public class Validation {

    static Scanner in = new Scanner(System.in);

    public static LocalDate validateItineraryDate() {

        LocalDate itineraryDate = null;
        while (itineraryDate == null) {
            String dateInput = in.nextLine();
            try {
                itineraryDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);

                if (itineraryDate.isAfter(LocalDate.now())) {
                    System.out.println("Success! Valid itinerary date entered: " + itineraryDate);

                } else {
                    System.out.println("Error! Invalid itinerary date - the date was not entered in the future\nEnter a valid date: ");
                    itineraryDate = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error! Invalid date format entered\nEnter a valid date in the YYYY-MM-DD format: ");
            }
        }
        return itineraryDate;

    }

    public static String validateItineraryReferenceNumber(Itinerary newItinerary) {

        // Logic for saving a reference number to the file + validation.
        boolean flag = false;

        String refNumInput = null;
        while (!flag) {
            refNumInput = in.nextLine();
            // Validate reference number with method call
            String validateRef = newItinerary.setItineraryReferenceNumber(refNumInput);
            System.out.println(validateRef);

            if (validateRef.contains("ERR_REFERENCE_NUMBER")) {
                System.out.println("\nEnter a valid reference number: ");
            } else {
                flag = true;
            }
        }

        String finalRefNumber = refNumInput;
        return finalRefNumber;

    }

    public static String validateLeadAttendeeName(Itinerary newItinerary) {

        boolean flag = false;

        String attendeeNameInput = null;
        while (!flag) {
            attendeeNameInput = in.nextLine();

            boolean validateAttendee = newItinerary.setLeadAttendeeName(attendeeNameInput);

            if (validateAttendee == false) {
                System.out.println("Error! Invalid lead attendee name entered\nEnter a valid lead attendee name: ");
            } else {
                flag = true;
            }
        }

        String finalAttendeeNameInput = attendeeNameInput;
        return finalAttendeeNameInput;
    }

    public static int validateTotalNumberOfAttendees() {

        boolean flag = false;

        int totalNumberOfAttendees = 0;
        while (!flag) {
            totalNumberOfAttendees = in.nextInt();

            if (totalNumberOfAttendees <= 0) {
                System.out.println("Error! The total number of attendees cannot be zero or under!\nEnter a valid number of attendees: ");
            } else {
                System.out.println("Success! Valid total number of attendees entered!");
                flag = true;
            }
        }

        int finalTotalNumberOfAttendees = totalNumberOfAttendees;
        return finalTotalNumberOfAttendees;
    }

    public static double validateTotalItineraryCost() {
        boolean flag = false;
        
        int minItineraryTotalCost = 25000;
        int maxItineraryTotalCost = 287500;
        double itineraryTotalCostInput = 0;
        
        while (!flag) {
            itineraryTotalCostInput = in.nextDouble();
            
            // If the user input is smaller than the minimum itinerary total cost or if the user input is larger than the maximum itinerary cost
            // print error and ask for another input.
            if (itineraryTotalCostInput < minItineraryTotalCost) {
                System.out.println("Error! Out of Range! The total itinerary cost entered is smaller than the minimum total itinerary cost possible.\nEnter a valid total itinerary cost: ");
            } 
            else if(itineraryTotalCostInput > maxItineraryTotalCost){
                System.out.println("Error! Out of Range! The total itinerary cost entered is larger than the maximum total itinerary cost possible.\nEnter a valid total itinerary cost: ");
            }
            else {
                System.out.println("Success! Valid itinerary cost entered!");
                flag = true;
            }
        }

        double finalItineraryCost = itineraryTotalCostInput;
        return finalItineraryCost;
    }

    public static int validateTotalNumOfActivities() {
        boolean flag = false;

        int totalActivities = 0;

        while (!flag) {
            totalActivities = in.nextInt();

            if (totalActivities <= 0) {
                System.out.println("Error! The total number of activities cannot be zero or under!\nEnter a valid number of activities: ");
            }
            // An itinerary is multiple activities so it must have at least two activities.
            else if (totalActivities < 2){
                System.out.println("Error! The total number of activities for an itinerary cannot be one or below!\nEnter a valid number of activities: ");
            }
            else if (totalActivities > 5) {
                System.out.println("Error! The total number of activities cannot be higher than five as there is only five activities!\nEnter a valid number of activities:");
            } else {
                System.out.println("Success! Valid total number of activities entered!");
                flag = true;
            }

        }

        int finalTotalActivities = totalActivities;
        return finalTotalActivities;
    }

    public static String validateAddOnsInput(List<String> addOnCodesList) {
        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> selectedAddOns = new HashSet<>();
        
        System.out.println("Enter add-on codes for the itinerary activities (Enter DONE when you have finished entering add-on codes): ");
        System.out.println("\nTravel: " + addOnCodesList.get(0));
        System.out.println("Insurance: " + addOnCodesList.get(1));
        System.out.println("Photography: " + addOnCodesList.get(2));
        System.out.println("Gear Rentals: " + addOnCodesList.get(3));
        System.out.println("Gift Bag: " + addOnCodesList.get(4));
        System.out.println("Accommodation: " + addOnCodesList.get(5));
        System.out.println("Coffee/Tea Breaks: " + addOnCodesList.get(6));
        System.out.println("Lunch: " + addOnCodesList.get(7));
        while (!flag) {
            String addOnsInput = in.nextLine();

            if ("DONE".equalsIgnoreCase(addOnsInput)) {
                flag = true;
                System.out.println("Success! All add-ons have been added to the itinerary!");
            } else if (addOnsInput.isEmpty()) {
                System.out.println("Error! Empty input.\nPlease enter an add-on: ");
            } else {
                boolean isAddOn = false;

                for (String addOn : addOnCodesList) {
                    if (addOnsInput.equals(addOn)) {
                        isAddOn = true;

                        if (selectedAddOns.contains(addOn)) {
                            System.out.println("Error! You cannot enter duplicate add-ons\nEnter a itinerary add-on that has not already been selected: ");
                        } else {
                            selectedAddOns.add(addOn);
                            System.out.println("Success! Added add-on to itinerary: " + addOn);
                            stringBuilder.append(addOn).append(" ");
                            System.out.println("Do you want to add additional add-ons to the itinerary? (Type DONE if you are finished adding add-ons): ");
                        }
                        break;

                    }
                }

                if (!isAddOn) {
                    System.out.println("Error! Invalid itinerary add-on entered\nEnter a valid itinerary add-on: ");
                }

            }

        }

        String finalAddOnsInput = stringBuilder.toString();
        return finalAddOnsInput;
    }
    
    public static String validateActivityCodes(List<String> activityCodesList) {
        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> selectedActivityCodes = new HashSet<>();

        in.nextLine();
        while (!flag) {
            String activityCodesInput = in.nextLine();
            if ("DONE".equalsIgnoreCase(activityCodesInput)) {
                flag = true;
                System.out.println("Success! All activity codes have been added to the itinerary.");

            } 
            else if (activityCodesInput.isEmpty()) {
                System.out.println("Error! Empty input.\nPlease enter an activity code: ");

            }
            else if (selectedActivityCodes.contains(activityCodesInput)){
                System.out.println("Error! You cannot enter duplicate activity codes\nEnter a activity code that has not already been selected: ");
            }
            else if (activityCodesList.contains(activityCodesInput)){
                selectedActivityCodes.add(activityCodesInput);
                stringBuilder.append(activityCodesInput).append(" ");
                System.out.println("Success! Added activity code to itinerary: " + activityCodesInput);
                System.out.println("Do you want to add additional activity codes to the itinerary? (Type DONE if you are finished adding activities): ");
            }
            else{
                System.out.println("Error! Invalid activity code for itinerary entered.\nEnter a valid activity code: ");
            }

        }
        
        String finalActivityCodes = stringBuilder.toString();
        return finalActivityCodes;

    }

}
