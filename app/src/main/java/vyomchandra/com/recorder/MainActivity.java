package vyomchandra.com.recorder;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    private static final int VIDEO_REQUEST_CODE = 100;
    private static final int AUDIO_REQUEST_CODE = 200;
    private static final int PICTURE_REQUEST_CODE = 300;
    int a = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   // String currentPhotoPath;

//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        currentPhotoPath = image.getAbsolutePath();
//        return image;
//    }

    public void camera(View view) {
        a = 3;
        Toast.makeText(this, "camera", Toast.LENGTH_SHORT).show();
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File image_file = getFile();
        Uri imageUri = Uri.fromFile(image_file);
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(camera_intent, 300);
    }

        public void audio (View view){
            a = 2;
            Toast.makeText(this, "audio", Toast.LENGTH_SHORT).show();
            // Intent audio_intent =new Intent(MediaPlayer.)

        }

        public void video (View view){
            a = 1;
            Toast.makeText(this, "video", Toast.LENGTH_SHORT).show();
            Intent video_intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File video_file = getFile();
            Uri videoUri = Uri.fromFile(video_file);
            video_intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
            video_intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(video_intent, 100);

        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            if (requestCode == VIDEO_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "video succesfully recorded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "video capture failed", Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode == AUDIO_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "image captured ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "image capture failed ", Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode == PICTURE_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "voice recorded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "voice recording failed ", Toast.LENGTH_SHORT).show();
                }
            }
        }

        public void save (View view){
            Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
        }
        public File getFile () {

            File folder = new File("SD card/recorder_app");
            if (!folder.exists()) {
                folder.mkdir();
            }
            File video_file = new File(folder, "sample_video.mp4");
            File audio_file = new File(folder, "sample_audio.mp3");
            File picture_file = new File(folder, "sample_picture.png");
            if (a == 1)
                return video_file;
            else if (a == 2)
                return audio_file;
            else if (a == 3)
                return picture_file;
            else
                return null;


        }
    }
