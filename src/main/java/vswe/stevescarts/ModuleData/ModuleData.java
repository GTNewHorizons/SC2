package vswe.stevescarts.ModuleData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.Helpers.CartVersion;
import vswe.stevescarts.Helpers.ColorHelper;
import vswe.stevescarts.Helpers.ComponentTypes;
import vswe.stevescarts.Helpers.Localization;
import vswe.stevescarts.Helpers.RecipeHelper;
import vswe.stevescarts.Helpers.ResourceHelper;
import vswe.stevescarts.Items.ModItems;
import vswe.stevescarts.Models.Cart.ModelAdvancedTank;
import vswe.stevescarts.Models.Cart.ModelBridge;
import vswe.stevescarts.Models.Cart.ModelCage;
import vswe.stevescarts.Models.Cart.ModelCake;
import vswe.stevescarts.Models.Cart.ModelCartbase;
import vswe.stevescarts.Models.Cart.ModelCleaner;
import vswe.stevescarts.Models.Cart.ModelCompactSolarPanel;
import vswe.stevescarts.Models.Cart.ModelDrill;
import vswe.stevescarts.Models.Cart.ModelDynamite;
import vswe.stevescarts.Models.Cart.ModelEggBasket;
import vswe.stevescarts.Models.Cart.ModelEngineFrame;
import vswe.stevescarts.Models.Cart.ModelEngineInside;
import vswe.stevescarts.Models.Cart.ModelExtractingChests;
import vswe.stevescarts.Models.Cart.ModelFarmer;
import vswe.stevescarts.Models.Cart.ModelFrontChest;
import vswe.stevescarts.Models.Cart.ModelFrontTank;
import vswe.stevescarts.Models.Cart.ModelGiftStorage;
import vswe.stevescarts.Models.Cart.ModelGun;
import vswe.stevescarts.Models.Cart.ModelHull;
import vswe.stevescarts.Models.Cart.ModelHullTop;
import vswe.stevescarts.Models.Cart.ModelLawnMower;
import vswe.stevescarts.Models.Cart.ModelLever;
import vswe.stevescarts.Models.Cart.ModelLiquidDrainer;
import vswe.stevescarts.Models.Cart.ModelLiquidSensors;
import vswe.stevescarts.Models.Cart.ModelMobDetector;
import vswe.stevescarts.Models.Cart.ModelNote;
import vswe.stevescarts.Models.Cart.ModelPigHead;
import vswe.stevescarts.Models.Cart.ModelPigHelmet;
import vswe.stevescarts.Models.Cart.ModelPigTail;
import vswe.stevescarts.Models.Cart.ModelPumpkinHull;
import vswe.stevescarts.Models.Cart.ModelPumpkinHullTop;
import vswe.stevescarts.Models.Cart.ModelRailer;
import vswe.stevescarts.Models.Cart.ModelSeat;
import vswe.stevescarts.Models.Cart.ModelShield;
import vswe.stevescarts.Models.Cart.ModelShootingRig;
import vswe.stevescarts.Models.Cart.ModelSideChests;
import vswe.stevescarts.Models.Cart.ModelSideTanks;
import vswe.stevescarts.Models.Cart.ModelSniperRifle;
import vswe.stevescarts.Models.Cart.ModelSolarPanelBase;
import vswe.stevescarts.Models.Cart.ModelSolarPanelHeads;
import vswe.stevescarts.Models.Cart.ModelToolPlate;
import vswe.stevescarts.Models.Cart.ModelTopChest;
import vswe.stevescarts.Models.Cart.ModelTopTank;
import vswe.stevescarts.Models.Cart.ModelTorchplacer;
import vswe.stevescarts.Models.Cart.ModelTrackRemover;
import vswe.stevescarts.Models.Cart.ModelWheel;
import vswe.stevescarts.Models.Cart.ModelWoodCutter;
import vswe.stevescarts.Modules.Addons.Mobdetectors.ModuleAnimal;
import vswe.stevescarts.Modules.Addons.Mobdetectors.ModuleBat;
import vswe.stevescarts.Modules.Addons.Mobdetectors.ModuleMonster;
import vswe.stevescarts.Modules.Addons.Mobdetectors.ModulePlayer;
import vswe.stevescarts.Modules.Addons.Mobdetectors.ModuleVillager;
import vswe.stevescarts.Modules.Addons.ModuleAddon;
import vswe.stevescarts.Modules.Addons.ModuleBrake;
import vswe.stevescarts.Modules.Addons.ModuleChunkLoader;
import vswe.stevescarts.Modules.Addons.ModuleColorRandomizer;
import vswe.stevescarts.Modules.Addons.ModuleColorizer;
import vswe.stevescarts.Modules.Addons.ModuleCrafter;
import vswe.stevescarts.Modules.Addons.ModuleCrafterAdv;
import vswe.stevescarts.Modules.Addons.ModuleCreativeIncinerator;
import vswe.stevescarts.Modules.Addons.ModuleCreativeSupplies;
import vswe.stevescarts.Modules.Addons.ModuleDrillIntelligence;
import vswe.stevescarts.Modules.Addons.ModuleEnchants;
import vswe.stevescarts.Modules.Addons.ModuleHeightControl;
import vswe.stevescarts.Modules.Addons.ModuleIncinerator;
import vswe.stevescarts.Modules.Addons.ModuleInvisible;
import vswe.stevescarts.Modules.Addons.ModuleLabel;
import vswe.stevescarts.Modules.Addons.ModuleLiquidSensors;
import vswe.stevescarts.Modules.Addons.ModuleMelter;
import vswe.stevescarts.Modules.Addons.ModuleMelterExtreme;
import vswe.stevescarts.Modules.Addons.ModuleOreTracker;
import vswe.stevescarts.Modules.Addons.ModulePowerObserver;
import vswe.stevescarts.Modules.Addons.ModuleShield;
import vswe.stevescarts.Modules.Addons.ModuleSmelter;
import vswe.stevescarts.Modules.Addons.ModuleSmelterAdv;
import vswe.stevescarts.Modules.Addons.ModuleSnowCannon;
import vswe.stevescarts.Modules.Addons.Plants.ModuleIC2Crops;
import vswe.stevescarts.Modules.Addons.Plants.ModuleModCrops;
import vswe.stevescarts.Modules.Addons.Plants.ModuleModTrees;
import vswe.stevescarts.Modules.Addons.Plants.ModuleNetherwart;
import vswe.stevescarts.Modules.Addons.Plants.ModulePlantSize;
import vswe.stevescarts.Modules.Addons.Projectiles.ModuleCake;
import vswe.stevescarts.Modules.Addons.Projectiles.ModuleEgg;
import vswe.stevescarts.Modules.Addons.Projectiles.ModuleFireball;
import vswe.stevescarts.Modules.Addons.Projectiles.ModulePotion;
import vswe.stevescarts.Modules.Addons.Projectiles.ModuleSnowball;
import vswe.stevescarts.Modules.Engines.ModuleCheatEngine;
import vswe.stevescarts.Modules.Engines.ModuleCoalStandard;
import vswe.stevescarts.Modules.Engines.ModuleCoalTiny;
import vswe.stevescarts.Modules.Engines.ModuleElectricHV;
import vswe.stevescarts.Modules.Engines.ModuleElectricLV;
import vswe.stevescarts.Modules.Engines.ModuleElectricMV;
import vswe.stevescarts.Modules.Engines.ModuleEngine;
import vswe.stevescarts.Modules.Engines.ModuleSolarBasic;
import vswe.stevescarts.Modules.Engines.ModuleSolarCompact;
import vswe.stevescarts.Modules.Engines.ModuleSolarStandard;
import vswe.stevescarts.Modules.Engines.ModuleThermalAdvanced;
import vswe.stevescarts.Modules.Engines.ModuleThermalStandard;
import vswe.stevescarts.Modules.Hull.ModuleCheatHull;
import vswe.stevescarts.Modules.Hull.ModuleGalgadorian;
import vswe.stevescarts.Modules.Hull.ModuleHull;
import vswe.stevescarts.Modules.Hull.ModulePig;
import vswe.stevescarts.Modules.Hull.ModulePumpkin;
import vswe.stevescarts.Modules.Hull.ModuleReinforced;
import vswe.stevescarts.Modules.Hull.ModuleStandard;
import vswe.stevescarts.Modules.Hull.ModuleWood;
import vswe.stevescarts.Modules.ModuleBase;
import vswe.stevescarts.Modules.Realtimers.ModuleAdvControl;
import vswe.stevescarts.Modules.Realtimers.ModuleArcade;
import vswe.stevescarts.Modules.Realtimers.ModuleCage;
import vswe.stevescarts.Modules.Realtimers.ModuleCakeServer;
import vswe.stevescarts.Modules.Realtimers.ModuleCakeServerDynamite;
import vswe.stevescarts.Modules.Realtimers.ModuleCleaner;
import vswe.stevescarts.Modules.Realtimers.ModuleDynamite;
import vswe.stevescarts.Modules.Realtimers.ModuleExperience;
import vswe.stevescarts.Modules.Realtimers.ModuleFirework;
import vswe.stevescarts.Modules.Realtimers.ModuleFlowerRemover;
import vswe.stevescarts.Modules.Realtimers.ModuleMilker;
import vswe.stevescarts.Modules.Realtimers.ModuleNote;
import vswe.stevescarts.Modules.Realtimers.ModuleSeat;
import vswe.stevescarts.Modules.Realtimers.ModuleShooter;
import vswe.stevescarts.Modules.Realtimers.ModuleShooterAdv;
import vswe.stevescarts.Modules.Storages.Chests.ModuleEggBasket;
import vswe.stevescarts.Modules.Storages.Chests.ModuleExtractingChests;
import vswe.stevescarts.Modules.Storages.Chests.ModuleFrontChest;
import vswe.stevescarts.Modules.Storages.Chests.ModuleGiftStorage;
import vswe.stevescarts.Modules.Storages.Chests.ModuleInternalStorage;
import vswe.stevescarts.Modules.Storages.Chests.ModuleSideChests;
import vswe.stevescarts.Modules.Storages.Chests.ModuleTopChest;
import vswe.stevescarts.Modules.Storages.Chests.ModuleTrainStorage;
import vswe.stevescarts.Modules.Storages.ModuleStorage;
import vswe.stevescarts.Modules.Storages.Tanks.ModuleAdvancedTank;
import vswe.stevescarts.Modules.Storages.Tanks.ModuleCheatTank;
import vswe.stevescarts.Modules.Storages.Tanks.ModuleFrontTank;
import vswe.stevescarts.Modules.Storages.Tanks.ModuleInternalTank;
import vswe.stevescarts.Modules.Storages.Tanks.ModuleOpenTank;
import vswe.stevescarts.Modules.Storages.Tanks.ModuleSideTanks;
import vswe.stevescarts.Modules.Storages.Tanks.ModuleTopTank;
import vswe.stevescarts.Modules.Workers.ModuleBridge;
import vswe.stevescarts.Modules.Workers.ModuleFertilizer;
import vswe.stevescarts.Modules.Workers.ModuleHydrater;
import vswe.stevescarts.Modules.Workers.ModuleLiquidDrainer;
import vswe.stevescarts.Modules.Workers.ModuleRailer;
import vswe.stevescarts.Modules.Workers.ModuleRailerLarge;
import vswe.stevescarts.Modules.Workers.ModuleRemover;
import vswe.stevescarts.Modules.Workers.ModuleTorch;
import vswe.stevescarts.Modules.Workers.Tools.ModuleDrillDiamond;
import vswe.stevescarts.Modules.Workers.Tools.ModuleDrillGalgadorian;
import vswe.stevescarts.Modules.Workers.Tools.ModuleDrillHardened;
import vswe.stevescarts.Modules.Workers.Tools.ModuleDrillIron;
import vswe.stevescarts.Modules.Workers.Tools.ModuleFarmerDiamond;
import vswe.stevescarts.Modules.Workers.Tools.ModuleFarmerGalgadorian;
import vswe.stevescarts.Modules.Workers.Tools.ModuleTool;
import vswe.stevescarts.Modules.Workers.Tools.ModuleWoodcutterDiamond;
import vswe.stevescarts.Modules.Workers.Tools.ModuleWoodcutterGalgadorian;
import vswe.stevescarts.Modules.Workers.Tools.ModuleWoodcutterHardened;
import vswe.stevescarts.StevesCarts;

public class ModuleData {

    private static HashMap<Byte, ModuleData> moduleList;
    private static Class[] moduleGroups;
    private static Localization.MODULE_INFO[] moduleGroupNames;

