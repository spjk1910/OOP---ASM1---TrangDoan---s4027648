package Module;

import java.util.Set;

public abstract class Customer
{
    private String id;
    private  String fullname;
    private InsuranceCard insuranceCard;
    private Set<Claim> claims;

    public Customer(String id, String fullname, InsuranceCard insuranceCard,
                    Set<Claim> claims)
    {
        this.id = id;
        this.fullname = fullname;
        this.insuranceCard = insuranceCard;
        this.claims = claims;
    }

    public InsuranceCard getInsuranceCard()
    {
        return insuranceCard;
    }

}
