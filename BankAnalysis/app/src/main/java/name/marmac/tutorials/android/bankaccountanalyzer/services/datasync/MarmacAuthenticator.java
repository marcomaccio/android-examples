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

import name.marmac.tutorials.android.bankaccountanalyzer.activities.auth.AuthenticatorActivity;

/**
 * Created by marcomaccio on 04/06/2015.
 */
public class MarmacAuthenticator extends AbstractAccountAuthenticator {

    private         String  TAG         = "BankAnalizerAuthenticator";
    private final   Context mContext;

    /**
     *
     * @param context
     */
    public MarmacAuthenticator(Context context) {
        super(context);
        this.mContext = context;
    }

    /**
     *
     * @param response
     * @param accountType
     * @return
     */
    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param response
     * @param accountType
     * @param authTokenType
     * @param requiredFeatures
     * @param options
     * @return
     * @throws NetworkErrorException
     */
    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response,
                             String     accountType,
                             String     authTokenType,
                             String[]   requiredFeatures,
                             Bundle     options) throws NetworkErrorException {

        Log.d("marmac", TAG + "> addAccount");

        final Intent intent = new Intent(mContext, AuthenticatorActivity.class);

        intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE,                    accountType);
        intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE,                       authTokenType);
        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT,           true);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,  response);

        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    /**
     *
     * @param response
     * @param account
     * @param options
     * @return
     * @throws NetworkErrorException
     */
    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    /**
     *
     * @param response
     * @param account
     * @param authTokenType
     * @param options
     * @return
     * @throws NetworkErrorException
     */
    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response,
                               Account account,
                               String authTokenType,
                               Bundle options) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param authTokenType
     * @return
     */
    @Override
    public String getAuthTokenLabel(String authTokenType) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param response
     * @param account
     * @param authTokenType
     * @param options
     * @return
     * @throws NetworkErrorException
     */
    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response,
                                    Account account,
                                    String authTokenType,
                                    Bundle options) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param response
     * @param account
     * @param features
     * @return
     * @throws NetworkErrorException
     */
    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }
}
