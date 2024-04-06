package View;

import Module.Claim;
import Module.Customer;
import Module.Dependent;
import Module.PolicyHolder;
import Module.ClaimStatus;
import Module.ReceiverBankingInfo;
import Controller.ClaimController;
import Utils.Date;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class ClaimView
{
    private ClaimController controller;
    private Scanner sc;

    public ClaimView(ClaimController controller)
    {
        this.controller = controller;
        this.sc = new Scanner(System.in);
    }

    public void displayMenu()
    {
        System.out.println("Welcome, admin!");
        System.out.println("~~~ Claim Process Manager ~~~");
        System.out.println("Enter 1: Add Claim");
        System.out.println("Enter 2: Update Claim");
        System.out.println("Enter 3: Delete Claim");
        System.out.println("Enter 4: Get a specified Claim");
        System.out.println("Enter 5: Get All Claims");
        System.out.println("Enter 0: Exit");
    }

    public void createClaimForm()
    {
        System.out.println("~~ Creating a new claim ~~");

        System.out.print("Enter Claim ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Claim Date (format dd/mm/yyyy): ");
        String claimDateStr = sc.nextLine();
        Utils.Date claimDate = new Utils.Date(claimDateStr);

        Customer insuredPerson;
        do
        {
            System.out.print("Enter Insured Person's ID: ");
            String insuredPersonId = sc.nextLine();
            insuredPerson = findCustomerById(insuredPersonId);
            if (insuredPerson == null) System.out.println("Insured Person is not exist!");
        } while (insuredPerson == null);

        String cardNumber = null;
        if (insuredPerson instanceof PolicyHolder)
        {
            PolicyHolder policyHolder = (PolicyHolder) insuredPerson;
            cardNumber = policyHolder.getInsuranceCard().getCardNumber();
        } else if (insuredPerson instanceof Dependent)
        {
            Dependent dependent = (Dependent) insuredPerson;
            cardNumber = dependent.getPolicyHolder().getInsuranceCard().getCardNumber();
        }

        System.out.print("Enter Examination Date (format dd/mm/yyyy): ");
        String examDateStr = sc.nextLine();
        Utils.Date examDate = new Date(examDateStr);

        Set<String> documents = gatherDocuments(id, cardNumber);

        System.out.print("Enter Claim Amount: ");
        double claimAmount = sc.nextDouble();

        sc.nextLine();

        ClaimStatus status = ClaimStatus.New;

        System.out.println("Enter Receiver Banking Info:");
        System.out.print("Bank Name: ");
        String bankName = sc.nextLine();
        System.out.print("Owner Name: ");
        String ownerName = sc.nextLine();
        System.out.print("Account Number: ");
        String accountNumber = sc.nextLine();

        ReceiverBankingInfo receiverBankingInfo = new ReceiverBankingInfo(bankName, ownerName, accountNumber);

        Claim newClaim = new Claim(
                id,
                claimDate,
                insuredPerson,
                cardNumber,
                examDate,
                documents,
                claimAmount,
                status,
                receiverBankingInfo
        );

        controller.addClaim(newClaim);
        System.out.println("Claim added successfully!");
    }

    public void updateClaim()
    {
        System.out.println("~~ Updating a claim ~~");

        System.out.print("Enter the ID of the claim you want to update: ");
        String claimId = sc.nextLine();
        Claim existingClaim = controller.getClaim(claimId);

        if (existingClaim == null)
        {
            System.out.println("Claim with ID " + claimId + " does not exist.");
            return;
        }

        controller.deleteClaim(existingClaim.getId());

        System.out.print("Enter Claim ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Claim Date (format dd/mm/yyyy): ");
        String claimDateStr = sc.nextLine();
        Utils.Date claimDate = new Utils.Date(claimDateStr);

        System.out.print("Enter Insured Person's ID: ");
        String insuredPersonId = sc.nextLine();
        Customer insuredPerson = findCustomerById(insuredPersonId);

        String cardNumber = null;
        if (insuredPerson instanceof PolicyHolder)
        {
            PolicyHolder policyHolder = (PolicyHolder) insuredPerson;
            cardNumber = policyHolder.getInsuranceCard().getCardNumber();
        } else if (insuredPerson instanceof Dependent)
        {
            Dependent dependent = (Dependent) insuredPerson;
            cardNumber = dependent.getPolicyHolder().getInsuranceCard().getCardNumber();
        }

        System.out.print("Enter Examination Date (format dd/mm/yyyy): ");
        String examDateStr = sc.nextLine();
        Utils.Date examDate = new Date(examDateStr);

        Set<String> documents = gatherDocuments(id, cardNumber);

        System.out.print("Enter Claim Amount: ");
        double claimAmount = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter a Claim status (New,Processing,Done): ");
        String status_str = sc.nextLine();
        ClaimStatus status;
        if (status_str == "New")
        {
            status = ClaimStatus.New;
        }
        else if (status_str == "Processing")
        {
            status = ClaimStatus.Processing;
        }else
        {
            status = ClaimStatus.Done;
        }

        System.out.println("Enter Receiver Banking Info:");
        System.out.print("Bank Name: ");
        String bankName = sc.nextLine();
        System.out.print("Owner Name: ");
        String ownerName = sc.nextLine();
        System.out.print("Account Number: ");
        String accountNumber = sc.nextLine();

        ReceiverBankingInfo receiverBankingInfo = new ReceiverBankingInfo(bankName, ownerName, accountNumber);

        Claim updatedClaim = new Claim
                (
                        id,
                        claimDate,
                        insuredPerson,
                        cardNumber,
                        examDate,
                        documents,
                        claimAmount,
                        status,
                        receiverBankingInfo
        );

        controller.updateClaim(updatedClaim);
        System.out.println("Claim updated successfully!");
    }

    public void deleteClaim()
    {
        System.out.println("~~ Deleting a claim ~~");

        System.out.print("Enter the ID of the claim you want to delete:");
        String claimId = sc.nextLine();

        Claim existingClaim = controller.getClaim(claimId);

        if (existingClaim == null)
        {
            System.out.println("Claim with ID " + claimId + " does not exist.");
            return;
        }

        System.out.println("Are you sure you want to delete the following claim?");

        printClaimDetails(existingClaim);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Enter 1: Yes");
        System.out.println("Enter 2: No");
        System.out.print("Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1)
        {
            controller.deleteClaim(claimId);
            System.out.println("Claim deleted successfully!");
        } else
        {
            System.out.println("You cancelled the Deletion!");
        }
    }

    public void getSpecifiedClaim()
    {
        System.out.println("~~ Getting a specified claim ~~");

        System.out.print("Enter the ID of the claim you want to get:");
        String claimId = sc.nextLine();

        Claim specifiedClaim = controller.getClaim(claimId);

        if (specifiedClaim == null)
        {
            System.out.println("Claim with ID " + claimId + " does not exist.");
        } else
        {
            printClaimDetails(specifiedClaim);
        }
    }

    public void getAllClaims()
    {
        System.out.println("~~ Getting All Claims ~~");

        Set<Claim> claims = controller.getAllClaims();

        if (claims.isEmpty())
        {
            System.out.println("No claims found.");
        } else
        {
            for (Claim claim : claims)
            {
                printClaimDetails(claim);
                System.out.println("------------------------");
            }
        }
    }
    private void printClaimDetails(Claim claim)
    {
        System.out.println("Claim ID: " + claim.getId());
        System.out.println("Claim Date: " + claim.getClaimDate().toString());
        System.out.println("Insured Person: " + claim.getInsuredPerson().getFullname());
        System.out.println("Card Number: " + claim.getCardNumber());

        System.out.println("List of Documents:");
        for (String document : claim.getDocuments())
        {
            System.out.println("- " + document);
        }

        System.out.println("Claim Amount: " + claim.getClaimAmount());
        System.out.println("Claim Status: " + claim.getStatus());

        System.out.println("Receiver Banking Info: ");
        ReceiverBankingInfo receiverInfo = claim.getReceiverBankingInfo();
        System.out.println("Bank Name: " + receiverInfo.getBankName());
        System.out.println("Owner Name: " + receiverInfo.getOwnerName());
        System.out.println("Account Number: " + receiverInfo.getNumber());

    }
    private Set<String> gatherDocuments(String claimId, String cardNumber)
    {
        Set<String> documents = new HashSet<>();

        System.out.print("Enter the number of documents:");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter Document Name for Document " + i + ":");
            String documentName = sc.nextLine();
            String documentFileName = claimId + "_" + cardNumber + "_" + documentName + ".pdf";
            documents.add(documentFileName);
        }

        return documents;
    }
    private Customer findCustomerById(String id)
    {
        return controller.getCustomerById(id);
    }


}