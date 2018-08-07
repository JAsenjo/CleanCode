package com.example.framework.cleancode.helper;

import android.support.annotation.NonNull;
import android.util.Patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase de ayuda para validar campos.
 *
 * Created by javier.asenjo on 15/05/2017.
 */
public final class ValidationHelper {

    /**
     * Log de la aplicación.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ValidationHelper.class);

    /**
     *
     */
    private ValidationHelper() {
    }

    /**
     *
     * @param nifOrNie DNI, NIF o NIE a validar
     * @return true si es correcto
     */
    public static boolean isValidNifOrNie(@NonNull String nifOrNie) {

        boolean isValid = false;

        nifOrNie = nifOrNie.toUpperCase();

        //si es NIE, eliminar la x,y,z inicial para tratarlo como nif (a cada letra le corresponde un número para el cálculo del dígito de control)
        if (nifOrNie.startsWith("X")) {
            nifOrNie = "0" + nifOrNie.substring(1);
        } else if (nifOrNie.startsWith("Y")) {
            nifOrNie = "1" + nifOrNie.substring(1);
        } else if (nifOrNie.startsWith("Z")) {
            nifOrNie = "2" + nifOrNie.substring(1);
        }

        final Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKE])");

        final Matcher matcher = pattern.matcher(nifOrNie);

        if (matcher.matches()) {

            final String letter = matcher.group(2);
            final String alphabet = "TRWAGMYFPDXBNJZSQVHLCKE";
            int index = Integer.parseInt(matcher.group(1));

            index = index % 23;

            final String reference = alphabet.substring(index, index + 1);

            isValid = reference.equals(letter);

        }

        return isValid;

    }

    /**
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(final String email) {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    /**
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isValidPhoneNumber(final String phoneNumber) {

        final Pattern phonePattern = Pattern.compile("\\d{9}");
        boolean isValidPhone = false;

        if (phonePattern.matcher(phoneNumber).matches()) {
            isValidPhone = true;
        }

        return isValidPhone;

    }


    /**
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isValidMobilePhoneNumber(final String phoneNumber) {

        final Pattern phonePattern = Pattern.compile("[678]\\d{8}");
        boolean isValidPhone = false;

        if (phonePattern.matcher(phoneNumber).matches()) {
            isValidPhone = true;
        }

        return isValidPhone;

    }

        /**
         *
         * @param password
         * @param confirmPassword
         * @return
         */
    public static boolean validatePassword(final String password, final String confirmPassword) {

        return password.equals(confirmPassword);

    }

}
