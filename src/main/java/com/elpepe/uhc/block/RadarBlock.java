package com.elpepe.uhc.block;

import com.elpepe.uhc.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RadarBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    private static final double SEARCH_DISTANCE = 100.0;

    public RadarBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(ModItems.RUBY) && !(Boolean) state.get(ACTIVE)) {
            if (world.isClient()) {
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 1.0F, 1.75F, true);
            } else {
                PlayerEntity nearest = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 100.0, (entity) -> {
                    if (!entity.equals(player) && entity instanceof PlayerEntity playerEntity) {
                        return !playerEntity.isCreative() && !playerEntity.isSpectator();
                    }

                    return false;
                });
                if (nearest != null) {
                    world.setBlockState(pos, state.with(ACTIVE, true));
                    player.sendMessage(Text.translatable("block.uhc.radar_block.found_player", 100.0));
                } else {
                    player.sendMessage(Text.translatable("block.uhc.radar_block.player_not_found", 100.0));
                }

                stack.decrement(1);
            }
        }

        return ActionResult.SUCCESS;
    }

    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(ACTIVE, false);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ACTIVE);
    }
}
