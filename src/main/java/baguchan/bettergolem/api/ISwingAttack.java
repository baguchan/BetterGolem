package baguchan.bettergolem.api;

import net.minecraft.world.entity.AnimationState;

public interface ISwingAttack {
	void setSwingAttack(boolean paramBoolean);

	boolean isSwingAttack();

	AnimationState slamAnimationState();
}
