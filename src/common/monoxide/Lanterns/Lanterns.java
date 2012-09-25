package monoxide.Lanterns;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.src.Block;
import net.minecraft.src.BlockTorch;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "monoxide_Lanterns", useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Lanterns {
	// The instance of your mod that Forge uses.
	@Instance("monoxide_Lanterns")
	public static Lanterns instance;
	protected static String version;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="monoxide.Lanterns.client.ClientProxy", serverSide="monoxide.Lanterns.CommonProxy")
	public static CommonProxy proxy;
	
	public static FilamentItem filament;
	protected static int filamentId;
	public static Block zeroTorch;
	protected static int zeroTorchId;
	
	public static String getVersion() {
		return version;
	}
	
	@PreInit
	public void PreInit(FMLPreInitializationEvent event) {
		version = event.getModMetadata().version;
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		try {
			config.load();
			filamentId = config.getItem("filament", 400).getInt();
			zeroTorchId = config.getBlock("zeroTorch", 500).getInt();
		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "Lanterns couldn't load it's configuration properly.");
		} finally {
			config.save();
		}
	}
	
	@Init
	public void Initialise(FMLInitializationEvent event) {
		proxy.registerRenderers();
		filament = new FilamentItem(filamentId);
		zeroTorch = (new BlockZeroTorch(zeroTorchId, 80))
				.setHardness(0.0F).setLightValue(1.0f).setStepSound(Block.soundWoodFootstep).setBlockName("torchZero");
		GameRegistry.registerBlock(zeroTorch);
		LanguageRegistry.addName(zeroTorch, "Zero Torch");
		GameRegistry.registerTileEntity(TileEntityZeroTorch.class, "torchZero");
		
		// Create regular filaments
		GameRegistry.addRecipe(
				new ItemStack(filament, 1, FilamentItem.PLAIN),
				"RRR",
				"CCC",
				'R', new ItemStack(Item.redstone),
				'C', new ItemStack(Item.coal, 1, 0)
				);
		
		// Bypass FML as it doesn't support metadata smelting recipes
		FurnaceRecipes.smelting().addSmelting(filament.shiftedIndex, FilamentItem.PLAIN, new ItemStack(filament, 1, FilamentItem.FORGED));
		
		GameRegistry.addRecipe(
				new ItemStack(filament, 1, FilamentItem.WATERPROOF),
				"CCC",
				" F ",
				'C', new ItemStack(Item.clay),
				'F', new ItemStack(filament, 1, FilamentItem.PLAIN)
				);
	}
}
