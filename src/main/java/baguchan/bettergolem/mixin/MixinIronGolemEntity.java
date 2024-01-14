package baguchan.bettergolem.mixin;

import baguchan.bettergolem.api.ISwingAttack;
import baguchan.bettergolem.entity.goal.SwingAttackGoal;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({IronGolem.class})
public class MixinIronGolemEntity extends AbstractGolem implements ISwingAttack {
	@Unique
	private boolean swingAttack;

	@Unique
	private AnimationState slamAnimationState = new AnimationState();

	public MixinIronGolemEntity(EntityType<? extends AbstractGolem> type, Level worldIn) {
		super(type, worldIn);
	}

	@Inject(method = {"registerGoals"}, at = {@At("TAIL")})
	protected void registerGoals(CallbackInfo callbackInfo) {
		this.goalSelector.addGoal(0, new SwingAttackGoal<>(this));
	}

	@Override
	public void tick() {
		super.tick();
	}

	public void setSwingAttack(boolean attack) {
		this.swingAttack = attack;
	}

	public boolean isSwingAttack() {
		return this.swingAttack;
	}

	@Inject(method = "handleEntityEvent", at = @At("HEAD"), cancellable = true)
	public void handleEntityEvent(byte p_28844_, CallbackInfo ci) {
		if (p_28844_ == 64) {
			this.slamAnimationState().start(this.tickCount);
			ci.cancel();
		}
	}

	@Override
	public AnimationState slamAnimationState() {
		return this.slamAnimationState;
	}
}
