package pt.tecnico.grpc.client;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ResponseCollector {
    ArrayList<String> responses;

    public ResponseCollector() {
        responses = new ArrayList<String>();
    }

    public synchronized void addResponse(String response) {
        responses.add(response);
        notifyAll();
    }

    public synchronized String getResponses() {
        return responses.stream().collect(Collectors.joining("\n- "));
    }

    public synchronized void waitUntilAllReceived(int numResponses) throws InterruptedException {
        do {
            wait(0);
        }while (responses.size() < numResponses);
    }
}
