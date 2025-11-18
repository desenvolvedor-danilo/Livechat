package com.dkmo.living_chatting;

import lombok.Builder;

@Builder
public record ChatInput(String user, String message) {
}
