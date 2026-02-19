package com.sprint2.doctor_notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Arrays;
import java.time.LocalDate;

@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    @Field("patId")
    private Long patientId;

    @Field("note")
    private String content;
    private String date;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Long getPatientId(){
        return patientId;
    }

    public void setPatientId(Long patientId){
        this.patientId = patientId;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
} 