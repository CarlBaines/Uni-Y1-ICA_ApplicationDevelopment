/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package carlt.activityplannerphasethree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.Channels;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlt
 */
public class ActivityPlannerPhaseThree {
    
    /**
     * 
     * THINGS I GOTTA CHANGE ABOUT THIS CODE:
     * - Allow user to enter the activity code of the activity that they want to do. 
     * Then ask the add-on they want for that specific activity before moving to the next one.
     * 
     * 
     * 
     * @param args
     */
    
    
    
    public static void main(String[] args) {
        AddOn newAddOn = new AddOn();
        // Initialize addOnCodesList before using it
        List<String> addOnCodesList = AddOn.getAddOnCodesList();
        // A list of type addon is initialised with addOns by calling the initialiseAddOns method.
        List<AddOn> addOnsList = initialiseAddOns();
        // Similarly, a list of type activities is populated with activities by calling the initialiseActivities method.
        // The addOnsList is passed as a parameter so the addOnsList can be added to each instance of the activity class.
        List<Activity> activities = initialiseActivities(addOnsList);

        // Custom itinerary date used to instantiate an instance of the itinerary class.
        LocalDate customItineraryDate = LocalDate.of(2024, 12, 25);
        Itinerary newItinerary = new Itinerary(customItineraryDate);

        //  Method call that returns the number of activities by passing the activities list as a parameter.
        newItinerary.setNumberOfActivities(activities);

        /**
         *
         * OUTPUTS
         *
         */
        System.out.println(newItinerary.toString());
        System.out.println("--------------------------------------------------------------");
        System.out.println("Activity Costs:");
        //  Output the return values of the ActivityBaseActivityCost method for each activity.
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
        
        Output newOutput = new Output();
        saveOrLoadItineraryFromFile(newItinerary, newOutput, newAddOn, addOnCodesList);
        outputItineraryToFile(newItinerary, addOnsList, activities.get(0), activities.get(1), activities.get(2));
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
        
        //  Third activity instantiated with the same default constructor values as activityOne for testing purposes.
        Activity activityThree = new Activity(
                0,
                "ACT-03",
                "SAS-Style Assault Courses",
                "Lock-in",
                "Middlesbrough",
                customActivityDate,
                customActivityTime,
                120,
                false,
                addOnsList
        );
        activityThree.setActivityCode();
        activityThree.validateActivityCode();

        //  Outputs toString methods of each instance of the activity class. Outputs the attributes of each activity.
        System.out.println(activityOne.toString() + "\n\n" + activityTwo.toString() + "\n" + activityThree.toString());

        //  Returns a list that takes activity objects is initialised to take each activity object.
        return Arrays.asList(activityOne, activityTwo, activityThree);
    }

