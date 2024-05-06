'''
单于格中放置图片示例
'''
import sys
from PyQt5.QtWidgets import (QApplication, QTableView, QVBoxLayout,
                             QTableWidgetItem, QAbstractItemView, QHeaderView, QTableWidget,QListWidget)
from PyQt5.QtGui import QIcon, QPixmap

class MyTable(QTableView):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        layout = QVBoxLayout()
        self.setWindowTitle('单元格中放置图片')
        self.resize(400, 300)
        self.tablWidget = QTableWidget()
        self.tablWidget.setRowCount(3)
        self.tablWidget.setColumnCount(3)
        self.tablWidget.setHorizontalHeaderLabels(['姓名', '性别', '年龄', '照片'])

        newItem = QTableWidgetItem('张三')
        newItem.setIcon(QIcon('../images/down.jpeg'))
        self.tablWidget.setItem(0, 0, newItem)

        layout.addWidget(self.tablWidget)
        self.setLayout(layout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = MyTable()
    ex.show()
    sys.exit(app.exec_())