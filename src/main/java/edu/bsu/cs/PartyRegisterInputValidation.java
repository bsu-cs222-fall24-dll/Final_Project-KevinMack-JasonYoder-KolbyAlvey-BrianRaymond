package edu.bsu.cs;

public class PartyRegisterInputValidation {

    public String formatPhoneNumber(String phoneNumber) {
        String cleanPhoneNumber = phoneNumber.replaceAll("\\D", "");
        if (cleanPhoneNumber.isEmpty()) return "";
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < cleanPhoneNumber.length(); i++) {
            if (i == 3 || i == 6) {
                formatted.append("-");
            }
            formatted.append(cleanPhoneNumber.charAt(i));
        }
        return formatted.toString();
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        String cleanPhoneNumber = phoneNumber.replaceAll("\\D", "");

        return cleanPhoneNumber.isEmpty() || cleanPhoneNumber.length() == 10;
    }

    public boolean isNotInboundInteger(int number) {
        return number > 60;
    }

    public boolean isNotRealNumber(String textBoxValue) {
        try {
            Integer.parseInt(textBoxValue);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

}
