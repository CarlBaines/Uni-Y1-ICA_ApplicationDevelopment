# Uni-Y1-ICA_ApplicationDevelopment

This repository contains the coursework completed for the Object-Oriented Programming module during my first year at Teesside University. The project involves designing and building a prototype Java application for Exciting Activities Ltd., a company that organises activities for individual and corporate clients.

https://github.com/user-attachments/assets/f7d93451-2805-4ed5-9f4d-f04151fd4ee9

---

## Project Overview

**Scenario:** Exciting Activities Ltd. requires a system to manage activities and attendees effectively, calculate costs, and maintain itineraries. This prototype demonstrates various development phases using object-oriented programming principles.

### Core Features
1. **Activity and Itinerary Management**
- Add diverse activities with specific details.
- Include itinerary add-ons (e.g., accommodation, meals).
- Calculate total costs considering base costs, add-ons, and discounts.

2. **Business Rules Implementation**
- Valid itinerary reference numbers.
- Attendee name validation.
- Cost formatting (stored in pence, displayed in decimal form).
- Insurance management for applicable activities.

3. **Phased Development**
- **Phase 1:** Class identification and implementation.
- **Phase 2:** Data persistence with file handling.
- **Phase 3:** Console-based user interface for itinerary creation.
- **Phase 4:** GUI for viewing and managing itineraries.

---

## Phases of Development

### Phase One - Class Identification and Implementation

**Minimum Details for Activities:**
- Code (e.g., SWM-01)
- Long, human-readable name
- Description of activity
- Date and time of activity
- Duration (in minutes)
- Mandatory insurance (boolean)
- Base cost (in pence)

**Demonstration:**
- Instantiation and usage of classes.
- Invocation of methods, demonstrating interaction between objects.

### Phase Two - Data Persistence

- Save and load itineraries to/from `itineraries.txt`.
- File format:
- Fields separated by tabs.
- Example: `Reference\tDate\tAttendee\tTotalCost\tTotalActivities\tActivitiesWithAddOns`
- Activity format: `Code:Addon1,Addon2` (e.g., SWM-01:INS,TRN).

### Phase Three - Console Interface

**Features:**
- Input validation:
- Correct format for itinerary and activity codes.
- Date validation (future dates only).
- Valid activity codes.
- Graceful exception handling for invalid input.
- Summary output:
- Activity count displayed as words (e.g., "One", "Two"). For values over five, numeric format is used.

### Phase Four - Swing-based GUI

**Features:**
- Display itinerary summary in a table.
- Lead attendee name.
- Total attendees.
- Total activities.
- Total cost.
- Double-click functionality:
- View detailed activity and add-on information for a selected itinerary.
