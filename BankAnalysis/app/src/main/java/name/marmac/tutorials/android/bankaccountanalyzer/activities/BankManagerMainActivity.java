package name.marmac.tutorials.android.bankaccountanalyzer.activities;


import name.marmac.tutorials.android.contentproviders.bankanalysis.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import name.marmac.tutorials.android.bankaccountanalyzer.fragments.AccountStatementListFragment;
import name.marmac.tutorials.android.bankaccountanalyzer.fragments.BankAccountListFragment;

public class BankManagerMainActivity extends FragmentActivity 
			implements BankAccountListFragment.OnBankAccountSelectedListener {
	
	
	// Add a New Bank Account Request Code
	private static final int ADD_NEWBANKACCOUNT_REQUEST = 10;
	private static final int LIST_BANKS_REQUEST			= 20;
	
	
	private static final String TAG = "BankAnalysisApp";
	
	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bankmanager_main);
		// Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {
        	
        	if (savedInstanceState != null) {
                return;
            }
        	
        	// Create an instance of BankAccountListFragment
        	BankAccountListFragment firstFragment = new BankAccountListFragment();
        	// In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
            			.add(R.id.fragment_container, firstFragment).commit();
        }
	}

	/**
	 * 
	 */
	@Override
	public void onBankAccountSelected(long itemId) {
		
		AccountStatementListFragment statementListFragment = (AccountStatementListFragment)
				getSupportFragmentManager().findFragmentById(R.id.accountstatementlist_fragment);
		
		if (statementListFragment != null) {
			// If statementListFragment is available, we're in two-pane layout...

            // Call a method in the statementListFragment to update its content
			statementListFragment.updateStatementView(itemId);
			
		} else {
			//Otherwise create new AccountStatementListFragment
			AccountStatementListFragment newFragment = new AccountStatementListFragment();
			Bundle args = new Bundle();
            args.putLong(AccountStatementListFragment.ARG_POSITION, itemId);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
		}
	}

}
