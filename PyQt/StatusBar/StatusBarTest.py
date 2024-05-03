
import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QAction, QStatusBar

class StatusBarExample(QMainWindow):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('Status Bar Example')
        self.setGeometry(300, 300, 400, 200)

        self.statusBar = QStatusBar(self)
        self.setStatusBar(self.statusBar)

        openAct = QAction('Open', self)
        openAct.setStatusTip('Open a file')
        openAct.triggered.connect(self.openFile)

        self.toolbar = self.addToolBar('Open')
        self.toolbar.addAction(openAct)

    def openFile(self):
        self.statusBar.showMessage('File opened', 2000)  # 显示消息，持续2秒

app = QApplication(sys.argv)
ex = StatusBarExample()
ex.show()
sys.exit(app.exec_())
