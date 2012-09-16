package monoxide.Lanterns;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class TileEntityZeroTorch extends TileEntity {
	public TileEntityZeroTorch() {
	}
	
	@Override
	public boolean canUpdate() {
		return true;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (this.worldObj.isRemote) {
			System.out.println("tile updated");
		}
	}
	
	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
	}

	@Override
	public void validate() {
		super.validate();
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
	}
}