    public static HashMap<Byte, ModuleData> getList() {
        return moduleList;
    }

    public static Collection<ModuleData> getModules() {
        return getList().values();
    }

    public static void init() {
        String planks = "plankWood"; // ore dict
        String wood = "logWood"; // ore dict
        ItemStack woodSingleSlab = new ItemStack(Blocks.wooden_slab, 1, -1);
        ItemStack bonemeal = new ItemStack(Items.dye, 1, 15);

        moduleGroups = new Class[] { ModuleHull.class, ModuleEngine.class, ModuleTool.class, ModuleStorage.class,
                ModuleAddon.class };
        moduleGroupNames = new Localization.MODULE_INFO[] { Localization.MODULE_INFO.HULL_CATEGORY,
                Localization.MODULE_INFO.ENGINE_CATEGORY, Localization.MODULE_INFO.TOOL_CATEGORY,
                Localization.MODULE_INFO.STORAGE_CATEGORY, Localization.MODULE_INFO.ADDON_CATEGORY,
                Localization.MODULE_INFO.ATTACHMENT_CATEGORY };

        moduleList = new HashMap<Byte, ModuleData>();

        ModuleDataGroup engineGroup = new ModuleDataGroup(Localization.MODULE_INFO.ENGINE_GROUP);

        ModuleData coalStandard = new ModuleData(0, "Coal Engine", ModuleCoalStandard.class, 15)/*
                                                                                                 * .addSide(SIDE.BACK)
                                                                                                 */
                .addRecipe(
                        new Object[][] { { Items.iron_ingot, Items.iron_ingot, Items.iron_ingot },
                                { Items.iron_ingot, Blocks.furnace, Items.iron_ingot },
                                { Blocks.piston, null, Blocks.piston } });

        ModuleData coalTiny = new ModuleData(44, "Tiny Coal Engine", ModuleCoalTiny.class, 2)/* .addSide(SIDE.BACK) */
                .addRecipe(
                        new Object[][] { { Items.iron_ingot, Blocks.furnace, Items.iron_ingot },
                                { null, Blocks.piston, null } });

        addNemesis(coalTiny, coalStandard);

        ModuleData solar1 = new ModuleData(1, "Solar Engine", ModuleSolarStandard.class, 20)
                .addSides(new SIDE[] { SIDE.CENTER, SIDE.TOP }).removeModel("Top").addRecipe(
                        new Object[][] {
                                { Items.iron_ingot, ComponentTypes.SOLAR_PANEL.getItemStack(), Items.iron_ingot },
                                { ComponentTypes.SOLAR_PANEL.getItemStack(), ComponentTypes.ADVANCED_PCB.getItemStack(),
                                        ComponentTypes.SOLAR_PANEL.getItemStack() },
                                { Blocks.piston, ComponentTypes.SOLAR_PANEL.getItemStack(), Blocks.piston } });

        ModuleData solar0 = new ModuleData(45, "Basic Solar Engine", ModuleSolarBasic.class, 12)
                .addSides(new SIDE[] { SIDE.CENTER, SIDE.TOP }).removeModel("Top").addRecipe(
                        new Object[][] {
                                { ComponentTypes.SOLAR_PANEL.getItemStack(), Items.iron_ingot,
                                        ComponentTypes.SOLAR_PANEL.getItemStack() },
                                { Items.iron_ingot, ComponentTypes.SIMPLE_PCB.getItemStack(), Items.iron_ingot },
                                { null, Blocks.piston, null } });

        ModuleData compactsolar = new ModuleData(56, "Compact Solar Engine", ModuleSolarCompact.class, 32)
                .addSides(new SIDE[] { SIDE.RIGHT, SIDE.LEFT }).addRecipe(
                        new Object[][] {
                                { ComponentTypes.ADVANCED_SOLAR_PANEL.getItemStack(), Items.iron_ingot,
                                        ComponentTypes.ADVANCED_SOLAR_PANEL.getItemStack() },
                                { ComponentTypes.ADVANCED_PCB.getItemStack(), Items.redstone,
                                        ComponentTypes.ADVANCED_PCB.getItemStack() },
                                { Blocks.piston, Items.iron_ingot, Blocks.piston } });

        new ModuleData(2, "Side Chests", ModuleSideChests.class, 3).addSides(new SIDE[] { SIDE.RIGHT, SIDE.LEFT })
                .addRecipe(
                        new Object[][] {
                                { ComponentTypes.HUGE_CHEST_PANE.getItemStack(),
                                        ComponentTypes.CHEST_PANE.getItemStack(),
                                        ComponentTypes.HUGE_CHEST_PANE.getItemStack() },
                                { ComponentTypes.LARGE_CHEST_PANE.getItemStack(),
                                        ComponentTypes.CHEST_LOCK.getItemStack(),
                                        ComponentTypes.LARGE_CHEST_PANE.getItemStack() },
                                { ComponentTypes.HUGE_CHEST_PANE.getItemStack(),
                                        ComponentTypes.CHEST_PANE.getItemStack(),
                                        ComponentTypes.HUGE_CHEST_PANE.getItemStack() } });
        new ModuleData(3, "Top Chest", ModuleTopChest.class, 6).addSide(SIDE.TOP).addRecipe(
                new Object[][] {
                        { ComponentTypes.HUGE_CHEST_PANE.getItemStack(), ComponentTypes.HUGE_CHEST_PANE.getItemStack(),
                                ComponentTypes.HUGE_CHEST_PANE.getItemStack() },
                        { ComponentTypes.CHEST_PANE.getItemStack(), ComponentTypes.CHEST_LOCK.getItemStack(),
                                ComponentTypes.CHEST_PANE.getItemStack() },
                        { ComponentTypes.HUGE_CHEST_PANE.getItemStack(), ComponentTypes.HUGE_CHEST_PANE.getItemStack(),
                                ComponentTypes.HUGE_CHEST_PANE.getItemStack() } });
        ModuleData frontChest = new ModuleData(4, "Front Chest", ModuleFrontChest.class, 5).addSide(SIDE.FRONT)
                .addRecipe(
                        new Object[][] {
                                { ComponentTypes.CHEST_PANE.getItemStack(),
                                        ComponentTypes.LARGE_CHEST_PANE.getItemStack(),
                                        ComponentTypes.CHEST_PANE.getItemStack() },
                                { ComponentTypes.CHEST_PANE.getItemStack(), ComponentTypes.CHEST_LOCK.getItemStack(),
                                        ComponentTypes.CHEST_PANE.getItemStack() },
                                { ComponentTypes.LARGE_CHEST_PANE.getItemStack(),
                                        ComponentTypes.LARGE_CHEST_PANE.getItemStack(),
                                        ComponentTypes.LARGE_CHEST_PANE.getItemStack() } });

        new ModuleData(5, "Internal Storage", ModuleInternalStorage.class, 25).setAllowDuplicate().addRecipe(
                new Object[][] {
                        { ComponentTypes.CHEST_PANE.getItemStack(), ComponentTypes.CHEST_PANE.getItemStack(),
                                ComponentTypes.CHEST_PANE.getItemStack() },
                        { ComponentTypes.CHEST_PANE.getItemStack(), ComponentTypes.CHEST_LOCK.getItemStack(),
                                ComponentTypes.CHEST_PANE.getItemStack() },
                        { ComponentTypes.CHEST_PANE.getItemStack(), ComponentTypes.CHEST_PANE.getItemStack(),
                                ComponentTypes.CHEST_PANE.getItemStack() } });

        if (StevesCarts.isRailcraftLoaded) {
            ModuleData tc = new ModuleData(116, "Train Connector", ModuleTrainStorage.class, 30).addRecipe(
                    new Object[][] {
                            { ComponentTypes.LARGE_DYNAMIC_PANE.getItemStack(), Blocks.hopper,
                                    ComponentTypes.LARGE_DYNAMIC_PANE.getItemStack() },
                            { ComponentTypes.HUGE_DYNAMIC_PANE.getItemStack(), ComponentTypes.CHEST_LOCK.getItemStack(),
                                    ComponentTypes.HUGE_DYNAMIC_PANE.getItemStack() },
                            { ComponentTypes.LARGE_DYNAMIC_PANE.getItemStack(), Blocks.hopper,
                                    ComponentTypes.LARGE_DYNAMIC_PANE.getItemStack() } });
        }

        new ModuleData(6, "Extracting Chests", ModuleExtractingChests.class, 75)
                .addSides(new SIDE[] { SIDE.CENTER, SIDE.RIGHT, SIDE.LEFT }).addRecipe(
                        new Object[][] {
                                { ComponentTypes.HUGE_IRON_PANE.getItemStack(),
                                        ComponentTypes.HUGE_IRON_PANE.getItemStack(),
                                        ComponentTypes.HUGE_IRON_PANE.getItemStack() },
                                { ComponentTypes.LARGE_IRON_PANE.getItemStack(),
                                        ComponentTypes.CHEST_LOCK.getItemStack(),
                                        ComponentTypes.LARGE_IRON_PANE.getItemStack() },
                                { ComponentTypes.HUGE_DYNAMIC_PANE.getItemStack(),
                                        ComponentTypes.LARGE_DYNAMIC_PANE.getItemStack(),
                                        ComponentTypes.HUGE_DYNAMIC_PANE.getItemStack() } });

        new ModuleData(7, "Torch Placer", ModuleTorch.class, 14).addSides(new SIDE[] { SIDE.RIGHT, SIDE.LEFT })
                .addRecipe(
                        new Object[][] {
                                { ComponentTypes.TRI_TORCH.getItemStack(), null,
                                        ComponentTypes.TRI_TORCH.getItemStack() },
                                { Items.iron_ingot, null, Items.iron_ingot },
                                { Items.iron_ingot, Items.iron_ingot, Items.iron_ingot } });

        ModuleData drill = new ModuleDataTool(8, "Basic Drill", ModuleDrillDiamond.class, 10, false).addSide(SIDE.FRONT)
                .addRecipe(
                        new Object[][] { { Items.iron_ingot, Items.diamond, null },
                                { null, Items.iron_ingot, Items.diamond }, { Items.iron_ingot, Items.diamond, null } });

        ModuleData ironDrill = new ModuleDataTool(42, "Iron Drill", ModuleDrillIron.class, 3, false).addSide(SIDE.FRONT)
                .addRecipe(
                        new Object[][] { { Items.iron_ingot, Items.iron_ingot, null },
                                { null, Items.iron_ingot, Items.iron_ingot },
                                { Items.iron_ingot, Items.iron_ingot, null } });

        ModuleData hardeneddrill = new ModuleDataTool(43, "Hardened Drill", ModuleDrillHardened.class, 45, false)
                .addSide(SIDE.FRONT).addRecipe(
                        new Object[][] {
                                { ComponentTypes.HARDENED_MESH.getItemStack(),
                                        ComponentTypes.REINFORCED_METAL.getItemStack(), null },
                                { Blocks.diamond_block, drill.getItemStack(),
                                        ComponentTypes.REINFORCED_METAL.getItemStack() },
                                { ComponentTypes.HARDENED_MESH.getItemStack(),
                                        ComponentTypes.REINFORCED_METAL.getItemStack(), null } });

        ModuleData galgdrill = new ModuleDataTool(9, "Galgadorian Drill", ModuleDrillGalgadorian.class, 150, true)
                .addSide(SIDE.FRONT).addRecipe(
                        new Object[][] {
                                { ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                        ComponentTypes.ENHANCED_GALGADORIAN_METAL.getItemStack(), null },
                                { Blocks.diamond_block, hardeneddrill.getItemStack(),
                                        ComponentTypes.ENHANCED_GALGADORIAN_METAL.getItemStack() },
                                { ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                        ComponentTypes.ENHANCED_GALGADORIAN_METAL.getItemStack(), null } });

        ModuleDataGroup drillGroup = new ModuleDataGroup(Localization.MODULE_INFO.DRILL_GROUP);
        drillGroup.add(drill);
        drillGroup.add(ironDrill);
        drillGroup.add(hardeneddrill);
        drillGroup.add(galgdrill);

        ModuleData railer = new ModuleData(10, "Railer", ModuleRailer.class, 3)/* .addSide(SIDE.TOP) */
                .addRecipe(
                        new Object[][] { { Blocks.stone, Blocks.stone, Blocks.stone },
                                { Items.iron_ingot, Blocks.rail, Items.iron_ingot },
                                { Blocks.stone, Blocks.stone, Blocks.stone } });
        ModuleData largerailer = new ModuleData(11, "Large Railer", ModuleRailerLarge.class, 17)/* .addSide(SIDE.TOP) */
                .addRecipe(
                        new Object[][] { { Items.iron_ingot, Items.iron_ingot, Items.iron_ingot },
                                { railer.getItemStack(), Blocks.rail, railer.getItemStack() },
                                { Items.iron_ingot, Items.iron_ingot, Items.iron_ingot } });
        addNemesis(railer, largerailer);

        new ModuleData(12, "Bridge Builder", ModuleBridge.class, 14).addRecipe(
                new Object[][] { { null, Items.redstone, null },
                        { Blocks.brick_block, ComponentTypes.SIMPLE_PCB.getItemStack(), Blocks.brick_block },
                        { null, Blocks.piston, null } });
        new ModuleData(13, "Track Remover", ModuleRemover.class, 8).addSides(new SIDE[] { SIDE.TOP, SIDE.BACK })
                .addRecipe(
                        new Object[][] { { Items.iron_ingot, Items.iron_ingot, Items.iron_ingot },
                                { Items.iron_ingot, null, Items.iron_ingot }, { Items.iron_ingot, null, null } });

        ModuleDataGroup farmerGroup = new ModuleDataGroup(Localization.MODULE_INFO.FARMER_GROUP);

        ModuleData farmerbasic = new ModuleDataTool(14, "Basic Farmer", ModuleFarmerDiamond.class, 36, false)
                .addSide(SIDE.FRONT).addRecipe(
                        new Object[][] { { Items.diamond, Items.diamond, Items.diamond },
                                { null, Items.iron_ingot, null }, { ComponentTypes.SIMPLE_PCB.getItemStack(),
                                        Items.gold_ingot, ComponentTypes.SIMPLE_PCB.getItemStack() } });

        ModuleData farmergalg = new ModuleDataTool(84, "Galgadorian Farmer", ModuleFarmerGalgadorian.class, 55, true)
                .addSide(SIDE.FRONT)
                .addRecipe(
                        new Object[][] {
                                { ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                        ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                        ComponentTypes.GALGADORIAN_METAL.getItemStack() },
                                { null, ComponentTypes.REINFORCED_METAL.getItemStack(), null },
                                { ComponentTypes.ADVANCED_PCB.getItemStack(), Items.gold_ingot,
                                        ComponentTypes.ADVANCED_PCB.getItemStack() } })
                .addRecipe(
                        new Object[][] {
                                { ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                        ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                        ComponentTypes.GALGADORIAN_METAL.getItemStack() },
                                { null, farmerbasic.getItemStack(), null },
                                { null, ComponentTypes.SIMPLE_PCB.getItemStack(), null } });

        farmerGroup.add(farmerbasic);
        farmerGroup.add(farmergalg);

        ModuleDataGroup woodcutterGroup = new ModuleDataGroup(Localization.MODULE_INFO.CUTTER_GROUP);

        ModuleData woodcutter = new ModuleDataTool(15, "Basic Wood Cutter", ModuleWoodcutterDiamond.class, 34, false)
                .addSide(SIDE.FRONT).addRecipe(
                        new Object[][] {
                                { ComponentTypes.SAW_BLADE.getItemStack(), ComponentTypes.SAW_BLADE.getItemStack(),
                                        ComponentTypes.SAW_BLADE.getItemStack() },
                                { ComponentTypes.SAW_BLADE.getItemStack(), Items.iron_ingot,
                                        ComponentTypes.SAW_BLADE.getItemStack() },
                                { null, ComponentTypes.WOOD_CUTTING_CORE.getItemStack(), null } });

        ModuleData woodcutterHardened = new ModuleDataTool(
                79,
                "Hardened Wood Cutter",
                ModuleWoodcutterHardened.class,
                65,
                false).addSide(SIDE.FRONT)
                        .addRecipe(
                                new Object[][] {
                                        { ComponentTypes.HARDENED_SAW_BLADE.getItemStack(),
                                                ComponentTypes.HARDENED_SAW_BLADE.getItemStack(),
                                                ComponentTypes.HARDENED_SAW_BLADE.getItemStack() },
                                        { ComponentTypes.HARDENED_SAW_BLADE.getItemStack(), Items.diamond,
                                                ComponentTypes.HARDENED_SAW_BLADE.getItemStack() },
                                        { null, ComponentTypes.WOOD_CUTTING_CORE.getItemStack(), null } })
                        .addRecipe(
                                new Object[][] {
                                        { ComponentTypes.REINFORCED_METAL.getItemStack(),
                                                ComponentTypes.REINFORCED_METAL.getItemStack(),
                                                ComponentTypes.REINFORCED_METAL.getItemStack() },
                                        { ComponentTypes.REINFORCED_METAL.getItemStack(), Items.iron_ingot,
                                                ComponentTypes.REINFORCED_METAL.getItemStack() },
                                        { null, woodcutter.getItemStack(), null } });

        ModuleData woodcutterGalgadorian = new ModuleDataTool(
                80,
                "Galgadorian Wood Cutter",
                ModuleWoodcutterGalgadorian.class,
                120,
                true).addSide(SIDE.FRONT)
                        .addRecipe(
                                new Object[][] {
                                        { ComponentTypes.GALGADORIAN_SAW_BLADE.getItemStack(),
                                                ComponentTypes.GALGADORIAN_SAW_BLADE.getItemStack(),
                                                ComponentTypes.GALGADORIAN_SAW_BLADE.getItemStack() },
                                        { ComponentTypes.GALGADORIAN_SAW_BLADE.getItemStack(),
                                                ComponentTypes.REINFORCED_METAL.getItemStack(),
                                                ComponentTypes.GALGADORIAN_SAW_BLADE.getItemStack() },
                                        { null, ComponentTypes.WOOD_CUTTING_CORE.getItemStack(), null } })
                        .addRecipe(
                                new Object[][] {
                                        { ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                                ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                                ComponentTypes.GALGADORIAN_METAL.getItemStack() },
                                        { ComponentTypes.GALGADORIAN_METAL.getItemStack(), Items.iron_ingot,
                                                ComponentTypes.GALGADORIAN_METAL.getItemStack() },
                                        { null, woodcutterHardened.getItemStack(), null } });

        woodcutterGroup.add(woodcutter);
        woodcutterGroup.add(woodcutterHardened);
        woodcutterGroup.add(woodcutterGalgadorian);

        ModuleDataGroup tankGroup = new ModuleDataGroup(Localization.MODULE_INFO.TANK_GROUP);
        new ModuleData(16, "Hydrator", ModuleHydrater.class, 6).addRequirement(tankGroup).addRecipe(
                new Object[][] { { Items.iron_ingot, Items.glass_bottle, Items.iron_ingot },
                        { null, Blocks.fence, null }, });

        new ModuleData(18, "Fertilizer", ModuleFertilizer.class, 10).addRecipe(
                new Object[][] { { bonemeal, null, bonemeal },
                        { Items.glass_bottle, Items.leather, Items.glass_bottle },
                        { Items.leather, ComponentTypes.SIMPLE_PCB.getItemStack(), Items.leather } });

        new ModuleData(19, "Height Controller", ModuleHeightControl.class, 20).addRecipe(
                new Object[][] { { null, Items.compass, null },
                        { Items.paper, ComponentTypes.SIMPLE_PCB.getItemStack(), Items.paper },
                        { Items.paper, Items.paper, Items.paper } });
        ModuleData liquidsensors = new ModuleData(20, "Liquid Sensors", ModuleLiquidSensors.class, 27)
                .addRequirement(drillGroup).addRecipe(
                        new Object[][] { { Items.redstone, null, Items.redstone },
                                { Items.lava_bucket, Items.diamond, Items.water_bucket },
                                { Items.iron_ingot, ComponentTypes.ADVANCED_PCB.getItemStack(), Items.iron_ingot } });

        ModuleData seat = new ModuleData(25, "Seat", ModuleSeat.class, 3).addSides(new SIDE[] { SIDE.CENTER, SIDE.TOP })
                .addRecipe(new Object[][] { { null, planks }, { null, planks }, { woodSingleSlab, planks } });

        new ModuleData(26, "Brake Handle", ModuleBrake.class, 12).addSide(SIDE.RIGHT).addParent(seat).addRecipe(
                new Object[][] { { null, null, new ItemStack(Items.dye, 1, 1) },
                        { Items.iron_ingot, ComponentTypes.REFINED_HANDLE.getItemStack(), null },
                        { Items.redstone, Items.iron_ingot, null } });

        new ModuleData(27, "Advanced Control System", ModuleAdvControl.class, 38).addSide(SIDE.RIGHT).addParent(seat)
                .addRecipe(
                        new Object[][] { { null, ComponentTypes.GRAPHICAL_INTERFACE.getItemStack(), null },
                                { Items.redstone, ComponentTypes.WHEEL.getItemStack(), Items.redstone },
                                { Items.iron_ingot, Items.iron_ingot, ComponentTypes.SPEED_HANDLE.getItemStack() } });

        ModuleDataGroup detectorGroup = new ModuleDataGroup(Localization.MODULE_INFO.ENTITY_GROUP);

        ModuleData shooter = new ModuleData(28, "Shooter", ModuleShooter.class, 15).addSide(SIDE.TOP).addRecipe(
                new Object[][] {
                        { ComponentTypes.PIPE.getItemStack(), ComponentTypes.PIPE.getItemStack(),
                                ComponentTypes.PIPE.getItemStack() },
                        { ComponentTypes.PIPE.getItemStack(), ComponentTypes.SHOOTING_STATION.getItemStack(),
                                ComponentTypes.PIPE.getItemStack() },
                        { ComponentTypes.PIPE.getItemStack(), ComponentTypes.PIPE.getItemStack(),
                                ComponentTypes.PIPE.getItemStack() } });

        ModuleData advshooter = new ModuleData(29, "Advanced Shooter", ModuleShooterAdv.class, 50).addSide(SIDE.TOP)
                .addRequirement(detectorGroup).addRecipe(
                        new Object[][] { { null, ComponentTypes.ENTITY_SCANNER.getItemStack(), null },
                                { null, ComponentTypes.SHOOTING_STATION.getItemStack(),
                                        ComponentTypes.PIPE.getItemStack() },
                                { Items.iron_ingot, ComponentTypes.ENTITY_ANALYZER.getItemStack(),
                                        Items.iron_ingot } });

        ModuleDataGroup shooterGroup = new ModuleDataGroup(Localization.MODULE_INFO.SHOOTER_GROUP);
        shooterGroup.add(shooter);
        shooterGroup.add(advshooter);

        ModuleData animal = new ModuleData(21, "Entity Detector: Animal", ModuleAnimal.class, 1).addParent(advshooter)
                .addRecipe(new Object[][] { { Items.porkchop }, { ComponentTypes.EMPTY_DISK.getItemStack() } });
        ModuleData player = new ModuleData(22, "Entity Detector: Player", ModulePlayer.class, 7).addParent(advshooter)
                .addRecipe(new Object[][] { { Items.diamond }, { ComponentTypes.EMPTY_DISK.getItemStack() } });
        ModuleData villager = new ModuleData(23, "Entity Detector: Villager", ModuleVillager.class, 1)
                .addParent(advshooter)
                .addRecipe(new Object[][] { { Items.emerald }, { ComponentTypes.EMPTY_DISK.getItemStack() } });
        ModuleData monster = new ModuleData(24, "Entity Detector: Monster", ModuleMonster.class, 1)
                .addParent(advshooter)
                .addRecipe(new Object[][] { { Items.slime_ball }, { ComponentTypes.EMPTY_DISK.getItemStack() } });
        ModuleData bats = new ModuleData(48, "Entity Detector: Bat", ModuleBat.class, 1).addParent(advshooter)
                .addRecipe(new Object[][] { { Blocks.pumpkin }, { ComponentTypes.EMPTY_DISK.getItemStack() } });
        if (!StevesCarts.isHalloween) {
            bats.lock();
        }

        detectorGroup.add(animal);
        detectorGroup.add(player);
        detectorGroup.add(villager);
        detectorGroup.add(monster);
        detectorGroup.add(bats);

        ModuleData cleaner = new ModuleData(30, "Cleaning Machine", ModuleCleaner.class, 23).addSide(SIDE.CENTER)
                .addRecipe(
                        new Object[][] {
                                { ComponentTypes.CLEANING_TUBE.getItemStack(),
                                        ComponentTypes.CLEANING_CORE.getItemStack(),
                                        ComponentTypes.CLEANING_TUBE.getItemStack() },
                                { ComponentTypes.CLEANING_TUBE.getItemStack(), null,
                                        ComponentTypes.CLEANING_TUBE.getItemStack() },
                                { ComponentTypes.CLEANING_TUBE.getItemStack(), null,
                                        ComponentTypes.CLEANING_TUBE.getItemStack() } });

        addNemesis(frontChest, cleaner);

        new ModuleData(31, "Dynamite Carrier", ModuleDynamite.class, 3).addSide(SIDE.TOP).addRecipe(
                new Object[][] { { null, ComponentTypes.DYNAMITE.getItemStack(), null },
                        { ComponentTypes.DYNAMITE.getItemStack(), Items.flint_and_steel,
                                ComponentTypes.DYNAMITE.getItemStack() },
                        { null, ComponentTypes.DYNAMITE.getItemStack(), null } });

        new ModuleData(32, "Divine Shield", ModuleShield.class, 60).addRecipe(
                new Object[][] { { Blocks.obsidian, ComponentTypes.REFINED_HARDENER.getItemStack(), Blocks.obsidian },
                        { ComponentTypes.REFINED_HARDENER.getItemStack(), Blocks.diamond_block,
                                ComponentTypes.REFINED_HARDENER.getItemStack() },
                        { Blocks.obsidian, ComponentTypes.REFINED_HARDENER.getItemStack(), Blocks.obsidian } });

        ModuleData melter = new ModuleData(33, "Melter", ModuleMelter.class, 10).addRecipe(
                new Object[][] { { Blocks.nether_brick, Blocks.glowstone, Blocks.nether_brick },
                        { Items.glowstone_dust, Blocks.furnace, Items.glowstone_dust },
                        { Blocks.nether_brick, Blocks.glowstone, Blocks.nether_brick } });
        ModuleData extrememelter = new ModuleData(34, "Extreme Melter", ModuleMelterExtreme.class, 19).addRecipe(
                new Object[][] { { Blocks.nether_brick, Blocks.obsidian, Blocks.nether_brick },
                        { melter.getItemStack(), Items.lava_bucket, melter.getItemStack() },
                        { Blocks.nether_brick, Blocks.obsidian, Blocks.nether_brick } });
        addNemesis(melter, extrememelter);

        new ModuleData(36, "Invisibility Core", ModuleInvisible.class, 21)
                .addRecipe(
                        new Object[][] { { null, ComponentTypes.GLASS_O_MAGIC.getItemStack(), null },
                                { ComponentTypes.GLASS_O_MAGIC.getItemStack(), Items.ender_eye,
                                        ComponentTypes.GLASS_O_MAGIC.getItemStack() },
                                { null, Items.golden_carrot, null } });

        new ModuleDataHull(37, "Wooden Hull", ModuleWood.class).setCapacity(50).setEngineMax(1).setAddonMax(0)
                .setComplexityMax(15).addRecipe(
                        new Object[][] { { planks, null, planks }, { planks, planks, planks },
                                { ComponentTypes.WOODEN_WHEELS.getItemStack(), null,
                                        ComponentTypes.WOODEN_WHEELS.getItemStack() } });

        new ModuleDataHull(38, "Standard Hull", ModuleStandard.class).setCapacity(200).setEngineMax(3).setAddonMax(6)
                .setComplexityMax(50).addRecipe(
                        new Object[][] { { Items.iron_ingot, null, Items.iron_ingot },
                                { Items.iron_ingot, Items.iron_ingot, Items.iron_ingot },
                                { ComponentTypes.IRON_WHEELS.getItemStack(), null,
                                        ComponentTypes.IRON_WHEELS.getItemStack() } });

        ModuleData reinfhull = new ModuleDataHull(39, "Reinforced Hull", ModuleReinforced.class).setCapacity(500)
                .setEngineMax(5).setAddonMax(12).setComplexityMax(150).addRecipe(
                        new Object[][] {
                                { ComponentTypes.REINFORCED_METAL.getItemStack(), null,
                                        ComponentTypes.REINFORCED_METAL.getItemStack() },
                                { ComponentTypes.REINFORCED_METAL.getItemStack(),
                                        ComponentTypes.REINFORCED_METAL.getItemStack(),
                                        ComponentTypes.REINFORCED_METAL.getItemStack() },
                                { ComponentTypes.REINFORCED_WHEELS.getItemStack(), null,
                                        ComponentTypes.REINFORCED_WHEELS.getItemStack() } });

        ModuleData pumpkinhull = new ModuleDataHull(47, "Pumpkin chariot", ModulePumpkin.class).setCapacity(40)
                .setEngineMax(1).setAddonMax(0).setComplexityMax(15).addRecipe(
                        new Object[][] { { planks, null, planks }, { planks, Blocks.pumpkin, planks },
                                { ComponentTypes.WOODEN_WHEELS.getItemStack(), null,
                                        ComponentTypes.WOODEN_WHEELS.getItemStack() } });

        if (!StevesCarts.isHalloween) {
            pumpkinhull.lock();
        }

        new ModuleDataHull(62, "Mechanical Pig", ModulePig.class).setCapacity(150).setEngineMax(2).setAddonMax(4)
                .setComplexityMax(50).addSide(SIDE.FRONT)
                .addRecipe(
                        new Object[][] { { Items.porkchop, null, Items.porkchop },
                                { Items.porkchop, Items.porkchop, Items.porkchop },
                                { ComponentTypes.IRON_WHEELS.getItemStack(), null,
                                        ComponentTypes.IRON_WHEELS.getItemStack() } })
                .addMessage(Localization.MODULE_INFO.PIG_MESSAGE);

        new ModuleDataHull(76, "Creative Hull", ModuleCheatHull.class).setCapacity(10000).setEngineMax(5)
                .setAddonMax(12).setComplexityMax(150);

        new ModuleDataHull(81, "Galgadorian Hull", ModuleGalgadorian.class).setCapacity(1000).setEngineMax(5)
                .setAddonMax(12).setComplexityMax(150).addRecipe(
                        new Object[][] {
                                { ComponentTypes.GALGADORIAN_METAL.getItemStack(), null,
                                        ComponentTypes.GALGADORIAN_METAL.getItemStack() },
                                { ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                        ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                        ComponentTypes.GALGADORIAN_METAL.getItemStack() },
                                { ComponentTypes.GALGADORIAN_WHEELS.getItemStack(), null,
                                        ComponentTypes.GALGADORIAN_WHEELS.getItemStack() } });

        StevesCarts.tabsSC2.setIcon(reinfhull.getItemStack());
        StevesCarts.tabsSC2Components.setIcon(ComponentTypes.REINFORCED_WHEELS.getItemStack());

        new ModuleData(40, "Note Sequencer", ModuleNote.class, 30).addSides(new SIDE[] { SIDE.RIGHT, SIDE.LEFT })
                .addRecipe(
                        new Object[][] { { Blocks.noteblock, null, Blocks.noteblock },
                                { Blocks.noteblock, Blocks.jukebox, Blocks.noteblock },
                                { planks, Items.redstone, planks } });

        ModuleData colorizer = new ModuleData(41, "Colorizer", ModuleColorizer.class, 15).addRecipe(
                new Object[][] {
                        { ComponentTypes.RED_PIGMENT.getItemStack(), ComponentTypes.GREEN_PIGMENT.getItemStack(),
                                ComponentTypes.BLUE_PIGMENT.getItemStack() },
                        { Items.iron_ingot, Items.redstone, Items.iron_ingot }, { null, Items.iron_ingot, null } });

        ModuleData colorRandomizer = new ModuleData(101, "Color Randomizer", ModuleColorRandomizer.class, 20).addRecipe(
                new Object[][] { { colorizer.getItemStack() }, { ComponentTypes.SIMPLE_PCB.getItemStack() } });

        // Having two modules try to set the color doesn't sound like a good idea
        addNemesis(colorizer, colorRandomizer);

        // new ModuleData(46, "Commander", realtimeModuleCommand.class, 15);

        new ModuleData(49, "Chunk Loader", ModuleChunkLoader.class, 84).addRecipe(
                new Object[][] { { null, Items.ender_pearl, null },
                        { ComponentTypes.SIMPLE_PCB.getItemStack(), Items.iron_ingot,
                                ComponentTypes.SIMPLE_PCB.getItemStack() },
                        { ComponentTypes.REINFORCED_METAL.getItemStack(), ComponentTypes.ADVANCED_PCB.getItemStack(),
                                ComponentTypes.REINFORCED_METAL.getItemStack() } });

        ModuleData gift = new ModuleData(50, "Gift Storage", ModuleGiftStorage.class, 12) {

            @Override
            public String getModuleInfoText(byte b) {
                if (b == 0) {
                    return Localization.MODULE_INFO.STORAGE_EMPTY.translate();
                } else {
                    return Localization.MODULE_INFO.GIFT_STORAGE_FULL.translate();
                }
            }

            @Override
            public String getCartInfoText(String name, byte b) {
                if (b == 0) {
                    return Localization.MODULE_INFO.STORAGE_EMPTY.translate() + " " + name;
                } else {
                    return Localization.MODULE_INFO.STORAGE_FULL.translate() + " " + name;
                }
            }
        }.addSides(new SIDE[] { SIDE.RIGHT, SIDE.LEFT }).useExtraData((byte) 1).addRecipe(
                new Object[][] {
                        { ComponentTypes.YELLOW_GIFT_RIBBON.getItemStack(), null,
                                ComponentTypes.RED_GIFT_RIBBON.getItemStack() },
                        { ComponentTypes.RED_WRAPPING_PAPER.getItemStack(), ComponentTypes.CHEST_LOCK.getItemStack(),
                                ComponentTypes.GREEN_WRAPPING_PAPER.getItemStack() },
                        { ComponentTypes.RED_WRAPPING_PAPER.getItemStack(), ComponentTypes.STUFFED_SOCK.getItemStack(),
                                ComponentTypes.GREEN_WRAPPING_PAPER.getItemStack() } });
        if (!StevesCarts.isChristmas) {
            gift.lock();
        }

        new ModuleData(51, "Projectile: Potion", ModulePotion.class, 10).addRequirement(shooterGroup)
                .addRecipe(new Object[][] { { Items.glass_bottle }, { ComponentTypes.EMPTY_DISK.getItemStack() } });
        new ModuleData(52, "Projectile: Fire Charge", ModuleFireball.class, 10).lockByDefault()
                .addRequirement(shooterGroup)
                .addRecipe(new Object[][] { { Items.fire_charge }, { ComponentTypes.EMPTY_DISK.getItemStack() } });
        new ModuleData(53, "Projectile: Egg", ModuleEgg.class, 10).addRequirement(shooterGroup)
                .addRecipe(new Object[][] { { Items.egg }, { ComponentTypes.EMPTY_DISK.getItemStack() }

                });
        ModuleData snowballshooter = new ModuleData(54, "Projectile: Snowball", ModuleSnowball.class, 10)
                .addRequirement(shooterGroup)
                .addRecipe(new Object[][] { { Items.snowball }, { ComponentTypes.EMPTY_DISK.getItemStack() } });

        if (!StevesCarts.isChristmas) {
            snowballshooter.lock();
        }

        ModuleData cake = new ModuleData(90, "Projectile: Cake", ModuleCake.class, 10).addRequirement(shooterGroup)
                .lock().addRecipe(new Object[][] { { Items.cake }, { ComponentTypes.EMPTY_DISK.getItemStack() } });

        ModuleData snowgenerator = new ModuleData(55, "Freezer", ModuleSnowCannon.class, 24).addRecipe(
                new Object[][] { { Blocks.snow, Items.water_bucket, Blocks.snow },
                        { Items.water_bucket, ComponentTypes.SIMPLE_PCB.getItemStack(), Items.water_bucket },
                        { Blocks.snow, Items.water_bucket, Blocks.snow } });
        if (!StevesCarts.isChristmas) {
            snowgenerator.lock();
        }
        addNemesis(snowgenerator, melter);
        addNemesis(snowgenerator, extrememelter);

        ModuleData cage = new ModuleData(57, "Cage", ModuleCage.class, 7).addSides(new SIDE[] { SIDE.TOP, SIDE.CENTER })
                .addRecipe(
                        new Object[][] { { Blocks.fence, Blocks.fence, Blocks.fence },
                                { Blocks.fence, ComponentTypes.SIMPLE_PCB.getItemStack(), Blocks.fence },
                                { Blocks.fence, Blocks.fence, Blocks.fence } });

        new ModuleData(58, "Crop: Nether Wart", ModuleNetherwart.class, 20).addRequirement(farmerGroup)
                .addRecipe(new Object[][] { { Items.nether_wart }, { ComponentTypes.EMPTY_DISK.getItemStack() } });

        new ModuleData(59, "Firework display", ModuleFirework.class, 45).addRecipe(
                new Object[][] { { Blocks.fence, Blocks.dispenser, Blocks.fence },
                        { Blocks.crafting_table, ComponentTypes.FUSE.getItemStack(), Blocks.crafting_table },
                        { ComponentTypes.SIMPLE_PCB.getItemStack(), Items.flint_and_steel,
                                ComponentTypes.SIMPLE_PCB.getItemStack() } });

        ModuleData cheatengine = new ModuleData(61, "Creative Engine", ModuleCheatEngine.class, 1);

        if (StevesCarts.isRailcraftLoaded) {
            ModuleData electricengineLV = new ModuleData(117, "Electric Engine LV", ModuleElectricLV.class, 25);
            ModuleData electricengineMV = new ModuleData(118, "Electric Engine MV", ModuleElectricMV.class, 50);
            ModuleData electricengineHV = new ModuleData(119, "Electric Engine HV", ModuleElectricHV.class, 75);
            engineGroup.add(electricengineLV);
            engineGroup.add(electricengineMV);
            engineGroup.add(electricengineHV);
            electricengineLV.addNemesis(electricengineMV);
            electricengineLV.addNemesis(electricengineHV);
            electricengineMV.addNemesis(electricengineLV);
            electricengineMV.addNemesis(electricengineHV);
            electricengineHV.addNemesis(electricengineLV);
            electricengineHV.addNemesis(electricengineMV);
        }

        ModuleData internalTank = new ModuleData(63, "Internal Tank", ModuleInternalTank.class, 37).setAllowDuplicate()
                .addRecipe(
                        new Object[][] {
                                { ComponentTypes.TANK_PANE.getItemStack(), ComponentTypes.TANK_PANE.getItemStack(),
                                        ComponentTypes.TANK_PANE.getItemStack() },
                                { ComponentTypes.TANK_PANE.getItemStack(), ComponentTypes.TANK_VALVE.getItemStack(),
                                        ComponentTypes.TANK_PANE.getItemStack() },
                                { ComponentTypes.TANK_PANE.getItemStack(), ComponentTypes.TANK_PANE.getItemStack(),
                                        ComponentTypes.TANK_PANE.getItemStack() } });

        ModuleData sideTank = new ModuleData(64, "Side Tanks", ModuleSideTanks.class, 10)
                .addSides(new SIDE[] { SIDE.RIGHT, SIDE.LEFT }).addRecipe(
                        new Object[][] {
                                { ComponentTypes.HUGE_TANK_PANE.getItemStack(), ComponentTypes.TANK_PANE.getItemStack(),
                                        ComponentTypes.HUGE_TANK_PANE.getItemStack() },
                                { ComponentTypes.LARGE_TANK_PANE.getItemStack(),
                                        ComponentTypes.TANK_VALVE.getItemStack(),
                                        ComponentTypes.LARGE_TANK_PANE.getItemStack() },
                                { ComponentTypes.HUGE_TANK_PANE.getItemStack(), ComponentTypes.TANK_PANE.getItemStack(),
                                        ComponentTypes.HUGE_TANK_PANE.getItemStack() } });

        ModuleData topTank = new ModuleData(65, "Top Tank", ModuleTopTank.class, 22).addSide(SIDE.TOP).addRecipe(
                new Object[][] {
                        { ComponentTypes.HUGE_TANK_PANE.getItemStack(), ComponentTypes.HUGE_TANK_PANE.getItemStack(),
                                ComponentTypes.HUGE_TANK_PANE.getItemStack() },
                        { ComponentTypes.TANK_PANE.getItemStack(), ComponentTypes.TANK_VALVE.getItemStack(),
                                ComponentTypes.TANK_PANE.getItemStack() },
                        { ComponentTypes.HUGE_TANK_PANE.getItemStack(), ComponentTypes.HUGE_TANK_PANE.getItemStack(),
                                ComponentTypes.HUGE_TANK_PANE.getItemStack() } });

        ModuleData advancedTank = new ModuleData(66, "Advanced Tank", ModuleAdvancedTank.class, 54)
                .addSides(new SIDE[] { SIDE.TOP, SIDE.CENTER }).addRecipe(
                        new Object[][] {
                                { ComponentTypes.HUGE_TANK_PANE.getItemStack(),
                                        ComponentTypes.HUGE_TANK_PANE.getItemStack(),
                                        ComponentTypes.HUGE_TANK_PANE.getItemStack() },
                                { ComponentTypes.HUGE_TANK_PANE.getItemStack(),
                                        ComponentTypes.TANK_VALVE.getItemStack(),
                                        ComponentTypes.HUGE_TANK_PANE.getItemStack() },
                                { ComponentTypes.HUGE_TANK_PANE.getItemStack(),
                                        ComponentTypes.HUGE_TANK_PANE.getItemStack(),
                                        ComponentTypes.HUGE_TANK_PANE.getItemStack() } });

        ModuleData frontTank = new ModuleData(67, "Front Tank", ModuleFrontTank.class, 15).addSide(SIDE.FRONT)
                .addRecipe(
                        new Object[][] {
                                { ComponentTypes.TANK_PANE.getItemStack(),
                                        ComponentTypes.LARGE_TANK_PANE.getItemStack(),
                                        ComponentTypes.TANK_PANE.getItemStack() },
                                { ComponentTypes.TANK_PANE.getItemStack(), ComponentTypes.TANK_VALVE.getItemStack(),
                                        ComponentTypes.TANK_PANE.getItemStack() },
                                { ComponentTypes.LARGE_TANK_PANE.getItemStack(),
                                        ComponentTypes.LARGE_TANK_PANE.getItemStack(),
                                        ComponentTypes.LARGE_TANK_PANE.getItemStack() } });

        ModuleData creativeTank = new ModuleData(72, "Creative Tank", ModuleCheatTank.class, 1).setAllowDuplicate()
                .addMessage(Localization.MODULE_INFO.OCEAN_MESSAGE);

        ModuleData topTankOpen = new ModuleData(73, "Open Tank", ModuleOpenTank.class, 31).addSide(SIDE.TOP).addRecipe(
                new Object[][] {
                        { ComponentTypes.TANK_PANE.getItemStack(), null, ComponentTypes.TANK_PANE.getItemStack() },
                        { ComponentTypes.TANK_PANE.getItemStack(), ComponentTypes.TANK_VALVE.getItemStack(),
                                ComponentTypes.TANK_PANE.getItemStack() },
                        { ComponentTypes.HUGE_TANK_PANE.getItemStack(), ComponentTypes.HUGE_TANK_PANE.getItemStack(),
                                ComponentTypes.HUGE_TANK_PANE.getItemStack() } });

        addNemesis(frontTank, cleaner);

        tankGroup.add(internalTank).add(sideTank).add(topTank).add(advancedTank).add(frontTank).add(creativeTank)
                .add(topTankOpen);

        new ModuleData(68, "Incinerator", ModuleIncinerator.class, 23).addRequirement(tankGroup)
                .addRequirement(drillGroup).addRecipe(
                        new Object[][] { { Blocks.nether_brick, Blocks.nether_brick, Blocks.nether_brick },
                                { Blocks.obsidian, Blocks.furnace, Blocks.obsidian },
                                { Blocks.nether_brick, Blocks.nether_brick, Blocks.nether_brick } });

        ModuleData thermal0 = new ModuleData(69, "Thermal Engine", ModuleThermalStandard.class, 28)
                .addRequirement(tankGroup).addRecipe(
                        new Object[][] { { Blocks.nether_brick, Blocks.nether_brick, Blocks.nether_brick },
                                { Blocks.obsidian, Blocks.furnace, Blocks.obsidian },
                                { Blocks.piston, null, Blocks.piston } });

        ModuleData thermal1 = new ModuleData(70, "Advanced Thermal Engine", ModuleThermalAdvanced.class, 58)
                .addRequirement(tankGroup.copy(2)).addRecipe(
                        new Object[][] { { Blocks.nether_brick, Blocks.nether_brick, Blocks.nether_brick },
                                { ComponentTypes.REINFORCED_METAL.getItemStack(), thermal0.getItemStack(),
                                        ComponentTypes.REINFORCED_METAL.getItemStack() },
                                { Blocks.piston, null, Blocks.piston } });
        addNemesis(thermal0, thermal1);

        ModuleData cleanerliquid = new ModuleData(71, "Liquid Cleaner", ModuleLiquidDrainer.class, 30)
                .addSide(SIDE.CENTER).addParent(liquidsensors).addRequirement(tankGroup).addRecipe(
                        new Object[][] {
                                { ComponentTypes.LIQUID_CLEANING_TUBE.getItemStack(),
                                        ComponentTypes.LIQUID_CLEANING_CORE.getItemStack(),
                                        ComponentTypes.LIQUID_CLEANING_TUBE.getItemStack() },
                                { ComponentTypes.LIQUID_CLEANING_TUBE.getItemStack(), null,
                                        ComponentTypes.LIQUID_CLEANING_TUBE.getItemStack() },
                                { ComponentTypes.LIQUID_CLEANING_TUBE.getItemStack(), null,
                                        ComponentTypes.LIQUID_CLEANING_TUBE.getItemStack() } });

        addNemesis(frontTank, cleanerliquid);
        addNemesis(frontChest, cleanerliquid);

        ItemStack yellowWool = new ItemStack(Blocks.wool, 1, 4);
        ModuleData eggBasket = new ModuleData(74, "Egg Basket", ModuleEggBasket.class, 14) {

            @Override
            public String getModuleInfoText(byte b) {
                if (b == 0) {
                    return Localization.MODULE_INFO.STORAGE_EMPTY.translate();
                } else {
                    return Localization.MODULE_INFO.EGG_STORAGE_FULL.translate();
                }
            }

            @Override
            public String getCartInfoText(String name, byte b) {
                if (b == 0) {
                    return Localization.MODULE_INFO.STORAGE_EMPTY.translate() + " " + name;
                } else {
                    return Localization.MODULE_INFO.STORAGE_FULL.translate() + " " + name;
                }
            }
        }.addSide(SIDE.TOP).useExtraData((byte) 1).addRecipe(
                new Object[][] { { yellowWool, yellowWool, yellowWool },
                        { ComponentTypes.EXPLOSIVE_EASTER_EGG.getItemStack(), ComponentTypes.CHEST_LOCK.getItemStack(),
                                ComponentTypes.BURNING_EASTER_EGG.getItemStack() },
                        { ComponentTypes.GLISTERING_EASTER_EGG.getItemStack(), ComponentTypes.BASKET.getItemStack(),
                                ComponentTypes.CHOCOLATE_EASTER_EGG.getItemStack() } });

        if (!StevesCarts.isEaster) {
            eggBasket.lock();
        }

        ModuleData intelligence = new ModuleData(75, "Drill Intelligence", ModuleDrillIntelligence.class, 21)
                .addRequirement(drillGroup).addRecipe(
                        new Object[][] { { Items.gold_ingot, Items.gold_ingot, Items.gold_ingot },
                                { Items.iron_ingot, ComponentTypes.ADVANCED_PCB.getItemStack(), Items.iron_ingot },
                                { ComponentTypes.ADVANCED_PCB.getItemStack(), Items.redstone,
                                        ComponentTypes.ADVANCED_PCB.getItemStack() } });

        new ModuleData(77, "Power Observer", ModulePowerObserver.class, 12).addRequirement(engineGroup).addRecipe(
                new Object[][] { { null, Blocks.piston, null }, { Items.iron_ingot, Items.redstone, Items.iron_ingot },
                        { Items.redstone, ComponentTypes.ADVANCED_PCB.getItemStack(), Items.redstone } });

        engineGroup.add(coalTiny);
        engineGroup.add(coalStandard);
        engineGroup.add(solar0);
        engineGroup.add(solar1);
        engineGroup.add(thermal0);
        engineGroup.add(thermal1);
        engineGroup.add(compactsolar);
        engineGroup.add(cheatengine);

        ModuleDataGroup toolGroup = ModuleDataGroup
                .getCombinedGroup(Localization.MODULE_INFO.TOOL_GROUP, drillGroup, woodcutterGroup);
        toolGroup.add(farmerGroup);

        ModuleDataGroup enchantableGroup = ModuleDataGroup
                .getCombinedGroup(Localization.MODULE_INFO.TOOL_OR_SHOOTER_GROUP, toolGroup, shooterGroup);

        new ModuleData(82, "Enchanter", ModuleEnchants.class, 72).addRequirement(enchantableGroup).addRecipe(
                new Object[][] { { null, ComponentTypes.GALGADORIAN_METAL.getItemStack(), null },
                        { Items.book, Blocks.enchanting_table, Items.book },
                        { Items.redstone, ComponentTypes.ADVANCED_PCB.getItemStack(), Items.redstone } });

        new ModuleData(83, "Ore Extractor", ModuleOreTracker.class, 80).addRequirement(drillGroup).addRecipe(
                new Object[][] { { Blocks.redstone_torch, null, Blocks.redstone_torch },
                        { ComponentTypes.EYE_OF_GALGADOR.getItemStack(),
                                ComponentTypes.GALGADORIAN_METAL.getItemStack(),
                                ComponentTypes.EYE_OF_GALGADOR.getItemStack() },
                        { Items.quartz, ComponentTypes.ADVANCED_PCB.getItemStack(), Items.quartz } });

        ModuleData flowerremover = new ModuleData(85, "Lawn Mower", ModuleFlowerRemover.class, 38)
                .addSides(new SIDE[] { SIDE.RIGHT, SIDE.LEFT }).addRecipe(
                        new Object[][] {
                                { ComponentTypes.BLADE_ARM.getItemStack(), null,
                                        ComponentTypes.BLADE_ARM.getItemStack() },
                                { null, ComponentTypes.SIMPLE_PCB.getItemStack(), null },
                                { ComponentTypes.BLADE_ARM.getItemStack(), null,
                                        ComponentTypes.BLADE_ARM.getItemStack() } });

        new ModuleData(86, "Milker", ModuleMilker.class, 26).addParent(cage).addRecipe(
                new Object[][] { { Items.wheat, Items.wheat, Items.wheat },
                        { ComponentTypes.SIMPLE_PCB.getItemStack(), Items.bucket,
                                ComponentTypes.SIMPLE_PCB.getItemStack() },
                        { null, ComponentTypes.SIMPLE_PCB.getItemStack(), null }, });

        ModuleData crafter = new ModuleData(87, "Crafter", ModuleCrafter.class, 22).setAllowDuplicate()
                .addRecipe(new Object[][] { { ComponentTypes.SIMPLE_PCB.getItemStack() }, { Blocks.crafting_table } });

        new ModuleData(88, "Tree: Exotic", ModuleModTrees.class, 30).addRequirement(woodcutterGroup).addRecipe(
                new Object[][] { { Items.glowstone_dust, null, Items.glowstone_dust },
                        { Items.redstone, Blocks.sapling, Items.redstone }, { ComponentTypes.SIMPLE_PCB.getItemStack(),
                                ComponentTypes.EMPTY_DISK.getItemStack(), ComponentTypes.SIMPLE_PCB.getItemStack() } });

        new ModuleData(102, "Crop: Exotic", ModuleModCrops.class, 30).addRequirement(farmerGroup).addRecipe(
                new Object[][] { { Items.glowstone_dust, null, Items.glowstone_dust },
                        { Items.redstone, Items.wheat_seeds, Items.redstone },
                        { ComponentTypes.SIMPLE_PCB.getItemStack(), ComponentTypes.EMPTY_DISK.getItemStack(),
                                ComponentTypes.SIMPLE_PCB.getItemStack() } });

        if (StevesCarts.isIC2Loaded) {
            new ModuleData(103, "Crop: IC2", ModuleIC2Crops.class, 55).addRequirement(farmerGroup).addRecipe(
                    new Object[][] { { Items.iron_hoe, null, Items.iron_hoe },
                            { Items.redstone, Items.wheat_seeds, Items.redstone },
                            { ComponentTypes.ADVANCED_PCB.getItemStack(), ComponentTypes.EMPTY_DISK.getItemStack(),
                                    ComponentTypes.ADVANCED_PCB.getItemStack() } });
        }

        new ModuleData(89, "Planter Range Extender", ModulePlantSize.class, 20).addRequirement(woodcutterGroup)
                .addRecipe(
                        new Object[][] { { Items.redstone, ComponentTypes.ADVANCED_PCB.getItemStack(), Items.redstone },
                                { null, Blocks.sapling, null }, { ComponentTypes.SIMPLE_PCB.getItemStack(),
                                        Blocks.sapling, ComponentTypes.SIMPLE_PCB.getItemStack() } });

        new ModuleData(78, "Steve's Arcade", ModuleArcade.class, 10).addParent(seat).addRecipe(
                new Object[][] { { null, Blocks.glass_pane, null },
                        { planks, ComponentTypes.SIMPLE_PCB.getItemStack(), planks },
                        { Items.redstone, planks, Items.redstone } });

        ModuleData smelter = new ModuleData(91, "Smelter", ModuleSmelter.class, 22).setAllowDuplicate()
                .addRecipe(new Object[][] { { ComponentTypes.SIMPLE_PCB.getItemStack() }, { Blocks.furnace } });

        new ModuleData(92, "Advanced Crafter", ModuleCrafterAdv.class, 42).setAllowDuplicate().addRecipe(
                new Object[][] { { null, Items.diamond, null },
                        { null, ComponentTypes.ADVANCED_PCB.getItemStack(), null },
                        { ComponentTypes.SIMPLE_PCB.getItemStack(), crafter.getItemStack(),
                                ComponentTypes.SIMPLE_PCB.getItemStack() } });

        new ModuleData(93, "Advanced Smelter", ModuleSmelterAdv.class, 42).setAllowDuplicate().addRecipe(
                new Object[][] { { null, Items.diamond, null },
                        { null, ComponentTypes.ADVANCED_PCB.getItemStack(), null },
                        { ComponentTypes.SIMPLE_PCB.getItemStack(), smelter.getItemStack(),
                                ComponentTypes.SIMPLE_PCB.getItemStack() } });

        new ModuleData(94, "Information Provider", ModuleLabel.class, 12).addRecipe(
                new Object[][] { { Blocks.glass_pane, Blocks.glass_pane, Blocks.glass_pane },
                        { Items.iron_ingot, Items.glowstone_dust, Items.iron_ingot },
                        { ComponentTypes.SIMPLE_PCB.getItemStack(), Items.sign,
                                ComponentTypes.SIMPLE_PCB.getItemStack() } });

        new ModuleData(95, "Experience Bank", ModuleExperience.class, 36).addRecipe(
                new Object[][] { { null, Items.redstone, null },
                        { Items.glowstone_dust, Items.emerald, Items.glowstone_dust },
                        { ComponentTypes.SIMPLE_PCB.getItemStack(), Items.cauldron,
                                ComponentTypes.SIMPLE_PCB.getItemStack() } });

        new ModuleData(96, "Creative Incinerator", ModuleCreativeIncinerator.class, 1).addRequirement(drillGroup);

        new ModuleData(97, "Creative Supplies", ModuleCreativeSupplies.class, 1);

        new ModuleData(99, "Cake Server", ModuleCakeServer.class, 10).addSide(SIDE.TOP)
                .addMessage(Localization.MODULE_INFO.ALPHA_MESSAGE).addRecipe(
                        new Object[][] { { null, Items.cake, null }, { "slabWood", "slabWood", "slabWood" },
                                { null, ComponentTypes.SIMPLE_PCB.getItemStack(), null } });

        ModuleData trickOrTreat = new ModuleData(100, "Trick-or-Treat Cake Server", ModuleCakeServerDynamite.class, 15)
                .addSide(SIDE.TOP).addRecipe(
                        new Object[][] { { null, Items.cake, null }, { "slabWood", "slabWood", "slabWood" },
                                { ComponentTypes.DYNAMITE.getItemStack(), ComponentTypes.SIMPLE_PCB.getItemStack(),
                                        ComponentTypes.DYNAMITE.getItemStack() } });

        if (!StevesCarts.isHalloween) {
            trickOrTreat.lock();
        }

        // new ModuleData(98, "Pew Pew", ModuleShooterAdvSide.class, 1);

        // new ModuleData(35, "Computer", ModuleComputer.class, 16);

        // System.out.println("Module Combinations: " + calculateCombinations());
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        // Coal engine
        moduleList.get((byte) 0).addModel("Engine", new ModelEngineFrame()).addModel("Fire", new ModelEngineInside());

        // Tiny coal engine
        moduleList.get((byte) 44).addModel("Engine", new ModelEngineFrame()).addModel("Fire", new ModelEngineInside());

        // solar engine
        moduleList.get((byte) 1).addModel("SolarPanelBase", new ModelSolarPanelBase())
                .addModel("SolarPanels", new ModelSolarPanelHeads(4));

        moduleList.get((byte) 45).addModel("SolarPanelBase", new ModelSolarPanelBase())
                .addModel("SolarPanels", new ModelSolarPanelHeads(2));

        moduleList.get((byte) 56).addModel("SolarPanelSide", new ModelCompactSolarPanel());

        moduleList.get((byte) 2).addModel("SideChest", new ModelSideChests());

        moduleList.get((byte) 3).removeModel("Top").addModel("TopChest", new ModelTopChest());

        moduleList.get((byte) 4).addModel("FrontChest", new ModelFrontChest()).setModelMult(0.68F);

        moduleList.get((byte) 6).addModel("SideChest", new ModelExtractingChests());

        moduleList.get((byte) 7).addModel("Torch", new ModelTorchplacer());

        moduleList.get((byte) 8)
                .addModel("Drill", new ModelDrill(ResourceHelper.getResource("/models/drillModelDiamond.png")))
                .addModel("Plate", new ModelToolPlate());

        moduleList.get((byte) 42)
                .addModel("Drill", new ModelDrill(ResourceHelper.getResource("/models/drillModelIron.png")))
                .addModel("Plate", new ModelToolPlate());

        moduleList.get((byte) 43)
                .addModel("Drill", new ModelDrill(ResourceHelper.getResource("/models/drillModelHardened.png")))
                .addModel("Plate", new ModelToolPlate());

        moduleList.get((byte) 9)
                .addModel("Drill", new ModelDrill(ResourceHelper.getResource("/models/drillModelMagic.png")))
                .addModel("Plate", new ModelToolPlate());

        moduleList.get((byte) 10).addModel("Rails", new ModelRailer(3));

        moduleList.get((byte) 11).addModel("Rails", new ModelRailer(6));

        moduleList.get((byte) 12).addModel("Bridge", new ModelBridge()).addModel("Plate", new ModelToolPlate());

        moduleList.get((byte) 13).addModel("Remover", new ModelTrackRemover()).setModelMult(0.60F);

        moduleList.get((byte) 14)
                .addModel("Farmer", new ModelFarmer(ResourceHelper.getResource("/models/farmerModelDiamond.png")))
                .setModelMult(0.45F);

        moduleList.get((byte) 84)
                .addModel("Farmer", new ModelFarmer(ResourceHelper.getResource("/models/farmerModelGalgadorian.png")))
                .setModelMult(0.45F);

        moduleList.get((byte) 15)
                .addModel(
                        "WoodCutter",
                        new ModelWoodCutter(ResourceHelper.getResource("/models/woodCutterModelDiamond.png")))
                .addModel("Plate", new ModelToolPlate());

        moduleList.get((byte) 79)
                .addModel(
                        "WoodCutter",
                        new ModelWoodCutter(ResourceHelper.getResource("/models/woodCutterModelHardened.png")))
                .addModel("Plate", new ModelToolPlate());

        moduleList.get((byte) 80)
                .addModel(
                        "WoodCutter",
                        new ModelWoodCutter(ResourceHelper.getResource("/models/woodCutterModelGalgadorian.png")))
                .addModel("Plate", new ModelToolPlate());

        moduleList.get((byte) 20).addModel("Sensor", new ModelLiquidSensors());

        moduleList.get((byte) 25).removeModel("Top").addModel("Chair", new ModelSeat());

        moduleList.get((byte) 26)
                .addModel("Lever", new ModelLever(ResourceHelper.getResource("/models/leverModel.png")));

        moduleList.get((byte) 27)
                .addModel("Lever", new ModelLever(ResourceHelper.getResource("/models/leverModel2.png")))
                .addModel("Wheel", new ModelWheel());

        // used for the guns model
        ArrayList<Integer> pipes = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            if (i == 4) continue;
            /*
             * if (i == 3) continue; if (i == 5) continue;
             */
            pipes.add(i);
        }

