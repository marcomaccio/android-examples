package name.marmac.tutorials.android.bankaccountanalyzer.tests.activities;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;

import name.marmac.tutorials.android.bankaccountanalyzer.activities.BankManagerMainActivity;
import name.marmac.tutorials.android.bankaccountanalyzer.R;

/**
 * Created by marcomaccio on 19/07/2015.
 */
public class MainActivityUnitTest extends
        android.test.ActivityUnitTestCase<BankManagerMainActivity>  {


    private BankManagerMainActivity bankManagerMainActivity;

    /**
     *
     * @param activityClass
     */
    public MainActivityUnitTest(Class<BankManagerMainActivity> activityClass) {
        super(activityClass);
    }

    /**
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                                BankManagerMainActivity.class);
        startActivity(intent, null, null);
        bankManagerMainActivity = getActivity();
    }

    /**
     *
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     *
     */
    @SmallTest
    public void testLayoutExist() {
        //Verify
        assertNotNull(bankManagerMainActivity.findViewById(R.id.bankaccount_fragment));
    }
}
