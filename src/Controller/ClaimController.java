package Controller;

import Module.Customer;
import Module.ClaimProcessManager;
import Module.ClaimProcessManagerImplement;
import Module.Claim;
import Utils.LoadData;
import View.ClaimView;
import Utils.SaveData;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class ClaimController
{
    private ClaimProcessManager manage;
    private ClaimProcessManagerImplement manager;
    private ClaimView view;
    private SaveData save;
    private LoadData load;


    public ClaimController(ClaimProcessManager manage, ClaimView view,SaveData save,LoadData load, ClaimProcessManagerImplement manager)
    {
        this.manage = manage;
        this.manager = manager;
        this.view = view;
        this.save = save;
        this.load = load;
    }

    public void setView(ClaimView view)
    {
        this.view = view;
    }

    public void application() throws IOException {
        int choice;

        Scanner sc = new Scanner(System.in);
        Set<Customer> customers = load.loadCustomers("Customer.txt");
        for (Customer customer:customers)
        {
            manager.addCustomer(customer);
            Set<Claim> claims = customer.getClaims();
            for (Claim claim : claims)
            {
                claim.setInsuredPerson(customer);
                manage.add(claim);
            }
        }
        do
        {
            view.displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    view.createClaimForm();
                    break;
                case 2:
                    view.updateClaim();
                    break;
                case 3:
                    view.deleteClaim();
                    break;
                case 4:
                    view.getSpecifiedClaim();
                    break;
                case 5:
                    view.getAllClaims();
                    break;
                case 0:
                    save.saveCustomers(getAllCustomers(),"Customer.txt");
                    save.saveClaims(getAllClaims(),"Claim.txt");
                    save.saveInsuranceCard(getAllCustomers(),"InsuranceCard.txt");
                    save.saveReceiverBankingInfo(getAllClaims(),"ReceiverBankingInfo.txt");
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } while (choice != 0);
    }

    public void addClaim(Claim claim)
    {
        manage.add(claim);
    }
    public void updateClaim(Claim claim)
    {
        manage.update(claim);
    }
    public Claim getClaim(String id)
    {
        return manage.getOne(id);
    }
    public void deleteClaim(String claimId)
    {
        manage.delete(claimId);
    }
    public Customer getCustomerById(String id)
    {
        return manage.getCustomerById(id);
    }

    public Set<Claim> getAllClaims()
    {
        return manage.getAll();
    }

    public Set <Customer> getAllCustomers()
    {
        return manage.getAll_C();
    }

}
