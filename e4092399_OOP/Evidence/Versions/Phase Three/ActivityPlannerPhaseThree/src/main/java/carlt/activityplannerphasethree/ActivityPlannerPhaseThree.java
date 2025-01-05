/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package carlt.activityplannerphasethree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlt
 */
public class ActivityPlannerPhaseThree {
    
    public static void main(String[] args) {
        
        System.out.println("\n============ Activity Planner System - Phase One ============");
        
        // A list of type addon is initialised with addOns by calling the initialiseAddOns method.
        List<AddOn> addOnsList = initialiseAddOns();
        // Similarly, a list of type activities is populated with activities by calling the initialiseActivities method.
        // The addOnsList is passed as a parameter so the addOnsList can be added to each instance of the activity class.
        List<Activity> activities = initialiseActivities(addOnsList);
        int finalTotalActivities = 0;
        
        outputActivities(activities);
        
        List<String> activityCodesList = new ArrayList<>();
        
        for (Activity activity : activities){
            activityCodesList.add(activity.getCode());
        }

        // Custom itinerary date used to instantiate an instance of the itinerary class.
        LocalDate customItineraryDate = LocalDate.of(2024, 12, 25);
        Itinerary newItinerary = new Itinerary(customItineraryDate);

        //  Method call that returns the number of activities by passing the activities list as a parameter.
        newItinerary.setNumberOfActivities(finalTotalActivities, activities);

        /**
         *
         * OUTPUTS
         *
         */
        System.out.println(newItinerary.toString());
        System.out.println("\nActivity Costs:");
        
        double totalCost = 0;
        
        for (int index = 0; index < activities.size(); index++){
            activities.get(index).setActivityBaseActivityCost();
            
            String activityTitle = activities.get(index).getActivityTitle();
            
            double activityCost = activities.get(index).getBaseActivityCost();
            
            double totalActivityCost = activityCost * newItinerary.getNumOfAttendees();
            
            System.out.println(activityTitle + ": " + String.format("£%.2f", activityCost)
                    + " * " + newItinerary.getNumOfAttendees() + " Attendees = " + String.format("£%.2f", totalActivityCost));
            
            totalCost += totalActivityCost;
        }
        
        

        System.out.println("\nItinerary Costs:");
        //  Outputs the return values of two methods: one method sets the itinerary base cost without add-ons, the other sets the itinerary base cost, with add-ons.
        System.out.printf("The itinerary base cost (add-ons not included) is: £%.2f\n", newItinerary.setItineraryBaseCost());
        System.out.printf("The total itinerary base cost (add-ons included) is: £%.2f\n", newItinerary.setItineraryBaseCostWithAddOns());
        //  Method call for use in calculation.
        //  The method works out the discount percentage for the itinerary based on the number of activities taken and the number of attendees.
        newItinerary.setItineraryDiscountPercentage();
        //  Returns value from method call.
        //  The setDiscountOutput method works out the discount applied by
        //  subtracting the itinerary base cost with add-ons away from the total itinerary cost with discount.
        System.out.printf("Discount = £%.2f", newItinerary.setDiscountOutput());
        System.out.printf("\nThe total itinerary cost (with discount applied is): £%.2f\n", newItinerary.setItineraryTotalCost());
        
        System.out.println("\n============ Activity Planner System - Phase Two ============");
        System.out.println("Itinerary details have successfully been written to file.");
        System.out.println("-------------------------------------------------------------");

        // Method calls
        // outputItineraryToFile method takes the instance of the itinerary class created with the addOnsList and the attributes of each instance of the activity class
        // using .get from the activities list.
        
        Output newOutput = new Output();
        saveOrLoadItineraryFromFile(new Activity(), activities, activityCodesList, addOnsList, newItinerary, newOutput, new AddOn());
        outputItineraryToFile(newItinerary, addOnsList, activities.get(0), activities.get(1));
    }

