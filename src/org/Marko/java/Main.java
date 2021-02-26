package org.Marko.java;

import javax.crypto.spec.PSource;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Callable;


public class Main {

    private  static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id = 0;
    public static void main(String[] args) {

        contacts = new ArrayList<>();
        System.out.println("welcome to my app");
        showInitialOptions();

    }
    private static void showInitialOptions(){
        System.out.println("Please select one:" +
                "\n\t1 Manage Contacts" +
                "\n\t2 Messages" +
                "\n\t3 Quit");
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;
                
        }
    }

    private static void manageContacts() {
        System.out.println("Please select one:" +
                "\n\t1. Show all contacts" +
                "\n\t2. Add a new contact" +
                "\n\t3. Search for a contact" +
                "\n\t4. Delete a contact" +
                "\n\t5. Go Back");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllContacts();
                break;
            case 2:
                AddNewContact();
                break;
            case 3:
                SearchForContact();
                break;
            case 4:
                DeleteContact();
                break;
            default:
                showInitialOptions();
                break;

        }
    }

    private static void DeleteContact() {
        System.out.println("Please enter the name: ");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("please enter the name:");
            DeleteContact();
        }
        else {
            boolean doesExist = false;

            for (Contact c: contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                    contacts.remove(c);
                }
            }
            if (!doesExist){
                System.out.println("There is no such contact in your phone");
            }
        }
        showInitialOptions();
    }

    private static void SearchForContact() {
        System.out.println("Please enter the name: ");
        String name = scanner.next();
        if (name.equals("")) {
            System.out.println("Please enter the name");
            SearchForContact();
        } else {
            boolean doesExist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                    c.getDetails();
                }
            }
            if (!doesExist) {
                System.out.println("There is no such contact in your phone");
            }
        }
        showInitialOptions();
    }

    private static void AddNewContact() {
        System.out.println("Adding a new Contact..."+
                "\nPlease enter a new name");
        String name = scanner.next();

        System.out.println("Please enter a number");
        String number = scanner.next();

        System.out.println("Please enter a email");
        String email = scanner.next();

        if (name.equals("")|| number.equals("")|| email.equals("")){
            System.out.println("please enter all information");
            AddNewContact();
        }
        else {
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)) {
                    doesExist = true;


                }
            }
            if (doesExist){
                System.out.println("we have a contact named"+ name + " saved on this device");
            }
            else {
                Contact contact = new Contact(name, number, email) ;
                contacts.add(contact);
                System.out.println(name + " contact has added successfully " );

            }

        }
        showInitialOptions();
    }

    private static void showAllContacts() {
        for (Contact c: contacts){
            c.getDetails();
            System.out.println("****************************");
        }
        showInitialOptions();
    }


    private static void manageMessages() {
        System.out.println("Please select one: "+
                "\n\t1. Show all messages"+
                "\n\t2. Send a new message"+
                "\n\t3. Go back");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                ShowAllMessages();
                break;
            case 2:
                SendNewMessage();
            default:
                showInitialOptions();
                break;
        }
    }

    private static void SendNewMessage() {
        System.out.println("Who are u going to send a message?");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("please enter the name of the contact");
            SendNewMessage();
        } else {
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;

                }
            }
            if (doesExist){
                System.out.println("what are you going to say?");
                String text = scanner.next();
                if (text.equals("")){
                    System.out.println("please enter a message");
                    SendNewMessage();
                }else {
                    id++;
                    Messages newMessage = new Messages(text, name, id);
                    for (Contact c: contacts){
                        if(c.getName().equals(name)){
                            ArrayList<Messages> newMessages = c.getMessages();
                            newMessages.add(newMessage);
                           c.setMessages(newMessages);
                        }
                    }
                    showInitialOptions();
                }
            }else {
                System.out.println("there is no such contact");
            }
        }
    }

    private static void ShowAllMessages() {
        ArrayList<Messages> allMessages = new ArrayList<>();
        for (Contact c: contacts){
            allMessages.addAll(c.getMessages());
        }
        if (allMessages.size()>0){
              for (Messages m: allMessages){
              m.getDetails();
            System.out.println("******************************");

            }
        }
        else {
            System.out.println("You don't have any message");
             }
        showInitialOptions();
    }
}
