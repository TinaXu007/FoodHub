package com.wacmob.foodhub.view.ui.edit_profile;

import android.Manifest;
import android.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.databinding.ActivityEditProfileBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.viewmodel.EditProfileViewModel;

import java.io.IOException;

import javax.inject.Inject;

public class EditProfileActivity extends BaseActivity<ActivityEditProfileBinding, EditProfileViewModel> implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    Bitmap photo;
    ActivityEditProfileBinding dataBinding;
    EditProfileViewModel editProfileViewModel;
    String userName, email, cityName, pinCode, houseName, phoneNumber;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;


    @Inject
    ViewModelProviderFactory mViewModelFactory;

    public static Intent newIntent(Context context) {
        return new Intent(context, EditProfileActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.editProfile;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    public EditProfileViewModel getBindingObject() {
        return editProfileViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editProfileViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EditProfileViewModel.class);
        dataBinding = getViewDataBinding();
        dataBinding.setListener(this::onClick);
        //requestMultiplePermissions();


    }

    @Override
    public void onClick(View v) {

        if (v == dataBinding.imageViewCamera) {
            showPictureDialog();

        } else if (v == dataBinding.imageViewPrevArrow) {

            hideKeyboard();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onBackPressed();
                }
            }, 300);


        } else if (v == dataBinding.imageViewNextArrow) {

            hideKeyboard();
            userName = dataBinding.editTextUserName.getText().toString();
            email = dataBinding.editTextEmail.getText().toString();
            cityName = dataBinding.editTextCity.getText().toString();
            pinCode = dataBinding.editTextPin.getText().toString();
            houseName = dataBinding.editTextHouseName.getText().toString();
            phoneNumber = dataBinding.editTextPhone.getText().toString();

            int l = phoneNumber.length();
            if (!email.isEmpty() && !cityName.isEmpty() && !pinCode.isEmpty() &&
                    !phoneNumber.isEmpty() && !userName.isEmpty() && !houseName.isEmpty()) {


              /*  if (l <= 8 || l >= 14) {

                    dataBinding.editTextPhone.setError("Enter a valid phone number");

                }*/
                String email = dataBinding.editTextEmail.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email.matches(emailPattern) && !(l <= 8 || l >= 14)) {

                    Intent i = new Intent();
                    i.putExtra("photo", photo);
                    i.putExtra("NAME", userName);
                    i.putExtra("HOUSE_NAME", houseName);
                    i.putExtra("CITY_NAME", cityName);
                    i.putExtra("PIN_CODE", pinCode);
                    i.putExtra("PHONE_NUMBER", phoneNumber);
                    i.putExtra("USER_NAME", userName);
                    setResult(RESULT_OK, i);
                    EditProfileActivity.this.finish();
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

                } else {
                    if (email.matches(emailPattern) && (l <= 8 || l >= 14)) {
                        dataBinding.editTextPhone.setError("Enter a valid  contact number");
                    } else if (!email.matches(emailPattern) && (l <= 8 || l >= 14)) {
                        dataBinding.editTextEmail.setError("Enter a valid email id");
                        dataBinding.editTextPhone.setError("Enter a valid  contact number");

                    } else {
                        dataBinding.editTextEmail.setError("Enter a valid email id");
                    }
                }


            } else {
                showToast("please fill all the fields");
            }
        }

    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {

        if (ContextCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else {
            /*Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);*/

            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("camera permission granted");
                // Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                showToast("camera permission denied");
                //Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    //String path = saveImage(bitmap);
                    // Toast.makeText(EditProfileActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    dataBinding.circleImageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(EditProfileActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {

            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            dataBinding.circleImageView.setImageBitmap(thumbnail);
            //saveImage(thumbnail);
            // Toast.makeText(EditProfileActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    /*  public String saveImage(Bitmap myBitmap) {
          ByteArrayOutputStream bytes = new ByteArrayOutputStream();
          myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
          File wallpaperDirectory = new File(
                  Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
          // have the object build the directory structure, if needed.
          if (!wallpaperDirectory.exists()) {
              wallpaperDirectory.mkdirs();
          }

          try {
              File f = new File(wallpaperDirectory, Calendar.getInstance()
                      .getTimeInMillis() + ".jpg");
              f.createNewFile();
              FileOutputStream fo = new FileOutputStream(f);
              fo.write(bytes.toByteArray());
              MediaScannerConnection.scanFile(this,
                      new String[]{f.getPath()},
                      new String[]{"image/jpeg"}, null);
              fo.close();
              Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

              return f.getAbsolutePath();
          } catch (IOException e1) {
              e1.printStackTrace();
          }
          return "";
      }
  */
   /* private void requestMultiplePermissions() {

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                });


    }*/
    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        EditProfileActivity.this.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }
}