    // Methods
    /**
     *
     * initialiseAddOns() method
     *
     * @return an addOnsList populated with the two new instances of the addOn
     * class initialised within the method.
     *
     */
    public static List<AddOn> initialiseAddOns() {
        //  Initialises a new ArrayList that takes AddOn objects 
        List<AddOn> addOnsList = new ArrayList<>();

        //  Initialises two new instances of the AddOn class and gives them preset values.
        AddOn travelActivityAddOn = new AddOn("Activity", "Travel", "TRV", 0.0, 0.0);
        AddOn accommodationItineraryAddOn = new AddOn("Itinerary", "Accommodation", "ACC", 0.0, 0.0);

        //  Method calls on the addOn instances - to set the cost and type of each add-on.
        travelActivityAddOn.setAddOnCostAndType();
        travelActivityAddOn.setAddOnCode();
        accommodationItineraryAddOn.setAddOnCostAndType();
        accommodationItineraryAddOn.setAddOnCode();

        //  Adds the attributes of each AddOn instance to the addOnsList.
        addOnsList.add(travelActivityAddOn);
        addOnsList.add(accommodationItineraryAddOn);

        return addOnsList;
    }

    /**
     *
     * initialiseActivities method
     *
     * @param addOnsList
     * @return a list that is initialised to take each activity object.
     *
     */
    public static List<Activity> initialiseActivities(List<AddOn> addOnsList) {
        // Method calls
        initialiseAddOns();

        //  Custom activity date and time is set here to be passed into the new instance of the activity class.
        LocalDate customActivityDate = LocalDate.of(2024, 12, 15);
        LocalTime customActivityTime = LocalTime.of(14, 30);

        //  Creates new instance of the activity class with default constructor values.
        Activity activityOne = new Activity();
        activityOne.setActivityCode();
        activityOne.validateActivityCode();
        activityOne.setAddOnsList(new ArrayList<>());

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
        activityTwo.setActivityCode();
        activityTwo.validateActivityCode();
        
        Activity activityThree = new Activity(
                0,
                "ACT-03",
                "SAS-Style Assault Courses",
                "Lock in",
                "Middlesbrough",
                customActivityDate,
                customActivityTime,
                120,
                true,
                addOnsList
        );
        activityThree.setActivityCode();
        activityThree.validateActivityCode();
        activityThree.setAddOnsList(new ArrayList<>());

        //  Returns a list that takes activity objects is initialised to take each activity object.
        return Arrays.asList(activityOne, activityTwo, activityThree);
    }
    
    public static void outputActivities(List<Activity> activities) {
        for (Activity activity : activities) {
            System.out.println(activity.toString());
        }
    }

