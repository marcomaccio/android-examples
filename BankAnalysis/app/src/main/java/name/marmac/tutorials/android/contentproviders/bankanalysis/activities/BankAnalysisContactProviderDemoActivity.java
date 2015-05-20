package name.marmac.tutorials.android.contentproviders.bankanalysis.activities;


import name.marmac.tutorials.android.contentproviders.bankanalysis.adapters.BankAccountCursorAdapter;
import name.marmac.tutorials.android.contentproviders.bankanalysis.providers.BankAnalysisContract;
import name.marmac.tutorials.android.contentproviders.bankanalysis.R;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BankAnalysisContactProviderDemoActivity extends ListActivity {
	
	/**
	 * tag for Log purposes
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "BankAnalysisContactProviderDemoActivity";
	
	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		//call the super onCreate method as usual
		super.onCreate(savedInstanceState);
		
		// Put divider between BankAccount and FooterView
		getListView().setFooterDividersEnabled(true);
		
		//Inflate footerView for footer_view.xml file
		RelativeLayout footerView = (RelativeLayout)getLayoutInflater().inflate(R.layout.bankaccountlist_footer, null); 

		//Add footerView to ListView
		getListView().addFooterView(footerView);
		
		//Instantiate the ContentResolver in order to operate the CRUD operations
		ContentResolver contentResolver = getContentResolver();
		
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
		BankAccountCursorAdapter cursorAdapter = new BankAccountCursorAdapter(this, 
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
	public void onListItemClick(ListView l, View v, int position, long id) {
		CharSequence text = "You have choosen element n." + position  
							+ " BankId: " + id 
							+ " View: " + v.getId();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(this, text, duration);
		toast.show();
    }
	
	/**
	 * 
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
		
			ContentValues values = new ContentValues();
			
			//CRUD Methods: Create DEMO code
			//Create a 1st Record to insert filling all the Object Properties --> Table Columns
			values.put(BankAnalysisContract.Banks.Cols.BANK_NAME, "BCV");
			values.put(BankAnalysisContract.Banks.Cols.BANK_ADDRESS, "Rue Central Morges");
			values.put(BankAnalysisContract.Banks.Cols.BANK_COUNTRY, "SWITZERLAND");
	
			//Insert the record and retrieve the URI for the new resource
			Uri firstRecordUri = contentResolver.insert(BankAnalysisContract.Banks.CONTENT_URI, values);
			
			//Clean the values and ready to insert a new record
			values.clear();
			
			
			//Create a 2nd Record to insert filling all the Object Properties --> Table Columns
			values.put(BankAnalysisContract.Banks.Cols.BANK_NAME, 		"IWBANK");
			values.put(BankAnalysisContract.Banks.Cols.BANK_ADDRESS, 	"Via Cavriana Milano");
			values.put(BankAnalysisContract.Banks.Cols.BANK_COUNTRY, 	"ITALY");
			//Insert the record and retrieve the URI for the new resource
			Uri secondRecordUri = contentResolver.insert(BankAnalysisContract.Banks.CONTENT_URI, values);
			//Clean the values and ready to insert a new record
			values.clear();
		}
	}
	
}
