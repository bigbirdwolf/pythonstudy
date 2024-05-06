'''
QTableView 上下文菜单
'''

import sys
from PyQt5.QtWidgets import QApplication, QVBoxLayout, QTableWidget, QMenu, QAction,QWidget,QTableWidgetItem
from PyQt5.QtCore import Qt

class MyTableWidget(QWidget):
    def __init__(self, parent=None):
        super(MyTableWidget, self).__init__(parent)
        self.InitUI()

        self.setWindowTitle('')

    def InitUI(self):
        layout = QVBoxLayout()
        self.tableWidget = QTableWidget(self)
        self.tableWidget.setRowCount(5)
        self.tableWidget.setColumnCount(5)
        self.tableWidget.setHorizontalHeaderLabels(['A', 'B', 'C', 'D', 'E'])
        self.tableWidget.setVerticalHeaderLabels(['1', '2', '3', '4', '5'])
        self.tableWidget.setItem(0, 0, QTableWidgetItem('1'))
        self.tableWidget.setItem(0, 1, QTableWidgetItem('2'))
        self.tableWidget.setItem(0, 2, QTableWidgetItem('3'))
        self.tableWidget.setItem(0, 3, QTableWidgetItem('4'))
        self.tableWidget.setItem(0, 4, QTableWidgetItem('5'))
        self.tableWidget.setItem(1, 0, QTableWidgetItem('6'))
        self.tableWidget.setItem(1, 1, QTableWidgetItem('7'))
        self.tableWidget.setItem(1, 2, QTableWidgetItem('8'))
        self.tableWidget.setItem(1, 3, QTableWidgetItem('9'))
        self.tableWidget.setItem(1, 4, QTableWidgetItem('10'))

        self.setContextMenuPolicy(Qt.CustomContextMenu)
        self.customContextMenuRequested.connect(self.ShowContextMenu)

        layout.addWidget(self.tableWidget)
        self.setLayout(layout)


    def ShowContextMenu(self, pos):
        # 获取选中行号
        for i in self.tableWidget.selectedIndexes():
            print(i.row(), i.column())
            rowNum = i.row()
        # 创建菜单
        menu = QMenu(self)
        action1 = QAction('Action 1', self)
        action2 = QAction('Action 2', self)
        action3 = QAction('Action 3', self)
        menu.addAction(action1)
        menu.addAction(action2)
        menu.addAction(action3)
        # 显示菜单
        action = menu.exec(self.mapToGlobal(pos))
        if action == action1:
            print('选择第1个菜单项',self.tableWidget.item(rowNum,0).text(),
                  self.tableWidget.item(rowNum,1).text(),
                  self.tableWidget.item(rowNum,2).text())

if __name__ == '__main__':
    app = QApplication(sys.argv)
    table = MyTableWidget()
    table.show()
    sys.exit(app.exec_())