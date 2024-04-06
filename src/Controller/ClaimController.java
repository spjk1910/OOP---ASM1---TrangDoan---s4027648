package Controller;

import Module.Customer;
import Module.ClaimProcessManager;
import Module.Claim;
import View.ClaimView;

import java.util.Scanner;
import java.util.Set;

public class ClaimController
{
    private ClaimProcessManager manage;
    private Customer model;
    private ClaimView view;

    public ClaimController(ClaimProcessManager manage, ClaimView view,Customer model)
    {
        this.manage = manage;
        this.model = model;
        this.view = view;
    }

    public void setView(ClaimView view)
    {
        this.view = view;
    }

    public void application()
    {
        int choice;
        Scanner sc = new Scanner(System.in);
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


}
