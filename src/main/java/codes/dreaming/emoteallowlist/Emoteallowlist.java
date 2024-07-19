package codes.dreaming.emoteallowlist;

import net.fabricmc.api.ModInitializer;
import codes.dreaming.emoteallowlist.EmotecraftAllowlistConfig;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class Emoteallowlist implements ModInitializer {
    public static final EmotecraftAllowlistConfig CONFIG = EmotecraftAllowlistConfig.createAndLoad();

    @Override
    public void onInitialize() {
    }
}
