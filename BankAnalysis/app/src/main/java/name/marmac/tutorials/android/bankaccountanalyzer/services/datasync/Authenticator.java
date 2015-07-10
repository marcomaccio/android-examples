package name.marmac.tutorials.android.bankaccountanalyzer.services.datasync;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import name.marmac.tutorials.android.bankaccountanalyzer.activities.auth.SignInActivity;

/**
 * Created by marcomaccio on 04/06/2015.
 */
public class Authenticator extends AbstractAccountAuthenticator {

    private         String  TAG         = "BankAnalizerAuthenticator";
    private final   Context mContext;

    public Authenticator(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response,
                             String     accountType,
                             String     authTokenType,
                             String[]   requiredFeatures,
                             Bundle     options) throws NetworkErrorException {

        Log.d("marmac", TAG + "> addAccount");

        final Intent intent = new Intent(mContext, SignInActivity.class);

        intent.putExtra(SignInActivity.ARG_ACCOUNT_TYPE,                    accountType);
        intent.putExtra(SignInActivity.ARG_AUTH_TYPE,                       authTokenType);
        intent.putExtra(SignInActivity.ARG_IS_ADDING_NEW_ACCOUNT,           true);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,  response);

        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }
}
