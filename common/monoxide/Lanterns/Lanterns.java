package monoxide.Lanterns;

import java.io.File;

import net.minecraft.src.Item;
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
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "monoxide_Lanterns", name = "Lanterns", version = "1.0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Lanterns {
	// The instance of your mod that Forge uses.
	@Instance
	public static Lanterns instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="monoxide.Lanterns.client.ClientProxy", serverSide="monoxide.Lanterns.CommonProxy")
	public static CommonProxy proxy;
	
	public static Item filament;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}

	@Init
	public void Initialise(FMLInitializationEvent event) {
		Configuration config = new Configuration(new File("config/Lanterns.cfg"));
		filament = new FilamentItem(config.getOrCreateItemIdProperty("filament", 400).getInt());
		
		config.save();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
