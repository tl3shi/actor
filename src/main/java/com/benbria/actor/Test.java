package com.benbria.actor;

public class Test {

    public static void main(String[] args) throws Exception {

        Actor<String> actor = Actor.createAndStart(new Actor.Behavior<String>(){

            public boolean onReceive(Actor<String> self, String msg) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Got: " + msg);
                return true;//!msg.equals("stop");
            }

            public void onException(Actor<String> self, Exception e) {
                System.out.println("actor has been stopped.");
            }
        });

        new Thread(){
            @Override
            public void run() {
                try {
                    actor.send("world xxx");
                } catch (Actor.DeadException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        actor.send("hello");
        actor.send("stop");
        actor.send("hello1");
        actor.send("stop11");
        actor.send("stop22");
        actor.send("stop23");
        actor.send("stop24");
    }
}
