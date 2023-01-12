package se.setodostudykihyun.dto.request;

import lombok.Data;

@Data
public class TodoRegisterRequest {
    private final String title;
    private final String content;
}
