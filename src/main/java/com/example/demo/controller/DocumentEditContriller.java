package com.example.demo.controller;

import com.example.demo.entity.Document;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@ServerEndpoint("/websocket/{filename}")
public class DocumentEditContriller {
    /**
     * 与某个客户端的连接对话，需要通过它来给客户端发送消息
     */
    private Session session;

    /**
     * 标识当前连接客户端的用户名
     */
    private String filename;

    private static ConcurrentHashMap<String,Document> allDocument = new ConcurrentHashMap<>();

    /**
     * 用于存所有的连接服务的客户端，这个对象存储是安全的
     * 注意这里的kv,设计的很巧妙，v刚好是本类 WebSocket (用来存放每个客户端对应的MyWebSocket对象)
     */
    private static ConcurrentHashMap<String, List<DocumentEditContriller> > webSocketSet = new ConcurrentHashMap<>();


    /**
     * 连接建立成功调用的方法
     * session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void OnOpen(Session session, @PathParam(value = "filename") String filename) throws IOException {

        this.session = session;
        this.filename = filename;
        // fimename是用来表示唯一文件，如果需要指定发送，需要指定发送通过filename来区分
        if(webSocketSet.containsKey(filename)) {
            AppointSending(filename,"{new_user: 1}");
            webSocketSet.get(filename).add(this);
        }
        else {
            List<DocumentEditContriller> newList = new ArrayList<>();
            newList.add(this);
            webSocketSet.put(filename,newList);
            //发送数据结构
            Document curDoc = Document.loadDocument(filename);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String docmessage = objectMapper.writeValueAsString(curDoc);
            //AppointSending(filename,docmessage);
            this.session.getBasicRemote().sendText(docmessage);
            allDocument.put(filename,curDoc);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void OnClose() throws IOException {
        if(webSocketSet.get(this.filename).contains(this)) {

            webSocketSet.get(this.filename).remove(this);
//            this.session.getBasicRemote().sendText("用户退出，现在还剩"+webSocketSet.get(this.filename).size()+"位用户");
//            this.session.close();
        }
        if(webSocketSet.get(this.filename).size() == 0) {
            webSocketSet.remove(this.filename);
            allDocument.remove(this.filename);
            try {
                //这里需要改成文档的保存路径
                FileOutputStream fileOut = new FileOutputStream(this.filename+".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(allDocument.get(this.filename));
                out.close();
                fileOut.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void OnMessage(String message_str){

        AppointSending(this.filename,message_str);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Document curDoc = objectMapper.readValue(message_str, Document.class);
            allDocument.put(this.filename,curDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }


    /**
     * 指定发送
     * @param filename
     * @param message
     */
    public void AppointSending(String filename,String message){
        try {
            if(message == "{new_user: 1}")
                webSocketSet.get(filename).get(0).session.getBasicRemote().sendText(message);
            else
                for(DocumentEditContriller nowDoc : webSocketSet.get(filename)) {
                    if(nowDoc.session != this.session)
                        nowDoc.session.getBasicRemote().sendText(message);
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