        moduleList.get((byte) 28).addModel("Rig", new ModelShootingRig()).addModel("Pipes", new ModelGun(pipes));

        moduleList.get((byte) 29).addModel("Rig", new ModelShootingRig())
                .addModel("MobDetector", new ModelMobDetector()).addModel("Pipes", new ModelSniperRifle());

        moduleList.get((byte) 30)
                .addModel("Top", new ModelHullTop(ResourceHelper.getResource("/models/cleanerModelTop.png"), false))
                .addModel("Cleaner", new ModelCleaner());

        moduleList.get((byte) 31).addModel("Tnt", new ModelDynamite());

        moduleList.get((byte) 32).addModel("Shield", new ModelShield()).setModelMult(0.68F);

        moduleList.get((byte) 37)
                .addModel("Hull", new ModelHull(ResourceHelper.getResource("/models/hullModelWooden.png")))
                .addModel("Top", new ModelHullTop(ResourceHelper.getResource("/models/hullModelWoodenTop.png")));

        moduleList.get((byte) 38)
                .addModel("Hull", new ModelHull(ResourceHelper.getResource("/models/hullModelStandard.png")))
                .addModel("Top", new ModelHullTop(ResourceHelper.getResource("/models/hullModelStandardTop.png")));

        moduleList.get((byte) 39)
                .addModel("Hull", new ModelHull(ResourceHelper.getResource("/models/hullModelLarge.png")))
                .addModel("Top", new ModelHullTop(ResourceHelper.getResource("/models/hullModelLargeTop.png")));

