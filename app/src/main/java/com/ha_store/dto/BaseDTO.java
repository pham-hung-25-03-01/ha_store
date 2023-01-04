package com.ha_store.dto;

import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseDTO {
    protected String convert_big_decimal_to_date(BigDecimal big_decimal) throws ParseException {
        String s_date = big_decimal.toString();
        String result = "";
        if(s_date.length() < 8){
            s_date = "0" + s_date;
            result = s_date.substring(0, 2) + "/" + s_date.substring(2,4) + "/" + s_date.substring(4);
        }
        else{
            DateFormat d_format = new SimpleDateFormat("ddMMyyyy");
            Date date = d_format.parse(s_date);
            SimpleDateFormat s_format = new SimpleDateFormat("dd/MM/yyyy");
            result = s_format.format(date);
        }
        return result;
    }
    protected BigDecimal convert_date_to_big_decimal(Date date){
        SimpleDateFormat b_format = new SimpleDateFormat("ddMMyyyy");
        return new BigDecimal(b_format.format(date));
    }
    protected String generate_hashed_pass(String pass) {
        // hash a plaintext password using the typical log rounds (10)
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }
}
