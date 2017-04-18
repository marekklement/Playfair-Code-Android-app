package com.example.kleme.plaifaircode;

import android.text.Editable;

import static java.lang.System.*;

/**
 * Created by kleme on 14.04.2017.
 */

public class Functions {
    MainClass design;
    //String output = "";
    String pasword;
    String table = "abcde"
            + "fghik"
            + "lmnop"
            + "qrstu"
            + "vwxyz";

    //this is main of functions, its called when submit pressed
    public void GetPassword(Editable password, MainClass design){
        this.design = design;
        out.println(password);
        this.pasword = password.toString();
        this.pasword = this.pasword.toLowerCase();
        out.println(pasword);
        SetCorrectPasword();
        SetNewTable();
        //Decode("ahoj");
    }
    //this function check for same letters in given password
    public void SetCorrectPasword(){
        String correctPasw = "";
        pasword = getJ(pasword);
        for (int i = 0; i < pasword.length(); i++) {
            for (int j = 0; j < pasword.length(); j++) {
                if(pasword.charAt(i)==' ')break;
                if(pasword.charAt(i)==pasword.charAt(j)){
                    if(i==j){
                        if(j==pasword.length()-1){
                            correctPasw+=pasword.charAt(i);
                            out.println("End Adding same, but last");
                            out.println(correctPasw);
                            break;
                        }
                        out.println("Same letter");
                        continue;
                    }else if (i>j){
                        out.println("Already should be there");
                        break;
                    }else if(j==pasword.length()-1){
                        correctPasw+=pasword.charAt(i);
                        out.println("Reach the end, match, but not there");
                        out.println(correctPasw);

                    }else{
                        out.println("Same but not there, continue");
                    }

                }else{
                    if(j==pasword.length()-1){
                        out.println("Came to the end and not match so adding");
                        out.println(correctPasw);
                        correctPasw+=pasword.charAt(i);
                    }else{
                        out.println("Nothing");
                    }
                }
            }
        }
        out.println(correctPasw);
        pasword=correctPasw;
    }
    //this function remove all j & spaces
    public String getJ(String string){
        String newpswd = "";
        for (int i = 0; i < string.length(); i++) {
            if(string.charAt(i)=='j'){
                newpswd+='i';
            }else{
                if(string.charAt(i)==' ' || string.charAt(i)=='1' || string.charAt(i)=='2' || string.charAt(i)=='3' || string.charAt(i)=='4' || string.charAt(i)=='5' || string.charAt(i)=='6' || string.charAt(i)=='7' || string.charAt(i)=='8' || string.charAt(i)=='9' || string.charAt(i)=='0'){
                    continue;
                }else{
                    newpswd+=string.charAt(i);
                }
            }
        }
        return newpswd;
    }
    //this function set a new table by given pasword
    public void SetNewTable(){
        String newtable = "";
        newtable+=pasword;
        for (int i = 0; i < table.length(); i++) {
            for (int j = 0; j < pasword.length(); j++) {
                if(table.charAt(i)==pasword.charAt(j)){
                    break;
                }else if (j==pasword.length()-1){
                    newtable+=table.charAt(i);
                }
            }
        }
        out.println(newtable);
        table = newtable;
    }
    //heart of the program...this is coding the message

