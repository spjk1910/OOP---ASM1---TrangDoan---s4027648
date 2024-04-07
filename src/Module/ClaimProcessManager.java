package Module;

/**
 * @author Doan Phan Thuy Trang - s4027648
 */


import java.util.Set;
public interface ClaimProcessManager
{
    void add(Claim claim);
    void update(Claim claim);
    void delete(String id);
    Claim getOne(String id);
    Set<Claim> getAll();

    Set<Customer> getAll_C();
    Customer getCustomerById(String id);
}
