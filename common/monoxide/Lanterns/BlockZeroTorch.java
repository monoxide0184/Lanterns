package monoxide.Lanterns;

import java.util.Collection;
import java.util.Dictionary;

import net.minecraft.src.Block;
import net.minecraft.src.BlockTorch;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockZeroTorch extends BlockTorch {
	protected BlockZeroTorch(int par1, int par2) {
		super(par1, par2);
		setRequiresSelfNotify();
		isBlockContainer = true;
	}
	
    public TileEntity createNewTileEntity(World world) {
    	return new TileEntityZeroTorch();
    }

	@Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        par1World.setBlockTileEntity(par2, par3, par4, createNewTileEntity(par1World));
    }

	@Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        par1World.removeBlockTileEntity(par2, par3, par4);
    }
	
	@Override
    public void onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
        TileEntity var7 = par1World.getBlockTileEntity(par2, par3, par4);

        if (var7 != null)
        {
            var7.receiveClientEvent(par5, par6);
        }
    }
}
