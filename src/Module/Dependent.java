package Module;

import java.util.ArrayList;
public class Dependent extends Customer
{
    private PolicyHolder policyHolder;
    public Dependent(String id, String fullName, InsuranceCard insuranceCard,
                     ArrayList<Claim> claims, PolicyHolder policyHolder)
    {
        super(id, fullName, insuranceCard,claims);
        this.policyHolder = policyHolder;
    }
}
