package name.marmac.tutorials.android.bankaccountanalyzer.activities.auth;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;

import name.marmac.tutorials.android.contentproviders.bankanalysis.R;

/**
 * Created by marcomaccio on 05/06/2015.
 */
public class SignInActivity extends AccountAuthenticatorActivity {

    public final static String ARG_ACCOUNT_TYPE             = "ACCOUNT_TYPE";
    public final static String ARG_AUTH_TYPE                = "AUTH_TYPE";
    public final static String ARG_ACCOUNT_NAME             = "ACCOUNT_NAME";
    public final static String ARG_IS_ADDING_NEW_ACCOUNT    = "IS_ADDING_ACCOUNT";

    public static final String KEY_ERROR_MESSAGE            = "ERR_MSG";

    public final static String PARAM_USER_PASS              = "USER_PASS";

    private final int REQ_SIGNUP = 1;

    private final String TAG = this.getClass().getSimpleName();

    private AccountManager mAccountManager;
    private String mAuthTokenType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_login);
        mAccountManager = AccountManager.get(getBaseContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
