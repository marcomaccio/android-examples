package name.marmac.tutorials.android.bankaccountanalyzer.fragments;

import name.marmac.tutorials.android.contentproviders.bankanalysis.R;
import name.marmac.tutorials.android.bankaccountanalyzer.adapters.AccountStatementCursorAdapter;
import name.marmac.tutorials.android.bankaccountanalyzer.contentproviders.BankAnalysisContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This fragment show the list of statements of the chosen bank account 
 * 
 * @author marcomaccio
 *
 */
public class AccountStatementListFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {
	
	private static final String TAG = "BankAnalysis";

	public final static String ARG_POSITION = "position";
    long mCurrentId = -1;

    AccountStatementCursorAdapter mCursorAdapter;
    
    /**
     * 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

    }
    
    /**
     * 
     */
    @Override
    public void onStart(){
    	super.onStart();
    	
    	if (getFragmentManager().findFragmentById(R.id.accountstatementlist_fragment)!= null) {
    		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    	}
    }
    
    /**
     * 
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    
    }
    
    /**
     * 
     */
    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

        // Access the ContentResolver
        // Instantiate the ContentResolver in order to operate the CRUD operations
        ContentResolver contentResolver = getActivity().getContentResolver();

        // CRUD Methods: Retrieve DEMO code
        //Create the cursor to retrieve the data from the ContentProvider
        Cursor statementCursor = contentResolver.query(BankAnalysisContract.Statements.CONTENT_URI,
                null, 		//projection
                null, 		//selection
                null, 		//selectionArgs
                null 		//sortOrder
        );

        mCursorAdapter = new AccountStatementCursorAdapter(getActivity(),
                R.layout.list_layout,
                statementCursor,
                0);

        setListAdapter(mCursorAdapter);

        // Start out with a progress indicator.
        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
	}
    
    /**
     * 
     */ 
    //@Override
    //public View onCreateView(LayoutInflater inflater, 
    //							ViewGroup container, 
    //							Bundle savedInstanceState) {
    	// We need to use a different list item layout for devices older than Honeycomb
        //int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
        //        android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
        
        
    	// If activity recreated (such as from screen rotate), restore
        // the previous bankAccount selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
    	//if (savedInstanceState != null) {
        //    mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        //}
    	// Inflate the layout for this fragment
        //return inflater.inflate(R.layout.accountstatementlist_fragment, container, false);
    //}

    /**
     *
     * @param itemId
     */
    public void updateStatementView(long itemId) {
    	//retrieve the TextView
    	TextView dateOperation = (TextView) getActivity().findViewById(R.id.operationDate);
    	//set the text in the textView
    	dateOperation.setText("01/03/2015");
    	
    	TextView dateValue = (TextView) getActivity().findViewById(R.id.valueDate);
    	//set the text in the textView
    	dateValue.setText("28/02/2015");
    	
    	mCurrentId = itemId;
    }
    
    public void onListItemClick(ListView l, View v, int position, long id) {
    	Log.i(TAG, "itemPosition: " + position + " itemId: " + id);
     
    	CharSequence text = "You have choosen element n." + position  
				+ " StatementId: " + id 
				+ " View: " + v.getId();
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(getActivity(), text, duration);
    	toast.show();
    }
    
    /**
     * 
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putLong(ARG_POSITION, mCurrentId);
    }

    /**
     *
     * @param id
     * @param args
     * @return
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        return new CursorLoader(getActivity(),
                BankAnalysisContract.Statements.CONTENT_URI,
                null, 		//projection
                null, 		//selection
                null, 		//selectionArgs
                null 		//sortOrder
                );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);

        // The list should now be shown.
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mCursorAdapter.swapCursor(null);
    }
}
