package poised; // package name


// needed imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;


class Project {

    // private variables used within this file
    private int projNumber;
    private String projName;
    private String buildingType;
    private String projPhysAddress;
    private int numERF;
    private double totalProjFee;
    private double totalPaidToDate;    
    private LocalDate projDeadline;
    private String connectionString = "src\\poised\\projects.txt";
    private int endOfProjectInfoBlock = -1;
    private String allData = "";
    private String newData = "";
    private int index = -1;
    private Contractor contractorObj;
    

    public Project() {
    }
    
    // all the project getters and setters used to be used when initially entering info and editing

    public int getProjNumber() {
        return projNumber;
    }

    public void setProjNumber(int projNumber) {
        this.projNumber = projNumber;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getProjPhysAddress() {
        return projPhysAddress;
    }

    public void setProjPhysAddress(String projPhysAddress) {
        this.projPhysAddress = projPhysAddress;
    }

    public int getNumERF() {
        return numERF;
    }

    public void setNumERF(int numERF) {
        this.numERF = numERF;
    }

    public double getTotalProjFee() {
        return totalProjFee;
    }

    public void setTotalProjFee(double totalProjFee) {
        this.totalProjFee = totalProjFee;
    }

    public double getTotalPaidToDate() {
        return totalPaidToDate;
    }

    public void setTotalPaidToDate(double totalPaidToDate) {
        this.totalPaidToDate = totalPaidToDate;
    }

    public LocalDate getProjDeadline() {
        return projDeadline;
    }

    public void setProjDeadline(LocalDate projDeadline) {
        this.projDeadline = projDeadline;
    }

    public int getEndOfProjectInfo() {
        return endOfProjectInfoBlock;
    }
    
    
    public void SaveProject() {
        
        // printing the entered information when requesting the project details
        String output = "Project Details:";
        output += "\nProject number: " + projNumber;
        output += "\nProject name: " + projName;
        output += "\nType of building being designed: " + buildingType;
        output += "\nPhysical address of project: " + projPhysAddress;
        output += "\nERF number: " + numERF;
        output += "\nTotal project fee: R" + totalProjFee;
        output += "\nTotal of fee paid to date: R" + totalPaidToDate;
        output += "\nProject deadline: " + projDeadline;
        output += "\n\n";

        
        // entered information is written to the file
        // '-----Project created-----' is printed if a project is succesfully created or an error is given otherwise
        try {
            FileWriter myWriter = new FileWriter(connectionString, true);
            myWriter.write(output);
            myWriter.close();
            System.out.println("\n-----Project created-----\n");
        } catch (IOException e) {
            System.out.println("\n-----An error occurred-----\n");
            e.printStackTrace();
        }

    }
    
    
    // editing the project
    public void editProject(int projNumber, Contractor cObj) {
        
        contractorObj = cObj;
        
        try { // reading the file
            File f = new File(connectionString);

            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.flush();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            

            // Gets all data into a single string
            try {
                while ((line = br.readLine()) != null) {
                    allData = allData + line + "\n";
                }
                br.close();
                fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // end of big string
            
            // filtering by the project number given when a project number to be edited is requested
            index = allData.indexOf("Project number: " + projNumber);            
            newData = allData.substring(0, index);
            newData = newData + "Project number: " + projNumber;
            

            compareOldtoNew();
            endOfProjectInfoBlock = allData.indexOf("---", index);
            newData = newData + allData.substring((endOfProjectInfoBlock), allData.length());
            
                        
            PrintWriter outFile = new PrintWriter(new FileWriter(connectionString, false));
            outFile.println(newData);

            outFile.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }        
    }
    
    
    // to edit the file - the selected part to be edited is found, that line is replaced with the newly entered data when selected
    // this is listed for all editable aspects
    
    private void compareOldtoNew() {
        
        //PROJECT EDITING
        if (projName != null) {
            newData = newData + "\nProject name: " + projName;
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Project name:", index), allData.indexOf("Type", index) - 1);
            newData = newData + "\n" + oldData;
        }
        

        if (buildingType != null) {
            newData = newData + "\nType of building being designed: " + buildingType;
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Type", index), allData.indexOf("Physical", index) - 1);
            newData = newData + "\n" + oldData;
        }
        

        if (projPhysAddress != null) {
            newData = newData + "\nPhysical address of project: " + projPhysAddress;
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Physical", index), allData.indexOf("ERF", index) - 1);
            newData = newData + "\n" + oldData;
        }
        

        if (numERF != 0) {
            newData = newData + "\nERF number: " + numERF;
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("ERF", index), allData.indexOf("Total project", index) - 1);
            newData = newData + "\n" + oldData;
        }
        

        if (totalProjFee != 0) {
            newData = newData + "\nTotal project fee: R" + totalProjFee;
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Total project", index), allData.indexOf("Total of fee", index) - 1);
            newData = newData + "\n" + oldData;
        }
        

        if (totalPaidToDate != 0) {
            newData = newData + "\nTotal of fee paid to date: R" + totalPaidToDate;
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Total of fee", index), allData.indexOf("Project deadline:", index) - 1);
            newData = newData + "\n" + oldData;
        }
        

        if (projDeadline != null) {
            newData = newData + "\nProject deadline: " + projDeadline;
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Project deadline:", index), allData.indexOf("Contractor Details", index) - 2);
            newData = newData + "\n" + oldData;
        }
        
        
        //CONTRACTOR EDITING
        newData = newData + "\n\n" + "Contractor Details: ";

        if (contractorObj.getContractorName() != null) {
            newData = newData + "\nContractor name: " + contractorObj.getContractorName();
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Contractor name:", index), allData.indexOf("Contractor contact", index) - 1);
            newData = newData + "\n" + oldData;
        }
        
        
        if (contractorObj.getContractorNumber() != 0) {
            newData = newData + "\nContractor contact number: " + contractorObj.getContractorNumber();
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Contractor contact", index), allData.indexOf("Contractor email", index) - 1);
            newData = newData + "\n" + oldData;
        }
        
        
        if (contractorObj.getContractorEmail() != null) {
            newData = newData + "\nContractor email address: " + contractorObj.getContractorEmail();
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Contractor email", index), allData.indexOf("Contractor physical", index) - 1);
            newData = newData + "\n" + oldData;
        }
        
        
        if (contractorObj.getContractorPhysAddress() != null) {
            newData = newData + "\nContractor physical address: " + contractorObj.getContractorPhysAddress();
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Contractor physical", index), allData.indexOf("Complete", index) - 2);
            newData = newData + "\n" + oldData;
        }
        
        
        // Completion editing
        if (contractorObj.getComplete() != null) {
            newData = newData + "\n\nComplete: " + contractorObj.getComplete();
        } else {
            //pull from original
            String oldData = allData.substring(allData.indexOf("Complete", index), allData.indexOf("---", index) - 1);
            newData = newData + "\n\n" + oldData;
        }
        
        newData = newData + "\n";
        
        System.out.println("\n-----Project updated-----");
    }
    
    
    // printing the newly updated project information
    public String toString() {
        String output = "\nProject number: " + projNumber;
        output += "\nProject name: " + projName;
        output += "\nType of building being designed: " + buildingType;
        output += "\nPhysical address of project: " + projPhysAddress;
        output += "\nERF number: " + numERF;
        output += "\nTotal project fee: R" + totalProjFee;
        output += "\nTotal of fee paid to date: R" + totalPaidToDate;
        output += "\nProject deadline: " + projDeadline;
        return output;
    }
    
    
    // error to be thrown
    void setNumERF(String userChoice) {
        throw new UnsupportedOperationException("Error"); 
    }
}


