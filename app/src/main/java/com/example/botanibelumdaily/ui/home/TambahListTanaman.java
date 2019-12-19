package com.example.botanibelumdaily.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.botanibelumdaily.MainMenu;
import com.example.botanibelumdaily.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TambahListTanaman extends AppCompatActivity {

    EditText txtNamaTanaman, txtDesTanaman;


    NumberPicker txtWaktuTanaman;

    Button back, upload;
    ImageView imgview;

    String token;

    StorageReference strRef;
    public Uri imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_list_tanaman);

        Intent intent = getIntent();

        token = intent.getStringExtra("token");

        strRef = FirebaseStorage.getInstance().getReference();

        upload = findViewById(R.id.submit_list_tanaman);
        back = findViewById(R.id.kembali);
        
        txtNamaTanaman = findViewById(R.id.edit_text_nama_tanaman);
        txtDesTanaman = findViewById(R.id.edit_text_deskripsi_tanaman);
        txtWaktuTanaman = findViewById(R.id.edit_text_waktu_tanaman);

        txtWaktuTanaman.setMaxValue(100);
        txtWaktuTanaman.setMinValue(1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kembali();
            }
        });
        
        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FileUploader();
            }
        });

    }

    private void Kembali() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    private void FileUploader() {
        final StorageReference reference = strRef.child(System.currentTimeMillis()+".");
        final String nama = txtNamaTanaman.getText().toString();
        final String des = txtDesTanaman.getText().toString();
        final int waktu = txtWaktuTanaman.getValue();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user").child(token).child("tanaman").child(nama);

        ref.child("nama_list_tanaman").setValue(nama);
        ref.child("deskripsi_list_tanaman").setValue(des);
        ref.child("waktu_list_tanaman").setValue(waktu);


        Toast.makeText(this, "List tanaman berhasil ditambahkan", Toast.LENGTH_SHORT).show();
        return;


//        final UploadTask uploadTask = reference.putFile(imguri);
//
//        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//            @RequiresApi(api = Build.VERSION_CODES.P)
//            @Override
//            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
//
//                Toast.makeText(TambahListTanaman.this, "Image Uploaded Succesfully",Toast.LENGTH_SHORT).show();
//                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                    @Override
//                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                        if(!task.isSuccessful()){
//                            Log.i("problem",task.getException().toString());
//                        }
//
//                        return reference.getDownloadUrl();
//                    }
//                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task) {
//                        if(task.isSuccessful()){
//                            Uri downloadUri = task.getResult();
//                            System.out.println(downloadUri.toString());
//
//                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user").child(token).child("tanaman");
//
//                            ref.child("gambar_list_tanaman").setValue(downloadUri.toString());
//                            ref.child("nama_list_tanaman").setValue(nama);
//                            ref.child("deskripsi_list_tanaman").setValue(des);
//                            ref.child("waktu_list_tanaman").setValue(waktu);
//                        } else{
//                            Log.i("wentWrong","downloadUri failure");
//                        }
//                    }
//                });
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                // Handle unsuccessful uploads
//                // ..
//            }
//        });


    }

    private void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    private String getExtension(Uri uri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode == RESULT_OK & data != null ){
            imguri = data.getData();
            imgview.setImageURI(imguri);
        }
    }
}
