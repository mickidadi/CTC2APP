package com.ucc.ctc.utils;

import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Util {
    private ImageView handImage;
    public String StringJoin(List<String> stringList, String delimeter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            sb.append(stringList.get(i));
            if (i != stringList.size() - 1) {
                sb.append(delimeter);
            }
        }

        return sb.toString();
    }
  public static  int getIndex(Spinner spinner,String value){
        //use for loop
      for (int i = 0; i < spinner.getCount(); i++) {
          //check condition
          if(spinner.getItemAtPosition( i ).toString().equalsIgnoreCase(value)){
               //when value
              return i;
          }

      }
      return 0;
  }
  public static List<String> getRightFinger(){
    List<String> items = new ArrayList<>();
          items.add( String.valueOf( Finger.RIGHT_THUMB ) );
          items.add(String.valueOf( Finger.RIGHT_INDEX_FINGER));
          items.add(String.valueOf( Finger.RIGHT_MIDDLE_FINGER));
          items.add(String.valueOf( Finger.RIGHT_RING_FINGER));
          items.add(String.valueOf( Finger.RIGHT_LITTLE_FINGER));
      return items;
  }
  public static List<String> getLeftFinger(){

        List<String> items = new ArrayList<>();
        items.add( String.valueOf( Finger.LEFT_LITTLE_FINGER ) );
        items.add( String.valueOf( Finger.LEFT_RING_FINGER ) );
        items.add( String.valueOf( Finger.LEFT_MIDDLE_FINGER ) );
        items.add( String.valueOf( Finger.LEFT_INDEX_FINGER ) );
        items.add( String.valueOf( Finger.LEFT_THUMB ) );
        return items;
    }
    public static boolean isValidIPAddress(String ipAddress) {
        String pattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return ipAddress.matches(pattern);
    }
}
