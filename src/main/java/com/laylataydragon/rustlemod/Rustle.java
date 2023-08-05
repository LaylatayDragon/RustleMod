package com.laylataydragon.rustlemod;

import com.laylataydragon.rustlemod.init.RustleModEntityRenderers;
import com.laylataydragon.rustlemod.client.renderer.CalfRenderer;
import com.laylataydragon.rustlemod.client.renderer.CowRenderer;
import com.laylataydragon.rustlemod.client.renderer.LlamaRenderer;
import com.laylataydragon.rustlemod.client.renderer.SheepRenderer;
import com.laylataydragon.rustlemod.init.RustleModEntities;
import com.laylataydragon.rustlemod.init.RustleModItems;
import com.mojang.logging.LogUtils;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import software.bernie.geckolib3.GeckoLib;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;


import java.util.stream.Collectors;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Rustle.MODID)
public class Rustle
{
	public static final String MODID = "rustle";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;


    public Rustle()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
    	 final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


 		RustleModEntities.REGISTRY.register(eventBus);

 		GeckoLib.initialize();

         //eventBus.addListener(this::commonSetup);
         //eventBus.addListener(this::clientSetup);
        // eventBus.addListener(this::registerCapabilities);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		RustleModEntities.REGISTRY.register(bus);

		GeckoLib.initialize();


        // Register New Flowers to be Able to Place in Pots
        
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    	EntityRenderers.register(RustleModEntities.CALF.get(), CalfRenderer::new);
    	EntityRenderers.register(RustleModEntities.COW.get(), CowRenderer::new);
    	EntityRenderers.register(RustleModEntities.SHEEP.get(), SheepRenderer::new);
    	EntityRenderers.register(RustleModEntities.LLAMA.get(), LlamaRenderer::new);
        /*DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> CNBClient::init);
        event.enqueueWork(() -> {
            ItemProperties.register(CNBItems.CACTEM_SPEAR.get(), new ResourceLocation("throwing"), (item, resourceLocation, entity, itemPropertyFunction) -> entity != null && entity.isUsingItem() && entity.getUseItem() == item ? 1.0F : 0.0F);
        });*/
    }
    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
       // CinderSwordCapability.register(event);
    }
}
