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
          items.add("RIGHT_THUMB");
          items.add("RIGHT_INDEX_FINGER");
          items.add("RIGHT_MIDDLE_FINGER");
          items.add("RIGHT_RING_FINGER");
          items.add("RIGHT_PINKY_FINGER");
      return items;
  }
  public static List<String> getLeftFinger(){

        List<String> items = new ArrayList<>();
        items.add("LEFT_PINKY_FINGER");
        items.add("LEFT_RING_FINGER");
        items.add("LEFT_MIDDLE_FINGER");
        items.add("LEFT_INDEX_FINGER");
        items.add("LEFT_THUMB");
        return items;
    }

}