        moduleList.get((byte) 47)
                .addModel(
                        "Hull",
                        new ModelPumpkinHull(
                                ResourceHelper.getResource("/models/hullModelPumpkin.png"),
                                ResourceHelper.getResource("/models/hullModelWooden.png")))
                .addModel(
                        "Top",
                        new ModelPumpkinHullTop(
                                ResourceHelper.getResource("/models/hullModelPumpkinTop.png"),
                                ResourceHelper.getResource("/models/hullModelWoodenTop.png")));

        moduleList.get((byte) 62)
                .addModel("Hull", new ModelHull(ResourceHelper.getResource("/models/hullModelPig.png")))
                .addModel("Top", new ModelHullTop(ResourceHelper.getResource("/models/hullModelPigTop.png")))
                .addModel("Head", new ModelPigHead()).addModel("Tail", new ModelPigTail())
                .addModel("Helmet", new ModelPigHelmet(false)).addModel("Helmet_Overlay", new ModelPigHelmet(true));

        moduleList.get((byte) 76)
                .addModel("Hull", new ModelHull(ResourceHelper.getResource("/models/hullModelCreative.png")))
                .addModel("Top", new ModelHullTop(ResourceHelper.getResource("/models/hullModelCreativeTop.png")));

        moduleList.get((byte) 81)
                .addModel("Hull", new ModelHull(ResourceHelper.getResource("/models/hullModelGalgadorian.png")))
                .addModel("Top", new ModelHullTop(ResourceHelper.getResource("/models/hullModelGalgadorianTop.png")));

