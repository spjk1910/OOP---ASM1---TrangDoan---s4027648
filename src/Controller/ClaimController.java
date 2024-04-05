package Controller;

import Module.Customer;
import Module.ClaimProcessManager;
import Module.Claim;

public class ClaimController
{
    private ClaimProcessManager manage;
    public ClaimController(ClaimProcessManager manage)
    {
        this.manage = manage;
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
}
