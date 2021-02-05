package micalobia.soul_magic.mixin;

import micalobia.soul_magic.PlayerEntityExtension;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
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
public abstract class InGameHudMixin extends DrawableHelper {
    private static final int moveUpBy = 5;
    @Shadow
    @Final
    private MinecraftClient client;
    @Shadow
    private int scaledHeight;
    @Shadow
    private int scaledWidth;

    @Shadow
    public abstract TextRenderer getFontRenderer();

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
        //if (count > 0) {
            this.client.getProfiler().push("soulCount");
            String str = "" + count;
            int u = (this.scaledWidth - this.getFontRenderer().getWidth(str)) / 2;
            int v = this.scaledHeight - 43;
            this.getFontRenderer().draw(matrices, str, u + 1, v, 0);
            this.getFontRenderer().draw(matrices, str, u - 1, v, 0);
            this.getFontRenderer().draw(matrices, str, u, v + 1, 0);
            this.getFontRenderer().draw(matrices, str, u, v - 1, 0);
            this.getFontRenderer().draw(matrices, str, u, v, 0x1f80ff);
            this.client.getProfiler().pop();
        //}
    }

    @ModifyConstant(method = "renderExperienceBar", constant = {@Constant(intValue = 32)})
    public int moveExperienceBarUp(int value) {
        return value + moveUpBy;
    }

    @ModifyConstant(method = "renderExperienceBar", constant = {@Constant(intValue = 31)})
    public int moveExperienceTextUp(int value) {
        return value + 18;
    }

//    @ModifyVariable(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;getFontRenderer()Lnet/minecraft/client/font/TextRenderer;", ordinal = 1, shift = At.Shift.AFTER), ordinal = 1)
//    public int moveExperienceTextLeft(int a) {
//        int width = this.getFontRenderer().getWidth(client.player.experienceLevel + "");
//        return a - 92 - width / 2;
//    }

    @ModifyConstant(method = "renderStatusBars", constant = {@Constant(intValue = 39)})
    public int moveStatusUp(int value) {
        return value + moveUpBy;
    }

    @ModifyConstant(method = "renderMountHealth", constant = {@Constant(intValue = 39)})
    public int moveMountHealthUp(int value) {
        return value + moveUpBy;
    }

    @ModifyConstant(method = "renderMountJumpBar", constant = {@Constant(intValue = 32)})
    public int moveMountJumpBarUp(int value) {
        return value + moveUpBy;
    }


    //Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/util/math/MatrixStack;I)V
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", shift = At.Shift.AFTER))
    public void injectRenderSoulBar(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        int i = this.scaledWidth / 2 - 91;
        renderSoulBar(matrices, i);
    }
}

