'''
在单元格中添加控件

QTableView控件提供了一种简单的方法来在单元格中添加控件.
'''

import sys
from PyQt5.QtWidgets import (QApplication, QTableView, QVBoxLayout, QTableWidget,QHBoxLayout,QComboBox,
                             QTableWidgetItem, QWidget, QPushButton,QMainWindow)

class MyWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('在单元格中添加控件')
        self.resize(500, 300)

        widget = QWidget()

        layout = QHBoxLayout()
        tableWidget = QTableWidget()
        tableWidget.setColumnCount(4)
        tableWidget.setRowCount(4)

        layout.addWidget(tableWidget)

        tableWidget.setHorizontalHeaderLabels(['姓名', '性别', '年龄', '地址'])
        textItem = QTableWidgetItem('wolf')
        tableWidget.setItem(0, 0, textItem)

        combox = QComboBox()
        combox.addItem('男')
        combox.addItem('女')

        tableWidget.setCellWidget(0, 1, combox)
        widget.setLayout(layout)
        self.setCentralWidget(widget)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    sys.exit(app.exec_())