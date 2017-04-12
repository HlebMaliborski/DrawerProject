package com.example.threadmodule;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class CustomAsyncTask<Params, Progress, Result> {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;

    private static final CustomThreadFactory threadFactory =
            new CustomThreadFactory().setThreadName("CustomThread");

    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);

    public static final ExecutorService THREAD_POOL_EXECUTOR;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
                sPoolWorkQueue, threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
    }

    public Handler handler = new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1:
                    onPostExecute(result);
                    break;
            }
        }
    };

    private Params params;
    private Progress progress;
    private Result result;

    public CustomAsyncTask()
    {

    }

    public abstract Result doInBackground(Params... params);

    public void execute(Params... params)
    {
        Future<Result> future = THREAD_POOL_EXECUTOR.submit(()->
        {
            return doInBackground(params);
        });

        try {
            result = future.get();
            if(result != null)
            {
                Message message = handler.obtainMessage(1);
                handler.sendMessage(message);
            }
            else
            {
                throw new NullPointerException();
            }
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {
        }
    }

    public abstract void onPostExecute(Result result);

    public Params getParams() {
        return params;
    }

    public Progress getProgress() {
        return progress;
    }

    public Result getResult() {
        return result;
    }
}
