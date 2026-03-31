package com.example;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class Menu extends Screen {
    
    private boolean fullbrightEnabled = false;

    public Menu() {
        super(Text.literal("Menu"));
    }

    @Override
    protected void init() {
        // Кнопка для включения/выключения Fullbright
        ButtonWidget fullbrightButton = ButtonWidget.builder(
            Text.literal("Fullbright: OFF"),
            button -> {
                fullbrightEnabled = !fullbrightEnabled;
                if (fullbrightEnabled) {
                    MinecraftClient.getInstance().options.getGamma().setValue(100.0);
                    button.setMessage(Text.literal("Fullbright: ON"));
                } else {
                    MinecraftClient.getInstance().options.getGamma().setValue(1.0);
                    button.setMessage(Text.literal("Fullbright: OFF"));
                }
            }
        )
        .dimensions(this.width / 2 - 75, this.height / 2 - 20, 150, 20)
        .build();
        
        this.addDrawableChild(fullbrightButton);
        
        // Кнопка закрытия
        ButtonWidget closeButton = ButtonWidget.builder(
            Text.literal("Close"),
            button -> this.close()
        )
        .dimensions(this.width / 2 - 50, this.height / 2 + 20, 100, 20)
        .build();
        
        this.addDrawableChild(closeButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(
            this.textRenderer, 
            this.title, 
            this.width / 2, 
            this.height / 2 - 50, 
            0xFFFFFF
        );
        super.render(context, mouseX, mouseY, delta);
    }
}