package name.marmac.tutorials.android.bankaccountanalyzer.services.datasync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * Created by marcomaccio on 04/06/2015.
 */
public class MarmacAuthenticatorService extends Service {

    private MarmacAuthenticator authenticator;

    @Override
    public void onCreate() {
        authenticator = new MarmacAuthenticator(this);
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
