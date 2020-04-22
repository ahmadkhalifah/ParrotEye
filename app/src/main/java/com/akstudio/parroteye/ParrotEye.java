package com.akstudio.parroteye;

import twitter4j.Status;

@SuppressWarnings("deprecation")
public class ParrotEye {

    public ParrotEye() {
        // TODO Auto-generated constructor stub
    }

    public String parrotEye(Status status) {
        String item = "";
        try {
            //System.out.println("++++++++++++++++++++ITME++++++++++++++++++++");
            item = status.getUser().getName() + "\t" +
                    "@" + status.getUser().getScreenName() + "\n" +
                    status.getText() + "\n" +
                    status.getCreatedAt().toString();
                    //"ID:\t\t" + "\n" + status.getId() + "\n";

            //System.out.println(item);
            //System.out.println("++++++++++++++++++++ITEM++++++++++++++++++++");

        } catch (Exception ex) {
            ex.printStackTrace();
            item = "error";
        }
        return item;
    }
}