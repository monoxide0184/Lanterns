package monoxide.Lanterns.client;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.ILightingHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import monoxide.Lanterns.CommonProxy;
import monoxide.Lanterns.Lanterns;

public class ClientProxy extends CommonProxy implements ILightingHandler {
	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(CommonProxy.ITEMS_PNG);
		ForgeHooksClient.registerLightingHandler(this);
	}

	@Override
	public int getLightingPriority() {
		return 10;
	}

	@Override
	public int getSkyLightLevel(int x, int y, int z, int currentLight, LightingType type) {
		return getLightLevel(x, y, z, currentLight);
	}

	@Override
	public int getBlockLightLevel(int x, int y, int z, int currentLight, LightingType type) {
		return getLightLevel(x, y, z, currentLight);
	}
	
	public int getLightLevel(int x, int y, int z, int currentLight) {
		World world = Minecraft.getMinecraft().theWorld;
		
		if (world.getBlockId(x, y, z) == Lanterns.zeroTorch.blockID) {
			List entities = world.getEntitiesWithinAABB(TileEntityChest.class, AxisAlignedBB.getBoundingBox(x-16, y-16, z-16, x+16, y+16, z+16));
		}
		
		return -1;
	}
	
	private int distance(int x1, int y1, int z1, int x2, int y2, int z2) {
		int xDiff = x2 - x1;
		int yDiff = y2 - y1;
		int zDiff = z2 - z1;
		
		return (int) Math.round(Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff));
	}
}
