'''
Created on 2019年5月29日

@author: wolf

树形控件基本用法

This signal is emitted when the user clicks inside the widget.

The specified item is the item that was clicked. The column is the item's column that was clicked. If no item was clicked, no signal will be emitted.
'''

import sys

from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QApplication, QTreeWidget, QTreeWidgetItem, QHeaderView, QWidget, QVBoxLayout, QHBoxLayout, \
    QPushButton


class TreeWidget(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('Tree Widget')
        self.resize(600,400)

        self.tree = QTreeWidget()
        self.tree.setHeaderLabels(['Key', 'Value']) #设置树形控件的头部标签
        self.tree.setColumnCount(2) #设置列数
        self.tree.setAlternatingRowColors(True) #设置交替行颜色
        self.tree.setIndentation(20) #设置缩进
        self.tree.setHeaderHidden(False) #设置头部是否隐藏

        #设置根节点
        root = QTreeWidgetItem(self.tree)
        root.setText(0, 'Root')
        root.setIcon(0,QIcon('../images/down.jpeg'))
        root.setExpanded(True) #设置根节点是否展开

        #设置子节点
        child1 = QTreeWidgetItem(root)
        child1.setText(0, 'Child1')
        child1.setText(1, 'Value1')
        child1.setIcon(0,QIcon('../images/down.jpeg'))

        child2 = QTreeWidgetItem(root)
        child2.setText(0, 'Child2')
        child2.setText(1, 'Value2')

        child3 = QTreeWidgetItem(root)
        child3.setText(0, 'Child3')
        child3.setText(1, 'Value3')

        #设置子节点的子节点
        child11 = QTreeWidgetItem(child1)
        child11.setText(0, 'Child11')
        child11.setText(1, 'Value11')

        child12 = QTreeWidgetItem(child1)
        child12.setText(0, 'Child12')
        child12.setText(1, 'Value12')

        child21 = QTreeWidgetItem(child2)
        child21.setText(0, 'Child21')
        child21.setText(1, 'Value21')

        child22 = QTreeWidgetItem(child2)
        child22.setText(0, 'Child22')
        child22.setText(1, 'Value22')

        child31 = QTreeWidgetItem(child3)
        child31.setText(0, 'Child31')
        child31.setText(1, 'Value31')

        child32 = QTreeWidgetItem(child3)
        child32.setText(0, 'Child32')
        child32.setText(1, 'Value32')

        #设置子节点的子节点的子节点
        child111 = QTreeWidgetItem(child11)
        child111.setText(0, 'Child111')
        child111.setText(1, 'Value111')

        child112 = QTreeWidgetItem(child11)
        child112.setText(0, 'Child112')
        child112.setText(1, 'Value112')

        child121 = QTreeWidgetItem(child12)
        child121.setText(0, 'Child121')
        child121.setText(1, 'Value121')

        child122 = QTreeWidgetItem(child12)
        child122.setText(0, 'Child122')
        child122.setText(1, 'Value122')

        child211 = QTreeWidgetItem(child21)
        child211.setText(0, 'Child211')
        child211.setText(1, 'Value211')

        child212 = QTreeWidgetItem(child21)
        child212.setText(0, 'Child212')
        child212.setText(1, 'Value212')

        child221 = QTreeWidgetItem(child22)
        child221.setText(0, 'Child221')
        child221.setText(1, 'Value221')

        child222 = QTreeWidgetItem(child22)
        child222.setText(0, 'Child222')
        child222.setText(1, 'Value222')

        child311 = QTreeWidgetItem(child31)
        child311.setText(0, 'Child311')
        child311.setText(1, 'Value311')

        child312 = QTreeWidgetItem(child31)
        child312.setText(0, 'Child312')
        child312.setText(1, 'Value312')

        child321 = QTreeWidgetItem(child32)
        child321.setText(0, 'Child321')
        child321.setText(1, 'Value321')

        child322 = QTreeWidgetItem(child32)
        child322.setText(0, 'Child322')
        child322.setText(1, 'Value322')

        #设置树形控件的布局
        self.tree.header().setSectionResizeMode(QHeaderView.ResizeToContents) #设置头部标签的宽度自适应
        self.tree.header().setStretchLastSection(True) #设置最后一列的宽度自动拉伸

        #设置信号槽
        self.tree.itemClicked.connect(self.onItemClicked)

        #设置布局
        layout = QVBoxLayout()
        hlayout = QHBoxLayout()
        addBtn = QPushButton('Add')
        addBtn.clicked.connect(self.addItem)
        modifyBtn = QPushButton('Modify')
        deleteBtn = QPushButton('Delete')
        deleteBtn.clicked.connect(self.deleteItem)
        hlayout.addWidget(addBtn)
        hlayout.addWidget(modifyBtn)
        hlayout.addWidget(deleteBtn)
        layout.addLayout(hlayout)
        layout.addWidget(self.tree)
        self.setLayout(layout)

    def addItem(self):
        item = self.tree.currentItem()
        if item is None:
            item = QTreeWidgetItem(self.tree)
            item.setText(0, 'New Item')
            item.setText(1, 'New Value')
            item.setIcon(0, QIcon('../images/down.jpeg'))

        else:
            item = QTreeWidgetItem(item)
            item.setText(0, 'New Item')
            item.setText(1, 'New Value')
            item.setIcon(0,QIcon('../images/down.jpeg'))

    def modifyItem(self):
        item = self.tree.currentItem()

    def deleteItem(self):
        item = self.tree.currentItem()
        if item is None:
            return
        parent = item.parent()
        print(parent)
        if parent is not None:
            parent.removeChild(item)
        else:
            self.tree.takeTopLevelItem(self.tree.indexOfTopLevelItem(item))

    def onItemClicked(self, item, column):
        print('Item key:', item.text(0), 'Column:', column,'', 'Value:', item.text(1))


if __name__ == '__main__':
    app = QApplication(sys.argv)
    tree = TreeWidget()
    tree.show()
    sys.exit(app.exec_())