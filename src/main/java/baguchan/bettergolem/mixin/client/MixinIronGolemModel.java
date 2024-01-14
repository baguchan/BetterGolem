package baguchan.bettergolem.mixin.client;

import baguchan.bettergolem.api.ISwingAttack;
import baguchan.bettergolem.client.GolemAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.IronGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IronGolemModel.class)
public abstract class MixinIronGolemModel<T extends IronGolem> extends HierarchicalModel<T> {

	@Inject(method = {"prepareMobModel(Lnet/minecraft/world/entity/animal/IronGolem;FFF)V"}, at = {@At("HEAD")})
	public void prepareMobModel(T p_102957_, float p_102958_, float p_102959_, float p_102960_, CallbackInfo ci) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
	}

	@Inject(method = {"setupAnim(Lnet/minecraft/world/entity/animal/IronGolem;FFFFF)V"}, at = {@At("TAIL")})
	public void setupAnimTail(T ironGolemEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
		float f = ageInTicks - ironGolemEntity.tickCount;
		if (ironGolemEntity instanceof ISwingAttack swingAttack) {
			this.animate(swingAttack.slamAnimationState(), GolemAnimations.ground_attack, ageInTicks);
		}
	}

	public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