    /**
     *
     * saveOrLoadItineraryFromFile() method Allows the user to load or save an
     * itinerary from/to the 'itineraries.txt' file. Saving an itinerary calls
     * the inputItineraryDetails() method.
     *
     * @param newItinerary
     * @param newOutput
     * @param newAddOn
     */
    public static void saveOrLoadItineraryFromFile(Itinerary newItinerary, Output newOutput, AddOn newAddOn, List<String> addOnsList) {
        try (Scanner in = new Scanner(System.in)) {
            File file = new File("itineraries.txt");
            String choice;
            
            do {
                System.out.println("Load or save an itinerary from/to: " + file + "? ");
                System.out.println("----------------------------------------------");
                choice = in.nextLine();
                
                switch (choice.toLowerCase()) {
                    case "load" -> {
                        boolean found = false;
                        
                        // Load logic
                        System.out.println("\nEnter the reference number of the itinerary you want to load: ");
                        String loadItineraryFromRef = in.nextLine();
                        
                        try (Scanner fileReader = new Scanner(file)) {
                            while (fileReader.hasNextLine()) {
                                String line = fileReader.nextLine();
                                if (line.toLowerCase().contains(loadItineraryFromRef.toLowerCase())) {
                                    System.out.println("An itinerary was found with the reference number: " + loadItineraryFromRef + "\n" + line);
                                    found = true;
                                    viewItinerarySummary(newItinerary, newOutput, newAddOn, addOnsList);
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
                        inputItineraryDetails();
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
     */
    public static void inputItineraryDetails() {
        // Save an itinerary
        // Get a reference number, date, attendee name, total cost (in pence), total activities and whether there is any add-ons.
        
        Output newOutput = new Output();
        
        AddOn newAddOn = new AddOn();
        List<String> addOnsList = AddOn.getAddOnCodesList();
        List<String> addOnCodesList = AddOn.getAddOnCodesList();
        
        Activity newActivity = new Activity();
        List<String> activityCodesList = newActivity.getActivityCodesList();

        File file = new File("itineraries.txt");
        Scanner in = new Scanner(System.in);
        
        // Logic for saving a date to the file + validation.
        System.out.println("\nSave Itinerary to: " + file + "\nEnter the itinerary date: ");
        
        LocalDate finalItineraryDate = Validation.validateItineraryDate();
        Itinerary newItinerary = new Itinerary(finalItineraryDate);
        
        // Logic for saving a reference number to the file + validation.
        System.out.println("Enter the itinerary reference number: ");
        String finalRefNumber = Validation.validateItineraryReferenceNumber(newItinerary);
        
        // Logic for saving a lead attendee name to the file + validation.
        System.out.println("Enter the name of the itinerary's lead attendee: ");
        String finalAttendeeNameInput = Validation.validateLeadAttendeeName(newItinerary);
        
        // Logic for saving the total number of attendees to the file + validation.
        System.out.println("Enter the total number of attendees: ");
        int finalTotalNumberOfAttendees = Validation.validateTotalNumberOfAttendees();
        
        // Logic for saving the total number of activities to the file + validation.
        System.out.println("Enter the total number of activities in the itinerary: ");
        int finalTotalActivities = Validation.validateTotalNumOfActivities();
        
        // Logic for saving the itinerary activity codes to the file + validation.
        System.out.println("Enter activity codes for the itinerary's activities (Enter DONE when you have finished entering activity codes): ");
        System.out.println("\nBuilding a bridge from paper: " + activityCodesList.get(0));
        System.out.println("Cookery Classes: " + activityCodesList.get(1));
        System.out.println("SAS-Style Assault Courses: " + activityCodesList.get(2));
        System.out.println("Obstacle Course: " + activityCodesList.get(3));
        System.out.println("Cavern Ziplining: " + activityCodesList.get(4));
        String finalActivityCodes = Validation.validateActivityCodes(activityCodesList);
        
        // Logic for saving the add-on codes for the itinerary activities to the file + validation.
        
        String finalAddOnsInput = Validation.validateAddOnsInput(addOnCodesList);
        
        // Logic for saving the total cost of the itinerary to the file + validation.
        System.out.println("Enter the total cost of the itinerary (in pence): ");
        double finalItineraryCost = Validation.validateTotalItineraryCost();
        
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("The inputted itinerary details have successfully been written to file.");
        System.out.println("--------------------------------------------------------------------------");
        
        
        viewItinerarySummary(newItinerary, newOutput, newAddOn, addOnsList);
        
        saveItineraryToFile(newItinerary, finalItineraryDate, finalRefNumber, finalAttendeeNameInput, finalTotalNumberOfAttendees, finalItineraryCost, finalTotalActivities, finalActivityCodes, finalAddOnsInput);

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
     * @param finalItineraryCost
     * @param finalAddOnsInput
     * @param finalActivityCodes
     * @param finalTotalActivities
     */
    public static void saveItineraryToFile(Itinerary newItinerary, LocalDate finalItineraryDate, String finalRefNumber, String finalAttendeeNameInput, int finalTotalNumberOfAttendees, double finalItineraryCost, int finalTotalActivities, String finalActivityCodes ,String finalAddOnsInput) {

        Output newOutput = new Output();

        File file = new File("itineraries.txt");

        LocalDate itineraryDate = newOutput.generateItineraryDate(newItinerary);
        String RefNumber = newOutput.generateItineraryRef(newItinerary);
        String attendeeNameInput = newOutput.generateAttendeeName(newItinerary);

        String t = "\t";

        try (FileWriter newWriter = new FileWriter(file, true)) {
            newWriter.write("\n" + RefNumber + t + itineraryDate + t + attendeeNameInput + t + finalTotalNumberOfAttendees + t + finalTotalActivities + t
                    + finalActivityCodes + t + finalAddOnsInput + t + finalItineraryCost + "\n");

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
    
    public static void outputItineraryToFile(Itinerary newItinerary, List<AddOn> addOnsList, Activity activityOne, Activity activityTwo, Activity activityThree) {
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
        String activityThreeCode = newOutput.generateActivitiesCode(activityThree, new ArrayList<>());

        String t = "\t";

        String newEntry = itineraryRef + t + date + t + attendeeName + t + totalNumOfAttendees + t + totalCost + t + totalActivities + t
                + activityOneCode + activityTwoCode + activityThreeCode;

        try (FileWriter newWriter = new FileWriter(file, true)) {
            newWriter.write(newEntry);
        } catch (IOException ex) {
            Logger.getLogger(ActivityPlannerPhaseThree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void phaseThreePart(Itinerary newItinerary, Output newOutput, AddOn newAddOn, List<String> addOnsList){
        StringBuilder consoleOutput = new StringBuilder();
        
        String topBorder = "\n+=====================================================+";
        consoleOutput.append(topBorder);
        
        // Lead Attendee and Reference Number row.
        String clientRefRow = String.format("\n|                                                     |\n|    Client: %-20s Ref: %-15s|", 
                            newOutput.generateAttendeeName(newItinerary), newOutput.generateItineraryRef(newItinerary));
        consoleOutput.append(clientRefRow);
        
        // Date row
        String dateRow = String.format("\n|    Date: %-43s|",
                newOutput.generateItineraryDate(newItinerary));
        consoleOutput.append(dateRow);
        
        // Activities/Attendees row
        String activitiesAttendeesRow = String.format("\n| Activities: %-15s Attendees: %-13s|",
                newOutput.generateTotalActivities(newItinerary), newOutput.generateNumberOfAttendees(newItinerary));
        consoleOutput.append(activitiesAttendeesRow);
        
        // Itinerary total cost row
        String itineraryCostRow = String.format("\n|                                                     |\n| Cost: £%-46s|", newOutput.generateTotalItineraryCost(newItinerary));
        consoleOutput.append(itineraryCostRow);
        
        // Cost breakdown heading row
        String costBr = String.format("\n|                                                     |\n|%24sCost breakdown%15s|", "", "");
        consoleOutput.append(costBr);
        
        // Itinerary Add-Ons row
        // THE GENERATE ADDON SUBTOTAL METHOD IS WRONG.
        String addOnsRow = String.format("\n| Itinerary Add-ons%-15s Sub-Total: £%.2f |", "", newOutput.generateAddOnSubtotal(newAddOn));
        consoleOutput.append(addOnsRow);
        
        // Specific Add-On row
        // Displays name of add-on, price of one add-on, quantity of the add-on (same as the number of attendees), total add-on price (price x quantity).
        String spAddOn = String.format("\n| - %s @ £%.2f x %d = £%.2f|", newOutput.generateAddOnName(newAddOn),
                newOutput.generateBaseAddOnCost(newAddOn),
                newOutput.generateAddOnsQuantity(addOnsList),
                newOutput.generateTotalAddOnCost(newAddOn, addOnsList));
        consoleOutput.append(spAddOn);
        
        // Add the remaining empty rows with the pipe borders
        for (int index = 0; index < 20; index++){
            consoleOutput.append("\n|                                                     |");
        }       
        
        // Add bottomborder.
        String bottomBorder = "\n+=====================================================+";
        consoleOutput.append(bottomBorder);
        // Outputs full itinerary summary from the stringbuilder.
        System.out.println(consoleOutput);
        
    }
    
    public static void viewItinerarySummary(Itinerary newItinerary, Output newOutput, AddOn newAddOn, List<String> addOnsList){
        
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to view a summary of the loaded/saved itinerary? (Y/N):");
        
        boolean flag = false;
        while(!flag){
            String viewItinerary = in.nextLine();
            if (viewItinerary.equalsIgnoreCase("Y")){
                phaseThreePart(newItinerary, newOutput, newAddOn, addOnsList);
                in.close();
                flag = true;
                break;
            }
            else{
                System.exit(0);
            }
        }
    }

}
