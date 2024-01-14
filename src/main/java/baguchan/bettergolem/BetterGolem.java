package baguchan.bettergolem;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BetterGolem.MODID)
public class BetterGolem {
	private static final Logger LOGGER = LogManager.getLogger();

	public static final String MODID = "bettergolem";

	public BetterGolem(IEventBus modBus) {
	}
}
