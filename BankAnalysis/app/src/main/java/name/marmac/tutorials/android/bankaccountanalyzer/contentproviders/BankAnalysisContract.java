package name.marmac.tutorials.android.bankaccountanalyzer.contentproviders;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/** 
 * The contract class between Content Provider Clients and the BankAnalysis Content Provider
 * 
 * @author marcomaccio
 *
 */
public final class BankAnalysisContract {

	public static final String 	AUTHORITY 	= "name.marmac.tutorials.android.contentproviders.bankanalysis.provider";
	
	public static final Uri 	BASE_URI 	= Uri.parse("content://"+ AUTHORITY);
	
	/**
	 * Define the static class that represents Bank description of stored bank entity.
	 * it defines the URI Paths to access the entity
	 * BASE_URI/banks 	- to list of bank(s)
	 * BASE_URI/banks/* - to retrieve specific bank by id 
	 * @author marcomaccio
	 *
	 */
	public static final class Banks {
		/**
		 * Bank table name
		 */
		public static final String 	TABLE_NAME 			= "banks";
		/**
		 * Path in case of multiple rows for the Content URI
		 */
		public static final String	PATH 				= TABLE_NAME;
		/**
		 * Token for the UriMatcher resolution
		 */
		public static final int 	PATH_TOKEN 			= 110;

		/**
		 * Path in case of single row for the Content URI
		 */
		public static final String	PATH_FOR_ID 		= PATH +"/*";
		/**
		 * Token for the UriMatcher resolution
		 */
		public static final int 	PATH_FOR_ID_TOKEN 	= 120;
		/**
		 *  URI for all content stored as banks entity
		 */
		public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH).build();
		/**
		 * MimeType for multiple row in table Banks
		 * as explained here http://developer.android.com/guide/topics/providers/content-provider-creating.html#MIMETypes
		 * in this case:
		 * vnd.android.cursor.dir/vnd.name.marmac.tutorials.android.contentproviders.bankanalysis.provider.banks
		 */
		public static final String CONTENT_TYPE_DIR = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + AUTHORITY + Banks.PATH;
		/**
		 * MimeType for single row in table Banks
		 * as explained here http://developer.android.com/guide/topics/providers/content-provider-creating.html#MIMETypes
		 * in this case:
		 * vnd.android.cursor.item/vnd.name.marmac.tutorials.android.contentproviders.bankanalysis.provider.banks
		 */
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + AUTHORITY + Banks.PATH;
		
		/**
		 * A static class to store columns in entity
		 * @author marcomaccio
		 *
		 */
		public static class Cols {
			public static final String _ID 				= BaseColumns._ID; // convention
			// The name and column index of each column in the banks table
			public static final String BANK_NAME 		= "name";
			public static final String BANK_ADDRESS 	= "address";
			public static final String BANK_COUNTRY 	= "country";
			public static final String BANK_BIC 		= "bic";
			public static final String BANK_SWIFT 		= "swift";
			
		}
		
