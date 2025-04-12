package vn.edu.fpt.jpos.resources.entities.http;

import java.util.List;
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
public class ListBaseResponse<T> {

    private int status;
    private String message;
    private int page;
    private int size;
    private int total;
    private int totalPages;
    private List<T> items;

    public ListBaseResponse(int status, String message, int page, int size, List<T> items) {
        this.status = status;
        this.message = message;
        this.page = page;
        this.size = size;
        this.total = items.size();
        this.totalPages = Math.floorDiv(items.size(), size) * size == items.size()
                ? Math.floorDiv(items.size(), size)
                : Math.floorDiv(items.size(), size) + 1;
        this.items = items.subList((page - 1) * size, Math.min(items.size(), page * size));
    }
}
