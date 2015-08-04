package name.marmac.tutorials.android.bankaccountanalyzer.services.datasync;

/**
 * Created by marcomaccio on 05/06/2015.
 */
public interface ServerAuthenticateInterface {

    /**
     *
     * @param name
     * @param email
     * @param pass
     * @param authType
     * @return
     * @throws Exception
     */
    public String userSignUp(final String name, final String email, final String pass, String authType) throws Exception;

    /**
     *
     * @param user
     * @param pass
     * @param authType
     * @return
     * @throws Exception
     */
    public String userSignIn(final String user, final String pass, String authType) throws Exception;

}
