package baguchan.bettergolem.mixin.client;

import baguchan.bettergolem.api.ISwingAttack;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.IronGolem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({IronGolemModel.class})
public abstract class MixinIronGolemModel<T extends IronGolem> extends HierarchicalModel<T> {
	private ModelPart body;

	@Shadow
	@Final
	private ModelPart head;

	@Shadow
	@Final
	private ModelPart rightArm;

	@Shadow
	@Final
	private ModelPart leftArm;

	@Shadow
	@Final
	private ModelPart rightLeg;

	@Shadow
	@Final
	private ModelPart leftLeg;

	@Inject(method = {"<init>"}, at = {@At("TAIL")})
	public void init(ModelPart p_170697_, CallbackInfo callbackInfo) {
		this.body = p_170697_.getChild("body");
	}

	@Inject(method = {"setupAnim(Lnet/minecraft/world/entity/animal/IronGolem;FFFFF)V"}, at = {@At("TAIL")})
	public void setupAnim(T ironGolemEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
		float f = ageInTicks - ((IronGolem) ironGolemEntity).tickCount;
		if (((ISwingAttack) ironGolemEntity).getSwingAttackAnimationScale(f) > 0.0D) {
			float f2 = ((ISwingAttack) ironGolemEntity).getSwingAttackAnimationScale(f);
			f2 = 1.0F - f2 * f2;
			this.body.setRotation((Mth.PI / 4), 0, 0);
			this.body.setPos(0.0F, 1.0F, -8.0F);
			this.head.setPos(0.0F, -2.0F, -6.0F);
			setRotateAngle(this.head, 0.78F, 0.0F, 0.0F);
			this.rightArm.setPos(0.0F, 1.0F, -5.0F);
			setRotateAngle(this.rightArm, -0.78F + -1.0F * f2, 0.0F, 0.0F);
			this.leftArm.setPos(0.0F, 1.0F, -5.0F);
			setRotateAngle(this.leftArm, -0.78F + -1.0F * f2, 0.0F, 0.0F);
		} else {
			this.body.setRotation(0, 0, 0);
			this.body.setPos(0.0F, -7.0F, 0.0F);
			this.head.setPos(0.0F, -7.0F, 0.0F);
			this.rightArm.setPos(0.0F, -7.0F, 0.0F);
			this.leftArm.setPos(0.0F, -7.0F, 0.0F);
		}
	}

	public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
