'''

合并单元格

QTableView 支持合并单元格，即将一行或一列的单元格合并成一个单元格。合并单元格可以方便地显示数据，提高数据的可读性。

合并单元格的操作非常简单，只需要在 QTableWidget 中选中需要合并的单元格，然后按下 Alt+Shift+上下左右键，即可将所选单元格合并。

下面是合并单元格的示例代码：

'''
import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QTableWidget, QTableWidgetItem,QWidget,QVBoxLayout

class MyWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('合并单元格')
        self.resize(400, 300)

        layout = QVBoxLayout()

        # 创建 QTableWidget
        self.tableWidget = QTableWidget()
        self.tableWidget.setRowCount(4)
        self.tableWidget.setColumnCount(4)
        self.tableWidget.setHorizontalHeaderLabels(['姓名', '性别', '年龄', '电话'])

        self.tableWidget.setItem(0, 0, QTableWidgetItem('张三'))
        self.tableWidget.setItem(0, 1, QTableWidgetItem('男'))
        self.tableWidget.setItem(0, 2, QTableWidgetItem('25'))
        self.tableWidget.setItem(0, 3, QTableWidgetItem('13812345678'))

        self.tableWidget.setItem(1, 0, QTableWidgetItem('李四'))
        self.tableWidget.setItem(1, 1, QTableWidgetItem('女'))
        self.tableWidget.setItem(1, 2, QTableWidgetItem('23'))
        self.tableWidget.setItem(1, 3, QTableWidgetItem('13812345678'))

        self.tableWidget.setItem(2, 0, QTableWidgetItem('赵六'))
        self.tableWidget.setItem(2, 1, QTableWidgetItem('男'))
        self.tableWidget.setItem(2, 2, QTableWidgetItem('22'))
        self.tableWidget.setItem(2, 3, QTableWidgetItem('13812345678'))

        self.tableWidget.setItem(3, 0, QTableWidgetItem('王五'))
        self.tableWidget.setItem(3, 1, QTableWidgetItem('女'))
        self.tableWidget.setItem(3, 2, QTableWidgetItem('24'))
        self.tableWidget.setItem(3, 3, QTableWidgetItem('13812345678'))


        # 合并单元格
        self.tableWidget.setSpan(0, 0, 4, 1)
        self.tableWidget.setSpan(0, 1, 1, 2)
        layout.addWidget(self.tableWidget)
        self.setLayout(layout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWin = MyWindow()
    myWin.show()
    sys.exit(app.exec_())