/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carlt.phaseone;

/**
 *
 * @author E4092399
 */
public class AddOn {
     //  Attributes
    private String addOnType;
    private final String addOnTitle;
    private String addOnCode;
    private double addOnCost;
    private double addOnCostInPounds;
    
    //  Default Constructor
    public AddOn() {
        this.addOnType = "Activity";
        this.addOnTitle = "Travel";
        this.addOnCost = 0;
        this.addOnCostInPounds = 0;
    }

    //  Constructor
    public AddOn(String addOnType, String addOnTitle, double addOnCost, double addOnCostInPounds) {
        this.addOnType = addOnType;
        this.addOnTitle = addOnTitle;
        this.addOnCode = addOnCode;
        this.addOnCost = addOnCost;
        this.addOnCostInPounds = addOnCostInPounds;
    }
    
    

    //  Methods
    //  Getters
    
    /**
     * getAddOnType() method
     * @return addOnType
     */
    
    public String getAddOnType() {
        return addOnType;
    }
    
    /**
     * getAddOnTitle() method
     * @return addOnTitle
     */
    
    public String getAddOnTitle(){
        return addOnTitle;
    }
    
    /**
     * getAddOnCode() method
     * @return addOnCode
     */
    
    public String getAddOnCode(){
        return addOnCode;
    }
    
    /**
     * getAddOnCost() method
     * @return addOnCost(stored in pence) divided by 100 to make cost equivalent to pounds.
     */
    
    public double getAddOnCost() {
        return addOnCost / 100;
    }
    
    /**
     * getAddOnCostInPounds() method
     * @return addOnCostInPounds
     */
    
    public double getAddOnCostInPounds(){
        return addOnCostInPounds;
    }

    /**
     * setAddOnCostAndType() method
     * The method sets the add-on cost and the add-on type dependant on the add-on that is selected.
     * 
     */
    
    public void setAddOnCostAndType(){
        
        String addOn = this.getAddOnTitle();
        
        switch(addOn){
            case "Travel" ->             {
                //  Costs are stored in pence
                //  5000p is £50.
                addOnCost = 5000.0;
                addOnCostInPounds = (addOnCost/100);
                addOnType = "Activity";
            }
            case "Insurance" ->             {
                //  150000.0p is £1500.
                addOnCost = 150000.0;
                addOnCostInPounds = (addOnCost/100);
                addOnType = "Activity";
            }
            case "Photography" ->             {
                addOnCost = 20000.0;
                addOnCostInPounds = (addOnCost/100);
                addOnType = "Activity";
            }
            case "Gear Rentals" ->             {
                addOnCost = 1000.0;
                addOnCostInPounds = (addOnCost/100);
                addOnType = "Activity";
            }
            case "Gift Bag" ->             {
                addOnCost = 2500.0;
                addOnCostInPounds = (addOnCost/100);
                addOnType = "Itinerary";
            }
            case "Accommodation" ->             {
                addOnCost = 17500.0;
                addOnCostInPounds = (addOnCost/100);
                addOnType = "Itinerary";
            }
            case "Coffee/Tea Breaks" ->             {
                addOnCost = 1500.0;
                addOnCostInPounds = (addOnCost/100);
                addOnType = "Itinerary";
            }
            case "Lunch" ->             {
                addOnCost = 2500.0;
                addOnCostInPounds = (addOnCost/100);
                addOnType = "Itinerary";
            }
            default ->             {
                addOnCost = 0;
                addOnCostInPounds = 0;
                addOnType = "NULL_TYPE";
            }    
        }
        
    }
    
   
    /**
     *  Override toString() method
     *  
     *  @return String of add-on object details.
     * 
     */
    @Override
    public String toString(){
        return "Add-On: {" + addOnTitle + "}" + ", Type: {" + addOnType + "}" + ", Code: {" + addOnCode + "}" + String.format(", Cost: {£%.2f" , addOnCostInPounds) + "}";
    }
}
