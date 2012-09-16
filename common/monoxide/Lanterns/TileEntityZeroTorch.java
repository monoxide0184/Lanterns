package monoxide.Lanterns;

import java.util.LinkedList;
import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class TileEntityZeroTorch extends TileEntity {
	public static List<TileEntityZeroTorch> allTorches = new LinkedList<TileEntityZeroTorch>();
	
	@Override
	public boolean canUpdate() {
		return false;
	}
	
	@Override
	public void onChunkUnload() {
		allTorches.remove(this);
	}

	@Override
	public void validate() {
		allTorches.add(this);
	}
	
	@Override
	public void invalidate() {
		allTorches.remove(this);
	}
}
