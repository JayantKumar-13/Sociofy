package com.jayant.connections_service.event;

import lombok.Data;

@Data
public class SendConnectionRequestEvent {
    private Long senderId;
    private Long receiverId;
}
