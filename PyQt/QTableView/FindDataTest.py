'''
在QTableView中查找数据

QTableView是一种表格视图，它可以显示多行多列的数据。在使用QTableView时，我们经常需要查找数据。本文将介绍如何在QTableView中查找数据。

首先，我们需要创建一个QTableView。
'''
import sys

from PyQt5.QtGui import QFont, QColor, QBrush
from PyQt5.QtWidgets import (QApplication, QTableView,QTableWidget,QTableWidgetItem,
                             QWidget, QMainWindow, QTableWidgetItem, QVBoxLayout)
from PyQt5.QtCore import Qt

class MainWindow(QWidget):
    def __init__(self):
        super().__init__()

        self.InitUI()

    def InitUI(self):
        self.tableWidget = QTableWidget()
        layout = QVBoxLayout()
        layout.addWidget(self.tableWidget)

        #设置数据
        self.tableWidget.setRowCount(40)
        self.tableWidget.setColumnCount(5)
        for i in range(40):
            for j in range(5):
                item = QTableWidgetItem("Row %d, Column %d" % (i, j))
                self.tableWidget.setItem(i, j, item)


        text = 'Row 16, Column 1'

        items = self.tableWidget.findItems(text, Qt.MatchExactly)

        if len(items) > 0:
            item = items[0]
            item.setForeground(QBrush(QColor(Qt.red))) #设置字体颜色
            item.setBackground(QBrush(QColor(Qt.yellow))) #设置背景颜色
            self.tableWidget.setCurrentItem(item)  #定位到当前item

        self.setLayout(layout)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    mainWindow = MainWindow()
    mainWindow.show()
    sys.exit(app.exec_())