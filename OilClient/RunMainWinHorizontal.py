import sys
from PyQt5.QtWidgets import QApplication, QMainWindow
from designer.MainWinHorizontal import Ui_MainWindow


if __name__ == '__main__':
    app = QApplication(sys.argv)    # create a new instance of QApplication
    mainWindow = QMainWindow()  # create a new window
    ui = Ui_MainWindow() # create a new instance of the UI class
    ui.setupUi(mainWindow)  # set up the UI
    mainWindow.show()   # show the window
    sys.exit(app.exec_())   # run the application