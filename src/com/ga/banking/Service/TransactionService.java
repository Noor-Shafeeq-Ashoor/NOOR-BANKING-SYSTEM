//package com.ga.banking.Service;
//
//import com.ga.banking.Exception.AccountInactiveEx;
//import com.ga.banking.Exception.InvalidAmountEx;
//import com.ga.banking.models.Account;
//
//public class TransactionService {
//
//    public void desposit(Account account , double amount){
//        if (!account.isActive()){
//            throw new AccountInactiveEx("Account is Inactive");
//        }
//        if(amount <= 0){
//            throw new InvalidAmountEx("Desposit amount should be greater than ZERO");
//        }
//        account.increaseBalance(amount);
//    }
//}
