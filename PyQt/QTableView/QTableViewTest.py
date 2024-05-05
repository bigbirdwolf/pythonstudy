'''
QTableView控件使用的也是MVC模式，即Model-View-Controller模式。
Model：数据模型，即数据源，它是用来存储数据的，比如数据库中的数据。
View：视图，即显示数据的地方，比如表格控件。
Controller：控制器，即控制视图和模型之间的数据交互，比如双击某一行，则触发模型中的数据更新。

QTableView控件的使用步骤如下：
1. 创建数据模型，比如QSqlTableModel。
2. 创建视图，比如QTableView。
3. 设置视图的数据模型。
4. 连接视图的信号槽。
5. 运行程序。

下面是具体的代码：
'''
import sys
import PyQt5.QtCore as QtCore
from PyQt5.QtGui import QStandardItemModel, QStandardItem
from PyQt5.QtWidgets import QApplication, QTableView, QTableView, QVBoxLayout


class MainWindow(QTableView):
    def __init__(self):
        super(MainWindow, self).__init__()
        self.init_ui()

    def init_ui(self):
        self.setWindowTitle('QTableView控件使用')
        self.resize(600, 400)

        # 创建一个5行4列的数据模型
        self.tableModel = QStandardItemModel(5,4)
        self.tableModel.setHorizontalHeaderLabels(['id','姓名','职务','薪资']) #设置表头

        self.tableView = QTableView() #创建表格视图
        self.tableView.setModel(self.tableModel) #设置数据模型

        #添加数据第一行
        item11 = QStandardItem('10')
        item12 = QStandardItem('wolf')
        item13 = QStandardItem('程序员')
        item14 = QStandardItem('10000')

        #添加数据第四行
        item41 = QStandardItem('20')
        item42 = QStandardItem('tom')
        item43 = QStandardItem('技术总监')
        item44 = QStandardItem('20000')

        self.tableModel.setItem(0,0,item11)
        self.tableModel.setItem(0,1,item12)
        self.tableModel.setItem(0,2,item13)
        self.tableModel.setItem(0,3,item14)

        self.tableModel.setItem(3,0,item41)
        self.tableModel.setItem(3,1,item42)
        self.tableModel.setItem(3,2,item43)
        self.tableModel.setItem(3,3,item44)

        vLayout = QVBoxLayout()

        vLayout.addWidget(self.tableView)
        self.setLayout(vLayout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    mainWindow = MainWindow()
    mainWindow.show()
    sys.exit(app.exec_())

'''
运行程序后，会显示一个表格，表格中的数据来自数据库中的employee表。双击某一行会触发lambda表达式中的print语句。 
'''