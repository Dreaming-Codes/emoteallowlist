package codes.dreaming.emoteallowlist;

import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.impl.event.EventResult;
import io.github.kosmx.emotes.api.events.server.ServerEmoteEvents;

import java.util.UUID;

public class CustomEmoteVerifier implements ServerEmoteEvents.EmoteVerifier {
    @Override
    public EventResult verify(KeyframeAnimation emote, UUID userID) {
        if (!isEmoteAllowed(emote)) {
            return EventResult.PASS;
        }

        return EventResult.FAIL;
    }

    private Boolean isEmoteAllowed(KeyframeAnimation emote) {
        return Emoteallowlist.CONFIG.allowlist().contains(emote.getUuid().toString());
    }
}
