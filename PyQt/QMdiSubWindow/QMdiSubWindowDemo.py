'''

多文档窗口示例

'''
import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QAction, QMdiArea, QMdiSubWindow, QTextEdit
from PyQt5.QtCore import Qt


class MultiDocumentWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('Multi Document Window')
        self.setGeometry(100, 100, 600, 400)

        self.mdi = QMdiArea()
        self.setCentralWidget(self.mdi)

        newAct = QAction('New', self)
        newAct.triggered.connect(self.newDocument)

        self.toolbar = self.addToolBar('New')
        self.toolbar.addAction(newAct)

    def newDocument(self):
        sub = QMdiSubWindow()
        editor = QTextEdit()
        sub.setWidget(editor)
        self.mdi.addSubWindow(sub)
        sub.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = MultiDocumentWindow()
    ex.show()
    sys.exit(app.exec_())
