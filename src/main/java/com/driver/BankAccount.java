package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
       if(sum>9*digits){
           throw new Exception("Account Number can not be generated");
       }
       int q=sum/9;
       int rem=sum%9;

       String accNo="";
       while(q-->0){
           accNo+="9";
       }

       if(rem>0){
           accNo+=rem;
       }
       int remDigit=digits-accNo.length();
       while(remDigit-->0){
           accNo+="0";
       }
        return accNo;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance+=amount;
        System.out.println(this.balance);
}

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(this.balance-amount<minBalance){
             throw new Exception("Insufficient Balance");
        }
        this.balance-=amount;
    }

}