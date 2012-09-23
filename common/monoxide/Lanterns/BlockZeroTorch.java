package monoxide.Lanterns;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

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
	
	@SideOnly(Side.CLIENT)
	@Override
	/**
	 * Shamelessly ripped off from BlockTorch
	 */
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int var6 = par1World.getBlockMetadata(par2, par3, par4);
		double var7 = (double)((float)par2 + 0.5F);
		double var9 = (double)((float)par3 + 0.7F);
		double var11 = (double)((float)par4 + 0.5F);
		double var13 = 0.2199999988079071D;
		double var15 = 0.27000001072883606D;
		
		if (var6 == 1)
		{
			par1World.spawnParticle("portal", var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
		}
		else if (var6 == 2)
		{
			par1World.spawnParticle("portal", var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
		}
		else if (var6 == 3)
		{
			par1World.spawnParticle("portal", var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
		}
		else if (var6 == 4)
		{
			par1World.spawnParticle("portal", var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
		}
		else
		{
			par1World.spawnParticle("portal", var7, var9, var11, 0.0D, 0.0D, 0.0D);
		}
	}
}
