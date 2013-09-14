package com.example.simplephonebook;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
//import android.widget.EditText;
import android.widget.TextView;




public class MainActivity extends Activity {
	
	DBAdapter myDb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		openDB();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();	
		closeDB();
	}


	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}
	private void closeDB() {
		myDb.close();
	}

	
	
	private void displayText(String message) {
        TextView textView = (TextView) findViewById(R.id.textDisplay);
        textView.setText(message);
	}
	
	

	public void onClick_AddRecord(View v) {
		displayText("The contact has been added");
		
		//HEr
		//get data from form
	   // EditText fnameTxt = (EditText)findViewById(R.id.add_firstname);
	   // EditText lnameTxt = (EditText)findViewById(R.id.add_lastname);
	   // EditText pnumTxt = (EditText)findViewById(R.id.add_phonenum);
	   	
	   	//String firstN = fnameTxt.getText().toString();
	   //String lastN = lnameTxt.getText().toString();
	   	//Int phoneN = pnumTxt.getText();
	   	//db.open();        
	       //long newId = myDb.insertRow(fnameTxt.getText().toString(), lnameTxt.getText().toString(), 91700014);        
	      // db.close();
		
		long newId = myDb.insertRow("Marte", "Johnsen", 91741514);
		
		// Query for the record we just added.
		// Use the ID:
		Cursor cursor = myDb.getRow(newId);
		displayRecordSet(cursor);
	}

	public void onClick_ClearAll(View v) {
		displayText("The phonebook has been cleared");
		myDb.deleteAll();
	}

	public void onClick_DisplayRecords(View v) {
		displayText("No contacts");
		
		Cursor cursor = myDb.getAllRows();
		displayRecordSet(cursor);
	}
	
	// Display an entire recordset to the screen.
	private void displayRecordSet(Cursor cursor) {
		String message = "";
		// populate the message from the cursor
		
		// Reset cursor to start, checking to see if there's data:
		if (cursor.moveToFirst()) {
			do {
				// Process the data:
				int id = cursor.getInt(DBAdapter.COL_ROWID);
				String firstName = cursor.getString(DBAdapter.COL_FIRSTNAME);
				String lastName = cursor.getString(DBAdapter.COL_LASTNAME);
				int phoneNumber = cursor.getInt(DBAdapter.COL_PHONENUM);
				
				// Append data to the message:
				message += "id=" + id
						   +", first name:" + firstName
						   +", last name:" + lastName
						   +", phone number:" + phoneNumber
						   +"\n";
			} while(cursor.moveToNext());
		}
		
		// Close the cursor to avoid a resource leak.
		cursor.close();
		
		displayText(message);
	}
	

}










