package codes.dreaming.emoteallowlist;

import io.github.kosmx.emotes.api.events.server.ServerEmoteEvents;
import net.fabricmc.api.ModInitializer;
import codes.dreaming.emoteallowlist.EmotecraftAllowlistConfig;

public class Emoteallowlist implements ModInitializer {
    public static final EmotecraftAllowlistConfig CONFIG = EmotecraftAllowlistConfig.createAndLoad();

    @Override
    public void onInitialize() {
        ServerEmoteEvents.EMOTE_VERIFICATION.register(new CustomEmoteVerifier());
    }
}
