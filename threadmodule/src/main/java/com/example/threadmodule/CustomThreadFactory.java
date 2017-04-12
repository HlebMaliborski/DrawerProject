package com.example.threadmodule;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {
    private String threadName = null;
    private int priority = Thread.NORM_PRIORITY;
    private int counter = 0;

    public CustomThreadFactory setThreadName(String threadName) {
        if (threadName == null) {
            throw new NullPointerException();
        }

        this.threadName = threadName;
        return this;
    }

    public CustomThreadFactory setThreadPriority(int priority) {
        if (priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException(String.format("Thread priority must be less than %s", Thread.MAX_PRIORITY));
        }

        this.priority = priority;
        return this;
    }

    @Override
    public Thread newThread(@NonNull Runnable r) {

        Thread thread = new Thread(r);
        thread.setName(threadName + "-" + counter);
        thread.setPriority(priority);
        counter++;

        return thread;
    }
}