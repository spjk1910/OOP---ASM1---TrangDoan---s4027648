package Module;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        return new HashSet<>(claims.values());
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
}
