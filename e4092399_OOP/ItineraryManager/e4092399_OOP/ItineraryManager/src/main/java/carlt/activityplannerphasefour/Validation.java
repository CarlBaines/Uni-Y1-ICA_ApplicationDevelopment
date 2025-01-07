/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlt.activityplannerphasefour;

// Imports
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * The Validation class is responsible for validating user inputs that relate to
 * itineraries, activities and add-ons within the activity planner system. It
 * features a static Scanner object. It features many validation methods to
 * validate attributes of itinerary objects.
 *
 * @author Carlt
 */
public class Validation {

    static Scanner in = new Scanner(System.in);

    /**
     * validateItineraryDate() method - This method validates and returns the
     * itinerary date input by checking if it is null, if the date entered is
     * entered in the future and if it is entered in the correct format.
     *
     *
     * @return itineraryDate.
     */
    public static LocalDate validateItineraryDate() {
        LocalDate itineraryDate = null;
        // Runs block of code whilst itinerary date is null
        while (itineraryDate == null) {
            // the date input is equal to the next line read by the scanner.
            String dateInput = in.nextLine();
            try {
                // parses the inputted itinerary date to the iso_local_date format for validation purposes.
                itineraryDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
                
                // Checks if the itineraryDate is entered in the future from the current date.
                if (itineraryDate.isAfter(LocalDate.now())) {
                    System.out.println("Success! Valid itinerary date entered: " + itineraryDate);
                // else invalid date input.
                } else {
                    System.out.println("Error! Invalid itinerary date - the date was not entered in the future\nEnter a valid date: ");
                    itineraryDate = null;
                }
            // Exception handling for if the date is entered in an incorrect format.
            } catch (DateTimeParseException e) {
                System.out.println("Error! Invalid date format entered\nEnter a valid date in the YYYY-MM-DD format: ");
            }
        }
        return itineraryDate;
    }

