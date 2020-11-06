package carpettisaddition.logging.loggers.microtiming.events;

import carpet.utils.Messenger;
import carpettisaddition.logging.loggers.microtiming.MicroTimingLoggerManager;
import carpettisaddition.logging.loggers.microtiming.enums.EventType;
import carpettisaddition.logging.loggers.microtiming.utils.MicroTimingUtil;
import carpettisaddition.utils.TextUtil;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.text.BaseText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TickPriority;

import java.util.List;
import java.util.Objects;

public class ScheduleTileTickEvent extends BaseEvent
{
	private final Block block;
	private final BlockPos pos;
	private final int delay;
	private final TickPriority priority;
	private final Boolean success;

	public ScheduleTileTickEvent(Block block, BlockPos pos, int delay, TickPriority priority, Boolean success)
	{
		super(EventType.EVENT, "schedule_tile_tick");
		this.block = block;
		this.pos = pos;
		this.delay = delay;
		this.priority = priority;
		this.success = success;
	}

	@Override
	public BaseText toText()
	{
		List<Object> list = Lists.newArrayList();
		list.add(this.getEnclosedTranslatedBlockNameHeaderText(block));
		list.add(COLOR_ACTION + this.tr("Scheduled"));
		list.add(TextUtil.getSpaceText());
		list.add(COLOR_TARGET + this.tr("TileTick Event"));
		list.add(String.format("^w %s: %dgt\n%s: %d (%s)", MicroTimingLoggerManager.tr("Delay"), delay, MicroTimingLoggerManager.tr("Priority"), priority.getIndex(), priority));
		if (this.success != null)
		{
			list.add("w  ");
			list.add(MicroTimingUtil.getSuccessText(this.success, false));
		}
		return Messenger.c(list.toArray(new Object[0]));
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof ScheduleTileTickEvent)) return false;
		if (!super.equals(o)) return false;
		ScheduleTileTickEvent that = (ScheduleTileTickEvent) o;
		return delay == that.delay &&
				Objects.equals(block, that.block) &&
				Objects.equals(pos, that.pos) &&
				priority == that.priority &&
				Objects.equals(success, that.success);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), block, pos, delay, priority);
	}
}
