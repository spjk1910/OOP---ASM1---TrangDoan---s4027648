package Controller;

import Module.Customer;
import Module.ClaimProcessManager;
import Module.Claim;

import java.util.HashMap;
import java.util.Map;
public class ClaimController
{
    private ClaimProcessManager manage;
    private Map<String, Customer> customers;

    public ClaimController(ClaimProcessManager manage)
    {
        this.manage = manage;
        this.customers = new HashMap<>();
    }

    public void addClaim(Claim claim)
    {
        manage.add(claim);
    }
    public void updateClaim(Claim claim)
    {
        manage.update(claim);
    }
    public void addCustomer(String id, Customer customer)
    {
        customers.put(id, customer);
    }
    public Customer getCustomerById(String id)
    {
        return customers.get(id);
    }
}
