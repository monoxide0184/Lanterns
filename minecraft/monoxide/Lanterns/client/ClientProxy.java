package monoxide.Lanterns.client;

import net.minecraftforge.client.MinecraftForgeClient;
import monoxide.Lanterns.CommonProxy;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(CommonProxy.ITEMS_PNG);
	}
}
