/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.utils;

/**
 *
 * @author Acer
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    // Twilio account credentials
    public static final String ACCOUNT_SID = "AC61ecc50f852fe2669f214e1cc207371b";
    public static final String AUTH_TOKEN = "61ae8e22a7a92f829219a805f089f065";
    // Twilio phone number
    public static final String TWILIO_PHONE_NUMBER = "+16076083486"; // replace with your Twilio phone number

    // Initialize the Twilio API client with your account credentials
    public SmsSender() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    // Send an SMS message
    public void sendSms(String recipientPhoneNumber, String messageText) {
        Message message = Message.creator(
                new PhoneNumber(recipientPhoneNumber),
                new PhoneNumber(TWILIO_PHONE_NUMBER),
                messageText
        ).create();

        System.out.println("Message SID: " + message.getSid());
    }
}