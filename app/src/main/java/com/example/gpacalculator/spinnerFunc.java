package com.example.gpacalculator;

import android.widget.Spinner;
import android.widget.TextView;

public class spinnerFunc {

    public int getTotal(Spinner spinner1, Spinner spinner2) {
            String str = String.valueOf(spinner1.getSelectedItem());
            int int1 = Integer.parseInt(str);

            String str1 = String.valueOf(spinner2.getSelectedItem());
            int i = getGrade(str1);

            int total = int1 * i;
            return total;
    }
    public int getGrade(String str2){
        if(str2.equals("S")){
            return 10;
        }
        else if(str2.equals("A")){
            return 9;
        }
        else if(str2.equals("B")){
            return 8;
        }
        else if(str2.equals("C")){
            return 7;
        }
        else if(str2.equals("D")){
            return 6;
        }
        else if(str2.equals("E")){
            return 5;
        }

        else{
            return 0;
        }
    }


}
