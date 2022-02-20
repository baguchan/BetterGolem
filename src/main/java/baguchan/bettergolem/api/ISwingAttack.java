package baguchan.bettergolem.api;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface ISwingAttack {
	void setSwingAttack(boolean paramBoolean);

	boolean isSwingAttack();

	@OnlyIn(Dist.CLIENT)
	float getSwingAttackAnimationScale(float paramFloat);
}
