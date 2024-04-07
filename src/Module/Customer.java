package Module;

/**
 * @author Doan Phan Thuy Trang - s4027648
 */


import java.util.HashSet;
import java.util.Set;

public abstract class Customer
{
    private String id;
    private  String fullname;
    private InsuranceCard insuranceCard;
    private Set<Claim> claims;

    public Customer(String id, String fullname, InsuranceCard insuranceCard, Set<Claim> claims) {
        this.id = id;
        this.fullname = fullname;
        this.insuranceCard = insuranceCard;
        this.claims = claims != null ? claims : new HashSet<>();
    }

    public InsuranceCard getInsuranceCard()
    {
        return insuranceCard;
    }

    public String getId()
    {
        return id;
    }

    public String getFullname()
    {
        return fullname;
    }

    public void addClaim(Claim claim)
    {
        claims.add(claim);
    }

    public void  removeClaim(Claim claim) {claims.remove(claim);}

    public Set<Claim> getClaims()
    {
        return claims;
    }

}
