package carlt.activityplannerphasefour;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * GUI class is responsible for creating the GUI for the Activity/Itinerary
 * Planner System. It inherits the JPanel class and implements the MouseListener interface with its methods.
 *
 * @author Carlt
 */

public class GUI extends JPanel implements MouseListener {

    private static final long serialVersionUID = 1L;
    private static final String fileName = "itineraries.txt";

    private JFrame newFrame;
    private JTable itineraryList;
    private JLabel titleLabel;
    private DefaultTableModel tableModel;
    
    // Constructor
    public GUI() {
        // Creates the main JFrame and initialises its properties.
        newFrame = new JFrame("Activity Planner System");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(1000, 500);
        newFrame.setLayout(new BorderLayout());
        newFrame.setResizable(false);
        
        // Method call to read itinerary entries from the itineraries.txt file
        // Populates the JTable.
        setUpItineraries();
        
        // Creates a title JLabel and initialises its properties.
        titleLabel = new JLabel("Exciting Activities LTD - Activity Planner System");
        titleLabel.setBorder(new EmptyBorder(15, 15, 15, 15));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        
        // Adds a scrollPane to the JTable.
        JScrollPane scrollPane = new JScrollPane(itineraryList);
        newFrame.add(scrollPane, BorderLayout.CENTER);
        newFrame.add(titleLabel, BorderLayout.NORTH);
        
        // Sets frame visibility to true.
        newFrame.setVisible(true);
    }
    
    /**
     * setUpItineraries() method - This method reads through the itineraries.txt file for itinerary entries
     * and adds those entries to a list that holds an array of strings. The method splits the different itinerary 
     * attributes by searching for tab delimiters within the file. It also checks to see if the file is empty and displays an
     * appropriate message on the GUI. It also sets up the columnNames and calls the populateTable() method.
     *
     * 
     */
    private void setUpItineraries() {
        // ArrayList of an array of strings.
        List<String[]> itineraries = new ArrayList<>();
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            
            // Checks if file has contents
            while ((line = bufferedReader.readLine()) != null) {
                // Splits each itinerary attribute by tabs.
                String[] splitLine = line.split("\\t");

                if (splitLine.length >= 6) {
                    // Adds itinerary attributes to a new string array within the itineraries arraylist.
                    itineraries.add(new String[]{
                        splitLine[0], splitLine[1], splitLine[2], 
                        splitLine[3], splitLine[4], splitLine[5]
                    });
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Checks if file is empty
        if (itineraries.isEmpty()) {
            itineraries.add(new String[]{"THERE ARE NO ITINERARY ENTRIES STORED IN THE SYSTEM!"});
            String[] columnNames = {"ERROR!"};
            // MouseListener is set to False as there are no itinerary entries to double click on to view related add-ons and activities.
            populateTable(itineraries, columnNames, false);
        } else {
            String[] columnNames = {
                "Lead Attendee", "Total Attendees", "Total Activities", 
                "Ref Number", "Qtr", "Total Cost"
            };
            populateTable(itineraries, columnNames, true);
        }
    }
    
    /**
     * populateTable() method - This method populates the JTable with the tableData read from the itineraries.txt file
     * and the columnNames assigned from the previous method. It overrides the isCellEditable method within the DefaultTableModel
     * class to ensure that the user cannot edit the data stored in the cells. It also adds a mouse listener to the JTable dependant 
     * on the value of the addMouseListener boolean and calls the configureTabelAppearance() method.
     *
     * @param data
     * @param columnNames
     * @param addMouseListener
     */

    private void populateTable(List<String[]> data, String[] columnNames, boolean addMouseListener) {
        // Converts the list containing a string array to a 2D array.
        String[][] tableData = data.toArray(String[][]::new);
        tableModel = new DefaultTableModel(tableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        itineraryList = new JTable(tableModel);
        if (addMouseListener) {
            itineraryList.addMouseListener(this);
        }
        configureTableAppearance();
    }
    
     /**
     * configureTableAppearance() method - This method changes the JTable appearance such as its colour, font and its header.
     *
     * 
     */
    
    private void configureTableAppearance() {
        itineraryList.setShowGrid(true);
        itineraryList.setGridColor(Color.WHITE);
        itineraryList.setBackground(Color.BLACK);
        itineraryList.setForeground(Color.WHITE);
        itineraryList.setFont(new Font("Monospaced", Font.BOLD, 13));
        itineraryList.setFillsViewportHeight(true);
        
        // Creates a new DefaultTableCellRenderer object to render the JTable's header.
        // Configures the appearance of the JTable's header.
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBorder(null);
        headerRenderer.setBackground(Color.BLACK);
        headerRenderer.setForeground(Color.WHITE);

        itineraryList.getTableHeader().setDefaultRenderer(headerRenderer);
        itineraryList.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 14));
        itineraryList.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.WHITE));
    }
    
    /**
     * displayActivityCodesAndAddOnsClick method() - This method returns a string array of the activities and add-ons associated with
     * an itinerary entry that the user clicks on. It does this by reading the itineraries.txt file and splitting the itinerary attributes
     * by tabs. The method checks the reference number parameter against the reference numbers stored in the splitLine array. If the reference
     * number is found, it returns the sixth position in the splitLine array which is the index where the activity and add-on codes are stored for the itinerary.
     *
     * @param referenceNumber
     */

    private String[] displayActivityCodesAndAddOnsOnClick(String referenceNumber) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split("\\t");
                // Checks if the length of the splitLine array is larger or equal to seven
                // And if the attribute stored at position three (the reference number) is equal to the reference number parameter.
                if (splitLine.length >= 7 && splitLine[3].equals(referenceNumber)) {
                    // Returns position six of the splitLine array where the associated activity and add-ons for the itinerary is stored.
                    return new String[]{splitLine[6]};
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Mouse Listener methods
    
    /**
     * mouseClicked() method - This method is implemented from the mouseListener interface. However, it is an override method that checks if the user
     * double clicks on the JTable. It then gets the selected row of the double click and gets the reference number to pass into a method to display the
     * associated activity codes and add-ons of the itinerary the user clicked on.
     * 
     * @param e
     */
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int selectedRow = itineraryList.getSelectedRow();
            if (selectedRow != -1) {
                String referenceNumber = itineraryList.getValueAt(selectedRow, 3).toString();
                String[] activityCodesAndAddOns = displayActivityCodesAndAddOnsOnClick(referenceNumber);

                String message;
                if (activityCodesAndAddOns != null && activityCodesAndAddOns.length > 0){
                    message = activityCodesAndAddOns[0];
                } else{
                    message = "No activity codes and add-ons were found for the itinerary entry!";
                }
                JOptionPane.showMessageDialog(newFrame, message, "Activity Codes and Add-On Codes", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
