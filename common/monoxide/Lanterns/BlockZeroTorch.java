package monoxide.Lanterns;

import java.util.Collection;
import java.util.Dictionary;

import net.minecraft.src.BlockTorch;
import net.minecraft.src.World;

public class BlockZeroTorch extends BlockTorch {
	protected BlockZeroTorch(int par1, int par2) {
		super(par1, par2);
		setRequiresSelfNotify();
	}
	
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		
		
	}
}
