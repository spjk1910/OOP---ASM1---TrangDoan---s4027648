package Module;

import java.util.HashSet;
import java.util.Set;

public class PolicyHolder extends Customer
{
    private Set<Dependent> dependents;

    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard,
                        Set<Claim> claims, Set<Dependent> dependents)
    {
        super(id, fullName, insuranceCard,claims);
        this.dependents = new HashSet<>(dependents);
    }

    public Set<Dependent> getDependents() {
        return dependents;
    }
}
