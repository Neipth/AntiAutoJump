package net.neipth.antiautojump.mixin;

import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Está diseñado para modificar el comportamiento del metodo getOptions en la clase AccessibilityOptionsScreen
@Mixin(AccessibilityOptionsScreen.class)
public class AccessibilityScreenMixin {
    // @Inject(method = "getOptions", at = @At("HEAD"), cancellable = true) indica que se va a inyectar código a "inicio" (HEAD) del metodo getOptions.
    // cancellable = true permite cancelar el metodo original después de ejecutar el código inyectado.
    @Inject(method="getOptions", at = @At("HEAD"), cancellable = true)
    private static void addOptions(GameOptions gameOptions, CallbackInfoReturnable<SimpleOption<?>[]> cir) {
        cir.setReturnValue(new SimpleOption[]{gameOptions.getNarrator(), gameOptions.getShowSubtitles(), gameOptions.getTextBackgroundOpacity(), gameOptions.getBackgroundForChatOnly(), gameOptions.getChatOpacity(), gameOptions.getChatLineSpacing(), gameOptions.getChatDelay(), gameOptions.getAutoJump(), gameOptions.getSneakToggled(), gameOptions.getSprintToggled(), gameOptions.getDistortionEffectScale(), gameOptions.getFovEffectScale(), gameOptions.getMonochromeLogo(), gameOptions.getHideLightningFlashes(), gameOptions.getDarknessEffectScale()});
        cir.cancel();
    }
}
