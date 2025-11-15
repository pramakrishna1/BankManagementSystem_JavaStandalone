package com.bank;

import com.bank.model.BankDetails;
import com.bank.services.BankServices;

import java.util.Scanner;

public class BankManagementSystemMain {

    public static void main(String[] args) {
        BankServices bankService = new BankServices();
        System.out.println("Welcome to Bank");
        Scanner sc = new Scanner(System.in);
        boolean trigger = true;
        while(trigger) {
            BankDetails details = new BankDetails();
            System.out.println("""
                    Enter 0 to show all accounts
                    Enter 1 to create new account
                    Enter 2 to check balance
                    Enter 3 to withdraw amount
                    Enter 4 to credit amount
                    Enter 5 to transfer money
                    Enter 6 to exit""");
            int choice = sc.nextInt();
            sc.nextLine();
            if(choice == 0) {
                System.out.println(bankService.showAccounts());
            }else if (choice == 1){
                System.out.println("Enter name:");
                String name = sc.nextLine();
                details.setName(name);
                System.out.println("Enter phone number:");
                String phoneNo = sc.nextLine();
                details.setPhoneNumber(phoneNo);
                System.out.println("Enter address:");
                String address = sc.nextLine();
                details.setAddress(address);
                System.out.println("Enter amount:");
                long amount = sc.nextLong();
                details.setAmount(amount);
                String result = bankService.createAccount(details);
                System.out.println(result);
            }else if(choice == 2){
                System.out.println("Enter account number");
                String accNo = sc.nextLine();
                System.out.println(bankService.checkBalance(accNo));
            }else if(choice == 3){
                System.out.println("Enter account number");
                String accNo = sc.nextLine();
                System.out.println("Enter amount you want to withdraw");
                long amount = sc.nextLong();
                System.out.println(bankService.withdraw(accNo, amount));
            }else if(choice == 4){
                System.out.println("Enter account number");
                String accNo = sc.nextLine();
                System.out.println("Enter amount you want to credit");
                long amount = sc.nextLong();
                System.out.println(bankService.credit(accNo, amount));
            }else if(choice == 5){
                System.out.println("Enter sender account number");
                String senderAccNo = sc.nextLine();
                System.out.println("Enter receiver account number");
                String receiverAccNo = sc.nextLine();
                System.out.println("Enter the amount to transfer");
                long amount = sc.nextLong();
                System.out.println(bankService.transferAmount(senderAccNo, receiverAccNo, amount));
            }
            else if(choice == 6){
                trigger = false;
            }
        }
        sc.close();
    }
}
