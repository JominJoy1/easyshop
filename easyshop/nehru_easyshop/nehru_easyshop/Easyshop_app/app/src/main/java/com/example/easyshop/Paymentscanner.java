package com.example.easyshop;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Paymentscanner extends AppCompatActivity {

    public static String aa;

    private SurfaceView cameraPreview;

    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentscanner);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        cameraPreview = findViewById(R.id.camera_preview);

        // Set the desired width and height for the camera preview
        int desiredWidth = 10; // Set your desired width in pixels
        int desiredHeight = 20; // Set your desired height in pixels

        ViewGroup.LayoutParams layoutParams = cameraPreview.getLayoutParams();
        layoutParams.width = desiredWidth;
        layoutParams.height = desiredHeight;
        cameraPreview.setLayoutParams(layoutParams);

        // Initialize the QR code scanner
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Scan a QR Code");
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
    }

    // onActivityResult method should be defined outside of onCreate

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getApplicationContext(), "no value", Toast.LENGTH_SHORT).show();
            } else {
                String scannedData = result.getContents();
                SharedPreferences.Editor e = sh.edit();
                e.putString("res",scannedData);
                e.commit();
                aa=scannedData;
                // Use the scanned data as needed
                Toast.makeText(getApplicationContext(), "result" + sh.getString("res","") , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Payment.class));


            }
        }
    }
}
