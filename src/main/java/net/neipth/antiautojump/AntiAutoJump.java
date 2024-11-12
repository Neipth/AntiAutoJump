package net.neipth.antiautojump;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AntiAutoJump implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("AntiAutoJump");

	@Override
	public void onInitialize() {
		LOGGER.info("AntiAuto Jump has been initialized!!");
		LOGGER.info("Sin AccessibilityScreenMixin sigue funcionando");
	}
}