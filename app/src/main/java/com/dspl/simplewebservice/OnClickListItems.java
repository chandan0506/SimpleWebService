package com.dspl.simplewebservice;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class OnClickListItems extends Activity {
    EditText editTextName,editTextEmail,editTextMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_list_items);
        initialize();
    }
    private void initialize() {

        editTextName = (EditText) this.findViewById(R.id.edit_text_showingName);
        editTextEmail = (EditText) this.findViewById(R.id.edit_text_showingEmail);
        editTextMobile = (EditText) this.findViewById(R.id.edit_text_showingMobile);
    }
}
