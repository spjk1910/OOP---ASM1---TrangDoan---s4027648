package Module;

import Utils.Date;

import java.util.ArrayList;

public class Claim
{
    private String id;
    private Date claimDate;
    private String insuredPerson;
    private String cardNumber;
    private Date examDate;
    private ArrayList<String> documents;
    private double claimAmount;
    private ClaimStatus status;
    private ReceiverBankingInfo receiverBankingInfo;


}
