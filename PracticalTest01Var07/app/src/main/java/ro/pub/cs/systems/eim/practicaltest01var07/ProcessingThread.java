package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Random;

public class ProcessingThread  extends Thread {
    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    // media aritmetica
    private int random1;
    private int random2;
    private int random3;
    private int random4;


    public ProcessingThread(Context context) {
        this.context = context;

        random1 = new Random().nextInt(60);
        random2 = new Random().nextInt(60);
        random3 = new Random().nextInt(60);
        random4 = new Random().nextInt(60);

    }

    @Override
    public void run() {
        Log.d("emi", "Thread has started! PID:" + Process.myPid() + "TID: " + Process.myTid());
        while(isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("emi", "Thread has stopped");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);

        intent.putExtra("random1", random1);
        intent.putExtra("random2", random2);
        intent.putExtra("random3", random3);
        intent.putExtra("random4", random4);

        context.sendBroadcast(intent);

    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
