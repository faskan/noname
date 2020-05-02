package org.faskan.noname.contact;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Contact {
    private String id;
    private String name;
    //private boolean status;
}
