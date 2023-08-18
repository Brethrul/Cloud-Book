package com.example.demo.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import com.example.demo.utils.HashService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
class Cell implements Serializable{
    private String userid;

    private String displayName;

    private boolean isUsing;

    private String context;
}
@Data
@Entity
@Table(name = "document")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "doc_name")
    private String docName;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "last_accessed")
    private LocalDateTime lastAccessed;
    @Column(name = "status")
    private int status;// 1:created 0:deleted
    @Transient
    private List<List<Cell>> document = new ArrayList<>();

    private static HashService hashService = new HashService();
    @Column(name = "filename")
    private String fileName;
    public Document(String docName){
        this.docName=docName;
        this.createdOn=LocalDateTime.now();
        this.lastAccessed=LocalDateTime.now();
        this.status=1;
    }

    public Document() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public Document(String name, int rows, int cols, String username)
    {
        this.docName = name;
        this.createdOn=LocalDateTime.now();
        this.lastAccessed=LocalDateTime.now();
        this.status=1;
        for(int i=0;i < rows;i++)
        {
            List<Cell>currentRow = new ArrayList<>();
            for(int j=0;j < cols;j++)
            {
                Cell nowCell = new Cell();
                currentRow.add(nowCell);
            }
            this.document.add(currentRow);
        }
        this.fileName=saveDocument(this,docName,username);
    }
    public static String saveDocument(Document document,String docname,String username)
    {
        String filename = hashService.hashString(username+docname);
        //序列化
        try {
            FileOutputStream fileOut = new FileOutputStream(filename+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(document);
            out.close();
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public static Document loadDocument(String filename) {
        // 反序列化
        Document deserializedDocument = null;
        try {
            FileInputStream fileIn = new FileInputStream("/Users/huxuejian/Documents/CD_Proj/DocumentFile/"+filename+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserializedDocument = (Document) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedDocument;
    }

}
