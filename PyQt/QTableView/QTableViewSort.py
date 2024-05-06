'''
QTableWidget 数据排序

QTableView 控件提供了对表格数据的排序功能。
'''

import sys
from PyQt5.QtWidgets import (QApplication, QTableView, QTableWidget, QTableWidgetItem,QPushButton,
                             QAbstractItemView, QHeaderView, QVBoxLayout)
from PyQt5.QtCore import Qt, QSortFilterProxyModel

class QTableViewSortFilter(QAbstractItemView):
    def __init__(self):
        super().__init__()
        self.InitUI()

    def InitUI(self):
        layout = QVBoxLayout()
        self.tableWidget = QTableWidget()
        self.tableWidget.setSortingEnabled(True)
        self.tableWidget.setRowCount(5)
        self.tableWidget.setColumnCount(3)
        self.tableWidget.setHorizontalHeaderLabels(['姓名', '年龄', '性别'])
        self.tableWidget.setItem(0, 0, QTableWidgetItem('张三'))
        self.tableWidget.setItem(2, 0, QTableWidgetItem('王五'))
        self.tableWidget.setItem(3, 0, QTableWidgetItem('赵六'))
        self.tableWidget.setItem(4, 0, QTableWidgetItem('田七'))
        self.tableWidget.setItem(0, 1, QTableWidgetItem('20'))
        self.tableWidget.setItem(1, 1, QTableWidgetItem('25'))
        self.tableWidget.setItem(2, 1, QTableWidgetItem('30'))
        self.tableWidget.setItem(3, 1, QTableWidgetItem('35'))
        self.tableWidget.setItem(4, 1, QTableWidgetItem('40'))
        self.tableWidget.setItem(0, 2, QTableWidgetItem('男'))
        self.tableWidget.setItem(1, 2, QTableWidgetItem('女'))
        self.tableWidget.setItem(2, 2, QTableWidgetItem('男'))
        self.tableWidget.setItem(3, 2, QTableWidgetItem('女'))
        self.tableWidget.setItem(4, 2, QTableWidgetItem('男'))

        self.button = QPushButton('排序')
        self.button.clicked.connect(self.sortTable)

        self.orderType = Qt.AscendingOrder
        layout.addWidget(self.tableWidget)
        layout.addWidget(self.button)

        self.setLayout(layout)

    def sortTable(self):
        if self.orderType == Qt.DescendingOrder:
            self.orderType = Qt.AscendingOrder
        else:
            self.orderType = Qt.DescendingOrder
        self.tableWidget.sortItems(1, self.orderType)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = QTableViewSortFilter()
    ex.show()
    sys.exit(app.exec_())