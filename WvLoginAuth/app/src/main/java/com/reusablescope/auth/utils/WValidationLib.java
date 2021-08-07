package com.reusablescope.auth.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class WValidationLib {
    /*
     * Regular Expression
     */
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_REGEX = "^.{6,}$";
    private static final String USERNAME_REGEX = "^(?=.*\\S).*";
    private static final String FULL_NAME = "[\\p{L}- ]+";
    private static final String VALID_URL_REGEX = "(((f|ht){1}tp|tps:[//])[-a-zA-Z0-9@:%_\\+.~#?&//=]+)";
    private static final String ALPHANUMERIC = "[a-zA-Z0-9\\u00C0-\\u00FF \\\\./-\\\\?]*";
    private static final String ALPHA = "^.{1,}$";
    private static final String PHONE_REGEX = "[0-9]{10}$";
    private static final String US_ZIP_REGEX = "^.{1,6}$";


    /**
     * @param editTextPassword        password edittext
     * @param editTextConfirmPassword confirm password edittext
     * @param required                pass boolean to check if the following validation is required
     * @return true when all validations are success else false
     */
    public static boolean isConfirmPasswordValidation(TextInputLayout textInputLayoutPassword, EditText editTextPassword,
                                                      TextInputLayout textInputLayoutConfirmPassword,
                                                      EditText editTextConfirmPassword, String requireMsg, String requireConfMsg, String errorMsg, String notMatchMsg,
                                                      boolean required) {
        if (isPassword(textInputLayoutPassword, editTextPassword, requireMsg, errorMsg, required)) {
            if (isPassword(textInputLayoutConfirmPassword, editTextConfirmPassword, requireConfMsg, errorMsg, required)) {
                return isPasswordEqual(textInputLayoutPassword, textInputLayoutConfirmPassword, editTextPassword, editTextConfirmPassword, requireMsg, notMatchMsg);
            }
        }
        return false;
    }

    /**
     * Check if email id valid.
     *
     * @param editText the edit text
     * @param required the required
     * @return the boolean
     */
    public static boolean isEmailAddress(TextInputLayout textInputLayout, EditText editText, String requireMsg, String errorMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isValid(textInputLayout, editText, EMAIL_REGEX, requireMsg, errorMsg, required);
    }

    /**
     * Check if user name is valid.
     *
     * @param editText the edit text
     * @param required the required
     * @return the boolean
     */
    public static boolean isUserName(TextInputLayout textInputLayout, EditText editText, String requireMsg, String lengthError, String errorMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isValid(textInputLayout, editText, USERNAME_REGEX, requireMsg, errorMsg, lengthError, required);
    }

    /**
     * Call this method when you need to check password validation for minimum length is 6 character with lowercase letter,uppercase letters,numbers and special characters
     *
     * @param editText the edit text
     * @param required the required
     * @return the boolean
     */
    public static boolean isPassword(TextInputLayout textInputLayout, EditText editText, String requireMsg, String errorMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isValid(textInputLayout, editText, PASSWORD_REGEX, requireMsg, errorMsg, required);
    }

    /**
     * Check if full name is valid.
     *
     * @param editText the edit text
     * @param required the required
     * @return the boolean
     */
    public static boolean isFullName(TextInputLayout textInputLayout, EditText editText, String requireMsg, String errorMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isValid(textInputLayout, editText, FULL_NAME, requireMsg, errorMsg, required);
    }

    /**
     * Check if only alpha numeric values are entered.
     *
     * @param editText the edit text
     * @param required the required
     * @return the boolean
     */
    public static boolean isAlphaNumeric(TextInputLayout textInputLayout, EditText editText, String requireMsg, String errorMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isValid(textInputLayout, editText, ALPHANUMERIC, requireMsg, errorMsg, required);
    }

    /**
     * Check if only alpha numeric values are entered.
     *
     * @param editText the edit text
     * @param required the required
     * @return the boolean
     */
    public static boolean isValidPhone(TextInputLayout textInputLayout, EditText editText, String requireMsg, String errorMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isValid(textInputLayout, editText, PHONE_REGEX, requireMsg, errorMsg, required);
    }

    /**
     * Check if only alphabetic values are entered.
     *
     * @param editText the edit text
     * @param required the required
     * @return the boolean
     */
    public static boolean isAlphabetic(TextInputLayout textInputLayout, EditText editText, String requireMsg, String errorMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isValid(textInputLayout, editText, ALPHA, requireMsg, errorMsg, required);
    }

    public static boolean isUsZipCode(TextInputLayout textInputLayout, EditText editText, String requireMsg, String errorMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isValid(textInputLayout, editText, US_ZIP_REGEX, requireMsg, errorMsg, required);
    }

    /**
     * Check if Url is valid url
     *
     * @param editText the edit text
     * @param required the required
     * @return the boolean
     */
    public static boolean isValidUrl(TextInputLayout textInputLayout, EditText editText, String requireMsg, String errorMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isValid(textInputLayout, editText, VALID_URL_REGEX, requireMsg, errorMsg, required);
    }

    /**
     * Check if edittext is empty
     *
     * @param editText edittext to check validation for
     * @param required pass boolean to check if the following validation is required
     * @return true when all validations are success else false
     */
    public static boolean isEmpty(TextInputLayout textInputLayout, EditText editText, String requireMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isEmptyValid(textInputLayout, editText, requireMsg, required);
    }

    public static boolean errorForSpinner(String key, String errorMsg, TextView errorTextView) {
        if (key.equals("")) {
            errorTextView.setText(errorMsg);
            errorTextView.setVisibility(View.VISIBLE);
            return false;
        } else {
            errorTextView.setVisibility(View.GONE);
            return true;
        }
    }

    public static boolean errorForCheckBox(boolean selected, String errorMsg, TextView errorTextView) {
        if (!selected) {
            errorTextView.setText(errorMsg);
            errorTextView.setVisibility(View.VISIBLE);
            return false;
        } else {
            errorTextView.setVisibility(View.GONE);
            return true;
        }
    }

    /**
     * Method to check if password entered & confirm password is equal
     *
     * @param editText  editext of new password
     * @param editText1 edittext of confirm password
     * @return if equal return true else false
     */
    public static boolean isPasswordEqual(TextInputLayout textInputLayout, TextInputLayout textInputLayoutConfirmPassword, EditText editText, EditText editText1, String requireMsg, String notMatchMsg) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isEqual(textInputLayout, editText, textInputLayoutConfirmPassword, editText1, requireMsg, notMatchMsg);
    }

    /**
     * Check if only numeric values are entered.
     *
     * @param editText the edit text
     * @param required the required
     * @return the boolean
     */
    public static boolean isValidNumeric(TextInputLayout textInputLayout, EditText editText, String requireMsg, String errorMsg, boolean required) {
        WValidationLib v_lib = new WValidationLib();
        return v_lib.isValidNumber(textInputLayout, editText, requireMsg, errorMsg, required);
    }

    /**
     * Is valid boolean.
     *
     * @param editText the edit text
     * @param regex    the regex
     * @param errMsg   the err msg
     * @param required the required
     * @return the boolean
     */
    public boolean isValid(TextInputLayout textInputLayout, EditText editText, String regex, String requireMsg, String errMsg, boolean required) {

        String text = editText.getText().toString();

        if (required && !hasText(textInputLayout, editText, requireMsg)) {
            return false;
        }
        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            if (!textInputLayout.isErrorEnabled()) {
                textInputLayout.setErrorEnabled(true);
            }
            if (textInputLayout.getError() == null || !textInputLayout.getError().toString().equals(errMsg)) {
                textInputLayout.setError(errMsg);
            }
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    public boolean isValid(TextInputLayout textInputLayout, EditText editText, String regex, String requireMsg, String errMsg, String lengthError, boolean required) {

        String text = editText.getText().toString().trim();

        if (required && text.length() < 2) {
            if (!textInputLayout.isErrorEnabled()) {
                textInputLayout.setErrorEnabled(true);
            }
            if (textInputLayout.getError() == null || !textInputLayout.getError().toString().equals(lengthError)) {
                textInputLayout.setError(lengthError);
            }
            return false;
        }
        if (required && !hasText(textInputLayout, editText, requireMsg)) {
            return false;
        }
        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            if (!textInputLayout.isErrorEnabled()) {
                textInputLayout.setErrorEnabled(true);
            }
            if (textInputLayout.getError() == null || !textInputLayout.getError().toString().equals(errMsg)) {
                textInputLayout.setError(errMsg);
            }
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    /**
     * Has text boolean.
     *
     * @param editText the edit text
     * @return the boolean
     */
    // check the input field has any text or not
    // return true if it contains text otherwise false
    private boolean hasText(TextInputLayout textInputLayout, TextView editText, String requireMsg) {

        String text = editText.getText().toString().trim();

        if (text.length() == 0) {
            if (!textInputLayout.isErrorEnabled()) {
                textInputLayout.setErrorEnabled(true);
            }
            if (textInputLayout.getError() == null || !textInputLayout.getError().toString().equals(requireMsg)) {
                textInputLayout.setError(requireMsg);
            }
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        return true;
    }


    /**
     * @param textInputLayout
     * @param editText
     * @param textInputLayout1
     * @param editText1
     * @param requireMsg
     * @param noMatchMsg
     * @return
     */
    // check the input field has any text or not
    // return true if it contains text otherwise false
    private boolean isEqual(TextInputLayout textInputLayout, EditText editText, TextInputLayout textInputLayout1, EditText editText1, String requireMsg, String noMatchMsg) {
        String text = editText.getText().toString().trim();
        String text1 = editText1.getText().toString().trim();

        if (text.length() == 0) {
            if (textInputLayout.getError() == null || !textInputLayout.getError().toString().equals(requireMsg)) {
                textInputLayout.setError(requireMsg);
            }
            return false;
        } else if (text1.length() == 0) {
            if (textInputLayout1.getError() == null || !textInputLayout1.getError().toString().equals(requireMsg)) {
                textInputLayout1.setError(requireMsg);
            }
            return false;
        } else if (!text.equals(text1)) {
            textInputLayout1.setError(noMatchMsg);
            if (textInputLayout1.getError() == null || !textInputLayout1.getError().toString().equals(noMatchMsg)) {
                textInputLayout1.setError(noMatchMsg);
            }
            return false;
        }
        return true;
    }

    /**
     * Is valid boolean.
     *
     * @param editText the edit text
     * @param required the required
     * @return the boolean
     */
    public boolean isEmptyValid(TextInputLayout textInputLayout, EditText editText, String requireMsg, boolean required) {

        String text = editText.getText().toString().trim();
       if (required && !hasText(textInputLayout, editText, requireMsg)) {
            return false;
        }
        return true;
    }

    /**
     * Is valid number boolean.
     *
     * @param et       the et
     * @param errMsg   the err msg
     * @param required the required
     * @return the boolean
     */
    // check the input field has any digits or not
    // return true if it contains digits otherwise false
    public boolean isValidNumber(TextInputLayout textInputLayout, EditText et, String requireMsg, String errMsg, boolean required) {
        String text = et.getText().toString().trim();
       if (required && !hasText(textInputLayout, et, requireMsg)) {
            return false;
        }
        // pattern doesn't match so returning false
        if (required && !TextUtils.isDigitsOnly(text)) {
            if (textInputLayout.getError() == null || !textInputLayout.getError().toString().equals(errMsg)) {
                textInputLayout.setError(errMsg);
            }
            return false;
        }
        if (text.length() < 6) {
            if (textInputLayout.getError() == null || !textInputLayout.getError().toString().equals(errMsg)) {
                textInputLayout.setError(errMsg);
            }
            return false;
        }
        return true;
    }
}
