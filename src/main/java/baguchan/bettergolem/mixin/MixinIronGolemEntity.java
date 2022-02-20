package baguchan.bettergolem.mixin;

import baguchan.bettergolem.api.ISwingAttack;
import baguchan.bettergolem.entity.goal.SwingAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({IronGolem.class})
public abstract class MixinIronGolemEntity extends AbstractGolem implements ISwingAttack {
  private static final EntityDataAccessor<Boolean> SWING_ATTACK = SynchedEntityData.defineId(IronGolem.class, EntityDataSerializers.BOOLEAN);

  private float swingAttackAnimationProgress;
  
  private float lastSwingAttackAnimationProgress;
  
  public MixinIronGolemEntity(EntityType<? extends AbstractGolem> type, Level worldIn) {
    super(type, worldIn);
  }
  
  @Inject(method = {"defineSynchedData"}, at = {@At("TAIL")})
  protected void defineSynchedData(CallbackInfo callbackInfo) {
    this.entityData.define(SWING_ATTACK, Boolean.FALSE);
  }
  
  @Inject(method = {"registerGoals"}, at = {@At("TAIL")})
  protected void registerGoals(CallbackInfo callbackInfo) {
    this.goalSelector.addGoal(0, new SwingAttackGoal<>(this));
  }

  @Override
  public void tick() {
    super.tick();
    if (isAlive()) {
      this.lastSwingAttackAnimationProgress = this.swingAttackAnimationProgress;
      if (isSwingAttack()) {
        this.swingAttackAnimationProgress = Mth.clamp(this.swingAttackAnimationProgress + 0.2F, 0.0F, 1.0F);
      } else {
        this.swingAttackAnimationProgress = Mth.clamp(this.swingAttackAnimationProgress - 0.05F, 0.0F, 1.0F);
      }
    }
  }
  
  public void setSwingAttack(boolean attack) {
    this.entityData.set(SWING_ATTACK, attack);
  }
  
  public boolean isSwingAttack() {
    return this.entityData.get(SWING_ATTACK);
  }
  
  @OnlyIn(Dist.CLIENT)
  public float getSwingAttackAnimationScale(float ageInTicks) {
    return this.lastSwingAttackAnimationProgress + (this.swingAttackAnimationProgress - this.lastSwingAttackAnimationProgress) * ageInTicks;
  }
}
