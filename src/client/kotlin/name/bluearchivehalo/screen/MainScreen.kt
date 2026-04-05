package name.bluearchivehalo.screen

import name.bluearchivehalo.config.Config
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.GridWidget
import net.minecraft.client.gui.widget.SimplePositioningWidget
import net.minecraft.text.Text



class MainScreen(parent: Screen): MyScreen(Text.of("Halo Settings"),parent) {
    override fun close() {
        client?.setScreen(parent)
        Config.save()
    }
    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
    }
    val chooseLevel = ButtonWidget.builder(Text.of("Per-Level Settings")){
        client?.setScreen(LevelChooseScreen(this))
    }.build() tooltip "Specific configuration for beacons at each level"
    val baseAlpha = slider(conf.baseAlpha,0f..1f) { Text.of("Base Opacity") }
    val mixWhite = slider(conf.mixWhite,0f..1f) { Text.of("White Tint") } tooltip "Blend in some white to make the visual effect brighter"
    val pulseTail = slider(conf.pulseTail,0.1f..1f) { Text.of("Pulse Trail Length") }
    val spacingMidAlpha = slider(conf.spacingMidAlpha,0f..1f) { Text.of("Spacing Mode Opacity") } tooltip "Opacity of the highlighted segments in spacing mode"
    val spacingAlpha = slider(conf.spacingAlpha,0f..1f) { Text.of("Spacing Gap Opacity") } tooltip "Opacity of the gap segments in spacing mode"
    val spacingCount = slider(conf.spacingCount,4..20) { Text.of("Spacing Count: ${conf.spacingCount.get}") }



    override fun init() {
        val gridWidget = GridWidget()
        gridWidget.mainPositioner.marginX(5).marginBottom(4).alignHorizontalCenter()
        val adder = gridWidget.createAdder(2)
        listOf(chooseLevel,baseAlpha,mixWhite,pulseTail,spacingMidAlpha,spacingAlpha,spacingCount)
            .forEach { adder.add(it) }
        adder.add(previewButton,2,adder.copyPositioner().marginTop(6))
        adder.add(done,2, adder.copyPositioner().marginTop(6))
        gridWidget.refreshPositions()
        SimplePositioningWidget.setPos(gridWidget, 0, height / 6 - 12,width,height, 0.5f, 0.0f)
        gridWidget.forEachChild(::addDrawableChild)

        super.init()
    }
}