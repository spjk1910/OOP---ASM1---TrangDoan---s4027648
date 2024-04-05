package Module;

import Utils.Date;

import java.util.Set;

public class Claim
{
    private String id;
    private Date claimDate;
    private String insuredPerson;
    private String cardNumber;
    private Date examDate;
    private Set<String> documents;
    private double claimAmount;
    private ClaimStatus status;
    private ReceiverBankingInfo receiverBankingInfo;

    public Claim(String id, Date claimDate, String insuredPerson, String cardNumber,
                 Date examDate, Set<String> documents, double claimAmount,
                 ClaimStatus status, ReceiverBankingInfo receiverBankingInfo)
    {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }
}
