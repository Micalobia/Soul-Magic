package micalobia.soul_magic.mixin;

import micalobia.soul_magic.PlayerEntityExtension;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {
    private static final int moveUpBy = 5;
    @Shadow
    @Final
    private MinecraftClient client;
    @Shadow
    private int scaledHeight;
    @Shadow
    private int scaledWidth;

    public void renderSoulBar(MatrixStack matrices, int x) {
        this.client.getProfiler().push("soulBar");
        this.client.getTextureManager().bindTexture(DrawableHelper.GUI_ICONS_TEXTURE);
        PlayerEntityExtension player = ((PlayerEntityExtension) (Object) this.client.player);
        int max = player.getSoulMax();
        int count = player.getSoulCount();
        int maxWidth = 183;
        float progress = (float) count / (float) max;
        int width = (int) (progress * maxWidth);
        int n = this.scaledHeight - 28;
        this.drawTexture(matrices, x, n, 0, 166, maxWidth - 1, 5);
        if (width > 0) {
            this.drawTexture(matrices, x, n, 0, 171, width, 5);
        }
        this.client.getProfiler().pop();
    }

    @ModifyConstant(method = "renderExperienceBar", constant = {@Constant(intValue = 32)})
    public int moveExperienceBarUp(int value) {
        return value + moveUpBy;
    }

    @ModifyConstant(method = "renderExperienceBar", constant = {@Constant(intValue = 31)})
    public int moveExperienceTextUp(int value) {
        return value + moveUpBy;
    }


    @ModifyConstant(method = "renderStatusBars", constant = {@Constant(intValue = 39)})
    public int moveStatusUp(int value) {
        return value + moveUpBy;
    }

    @ModifyConstant(method = "renderMountHealth", constant = {@Constant(intValue = 39)})
    public int moveMountHealthUp(int value) {
        return value + moveUpBy;
    }

    @ModifyConstant(method = "renderMountJumpBar", constant = {@Constant(intValue = 32)})
    public int moveMountJumpBarUp(int value) { return value + moveUpBy; }


//Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/util/math/MatrixStack;I)V
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", shift = At.Shift.AFTER))
    public void injectRenderSoulBar(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        int i = this.scaledWidth / 2 - 91;
        renderSoulBar(matrices, i);
    }
}

