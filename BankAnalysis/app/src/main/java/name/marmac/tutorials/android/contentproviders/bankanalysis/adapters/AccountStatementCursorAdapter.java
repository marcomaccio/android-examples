package name.marmac.tutorials.android.contentproviders.bankanalysis.adapters;

import name.marmac.tutorials.android.contentproviders.bankanalysis.R;
import name.marmac.tutorials.android.contentproviders.bankanalysis.providers.BankAnalysisContract;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.widget.ResourceCursorAdapter;

public class AccountStatementCursorAdapter extends ResourceCursorAdapter {
	
	private LayoutInflater mInflater;
	
	/**
	 * 
	 * @param context
	 * @param layout
	 * @param c
	 * @param flags
	 */
	public AccountStatementCursorAdapter(Context context, int layout, Cursor c, int flags) {
		  super(context, layout, c, flags);
		  mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	/**
	 * 
	 */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return mInflater.inflate(R.layout.statementlist_item, parent, false);
	}
	
	/**
	 * 
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		if(cursor.getPosition()%2==1) {
			view.setBackgroundColor(context.getResources().getColor(R.color.background_odd));
		}
		else {
			view.setBackgroundColor(context.getResources().getColor(R.color.background_even));
		}
		
		TextView operationDate = (TextView)view.findViewById(R.id.operationDate);
		operationDate.setText(cursor.getString(cursor.getColumnIndex(BankAnalysisContract.Statements.Cols.S_EXECDATE)));

		TextView valueDate = (TextView)view.findViewById(R.id.operationDate);
		valueDate.setText(cursor.getString(cursor.getColumnIndex(BankAnalysisContract.Statements.Cols.S_VALUEDATE)));
	}

}
