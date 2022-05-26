package com.otus.homeworks.homework11;

public class HomeWork {
    public static void main(String[] args) {
        //ради проверки создаём клиентов, аккаунты и добавляем их в банк
        Client client1 = new Client("Петров", 18, 180911);
        Client client2 = new Client("Иванов", 20, 200134);
        Client client3 = new Client("Сидоров", 17, 210012);
        Client client4 = new Client("Сидоров", 19, 230012);

        Account account1 = new Account(client1, 100, 1809111);
        Account account2 = new Account(client1, 200, 1809112);
        Account account3 = new Account(client4, 100, 2300121);
        Account account4 = new Account(client4, 300, 2300122);
        Account account5 = new Account(client2, 100, 2001341);
        Account account6 = new Account(client3, 100, 2100121);

        Bank bank = new Bank();
        bank.addClientInfo(account1, client1);
        bank.addClientInfo(account2, client1);
        bank.addClientInfo(account3, client4);
        bank.addClientInfo(account4, client4);
        bank.addClientInfo(account5, client2);
        bank.addClientInfo(account6, client3);

        //выводим ID аккаунта и имя клиента, которому они принадлежат
        for (Account account : bank.getAccounts(client4)) {
            System.out.println(bank.findClient(account).getClientName() + " " + account.getAccountID());
        }
    }
}
