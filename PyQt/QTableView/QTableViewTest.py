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

from PyQt5.QtSql import QSqlTableModel
from PyQt5.QtWidgets import QApplication, QTableView

app = QApplication(sys.argv)

# 创建数据模型
model = QSqlTableModel()
model.setTable("employee")
model.select()

# 创建视图
view = QTableView()
view.setModel(model)

# 设置视图的列宽
view.setColumnWidth(0, 100)
view.setColumnWidth(1, 100)
view.setColumnWidth(2, 100)

# 连接视图的信号槽
view.doubleClicked.connect(lambda: print("双击了某一行"))

# 运行程序
view.show()
sys.exit(app.exec_())

'''
运行程序后，会显示一个表格，表格中的数据来自数据库中的employee表。双击某一行会触发lambda表达式中的print语句。 
'''