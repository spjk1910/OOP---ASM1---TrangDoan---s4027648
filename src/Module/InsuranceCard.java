package Module;

/**
 * @author Doan Phan Thuy Trang - s4027648
 */


import Utils.Date;

public class InsuranceCard
{
    private String cardNumber;
    private String cardHolder;
    private String policyOwner;
    Date expirationDate;

    public InsuranceCard(String cardNumber, String cardHolder, String policyOwner, Date expirationDate)
    {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
