'''
QDockWidget is a widget that can be docked in a main window.
It can be dragged and resized to be placed in different positions within the main window.
The dock widget can be floated as a separate window or docked in the main window.
'''

import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QDockWidget, QPushButton, QWidget, QTableView, QTableWidget, \
    QVBoxLayout, QLineEdit
from PyQt5.QtCore import Qt

class QDockWidgetTest(QMainWindow):
    def __init__(self):
        super().__init__()
        self.initUI()


    def initUI(self):
        self.setWindowTitle('QDockWidget')
        self.dockWidget = QDockWidget('Dock Widget', self)
        self.tableWidget = QTableWidget()

        self.tableWidget.setRowCount(4)
        self.tableWidget.setColumnCount(3)
        self.tableWidget.setHorizontalHeaderLabels(['Name', 'Age', 'Gender'])

        self.dockWidget.setWidget(self.tableWidget)
        self.addDockWidget(Qt.LeftDockWidgetArea, self.dockWidget)

        self.setCentralWidget(QLineEdit('Central Widget'))

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = QDockWidgetTest()
    ex.show()
    sys.exit(app.exec_())