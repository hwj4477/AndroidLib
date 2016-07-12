//package com.hwj4477.androidlib.s3;
//
//import android.content.Context;
//import android.content.ContextWrapper;
//import android.os.AsyncTask;
//import android.telephony.TelephonyManager;
//
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Region;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//
//import java.util.UUID;
//
//public class S3UploadManager {
//    private static Context _context = null;
//
//    private static S3UploadManager _manager = null;
//    private static AmazonS3Client s3Client = null;
//
//    private static UploadListener _listener = null;
//
//    public static final String ACCESS_KEY_ID = "AKIAJFHUPRFFXGDTWT7Q";
//    public static final String SECRET_KEY = "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";
//
//    public static final String BUCKET_NAME = "gawewang-chat-img";
//
//    private String imageFilePath = null;
//    public String imageFileName = null;
//
//    private S3UploadManager() {
//        s3Client = new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_KEY));
//        s3Client.setRegion(Region.getRegion(Regions.AP_NORTHEAST_2));
//    }
//
//    public synchronized static S3UploadManager getInstance(Context context) {
//        if (_manager == null) {
//            _context = context;
//            _manager = new S3UploadManager();
//        }
//
//        return _manager;
//    }
//
//    public void putObjectTask(String imageFilePath, String roomId, UploadListener listener) {
//        imageFileName = generateImageFilename(roomId);
//        this.imageFilePath = imageFilePath;
//
//        _listener = listener;
//        new S3PutObjectTask().execute();
//    }
//
//    private class S3PutObjectTask extends AsyncTask<Void, Void, S3TaskResult> {
//        protected void onPreExecute() {
//            // do nothing
//        }
//
//        protected S3TaskResult doInBackground(Void... params) {
//            S3TaskResult result = new S3TaskResult();
//
//            if (imageFilePath == null) {
//                result.setErrorMessage("imageFilePath is null");
//
//                return result;
//            }
//            try {
//                // upload image
//                PutObjectRequest por = new PutObjectRequest(BUCKET_NAME, imageFileName, new java.io.File(imageFilePath));
//                por.setCannedAcl(CannedAccessControlList.PublicRead);
//
//                s3Client.putObject(por);
//            } catch (Exception exception) {
//                result.setErrorMessage(exception.getMessage());
//            }
//
//            return result;
//        }
//
//        protected void onPostExecute(S3TaskResult result) {
//            if (result.getErrorMessage() != null) {
//                _listener.uploadFailed();
//            } else {
//                _listener.returnUploadURL(s3Client.getResourceUrl(BUCKET_NAME, imageFileName));
//            }
//        }
//    }
//
//    private String generateImageFilename(String roomId) {
//        String saveTime = String.valueOf(System.currentTimeMillis());
//        String deviceID = makeUniqueID();
//
//        return roomId + "_pic_" + saveTime + "_" + deviceID + ".jpg";
//    }
//
//    private String makeUniqueID() {
//        final TelephonyManager tm = (TelephonyManager) ((ContextWrapper) _context).getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
//
//        final String tmDevice, tmSerial, androidId;
//        tmDevice = "" + tm.getDeviceId();
//        tmSerial = "" + tm.getSimSerialNumber();
//        androidId = "" + android.provider.Settings.Secure.getString(_context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
//
//        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
//        String deviceId = deviceUuid.toString();
//
//        return deviceId;
//    }
//
//    private class S3TaskResult {
//        String errorMessage = null;
//
//        public String getErrorMessage() {
//            return errorMessage;
//        }
//
//        public void setErrorMessage(String errorMessage) {
//            this.errorMessage = errorMessage;
//        }
//    }
//
//    public interface UploadListener {
//        void returnUploadURL(String imageUrl);
//
//        void uploadFailed();
//    }
//}
