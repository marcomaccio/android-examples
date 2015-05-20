/**
 * 
 */
package name.marmac.tutorials.android.contentproviders.bankanalysis.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author marcomaccio
 *
 */
public class BankAnalysisSQLiteOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME 	= "BankAnalysis.db";
	private static final int DATABASE_VERSION	= 3;
	
	/**
	 * String for SQL DDLs
	 * Create Tables using the values set in Contract class BankAnalysisContract.Banks
	 */
	private static final String CREATE_TABLE_BANKS 			= "CREATE TABLE " + BankAnalysisContract.Banks.TABLE_NAME + " ( "
								+ BankAnalysisContract.Banks.Cols._ID 			+ " INTEGER  PRIMARY KEY AUTOINCREMENT, " 
								+ BankAnalysisContract.Banks.Cols.BANK_NAME 	+ " TEXT, " 
								+ BankAnalysisContract.Banks.Cols.BANK_ADDRESS 	+ " TEXT, " 
								+ BankAnalysisContract.Banks.Cols.BANK_COUNTRY  + "	TEXT, " 
								+ BankAnalysisContract.Banks.Cols.BANK_BIC		+ " TEXT, " 
								+ BankAnalysisContract.Banks.Cols.BANK_SWIFT	+ " TEXT )";
	
	
	/**
	 * String for SQL DDLs
	 * Create Tables using the values set in Contract class BankAnalysisContract.Accounts
	 */
	private static final String CREATE_TABLE_ACCOUNTS 	= "CREATE TABLE " + BankAnalysisContract.Accounts.TABLE_NAME + " ( " 
								+ BankAnalysisContract.Accounts.Cols._ID 			+ " INTEGER  PRIMARY KEY AUTOINCREMENT, "
								+ BankAnalysisContract.Accounts.Cols.AC_IBAN    	+ " TEXT, "
								+ BankAnalysisContract.Accounts.Cols.AC_HOLDERNAME	+ " TEXT, "
								+ BankAnalysisContract.Accounts.Cols.AC_BANK_ID 	+ " INTEGER, "
									+ "FOREIGN KEY ("+ BankAnalysisContract.Accounts.Cols.AC_BANK_ID +") REFERENCES "+ BankAnalysisContract.Banks.TABLE_NAME + "("+ BankAnalysisContract.Banks.Cols._ID +")  "
									+ ")";
	
	/**
	 * String for SQL DDLs
	 * Create Tables using the values set in Contract class BankAnalysisContract.Statements
	 */
	private static final String CREATE_TABLE_STATEMENTS 	= "CREATE TABLE " + BankAnalysisContract.Statements.TABLE_NAME + " ( "
								+ BankAnalysisContract.Statements.Cols._ID 			+ " INTEGER  PRIMARY KEY AUTOINCREMENT, "
								+ BankAnalysisContract.Statements.Cols.S_EXECDATE 	+ " TEXT, "
								+ BankAnalysisContract.Statements.Cols.S_VALUEDATE 	+ " TEXT, "
								+ BankAnalysisContract.Statements.Cols.S_ACCOUNT_ID + " INTEGER, "
									+ "FOREIGN KEY ("+ BankAnalysisContract.Statements.Cols.S_ACCOUNT_ID +") REFERENCES "+ BankAnalysisContract.Accounts.TABLE_NAME + "("+ BankAnalysisContract.Accounts.Cols._ID +") "
									+ ")";
	
	
	//Drop Tables
	private static final String DDL_DROP_TBL_BANKS 			= "DROP TABLE IF EXISTS " + BankAnalysisContract.Banks.TABLE_NAME;
	private static final String DDL_DROP_TBL_ACCOUNTS		= "DROP TABLE IF EXISTS " + BankAnalysisContract.Accounts.TABLE_NAME;
	private static final String DDL_DROP_TBL_STATEMENTS		= "DROP TABLE IF EXISTS " + BankAnalysisContract.Statements.TABLE_NAME;
	

	
	/**
	 * Public Constructor 
	 * @param context
	 */
	public BankAnalysisSQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_BANKS);
		db.execSQL(CREATE_TABLE_ACCOUNTS);
		db.execSQL(CREATE_TABLE_STATEMENTS);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 if(oldVersion < newVersion){
			 db.execSQL(DDL_DROP_TBL_STATEMENTS);
			 db.execSQL(DDL_DROP_TBL_ACCOUNTS);
			 db.execSQL(DDL_DROP_TBL_BANKS);
			 //Recreate the Db 
			 onCreate(db);
		 }
	}

}