		/**
		 * A projection of ALL columns in the banks table, including internal use ones
		 */
		public static final String[] PROJECTION_ALL = {Cols._ID, Cols.BANK_NAME, Cols.BANK_ADDRESS, Cols.BANK_COUNTRY,
										Cols.BANK_BIC, Cols.BANK_SWIFT};
		/**
		 * The default sort order for queries containing NAME fields.
		 */
		public static final String SORT_ORDER_DEFAULT = Cols.BANK_NAME + " ASC";
	}

	/**
	 * Define the static class that represents Accounts description of stored account entity.
	 * it defines the URI Paths to access the entity
	 * BASE_URI/accounts 	- to list all account(s)
	 * BASE_URI/accounts/* 	- to retrieve specific account by its id 
	 * 
	 * @author marcomaccio
	 *
	 */
	public static class Accounts {
		/**
		 * Accounts table name
		 */
		public static final String 	TABLE_NAME 	= "accounts";
		/**
		 * Path in case of multiple rows for the Content URI
		 */
		public static final String	PATH 				= TABLE_NAME;
		/**
		 * Token for the UriMatcher resolution
		 */
		public static final int 	PATH_TOKEN 			= 130;

		/**
		 * Path in case of single row for the Content URI
		 */
		public static final String	PATH_FOR_ID 		= PATH +"/*";
		/**
		 * Token for the UriMatcher resolution
		 */
		public static final int 	PATH_FOR_ID_TOKEN 	= 140;
		/**
		 *  URI for all content stored as banks entity
		 */
		public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH).build();
		/**
		 * MimeType for multiple row in table Banks
		 * as explained here http://developer.android.com/guide/topics/providers/content-provider-creating.html#MIMETypes
		 * in this case:
		 * vnd.android.cursor.dir/vnd.name.marmac.tutorials.android.contentproviders.bankanalysis.provider.banks
		 */
		public static final String CONTENT_TYPE_DIR = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + AUTHORITY + Accounts.PATH;
		/**
		 * MimeType for single row in table Banks
		 * as explained here http://developer.android.com/guide/topics/providers/content-provider-creating.html#MIMETypes
		 * in this case:
		 * vnd.android.cursor.item/vnd.name.marmac.tutorials.android.contentproviders.bankanalysis.provider.banks
		 */
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + AUTHORITY + Accounts.PATH;
		
		
		/**
		 * 
		 */
		public static class Cols {
			public static final String _ID 				= BaseColumns._ID; // convention
			public static final String AC_IBAN 			= "iban";
			public static final String AC_HOLDERNAME 	= "holder_name";
			public static final String AC_BANK_ID 		= "bank_id";
		}
		
		/**
		 * A projection of ALL columns in the banks table, including internal use ones
		 */
		public static final String[] PROJECTION_ALL = {Cols._ID, Cols.AC_IBAN, Cols.AC_HOLDERNAME, Cols.AC_BANK_ID};
		
		/**
		 * The default sort order for queries containing NAME fields.
		 */
		public static final String SORT_ORDER_DEFAULT = Cols.AC_IBAN + " ASC";
		
	}
	
	/**
	 * Define the static class that represents Statement description of stored statement entity.
	 * it defines the URI Paths to access the entity
	 * BASE_URI/statements 		- to list all statements(s)
	 * BASE_URI/statements/* 	- to retrieve specific statement by its id
	 * 
	 * @author marcomaccio
	 *
	 */
	public static class Statements {
		/**
		 * Statements table name
		 */
		public static final String 	TABLE_NAME 	= "statements";
		/**
		 * Path in case of multiple rows for the Content URI
		 */
		public static final String	PATH 				= TABLE_NAME;
		/**
		 * Token for the UriMatcher resolution
		 */
		public static final int 	PATH_TOKEN 			= 150;

		/**
		 * Path in case of single row for the Content URI
		 */
		public static final String	PATH_FOR_ID 		= PATH +"/*";
		/**
		 * Token for the UriMatcher resolution
		 */
		public static final int 	PATH_FOR_ID_TOKEN 	= 160;
		/**
		 *  URI for all content stored as banks entity
		 */
		public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH).build();
		/**
		 * MimeType for multiple row in table Banks
		 * as explained here http://developer.android.com/guide/topics/providers/content-provider-creating.html#MIMETypes
		 * in this case:
		 * vnd.android.cursor.dir/vnd.name.marmac.tutorials.android.contentproviders.bankanalysis.provider.banks
		 */
		public static final String CONTENT_TYPE_DIR = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + AUTHORITY + Statements.PATH;
		/**
		 * MimeType for single row in table Banks
		 * as explained here http://developer.android.com/guide/topics/providers/content-provider-creating.html#MIMETypes
		 * in this case:
		 * vnd.android.cursor.item/vnd.name.marmac.tutorials.android.contentproviders.bankanalysis.provider.banks
		 */
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + AUTHORITY + Accounts.PATH;

		/**
		 * 
		 * @author marcomaccio
		 *
		 */
		public static class Cols {
			public static final String 	_ID 			= BaseColumns._ID; // convention
			public static final String	S_ACCOUNT_ID	= "account_id";
			public static final String 	S_EXECDATE		= "exec_date";
			public static final String 	S_VALUEDATE		= "value_date";
		}
		
		/**
		 * A projection of ALL columns in the banks table, including internal use ones
		 */
		public static final String[] PROJECTION_ALL = {Cols._ID, Cols.S_EXECDATE, Cols.S_VALUEDATE, Cols.S_ACCOUNT_ID};
		
		/**
		 * The default sort order for queries containing NAME fields.
		 */
		public static final String SORT_ORDER_DEFAULT = Cols.S_EXECDATE + " ASC";
	}

	/**
	 * Define the static class that represents BankAndAccounts description that join the two tables banks and accounts 
	 * it defines the URI Paths to access the entity
	 * BASE_URI/banksandaccounts 	- to list all accounts associated to a given bank
	 * BASE_URI/banksandaccounts/* 	- to retrieve specific account by its id
	 * 
	 * @author marcomaccio
	 *
	 */
	public static class BankAndAccountsView {
		/**
		 * BankAndAccount view name
		 */
		public static final String 	VIEW_NAME 			= "banksandaccounts";

	}
}
