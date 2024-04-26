import sys
from PyQt5.QtWidgets import QMainWindow, QApplication
from designer.MainWinBuddy import Ui_MainWindow

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(window)
    window.show()
    sys.exit(app.exec_())