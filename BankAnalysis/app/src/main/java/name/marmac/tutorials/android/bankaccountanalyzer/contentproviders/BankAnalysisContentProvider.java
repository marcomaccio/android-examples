package name.marmac.tutorials.android.bankaccountanalyzer.contentproviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * This class defines the BankAnalysisContentProvider
 * 
 * @author marcomaccio
 *
 */
public class BankAnalysisContentProvider extends ContentProvider {
	/**
	 * Instance of the SQLiteDatabase
	 */
	private BankAnalysisSQLiteOpenHelper mBankAnalysisDbHelper;

	/**
	 * Instance of the UriMatcher class used to resolve Uri to integer token used in the switch clauses
	 * to identify the correct action to take.ls -al d
	 */
	private static final UriMatcher URI_MATCHER;
	
	/**
	 * 
	 */
	static {
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(BankAnalysisContract.AUTHORITY, BankAnalysisContract.Banks.PATH, BankAnalysisContract.Banks.PATH_TOKEN);
		URI_MATCHER.addURI(BankAnalysisContract.AUTHORITY, BankAnalysisContract.Banks.PATH_FOR_ID, BankAnalysisContract.Banks.PATH_FOR_ID_TOKEN);
		
		URI_MATCHER.addURI(BankAnalysisContract.AUTHORITY, BankAnalysisContract.Accounts.PATH, BankAnalysisContract.Accounts.PATH_TOKEN);
		URI_MATCHER.addURI(BankAnalysisContract.AUTHORITY, BankAnalysisContract.Accounts.PATH_FOR_ID, BankAnalysisContract.Accounts.PATH_FOR_ID_TOKEN);
		
		URI_MATCHER.addURI(BankAnalysisContract.AUTHORITY, BankAnalysisContract.Statements.PATH, BankAnalysisContract.Statements.PATH_TOKEN);
		URI_MATCHER.addURI(BankAnalysisContract.AUTHORITY, BankAnalysisContract.Statements.PATH_FOR_ID, BankAnalysisContract.Statements.PATH_FOR_ID_TOKEN);
		
	}
	
	/**
	 * 
	 */
	@Override
	public boolean onCreate() {
		//retrieve the Context
		Context context = getContext();
		mBankAnalysisDbHelper = new BankAnalysisSQLiteOpenHelper(context);
		return true;
	}

	/**
	 * 
	 */
	@Override
	public Cursor query(Uri uri, 
			String[] projection, 
			String selection,
			String[] selectionArgs, 
			String sortOrder) {
		
		SQLiteDatabase db = mBankAnalysisDbHelper.getReadableDatabase();
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		
		switch (URI_MATCHER.match(uri)) {
		
			case BankAnalysisContract.Banks.PATH_TOKEN: {
				builder.setTables(BankAnalysisContract.Banks.TABLE_NAME);
				
				if (TextUtils.isEmpty(sortOrder)) {
					sortOrder = BankAnalysisContract.Banks.SORT_ORDER_DEFAULT;
				}
				break;
			}
			case BankAnalysisContract.Banks.PATH_FOR_ID_TOKEN: {
				builder.setTables(BankAnalysisContract.Banks.TABLE_NAME);
				builder.appendWhere(BankAnalysisContract.Banks.Cols._ID + " = " + uri.getLastPathSegment());
				break;
			}
			
			case BankAnalysisContract.Accounts.PATH_TOKEN: {
				builder.setTables(BankAnalysisContract.Accounts.TABLE_NAME);
				
				if (TextUtils.isEmpty(sortOrder)) {
					sortOrder = BankAnalysisContract.Accounts.SORT_ORDER_DEFAULT;
				}
				break;
			}
			case BankAnalysisContract.Accounts.PATH_FOR_ID_TOKEN: {
				builder.setTables(BankAnalysisContract.Accounts.TABLE_NAME);
				builder.appendWhere(BankAnalysisContract.Accounts.Cols._ID + " = " + uri.getLastPathSegment());
				break;
			}
			
			case BankAnalysisContract.Statements.PATH_TOKEN: {
				builder.setTables(BankAnalysisContract.Statements.TABLE_NAME);
				
				if (TextUtils.isEmpty(sortOrder)) {
					sortOrder = BankAnalysisContract.Statements.SORT_ORDER_DEFAULT;
				}
				break;
			}
			case BankAnalysisContract.Statements.PATH_FOR_ID_TOKEN: {
				builder.setTables(BankAnalysisContract.Statements.TABLE_NAME);
				builder.appendWhere(BankAnalysisContract.Statements.Cols._ID + " = " + uri.getLastPathSegment());
				break;
			}
			
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}

		return builder.query(db,
				projection,
				selection,
				selectionArgs,
				null,
				null,
				sortOrder);
	}

	/**
	 * 
	 */
	@Override
	public String getType(Uri uri) {
		
		switch (URI_MATCHER.match(uri)) {
		
			case BankAnalysisContract.Banks.PATH_TOKEN:
				return BankAnalysisContract.Banks.CONTENT_TYPE_DIR;
			
			case BankAnalysisContract.Banks.PATH_FOR_ID_TOKEN:
				return BankAnalysisContract.Banks.CONTENT_ITEM_TYPE;
				
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	/**
	 * 
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = mBankAnalysisDbHelper.getWritableDatabase();
		switch (URI_MATCHER.match(uri)) {
			case BankAnalysisContract.Banks.PATH_TOKEN: {
				long id = db.insert(BankAnalysisContract.Banks.TABLE_NAME, null, values);
				getContext().getContentResolver().notifyChange(uri, null);
				return BankAnalysisContract.Banks.CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
			}	
			case BankAnalysisContract.Accounts.PATH_TOKEN: {
				long id = db.insert(BankAnalysisContract.Accounts.TABLE_NAME, null, values);
				getContext().getContentResolver().notifyChange(uri, null);
				return BankAnalysisContract.Accounts.CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
			}	
			case BankAnalysisContract.Statements.PATH_TOKEN: {
				long id = db.insert(BankAnalysisContract.Statements.TABLE_NAME, null, values);
				getContext().getContentResolver().notifyChange(uri, null);
				return BankAnalysisContract.Statements.CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
			}
			
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	/**
	 * 
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
