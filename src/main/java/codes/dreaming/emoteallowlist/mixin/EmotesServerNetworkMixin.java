package codes.dreaming.emoteallowlist.mixin;

import codes.dreaming.emoteallowlist.Emoteallowlist;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Pair;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import io.github.kosmx.emotes.api.proxy.INetworkInstance;
import io.github.kosmx.emotes.common.network.EmotePacket;
import io.github.kosmx.emotes.common.network.objects.NetData;
import io.github.kosmx.emotes.fabric.network.ServerNetwork;
import io.github.kosmx.emotes.server.network.AbstractServerEmotePlay;
import io.github.kosmx.emotes.server.network.IServerNetworkInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(value = AbstractServerEmotePlay.class, remap = false)
public abstract class EmotesServerNetworkMixin<P> extends ServerEmoteAPI {

    @Shadow protected abstract void sendForPlayer(NetData netData, P p, UUID uuid);

    @Shadow
    protected abstract IServerNetworkInstance getPlayerNetworkInstance(P var1);

    @Shadow protected abstract UUID getUUIDFromPlayer(P p);

    @Inject(method = "handleStreamEmote", at = @At("HEAD"), cancellable = true)
    private void handleStreamEmote(NetData data, P player, INetworkInstance instance, CallbackInfo ci) {
        if (data.emoteData == null) {
            return;
        }

        if (Emoteallowlist.CONFIG.allowlist().contains(data.emoteData.getUuid().toString())) {
            return;
        }

        this.getPlayerNetworkInstance(player).getEmoteTracker().setPlayedEmote((KeyframeAnimation) null, false);
        NetData stopData = (new EmotePacket.Builder()).configureToSendStop(data.emoteData.getUuid(), this.getUUIDFromPlayer(player)).build().data;
        stopData.isForced = true;
        this.sendForPlayer(stopData, player, this.getUUIDFromPlayer(player));
        ci.cancel();
    }

}
