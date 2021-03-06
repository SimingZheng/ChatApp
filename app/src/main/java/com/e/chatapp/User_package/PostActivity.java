package com.e.chatapp.User_package;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.e.chatapp.R;
import com.e.chatapp.ui.channel.ChannelFragment;
import com.e.chatapp.ui.channel.subchannel.SubChannel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class PostActivity extends AppCompatActivity {
    private ImageButton imageBtn;
    private static final int GALLERY_REQUEST_CODE = 1;
    private Uri uri = null;
    private EditText textChannel;
    private EditText textDesc;
    private Button postBtn;
    private StorageReference storage;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;
    private String data;
    private String string;
    public static Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mActivity=this;
        postBtn = (Button)findViewById(R.id.postBtn);
        textDesc = (EditText)findViewById(R.id.textDesc);
        textChannel = (EditText)findViewById(R.id.textChannel);
        storage = FirebaseStorage.getInstance().getReference().child("Post");
        databaseRef = database.getInstance().getReference().child("Postzone");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());
        data = getIntent().getStringExtra("Data");
        Log.i("PostActivity","getdata"+data);
        imageBtn = (ImageButton)findViewById(R.id.imageBtn);
        //picking image from gallery
        imageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST_CODE);
            }

        });
        //posting to Firebase
        postBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(PostActivity.this, "POSTING", Toast.LENGTH_LONG).show();
                final String PostChannel = textChannel.getText().toString().trim();
                final String PostDesc = textDesc.getText().toString().trim();
                //do a check for empty fields
                if (!TextUtils.isEmpty(PostDesc) && !TextUtils.isEmpty(PostChannel)){
                    //final StorageReference filepath = storage.child("post_images").child(uri.getLastPathSegment());
                    final  StorageReference filepath = storage.child(mDatabaseUsers+".jpg");
                    filepath.putFile(uri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                            //@SuppressWarnings("VisibleForTest")
                            //getting the post image download url
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    final String imagedownload =uri.toString();
                                    //string = imagedownload;
                                    Log.w("Postactivity","string changed");
                                    Toast.makeText(getApplicationContext(), "Succesfully Uploaded", Toast.LENGTH_SHORT).show();
                                    final DatabaseReference newPost = databaseRef.push();
                                    //adding post contents to database reference
                                    mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            newPost.child("channel").setValue(PostChannel);
                                            newPost.child("desc").setValue(PostDesc);
                                            //newPost.child("imageUrl").setValue(downloadUrl);
                                            newPost.child("imageUrl").setValue(imagedownload);
                                            Log.w("PostActivity","ImageUrl set successfully");
                                            newPost.child("uid").setValue(mCurrentUser.getUid());
                                            newPost.child("username").setValue(dataSnapshot.child("username").getValue())
                                                    .addOnCompleteListener(new OnCompleteListener<Void>(){
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task){
                                                            if(task.isSuccessful()){
                                                                PostActivity.mActivity.finish();
                                                            }
                                                        }
                                                    });


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }

                                    });
                                }
                            });
                            //final Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                            //Log.i("downloadURI",downloadUrl.toString());
                            /*Toast.makeText(getApplicationContext(), "Succesfully Uploaded", Toast.LENGTH_SHORT).show();
                            final DatabaseReference newPost = databaseRef.push();
                            //adding post contents to database reference
                            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    newPost.child("channel").setValue(PostChannel);
                                    newPost.child("desc").setValue(PostDesc);
                                    //newPost.child("imageUrl").setValue(downloadUrl);
                                    newPost.child("imageUrl").setValue(string);
                                    Log.w("PostActivity","ImageUrl set successfully");
                                    newPost.child("uid").setValue(mCurrentUser.getUid());
                                    newPost.child("username").setValue(dataSnapshot.child("username").getValue())
                                            .addOnCompleteListener(new OnCompleteListener<Void>(){
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task){
                                                    if(task.isSuccessful()){
                                                        PostActivity.mActivity.finish();
                                                    }
                                                }
                                            });


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }

                            });
                        */}
                    });


                }
            }
        });
    }
    @Override
    //image from gallery result
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == GALLERY_REQUEST_CODE&&resultCode == RESULT_OK){
            uri = data.getData();
            Log.i("URI",uri.toString());
            imageBtn.setImageURI(uri);
        }
    }
}
