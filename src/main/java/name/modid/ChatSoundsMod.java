package name.modid;  // 包名要与文件路径相匹配

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ChatSoundsMod implements ModInitializer {

    private static final Identifier COW_BELL_SOUND_ID = new Identifier("minecraft", "block.note_block.cow_bell");
    private static final SoundEvent COW_BELL_SOUND = Registry.SOUND_EVENT.get(COW_BELL_SOUND_ID);

    @Override
    public void onInitialize() {
        ServerStartCallback.EVENT.register(server -> {
            server.getPlayerManager().onPlayerMessage((serverPlayerEntity, text) -> {
                // 当玩家发送消息时的回调函数
                if (text.equals("ping")) { // 可以根据消息内容进行过滤
                    serverPlayerEntity.playSound(COW_BELL_SOUND, 1.0f, 1.0f); // 播放 cow_bell 声音
                }
                return true; // 返回 true 表示处理了消息
            });
        });
    }
}
