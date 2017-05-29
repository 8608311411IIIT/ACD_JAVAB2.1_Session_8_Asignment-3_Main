/*A simple program which has two object of class Customer and one object of class Account.both object withdraw from same account using thread.There
  is a disadvantage of using thread is that remaining money goes to -ve even after proper if and else condition.so we use synchronization in thread
*/
import java.util.*;
class Account{
      private int bal;
      public Account(int bal){//constructor of class Account
            this.bal=bal;
      }
   
         public boolean isSufficientBalance(int w){//checks whether account has sufficient balance or not
               if(bal>w)
                     return(true);
               else
                     return(false);

         }

             public void withdraw(int amt){//if balance is +ve.reduces the amount by money u withdraw and print message
                   bal=bal-amt;
                   System.out.println("withdrawal money is "+amt);
                   System.out.println("your current balance is "+bal);

             }

                 public void deposit(int amt){//deposit money by adding money to total amount in Account
                       bal=bal+amt;
                       System.out.println("deposit money is "+amt);
                       System.out.println("your current balance is "+bal);

             }

}

class Customer implements Runnable{//implements runnable interface
      private String name;
      private Account account;
      public Customer(Account account,String n){
            this.account=account;name=n;
      }

          public void run(){//override run method of interface runnable
                 Scanner sc=new Scanner(System.in);     
                  synchronized(account){ 
                      System.out.println(name+" 1.to withdraw  2. to deposit");
                      int choice=sc.nextInt();
                      if(choice==1){
                           System.out.println(name+" Enter amount to withdraw");
                           int amt=sc.nextInt();
                           if(account.isSufficientBalance(amt)){
                               System.out.println(name);
                               account.withdraw(amt);
                           }

                              else{
                                  System.out.println(" Insufficient balance");
                              }
                       }
                
                         else if(choice==2){
                               System.out.println(name+" Enter amount to deposit");
                               int amt=sc.nextInt();
                               System.out.println(name);
                               account.deposit(amt);
                        }
                 }
          }



}


public class Session8c{

      public static void main(String []args){
            Account a1=new Account(1000);//making object of class Account
            Customer c1=new Customer(a1,"Sanjeev");//making object of class Customer
            Customer c2=new Customer(a1,"Shivu");
            Thread t1=new Thread(c1);//making object of Thread class
            Thread t2=new Thread(c2);
            t1.start();
            t2.start();

      }
}



