package homeworks.homework11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private HashMap<Account, Client> bankClientInfo = new HashMap();

    void addClientInfo(Account account, Client client) {
        bankClientInfo.put(account, client);
    }

    Client findClient(Account account) {
        return bankClientInfo.get(account);
    }

    List<Account> getAccounts(Client client) {
        List<Account> clientAccounts = new ArrayList<>();
        for (Map.Entry<Account, Client> userInfo : bankClientInfo.entrySet()) {
            if (userInfo.getValue().getClientID() == client.getClientID()) {
                clientAccounts.add(userInfo.getKey());
            }
        }
        return clientAccounts;
    }
}
