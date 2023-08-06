package controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread{

    public static ArrayList<ClientHandler> clientHandlers=new ArrayList<>();
    private Socket socket;

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private  String username;

    public ClientHandler(Socket socket){
        try {
            this.socket=socket;
            this.bufferedWriter =new BufferedWriter( new OutputStreamWriter( socket.getOutputStream()));
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username=bufferedReader.readLine();
            clientHandlers.add(this);
            broadCastMsg(username + " has entered");
        } catch (IOException e) {
            closeEveryThing(socket,bufferedReader,bufferedWriter);
        }
    }

    @Override
    public void run() {
        String msgFormClient;

        while (socket.isConnected()){
            try{
                msgFormClient=bufferedReader.readLine();
                broadCastMsg(msgFormClient);
            }catch (IOException e){
                closeEveryThing(socket,bufferedReader,bufferedWriter);
                break;
            }
        }
    }

    private void broadCastMsg(String msgToSend) {
        for (ClientHandler clientHandler:clientHandlers){
            try{
                if (!clientHandler.username.equals(username)){
                    clientHandler.bufferedWriter.write(msgToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();

                }
            }catch (IOException e){
                closeEveryThing(socket,bufferedReader,bufferedWriter);
            }
        }
    }
 public  void removeClientHandler(){
        clientHandlers.remove(this);
        broadCastMsg(username+" has left");

 }
    private void closeEveryThing(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
        try{
            if (bufferedReader!=null){
                bufferedReader.close();
            }
            if (bufferedWriter!=null){
                bufferedWriter.close();
            }
            if (socket!=null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
