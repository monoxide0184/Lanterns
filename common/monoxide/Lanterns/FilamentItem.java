package monoxide.Lanterns;

import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class FilamentItem extends Item {
	
	public static final int DAMAGE_PLAIN = 0;
	public static final int DAMAGE_FORGED = 1;
	public static final int DAMAGE_WATERPROOF = 2;
	
	public FilamentItem(int i) {
		super(i);
		setMaxStackSize(16);
		this.setTabToDisplayOn(CreativeTabs.tabMaterials);
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
	@Override
	public int getIconFromDamage(int damage) {
		return damage;
	}
	
	@Override
	public String getItemDisplayName(ItemStack itemStack) {
		switch(itemStack.getItemDamage()) {
		case DAMAGE_PLAIN:
			return "Filament";
		case DAMAGE_FORGED:
			return "Fired Filament";
		case DAMAGE_WATERPROOF:
			return "Waterproof Filament";
		}
		return super.getItemDisplayName(itemStack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs tabs, List subItems) {
		for (int i = 0; i < 3; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
}
