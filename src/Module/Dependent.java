package Module;

import java.util.Set;

public class Dependent extends Customer
{
    private PolicyHolder policyHolder;
    public Dependent(String id, String fullName, InsuranceCard insuranceCard,
                     Set<Claim> claims, PolicyHolder policyHolder)
    {
        super(id, fullName, insuranceCard,claims);
        this.policyHolder = policyHolder;
    }

    public PolicyHolder getPolicyHolder()
    {
        return policyHolder;
    }
}
