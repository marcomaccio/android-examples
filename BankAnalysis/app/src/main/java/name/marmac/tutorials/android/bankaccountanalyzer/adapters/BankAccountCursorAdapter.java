package name.marmac.tutorials.android.bankaccountanalyzer.adapters;

import name.marmac.tutorials.android.bankaccountanalyzer.R;
import name.marmac.tutorials.android.bankaccountanalyzer.contentproviders.BankAnalysisContract;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 */
public class BankAccountCursorAdapter extends ResourceCursorAdapter {
	
	private static final String TAG = "BankAnalysisApp";
	private LayoutInflater mInflater;

	/**
	 * 
	 * @param context
	 * @param layout
	 * @param c
	 * @param flags
	 */
	public BankAccountCursorAdapter(Context context, int layout, Cursor c, int flags) {
		  super(context, layout, c, flags);
		  mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * 
	 */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return mInflater.inflate(R.layout.bankaccountlist_item, parent, false);
	}

	/**
	 * 
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		
		if (cursor.getPosition()%2==1) {
			view.setBackgroundColor(context.getResources().getColor(R.color.background_odd));
		}
		else {
			view.setBackgroundColor(context.getResources().getColor(R.color.background_even));
		}
		
		TextView bankName = (TextView) view.findViewById(R.id.bankName);
		bankName.setText(cursor.getString(cursor.getColumnIndex(BankAnalysisContract.Banks.Cols.BANK_NAME)));
		
		TextView bankAddress = (TextView)view.findViewById(R.id.bankAddress);
		bankAddress.setText(cursor.getString(cursor.getColumnIndex(BankAnalysisContract.Banks.Cols.BANK_ADDRESS)));
		
		TextView bankCountry = (TextView)view.findViewById(R.id.bankCountry);
		bankCountry.setText(cursor.getString(cursor.getColumnIndex(BankAnalysisContract.Banks.Cols.BANK_COUNTRY)));
	}

}