        moduleList.get((byte) 40).setModelMult(0.65F).addModel("Speakers", new ModelNote());

        moduleList.get((byte) 50).addModel("GiftStorage", new ModelGiftStorage());

        moduleList.get((byte) 57).removeModel("Top").addModel("Cage", new ModelCage(false), false)
                .addModel("Cage", new ModelCage(true), true).setModelMult(0.65F);

        moduleList.get((byte) 64).addModel("SideTanks", new ModelSideTanks());

        moduleList.get((byte) 65).addModel("TopTank", new ModelTopTank(false));

        moduleList.get((byte) 66).addModel("LargeTank", new ModelAdvancedTank()).removeModel("Top");

        moduleList.get((byte) 67).setModelMult(0.68F).addModel("FrontTank", new ModelFrontTank());

        moduleList.get((byte) 73).addModel("TopTank", new ModelTopTank(true));

        moduleList.get((byte) 71)
                .addModel("Top", new ModelHullTop(ResourceHelper.getResource("/models/cleanerModelTop.png"), false))
                .addModel("Cleaner", new ModelLiquidDrainer());

        moduleList.get((byte) 74).addModel("TopChest", new ModelEggBasket());

        moduleList.get((byte) 85).addModel("LawnMower", new ModelLawnMower()).setModelMult(0.4F);

