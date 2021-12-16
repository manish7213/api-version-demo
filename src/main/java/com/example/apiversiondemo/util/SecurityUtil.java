package com.example.apiversiondemo.util;

import org.owasp.esapi.ESAPI;

public class SecurityUtil {

    public static String cleanString(String string) {

        String cleanString = ESAPI.encoder().encodeForHTML(string);

        return cleanString;
    }
}
