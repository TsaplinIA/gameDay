package view

import controller.ControllerN
import javafx.scene.control.Alert
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem

class GameBarN(val cont: ControllerN): MenuBar() {
    val item1 = MenuItem("Rules")
    val item2 = MenuItem("My GitHub")
    val item3 = MenuItem("Restart")
    val item4 = MenuItem("Close")
    val menu = Menu("Help")
    val menu2 = Menu("Process")
    init {
        item1.setOnAction {
            val rule = Alert(Alert.AlertType.INFORMATION)
            rule.contentText = "[RU]\n" +
                    "Задается какая-то исходная дата ( день и месяц)." +
                    " Игра идет с роботом." +
                    " Каждый игрок на своем ходе задает более позднюю дату," +
                    " по сдедующему правилу: увеличивая на 1 или 2 либо день в месяце," +
                    " либо месяц (но не то и другое сразу)." +
                    " При этом сочетание дня и месяца должно оставаться реальной датой." +
                    " Игрок, задавший 31.12 проигрывает.\n\n\n" +
                    "[EN]\n" +
                    "Some initial date (day and month) is set." +
                    " The game is with a robot. Each player at his turn sets a later date," +
                    " according to the following rule:" +
                    " increasing by 1 or 2 either a day in a month or a month (but not both at once)." +
                    " In this case, the combination of day and month should remain a real date." +
                    " The player who asked 31.12 loses."
            rule.headerText = "Rules"
            rule.title = "Rules"
            rule.show()
        }

        item2.setOnAction {
            cont.goToGH()
        }

        item3.setOnAction {
            cont.restartGame()
        }
        item4.setOnAction {
            cont.closeGame()
        }
        menu.items.addAll(item1, item2)
        menu2.items.addAll(item3,item4)
        this.menus.addAll(menu,menu2)
    }
}