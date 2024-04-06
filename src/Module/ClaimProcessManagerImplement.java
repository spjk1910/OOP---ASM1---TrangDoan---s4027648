package Module;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClaimProcessManagerImplement implements ClaimProcessManager
{
    private Map<String, Claim> claims;
    private Set<Customer> customers;

    public ClaimProcessManagerImplement()
    {
        this.claims = new HashMap<>();
        this.customers = new HashSet<>();
    }
    @Override
    public void add(Claim claim)
    {
        claims.put(claim.getId(), claim);
    }
    @Override
    public void update(Claim claim)
    {
        claims.put(claim.getId(), claim);
    }
    @Override
    public void delete(String id)
    {
        claims.remove(id);
    }
    @Override
    public Claim getOne(String id)
    {
        return claims.get(id);
    }

    @Override
    public Set<Claim> getAll()
    {
        Set<Claim> claimSet = new HashSet<>(claims.values());
        List<Claim> claimList = new ArrayList<>(claimSet);
        Collections.sort(claimList, Comparator.comparing(Claim::getId).reversed());
        Set<Claim> sortedClaims = new HashSet<Claim>(claimList);

        return sortedClaims;
    }
    @Override
    public Customer getCustomerById(String id)
    {
        for (Customer customer : customers)
        {
            if (customer.getId().equals(id))
            {
                return customer;
            }
        }
        return null;
    }
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
}
