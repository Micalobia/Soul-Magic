package micalobia.soul_magic.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class SoulComponent implements ComponentV3, AutoSyncedComponent {
    private final Object provider;
    private int souls;

    public SoulComponent(Object provider) {
        this.provider = provider;
    }

    public int getSouls() {
        return souls;
    }

    public void setSouls(int souls) {
        this.souls = souls;
        Components.SOULS.sync(provider);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == provider;
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity player) {
        buf.writeVarInt(souls);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        souls = buf.readVarInt();
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        souls = tag.getInt("Souls");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putInt("Souls", souls);
    }
}
