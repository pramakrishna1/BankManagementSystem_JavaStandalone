package com.bank.services;

import com.bank.model.BankDetails;

import java.util.ArrayList;
import java.util.List;

public class BankServices {

    List<BankDetails> bankDetails = new ArrayList<>();

    private String createAccountNumber(){
        StringBuilder accountNo = new StringBuilder();
        for(int i = 0; i <10; i++) {
            int randomNumber = (int) (Math.random() * 10);
            accountNo.append(randomNumber);
        }
        return accountNo.toString();
    }
    public String createAccount(BankDetails details){
        String result = "";
        String phoneNumber = details.getPhoneNumber();
        if(phoneNumber.isBlank() && !(phoneNumber.matches("\\d{10}"))){
           result = "Please give valid Phone number";
        }else {
            String name = details.getName();
            String address = details.getAddress();
            if (!(name.isBlank() &&  address.isBlank())) {
                BankDetails newDetails = new BankDetails();
                newDetails.setName(name);
                newDetails.setPhoneNumber(phoneNumber);
                newDetails.setAddress(address);
                String accountNumber = createAccountNumber();
                newDetails.setAccNo(accountNumber);
                newDetails.setAmount(details.getAmount());
                bankDetails.add(newDetails);
                result = "Account created successfully %s".formatted(newDetails.toString());
            }else{
                result = "Please give Name and Address";
            }
        }
        return result;
    }

    public String checkBalance(String accountNumber){
        for (BankDetails details : bankDetails) {
            if (details.getAccNo().equals(accountNumber)){
                return "your current balance %d".formatted(details.getAmount());
            }
        }
        return "pls give correct account number";
    }

    public String withdraw(String accountNumber, long amount){
        for (BankDetails details : bankDetails) {
            if (details.getAccNo().equals(accountNumber)) {
                long accountAmount = details.getAmount();
                if(accountAmount < amount){
                    return "insufficient balance";
                }
                long balance = accountAmount - amount;
                details.setAmount(balance);
                return "withdrawn successfully, your current balance %d".formatted(balance);
            }
        }
        return "pls give correct account number";
    }

    public String credit(String accountNumber, long amount){
        for (BankDetails details : bankDetails) {
            if (details.getAccNo().equals(accountNumber)) {
                long balance = details.getAmount() + amount;
                details.setAmount(balance);
                return "credited successfully, your current balance %d".formatted(balance);
            }
        }
        return "pls give correct account number";
    }

    public String transferAmount(String senderAccountNo, String receiverAccountNo, long amount){
        BankDetails senderDetails = new BankDetails();
        BankDetails receiverDetails = new BankDetails();
        for (BankDetails details : bankDetails) {
            if (details.getAccNo().equals(senderAccountNo)) {
               senderDetails = details;
            }
            if (details.getAccNo().equals(receiverAccountNo)) {
                receiverDetails = details;
            }
        }
        if(senderDetails.getAccNo() == null) {
            return "Pls Enter valid sender account number";
        }
        if(receiverDetails.getAccNo() == null){
            return "Pls Enter valid receiver account number";
        }else{
            long senderAmount = senderDetails.getAmount();
            if(senderAmount < amount){
                return "sender current balance is low %d".formatted(senderAmount);
            }
            senderDetails.setAmount(senderAmount-amount);
            receiverDetails.setAmount(receiverDetails.getAmount()+amount);
        }
        return "amount transferred successfully";
    }

    public String showAccounts(){
        String result = "";
        for(int i = 0; i < bankDetails.size(); i++){
            result += bankDetails.get(i) + "\n";
        }
        return result;
    }
}
