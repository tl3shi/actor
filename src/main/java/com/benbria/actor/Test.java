package com.benbria.actor;

public class Test {
    public static void main(String[] args) throws Exception {

        Actor<String> actor = Actor.createAndStart(new Actor.Behavior<String>(){

            public boolean onReceive(Actor<String> self, String msg) {
                System.out.println("Got: " + msg);
                return !msg.equals("stop");
            }

            public void onException(Actor<String> self, Exception e) {}
        });

        actor.send("hello");
        actor.send("world");
        Thread.sleep(1000);
        actor.send("stop");
    }
}
