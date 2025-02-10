package pt.tecnico.grpc.client;

import io.grpc.stub.StreamObserver;
import pt.tecnico.grpc.HelloWorld.HelloResponse;

public class HelloObserver implements StreamObserver<HelloResponse> {
    ResponseCollector collector;

    public HelloObserver (ResponseCollector collector) {
        this.collector = collector;
    }

    @Override
    public void onNext(HelloResponse response) {
        collector.addResponse(response.getGreeting());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Received error: " + throwable);
    }

    @Override
    public void onCompleted() {
        System.out.println("Request completed");
    }
}
