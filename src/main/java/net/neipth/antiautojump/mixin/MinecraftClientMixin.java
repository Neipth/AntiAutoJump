package net.neipth.antiautojump.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	/**
	 * @Final Marca como inmutable, (es decir, no puede modificarse después de su inicialización).
	 * @Shadow te permite acceder a un campo o mtodo en la clase original sin definirlo explícitamente en el mixin.
	 * GameOptions Contiene datos relacionados con las configuraciones del juego, como la resolución de pantalla, el nivel de sonido, o los controles.
	 + options es la variable.
	 */
	@Final
	@Shadow
	public GameOptions options;

	/**
	 * @method = "<init>": <init> es el nombre especial que se usa para identificar el constructor.
	 	* Al usarlo en @Inject, estás indicando al Mixin que quieres inyectar código en el constructor de la clase.
	 * @at = @At("TAIL"): "TAIL" significa que el código se inyectará al final del constructor, justo antes de que termine de ejecutarse.
	 	* Esto asegura que todas las inicializaciones de la clase ya hayan sido realizadas cuando tu código adicional se ejecute.
	 */
	@Inject(at = @At("TAIL"), method = "<init>")
	private void init(CallbackInfo info) {
		options.getAutoJump().setValue(false);
	}
}