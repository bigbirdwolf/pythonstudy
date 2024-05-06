'''
修改单元格字体和颜色
'''


import sys
from PyQt5.QtWidgets import (QTableView, QWidget,
                             QTableWidgetItem, QTableWidget, QHBoxLayout, QApplication, QVBoxLayout)
from PyQt5.QtGui import QColor, QFont

class QTableWidgetModifyFont(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        layout = QVBoxLayout()
        self.tableWidget = QTableWidget()
        self.tableWidget.setRowCount(4)
        self.tableWidget.setColumnCount(4)
        self.tableWidget.setHorizontalHeaderLabels(['姓名', '性别', '年龄', '电话'])
        self.tableWidget.setAlternatingRowColors(True)

        item1 = QTableWidgetItem('张三')
        self.tableWidget.setItem(0, 0, item1)
        item2 = QTableWidgetItem('男')
        self.tableWidget.setItem(0, 1, item2)
        item3 = QTableWidgetItem('25')
        self.tableWidget.setItem(0, 2, item3)
        item4 = QTableWidgetItem('13812345678')
        self.tableWidget.setItem(0, 3, item4)


        item1.setFont(QFont('Arial', 12, QFont.Bold))
        item1.setForeground(QColor(255, 0, 0))

        layout.addWidget(self.tableWidget)
        self.setLayout(layout)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = QTableWidgetModifyFont()
    ex.show()
    sys.exit(app.exec_())