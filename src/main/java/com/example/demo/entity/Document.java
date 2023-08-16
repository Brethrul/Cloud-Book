package com.example.demo.entity;


import com.example.demo.utils.HashService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
class Cell {
    private String userid;

    private boolean isusing;

    private String context;
}
@Data
public class Document {
    private String name;

    private List<List<Cell>> document;

    private static HashService hashService = new HashService();

    public Document(String name,int rows,int cols)
    {
        this.name = name;

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
    }
    public static String saveDocument(Document document,String docname,String username)
    {
        String filename = hashService.hashString(username+docname);
        //序列化
        try {
            FileOutputStream fileOut = new FileOutputStream("~/DocmentFile/"+filename+".ser");
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
            FileInputStream fileIn = new FileInputStream("~/DocmentFile/" + filename + ".ser");
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
