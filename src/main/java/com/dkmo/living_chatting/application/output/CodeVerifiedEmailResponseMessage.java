package com.dkmo.living_chatting.application.output;

import java.util.UUID;

public record CodeVerifiedEmailResponseMessage(UUID id, int verifiedNumber) {
}
