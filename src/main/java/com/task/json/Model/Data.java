package com.task.json.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    String name;
    @Column(unique = true)
    String address;
    @Column(name = "created_at")
    LocalDateTime localDateTimeOfCreation;
    @Column(name = "updated_at")
    LocalDateTime localDateTimeOfUpdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getLocalDateTimeOfCreation() {
        return localDateTimeOfCreation;
    }

    public void setLocalDateTimeOfCreation(LocalDateTime localDateTimeOfCreation) {
        this.localDateTimeOfCreation = localDateTimeOfCreation;
    }

    public LocalDateTime getLocalDateTimeOfUpdate() {
        return localDateTimeOfUpdate;
    }

    public void setLocalDateTimeOfUpdate(LocalDateTime localDateTimeOfUpdate) {
        this.localDateTimeOfUpdate = localDateTimeOfUpdate;
    }
    public Map<String,String> ToMap(){
         Map <String,String> dataMap = new HashMap<String, String>();
         dataMap.put("id",String.valueOf(this.id));
         dataMap.put("name",this.name);
         dataMap.put("address",this.address);
         dataMap.put("created_at",this.localDateTimeOfCreation.toString());
         dataMap.put("updated_at",this.localDateTimeOfUpdate.toString());
         return dataMap;
    }

    @Override
    public String toString() {
        return "Data{"+ "id="+this.id + "name=" + this.name + "address=" + this.address + "update_at=" + this.localDateTimeOfUpdate.toString() + "created_at="+this.localDateTimeOfCreation.toString()+"}";
    }
}
