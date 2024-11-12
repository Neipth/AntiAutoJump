package net.neipth.antiautojump.mixin;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ControlsOptionsScreen.class)
public class ControlOptionsScreenMixin extends GameOptionsScreen {
    // Contructor de GameOptionsScreen
    public ControlOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    /**
     * @Redirect: Esta anotación intercepta y redirige a un método específico, permitiéndote cambiar su comportamiento.
     * method = "init": Indica que se está redirigiendo una llamada dentro del método init.
     * @At(value = "INVOKE", target = "..."): Especifica el punto de inyección de método (INVOKE).
        * El target señala el método exacto que deseas interceptar:
     * @addDrawableChild en ControlsOptionsScreen, que recibe un parámetro de tipo Element.
     * @addDrawableChild se utiliza para añadir un componente gráfico (o "elemento") a la pantalla de opciones de controles.
     * @RemoveAutoJump: Verifica si el texto de un ClickableWidget (botón) contiene la traducción de "options.autoJump",
         * el metodo devuelve null en vez de añadir el botón, lo que impide su visualización en la interfaz.
     * @params: instance
        * Representa una instancia de ControlsOptionsScreen, la pantalla de opciones de controles.
     * @params: element
        * El element de tipo Element que se quiere añadir a la pantalla. Aquí se espera que sea un ClickableWidget (por ejemplo, un botón o componente interactivo).
     */
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/ControlsOptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;"))
    private Element removeAutoJump(ControlsOptionsScreen instance, Element element) {
        // El "casting" es una técnica que permite convertir una variable de un tipo de dato a otro.
        // En este caso, se está realizando un casting explícito de la variable element, de tipo "Element", al tipo más específico "ClickableWidget".
        ClickableWidget widget = (ClickableWidget) element;
        String text = Text.translatable("options.autoJump").getString();
        // Verifica si el element tiene un mensaje (getMessage()) que contiene el texto traducido correspondiente a "options.autoJump".
        // Text.translatable("options.autoJump").getString() obtiene la traducción "Auto Jump", asegurando que la búsqueda funcione en cualquier idioma.
        // Si el texto del element coincide con "Auto Jump", se devuelve null. Esto evita que el botón de auto jump se añada a la pantalla.
        if (widget.getMessage().getString().contains(text)) return null;

        // Si el texto no coincide, el metodo sigue adelante y usa a addDrawableChild((ClickableWidget) element), añadiendo el elemento normalmente.
        return this.addDrawableChild(widget);
    }
}
