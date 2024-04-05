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

public class ClaimView {
    private ClaimController controller;
    private Scanner sc;

    public ClaimView(ClaimController controller) {
        this.controller = controller;
        this.sc = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Welcome, admin!");
        System.out.println("~~~ Claim Process Manager ~~~");
        System.out.println("Enter 1: Add Claim");
        System.out.println("Enter 2: Update Claim");
        System.out.println("Enter 3: Delete Claim");
        System.out.println("Enter 4: View a specified Claim");
        System.out.println("Enter 5: View All Claims");
        System.out.println("Enter 0: Exit");
    }

    public void createClaimForm()
    {
        System.out.println("~~ Creating a new claim ~~");

        System.out.println("Enter Claim ID: ");
        String id = sc.nextLine();

        System.out.println("Enter Claim Date (format dd/mm/yyyy): ");
        String claimDateStr = sc.nextLine();
        Utils.Date claimDate = new Utils.Date(claimDateStr);

        System.out.println("Enter Insured Person's ID: ");
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

        System.out.println("Enter Examination Date (format dd/mm/yyyy): ");
        String examDateStr = sc.nextLine();
        Utils.Date examDate = new Date(examDateStr);

        Set<String> documents = gatherDocuments(id, cardNumber);

        System.out.println("Enter Claim Amount: ");
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

        System.out.println("Enter the ID of the claim you want to update: ");
        String claimId = sc.nextLine();
        Claim existingClaim = controller.getClaim(claimId);

        if (existingClaim == null)
        {
            System.out.println("Claim with ID " + claimId + " does not exist.");
            return;
        }

        System.out.println("Enter Claim ID: ");
        String id = sc.nextLine();

        System.out.println("Enter Claim Date (format dd/mm/yyyy): ");
        String claimDateStr = sc.nextLine();
        Utils.Date claimDate = new Utils.Date(claimDateStr);

        System.out.println("Enter Insured Person's ID: ");
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

        System.out.println("Enter Examination Date (format dd/mm/yyyy): ");
        String examDateStr = sc.nextLine();
        Utils.Date examDate = new Date(examDateStr);

        Set<String> documents = gatherDocuments(id, cardNumber);

        System.out.println("Enter Claim Amount: ");
        double claimAmount = sc.nextDouble();

        sc.nextLine();

        System.out.println("Enter a Claim status (New,Processing,Done): ");
        String status_str = sc.nextLine();
        if (status_str == "New")
        {
            ClaimStatus status = ClaimStatus.New;
        }
        else if (status_str == "Processing")
        {
            ClaimStatus status = ClaimStatus.Processing;
        }else
        {
            ClaimStatus status = ClaimStatus.Done;
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
                existingClaim.getId(),
                claimDate,
                existingClaim.getInsuredPerson(),
                existingClaim.getCardNumber(),
                existingClaim.getExamDate(),
                existingClaim.getDocuments(),
                existingClaim.getClaimAmount(),
                existingClaim.getStatus(),
                existingClaim.getReceiverBankingInfo()
        );

        controller.updateClaim(updatedClaim);
        System.out.println("Claim updated successfully!");
    }

    private Set<String> gatherDocuments(String claimId, String cardNumber)
    {
        Set<String> documents = new HashSet<>();

        System.out.println("Enter the number of documents:");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.println("Enter Document Name for Document " + i + ":");
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