package homeworks.homework11;

public class Account {
    private final Client client;
    private final int accountID;
    private final int accountBalance;

    Account(Client client, int accountBalance, int accountID) {
        this.client = client;
        this.accountID = accountID;
        this.accountBalance = accountBalance;
    }

    public Client getClient() {
        return client;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getAccountBalance() {
        return accountBalance;
    }
}
