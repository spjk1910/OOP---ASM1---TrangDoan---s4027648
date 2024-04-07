package Module;

/**
 * @author Doan Phan Thuy Trang - s4027648
 */


import java.util.Set;

public class Dependent extends Customer
{
    public Dependent(String id, String fullName, InsuranceCard insuranceCard,
                     Set<Claim> claims)
    {
        super(id, fullName, insuranceCard,claims);
    }
}
