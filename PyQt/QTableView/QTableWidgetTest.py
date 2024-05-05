'''

QTableWidget 使用示例

'''

import sys
from PyQt5.QtWidgets import (QWidget,QTableWidget,QAbstractItemView,
                             QHBoxLayout,QTableWidgetItem,QVBoxLayout,QApplication)
class TableWidgetTest(QWidget):
    def __init__(self):
        super(TableWidgetTest,self).__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('QTableWidget')
        self.resize(500,500)
        layout = QHBoxLayout()
        tablewidget = QTableWidget()
        tablewidget.setRowCount(5)
        tablewidget.setColumnCount(4)

        layout.addWidget(tablewidget)

        tablewidget.setHorizontalHeaderLabels(['姓名','性别','体重','身高'])
        nameItem = QTableWidgetItem('张三')
        sexItem = QTableWidgetItem('男')
        weightItem = QTableWidgetItem('180')
        heightItem = QTableWidgetItem('180')

        tablewidget.setItem(0,0,nameItem)
        tablewidget.setItem(0,1,sexItem)
        tablewidget.setItem(0,2,weightItem)
        tablewidget.setItem(0,3,heightItem)

        #禁止编辑
        tablewidget.setEditTriggers(QTableWidget.NoEditTriggers)

        #整行选中
        tablewidget.setSelectionBehavior(QAbstractItemView.SelectRows)

        #调整行和列
        tablewidget.resizeRowsToContents()
        tablewidget.resizeColumnsToContents()

        #隐藏表头
        tablewidget.horizontalHeader().setVisible(False)

        #隐藏垂直头
        tablewidget.verticalHeader().setVisible(False)

        #隐藏表格线
        tablewidget.setShowGrid(False)

        self.setLayout(layout)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    main = TableWidgetTest()
    main.show()
    sys.exit(app.exec_())
