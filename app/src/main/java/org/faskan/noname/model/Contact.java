package org.faskan.noname.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Contact implements Serializable {
    private String id;
    private String name;
    //private boolean status;
}