    /**
     * validateItineraryReferenceNumber() method - This method validates and
     * returns the itinerary reference number user input. It works alongside the
     * setItineraryReferenceNumber method in the Itinerary class to do this.
     *
     *
     * @param newItinerary
     * @return finalRefNumber
     */
    public static String validateItineraryReferenceNumber(Itinerary newItinerary) {
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

    /**
     * validateLeadAttendeeName() method - This method validates and returns the
     * lead attendee name user input. It works alongside the setLeadAttendeeName
     * method in the Itinerary class to do this.
     *
     *
     * @param newItinerary
     * @return finalAttendeeNameInput.
     */
    public static String validateLeadAttendeeName(Itinerary newItinerary) {
        boolean flag = false;

        String attendeeNameInput = null;
        while (!flag) {
            // The input becomes the next line read by the scanner.
            attendeeNameInput = in.nextLine();
            
            // Validates the attendee input by passing into a helper method.
            boolean validateAttendee = newItinerary.setLeadAttendeeName(attendeeNameInput);
            
            // If the result of the validation method is equal to false - invalid lead attendee name entered.
            if (validateAttendee == false) {
                System.out.println("\nEnter a valid lead attendee name: ");
            } else {
                // break from loop.
                flag = true;
            }
        }

        String finalAttendeeNameInput = attendeeNameInput;
        return finalAttendeeNameInput;
    }

    /**
     * validateTotalNumberOfAttendees() method - This method validates and
     * returns the total number of attendees user input. It checks to see if the
     * number entered is zero or under.
     *
     *
     * @return finalTotalNumberOfAttendees.
     */
    public static int validateTotalNumberOfAttendees() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;

        int totalNumberOfAttendees = 0;
        while (!flag) {
            
            try{
                // The input becomes the next line read by the scanner.
                totalNumberOfAttendees = scanner.nextInt();
                // Checks if the input is zero or under
                if (totalNumberOfAttendees <= 0) {
                    System.out.println("Error! The total number of attendees cannot be zero or under!\nEnter a valid number of attendees: ");
                } // Checks if the input is above fifteen.
                else if (totalNumberOfAttendees > 15) {
                    System.out.println("Error! The total number of attendees cannot be higher than fifteen.\nEnter a valid number of attendees: ");
                } else {
                    System.out.println("Success! Valid total number of attendees entered: " + totalNumberOfAttendees);
                    flag = true;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Error! Input mismatch for the total number of attendees.\nEnter a valid number of attendees: ");
                in.nextLine();
            }
            
          
        }

        int finalTotalNumberOfAttendees = totalNumberOfAttendees;
        return finalTotalNumberOfAttendees;
    }

    /**
     * validateTotalNumOfActivities() method - This method validates and returns
     * the totalActivities user input. It checks to see if the totalActivities
     * user input is zero or under, if it is lower than two or if it is higher
     * than five. This is because an itinerary must have at least two activities
     * and the total number of activities cannot be higher than five as there is
     * only five activities.
     *
     *
     * @return finalTotalActivities.
     */
    public static int validateTotalNumOfActivities() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;

        int totalActivities = 0;

        while (!flag) {
            
            try{
                // Input becomes next line read by scanner.
                totalActivities = scanner.nextInt();

                // Checks to see if the total number of activities is zero or under.
                if (totalActivities <= 0) {
                    System.out.println("Error! The total number of activities cannot be zero or under!\nEnter a valid number of activities: ");
                } // An itinerary is multiple activities so it must have at least two activities.
                else if (totalActivities < 2) {
                    System.out.println("Error! The total number of activities for an itinerary cannot be one or below!\nEnter a valid number of activities: ");
                } // Checks to se if the total number of activities is higher than five. This cannot be possible as there is only five activities.
                else if (totalActivities > 5) {
                    System.out.println("Error! The total number of activities cannot be higher than five as there is only five activities!\nEnter a valid number of activities:");
                } else {
                    System.out.println("Success! Valid total number of activities entered: " + totalActivities);
                    flag = true;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Error! Input mismatch for the total number of activities!\nEnter a valid number of activities: ");
                scanner.nextLine();
            }
            

        }

        return totalActivities;
    }

    /**
     * validateActivityAddOnInput() method - This method prompts the user to add
     * add-on codes for an activity and validates whether the activity codes
     * entered to check if they are not duplicate and if the add-on codes are
     * valid. It returns a list of the selected add-ons in a string format.
     *
     *
     * @param activityAddOnCodesList
     * @param singleCode
     * @param newAddOn
     * @return stringBuilder.toString().trim() - trimmed string with removed
     * white space/trailing characters.
     */
    public static String validateActivityAddOnInput(List<String> activityAddOnCodesList, String singleCode, AddOn newAddOn) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> selectedAddOns = new HashSet<>();
        List<String> availableActivityAddOns = new ArrayList<>(activityAddOnCodesList);

        System.out.println("Enter optional activity add-ons for " + singleCode
                + " (Enter NONE if you do not want to add any add-ons): ");
        System.out.println("\nAvailable Activity Add-Ons: ");
        
        boolean insuranceRequired = isInsuranceRequired(singleCode);

        while (true) {

            availableActivityAddOns.clear();
            for (String addOnCode : activityAddOnCodesList) {
                if (!selectedAddOns.contains(addOnCode)) {
                    availableActivityAddOns.add(addOnCode);
                }
                if (insuranceRequired == false){
                    availableActivityAddOns.remove("INS");
                }
            }

            if (availableActivityAddOns.isEmpty()) {
                System.out.println("All possible activity add-ons have been added to the itinerary - there are no more add-ons available to select.");
                break;
            }

            for (String addOnCode : availableActivityAddOns) {
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
        // Return the list of selected add-ons
        return stringBuilder.toString().trim();
    }
    
     /**
     * isInsuranceRequired() method - This method is used as a helper method to determine whether insurance is required
     * for an activity code in the validateActivityAddOnInput method.
     *
     *
     * @param activity
     * @return insuranceRequired (true or false).
     */
    
    public static boolean isInsuranceRequired(String activity) {
        boolean insuranceRequired;

        switch (activity) {
            case "ACT-01" -> {
                insuranceRequired = false;
                return insuranceRequired;
            }
            case "ACT-02" -> {
                insuranceRequired = false;
                return insuranceRequired;
            }
            case "ACT-03" -> {
                insuranceRequired = true;
                return insuranceRequired;
            }
            case "ACT-04" -> {
                insuranceRequired = true;
                return insuranceRequired;
            }
            case "ACT-05" -> {
                insuranceRequired = true;
                return insuranceRequired;
            }
            default -> {
                insuranceRequired = false;
                return insuranceRequired;
            }
        }
    }

    /**
     * validateItineraryAddOnInput() method - This method works almost
     * identically to the validateActivityAddOnInput() method besides the fact
     * it handles itinerary add-ons.
     *
     *
     * @param itineraryAddOnCodesList
     * @param newAddOn
     * @return stringBuilder.toString().trim() - trimmed string with removed
     * white space/trailing characters.
     */
    public static String validateItineraryAddOnInput(List<String> itineraryAddOnCodesList, AddOn newAddOn) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> selectedAddOns = new HashSet<>();

        System.out.println("Enter optional itinerary add-ons for the entire itinerary (Type DONE when finished):");

        // Loop to allow the user to enter multiple add-ons
        while (true) {
            System.out.println("\nAvailable Itinerary Add-Ons: ");
            // Remove selected add-ons from the available list
            List<String> availableAddOnsForCurrentInput = new ArrayList<>(itineraryAddOnCodesList);
            availableAddOnsForCurrentInput.removeAll(selectedAddOns);

            if (availableAddOnsForCurrentInput.isEmpty()) {
                System.out.println("All possible itinerary add-ons have been added to the itinerary - there are no more add-ons available to select.");
                break;
            }

            // Display available add-ons for the current input cycle
            for (String addOnCode : availableAddOnsForCurrentInput) {
                String addOnCodeTitle = newAddOn.setAddOnTitleFromCode(addOnCode);
                System.out.println(addOnCode + " - " + addOnCodeTitle + ": " + newAddOn.addOnBaseCostOutput(addOnCodeTitle));
            }

            // Prompt for add-on input from user
            String addOnsInput = in.nextLine().trim();

            // Handle case when user is finished adding add-ons
            if ("DONE".equalsIgnoreCase(addOnsInput)) {
                System.out.println("\nSuccess! You have added add-ons to the itinerary.\n");
                break;  // Exit the loop when DONE is entered
            }

            // Handle case where no add-ons are selected
            if ("NONE".equalsIgnoreCase(addOnsInput)) {
                System.out.println("The itinerary has been added without add-ons.");
                break;
            }

            // Handle empty input
            if (addOnsInput.isEmpty()) {
                System.out.println("Error! Empty input. Please enter a valid add-on or 'DONE' to finish.");
                continue;
            }

            // Process add-ons and ensure they're valid
            if (itineraryAddOnCodesList.contains(addOnsInput)) {
                if (selectedAddOns.contains(addOnsInput)) {
                    System.out.println("Error! Duplicate add-on. Please enter a different add-on: ");
                    continue;
                }

                selectedAddOns.add(addOnsInput);  // Add to selected list to avoid duplicates
                stringBuilder.append(addOnsInput).append(" ");  // Add to the output string

            } else {
                // Handle invalid add-on
                System.out.println("Error! Invalid add-on entered. Please enter a valid add-on code.");
            }

            // Prompt for more add-ons or to finish
            System.out.println("\nDo you want to add additional itinerary add-ons? (Type DONE if you are finished): ");
        }

        return stringBuilder.toString().trim();  // Return the final string of selected add-ons
    }

    /**
     * validateActivityCode() method - This method validates the activity code
     * user input.
     *
     * @param activityCodesList
     * @param newActivity
     * @param finalTotalActivities
     * @param addOnCodesList
     * @param finalAddOns
     * @return itineraryBuilder.toString().trim() - trimmed string builder
     * (toString()) with removed white space/trailing characters.
     */
    public static String validateActivityCode(List<String> activityCodesList, Activity newActivity, int finalTotalActivities, List<String> addOnCodesList, String finalAddOns) {
        int totalActivitiesEntered = 1;
        // Stores all validated activities
        StringBuilder itineraryBuilder = new StringBuilder();
        // A set is instantiated to store the selected activity codes.
        Set<String> selectedActivityCodes = new HashSet<>();
        // An arraylist is instantiated to store the available activities. It will remove selected activities as duplicate activities cannot be entered.
        List<String> availableActivities = new ArrayList<>(activityCodesList);
        
        
        // while the size of the selectedActivityCodes set is smaller than the number of finalTotalActivities.
        while (selectedActivityCodes.size() < finalTotalActivities) {
            
            // removes all the elements from the arraylist.
            availableActivities.clear();
            // Loops through the list of activity code strings.
            for (String activityCode : activityCodesList) {
                // if the selectedActivityCodes set does not contain the current activity code
                if (!selectedActivityCodes.contains(activityCode)) {
                    // Add the current activity code to the arraylist.
                    availableActivities.add(activityCode);
                }
            }
            
            // Checks to see if the arraylist is empty.
            if (availableActivities.isEmpty()) {
                System.out.println("All activities have been added to the itinerary - there are no more activities available to select.");
                break;
            }
            
            // Prompts for activity code for the current activity number.
            System.out.println("Please enter an activity code for activity number " + totalActivitiesEntered + "/" + finalTotalActivities + ":");
            System.out.println("\nAvailable Activities: ");
            
            // Outputs a list of available activities.
            for (String activityCode : availableActivities) {
                int index = activityCodesList.indexOf(activityCode);
                String activityTitle = newActivity.setActivityTitleFromCode(activityCodesList, index);
                System.out.println(activityCode + " - " + newActivity.activityBaseCostOutput(activityTitle));
            }
            
            // Takes user input.
            String activityCodeInput = in.nextLine().trim();

            // Validate activity code input
            if (activityCodeInput.isEmpty()) {
                System.out.println("Error! Empty input.\nPlease enter a valid activity code: ");
                continue;
            }
            
            // Creates an array of codes by splitting the activity code input by spaces.
            String[] codes = activityCodeInput.split("\\s+");
            // Checks if the length of the array is higher than one.
            // Checking for multiple activity codes at once.
            if (codes.length > 1) {
                System.out.println("Error! You cannot enter multiple activity codes at once. Please enter only one code: ");
                continue;
            }
            
            // The singleCode is set to position zero of the codes array.
            String singleCode = codes[0];
            // Checks if the activityCodesList does not contain the singleCode.
            // Chceking for invalid activity code.
            if (!activityCodesList.contains(singleCode)) {
                System.out.println("Error! Invalid activity code entered.\nEnter a valid activity code: ");
                continue;
            }
            
            // Checks if the activityCodesList does contain the singleCode.
            // Checking for duplicate activity code.
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
        // Return the itinerary string without trailing spaces
        return itineraryBuilder.toString().trim();
    }

}
