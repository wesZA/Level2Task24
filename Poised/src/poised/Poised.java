package poised; // package name


// imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;



public class Poised {

    public void view() {

    }

    public static void main(String[] args) {

        // variables
        
        // project specific variables
        int projNumber = 0;
        String projName;
        String buildingType;
        String projPhysAddress;
        int numERF = 0;
        double totalProjFee = 0;
        double totalPaidToDate = 0;
        LocalDate projDeadline = null;
        
        
        // constructor specific variables
        String contractorName;
        int contractorNumber = 0;
        String contractorEmail;
        String contractorPhysAddress;
        String connectionString = "src\\poised\\projects.txt"; // link to projects.txt file

        String projComplete; // project complete variable

        boolean numCheck = true;

        Project proj = new Project();

        int MainMenuInput = 0;
        int SubMenuInput = 0;
        

        Scanner projectsScanner = new Scanner(System.in); // scanner

        do { 
            // Main project menu
            System.out.println("Project editor: \nPress 1 to view projects. \nPress 2 to create a project. \nPress 3 to edit a project. \nPress 4 to exit.\n---");
            MainMenuInput = projectsScanner.nextInt();
            

            // View Projects
            if (MainMenuInput == 1) { // pressing 1 on the main menu view projects
                System.out.println("\nProject viewer: \nPress 1 to view all projects. \nPress 2 to view uncompleted projects. \nPress 3 to view projects past their due date. \nPress 4 to go back.\n---");
                SubMenuInput = projectsScanner.nextInt();
                System.out.println("");

                if (SubMenuInput == 1) { // pressing 1 under the sub menu uses filereader to read the text file
                    // View all projects
                    try {
                        File f = new File(connectionString);
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);

                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                        br.close();
                        fr.close();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    

                } else if (SubMenuInput == 2) { // pressing 2 under sub menu filters projects containing "Complete: no" and shows them
                    
                    int count = 0;
                    String[] pro = new String[10];
                    
                    try {
                        File f = new File(connectionString);
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);

                        String line = "";
                        while ((line = br.readLine()) != null) {
                            pro[count] = pro[count] + line + "\n";
                            if (line.contains("---")) {
                                count++;
                            }
                        }

                        if (count != 9) {
                            while (count != 9) {
                                count++;
                                pro[count] = "";
                            }
                        }

                        for (String data : pro) {
                            if (data.contains("Complete: No") || data.contains("Complete: no")) {
                                System.out.println(data);
                            }
                        }
                        br.close();
                        fr.close();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    
                } else if (SubMenuInput == 3) { // if 3 is pressed under sub menu the date used on the projects is compared to the current date and projects past their due date are shown
                    
                    try {
                        File f = new File(connectionString);
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);

                        LocalDate myCurrentDate = LocalDate.now(); // Create a date object of now
                        LocalDate textFileDate = null;

                        boolean projectPastDueDate = false;

                        String deadline = "";
                        String displayData = "";
                        String projectData = "";
                        String line = "";

                        try {
                            while ((line = br.readLine()) != null) {
                                if (line.contains("deadline:")) {
                                    deadline = line.substring(line.indexOf(':') + 2, line.length());
                                    textFileDate = LocalDate.parse(deadline);
                                    //Checks if deadline is before current date
                                    if (myCurrentDate.isAfter(textFileDate)) {
                                        projectPastDueDate = true;
                                    }
                                }
                                projectData = projectData + line + "\n";
                                if (line.contains("---") && projectPastDueDate == true) {
                                    displayData = displayData + projectData;
                                    projectData = "";
                                    projectPastDueDate = false;
                                } else if (line.contains("---") && projectPastDueDate == false) {
                                    projectData = "";
                                }
                            }//end of while

                            System.out.println(displayData);

                            br.close();
                            fr.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            // End of View Projects
            

            // Create New Projects
            
            // all project details are requested
            // exception handling is used where needed
            // try - catches are used where either int or double is needed and an error is given and question repeated to request a number or double
            // case sensitivity is ignored
            
            if (MainMenuInput == 2) {
                System.out.println("\nEnter the project's details");

                do {
                    System.out.println("Enter the project's number: ");
                    try {
                        projNumber = projectsScanner.nextInt();
                        numCheck = false;
                    } catch (Exception e) {
                        System.out.println("Error - please enter a number.\n");
                        projectsScanner.next();
                        numCheck = true;
                    }
                } while (numCheck);
                

                System.out.println("\nEnter the project's name: ");
                projName = projectsScanner.next();
                

                System.out.println("\nEnter the project's building type: ");
                buildingType = projectsScanner.next();
                

                System.out.println("\nEnter the project's physical address: ");
                projPhysAddress = projectsScanner.next();
                

                do {
                    System.out.println("\nEnter the project's ERF number: ");
                    try {
                        numERF = projectsScanner.nextInt();
                        numCheck = false;
                    } catch (Exception e) {
                        System.out.println("Error - please enter a number.");
                        projectsScanner.next();
                        numCheck = true;
                    }
                } while (numCheck);
                

                do {
                    System.out.println("\nEnter the project's total fee: R");
                    try {
                        totalProjFee = projectsScanner.nextDouble();
                        numCheck = false;
                    } catch (Exception e) {
                        System.out.println("Error - please enter a number.");
                        projectsScanner.next();
                        numCheck = true;
                    }
                } while (numCheck);
                

                do {
                    System.out.println("\nEnter the project's fee paid to date: R");
                    try {
                        totalPaidToDate = projectsScanner.nextDouble();
                        numCheck = false;
                    } catch (Exception e) {
                        System.out.println("Error - please enter a number.");
                        projectsScanner.next();
                        numCheck = true;
                    }
                } while (numCheck);
                

                System.out.println("\nEnter the project's deadline (YYYY-MM-DD): ");
                try {
                    projDeadline = LocalDate.parse(projectsScanner.next());
                } catch (Exception e) {
                    System.out.println("Error." + e);
                }
                
                
                // requesting contractor details for the project
                // exception handling is used where needed the same way as where project details are requested
                
                System.out.println("\n\nEnter the contractor's details");
                

                System.out.println("Enter the contractor's name: ");
                contractorName = projectsScanner.next();
                

                do {
                    System.out.println("\nEnter the contractor's contact number: ");
                    try {
                        contractorNumber = projectsScanner.nextInt();
                        numCheck = false;
                    } catch (Exception e) {
                        System.out.print("Error - please enter a number.");
                        projectsScanner.next();
                        numCheck = true;
                    }
                } while (numCheck);
                

                System.out.println("\nEnter the contractor's email address: ");
                contractorEmail = projectsScanner.next();
                

                System.out.println("\nEnter the contractor's physical address: ");
                contractorPhysAddress = projectsScanner.next();
                

                do {
                    System.out.println("\nIs the project complete? ");
                    projComplete = projectsScanner.next();
                    if (projComplete.equalsIgnoreCase("yes")) {
                        break;
                    } else {
                        if (projComplete.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    System.out.println("Error - please enter Yes or No.");
                } while (!projComplete.equalsIgnoreCase("yes") || !projComplete.equalsIgnoreCase("no"));
                
                
                // setting the project details used in their respective getter setters
                proj.setProjNumber(projNumber);
                proj.setProjName(projName);
                proj.setBuildingType(buildingType);
                proj.setProjPhysAddress(projPhysAddress);
                proj.setNumERF(numERF);
                proj.setTotalProjFee(totalProjFee);
                proj.setTotalPaidToDate(totalPaidToDate);
                proj.setProjDeadline(projDeadline);
                
                
                // setting the contractor details in their respective getter setters
                Contractor cntrctr = new Contractor();
                cntrctr.setContractorEmail(contractorEmail);
                cntrctr.setContractorName(contractorName);
                cntrctr.setContractorNumber(contractorNumber);
                cntrctr.setContractorPhysAddress(contractorPhysAddress);

                cntrctr.setComplete(projComplete); // project completion status setting

                System.out.println(proj.toString());
                System.out.println(cntrctr.toString());

                proj.SaveProject();
                cntrctr.SaveProject();
            }
            //End of Create New Project
            

            //Edit Project
            if (MainMenuInput == 3) { // if 3 is pressed under the main menu project editing is allowed

                // variables used in editing
                String allData = "";
                int index = 0;
                String numb = "";
                int num = 0;
                boolean valid = true;

                
                try {
                    
                    // file is read
                    File f = new File(connectionString); 
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.flush();
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String line;

                    //Gets all data into a single string
                    try {
                        while ((line = br.readLine()) != null) {
                            allData = allData + line + "\n";
                        }
                        br.close();
                        fr.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //end of big string                 

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                do {
                    System.out.println("\nEnter the project number you want to edit:");
                    numb = projectsScanner.next();

                    index = allData.indexOf("Project number: " + numb); // projects are filtered by number entered
                } while (index == -1);

                Contractor cntrctr = new Contractor();
                String userChoice = "";
                String userInput = "";

                
                // editing the project's details
                // do while statements are used to decide wether 'yes' or 'no' is entered as these are only able to be used to either edit or skip that part to be edited
                // try - catches are used within the do while statements where numbers or double are needed to only allow either numbers or doubles
                // case sensitivity is ignored
                
                // Edit Project Name
                do {
                    System.out.println("\nDo you want to edit the name of the project?");
                    userChoice = projectsScanner.next();
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the new project name:");
                        userChoice = projectsScanner.nextLine();
                        proj.setProjName(userChoice);
                        break;
                    } else {
                        if (userChoice.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    System.out.println("Error - please enter Yes or No.");
                } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                

                //Edit Building Type
                do {
                    System.out.println("\nDo you want to edit the building type?");
                    userChoice = projectsScanner.next();
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the building's new type:");
                        userChoice = projectsScanner.nextLine();
                        proj.setBuildingType(userChoice);
                        break;
                    } else {
                        if (userChoice.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    System.out.println("Error - please enter Yes or No.");
                } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                

                //Edit Buildings Physical Address
                do {
                    System.out.println("\nDo you want to edit the buildings physical address?");
                    userChoice = projectsScanner.next();
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the building's new address:");
                        userChoice = projectsScanner.nextLine();
                        proj.setProjPhysAddress(userChoice);
                        break;
                    } else {
                        if (userChoice.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    System.out.println("Error - please enter Yes or No.");
                } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                

                //Edit ERF Number               
                int ERFisDigit = 0;
                System.out.println("\nDo you want to edit the ERF number?");
                userChoice = projectsScanner.next();
                do {
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the new ERF number:");
                        userChoice = projectsScanner.next();
                        try {
                            ERFisDigit = Integer.parseInt(userChoice);
                            numCheck = true;
                            proj.setNumERF(ERFisDigit);
                            break;
                        } catch (NumberFormatException e) {
                            numCheck = false;
                            System.out.println("Error - please enter a number.");
                            userChoice = "yes";
                        }
                    } else if (userChoice.equalsIgnoreCase("no")) {
                        break;
                    } else {
                        numCheck = false;
                    }
                    System.out.println("Error - please enter Yes or No.");
                    userChoice = projectsScanner.next();
                } while ((!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no")) && numCheck == false);
                

                //Edit Total Project Fee                
                double TotalFeeIsDigit = 0;
                System.out.println("\nDo you want to edit the total project fee?");
                userChoice = projectsScanner.next();
                do {
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the new total project fee:");
                        userChoice = projectsScanner.next();
                        try {
                            TotalFeeIsDigit = Double.parseDouble(userChoice);
                            numCheck = true;
                            proj.setTotalProjFee(TotalFeeIsDigit);
                            break;
                        } catch (NumberFormatException e) {
                            numCheck = false;
                            System.out.println("Error - please enter a number.");
                            userChoice = "yes";
                        }
                    } else if (userChoice.equalsIgnoreCase("no")) {
                        break;
                    } else {
                        numCheck = false;
                    }
                    System.out.println("Error - please enter Yes or No.");
                    userChoice = projectsScanner.next();
                } while ((!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no")) && numCheck == false);
                

                //Edit Total Project Fee Paid                
                double TotalPaidIsDigit = 0;
                System.out.println("\nDo you want to edit the total paid project fee?");
                userChoice = projectsScanner.next();
                do {
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the new total paid project fee:");
                        userChoice = projectsScanner.next();
                        try {
                            TotalPaidIsDigit = Double.parseDouble(userChoice);
                            numCheck = true;
                            proj.setTotalPaidToDate(TotalPaidIsDigit);
                            break;
                        } catch (NumberFormatException e) {
                            numCheck = false;
                            System.out.println("Error - please enter a number.");
                            userChoice = "yes";
                        }
                    } else if (userChoice.equalsIgnoreCase("no")) {
                        break;
                    } else {
                        numCheck = false;
                    }
                    System.out.println("Error - please enter Yes or No.");
                    userChoice = projectsScanner.next();
                } while ((!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no")) && numCheck == false);
                

                //Edit Project Deadline
                do {
                    System.out.println("\nDo you want to edit the project deadline?");
                    userChoice = projectsScanner.next();
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the new project deadline: (YYYY-MM-DD)");

                        boolean dateFormattedCorrectly = false;
                        do {
                            try {
                                //Try catch for possible error in user entering
                                userChoice = projectsScanner.next();
                                proj.setProjDeadline(LocalDate.parse(userChoice));
                                break;
                            } catch (Exception e) {
                                System.out.println("Incorrect format, try again.");
                            }
                        } while (dateFormattedCorrectly == false);
                        break;
                    } else {
                        if (userChoice.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    System.out.println("Error - please enter Yes or No.");
                } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                

                //Edit Contractor Name
                do {
                    System.out.println("\nDo you want to edit the contractor name?");
                    userChoice = projectsScanner.next();
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the new contractor name:");
                        userChoice = projectsScanner.nextLine();
                        cntrctr.setContractorName(userChoice);
                        break;
                    } else {
                        if (userChoice.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    System.out.println("Error - please enter Yes or No.");
                } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                

                //Edit Contractor Contact Number                
                int userChoiceInt = 0;
                System.out.println("\nDo you want to edit the contractor contact number?");
                userChoice = projectsScanner.next();
                do {
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the contractor's new contact number:");
                        userChoice = projectsScanner.next();
                        try {
                            userChoiceInt = Integer.parseInt(userChoice);
                            numCheck = true;
                            cntrctr.setContractorNumber(userChoiceInt);
                            break;
                        } catch (NumberFormatException e) {
                            numCheck = false;
                            System.out.println("Error - please enter a number.");
                            userChoice = "yes";
                        }
                    } else if (userChoice.equalsIgnoreCase("no")) {
                        break;
                    } else {
                        numCheck = false;
                    }
                    System.out.println("Error - please enter Yes or No.");
                    userChoice = projectsScanner.next();
                } while ((!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no")) && numCheck == false);
                

                //Edit Contractor Email
                do {
                    System.out.println("\nDo you want to edit the contractor email?");
                    userChoice = projectsScanner.next();
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the contractor's new email:");
                        userChoice = projectsScanner.nextLine();
                        cntrctr.setContractorEmail(userChoice);
                        break;
                    } else {
                        if (userChoice.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    System.out.println("Error - please enter Yes or No.");
                } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                

                //Edit Contractor Address
                do {
                    System.out.println("\nDo you want to edit the contractors address?");
                    userChoice = projectsScanner.next();
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter the contractor's new address:");
                        userChoice = projectsScanner.nextLine();
                        cntrctr.setContractorPhysAddress(userChoice);
                        break;
                    } else {
                        if (userChoice.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    System.out.println("Error - please enter Yes or No.");
                } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                

                //Edit Completion Status
                do {
                    System.out.println("\nDo you want to edit the project's completion status?");
                    userChoice = projectsScanner.next();
                    if (userChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Has the project been completed?");
                        userChoice = projectsScanner.nextLine();
                        cntrctr.setComplete(userChoice);
                        break;
                    } else {
                        if (userChoice.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    System.out.println("Error - please enter Yes or No.");
                } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                

                // updating the .txt
                proj.editProject(Integer.parseInt(numb), cntrctr); //Pass this contractor object to the project.java class for use too         
            }
            

            //End of Edit Project          
            System.out.println("");
        } while (MainMenuInput != 4); // 4 entered in the main menu ends the file running
    }
}