        /*
         * moduleList.get((byte)98) .addModel("SideGuns", new ModelSideGuns());
         */

        moduleList.get((byte) 99).addModel("Cake", new ModelCake());

        moduleList.get((byte) 100).addModel("Cake", new ModelCake());
    }

    private byte id;
    private Class<? extends ModuleBase> moduleClass;
    private String name;
    private int modularCost;
    private int groupID;
    private ArrayList<SIDE> renderingSides;
    private boolean allowDuplicate;
    private ArrayList<ModuleData> nemesis = null;
    private ArrayList<ModuleDataGroup> requirement = null;
    private ModuleData parent = null;
    private boolean isValid;
    private boolean isLocked;
    private boolean defaultLock;
    private boolean hasRecipe;
    private ArrayList<Localization.MODULE_INFO> message;
    private HashMap<String, ModelCartbase> models;
    private HashMap<String, ModelCartbase> modelsPlaceholder;
    private ArrayList<String> removedModels;
    private float modelMult = 0.75F;
    private boolean useExtraData;
    private byte extraDataDefaultValue;

    public ModuleData(int id, String name, Class<? extends ModuleBase> moduleClass, int modularCost) {
        this.id = (byte) id;
        this.moduleClass = moduleClass;
        this.name = name;
        this.modularCost = modularCost;

        groupID = moduleGroups.length;
        for (int i = 0; i < moduleGroups.length; i++) {
            if (moduleGroups[i].isAssignableFrom(moduleClass)) {
                groupID = i;
                break;
            }
        }

        if (moduleList.containsKey(this.id)) {
            System.out.println(
                    "WARNING! " + name
                            + " can't be added with ID "
                            + id
                            + " since that ID is already occupied by "
                            + moduleList.get(this.id).getName());
        } else {
            moduleList.put(this.id, this);
        }
    }

    public Class<? extends ModuleBase> getModuleClass() {
        return moduleClass;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    protected ModuleData lock() {
        isLocked = true;

        return this;
    }

    public boolean getEnabledByDefault() {
        return !defaultLock;
    }

    protected ModuleData lockByDefault() {
        defaultLock = true;

        return this;
    }

    protected ModuleData setAllowDuplicate() {
        allowDuplicate = true;

        return this;
    }

    protected boolean getAllowDuplicate() {
        return allowDuplicate;
    }

    protected ModuleData addSide(SIDE side) {
        if (renderingSides == null) {
            renderingSides = new ArrayList<SIDE>();
        }
        renderingSides.add(side);

        if (side == SIDE.TOP) {
            removeModel("Rails");
        }

        return this;
    }

    public ModuleData useExtraData(byte defaultValue) {
        this.extraDataDefaultValue = defaultValue;
        this.useExtraData = true;

        return this;
    }

    public boolean isUsingExtraData() {
        return useExtraData;
    }

    public byte getDefaultExtraData() {
        return extraDataDefaultValue;
    }

    public ArrayList<SIDE> getRenderingSides() {
        return renderingSides;
    }

    protected ModuleData addSides(SIDE[] sides) {
        for (int i = 0; i < sides.length; i++) {
            addSide(sides[i]);
        }

        return this;
    }

    protected ModuleData addParent(ModuleData parent) {
        this.parent = parent;

        return this;
    }

    protected ModuleData addMessage(Localization.MODULE_INFO s) {
        if (message == null) {
            message = new ArrayList<Localization.MODULE_INFO>();
        }
        message.add(s);

        return this;
    }

    protected void addNemesis(ModuleData nemesis) {
        if (this.nemesis == null) {
            this.nemesis = new ArrayList<ModuleData>();
        }
        this.nemesis.add(nemesis);
    }

    protected ModuleData addRequirement(ModuleDataGroup requirement) {
        if (this.requirement == null) {
            this.requirement = new ArrayList<ModuleDataGroup>();
        }
        this.requirement.add(requirement);

        return this;
    }

    protected static void addNemesis(ModuleData m1, ModuleData m2) {
        m2.addNemesis(m1);
        m1.addNemesis(m2);
    }

    public float getModelMult() {
        return modelMult;
    }

    protected ModuleData setModelMult(float val) {
        modelMult = val;

        return this;
    }

    protected ModuleData addModel(String tag, ModelCartbase model) {
        addModel(tag, model, false);
        addModel(tag, model, true);
        return this;
    }

    protected ModuleData addModel(String tag, ModelCartbase model, boolean placeholder) {
        if (placeholder) {
            if (modelsPlaceholder == null) {
                modelsPlaceholder = new HashMap<String, ModelCartbase>();
            }

            modelsPlaceholder.put(tag, model);
        } else {
            if (models == null) {
                models = new HashMap<String, ModelCartbase>();
            }

            models.put(tag, model);
        }

        return this;
    }

    public HashMap<String, ModelCartbase> getModels(boolean placeholder) {
        if (placeholder) {
            return modelsPlaceholder;
        } else {
            return models;
        }
    }

    public boolean haveModels(boolean placeholder) {
        if (placeholder) {
            return modelsPlaceholder != null;
        } else {
            return models != null;
        }
    }

    protected ModuleData removeModel(String tag) {
        if (removedModels == null) {
            removedModels = new ArrayList<String>();
        }

        if (!removedModels.contains(tag)) {
            removedModels.add(tag);
        }
        return this;
    }

    public ArrayList<String> getRemovedModels() {
        return removedModels;
    }

    public boolean haveRemovedModels() {
        return removedModels != null;
    }

    public String getName() {
        return StatCollector.translateToLocal(getUnlocalizedName());
    }

    public String getUnlocalizedName() {
        return "item." + StevesCarts.localStart + getRawName() + ".name";
    }

    public byte getID() {
        return id;
    }

    public int getCost() {
        return modularCost;
    }

    protected ModuleData getParent() {
        return parent;
    }

    protected ArrayList<ModuleData> getNemesis() {
        return nemesis;
    }

    protected ArrayList<ModuleDataGroup> getRequirement() {
        return requirement;
    }

    public boolean getHasRecipe() {
        return hasRecipe;
    }

    public String getModuleInfoText(byte b) {
        return null;
    }

    public String getCartInfoText(String name, byte b) {
        return name;
    }

    public static ArrayList<ItemStack> getModularItems(ItemStack cart) {
        ArrayList<ItemStack> modules = new ArrayList<ItemStack>();

        if (cart != null && cart.getItem() == ModItems.carts && cart.getTagCompound() != null) {
            NBTTagCompound info = cart.getTagCompound();
            if (info.hasKey("Modules")) {

                byte[] IDs = info.getByteArray("Modules");
                for (int i = 0; i < IDs.length; i++) {
                    byte id = IDs[i];
                    ItemStack module = new ItemStack(ModItems.modules, 1, id);

                    ModItems.modules.addExtraDataToModule(module, info, i);

                    modules.add(module);
                }
            }
        }

        return modules;
    }

    public static ItemStack createModularCart(MinecartModular parentcart) {
        ItemStack cart = new ItemStack(ModItems.carts, 1);

        NBTTagCompound save = new NBTTagCompound();
        byte[] moduleIDs = new byte[parentcart.getModules().size()];
        for (int i = 0; i < parentcart.getModules().size(); i++) {
            ModuleBase module = parentcart.getModules().get(i);
            for (ModuleData moduledata : moduleList.values()) {
                if (module.getClass() == moduledata.moduleClass) {
                    moduleIDs[i] = moduledata.getID();
                    break;
                }
            }

            ModItems.modules.addExtraDataToModule(save, module, i);

        }
        save.setByteArray("Modules", moduleIDs);
        cart.setTagCompound(save);
        CartVersion.addVersion(cart);

        return cart;
    }

    /*
     * public static ItemStack createModularCart(ArrayList<ModuleData> modules) { ItemStack cart = new
     * ItemStack(StevesCarts.carts, 1); NBTTagCompound save = new NBTTagCompound(); byte [] moduleIDs = new
     * byte[modules.size()]; for (int i = 0; i < moduleIDs.length; i++) { moduleIDs[i] = modules.get(i).getID(); }
     * save.setByteArray("Modules", moduleIDs); cart.setTagCompound(save); CartVersion.addVersion(cart); return cart; }
     */

    /*
     * public static ArrayList<ModuleData> getModulesFromItems(ArrayList<ItemStack> modules) { ArrayList<ModuleData>
     * moduledatas = new ArrayList<ModuleData>(); for (ItemStack module : modules) { ModuleData data =
     * StevesCarts.modules.getModuleData(module); if (data != null) { moduledatas.add(data); } } return moduledatas; }
     */

    public static ItemStack createModularCartFromItems(ArrayList<ItemStack> modules) {
        ItemStack cart = new ItemStack(ModItems.carts, 1);

        NBTTagCompound save = new NBTTagCompound();
        byte[] moduleIDs = new byte[modules.size()];
        for (int i = 0; i < moduleIDs.length; i++) {
            moduleIDs[i] = (byte) modules.get(i).getItemDamage();

            ModItems.modules.addExtraDataToCart(save, modules.get(i), i);
        }
        save.setByteArray("Modules", moduleIDs);
        cart.setTagCompound(save);
        CartVersion.addVersion(cart);

        return cart;
    }

    public static boolean isItemOfModularType(ItemStack itemstack, Class<? extends ModuleBase> validClass) {
        if (itemstack.getItem() == ModItems.modules) {
            ModuleData module = ModItems.modules.getModuleData(itemstack);
            if (module != null) {
                if (validClass.isAssignableFrom(module.moduleClass)) {
                    return true;
                }
            }
        }

        return false;
    }

    private ArrayList<Object[][]> recipes;

    protected ModuleData addRecipe(Object[][] recipe) {
        if (this.recipes == null) {
            this.recipes = new ArrayList<Object[][]>();
        }

        this.recipes.add(recipe);

        return this;
    }

    public void loadRecipe() {
        if (!isLocked) {
            isValid = true;
            if (recipes != null) {
                hasRecipe = true;
                for (Object[][] recipe : recipes) {
                    RecipeHelper.addRecipe(getItemStack(), recipe);
                }
            }
        }
    }

    public ItemStack getItemStack() {
        ItemStack module = new ItemStack(ModItems.modules, 1, id);

        if (isUsingExtraData()) {
            NBTTagCompound save = new NBTTagCompound();

            save.setByte("Data", getDefaultExtraData());

            module.setTagCompound(save);
        }

        return module;
    }

    public static boolean isValidModuleItem(int validGroup, ItemStack itemstack) {
        if (itemstack.getItem() == ModItems.modules) {

            ModuleData module = ModItems.modules.getModuleData(itemstack);
            return isValidModuleItem(validGroup, module);
        }

        return false;
    }

    public static boolean isValidModuleItem(int validGroup, ModuleData module) {
        if (module != null) {
            if (validGroup >= 0) {
                if (moduleGroups[validGroup].isAssignableFrom(module.moduleClass)) {
                    return true;
                }
            } else {
                for (int i = 0; i < moduleGroups.length; i++) {
                    if (moduleGroups[i].isAssignableFrom(module.moduleClass)) {
                        return false;
                    }
                }
                return true;
            }

        }

        return false;
    }

    public static boolean isValidModuleCombo(ModuleDataHull hull, ArrayList<ModuleData> modules) {
        int[] max = new int[] { 1, hull.getEngineMax(), 1, 4, hull.getAddonMax(), 6 };
        int[] current = new int[max.length];
        for (ModuleData module : modules) {
            int id = 5;
            for (int i = 0; i < 5; i++) {
                if (isValidModuleItem(i, module)) {
                    id = i;
                    break;
                }
            }
            current[id] += 1;
            if (current[id] > max[id]) {
                return false;
            }
        }
        return true;
    }

    private static final int MAX_MESSAGE_ROW_LENGTH = 30;

    public void addExtraMessage(List list) {
        if (message != null) {
            list.add("");
            for (Localization.MODULE_INFO m : message) {
                String str = m.translate();
                if (str.length() <= MAX_MESSAGE_ROW_LENGTH) {
                    addExtraMessage(list, str);
                } else {
                    String[] words = str.split(" ");
                    String row = "";
                    for (String word : words) {
                        String next = (row + " " + word).trim();
                        if (next.length() <= MAX_MESSAGE_ROW_LENGTH) {
                            row = next;
                        } else {
                            addExtraMessage(list, row);
                            row = word;
                        }
                    }
                    addExtraMessage(list, row);
                }
            }
        }
    }

    private void addExtraMessage(List list, String str) {
        list.add(ColorHelper.GRAY + "\u00a7o" + str + "\u00a7r");
    }

    public void addSpecificInformation(List list) {
        list.add(ColorHelper.LIGHTGRAY + Localization.MODULE_INFO.MODULAR_COST.translate() + ": " + modularCost);
    }

    public final void addInformation(List list, NBTTagCompound compound) {
        addSpecificInformation(list);
        if (compound != null && compound.hasKey("Data")) {
            String extradatainfo = getModuleInfoText(compound.getByte("Data"));
            if (extradatainfo != null) {
                list.add(ColorHelper.WHITE + extradatainfo);
            }
        }

        if (GuiScreen.isShiftKeyDown()) {

            if (getRenderingSides() == null || getRenderingSides().size() == 0) {
                list.add(ColorHelper.CYAN + Localization.MODULE_INFO.NO_SIDES.translate());
            } else {
                String sides = "";
                for (int i = 0; i < getRenderingSides().size(); i++) {
                    SIDE side = getRenderingSides().get(i);

                    if (i == 0) {
                        sides += side.toString();
                    } else if (i == getRenderingSides().size() - 1) {
                        sides += " " + Localization.MODULE_INFO.AND.translate() + " " + side.toString();
                    } else {
                        sides += ", " + side.toString();
                    }
                }

                list.add(
                        ColorHelper.CYAN + Localization.MODULE_INFO.OCCUPIED_SIDES
                                .translate(sides, String.valueOf(getRenderingSides().size())));
            }

            if (getNemesis() != null && getNemesis().size() != 0) {
                if (getRenderingSides() == null || getRenderingSides().size() == 0) {
                    list.add(ColorHelper.RED + Localization.MODULE_INFO.CONFLICT_HOWEVER.translate() + ":");
                } else {
                    list.add(ColorHelper.RED + Localization.MODULE_INFO.CONFLICT_ALSO.translate() + ":");
                }
                for (ModuleData module : getNemesis()) {
                    list.add(ColorHelper.RED + module.getName());
                }
            }

            if (parent != null) {
                list.add(
                        ColorHelper.YELLOW + Localization.MODULE_INFO.REQUIREMENT.translate() + " " + parent.getName());
            }

            if (getRequirement() != null && getRequirement().size() != 0) {
                for (ModuleDataGroup group : getRequirement()) {
                    list.add(
                            ColorHelper.YELLOW + Localization.MODULE_INFO.REQUIREMENT
                                    .translate() + " " + group.getCountName() + " " + group.getName());
                }
            }

            if (getAllowDuplicate()) {
                list.add(ColorHelper.LIME + Localization.MODULE_INFO.DUPLICATES.translate());
            }

        }

        list.add(
                ColorHelper.LIGHTBLUE + Localization.MODULE_INFO.TYPE.translate()
                        + ": "
                        + moduleGroupNames[groupID].translate());
        addExtraMessage(list);
    }

    // Sides that the different modules will claim that they occupy
    public enum SIDE {

        NONE(Localization.MODULE_INFO.SIDE_NONE),
        TOP(Localization.MODULE_INFO.SIDE_TOP),
        CENTER(Localization.MODULE_INFO.SIDE_CENTER),
        BOTTOM(Localization.MODULE_INFO.SIDE_BOTTOM),
        BACK(Localization.MODULE_INFO.SIDE_BACK),
        LEFT(Localization.MODULE_INFO.SIDE_LEFT),
        RIGHT(Localization.MODULE_INFO.SIDE_RIGHT),
        FRONT(Localization.MODULE_INFO.SIDE_FRONT);

        private Localization.MODULE_INFO name;

        private SIDE(Localization.MODULE_INFO name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name.translate();
        }
    }

    public static String checkForErrors(ModuleDataHull hull, ArrayList<ModuleData> modules) {
        // Normal errors here
        if (getTotalCost(modules) > hull.getCapacity()) {
            return Localization.MODULE_INFO.CAPACITY_ERROR.translate();
        }

        if (!isValidModuleCombo(hull, modules)) {
            return Localization.MODULE_INFO.COMBINATION_ERROR.translate();
        }

        for (int i = 0; i < modules.size(); i++) {
            ModuleData mod1 = modules.get(i);
            if (mod1.getCost() > hull.getComplexityMax()) {
                return Localization.MODULE_INFO.COMPLEXITY_ERROR.translate(mod1.getName());
            }
            if (mod1.getParent() != null && !modules.contains(mod1.getParent())) {
                return Localization.MODULE_INFO.PARENT_ERROR.translate(mod1.getName(), mod1.getParent().getName());
            }
            if (mod1.getNemesis() != null) {
                for (ModuleData nemesis : mod1.getNemesis()) {
                    if (modules.contains(nemesis)) {
                        return Localization.MODULE_INFO.NEMESIS_ERROR.translate(mod1.getName(), nemesis.getName());
                    }
                }
            }
            if (mod1.getRequirement() != null) {
                for (ModuleDataGroup group : mod1.getRequirement()) {
                    int count = 0;
                    for (ModuleData mod2 : group.getModules()) {
                        for (ModuleData mod3 : modules) {
                            if (mod2.equals(mod3)) {
                                count++;
                            }
                        }
                    }
                    if (count < group.getCount()) {
                        return Localization.MODULE_INFO.PARENT_ERROR
                                .translate(mod1.getName(), group.getCountName() + " " + group.getName());
                    }
                }
            }

            for (int j = i + 1; j < modules.size(); j++) {
                ModuleData mod2 = modules.get(j);

                if (mod1 == mod2) {
                    if (!mod1.getAllowDuplicate()) {
                        return Localization.MODULE_INFO.DUPLICATE_ERROR.translate(mod1.getName());
                    }
                } else if (mod1.getRenderingSides() != null && mod2.getRenderingSides() != null) {
                    SIDE clash = SIDE.NONE;
                    for (SIDE side1 : mod1.getRenderingSides()) {
                        for (SIDE side2 : mod2.getRenderingSides()) {
                            if (side1 == side2) {
                                clash = side1;
                                break;
                            }
                        }
                        if (clash != SIDE.NONE) {
                            break;
                        }
                    }
                    if (clash != SIDE.NONE) {
                        return Localization.MODULE_INFO.CLASH_ERROR
                                .translate(mod1.getName(), mod2.getName(), clash.toString());
                    }
                }
            }
        }

        return null;
    }

    public static int getTotalCost(ArrayList<ModuleData> modules) {
        int currentCost = 0;
        for (ModuleData module : modules) {
            currentCost += module.getCost();
        }
        return currentCost;
    }

    // Just for fun :) It's very slow but it does the trick and is solid(since it's using the built in
    // error check, this means you won't have to bother with editing this code just if some modules shouldn't work
    // together any more).
    // Calculated for alpha 23: 1,140,776,321
    private static long calculateCombinations() {
        long combinations = 0L;
        ArrayList<ModuleData> potential = new ArrayList<ModuleData>();
        for (ModuleData module : moduleList.values()) {
            if (!(module instanceof ModuleDataHull)) {
                potential.add(module);
            }
        }

        for (ModuleData module : moduleList.values()) {
            if (module instanceof ModuleDataHull) {
                ArrayList<ModuleData> modules = new ArrayList<ModuleData>();
                combinations += populateHull(
                        (ModuleDataHull) module,
                        modules,
                        (ArrayList<ModuleData>) potential.clone(),
                        0);
                System.out.println("Hull added: " + combinations);
            }
        }
        return combinations;
    }

    private static long populateHull(ModuleDataHull hull, ArrayList<ModuleData> attached,
            ArrayList<ModuleData> potential, int depth) {
        if (checkForErrors(hull, attached) != null) {
            return 0L;
        } else {
            long combinations = 1L;
            Iterator itt = potential.iterator();
            while (itt.hasNext()) {
                ModuleData module = (ModuleData) itt.next();
                ArrayList<ModuleData> attachedCopy = (ArrayList<ModuleData>) attached.clone();
                attachedCopy.add(module);
                ArrayList<ModuleData> potentialCopy = (ArrayList<ModuleData>) potential.clone();
                itt.remove();

                combinations += populateHull(hull, attachedCopy, potentialCopy, depth + 1);
                if (depth < 3) {
                    System.out.println("Modular state[" + depth + "]: " + combinations);
                }
            }
            return combinations;
        }
    }

    public String getRawName() {
        return name.replace(":", "").replace("'", "").replace(" ", "_").replace("-", "_").toLowerCase();
    }

    private IIcon icon;

    @SideOnly(Side.CLIENT)
    public void createIcon(IIconRegister register) {
        icon = register.registerIcon(StevesCarts.instance.textureHeader + ":" + getRawName() + "_icon");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon() {
        return icon;
    }

}
