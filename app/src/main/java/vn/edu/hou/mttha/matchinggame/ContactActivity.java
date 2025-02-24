package vn.edu.hou.mttha.matchinggame;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {
    ArrayList<Contact> lsContacts = new ArrayList<>();
    Button btnSave, btnContacts, btnThemMoi;
    ImageButton btnDate;
    EditText etID, etTen, etEmail, etPhone;
    AutoCompleteTextView auTinhThanh;
    TextView lblNgayTao;
    ListView lvContacts;

    //datasource
    final static String lsDiaChi[] ={"Ha Noi", "Hai Phong", "Phu Tho", "Ha Giang", "Nha Trang"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getViews();
        loadDiaChi();

    }

    private void getViews(){
        etID = findViewById(R.id.etID);
        etTen = findViewById(R.id.etTen);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        lvContacts = findViewById(R.id.lvContacts);
        btnContacts = findViewById(R.id.btnContacts);
        btnDate= findViewById(R.id.btnDate);
        btnSave= findViewById(R.id.btnSave);
        btnThemMoi= findViewById(R.id.btnThemMoi);
        lblNgayTao = findViewById(R.id.lblNgayTao);
        auTinhThanh = findViewById(R.id.auTinhThanh);
        btnSave.setOnClickListener(this::onClick);
        btnDate.setOnClickListener(this::onClick);
        btnContacts.setOnClickListener(this::onClick);
        btnThemMoi.setOnClickListener(this::onClick);
        lvContacts.setOnItemClickListener(this::onItemClick);
    }

    private void loadDiaChi(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,lsDiaChi);
        auTinhThanh.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnDate){
            getNgayTao();
        }
        if(v.getId()==R.id.btnContacts){
            loadContacts();
        }
        if(v.getId()==R.id.btnSave){
            saveContact(getContact());
        }
        if ((v.getId()==R.id.btnThemMoi)){
            reset();
        }

    }

    /**
     *
     * @return
     */
    private Contact getContact(){
        Contact contact = new Contact();
        contact.ID = etID.getText().toString();
        contact.hoTen=etTen.getText().toString();
        contact.diaChi = auTinhThanh.getText().toString();
        contact.email = etEmail.getText().toString();
        contact.soDT = etPhone.getText().toString();
        contact.ngayTao = lblNgayTao.getText().toString();
        return contact;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact contact =(Contact) lvContacts.getAdapter().getItem(position);
        Toast.makeText(this, contact.hoTen +", " + contact.soDT,Toast.LENGTH_LONG).show();
    }

    /**
     *
     */
    private void loadContacts(){
        ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,lsContacts);
        lvContacts.setAdapter(adapter);
    }

    /**
     *
     * @param contact
     */
    private void saveContact(Contact contact){
        lsContacts.add(contact);
    }
    private void getNgayTao(){
        final Calendar calendar = Calendar.getInstance();
        int year= calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog
                =new DatePickerDialog(ContactActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                lblNgayTao.setText(dayOfMonth +"/" +(month+1)+"/"+year);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private void reset(){
        etID.setText("");
        etTen.setText("");
        etPhone.setText("");
        etEmail.setText("");
        auTinhThanh.setText("");
        lblNgayTao.setText("");
    }
}