    /**
     *
     * saveOrLoadItineraryFromFile() method Allows the user to load or save an
     * itinerary from/to the 'itineraries.txt' file. Saving an itinerary calls
     * the inputItineraryDetails() method.
     *
     * @param newActivity
     * @param activities
     * @param activityCodesList
     * @param addOnsList
     * @param newItinerary
     * @param newOutput
     * @param newAddOn
     */
    public static void saveOrLoadItineraryFromFile(Activity newActivity, List<Activity> activities,  List<String> activityCodesList, List<AddOn> addOnsList, Itinerary newItinerary, Output newOutput, AddOn newAddOn) {
        try (Scanner in = new Scanner(System.in)) {
            File file = new File("itineraries.txt");
            String choice;
            
            do {
                System.out.println("Load or save an itinerary from/to: " + file + "? ");
                System.out.println("\nLoad itineraries from file - console output (Load)");
                System.out.println("Create and write to itineraries - console output (Save)");
                System.out.println("-------------------------------------------------------");
                choice = in.nextLine();
                
                switch (choice.toLowerCase()) {
                    case "load" -> {
                        boolean found = false;
                        
                        // Load logic
                        System.out.print("\nEnter the reference number of the itinerary you want to load: ");
                        String loadItineraryFromRef = in.nextLine();
                        
                        try (Scanner fileReader = new Scanner(file)) {
                            while (fileReader.hasNextLine()) {
                                String line = fileReader.nextLine();
                                if (line.toLowerCase().contains(loadItineraryFromRef.toLowerCase())) {
                                    System.out.println("An itinerary was found with the reference number: " + loadItineraryFromRef + "\n" + line);
                                    found = true;
                                    loadExistingItinerarySummary(newActivity, activities, activityCodesList, addOnsList, newItinerary, newOutput, newAddOn);
                                    break;
                                }
                            }
                            
                            if (!found) {
                                System.out.println("An itinerary was not found with the reference number: " + loadItineraryFromRef);
                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ActivityPlannerPhaseThree.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return;
                    }
                    case "save" -> {
                        //Save logic from method call
                        inputItineraryDetails(choice);
                        return;
                    }
                    default -> {
                        System.out.println("ERROR! A load or save option was not selected!\nEnter a valid option: ");
                    }
                }
            } while (!choice.equalsIgnoreCase("load") && !choice.equalsIgnoreCase("save"));
        }
        
        
    } 

   

    /**
     *
     * inputItineraryDetails() method A void method that allows the user to
     * input itinerary details to the 'itineraries.txt' file. The user-inputted
     * details are validated and then passed to the saveItineraryToFile()
     * method.
     *
     * @param choice
     */
    public static void inputItineraryDetails(String choice) {
        // Save an itinerary
        // Get a reference number, date, attendee name, total cost (in pence), total activities and whether there is any add-ons.
        
        Output newOutput = new Output();
        
        // Create fresh lists for user input phase
        AddOn newAddOn = new AddOn();
        List<AddOn> addOnsList = new ArrayList<>();
        List<String> activityAddOnCodesList = AddOn.getActivityAddOnCodesList();
        List<String> itineraryAddOnCodesList = AddOn.getItineraryAddOnCodesList();
        
        Activity newActivity = new Activity();
        List<Activity> activities = new ArrayList<>();
        List<String> activityCodesList = newActivity.getActivityCodesList();

        File file = new File("itineraries.txt");
        Scanner in = new Scanner(System.in);
        
        System.out.print("\nSave Itinerary to: " + file + "\nEnter the itinerary date (YYYY-MM-DD): ");
        
        LocalDate finalItineraryDate = Validation.validateItineraryDate();
        Itinerary newItinerary = new Itinerary(finalItineraryDate);
        
        System.out.print("Enter the itinerary reference number (1 letter, 4 Digits, 1 letter): ");
        String finalRefNumber = Validation.validateItineraryReferenceNumber(newItinerary);
        
        System.out.print("Enter the name of the itinerary's lead attendee (Initial <SPACE> Surname): ");
        String finalAttendeeNameInput = Validation.validateLeadAttendeeName(newItinerary);
        
        System.out.print("Enter the total number of attendees: ");
        int finalTotalNumberOfAttendees = Validation.validateTotalNumberOfAttendees();
        newItinerary.setNumOfAttendees(finalTotalNumberOfAttendees);
        
        System.out.print("Enter the total number of activities in the itinerary (maximum of 5): ");
        int finalTotalActivities = Validation.validateTotalNumOfActivities();
        
        System.out.print("Enter an activity code to add an activity to the itinerary: ");
        String finalActivityCodes = Validation.validateActivityCode(activityCodesList, newActivity, finalTotalActivities, activityAddOnCodesList, "");
        
        String[] selectedActivities = finalActivityCodes.split("\\s+");
        
        Map<String,String> activityAddOnsMap = new HashMap<>();
        Map<String, String> itineraryAddOnsMap = new HashMap<>();
        
        // Create activities based on user selection
        for (String activityCode : selectedActivities) {
            Activity activity = new Activity();
            int index = activityCodesList.indexOf(activityCode);
            activity.setActivityTitleFromCode(activityCodesList, index);
            activity.setActivityBaseActivityCost();
            activities.add(activity);
            
            // Process activity add-ons for this activity
            if (!activityAddOnsMap.containsKey(activityCode)) {
                String activityAddOns = Validation.validateActivityAddOnInput(activityAddOnCodesList, activityCode, newAddOn);
                activityAddOnsMap.put(activityCode, activityAddOns);
                
                // Create and add the selected add-ons
                if (!activityAddOns.equals("NONE")) {
                    String[] activityAddOnCodes = activityAddOns.split("\\s+");
                    for (String addOnCode : activityAddOnCodes) {
                        if (!addOnCode.equals("DONE")) {
                            AddOn activityAddOn = new AddOn("Activity", getAddOnTitle(addOnCode), addOnCode, 0.0, 0.0);
                            activityAddOn.setAddOnCostAndType();
                            activity.addAddOn(activityAddOn);
                        }
                    }
                }
            }
            
            // Process itinerary add-ons for this activity
            if (!itineraryAddOnsMap.containsKey(activityCode)){
                String itineraryAddOns = Validation.validateItineraryAddOnInput(itineraryAddOnCodesList, activityCode, newAddOn);
                itineraryAddOnsMap.put(activityCode, itineraryAddOns);
                
                if (!itineraryAddOns.equals("NONE")){
                    String[] itineraryAddOnCodes = itineraryAddOns.split("\\s+");
                    for (String addOnCode : itineraryAddOnCodes){
                        if (!addOnCode.equals("DONE")){
                            AddOn itineraryAddOn = new AddOn("Itinerary", getAddOnTitle(addOnCode), addOnCode, 0.0, 0.0);
                            itineraryAddOn.setAddOnCostAndType();
                            activity.addAddOn(itineraryAddOn);
                        }
                    }
                }
            }
        }
        
        // Set the activities in the itinerary
        newItinerary.setNumberOfActivities(finalTotalActivities, activities);
        
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("The inputted itinerary details have successfully been written to file.");
        System.out.println("--------------------------------------------------------------------------");
        
        saveItineraryToFile(newItinerary, finalItineraryDate, finalRefNumber, finalAttendeeNameInput, finalTotalNumberOfAttendees, finalTotalActivities, finalActivityCodes, activityAddOnsMap);
        viewInputtedItinerarySummary(newActivity, activities, activityCodesList, addOnsList, newItinerary, newOutput, newAddOn);
    }

    /**
     *
     * saveItineraryToFile() method The method writes the user-inputted
     * itinerary details from the method above to the 'itineraries.txt' file.
     *
     * @param newItinerary
     * @param finalItineraryDate
     * @param finalRefNumber
     * @param finalAttendeeNameInput
     * @param finalTotalNumberOfAttendees
     * @param finalActivityCodes
     * @param finalTotalActivities
     */
    public static void saveItineraryToFile(Itinerary newItinerary, LocalDate finalItineraryDate, String finalRefNumber, String finalAttendeeNameInput, int finalTotalNumberOfAttendees, int finalTotalActivities, String finalActivityCodes , Map<String,String> activityAddOnsMap) {

        Output newOutput = new Output();

        File file = new File("itineraries.txt");

        LocalDate itineraryDate = newOutput.generateItineraryDate(newItinerary);
        String refNumber = newOutput.generateItineraryRef(newItinerary);
        String attendeeNameInput = newOutput.generateAttendeeName(newItinerary);

        String t = "\t";
        
        try{
            boolean duplicateEntry = false;
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                while((line = br.readLine())!= null){
                    if (line.toLowerCase().contains(refNumber)){
                        duplicateEntry = true;
                        break;
                    }
                }
            }
            
            if (!duplicateEntry){
                try (FileWriter newWriter = new FileWriter(file, true)) {
                    newWriter.write("\n" + refNumber + t + itineraryDate + t + attendeeNameInput + t + finalTotalNumberOfAttendees + t + finalTotalActivities + t
                            + t + finalActivityCodes + t + activityAddOnsMap + "\n");
                }
            } else{
                System.out.println("Duplicate entry found. Itinerary was not added to the file.");
            }
        
        
       

        } catch (IOException ex) {
            Logger.getLogger(ActivityPlannerPhaseThree.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * outputItineraryToFile() method The method writes the itinerary details
     * that were created from the non-interactive side of the application to the
     * 'itineraries.txt' file.
     *
     * @param newItinerary
     * @param addOnsList
     * @param activityOne
     * @param activityThree
     * @param activityTwo
     */
    
    public static void outputItineraryToFile(Itinerary newItinerary, List<AddOn> addOnsList, Activity activityOne, Activity activityTwo) {
        Output newOutput = new Output();

        File file = new File("itineraries.txt");

        String itineraryRef = newOutput.generateItineraryRef(newItinerary);
        LocalDate date = newOutput.generateItineraryDate(newItinerary);
        String attendeeName = newOutput.generateAttendeeName(newItinerary);
        int totalNumOfAttendees = newOutput.generateNumberOfAttendees(newItinerary);
        String totalCost = newOutput.generateTotalItineraryCostInPence(newItinerary);
        int totalActivities = newOutput.generateTotalActivities(newItinerary);

        String activityOneCode = newOutput.generateActivitiesCode(activityOne, new ArrayList<>());
        String activityTwoCode = newOutput.generateActivitiesCode(activityTwo, addOnsList);

        String t = "\t";

        String newEntry = itineraryRef + t + date + t + attendeeName + t + totalNumOfAttendees + t + totalCost + t + totalActivities + t
                + activityOneCode + activityTwoCode;

        try (FileWriter newWriter = new FileWriter(file, true)) {
            newWriter.write(newEntry);
        } catch (IOException ex) {
            Logger.getLogger(ActivityPlannerPhaseThree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String getAddOnTitle(String addOnCode) {
        return switch (addOnCode) {
            case "TRV" -> "Travel";
            case "INS" -> "Insurance";
            default -> "Unknown Add-on";
        };
    }
    
    public static void loadExistingItinerarySummary(Activity newActivity, List<Activity> activities, List<String> activityCodesList, List<AddOn> addOnsList,  Itinerary newItinerary, Output newOutput, AddOn newAddOn){
        System.out.println("\n================= Activity Planner System - Phase Three ================");
        StringBuilder itinerarySummary = new StringBuilder();
        
        String topBorder = "+=====================================================+";
        itinerarySummary.append(topBorder);
        
        // Lead Attendee and Reference Number row.
        String clientRefRow = String.format("\n|                                                     |\n|    Client: %-20s Ref: %-15s|", 
                            newOutput.generateAttendeeName(newItinerary), newOutput.generateItineraryRef(newItinerary));
        itinerarySummary.append(clientRefRow);
        
        // Date row
        String dateRow = String.format("\n|    Date: %-43s|",
                newOutput.generateItineraryDate(newItinerary));
        itinerarySummary.append(dateRow);
        
        // Activities/Attendees row
        String activitiesAttendeesRow = String.format("\n| Activities: %-15s Attendees: %-13s|",
                newOutput.generateTotalActivitiesString(newItinerary), newOutput.generateNumOfAttendeesString(newItinerary));
        itinerarySummary.append(activitiesAttendeesRow);
        
        // Itinerary total cost row
        String itineraryCostRow = String.format("\n|                                                     |\n| Cost: £%-44s|", newOutput.generateTotalItineraryCost(newItinerary));
        itinerarySummary.append(itineraryCostRow);
        
        // Cost breakdown heading row
        String costBr = String.format("\n|                                                     |\n|%20sCost breakdown%19s|", "", "");
        itinerarySummary.append(costBr);
        
        // Itinerary Add-Ons row
        String addOnsRow = String.format("\n| Itinerary Add-ons%-16s Sub-Total: £%.2f|", "", newOutput.generateAddOnSubtotal(newAddOn, addOnsList));
        itinerarySummary.append(addOnsRow);
        
        
        
        // Activities row
        String activitiesRow = String.format("\n|                                                     |\n| Activities%-22s Sub-Total: £%-6.2f|", "", 
                newOutput.generateActivitiesSubtotal(newActivity, activityCodesList));
        itinerarySummary.append(activitiesRow);
        
        // Specific activity rows
        String spActivitiesRow = newOutput.generateActivityRows(activities, newActivity);
        itinerarySummary.append(spActivitiesRow);
        
     
        
        // Add the remaining empty rows with the pipe borders
        for (int index = 0; index < 6; index++){
            itinerarySummary.append("\n|                                                     |");
        }
        
        String discountRow = String.format(
                "\n| %-3s%-29s Sub-Total: £%-6.2f|",
                newOutput.generateDiscountNumber(newItinerary) + "%", 
                "Discount",
                newOutput.generateDiscountSubTotal(newItinerary)
        );
        itinerarySummary.append(discountRow);

        
        // Add bottomborder.
        String bottomBorder = "\n+=====================================================+";
        itinerarySummary.append(bottomBorder);
        // Outputs full itinerary summary from the stringbuilder.
        System.out.println(itinerarySummary);
        
    }
    
    public static void viewInputtedItinerarySummary(Activity newActivity, List<Activity> activities, List<String> activityCodesList, List<AddOn> addOnsList,  Itinerary newItinerary, Output newOutput, AddOn newAddOn) {
        System.out.println("\n============ Activity Planner System - Phase Three ============");
        StringBuilder itinerarySummary = new StringBuilder();
        
        String topBorder = "\n+=====================================================+";
        itinerarySummary.append(topBorder);
        
        // Lead Attendee and Reference Number row.
        String clientRefRow = String.format("\n|                                                     |\n|    Client: %-20s Ref: %-15s|", 
                            newOutput.generateAttendeeName(newItinerary), newOutput.generateItineraryRef(newItinerary));
        itinerarySummary.append(clientRefRow);
        
        // Date row
        String dateRow = String.format("\n|    Date: %-43s|",
                newOutput.generateItineraryDate(newItinerary));
        itinerarySummary.append(dateRow);

        // Activities/Attendees row
        String activitiesAttendeesRow = String.format("\n| Activities: %-15s Attendees: %-13s|",
                newOutput.generateTotalActivitiesString(newItinerary), newOutput.generateNumOfAttendeesString(newItinerary));
        itinerarySummary.append(activitiesAttendeesRow);
     
        // Calculate total cost based on selected activities and add-ons
        double totalCost = 0.0;
        
        // First calculate base activity costs
        for (Activity activity : activities) {
            switch(activity.getActivityTitle()) {
                case "Building a bridge from paper" -> totalCost += 25.00;
                case "Cookery Classes" -> totalCost += 75.00;
                case "SAS-Style Assault Courses" -> totalCost += 175.00;
                case "Obstacle Course" -> totalCost += 125.00;
                case "Cavern Ziplining" -> totalCost += 225.00;
            }
            
            // Add travel cost (£12.00) for each activity if selected
            List<AddOn> activityAddOns = activity.getAddOnsList();
            if (activityAddOns != null) {
                for (AddOn addOn : activityAddOns) {
                    if (addOn.getAddOnCode().equals("TRV")) {
                        totalCost += 12.00;
                    }
                }
            }
        }
        
        // Multiply by number of attendees
        totalCost *= newItinerary.getNumOfAttendees();
        
        // Apply discount based on number of activities and attendees
        double discountPercentage = 0.0;
        int numActivities = activities.size();
        int numAttendees = newItinerary.getNumOfAttendees();
        
        if (numActivities >= 3 && numActivities <= 5 && numAttendees < 10) {
            discountPercentage = 0.05; // 5% discount
        } else if (numActivities >= 3 && numActivities <= 5 && numAttendees >= 10 && numAttendees <= 20) {
            discountPercentage = 0.08; // 8% discount
        } else if (numActivities >= 3 && numActivities <= 5 && numAttendees > 20) {
            discountPercentage = 0.12; // 12% discount
        } else if (numActivities >= 6 && numAttendees < 10) {
            discountPercentage = 0.10; // 10% discount
        } else if (numActivities >= 6 && numAttendees >= 10 && numAttendees <= 20) {
            discountPercentage = 0.12; // 12% discount
        } else if (numActivities >= 6 && numAttendees > 20) {
            discountPercentage = 0.14; // 14% discount
        }
        
        double discountAmount = totalCost * discountPercentage;
        totalCost -= discountAmount;
        
        // Itinerary total cost row
        String itineraryCostRow = String.format("\n|                                                     |\n| Cost: £%-44.2f|", totalCost);
        itinerarySummary.append(itineraryCostRow);
        
        // Add bottomborder.
        String bottomBorder = "\n+=====================================================+";
        itinerarySummary.append(bottomBorder);
        
        // Outputs full itinerary summary from the stringbuilder.
        System.out.println(itinerarySummary);
        inputAnotherItinerary();
    }
    
    // Method asking the user if they want to enter another itinerary.
    public static void inputAnotherItinerary(){
        File newFile = new File("itineraries.txt");
        System.out.println("Saved inputted itinerary to: " + newFile);
        
        Scanner in = new Scanner(System.in);
        System.out.print("\nWould you like to input another itinerary? (Y/N): ");
        String inputAgain = in.nextLine();
        
        while(!inputAgain.equalsIgnoreCase("Y") && !inputAgain.equalsIgnoreCase("N")){
            System.out.print("Error! A Y or N option has not been provided\nPlease try again: ");
            inputAgain = in.nextLine();
        }
        
        if (inputAgain.equalsIgnoreCase("Y")){
            String choice = "Save";
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("                            Input new itinerary                           ");
            System.out.println("--------------------------------------------------------------------------");
            inputItineraryDetails(choice);
        }
        
        System.out.println("Thanks for using the Activity/Itinerary Planner System! Shutting Down...!");
        in.close();
        System.exit(0);
        
    }
}
