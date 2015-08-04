package name.marmac.tutorials.android.bankaccountanalyzer.services.datasync;

/**
 * Created by marcomaccio on 05/06/2015.
 */
public class AccountContract {

    /**
     * Account type id
     */
    public static final String ACCOUNT_TYPE = "name.marmac.tutorials.android.bankaccountanalyzer";

    /**
     * Account name
     */
    public static final String ACCOUNT_NAME = "MM-BankAnalyzer";

    /**
     * Auth token types
     */
    public static final String AUTHTOKEN_TYPE_READ_ONLY         = "Read only";
    public static final String AUTHTOKEN_TYPE_READ_ONLY_LABEL   = "Read only access to an MM BankAnalizer account";

    public static final String AUTHTOKEN_TYPE_FULL_ACCESS       = "Full access";
    public static final String AUTHTOKEN_TYPE_FULL_ACCESS_LABEL = "Full access to an MM BankAnalizer account";

    public static final ServerAuthenticateInterface sServerAuthenticate = new ServerAuthenticateImplParseCom();

}
