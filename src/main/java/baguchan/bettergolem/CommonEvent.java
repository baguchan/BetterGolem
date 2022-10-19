package baguchan.bettergolem;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterGolem.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvent {

	@SubscribeEvent
	public static void doSpawnAttribute(EntityAttributeModificationEvent event) {
		event.add(EntityType.IRON_GOLEM, Attributes.ARMOR, 12D);
		event.add(EntityType.IRON_GOLEM, Attributes.ARMOR_TOUGHNESS, 1D);
	}
}
