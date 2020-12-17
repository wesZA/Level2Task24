package poised; // package name


// needed imports
import java.io.FileWriter;
import java.io.IOException;


class Contractor { 

    // private variables to be used in the file
    private String contractorName; 
    private int contractorNumber;
    private String contractorEmail;
    private String contractorPhysAddress;
    private String complete;
    String connectionString = "src\\poised\\projects.txt";
    

    public Contractor() {
    }

    // all the contractor getters and setters used to be used when initially entering info and editing
    
    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public int getContractorNumber() {
        return contractorNumber;
    }

    public void setContractorNumber(int contractorNumber) {
        this.contractorNumber = contractorNumber;
    }

    public String getContractorEmail() {
        return contractorEmail;
    }

    public void setContractorEmail(String contractorEmail) {
        this.contractorEmail = contractorEmail;
    }

    public String getContractorPhysAddress() {
        return contractorPhysAddress;
    }

    public void setContractorPhysAddress(String contractorPhysAddress) {
        this.contractorPhysAddress = contractorPhysAddress;
    }
    
    public String getComplete() {
        return complete;
    }
      
    public void setComplete(String complete) {
        this.complete = complete;
    }

       

    public void SaveProject() {
        
        // printing the entered information when requesting the contractor details
        String output = "";
        output += "Contactor Details: ";
        output += "\nContractor name: " + contractorName;
        output += "\nContractor contact number: " + contractorNumber;
        output += "\nContractor email address: " + contractorEmail;
        output += "\nContractor physical address: " + contractorPhysAddress;
        output += "\n\nComplete: " + complete;
        output += "\n---\n\n";
        
        
        // entered information is written to the file
        try {
            FileWriter myWriter = new FileWriter(connectionString, true);
            myWriter.write(output);
            myWriter.close();            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    

    // newly updated contractor information
    public String toString() { 

        String output = "\nContractor name: " + contractorName;
        output += "\nContractor contact number: " + contractorNumber;
        output += "\nContractor email address: " + contractorEmail;
        output += "\nContractor physical address: " + contractorPhysAddress;
        output += "\n\nComplete: " + complete;
        return output;
    }
}
