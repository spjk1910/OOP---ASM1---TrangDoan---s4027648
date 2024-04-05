package Module;

import java.util.ArrayList;

public class PolicyHolder extends Customer
{
    private ArrayList<Dependent> dependents;
    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard,
                        ArrayList<Claim> claims, ArrayList<Dependent> dependents)
    {
        super(id, fullName, insuranceCard,claims);
        this.dependents = dependents;
    }
}
