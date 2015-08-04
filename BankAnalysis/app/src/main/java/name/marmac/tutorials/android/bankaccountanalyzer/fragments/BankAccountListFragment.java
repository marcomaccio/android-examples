package name.marmac.tutorials.android.bankaccountanalyzer.fragments;

import name.marmac.tutorials.android.bankaccountanalyzer.R;
import name.marmac.tutorials.android.bankaccountanalyzer.adapters.BankAccountCursorAdapter;
import name.marmac.tutorials.android.bankaccountanalyzer.contentproviders.BankAnalysisContract;
import android.support.v4.app.ListFragment;
import android.app.Activity;
//import android.app.ListFragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * This fragment list all the accounts of a given Banks
 * @author marcomaccio
 *
 */
public class BankAccountListFragment extends ListFragment {
	
	private static final String TAG = "BankAnalysis";
	
	OnBankAccountSelectedListener mCallback;
	
	/**
	 *  The container Activity must implement this interface so the fragment can deliver messages
	 * @author marcomaccio
	 *
	 */
    public interface OnBankAccountSelectedListener {
    	/**
    	 * Called by the Fragment when a BankAccount is selected
    	 * @param itemId
    	 */
    	public void onBankAccountSelected(long itemId);
    }
    
    /**
     * 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Put divider between BankAccount and FooterView
     	//getListView().setFooterDividersEnabled(true);
     	
 		// We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
     	
        //Instantiate the ContentResolver in order to operate the CRUD operations
  		ContentResolver contentResolver = getActivity().getContentResolver();
      	
  		//Initialize the application data 
  		this.initialize(contentResolver);
  		
  		//CRUD Methods: Retrieve DEMO code
  		//Create the cursor to retrieve the data from the ContentProvider
  		Cursor cursor = contentResolver.query(BankAnalysisContract.Banks.CONTENT_URI, 
  											null, 		//projection 
  											null, 		//selection 
  											null, 		//selectionArgs 
  											null 		//sortOrder
  											);
  		
  		//Create a SimpleCursorAdapater that will be used by the ListActivity to show the cursor elements
  		BankAccountCursorAdapter cursorAdapter = new BankAccountCursorAdapter(getActivity(), 
  												R.layout.list_layout, 
  												cursor,  
  												0);
     	
     	
		//attache the SimpleCursorAdapter to the ListView
		setListAdapter(cursorAdapter);
    }
    
    /**
     * 
     */
    @Override
    public void onStart(){
    	super.onStart();
    	
    	if (getFragmentManager().findFragmentById(R.id.accountstatementlist_fragment) != null) {
    		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    	}
    }
    
    /**
     * 
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
        	mCallback = (OnBankAccountSelectedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnBankAccountSelectedListener" );        	
        }
    }
    
    /**
     * 
     */
    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	
    /**
     * 
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	Log.i(TAG, "itemPosition: " + position + " itemId: " + id);
     
    	CharSequence text = "You have choosen element n." + position  
				+ " BankId: " + id 
				+ " View: " + v.getId();
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(getActivity(), text, duration);
    	toast.show();
    	
    	// Notify the parent activity of selected item
        mCallback.onBankAccountSelected(id);
        
        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }
    
    
    /**
	 * Temporary Convenient method to insert some data in the Content Provider
	 */
	private void initialize(ContentResolver contentResolver){
		//Instantiate the ContentValues in order to pass them as argument for the Create, Update operations.
		//Create the cursor to retrieve the data from the ContentProvider
		Cursor cursor = contentResolver.query(BankAnalysisContract.Banks.CONTENT_URI, 
											null, 		//projection 
											null, 		//selection 
											null, 		//selectionArgs 
											null 		//sortOrder
											);
		
		if (cursor.getCount() == 0) {  
		
			ContentValues bankValues = new ContentValues();
			
			//CRUD Methods: Create DEMO code
			//Create a 1st Record to insert filling all the Object Properties --> Table Columns
			bankValues.put(BankAnalysisContract.Banks.Cols.BANK_NAME, 		"BCV");
			bankValues.put(BankAnalysisContract.Banks.Cols.BANK_ADDRESS, 	"Rue Central Morges");
			bankValues.put(BankAnalysisContract.Banks.Cols.BANK_COUNTRY, 	"SWITZERLAND");
	
			//Insert the bank record and retrieve the URI for the new resource
			Uri firstBankRecordUri = contentResolver.insert(BankAnalysisContract.Banks.CONTENT_URI, bankValues);
			Log.i(TAG, "Bank Id: " + firstBankRecordUri.getLastPathSegment());
			
			ContentValues accountValues = new ContentValues();
			accountValues.put(BankAnalysisContract.Accounts.Cols.AC_HOLDERNAME, "Marco Maccio");
			accountValues.put(BankAnalysisContract.Accounts.Cols.AC_IBAN, 		"CH5900767000H53107881");
			accountValues.put(BankAnalysisContract.Accounts.Cols.AC_BANK_ID, firstBankRecordUri.getLastPathSegment());
			
			Uri firstAccountRecordUri = contentResolver.insert(BankAnalysisContract.Accounts.CONTENT_URI, accountValues);
			Log.i(TAG, "Account Id: " + firstAccountRecordUri.getLastPathSegment());
			
			ContentValues statementValues = new ContentValues();
			statementValues.put(BankAnalysisContract.Statements.Cols.S_EXECDATE, 	"2015-03-01");
			statementValues.put(BankAnalysisContract.Statements.Cols.S_VALUEDATE, 	"2015-03-01");
			statementValues.put(BankAnalysisContract.Statements.Cols.S_ACCOUNT_ID, 	firstAccountRecordUri.getLastPathSegment());
			
			Uri firstStatementRecordUri = contentResolver.insert(BankAnalysisContract.Statements.CONTENT_URI, statementValues);
			Log.i(TAG, "Statement Id: " + firstStatementRecordUri.getLastPathSegment());
			
			//Clean the statement value to insert another one 
			statementValues.clear();
			
			statementValues.put(BankAnalysisContract.Statements.Cols.S_EXECDATE, 	"2015-03-02");
			statementValues.put(BankAnalysisContract.Statements.Cols.S_VALUEDATE, 	"2015-03-02");
			statementValues.put(BankAnalysisContract.Statements.Cols.S_ACCOUNT_ID, 	firstAccountRecordUri.getLastPathSegment());
			
			Uri secondStatementRecordUri = contentResolver.insert(BankAnalysisContract.Statements.CONTENT_URI, statementValues);
			Log.i(TAG, "Statement Id: " + secondStatementRecordUri.getLastPathSegment());
			
			//Clean the values and ready to insert a new record
			bankValues.clear();
			accountValues.clear();
			statementValues.clear();
			
			//Create a 2nd Record to insert filling all the Object Properties --> Table Columns
			bankValues.put(BankAnalysisContract.Banks.Cols.BANK_NAME, 		"IWBANK");
			bankValues.put(BankAnalysisContract.Banks.Cols.BANK_ADDRESS, 	"Via Cavriana Milano");
			bankValues.put(BankAnalysisContract.Banks.Cols.BANK_COUNTRY, 	"ITALY");
			//Insert the record and retrieve the URI for the new resource
			Uri secondBankRecordUri = contentResolver.insert(BankAnalysisContract.Banks.CONTENT_URI, bankValues);
			
			//Clean the values and ready to insert a new record
			bankValues.clear();
		}
	}
}
