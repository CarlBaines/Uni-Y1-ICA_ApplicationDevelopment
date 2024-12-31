package carlt.activityplannerphasefour;

// Imports
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
 * Planner System. It inherits the JPanel class.
 *
 * @author Carlt
 */
public class GUI extends JPanel implements MouseListener {

    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "itineraries.txt";

    // Properties
    private JFrame newFrame;
    private JTable itineraryList;
    private JLabel titleLabel;
    private DefaultTableModel tableModel;

    // Constructor
    public GUI() {
        // Initialize main JFrame
        newFrame = new JFrame("Activity Planner System");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(1000, 500);
        newFrame.setLayout(new BorderLayout());
        newFrame.setResizable(false);

        // Set up the itineraries table
        setUpItineraries();

        // Set up the title label
        titleLabel = new JLabel("Exciting Activities LTD - Activity Planner System");
        titleLabel.setBorder(new EmptyBorder(15, 15, 15, 15));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 16));

        // Add components to the frame
        JScrollPane scrollPane = new JScrollPane(itineraryList);
        newFrame.add(scrollPane, BorderLayout.CENTER);
        newFrame.add(titleLabel, BorderLayout.NORTH);

        // Make the frame visible
        newFrame.setVisible(true);
    }

    // Method to populate the JTable
    private void setUpItineraries() {
        List<String[]> itineraries = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split("\\t");

                if (splitLine.length >= 6) {
                    itineraries.add(new String[]{
                        splitLine[0], splitLine[1], splitLine[2], 
                        splitLine[3], splitLine[4], splitLine[5]
                    });
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Populate table based on retrieved data
        if (itineraries.isEmpty()) {
            itineraries.add(new String[]{"THERE ARE NO ITINERARY ENTRIES STORED IN THE SYSTEM!"});
            String[] columnNames = {"ERROR!"};
            populateTable(itineraries, columnNames, false);
        } else {
            String[] columnNames = {
                "Lead Attendee", "Total Attendees", "Total Activities", 
                "Ref Number", "Qtr", "Total Cost"
            };
            populateTable(itineraries, columnNames, true);
        }
    }

    // Method to populate the table with data and configure appearance
    private void populateTable(List<String[]> data, String[] columnNames, boolean addMouseListener) {
        String[][] tableData = data.toArray(String[][]::new);
        tableModel = new DefaultTableModel(tableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table non-editable
            }
        };

        itineraryList = new JTable(tableModel);
        if (addMouseListener) {
            itineraryList.addMouseListener(this);
        }
        configureTableAppearance();
    }

    // Configure table appearance
    private void configureTableAppearance() {
        itineraryList.setShowGrid(true);
        itineraryList.setGridColor(Color.WHITE);
        itineraryList.setBackground(Color.BLACK);
        itineraryList.setForeground(Color.WHITE);
        itineraryList.setFont(new Font("Monospaced", Font.BOLD, 13));
        itineraryList.setFillsViewportHeight(true);

        // Custom header appearance
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBorder(null);
        headerRenderer.setBackground(Color.BLACK);
        headerRenderer.setForeground(Color.WHITE);

        itineraryList.getTableHeader().setDefaultRenderer(headerRenderer);
        itineraryList.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 14));
        itineraryList.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.WHITE));
    }

    // Display activity codes and add-ons for a selected itinerary
    private String[] displayActivityCodesAndAddOnsOnClick(String referenceNumber) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split("\\t");

                if (splitLine.length >= 7 && splitLine[3].equals(referenceNumber)) {
                    return new String[]{splitLine[6]};
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int selectedRow = itineraryList.getSelectedRow();
            if (selectedRow != -1) {
                String referenceNumber = itineraryList.getValueAt(selectedRow, 3).toString();
                String[] activityCodesAndAddOns = displayActivityCodesAndAddOnsOnClick(referenceNumber);

                String message = (activityCodesAndAddOns != null)
                        ? activityCodesAndAddOns[0]
                        : "No activity codes and add-ons were found for the itinerary entry!";
                JOptionPane.showMessageDialog(null, message);
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
