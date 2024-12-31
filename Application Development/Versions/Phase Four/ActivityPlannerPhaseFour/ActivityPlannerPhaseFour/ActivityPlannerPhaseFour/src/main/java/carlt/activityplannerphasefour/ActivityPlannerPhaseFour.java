/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package carlt.activityplannerphasefour;

// Imports
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ActivityPlannerPhaseFour class is designed to manage and plan various
 * activities through the creation of itineraries. The class initialises a list
 * of add-ons and activities. The main method allows the user to input itinerary
 * details, either by creating a new itinerary or by loading an existing one.
 * The class also calculates the costs of individual activities, add-ons and
 * itineraries. The class also contains a method that displays the details of
 * all activities in a console output.
 *
 * @author Carlt
 */
public class ActivityPlannerPhaseFour {

    public static void main(String[] args) {
        System.out.println("\n============ Activity Planner System - Phase One ============");

        // A list of type addon is initialised with addOns by calling the initialiseAddOns method.
        List<AddOn> activityAddOnsList = initialiseActivityAddOns();
        List<AddOn> itineraryAddOnsList = initialiseItineraryAddOns();

        // Similarly, a list of type activities is populated with activities by calling the initialiseActivities method.
        // The addOnsList is passed as a parameter so the addOnsList can be added to each instance of the activity class.
        List<Activity> activities = initialiseActivities(activityAddOnsList, itineraryAddOnsList);
        int finalTotalActivities = 0;

        outputActivities(activities);

        List<String> activityCodesList = new ArrayList<>();

        // Populates an activity codes list by looping through the activity list and calling the getCode getter method on each activity.
        for (Activity activity : activities) {
            activityCodesList.add(activity.getCode());
        }

        // Custom itinerary date used to instantiate an instance of the itinerary class.
        LocalDate customItineraryDate = LocalDate.of(2024, 12, 25);
        Itinerary newItinerary = new Itinerary(customItineraryDate);

        //  Method call that returns the number of activities by passing the activities list as a parameter.
        newItinerary.setNumberOfActivities(finalTotalActivities, activities);

        System.out.println(newItinerary.toString());
        System.out.println("\nActivity Costs:");

        double totalCost = 0;

        // Loops through the list of activities to get access to each index in the list.
        for (int index = 0; index < activities.size(); index++) {
            // Gets the activity stored at the current index and calls a method to set its base cost and activity title.
            activities.get(index).setActivityBaseActivityCost();
            String activityTitle = activities.get(index).getActivityTitle();
            double activityCost = activities.get(index).getBaseActivityCost();
            // The total activity cost is calculated by getting the total cost of one activity and multiplying it by the number of attendees in the itinerary.
            double totalActivityCost = activityCost * newItinerary.getNumOfAttendees();
            // Formatted output
            System.out.println(activityTitle + ": " + String.format("£%.2f", activityCost)
                    + " * " + newItinerary.getNumOfAttendees() + " Attendees = " + String.format("£%.2f", totalActivityCost));
            // Updates value of totalCost variable with each iteration.
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
        System.out.printf("\nThe total itinerary cost (with discount applied is): £%.2f\n", newItinerary.calculateItineraryTotalCost());

        System.out.println("\n============ Activity Planner System - Phase Two ============");

        // saveOrLoadItineraryFromFile method takes many parameters, including new instances of the activity and add-on class, which are required to facilitate the user
        // saving or loading an itinerary to file.
        // outputItineraryToFile method takes the instance of the itinerary class created with the addOnsList and the attributes of each instance of the activity class
        // using .get from the activities list.
        Output newOutput = new Output();
        outputItineraryToFile(newItinerary, activityCodesList, activityAddOnsList, itineraryAddOnsList, activities.get(0), activities.get(1), activities.get(2));
        saveOrLoadItineraryFromFile(new Activity(), activities, activityCodesList, activityAddOnsList, itineraryAddOnsList, newItinerary, newOutput, new AddOn());

    }

    /**
     *
     * initialiseAddOns() method
     *
     * @return an addOnsList populated with the two new instances of the addOn
     * class initialised within the method.
     *
     */
    public static List<AddOn> initialiseActivityAddOns() {
        //  Initialises a new ArrayList that takes AddOn objects 
        List<AddOn> activityAddOnsList = new ArrayList<>();

        //  Initialises two new instances of the AddOn class and gives them preset values.
        AddOn travelActivityAddOn = new AddOn("Activity", "ACT-02", "Travel", "TRV", 0.0, 0.0);

        //  Method calls on the addOn instances - to set the cost and type of each add-on.
        travelActivityAddOn.setAddOnCostAndType();
        travelActivityAddOn.setAddOnCode();

        //  Adds the attributes of each AddOn instance to the addOnsList.
        activityAddOnsList.add(travelActivityAddOn);

        return activityAddOnsList;
    }

    public static List<AddOn> initialiseItineraryAddOns() {
        List<AddOn> itineraryAddOnsList = new ArrayList<>();

        AddOn accommodationItineraryAddOn = new AddOn("Itinerary", "ACT-02", "Accommodation", "ACC", 0.0, 0.0);

        accommodationItineraryAddOn.setAddOnCostAndType();
        accommodationItineraryAddOn.setAddOnCode();

        itineraryAddOnsList.add(accommodationItineraryAddOn);

        return itineraryAddOnsList;
    }

    /**
     *
     * initialiseActivities method
     *
     * @param activityAddOnsList
     * @param itineraryAddOnsList
     * @return a list that is initialised to take each activity object.
     *
     */
    public static List<Activity> initialiseActivities(List<AddOn> activityAddOnsList, List<AddOn> itineraryAddOnsList) {

        //  Custom activity date and time is set here to be passed into the new instance of the activity class.
        LocalDate customActivityDate = LocalDate.of(2024, 12, 15);
        LocalTime customActivityTime = LocalTime.of(14, 30);

        //  Creates new instance of the activity class with default constructor values.
        Activity activityOne = new Activity();
        activityOne.setActivityCode();
        activityOne.validateActivityCode();
        activityOne.setActivityAddOnsList(new ArrayList<>());
        activityOne.setItineraryAddOnsList(new ArrayList<>());

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
                activityAddOnsList,
                itineraryAddOnsList
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
                new ArrayList<>(),
                itineraryAddOnsList
        );
        activityThree.setActivityCode();
        activityThree.validateActivityCode();
        activityThree.setActivityAddOnsList(new ArrayList<>());
        activityThree.setItineraryAddOnsList(new ArrayList<>());

        //  Returns a list that takes activity objects is initialised to take each activity object.
        return Arrays.asList(activityOne, activityTwo, activityThree);
    }

