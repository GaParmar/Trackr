package com.gaparmar.traq;

import java.util.LinkedList;

/**
 * Created by rtmurase on 10/21/17.
 */

public class DataHolder{
    private LinkedList<String> names;
    private LinkedList <String> locList;
    private LinkedList <String> timeList;
    private LinkedList <Boolean> connectedList;

    private String phoneNumber;
    private String secretWord;

    public DataHolder() {
        phoneNumber = "0000000000";
        secretWord = "";
        names = new LinkedList<>();
        names.add( "Wallet" );
        names.add( "Backpack" );
        names.add( "Earphones" );
        names.add( "Wallet" );

        locList = new LinkedList<>();
        locList.add("0.0, 0.0");
        locList.add("0.0, 0.0");
        locList.add("0.0, 0.0");
        locList.add("0.0, 0.0");

        timeList = new LinkedList<>();
        timeList.add("00:00");
        timeList.add("00:00");
        timeList.add("00:00");
        timeList.add("00:00");

        connectedList = new LinkedList<>();
        connectedList.add(true);
        connectedList.add(true);
        connectedList.add(true);
        connectedList.add(true);
    }


    public LinkedList getNames(){ return names; }
    public LinkedList getlocList(){ return locList; }
    public LinkedList getTimeList(){ return timeList; }
    public LinkedList getConnectedList(){ return connectedList; }
    public String getPhoneNum(){ return phoneNumber; };
    public String getSecretWord(){ return secretWord; };


    public void addNew( String name, String loc, String time ){
        names.addFirst(name);
        names.removeLast();

        connectedList.addFirst(true);
        connectedList.removeLast();

        locList.addFirst(loc);
        locList.removeLast();

        timeList.addFirst(time);
        timeList.removeLast();
    }

    public void disconnected( int index, String time, String loc) {
        connectedList.set(index, false);
        timeList.set(index, time);
        locList.set(index, loc);
    }

    public void updateNumandWord( String newNum, String newWord ) {
        phoneNumber = newNum;
        secretWord = newWord;
    }

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}

}
