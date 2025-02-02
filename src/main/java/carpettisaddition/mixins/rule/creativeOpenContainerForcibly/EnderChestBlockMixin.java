package carpettisaddition.mixins.rule.creativeOpenContainerForcibly;

import carpettisaddition.helpers.rule.creativeOpenContainerForcibly.CreativeOpenContainerForciblyHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnderChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnderChestBlock.class)
public abstract class EnderChestBlockMixin
{
	@Redirect(
			method = "onUse",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/block/BlockState;isSolidBlock(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z"
			),
			require = 0
	)
	private boolean isSimpleFullBlockAndNotCreative(BlockState blockState, BlockView view, BlockPos pos, /* parent method parameters -> */ BlockState state, World world, BlockPos pos2, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		if (CreativeOpenContainerForciblyHelper.canOpenForcibly(player))
		{
			return false;
		}
		// vanilla
		return blockState.isSolidBlock(view, pos);
	}
}
