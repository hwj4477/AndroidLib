//package com.hwj4477.androidlib.activity;
//
//import android.Manifest;
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import com.gun0912.tedpermission.PermissionListener;
//import com.gun0912.tedpermission.TedPermission;
//import com.stn.king.privatelesson.R;
//
//import java.io.File;
//import java.util.ArrayList;
//
//import static com.stn.utils.helper.FileUtil.internalStoragePath;
//
///**
// * @author wjhong
// * @since 2016. 2. 2..
// */
//public abstract class ImageLoadActivity extends AppCompatActivity {
//
//    protected abstract void resultLoadImage(Bitmap thumbnail, Bitmap image);
//    protected abstract void canceledLoadImage();
//    protected abstract void failedLoadImage();
//
//    private static final int RESULT_CODE_GALLERY = 1;
//    private static final int RESULT_CODE_CAMERA = 2;
//
//    // load image : Gallery
//    protected void imageLoadFromGallery() {
//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
////                Toast.makeText(ImageLoadActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
//
//                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                startActivityForResult(galleryIntent, RESULT_CODE_GALLERY);
//            }
//
//            @Override
//            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
////                Toast.makeText(ImageLoadActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//
//                failedLoadImage();
//            }
//        };
//
//        new TedPermission(this)
//                .setPermissionListener(permissionlistener)
//                .setDeniedMessage(getString(R.string.alert_read_phone_state_write_external_storage_read_external_storage_msg))
//                .setPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
//                .check();
//    }
//
//    // load image : Camera
//    protected void imageLoadFromCamera() {
//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
////                Toast.makeText(ImageLoadActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
//
//                Intent cameraIntent = new Intent();
//                cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                startActivityForResult(cameraIntent, RESULT_CODE_CAMERA);
//            }
//
//            @Override
//            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
////                Toast.makeText(ImageLoadActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//
//                failedLoadImage();
//            }
//        };
//
//        new TedPermission(this)
//                .setPermissionListener(permissionlistener)
//                .setDeniedMessage(getString(R.string.alert_read_phone_state_camera_write_external_storage_read_external_storage_msg))
//                .setPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
//                .check();
//    }
//
//    /**
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // gallery
//        if (requestCode == RESULT_CODE_GALLERY && resultCode == RESULT_OK && null != data) {
//
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//            Cursor cursor = getContentResolver().query(
//                    selectedImage, filePathColumn, null, null, null);
//            cursor.moveToFirst();
//
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String filePath = cursor.getString(columnIndex);
//            cursor.close();
//
//            Bitmap thumbnail = BitmapHelper.resizeBitmap(filePath, 6);
//            Bitmap image = BitmapHelper.resizeBitmap(filePath, 2);
//
//            thumbnail = BitmapHelper.GetRotatedBitmap(thumbnail, BitmapHelper.GetExifOrientation(filePath));
//            image = BitmapHelper.GetRotatedBitmap(image, BitmapHelper.GetExifOrientation(filePath));
//
//            resultLoadImage(thumbnail, image);
//
//        }
//        // camera
//        else if (requestCode == RESULT_CODE_CAMERA && resultCode == RESULT_OK && null != data) {
//
//            String filePath;
//
//            Bitmap thumbnail;
//            Bitmap image;
//
//            try {
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//                Cursor cursor = getContentResolver().query(
//                        selectedImage, filePathColumn, null, null, null);
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                filePath = cursor.getString(columnIndex);
//                cursor.close();
//
//                thumbnail = BitmapHelper.resizeBitmap(filePath, 6);
//                image = BitmapHelper.resizeBitmap(filePath, 2);
//            }
//            catch (NullPointerException e) {
//
//                Bundle extras = data.getExtras();
//                Bitmap mImageBitmap = (Bitmap)extras.get("data");
//
//                filePath = internalStoragePath(this) + "/image_temp.jpg";
//                new File(filePath);
//                FileUtil.saveBitmapToFile(mImageBitmap, filePath);
//
//                thumbnail = mImageBitmap;
//                image = mImageBitmap;
//            }
//
//            thumbnail = BitmapHelper.GetRotatedBitmap(thumbnail, BitmapHelper.GetExifOrientation(filePath));
//            image = BitmapHelper.GetRotatedBitmap(image, BitmapHelper.GetExifOrientation(filePath));
//
//            resultLoadImage(thumbnail, image);
//
//        }
//        // canceled
//        else if (resultCode == RESULT_CANCELED) {
//
//            canceledLoadImage();
//        }
//        // failed
//        else {
//
//            Log.d("stn", "failed : " + requestCode + ", " + resultCode);
//
//            failedLoadImage();
//
//        }
//    }
//}
