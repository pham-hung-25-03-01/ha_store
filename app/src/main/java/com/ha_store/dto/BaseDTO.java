package com.ha_store.dto;

import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseDTO {
    protected String convert_big_decimal_to_date(BigDecimal big_decimal) throws ParseException {
        DateFormat d_format = new SimpleDateFormat("ddMMyyyy");
        Date date = d_format.parse(big_decimal.toString());
        SimpleDateFormat s_format = new SimpleDateFormat("dd/MM/yyyy");
        return s_format.format(date);
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