    public String Decode(String message){
        String output = "Prelozeno: ";
        message = getJ(message);
        if(message.length()%2==1){
            message+='x';
        }
        for (int i = 0; i < message.length(); i+=2) {
            int pozicePrvni = getCharPosition(message.charAt(i));
            int poziceDruha = getCharPosition(message.charAt(i+1));
            if(poziceDruha==pozicePrvni){
                //pokud jsou stejne, tak pouze ponechame ... v sifre se ma ovsem vlozit mezi pismenka x
                output+=table.charAt(pozicePrvni);
                output+=table.charAt(poziceDruha);
            }else if(pozicePrvni%5 == poziceDruha%5){
                if(pozicePrvni+5>24){
                    output+=table.charAt((pozicePrvni+5)%5);
                }else{
                    output+=table.charAt(pozicePrvni+5);
                }
                if(poziceDruha+5>24){
                    output+=table.charAt((poziceDruha+5)%5);
                }else{
                    output+=table.charAt(poziceDruha+5);
                }

            }else if(areFromSameInterval(pozicePrvni, poziceDruha)){
                //pokud jsou na stejnem radku
                if((pozicePrvni+1)%5==0){
                    output+=table.charAt(pozicePrvni-4);
                }else{
                    output+=table.charAt(pozicePrvni+1);
                }
                if((poziceDruha+1)%5==0){
                    output+=table.charAt(poziceDruha-4);
                }else{
                    output+=table.charAt(poziceDruha+1);
                }
            }else{
                //pokud jsou cikcak
                if (poziceDruha%5>pozicePrvni%5){
                    output+=table.charAt(pozicePrvni+((poziceDruha%5)-(pozicePrvni%5)));
                    output+=table.charAt(poziceDruha-((poziceDruha%5)-(pozicePrvni%5)));
                }else{
                    output+=table.charAt(pozicePrvni-((pozicePrvni%5)-(poziceDruha%5)));
                    output+=table.charAt(poziceDruha+((pozicePrvni%5)-(poziceDruha%5)));
                }
            }
        }

        out.println(output);
        //trans.setText(output);
        return output;

    }

    public String unDecode(String message){
        String output = "Vase puvodni zprava: ";
        message = getJ(message);
        if(message.length()%2==1){
            message+='x';
        }
        for (int i = 0; i < message.length(); i+=2) {
            int pozicePrvni = getCharPosition(message.charAt(i));
            int poziceDruha = getCharPosition(message.charAt(i+1));
            if(poziceDruha==pozicePrvni){
                output+=table.charAt(pozicePrvni);
                output+=table.charAt(poziceDruha);
            }else if(pozicePrvni%5 == poziceDruha%5){
                //pokud jsou ve stejnem sloupci
                if(pozicePrvni-5<0){
                    output+=table.charAt(25+(pozicePrvni-5));
                }else{
                    output+=table.charAt(pozicePrvni-5);
                }
                if(poziceDruha-5<0){
                    output+=table.charAt(25+(poziceDruha-5));
                }else{
                    output+=table.charAt(poziceDruha-5);
                }

            }else if(areFromSameInterval(pozicePrvni, poziceDruha)){
                //pokud jsou na stejnem radku
                if((pozicePrvni-1)%5==4 || pozicePrvni-1<0){
                    output+=table.charAt(pozicePrvni+4);
                }else{
                    output+=table.charAt(pozicePrvni-1);
                }
                if((poziceDruha-1)%5==4 || poziceDruha-1<0){
                    output+=table.charAt(poziceDruha+4);
                }else{
                    output+=table.charAt(poziceDruha-1);
                }
            }else{
                //pokud jsou cikcak
                if (poziceDruha%5>pozicePrvni%5){
                    output+=table.charAt(pozicePrvni+((poziceDruha%5)-(pozicePrvni%5)));
                    output+=table.charAt(poziceDruha-((poziceDruha%5)-(pozicePrvni%5)));
                }else{
                    output+=table.charAt(pozicePrvni-((pozicePrvni%5)-(poziceDruha%5)));
                    output+=table.charAt(poziceDruha+((pozicePrvni%5)-(poziceDruha%5)));
                }
            }
        }
        out.println(output);
        //design.resultLabel2.setText(output);
        return  output;
    }

    //function to find out, if it is same line
    public boolean areFromSameInterval(int one,int two){
        if(one >= 0 && one <=4 && two >=0 && two<=4){
            return true;
        }else if (one >= 5 && one <=9 && two >=5 && two<=9){
            return true;
        }else if (one >= 10 && one <=14 && two >=10 && two<=14){
            return true;
        }else if (one >= 15 && one <=19 && two >=15 && two<=19){
            return true;
        }else if (one >= 20 && one <=24 && two >=20 && two<=24){
            return true;
        }else{
            return false;
        }
    }
    //function to remove old pasword and table
    public void getBack(){
        table = "abcde"
                + "fghik"
                + "lmnop"
                + "qrstu"
                + "vwxyz";
        pasword="";
        //design.getBackDesign(design.homepage);
    }
    //function to get position of char in table
    public int getCharPosition(char letter){
        int position=-1;
        for (int i = 0; i < table.length(); i++) {
            if(table.charAt(i)== letter){
                position = i;
            }
        }
        return position;
    }
}
