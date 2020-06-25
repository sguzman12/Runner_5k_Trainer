package com.guzman.viewmodel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class GPS_Service extends Service
{
    public GPS_Service()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
