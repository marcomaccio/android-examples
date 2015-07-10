package name.marmac.tutorials.android.bankaccountanalyzer.services.datasync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * Created by marcomaccio on 04/06/2015.
 */
public class AuthenticatorService extends Service {

    private Authenticator authenticator;

    @Override
    public void onCreate() {
        authenticator = new Authenticator(this);
    }

    /*
    * When the system binds to this Service to make the RPC call
    * return the authenticator’s IBinder.
    */
    @Override
    public IBinder onBind(Intent intent) {
        return authenticator.getIBinder();
    }
}
