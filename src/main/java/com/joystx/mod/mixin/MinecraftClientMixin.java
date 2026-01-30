package com.joystx.mod.mixin;

import net.minecraft.client.Minecraft; // Для Official Mappings используем Minecraft
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.joystx.mod.controller.ControllerManager;

@Mixin(Minecraft.class) // В Official Mappings миксин идет на класс Minecraft
public class MinecraftClientMixin { // Название класса ДОЛЖНО быть другим

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onClientInit(CallbackInfo ci) {
        ControllerManager.init();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onClientTick(CallbackInfo ci) {
        ControllerManager.update();
    }

    @Inject(method = "close", at = @At("HEAD"))
    private void onClientClose(CallbackInfo ci) {
        ControllerManager.shutdown();
    }
}