package controller

import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.DatePicker
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import model.GameDate
import view.GameAnd
import view.GameBarN
import view.GameButton
import view.GameLabel
import java.awt.Desktop
import java.net.URI
import java.time.LocalDate


class ControllerN(private val primaryStage: Stage) {
    private val dateN = GameDate(0, 1) // та дата, с которое "играем"
    private val bot = Bot(dateN)
    private val btns = mutableListOf<GameButton>()
    var ok: Boolean = false

    var dayPlusOnePossible = dateN.dayPlusPossible(1)
    var dayPlusTwoPossible = dateN.dayPlusPossible(2)
    var monthPlusOnePossible = dateN.monthPlusPossible(1)
    var monthPlusTwoPossible = dateN.monthPlusPossible(2)


    var labelL = GameLabel()
    var labelR = GameLabel()
    val bar = GameBarN(this)

    var choiceIsOpen = false

    var winner = "Player"

    fun openChoice() {
        val lbl = Label("Choice Date: ")
        val choiceDate = Button("Ok!")
        val choice = Stage()
        val vPane = VBox(20.0)
        val picker = DatePicker(LocalDate.of(2019, 1, 1))
        vPane.children.addAll(lbl, picker, choiceDate)
        vPane.alignment = Pos.CENTER
        val testScene = Scene(vPane, 200.0, 200.0)
        choice.scene = testScene
        choiceDate.setOnAction {
            ok = true
            choice.close()
            dateN.setDate(picker.value.monthValue - 1, picker.value.dayOfMonth)
        }
        choice.isResizable = false
        choiceIsOpen = true
        choice.showAndWait()
        choiceIsOpen = false
    }

    fun openPrStage() {
        labelL.text = "" + dateN.day
        labelR.text = "" + (dateN.month + 1)
        val vLabelL = labelL
        val vLabelR = labelR
        val topLabelL = Label("day:")
        val topLabelR = Label("month:")

        addBtn(1, 0)
        addBtn(2, 0)
        addBtn(0, 1)
        addBtn(0, 2)

        val vBoxL = VBox(20.0)
        val topVBoxL = VBox(1.0)
        topVBoxL.alignment = Pos.CENTER
        topVBoxL.children.addAll(topLabelL, btns[1])
        vBoxL.children.addAll(topVBoxL, vLabelL, btns[0])
        vBoxL.alignment = Pos.CENTER

        val vBoxR = VBox(20.0)
        val topVBoxR = VBox(1.0)
        topVBoxR.alignment = Pos.CENTER
        topVBoxR.children.addAll(topLabelR, btns[3])
        vBoxR.children.addAll(topVBoxR, vLabelR, btns[2])
        vBoxR.alignment = Pos.CENTER

        val hBox = HBox(30.0)
        hBox.children.addAll(vBoxL, vBoxR)
        hBox.alignment = Pos.CENTER

        val root = BorderPane()
        root.center = hBox
        root.top = bar
        bar.prefWidth = root.width
        val scene = Scene(root)
        primaryStage.scene = scene
        primaryStage.isResizable = false
        resetBtns()
        primaryStage.show()
        winner = "Player"
        checkWin()
    }

    private fun addBtn(plusD: Int, plusM: Int) {
        val newBtn = GameButton(plusD, plusM)
        newBtn.setOnAction {
            dateN.plusDay(plusD)
            dateN.plusMouth(plusM)
            afterStep()
            if (!dateN.victoryNow()) {
                bot.step()
                afterStep()
            }
        }
        resetBtn(newBtn)
        btns.add(newBtn)
    }

    private fun checkWin() {
        if (dateN.victoryNow()) {
            primaryStage.close()
            GameAnd(this)
        }
    }

    private fun resetBtn(el: GameButton) {
        val plusD = el.plusD
        val plusM = el.plusM
        val temp = when {
            plusD == 1 -> dayPlusOnePossible
            plusD == 2 -> dayPlusTwoPossible
            plusM == 1 -> monthPlusOnePossible
            plusM == 2 -> monthPlusTwoPossible
            else -> false
        }
        el.isDisable = !temp
    }

    private fun resetBtns() {
        dayPlusOnePossible = dateN.dayPlusPossible(1)
        dayPlusTwoPossible = dateN.dayPlusPossible(2)
        monthPlusOnePossible = dateN.monthPlusPossible(1)
        monthPlusTwoPossible = dateN.monthPlusPossible(2)
        for (el in btns)
            resetBtn(el)
    }

    private fun afterStep() {
        resetBtns()
        winner = when (winner) {
            "Bot" -> "Player"
            else -> "Bot"
        }
//        println(winner)
        checkWin()
        labelL.text = "" + dateN.day
        labelR.text = "" + (dateN.month + 1)
    }

    fun goToGH() {
        Desktop.getDesktop().browse(URI("https://github.com/TsaplinIA"))
    }

    fun restartGame() {
        ok = false
        primaryStage.close()
        openChoice()
        openPrStage()
    }

    fun closeGame() {
        primaryStage.close()
    }

}