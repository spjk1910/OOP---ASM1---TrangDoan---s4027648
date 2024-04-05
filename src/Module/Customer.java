package Module;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Customer
{
    private String id;
    private  String fullname;
    private InsuranceCard insuranceCard;
    private ArrayList<Claim> claims;

    public Customer(String id, String fullname, InsuranceCard insuranceCard,
                    ArrayList<Claim> claims)
    {
        this.id = id;
        this.fullname = fullname;
        this.insuranceCard = insuranceCard;
        this.claims = claims;
    }
}
