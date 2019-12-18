package view

import javafx.scene.control.Label
import javafx.scene.text.Font

class GameLabel: Label() {
    init {
        this.font = Font(50.0)
    }
}