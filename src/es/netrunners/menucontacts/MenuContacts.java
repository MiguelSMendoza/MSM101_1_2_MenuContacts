package es.netrunners.menucontacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Intents.Insert;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class MenuContacts extends Activity {
	// Variables Globales de IU 
	EditText name;
	EditText phone;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Enlazamos variables con componentes de la IU
		name = (EditText) findViewById(R.id.Name);
		phone = (EditText) findViewById(R.id.phone);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addContact:
			showConfirmDialog();
			return true;
		}
		return false;
	}

	/**
	 * Muestra un dialogo solicitando al usuario confirmación sobre el nuevo contacto
	 */
	private void showConfirmDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("¿Está seguro de querer añadir a "+name.getText()+" como Contacto?")
				.setCancelable(false)
				.setPositiveButton("Sí",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								addContact();

							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	/**
	 * Añade un nuevo contacto a la lista de Contactos del dispositivo
	 */
	protected void addContact() {
		// Call Phone New Contact Intent with our new Contact information
		Intent i = new Intent(Intent.ACTION_INSERT_OR_EDIT);
		i.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
		i.putExtra(Insert.NAME, name.getText().toString());
		i.putExtra(Insert.PHONE, phone.getText());
		startActivity(i);
	}
}