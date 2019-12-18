package view

import controller.ControllerN
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.stage.Stage

class GameAnd(val controller: ControllerN): Stage() {
    val vBox = VBox()
    val root = Scene(vBox)
    val labelW = Label("${controller.winner} WIN!!!")
    val label = Label("Game Over")
    val btn = Button("Close")
    val btn2 = Button("Restart")
    init {
        btn.setOnAction {
            this.close()
        }
        btn2.setOnAction {
            this.close()
            controller.restartGame()
        }
        vBox.children.addAll(labelW, label,btn,btn2)
        vBox.alignment = Pos.CENTER
        this.scene = root
        val font = Font(50.0)
        label.font = font
        labelW.font = font
        this.show()
    }
}