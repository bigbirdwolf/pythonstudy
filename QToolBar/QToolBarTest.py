import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QAction, QToolBar

class ToolbarExample(QMainWindow):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('Toolbar Example')
        self.setGeometry(300, 300, 300, 200)

        exitAct = QAction('Exit', self)
        exitAct.triggered.connect(self.close)

        saveAct = QAction('Save', self)

        newAct = QAction('New', self)

        self.toolbar = QToolBar(self)
        self.toolbar.addAction(newAct)
        self.toolbar.addAction(saveAct)
        self.toolbar.addAction(exitAct)

        self.addToolBar(self.toolbar)

app = QApplication(sys.argv)
ex = ToolbarExample()
ex.show()
sys.exit(app.exec_())
