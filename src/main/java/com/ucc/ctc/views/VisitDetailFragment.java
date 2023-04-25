package com.ucc.ctc.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ucc.ctc.R;


public class VisitDetailFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_visit_detail, container, false );
    }
    // Check if we have permission to write to external storage
					/*if (ContextCompat.checkSelfPermission(FingerClientActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
						// If we don't have permission, request it
						 ActivityCompat.requestPermissions(FingerClientActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
					} else {
						// If we already have permission, do something
						createDirectory();
					}
					try {
						//String dirPath = "/sdcard/templates"; // specify the path for the directory
						//File directory = new File(dirPath);
						//Environment.getExternalStorageDirectory()+"/templates"
						//getExternalStorageDirectory()
						//context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
						//File directory = new File(Environment.getExternalStorageDirectory()+"/templates");
						boolean result=false;
						/*if (!directory.exists()) {
                        result = directory.mkdir(); // create the directory and its parent directories if they don't exist
						}
						File directory = new File(getExternalFilesDir(null), "fingers");
						if (!directory.exists()) {
							result=directory.mkdir();
						}else{
							result=true;
						}
						String fileName = "template_"+fingerId+""+clientId+".dat";
						File file = new File( directory, fileName );
						    if(result) {
								try (FileOutputStream fos = new FileOutputStream( file )) {
									fos.write( nFTemplate );
								}
								Log.d("MyActivity", "Created ok"+directory);
							}else{
								Log.d("MyActivity", "Director Not Created"+directory);
							}
                  //Toast.makeText(FingerClientActivity.this, "Template saved to " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						e.printStackTrace();
						Log.d("MyActivity", "error");
					}*/
}