package monoxide.Lanterns;

import java.io.File;

import net.minecraft.src.Block;
import net.minecraft.src.BlockTorch;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.Configuration;
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

@Mod(modid = "monoxide_Lanterns", name = "Lanterns", version = "1.0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Lanterns {
	// The instance of your mod that Forge uses.
	@Instance("monoxide_Lanterns")
	public static Lanterns instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="monoxide.Lanterns.client.ClientProxy", serverSide="monoxide.Lanterns.CommonProxy")
	public static CommonProxy proxy;
	
	public static FilamentItem filament;
	public static Block zeroTorch;
	
	@Init
	public void Initialise(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		Configuration config = new Configuration(new File("config/Lanterns.cfg"));
		filament = new FilamentItem(config.getOrCreateItemIdProperty("filament", 400).getInt());
		zeroTorch = (new BlockZeroTorch(config.getOrCreateBlockIdProperty("zeroTorch", 500).getInt(), 80))
				.setHardness(0.0F).setLightValue(1.0f).setStepSound(Block.soundWoodFootstep).setBlockName("torchZero");
		GameRegistry.registerBlock(zeroTorch);
		LanguageRegistry.addName(zeroTorch, "Zero Torch");
		GameRegistry.registerTileEntity(TileEntityZeroTorch.class, "torchZero");
		
		config.save();
		
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