    /**
     *
     * outputActivities() method - Loops through the list of stored activities
     * and calls their toString methods, outputting the details of each activity
     * to console.
     *
     *
     * @param activities
     */
    public static void outputActivities(List<Activity> activities) {
        for (Activity activity : activities) {
            System.out.println(activity.toString());
        }
    }

    /**
     *
     * saveOrLoadItineraryFromFile() method - Allows the user to load, save or
     * view an itinerary from/to the 'itineraries.txt' file. Saving an itinerary
     * calls the inputItineraryDetails() method. Loading an itinerary calls the
     * loadExistingItinerarySummary() method. Viewing an itinerary calls the
     * phaseFourPart() method.
     *
     * @param newActivity
     * @param activities
     * @param activityCodesList
     * @param activityAddOnsList
     * @param itineraryAddOnsList
     * @param newItinerary
     * @param newOutput
     * @param newAddOn
     */
    public static void saveOrLoadItineraryFromFile(Activity newActivity, List<Activity> activities, List<String> activityCodesList, List<AddOn> activityAddOnsList, List<AddOn> itineraryAddOnsList, Itinerary newItinerary, Output newOutput, AddOn newAddOn) {
        try (Scanner in = new Scanner(System.in)) {
            // Creates new file object with the pathname of the file that stores itineraries.
            File file = new File("itineraries.txt");
            String choice;

            // loops block of code while user input is not equal to load or save (toLowerCase).
            do {
                // Menu choices
                System.out.println("Load or save an itinerary from/to: " + file + "? ");
                System.out.println("\nLoad itineraries from file - console output (Load)");
                System.out.println("Load itineraries from file - GUI output (View)");
                System.out.println("Create and write to itineraries - console output (Save)");
                System.out.println("-------------------------------------------------------");
                // Takes user input
                choice = in.nextLine();

                switch (choice.toLowerCase()) {
                    case "load" -> {
                        boolean found = false;

                        // Load logic
                        System.out.print("\nEnter the reference number of the itinerary you want to load: ");
                        String loadItineraryFromRef = in.nextLine();

                        // Reads the itineraries.txt file line by line to check for the reference number inputted by the user.
                        try (Scanner fileReader = new Scanner(file)) {
                            while (fileReader.hasNextLine()) {
                                String line = fileReader.nextLine();
                                if (line.toLowerCase().contains(loadItineraryFromRef.toLowerCase())) {
                                    System.out.println("An itinerary was found with the reference number: " + loadItineraryFromRef + "\n" + line);
                                    found = true;
                                    // Method call to output the itinerary details to console.
                                    loadExistingItinerarySummary(newActivity, activities, activityCodesList, activityAddOnsList, itineraryAddOnsList, newItinerary, newOutput, newAddOn);
                                    break;
                                }
                            }

                            if (!found) {
                                System.out.println("An itinerary was not found with the reference number: " + loadItineraryFromRef);
                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ActivityPlannerPhaseFour.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return;
                    }
                    case "view" -> {
                        // Method call to display itinerary summary output to console.
                        phaseFourPart();
                        return;
                    }
                    case "save" -> {
                        // Save logic from method call
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
     * method. The method also passes the relevant details as parameters to
     * display a console output for the user-inputted itinerary.
     *
     * @param choice
     */
    public static void inputItineraryDetails(String choice) {
        // Save an itinerary
        // Get a reference number, date, attendee name, total cost (in pence), total activities, and whether there are any add-ons.

        Output newOutput = new Output();

        // Create fresh lists for user input phase
        AddOn newAddOn = new AddOn();
        List<String> activityAddOnCodesList = AddOn.getActivityAddOnCodesList();
        List<String> itineraryAddOnCodesList = AddOn.getItineraryAddOnCodesList();

        Activity newActivity = new Activity();
        List<Activity> activities = new ArrayList<>();
        List<String> activityCodesList = newActivity.getActivityCodesList();

        File file = new File("itineraries.txt");

        // Takes user inputs and validates them using the validate methods from the validation class.
        System.out.print("\nSave Itinerary to: " + file + "\nEnter the itinerary date (YYYY-MM-DD): ");

        LocalDate finalItineraryDate = Validation.validateItineraryDate();
        // Initialises a new itinerary object that takes the user-inputted itinerary date.
        Itinerary newItinerary = new Itinerary(finalItineraryDate);

        System.out.println("Enter the itinerary reference number (1 letter, 4 Digits, 1 letter e.g. J1234A)");
        System.out.println("(The final letter of the reference number represents the quarters of the year):");
        System.out.println("\nA - Jan to Mar\nB - Apr to Jun\nC - Jul to Sep\nD - Oct to Dec");
        String finalRefNumber = Validation.validateItineraryReferenceNumber(newItinerary);

        // Sets the itinerary quarter by calling the setItineraryQuarter() method and passing the user-inputted reference number as a parameter.
        String finalQuarter = newItinerary.setItineraryQuarter(finalRefNumber);

        System.out.print("Enter the name of the itinerary's lead attendee (Initial <SPACE> Surname): ");
        String finalAttendeeNameInput = Validation.validateLeadAttendeeName(newItinerary);

        System.out.print("Enter the total number of attendees: ");
        int finalTotalNumberOfAttendees = Validation.validateTotalNumberOfAttendees();
        newItinerary.setNumOfAttendees(finalTotalNumberOfAttendees);

        System.out.print("Enter the total number of activities in the itinerary (maximum of 5): ");
        int finalTotalActivities = Validation.validateTotalNumOfActivities();

        System.out.print("Enter an activity code to add an activity to the itinerary: ");
        String finalActivityCodes = Validation.validateActivityCode(activityCodesList, newActivity, finalTotalActivities, activityAddOnCodesList, "");

        // Creates an array of activities selected throughout the user input phase by splitting the finalActivityCodes string by any group of whitespace characters.
        String[] selectedActivities = finalActivityCodes.split("\\s+");

        // A hashmap is instantiated to store activity add-ons.
        Map<String, String> activityAddOnsMap = new HashMap<>();

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

                // Create and add the selected activity add-ons
                if (!activityAddOns.equals("NONE")) {
                    String[] activityAddOnCodes = activityAddOns.split("\\s+");
                    for (String addOnCode : activityAddOnCodes) {
                        if (!addOnCode.equals("DONE")) {
                            AddOn activityAddOn = new AddOn("Activity", "ACT-02", getAddOnTitle(addOnCode), addOnCode, 0.0, 0.0);
                            activityAddOn.setAddOnCostAndType();
                            activity.addActivityAddOn(activityAddOn);
                        }
                    }
                }
            }
        }

        String finalItineraryAddOns = Validation.validateItineraryAddOnInput(itineraryAddOnCodesList, newAddOn);

        List<String> selectedItineraryAddOns = new ArrayList<>(Arrays.asList(finalItineraryAddOns));
        List<String> processedItineraryAddOns = new ArrayList<>();

        for (String itineraryAddOn : selectedItineraryAddOns) {
            String[] splitAddOns = itineraryAddOn.split("\\s+");
            processedItineraryAddOns.addAll(Arrays.asList(splitAddOns));
        }

        // Set the total number of activities in the itinerary
        newItinerary.setNumberOfActivities(finalTotalActivities, activities);

        String finalItineraryCost = totalItineraryCost(newItinerary, activities, processedItineraryAddOns);

        List<String> finalActivityAddOnsList = new ArrayList<>();
        for (Map.Entry<String, String> entry : activityAddOnsMap.entrySet()) {
            String activityCode = entry.getKey();
            String addOns = entry.getValue();
            if (!addOns.equals("NONE") && !addOns.isEmpty()) {
                finalActivityAddOnsList.add(activityCode + ": " + addOns);
            } else if (addOns.isEmpty()) {
                finalActivityAddOnsList.add(activityCode + ": No Add-Ons Applied!");
            }
        }

        String finalActivityAddOns = String.join(", ", finalActivityAddOnsList).trim();

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("The inputted itinerary details have successfully been written to file.");
        System.out.println("--------------------------------------------------------------------------");

        // Passes all the relevant user-inputted details into called methods - which help with writing the user-inputted itinerary to file and displaying a console output.
        saveItineraryToFile(newItinerary, finalActivityCodes, finalQuarter, finalRefNumber, finalAttendeeNameInput, finalTotalNumberOfAttendees, finalTotalActivities, finalActivityAddOns, finalItineraryAddOns, finalItineraryCost);
        viewInputtedItinerarySummary(newActivity, activities, selectedActivities, activityAddOnsMap, processedItineraryAddOns, newItinerary, newOutput, newAddOn);
    }

    /**
     *
     * saveItineraryToFile() method - The method writes the user-inputted
     * itinerary details from the method above to the 'itineraries.txt' file.
     *
     * @param newItinerary
     * @param finalActivityCodes
     * @param finalQuarter
     * @param finalRefNumber
     * @param finalAttendeeNameInput
     * @param finalTotalNumberOfAttendees
     * @param finalTotalActivities
     * @param finalActivityAddOns
     * @param finalItineraryAddOns
     * @param finalItineraryCost
     */
    public static void saveItineraryToFile(Itinerary newItinerary, String finalActivityCodes, String finalQuarter, String finalRefNumber, String finalAttendeeNameInput, int finalTotalNumberOfAttendees, int finalTotalActivities, String finalActivityAddOns, String finalItineraryAddOns, String finalItineraryCost) {

        // New file object with the pathname of the file which will store itineraries.
        File file = new File("itineraries.txt");
        // String used to add tab delimiter when adding a newEntry to the file.
        String t = "\t";

        List<String> activitiesAndAddOns = new ArrayList<>();

        activitiesAndAddOns.add(finalActivityAddOns);
        if (finalItineraryAddOns != null && !finalItineraryAddOns.isEmpty()) {
            activitiesAndAddOns.add("Itinerary Add-Ons: " + finalItineraryAddOns);
        }

        String newEntry = finalAttendeeNameInput + t + finalTotalNumberOfAttendees + t + finalTotalActivities
                + t + finalRefNumber + t + finalQuarter + t + finalItineraryCost + t + activitiesAndAddOns.toString();

        try {
            // Creates a set of itinerary entries.
            Set<String> fileEntries = new HashSet<>();
            // Checks to see if the itineraries.txt file exists.
            if (file.exists()) {
                // Reads the file line by line
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        // Each trimmed line is added to the collection of itinerary entries.
                        fileEntries.add(line.trim());
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ActivityPlannerPhaseFour.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ActivityPlannerPhaseFour.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Checks for duplicate entries.
            if (fileEntries.contains(newEntry.trim())) {
                System.out.println("Duplicate entry found. Itinerary was not written to the file.");
                System.out.println("-------------------------------------------------------------");
            } else {
                // If entry is not duplicate, it is appended to the file
                try (FileWriter newWriter = new FileWriter(file, true)) {
                    // Writes the newEntry seperated by the newline for the current operating system.
                    newWriter.write(newEntry + System.lineSeparator());
                    System.out.println("Itinerary details have successfully been written to file.");
                    System.out.println("-------------------------------------------------------------");
                } catch (IOException ex) {
                    Logger.getLogger(ActivityPlannerPhaseFour.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ActivityPlannerPhaseFour.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * outputItineraryToFile() method The method writes the itinerary details
     * that were created from the non-interactive side of the application to the
     * 'itineraries.txt' file.
     *
     * @param newItinerary
     * @param activityCodesList
     * @param activityAddOnsList
     * @param activityOne
     * @param itineraryAddOnsList
     * @param activityTwo
     * @param activityThree
     */
    public static void outputItineraryToFile(Itinerary newItinerary, List<String> activityCodesList, List<AddOn> activityAddOnsList, List<AddOn> itineraryAddOnsList, Activity activityOne, Activity activityTwo, Activity activityThree) {
        // Creates new instance of the output class which is used to access the methods for generating outputs.
        Output newOutput = new Output();
        // Creates new file object with the pathname of the itineraries file.
        File file = new File("itineraries.txt");

        // Initialises the itinerary attributes that will be written to file.
        // using method calls from the output class that generates formatted strings for output.
        String itineraryRef = newOutput.generateItineraryRef(newItinerary);
        String quarter = newOutput.generateItineraryQuarter(newItinerary, itineraryRef);
        String attendeeName = newOutput.generateAttendeeName(newItinerary);
        int totalNumOfAttendees = newOutput.generateNumberOfAttendees(newItinerary);
        String totalCost = newOutput.generateTotalItineraryCost(newItinerary);
        int totalActivities = newOutput.generateTotalActivities(newItinerary);

        String activityCodeOne = newOutput.generateActivitiesAddOnCodes(activityOne, activityAddOnsList, itineraryAddOnsList);
        String activityCodeTwo = newOutput.generateActivitiesAddOnCodes(activityTwo, activityAddOnsList, itineraryAddOnsList);
        String activityCodeThree = newOutput.generateActivitiesAddOnCodes(activityThree, activityAddOnsList, itineraryAddOnsList);

        String t = "\t";

        String newEntry = attendeeName + t + totalNumOfAttendees + t + totalActivities
                + t + itineraryRef + t + quarter + t + totalCost + t + activityCodeOne
                + " " + activityCodeTwo + " " + activityCodeThree;

        try {
            // Creates a set of itinerary entries.
            Set<String> fileEntries = new HashSet<>();
            // Checks to see if the itineraries.txt file exists.
            if (file.exists()) {
                // Reads the file line by line
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        // Each trimmed line is added to the collection of itinerary entries.
                        fileEntries.add(line.trim());
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ActivityPlannerPhaseFour.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ActivityPlannerPhaseFour.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Checks for duplicate entries.
            if (fileEntries.contains(newEntry.trim())) {
                System.out.println("Duplicate entry found. Itinerary was not added to the file.");
                System.out.println("-----------------------------------------------------------");
            } else {
                // If entry is not duplicate, it is appended to the file
                try (FileWriter newWriter = new FileWriter(file, true)) {
                    // Writes the newEntry seperated by the newline for the current operating system.
                    newWriter.write(newEntry + System.lineSeparator());
                    System.out.println("Itinerary successfully added to the file.");
                    System.out.println("-----------------------------------------");
                } catch (IOException ex) {
                    Logger.getLogger(ActivityPlannerPhaseFour.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ActivityPlannerPhaseFour.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * getAddOnTitle() method - returns the addOnTitle from its associated
     * addOnCode.
     *
     * @param addOnCode
     */
    private static String getAddOnTitle(String addOnCode) {
        return switch (addOnCode) {
            case "TRV" ->
                "Travel";
            case "INS" ->
                "Insurance";
            default ->
                "Unknown Add-on";
        };
    }

    /**
     *
     * loadExistingItinerarySummary() method - this method generates a
     * table-formatted console output for an itinerary summary that already
     * exists within the 'itineraries.txt' file.
     *
     *
     * @param newActivity
     * @param activities
     * @param activityCodesList
     * @param activityAddOnsList
     * @param itineraryAddOnsList
     * @param newOutput
     * @param newItinerary
     * @param newAddOn
     */
    public static void loadExistingItinerarySummary(Activity newActivity, List<Activity> activities, List<String> activityCodesList, List<AddOn> activityAddOnsList, List<AddOn> itineraryAddOnsList, Itinerary newItinerary, Output newOutput, AddOn newAddOn) {

        List<String> lines = new ArrayList<>();

        System.out.println("\n================= Activity Planner System - Phase Three ================");
        lines.add("");

        // Lead Attendee and Reference Number row.
        lines.add(String.format("    Client: %-20s Ref: %-15s", newOutput.generateAttendeeName(newItinerary), newOutput.generateItineraryRef(newItinerary)));

        // Date row
        String dateRow = String.format("    Date: %-43s",
                newOutput.generateItineraryDate(newItinerary));
        lines.add(dateRow);

        // Activities/Attendees row
        String activitiesAttendeesRow = String.format("Activities: %-15s Attendees: %-13s",
                newOutput.generateTotalActivitiesString(newItinerary), newOutput.generateNumOfAttendeesString(newItinerary));
        lines.add(activitiesAttendeesRow);

        lines.add("");

        // Itinerary total cost row
        String itineraryCostRow = String.format("Cost: %-45s", newOutput.generateTotalItineraryCost(newItinerary));
        lines.add(itineraryCostRow);

        lines.add("");

        // Cost breakdown heading row
        String costBr = String.format("%20sCost breakdown%19s", "", "");
        lines.add(costBr);
        lines.add("");

        // Itinerary Add-Ons row
        String itineraryAddOnsRow = String.format("Itinerary Add-ons%-15s Sub-Total: £%.2f", "", newOutput.generateItineraryAddOnSubtotal(newItinerary, itineraryAddOnsList));
        lines.add(itineraryAddOnsRow);

        // NEED TO GENERATE ITINERARY ADD-ON NAME, PRICE OF ITINERARY ADD-ON, THE NUMBER OF ATTENDEES, THE TOTAL ITINERARY ADD-ON COST
        String itineraryAddOn = String.format("- %s @ £%.2f x %d = £%.2f ", newOutput.generateItineraryAddOnName(itineraryAddOnsList),
                newOutput.generateBaseItineraryAddOnCost(itineraryAddOnsList),
                newOutput.generateNumberOfAttendees(newItinerary),
                newOutput.generateItineraryAddOnSubtotal(newItinerary, itineraryAddOnsList));
        lines.add(itineraryAddOn);

        // Activities row
        String activitiesRow = String.format("Activities%-22s Sub-Total: £%-6.2f", "",
                newOutput.generateActivitiesSubtotal(newActivity, activityCodesList, newItinerary));
        lines.add(activitiesRow);

        // Specific activity rows and add-on rows
        lines = newOutput.generateActivityAndAddOnRows(lines, activities, newItinerary, newAddOn, activityAddOnsList);

        lines.add("");

        // Discount row
        String discountRow = String.format(
                "%-3s%-29s Sub-Total: £%-6.2f",
                newOutput.generateDiscountNumber(newItinerary) + "%",
                "Discount",
                newOutput.generateDiscountSubTotal(newItinerary)
        );
        lines.add(discountRow);

        constructLines(lines);

    }

    /**
     *
     * viewInputtedItinerarySummary() method - this method generates a
     * table-formatted console output for an itinerary summary that exists
     * within the 'itineraries.txt' file.
     *
     *
     * @param newActivity
     * @param activities
     * @param selectedActivities
     * @param activityAddOnsMap
     * @param processedItineraryAddOns
     * @param newAddOn
     * @param newItinerary
     * @param newOutput
     */
    public static void viewInputtedItinerarySummary(Activity newActivity, List<Activity> activities, String[] selectedActivities, Map<String, String> activityAddOnsMap, List<String> processedItineraryAddOns, Itinerary newItinerary, Output newOutput, AddOn newAddOn) {

        List<String> lines = new ArrayList<>();

        System.out.println("\n============ Activity Planner System - Phase Three ============");
        lines.add("");

        // Lead Attendee and Reference Number row.
        String clientRefRow = String.format("    Client: %-20s Ref: %-15s",
                newOutput.generateAttendeeName(newItinerary), newOutput.generateItineraryRef(newItinerary));
        lines.add(clientRefRow);

        // Date row
        String dateRow = String.format("    Date: %-43s",
                newOutput.generateItineraryDate(newItinerary));
        lines.add(dateRow);

        // Activities/Attendees row
        String activitiesAttendeesRow = String.format(" Activities: %-15s Attendees: %-13s",
                newOutput.generateTotalActivitiesString(newItinerary), newOutput.generateNumOfAttendeesString(newItinerary));
        lines.add(activitiesAttendeesRow);

        String totalCost = totalItineraryCost(newItinerary, activities, processedItineraryAddOns);
        // Itinerary total cost row
        String itineraryCostRow = String.format(" Cost: %-44s", totalCost);
        lines.add(itineraryCostRow);

        // Cost breakdown heading row
        String costBr = String.format("%20sCost breakdown%19s", "", "");
        lines.add(costBr);

        // Itinerary Add-Ons row
        String itineraryAddOnsRow = String.format(" Itinerary Add-ons%-15s Sub-Total: £%.2f", "", newOutput.generateInputtedItineraryAddOnSubtotal(newItinerary, processedItineraryAddOns));
        lines.add(itineraryAddOnsRow);

        for (String itineraryAddOn : processedItineraryAddOns) {
            double addOnCost = switch (itineraryAddOn.trim()) {
                case "ACC" ->
                    175.00;
                case "CTB" ->
                    15.00;
                case "LUN" ->
                    4.00;
                default ->
                    0.00;
            };
            double addOnSubtotal = addOnCost * newItinerary.getNumOfAttendees();

            String addOnDetails = String.format(" - %s @ £%.2f x %d = £%.2f",
                    newAddOn.setAddOnTitleFromCode(itineraryAddOn.trim()), // Add-on name
                    addOnCost, // Cost per unit
                    newItinerary.getNumOfAttendees(), // Number of attendees
                    addOnSubtotal // Subtotal
            );
            lines.add(addOnDetails);
        }

        // Activities row
        String activitiesRow = String.format("\n  Activities%-22s Sub-Total: £%-6.2f", "",
                newOutput.generateInputtedActivitiesSubtotal(newActivity, newItinerary, selectedActivities, activityAddOnsMap));
        lines.add(activitiesRow);

        lines = newOutput.generateInputtedActivityAndAddOnRows(newActivity, lines, activityAddOnsMap, newItinerary, newAddOn);

        // Discount row
        String discountRow = String.format(
                "\n  %-3s%-29s Sub-Total: £%-6.2f",
                newOutput.generateDiscountNumber(newItinerary) + "%",
                "Discount",
                newOutput.generateInputtedDiscountSubTotal(totalCost, processedItineraryAddOns, newActivity, newItinerary, selectedActivities, activityAddOnsMap)
        );
        lines.add(discountRow);

        constructLines(lines);

        // Method call to ask user if they want to input another itinerary.
        inputAnotherItinerary();
    }

    public static void constructLines(List<String> lines) {

        int longest = -1;

        for (String s : lines) {
            if (longest == -1 || s.length() >= longest) {
                longest = s.length();
            }
        }

        System.out.print("\n+");

        for (int i = 0; i < longest + 2; i++) {
            System.out.print("=");
        }

        System.out.print("+\n");

        for (String s : lines) {
            System.out.print("| " + s);

            for (int i = 0; i < longest - s.length(); i++) {
                System.out.print(" ");
            }

            System.out.print(" |\n");
        }

        System.out.print("+");

        for (int i = 0; i < longest + 2; i++) {
            System.out.print("=");
        }

        System.out.print("+\n");

    }

    /**
     *
     * inputAnotherItinerary() method - this method asks the user if they want
     * to enter another itinerary. If the user wants to, the method calls upon
     * the inputItineraryDetails method to redirect the user to the input phase
     * once more. Otherwise, the method exits the program.
     *
     */
    public static void inputAnotherItinerary() {
        File newFile = new File("itineraries.txt");
        System.out.println("Saved inputted itinerary to: " + newFile);

        try (Scanner in = new Scanner(System.in)) {
            System.out.print("\nWould you like to input another itinerary? (Y/N): ");
            String inputAgain = in.nextLine();

            while (!inputAgain.equalsIgnoreCase("Y") && !inputAgain.equalsIgnoreCase("N")) {
                System.out.print("Error! A Y or N option has not been provided\nPlease try again: ");
                inputAgain = in.nextLine();
            }

            if (inputAgain.equalsIgnoreCase("Y")) {
                String choice = "Save";
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("                            Input new itinerary                           ");
                System.out.println("--------------------------------------------------------------------------");
                inputItineraryDetails(choice);
            }

            System.out.println("Thanks for using the Activity/Itinerary Planner System! Shutting Down...!");
        }
        System.exit(0);

    }

    /**
     *
     * phaseFourPart() method - this method creates a new object of the GUI
     * class which displays the Swing-based GUI of the itinerary management
     * system.
     *
     */
    public static void phaseFourPart() {
        System.out.println("\n============ Activity Planner System - Phase Four ============");
        System.out.println("Opening Activity Planner Management System...");
        GUI gui = new GUI();

    }

    /**
     *
     * totalItineraryCost() method - This method calculates the total cost based
     * on selected activities and add-ons for the itinerary. It is used for
     * calculating the total user-inputted itinerary cost.
     *
     * @param newItinerary
     * @param activities
     * @param processedItineraryAddOns
     * @return total itinerary cost
     */
    public static String totalItineraryCost(Itinerary newItinerary, List<Activity> activities, List<String> processedItineraryAddOns) {
        // Calculate total cost based on selected activities and add-ons
        double totalCost = 0.0;

        int numOfAttendees = newItinerary.getNumOfAttendees();

        // First calculate base activity costs
        for (Activity activity : activities) {
            switch (activity.getActivityTitle()) {
                // Updates value of totalCost variable dependant on the activities that were selected in the user input phase.
                case "Building a bridge from paper" ->
                    totalCost += 25.0 * numOfAttendees;
                case "Cookery Classes" ->
                    totalCost += 75.0 * numOfAttendees;
                case "SAS-Style Assault Courses" ->
                    totalCost += 150.0 * numOfAttendees;
                case "Obstacle Course" ->
                    totalCost += 90.0 * numOfAttendees;
                case "Cavern Ziplining" ->
                    totalCost += 175.0 * numOfAttendees;
            }

            // Add travel cost (£12.00) for each activity if selected
            List<AddOn> activityAddOns = activity.getActivityAddOnsList();
            if (activityAddOns != null) {
                for (AddOn activityAddOn : activityAddOns) {
                    switch (activityAddOn.getAddOnCode()){
                        case "TRV" ->
                            totalCost += 12.0 * numOfAttendees;
                        case "INS" ->
                            totalCost += 90.0 * numOfAttendees;
                        case "PHO" ->
                            totalCost += 150.0 * numOfAttendees;
                        case "GRT" ->
                            totalCost += 10.0 * numOfAttendees;
                        case "GBG" ->
                            totalCost += 5.0 * numOfAttendees;
                    }

                }
            }    
        }
            
        if (processedItineraryAddOns != null){
            for (String itineraryAddOn : processedItineraryAddOns){
                switch(itineraryAddOn){
                    case "ACC" ->
                        totalCost += 175.0 * numOfAttendees;
                    case "CTB" ->
                        totalCost += 15.0 * numOfAttendees;
                    case "LUN" ->
                        totalCost += 4.0 * numOfAttendees;
                }
            }
        }

        // Apply discount based on number of activities and attendees
        double discountPercentage = 0.0;
        int numActivities = activities.size();

        if (numActivities >= 3 && numActivities <= 5 && numOfAttendees < 10) {
            discountPercentage = 0.05;
            // 5% discount
        } else if (numActivities >= 3 && numActivities <= 5 && numOfAttendees >= 10 && numOfAttendees <= 20) {
            discountPercentage = 0.08;
            // 8% discount
        } else if (numActivities >= 3 && numActivities <= 5 && numOfAttendees > 20) {
            discountPercentage = 0.12;
            // 12% discount
        } else if (numActivities >= 6 && numOfAttendees < 10) {
            discountPercentage = 0.10;
            // 10% discount
        } else if (numActivities >= 6 && numOfAttendees >= 10 && numOfAttendees <= 20) {
            discountPercentage = 0.12;
            // 12% discount
        } else if (numActivities >= 6 && numOfAttendees > 20) {
            discountPercentage = 0.14;
            // 14% discount
        }

        // Calculates discount amount and subtracts it from the totalCost value.
        double discountAmount = totalCost * discountPercentage;
        totalCost -= discountAmount;

        return String.format("£%.2f", totalCost);
        
    }
}
