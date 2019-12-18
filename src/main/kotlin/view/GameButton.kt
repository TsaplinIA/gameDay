package view

import javafx.scene.control.Button

class GameButton(val plusD: Int, val plusM: Int): Button() {
    init {
        text = "+${plusD + plusM}"
        setPrefSize(60.0,30.0)
    }
}