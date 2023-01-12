package se.setodostudykihyun.dto.request;

import lombok.Data;

@Data
public class TodoUpdateRequest {
    private final String title;
    private final String content;
}
