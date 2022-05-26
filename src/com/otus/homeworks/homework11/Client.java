package com.otus.homeworks.homework11;

public class Client {
    private String clientName;
    private int clientAge;
    private int clientID;

    public Client(String clientName, int clientAge, int clientID) {
        this.clientName = clientName;
        this.clientAge = clientAge;
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public int getClientAge() {
        return clientAge;
    }

    public int getClientID() {
        return clientID;
    }
}
