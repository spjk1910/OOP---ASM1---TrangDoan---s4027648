import Module.*;
import Utils.Date;
import View.ClaimView;
import Controller.ClaimController;

import java.util.Set;

public class Main {
    public static void main(String[] args)
    {
        ClaimProcessManagerImplement manager = new ClaimProcessManagerImplement();

        ClaimController controller = new ClaimController(manager, null);

        ClaimView view = new ClaimView(controller);

        controller.setView(view);

        Dependent dependent = new Dependent("c-8568742", "Jane Doe", new InsuranceCard("2568945874", "Jane Doe", "John Doe", new Date(2024, 12, 31)), null, null);ReceiverBankingInfo receiverBankingInfo = new ReceiverBankingInfo("Bank Name", "Owner Name", "123456789");
        PolicyHolder policyHolder = new PolicyHolder("c-1234567", "John Doe", new InsuranceCard("5698532589", "John Doe", "RMIT", new Date(2024, 12, 31)), null, Set.of(dependent));
        dependent.setPolicyHolder(policyHolder);
        manager.addCustomer(dependent);
        manager.addCustomer(policyHolder);
        controller.application();
    }
}

