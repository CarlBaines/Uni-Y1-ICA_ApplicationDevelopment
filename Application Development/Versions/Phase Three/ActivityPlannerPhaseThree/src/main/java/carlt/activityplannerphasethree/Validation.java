/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlt.activityplannerphasethree;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
                System.out.print("\nEnter a valid reference number: ");
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
                System.out.println("Success! Valid total number of attendees entered: " + totalNumberOfAttendees);
                flag = true;
            }
        }

        int finalTotalNumberOfAttendees = totalNumberOfAttendees;
        return finalTotalNumberOfAttendees;
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
                System.out.println("Success! Valid total number of activities entered: " + totalActivities);
                flag = true;
            }

        }

        int finalTotalActivities = totalActivities;
        return finalTotalActivities;
    }

   

        
    public static String validateActivityAddOnInput(List<String> activityAddOnCodesList, String singleCode, AddOn newAddOn) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> selectedAddOns = new HashSet<>();
        List<String> availableActivityAddOns = new ArrayList<>(activityAddOnCodesList);
        
        System.out.println("Enter optional activity add-ons for " + singleCode
                + " (Enter NONE if you do not want to add any add-ons): ");
        System.out.println("\nAvailable Activity Add-Ons: ");
       

        while (true) {
            
            availableActivityAddOns.clear();
            for (String addOnCode : activityAddOnCodesList){
                if (!selectedAddOns.contains(addOnCode)){
                    availableActivityAddOns.add(addOnCode);
                }
            }
            
            if (availableActivityAddOns.isEmpty()){
                System.out.println("All possible activity add-ons have been added to the itinerary - there are no more add-ons available to select.");
                break;
            }
            
            for (String addOnCode : availableActivityAddOns){
                String addOnCodeTitle = newAddOn.setAddOnTitleFromCode(addOnCode);
                System.out.println(addOnCode + " - " + addOnCodeTitle + ": " + newAddOn.addOnBaseCostOutput(addOnCodeTitle));
            }
            
            
            String addOnsInput = in.nextLine().trim();  // Trim the input to avoid issues with extra spaces

            // Handle case where no add-ons are selected
            if ("NONE".equalsIgnoreCase(addOnsInput)) {
                System.out.println("The activity " + singleCode + " has been added to the itinerary without add-ons.");
                break;
            }

            // Handle case when user is finished adding add-ons
            if ("DONE".equalsIgnoreCase(addOnsInput)) {
                System.out.println("\nSuccess! You have added add-ons to " + singleCode + "\n");
                break;
            }

            // Handle empty input
            if (addOnsInput.isEmpty()) {
                System.out.println("Error! Empty input. Please enter a valid add-on or 'DONE' to finish.");
                continue;
            }

            // Validate the input
            if (activityAddOnCodesList.contains(addOnsInput)) {
                // Check for duplicates
                if (selectedAddOns.contains(addOnsInput)) {
                    System.out.println("Error! Duplicate add-on. Please enter a different add-on: ");
                    continue;
                }

                // Add the valid add-on to the list and string builder
                selectedAddOns.add(addOnsInput);
                stringBuilder.append(addOnsInput).append(" ");

            } else {
                // Handle invalid add-on
                System.out.println("Error! Invalid add-on entered. Please enter a valid add-on code.");
                continue;
            }

            // Prompt for more add-ons or to finish
            System.out.println("\nDo you want to add additional activity add-ons for " + singleCode + "? (Type DONE if you are finished): ");
            System.out.println("\nAvailable Activity Add-Ons: ");
        }

        return stringBuilder.toString().trim(); // Return the list of selected add-ons
    }

    public static String validateItineraryAddOnInput(List<String> itineraryAddOnCodesList, String singleCode, AddOn newAddOn){
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> selectedAddOns = new HashSet<>();
        List<String> availableItineraryAddOns = new ArrayList<>(itineraryAddOnCodesList);
        
        System.out.println("Enter optional itinerary add-ons for " + singleCode + " (Enter NONE if you do not want to add any add-ons): ");
        System.out.println("\nAvailable Itinerary Add-Ons: ");
        
        while (true){
            availableItineraryAddOns.clear();
            for (String addOnCode : itineraryAddOnCodesList){
                if (!selectedAddOns.contains(addOnCode)){
                    availableItineraryAddOns.add(addOnCode);
                }
            }
            
            if (availableItineraryAddOns.isEmpty()){
                System.out.println("All possible itineray add-ons have been added to the itinerary - there are no more add-ons available to select.");
                break;
            }
            
            for (String addOnCode : availableItineraryAddOns){
                String addOnCodeTitle = newAddOn.setAddOnTitleFromCode(addOnCode);
                System.out.println(addOnCode + " - " + addOnCodeTitle + ": " + newAddOn.addOnBaseCostOutput(addOnCodeTitle));
            }
            
            String addOnsInput = in.nextLine().trim();
            
            // Handle case where no add-ons are selected
            if ("NONE".equalsIgnoreCase(addOnsInput)) {
                System.out.println("The activity " + singleCode + " has been added to the itinerary without add-ons.");
                break;
            }

            // Handle case when user is finished adding add-ons
            if ("DONE".equalsIgnoreCase(addOnsInput)) {
                System.out.println("\nSuccess! You have added add-ons to " + singleCode + "\n");
                break;
            }

            // Handle empty input
            if (addOnsInput.isEmpty()) {
                System.out.println("Error! Empty input. Please enter a valid add-on or 'DONE' to finish.");
                continue;
            }
            
            if (itineraryAddOnCodesList.contains(addOnsInput)){
                if (selectedAddOns.contains(addOnsInput)){
                    System.out.println("Error! Duplicate add-on. Please enter a different add-on: ");
                    continue;
                }
                
                selectedAddOns.add(addOnsInput);
                stringBuilder.append(addOnsInput).append(" ");
            } else{
                // Handle invalid add-on
                System.out.println("Error! Invalid add-on entered. Please enter a valid add-on code.");
                continue;
            }
            
            // Prompt for more add-ons or to finish
            System.out.println("\nDo you want to add additional itinerary add-ons for " + singleCode + "? (Type DONE if you are finished): ");
            System.out.println("\nAvailable Itinerary Add-Ons: ");
            
            
        }
        
        return stringBuilder.toString().trim();
    }


    public static String validateActivityCode(List<String> activityCodesList, Activity newActivity, int finalTotalActivities, List<String> addOnCodesList, String finalAddOns) { 
        int totalActivitiesEntered = 1;
        StringBuilder itineraryBuilder = new StringBuilder(); // Stores all validated activities
        Set<String> selectedActivityCodes = new HashSet<>();
        List<String> availableActivities = new ArrayList<>(activityCodesList);

        in.nextLine();
        
        while (selectedActivityCodes.size() < finalTotalActivities) {
            
            availableActivities.clear();
            // Prompt for activity code
            for (String activityCode : activityCodesList){
                if (!selectedActivityCodes.contains(activityCode)){
                    availableActivities.add(activityCode);
                }
            }
            
            if (availableActivities.isEmpty()){
                System.out.println("All activities have been added to the itinerary - there are no more activities available to select.");
                break;
            }
            
            System.out.println("Please enter an activity code for activity number " + totalActivitiesEntered + "/" + finalTotalActivities + ":");
            System.out.println("\nAvailable Activities: ");
            
            for (String activityCode : availableActivities){
                int index = activityCodesList.indexOf(activityCode);
                String activityTitle = newActivity.setActivityTitleFromCode(activityCodesList, index);
                System.out.println(activityCode + " - " + newActivity.activityBaseCostOutput(activityTitle));
            }

            String activityCodeInput = in.nextLine().trim();

            // Validate activity code input
            if (activityCodeInput.isEmpty()) {
                System.out.println("Error! Empty input.\nPlease enter a valid activity code: ");
                continue;
            }

            String[] codes = activityCodeInput.split("\\s+");
            if (codes.length > 1) {
                System.out.println("Error! You cannot enter multiple activity codes at once. Please enter only one code: ");
                continue;
            }

            String singleCode = codes[0];
            if (!activityCodesList.contains(singleCode)) {
                System.out.println("Error! Invalid activity code entered.\nEnter a valid activity code: ");
                continue;
            }

            if (selectedActivityCodes.contains(singleCode)) {
                System.out.println("Error! Duplicate activity code entered. Try a different code: ");
                continue;
            }

            // Add activity to itinerary
            selectedActivityCodes.add(singleCode);
            itineraryBuilder.append(singleCode).append(" ");
            int index = activityCodesList.indexOf(singleCode);

            System.out.println("\nSuccess! You added an activity to the itinerary: " + newActivity.setActivityTitleFromCode(activityCodesList, index));


            totalActivitiesEntered++;
        }

        return itineraryBuilder.toString().trim(); // Return the itinerary string without trailing spaces
    }


}
