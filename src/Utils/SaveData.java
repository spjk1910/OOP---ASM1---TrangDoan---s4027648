package Utils;

/**
 * @author Doan Phan Thuy Trang - s4027648
 */


import Module.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class SaveData
{
    public void saveCustomers(Set<Customer> customers, String filePath) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            for (Customer customer : customers) {
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append(customer.getId()).append(",").append(customer.getFullname()).append(",")
                        .append((customer instanceof PolicyHolder ? "PolicyHolder" : "Dependent")).append(",")
                        .append(customer.getInsuranceCard().getCardNumber());

                if (customer instanceof PolicyHolder) {
                    PolicyHolder policyHolder = (PolicyHolder) customer;
                    StringBuilder dependentIds = new StringBuilder();
                    for (Dependent dependent : policyHolder.getDependents()) {
                        dependentIds.append(dependent.getId()).append(";");
                    }
                    lineBuilder.append(",").append(dependentIds.toString());
                } else {
                    lineBuilder.append(",");
                }

                if (!customer.getClaims().isEmpty()) {
                    StringBuilder claimIds = new StringBuilder();
                    for (Claim claim : customer.getClaims()) {
                        claimIds.append(claim.getId()).append(";");
                    }
                    lineBuilder.append(",").append(claimIds.toString());
                } else {
                    lineBuilder.append(",");
                }

                writer.write(lineBuilder.toString());
                writer.newLine();
            }
        }
    }

    public void saveClaims(Set<Claim> claims, String filePath) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            for (Claim claim : claims) {
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append(claim.getId()).append(",").append(claim.getClaimDate().toString()).append(",")
                        .append((claim.getInsuredPerson().getId())).append(",")
                        .append(claim.getCardNumber()).append(",").append((claim.getExamDate().toString())).append(",")
                        .append((claim.getClaimAmount())).append(",").append((claim.getStatus())).append(",")
                        .append((claim.getReceiverBankingInfo().getNumber())).append(",");
                if (claim.getDocuments() != null) {
                    for (String document : claim.getDocuments()) {
                        lineBuilder.append(document).append(";");
                    }
                }
                writer.write(lineBuilder.toString());
                writer.newLine();
            }
        }
    }

    public void saveInsuranceCard(Set<Customer> customers, String filePath) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
                for (Customer customer : customers) {
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append(customer.getInsuranceCard().getCardNumber()).append(",").append(customer.getInsuranceCard().getCardHolder()).append(",")
                        .append((customer.getInsuranceCard().getPolicyOwner())).append(",")
                        .append(customer.getInsuranceCard().getExpirationDate().toString());

                writer.write(lineBuilder.toString());
                writer.newLine();
            }
        }
    }

    public void saveReceiverBankingInfo(Set<Claim> claims, String filePath) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            for (Claim claim : claims) {
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append(claim.getReceiverBankingInfo().getBankName()).append(",")
                        .append(claim.getReceiverBankingInfo().getOwnerName()).append(",")
                        .append((claim.getReceiverBankingInfo().getNumber()));

                writer.write(lineBuilder.toString());
                writer.newLine();
            }
        }
    }
}