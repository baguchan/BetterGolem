package baguchan.bettergolem;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@Mod("bettergolem")
public class BetterGolem {
	private static final Logger LOGGER = LogManager.getLogger();

	public static final String MODID = "bettergolem";

	public BetterGolem() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(FMLCommonSetupEvent event) {
	}

	private void doClientStuff(FMLClientSetupEvent event) {
	}

	private void enqueueIMC(InterModEnqueueEvent event) {
	}

	private void processIMC(InterModProcessEvent event) {
	}

	@SubscribeEvent
	public void registerAttribute(EntityJoinWorldEvent event) {
		if(event.getEntity() instanceof IronGolem) {
			if(((IronGolem) event.getEntity()).getAttribute(Attributes.ARMOR).getBaseValue() <= 0.0F) {
				((IronGolem) event.getEntity()).getAttribute(Attributes.ARMOR).setBaseValue(10.0D);
			}

			if(((IronGolem) event.getEntity()).getAttribute(Attributes.ARMOR_TOUGHNESS).getBaseValue() <= 0.0F) {
				((IronGolem) event.getEntity()).getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(1.0D);
			}
		}
	}
}
