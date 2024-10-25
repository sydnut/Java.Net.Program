package com.testClient.client;
import com.testClient.service.ClientService;
import com.testClient.service.MessageService;

import java.io.IOException;
import java.util.Scanner;

public class ClientView {
    private boolean loop=true;
    private String key;//接受用户输入信息
    private ClientService clientService=new ClientService();
    private MessageService messageService=new MessageService();
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        new ClientView().mainMenu();
        System.out.println("now is quitting the system");
    }
    private void mainMenu() throws IOException, ClassNotFoundException, InterruptedException {
        while(loop){
            System.out.println("Welcome to the QQ system,try to input a number");
            System.out.println("\t\t1 : login");
            System.out.println("\t\t9 : quit");
            System.out.println("=========crossline========");
            Scanner scan=new Scanner(System.in);
            key=scan.next("[1-9]");
            switch (key){
                case "1":
                    System.out.println("Welcome to login!");
                    System.out.println("input     your   id:");
                    String userid=scan.next();
                    System.out.println("input your password:");
                    String pw=scan.next();
                    if(clientService.checkUser(userid,pw)){
                        System.out.println("Welcome to the QQ system,St "+userid);
                        while(loop){
                            Thread.sleep(1000);

                            System.out.println("\n=============St "+userid+"=============");
                            System.out.println("\t\t1 show the live User list");
                            System.out.println("\t\t2 group chatting(input 2 and your message between a blank)");
                            System.out.println("\t\t3 individual chatting");
                            System.out.println("\t\t4 file transporting");
                            System.out.println("\t\t9 : quit");
                            key=scan.next("[1-9]");
                            switch (key){
                                case "1":
                                    System.out.println("\t\tshow the live User list");
                                    clientService.showOnlineList();
                                    break;
                                case "2":
                                    System.out.println("\t\tgroup chatting");
                                    System.out.println("input your message in a line");
                                    String contents=scan.nextLine();
                                    messageService.sendMsgToAll(userid,contents);
                                    System.out.println("launch success!");
                                    break;
                                case "3":
                                    System.out.println("\t\tindividual chatting");
                                    System.out.println("\t\tinput your friend id and your message between a blank:");
                                    System.in.read();
                                    String id=scan.next();
                                    String content=scan.nextLine();
                                    messageService.sendMsgToOne(userid,id,content);
                                    System.out.println("launch success!");
                                    break;
                                case "9":
                                    clientService.quit();
                                    loop=false;
                                    break;
                            }
                        }

                    }else {
                        System.out.println("login error!!!");
                    }
                    break;
                case "9":
                    loop=false;
                    break;
            }
        }


    }
}

