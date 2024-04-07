    package Module;

    /**
     * @author Doan Phan Thuy Trang - s4027648
     */


    public class ReceiverBankingInfo
    {
        private String bankName;
        private String ownerName;
        private String number;

        public ReceiverBankingInfo(String bankName, String ownerName, String number)
        {
            this.bankName = bankName;
            this.ownerName = ownerName;
            this.number = number;
        }

        public String getBankName()
        {
            return bankName;
        }

        public String getOwnerName()
        {
            return ownerName;
        }

        public String getNumber()
        {
            return number;
        }
    }
