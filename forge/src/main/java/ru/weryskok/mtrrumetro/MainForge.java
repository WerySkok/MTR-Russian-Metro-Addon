package ru.weryskok.mtrrumetro;

import mtr.CreativeModeTabs;
import mtr.Registry;
import mtr.RegistryObject;
import mtr.item.ItemWithCreativeTabBase;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.DeferredRegisterHolder;
import mtr.mappings.RegistryUtilities;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.weryskok.mtrrumetro.mappings.ForgeUtilities;

@Mod(Main.MOD_ID)
public class MainForge {
    private static final DeferredRegisterHolder<Item> ITEMS = new DeferredRegisterHolder<>(Main.MOD_ID, ForgeUtilities.registryGetItem());
    private static final DeferredRegisterHolder<Block> BLOCKS = new DeferredRegisterHolder<>(Main.MOD_ID, ForgeUtilities.registryGetBlock());
    private static final DeferredRegisterHolder<BlockEntityType<?>> BLOCK_ENTITY_TYPES = new DeferredRegisterHolder<>(Main.MOD_ID, ForgeUtilities.registryGetBlockEntityType());
    private static final DeferredRegisterHolder<SoundEvent> SOUND_EVENTS = new DeferredRegisterHolder<>(Main.MOD_ID, ForgeUtilities.registryGetSoundEvent());
    private static final DeferredRegisterHolder<EntityType<?>> ENTITY_TYPES = new DeferredRegisterHolder<>(Main.MOD_ID, ForgeUtilities.registryGetEntityType());

    static {
        Main.init(MainForge::registerItem, MainForge::registerBlock, MainForge::registerBlock, MainForge::registerBlockEntityType, MainForge::registerEntityType, MainForge::registerSoundEvent);
    }

    public MainForge() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ForgeUtilities.registerModEventBus(Main.MOD_ID, eventBus);

        ITEMS.register();
        BLOCKS.register();
        BLOCK_ENTITY_TYPES.register();
        SOUND_EVENTS.register();

        eventBus.register(MTRForgeRegistry.class);
    }

    private static void registerItem(String path, RegistryObject<Item> item) {
        ITEMS.register(path, () -> {
            final Item itemObject = item.get();
            if (itemObject instanceof ItemWithCreativeTabBase) {
                Registry.registerCreativeModeTab(((ItemWithCreativeTabBase) itemObject).creativeModeTab.resourceLocation, itemObject);
            } else if (itemObject instanceof ItemWithCreativeTabBase.ItemPlaceOnWater) {
                Registry.registerCreativeModeTab(((ItemWithCreativeTabBase.ItemPlaceOnWater) itemObject).creativeModeTab.resourceLocation, itemObject);
            }
            return itemObject;
        });
    }

    private static void registerBlock(String path, RegistryObject<Block> block) {
        BLOCKS.register(path, block::get);
    }

    private static void registerBlock(String path, RegistryObject<Block> block, CreativeModeTabs.Wrapper creativeModeTabWrapper) {
        registerBlock(path, block);
        ITEMS.register(path, () -> {
            final BlockItem blockItem = new BlockItem(block.get(), RegistryUtilities.createItemProperties(creativeModeTabWrapper::get));
            Registry.registerCreativeModeTab(creativeModeTabWrapper.resourceLocation, blockItem);
            return blockItem;
        });
    }

    private static void registerBlockEntityType(String path, RegistryObject<? extends BlockEntityType<? extends BlockEntityMapper>> blockEntityType) {
        BLOCK_ENTITY_TYPES.register(path, blockEntityType::get);
    }

    private static void registerSoundEvent(String path, SoundEvent soundEvent) {
        SOUND_EVENTS.register(path, () -> soundEvent);
    }

    private static class MTRForgeRegistry {
        @SubscribeEvent
        public static void onClientSetupEvent(FMLClientSetupEvent event) {
            MainClient.init();
            event.enqueueWork(MainClient::registerItemModelPredicates);
        }
    }

    private static void registerEntityType(String path, RegistryObject<? extends EntityType<? extends Entity>> entityType) {
        ENTITY_TYPES.register(path, entityType::get);
    }
}