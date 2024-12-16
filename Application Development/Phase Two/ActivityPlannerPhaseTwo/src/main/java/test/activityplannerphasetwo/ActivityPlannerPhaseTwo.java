/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package test.activityplannerphasetwo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author E4092399
 */
public class ActivityPlannerPhaseTwo {

    public static void main(String[] args) {
        
        // A list of type addon is initialised with addOns by calling the initialiseAddOns method.
        List<AddOn> addOnsList = initialiseAddOns();
        // Similarly, a list of type activities is populated with activities by calling the initialiseActivities method.
        // The addOnsList is passed as a parameter so the addOnsList can be added to each instance of the activity class.
        List<Activity> activities = initialiseActivities(addOnsList);
        
        // Custom itinerary date used to instantiate an instance of the itinerary class.
        LocalDate customItineraryDate = LocalDate.of(2024, 12, 9);
        Itinerary newItinerary = new Itinerary(customItineraryDate);
        
        //  Method call that returns the number of activities by passing the activities list as a parameter.
        newItinerary.setNumberOfActivities(activities);

        
        /**
         * 
         * OUTPUTS
         * 
        */
        System.out.println("\n" + newItinerary.toString() + "\n");
        System.out.println("Activity Costs:");
        //  Output the return values of the setActivityBaseActivityCost method for each activity.
        //  The method determines the base cost for each activity based on the activity title.
        System.out.println(activities.get(0).setActivityBaseActivityCost());
        System.out.println(activities.get(1).setActivityBaseActivityCost());
        System.out.println(activities.get(2).setActivityBaseActivityCost());
        //  Outputs the return value of the setTotalActivityCost method.
        //  The method sets the total activity cost by looping through the list of activities and getting the base cost of each activity.
        System.out.printf("The total cost of all activities is: £%.2f\n", newItinerary.setTotalActivityCost());

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
        System.out.printf("\nThe total itinerary cost (with discount applied is): £%.2f", newItinerary.setItineraryTotalCost());
        
        System.out.println("\n\nItinerary details have successfully been written to file.");
        System.out.println("-------------------------------------------------------------");
        
        // Method calls
        // outputItineraryToFile method takes the instance of the itinerary class created with the addOnsList and the attributes of each instance of the activity class
        // using .get from the activities list.
        saveOrLoadItineraryFromFile();
        outputItineraryToFile(newItinerary, addOnsList, activities.get(0), activities.get(1), activities.get(2));
        
        
        
    }
    
    
    // Methods
    
    /**
     * 
     * initialiseAddOns() method
     * @return an addOnsList populated with the two new instances of the addOn class
     * initialised within the method.
     * 
     */
    
