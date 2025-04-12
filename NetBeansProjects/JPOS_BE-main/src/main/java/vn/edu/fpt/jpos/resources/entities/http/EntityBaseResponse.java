package vn.edu.fpt.jpos.resources.entities.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EntityBaseResponse<T> {

    private int status;
    private String message;
    private T item;
}