    public static List<AddOn> initialiseAddOns(){
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
     * @param addOnsList
     * @return a list that is initialised to take each activity object.
     * 
     */
    
    public static List<Activity> initialiseActivities(List<AddOn> addOnsList){
         // Method calls
        initialiseAddOns();

        //  Custom activity date and time is set here to be passed into the new instance of the activity class.
        LocalDate customActivityDate = LocalDate.of(2024, 12, 15);
        
        LocalTime customActivityTime = LocalTime.of(14, 30);

        //  Creates new instance of the activity class with default constructor values.
        Activity activityOne = new Activity();
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
        //  Third activity instantiated with the same default constructor values as activityOne for testing purposes.
        Activity activityThree = new Activity();
        activityThree.setAddOnsList(new ArrayList<>());


        //  Outputs toString methods of each instance of the activity class. Outputs the attributes of each activity.
        System.out.println(activityOne.toString() + "\n\n" + activityTwo.toString() + "\n" + activityThree.toString());
        
        //  Returns a list that takes activity objects is initialised to take each activity object.
        return Arrays.asList(activityOne, activityTwo, activityThree);
    }
    
    /**
     * 
     * saveOrLoadItineraryFromFile() method
     * Allows the user to load or save an itinerary from/to the 'itineraries.txt' file.
     * Saving an itinerary calls the inputItineraryDetails() method.
     * 
     */
    
    
    public static void saveOrLoadItineraryFromFile(){
        Scanner in = new Scanner(System.in);
        File file = new File("Itineraries.txt");
        
        System.out.println("Load or save an itinerary from: " + file + "? ");
        System.out.println("----------------------------------------------");
        String choice = in.nextLine();
        
        
        switch(choice){
            case "Load", "load" -> {
                
                boolean found = false;
                
                // Load logic
                System.out.println("\nEnter the reference number of the itinerary you want to load: ");
                String loadItineraryFromRef = in.nextLine();
                
                try(Scanner fileReader = new Scanner(file)){
                    while (fileReader.hasNextLine()){
                        String line = fileReader.nextLine();
                        if (line.toLowerCase().contains(loadItineraryFromRef.toLowerCase())){
                            System.out.println("An itinerary was found with the reference number: " + loadItineraryFromRef + "\n" + line);
                            found = true;
                            fileReader.close();
                            break;
                        }
                    }
                    
                    if (!found){
                        System.out.println("An itinerary was not found with the reference number: " + loadItineraryFromRef);
                    }
                } 
                catch (FileNotFoundException ex) {
                    Logger.getLogger(ActivityPlannerPhaseTwo.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
            }
            case "Save", "save" -> {
                
                // Method call
                inputItineraryDetails();
                
            }
            default -> {
                System.out.println("ERROR! A load or save option was not selected!");
                System.exit(0);
            }   
               
                
        }
        
        in.close();
    }
    
    
    /**
     * 
     * inputItineraryDetails() method
     * A void method that allows the user to input itinerary details to the 'itineraries.txt' file.
     * The user-inputted details are validated and then passed to the saveItineraryToFile() method.
     * 
     */
    
    public static void inputItineraryDetails(){
        // Save an itinerary
        // Get a reference number, date, attendee name, total cost (in pence), total activities and whether there is any add-ons.
        // Call seperate method
        
        ArrayList<String> addOnCodes = new ArrayList<>();
        addOnCodes.add("TRV");
        addOnCodes.add("INS");
        addOnCodes.add("PHO");
        addOnCodes.add("GRT");
        addOnCodes.add("GBG");
        addOnCodes.add("CTB");
        addOnCodes.add("EAT");
        
        File file = new File("itineraries.txt");
        Scanner in = new Scanner(System.in);
        
        System.out.println("\nSave Itinerary to: " + file + "\nEnter the itinerary date: ");
        LocalDate itineraryDate = null;

        while (itineraryDate == null) {
            String dateInput = in.nextLine();
            try {
                itineraryDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
                System.out.println("Success! Valid itinerary date entered: " + itineraryDate);

            } catch (DateTimeParseException e) {
                System.out.println("Error! Invalid date format entered. Please enter the date in YYYY-MM-DD format: ");
            }
        }
        
        LocalDate finalItineraryDate = itineraryDate;
        Itinerary newItinerary = new Itinerary(itineraryDate);

        System.out.println("Enter the itinerary reference number: ");
        boolean flag = false;
        
        String refNumInput = null;
        while (!flag) {
            refNumInput = in.nextLine();
            // Validate reference number with method call
            String validateRef = newItinerary.setItineraryReferenceNumber(refNumInput);
            System.out.println(validateRef);

            if (validateRef.contains("ERR_REFERENCE_NUMBER")) {
                System.out.println("\nEnter a valid reference number: ");
            } 
            else {
                flag = true;
            }
        }
        
        String finalRefNumber = refNumInput;
        
        System.out.println("Enter the name of the itinerary's lead attendee: ");
        flag = false;
        
        String attendeeNameInput = null;
        while (!flag){
            attendeeNameInput = in.nextLine();
            
            boolean validateAttendee = newItinerary.setLeadAttendeeName(attendeeNameInput);
            
            if (validateAttendee == false){
                System.out.println("Error! Invalid lead attendee name entered\nEnter a valid lead attendee name: ");
            }
            else{
                flag = true;
            }
        }
        
        String finalAttendeeNameInput = attendeeNameInput;
        
        System.out.println("Enter the total cost of the itinerary (in pence): ");
        flag = false;
        
        String itineraryTotalCost = null;
        while(!flag){
            itineraryTotalCost = in.nextLine();
            double validateCost = Double.parseDouble(itineraryTotalCost);
            
            if (validateCost <= 0){
                System.out.println("Error! The total cost cannot be zero or under!\nEnter a valid total itinerary cost: ");
            }
            else{
                System.out.println("Success! Valid itinerary cost entered!");
                flag = true;
            }
        }
        
        String finalItineraryCost = itineraryTotalCost;
        
        System.out.println("Enter the total number of activities in the itinerary: ");
        flag = false;
        
        int validateTotal = 0;
        
        while(!flag){
            String totalActivities = in.nextLine();
            validateTotal = Integer.parseInt(totalActivities);
            
            if (validateTotal <= 0){
                System.out.println("Error! The total number of activities cannot be zero or under!\nEnter a valid number of activities: ");
            }
            else{
                System.out.println("Success! Valid total number of activities entered!");
                flag = true;
            }
            
        }
        
        int finalTotalActivities = validateTotal;
        
        flag = false;
        System.out.println("Enter add-on codes for the itinerary activities (ENTER DONE WHEN YOU HAVE ADDED ALL ADD-ONS): ");
        
        StringBuilder stringBuilder = new StringBuilder();
        
        while(!flag){
            String addOnsInput = in.nextLine();
            
            if ("DONE".equals(addOnsInput)){
                flag = true;
                System.out.println("Success! Add-Ons have been added to the itinerary!");
            }
            else{
                boolean isAddOn = false;
                
                for (String addOn : addOnCodes){
                    if (addOnsInput.equals(addOn)){
                        isAddOn = true;
                        System.out.println("Success! Added add-on to itinerary: " + addOn);
                        stringBuilder.append(addOn + " ");
                        break;
                    }
                }
                
                if (!isAddOn){
                    System.out.println("Error! Invalid itinerary add-on entered\nEnter a valid itinerary add-on: ");
                }
                
                System.out.println("Additional itinerary add-ons?: ");
            }
            
        }
        
        // the finalAddsOnsInput is always going to be 'DONE'.
        String finalAddsOnInput = stringBuilder.toString();
        
        saveItineraryToFile(newItinerary, finalItineraryDate, finalRefNumber, finalAttendeeNameInput, finalItineraryCost, finalTotalActivities, finalAddsOnInput);
        
    }
    
    /**
     * 
     * saveItineraryToFile() method
     * The method writes the user-inputted itinerary details from the method above to the 'itineraries.txt' file.
     * 
     * @param newItinerary
     * @param finalItineraryDate
     * @param finalRefNumber
     * @param finalAttendeeNameInput
     * @param finalItineraryCost
     * @param finalAddsOnInput
     * @param finalTotalActivities
     */
    
    public static void saveItineraryToFile(Itinerary newItinerary, LocalDate finalItineraryDate, String finalRefNumber, String finalAttendeeNameInput, String finalItineraryCost, int finalTotalActivities, String finalAddsOnInput){
        
        Output newOutput = new Output();
        
        File file = new File("itineraries.txt");
        
        LocalDate itineraryDate = newOutput.generateItineraryDate(newItinerary);
        String RefNumber = newOutput.generateItineraryRef(newItinerary);
        String attendeeNameInput = newOutput.generateAttendeeName(newItinerary);
        
        String t = "\t";
        
        try (FileWriter newWriter = new FileWriter(file, true)){
            newWriter.write(RefNumber + t + itineraryDate + t + attendeeNameInput + t + finalItineraryCost + t + finalTotalActivities + t +
                    finalAddsOnInput + "\n");
            
        } 
        catch (IOException ex) {
            Logger.getLogger(ActivityPlannerPhaseTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * 
     * outputItineraryToFile() method
     * The method writes the itinerary details that were created from the non-interactive side of the application to the 'itineraries.txt' file.
     * 
     * @param newItinerary
     * @param addOnsList
     * @param activityOne
     * @param activityThree
     * @param activityTwo
     */
    
    
    public static void outputItineraryToFile(Itinerary newItinerary, List<AddOn> addOnsList, Activity activityOne, Activity activityTwo, Activity activityThree){
        Output newOutput = new Output();
        
        File file = new File("itineraries.txt");
        
        String itineraryRef = newOutput.generateItineraryRef(newItinerary);
        LocalDate date = newOutput.generateItineraryDate(newItinerary);
        String attendeeName = newOutput.generateAttendeeName(newItinerary);
        String totalCost = newOutput.generateTotalItineraryCostInPence(newItinerary);
        int totalActivities = newOutput.generateTotalActivities(newItinerary);
        
        String activityOneCode = newOutput.generateActivitiesCode(activityOne, new ArrayList<>());
        String activityTwoCode = newOutput.generateActivitiesCode(activityTwo, addOnsList);
        String activityThreeCode = newOutput.generateActivitiesCode(activityThree, new ArrayList<>());
        
        String t = "\t";
        
        String newEntry = itineraryRef + t + date + t + attendeeName + t + totalCost + t + totalActivities + t +
                      activityOneCode + activityTwoCode + activityThreeCode + t;
        
        boolean duplicateEntry = false;
        try (BufferedReader newBr = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = newBr.readLine()) != null) {
                if (line.contains(itineraryRef)) {
                    duplicateEntry = true;
                    break;
                }
            }
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(ActivityPlannerPhaseTwo.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(ActivityPlannerPhaseTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
        // if there is not a duplicate entry - write to the file
        if (!duplicateEntry) {
            try (FileWriter newWriter = new FileWriter(file, true)) {
                newWriter.write(newEntry);
            } 
            catch (IOException ex) {
                Logger.getLogger(ActivityPlannerPhaseTwo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

